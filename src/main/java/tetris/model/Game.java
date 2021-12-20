package tetris.model;

import tetris.model.figures.*;
import tetris.gui.ActionHandler;
import tetris.gui.Block;
import tetris.gui.GUI;

import java.util.Random;

public class Game {

    private final Random random = new Random();

    private final GUI gui;
    private Figure figure;
    private final Field field;
    private final Scoring scoring;
    private FigureController figureController;

    public Game(int width, int height, GUI gui) {
        this.gui = gui;
        this.field = new Field(width, height);
        this.scoring = new Scoring();
    }

    public void start() {
        createFigure();
        figureController = new FigureController();
        gui.setActionHandler(figureController);
        figureController.start();
    }

    private void createFigure() {
        switch (random.nextInt(7)) {
            case 0: this.figure = new IFigure(); break;
            case 1: this.figure = new JFigure(); break;
            case 2: this.figure = new LFigure(); break;
            case 3: this.figure = new OFigure(); break;
            case 4: this.figure = new SFigure(); break;
            case 5: this.figure = new TFigure(); break;
            case 6: this.figure = new ZFigure(); break;
            default: break;
        }
        gui.drawBlocks(this.figure.getBlocks());
    }

    // think of better method name
    private void betweenTurns() {
        landFigure(figure);
        int removedRows = field.removeFullRows();
        scoring.updateScore(removedRows);
        createFigure();
        checkDoGameOver();
        updateGUI();
    }

    private void landFigure(Figure figure) {
        field.addBlocks(figure.getBlocks());
    }

    private void checkDoGameOver() {
        if (isGameOver()) stop();
    }

    private boolean isGameOver() {
        for (Block figureBlock : figure.getBlocks()) {
            if (field.detectCollisionDirection(figure) == Direction.DOWN && figureBlock.y == field.getHeight() - 1) return true;
        }
        return false;
    }

    private void stop() {
        gui.setActionHandler(null);
        figureController.interrupt();
        figure = null;
        scoring.updateHighScore();
        updateGUI();
    }

    private void updateGUI() {
        gui.clear();
        if (figure != null) gui.drawBlocks(this.figure.getBlocks());
        gui.drawBlocks(this.field.getBlocks());
        gui.setLevel(scoring.getLevel());
        gui.setScore(scoring.getScore());
        gui.setHighScore(scoring.getHighScore());
    }

    private class FigureController extends Thread implements ActionHandler {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep((long) (1000 * Math.exp(-0.1 * scoring.getLevel())));
                    moveDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public synchronized void moveDown() {
            // TODO : throws exceptions when game ends because of moveDown
            if (!doMovement(figure -> figure.move(Direction.DOWN), figure -> figure.move(Direction.UP))) betweenTurns();
        }

        @Override
        public synchronized void moveLeft() {
            doMovement(figure -> figure.move(Direction.LEFT), figure -> figure.move(Direction.RIGHT));
        }

        @Override
        public synchronized void moveRight() {
            doMovement(figure -> figure.move(Direction.RIGHT), figure -> figure.move(Direction.LEFT));
        }

        @Override
        public synchronized void rotateLeft() {
            doMovement(figure -> figure.rotate(Direction.LEFT), figure -> figure.rotate(Direction.RIGHT));
        }

        @Override
        public synchronized void rotateRight() {
            doMovement(figure -> figure.rotate(Direction.RIGHT), figure -> figure.rotate(Direction.LEFT));
        }

        @Override
        public synchronized void drop() {
            // TODO : throws exceptions when game ends because of drop
            while (doMovement(figure -> figure.move(Direction.DOWN), figure -> figure.move(Direction.UP))) {}
            betweenTurns();
        }

        private boolean doMovement(Movement movement, Movement reverseMovement) {
            try {
                movement.make(figure);
                field.detectCollision(figure);
                updateGUI();
                return true;
            } catch (CollisionException e) {
                reverseMovement.make(figure);
                return false;
            }
        }
    }
}
