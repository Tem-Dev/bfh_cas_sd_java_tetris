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

    public Game(int width, int height, GUI gui) {
        this.field = new Field(width, height);
        this.gui = gui;
    }

    public void start() {
        createFigure();
        gui.setActionHandler(new FigureController());
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
        field.removeFullRows();
        createFigure();
        checkDoGameOver();
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
        figure = null;
        gui.setActionHandler(null);
        updateGUI();
    }

    private void updateGUI() {
        gui.clear();
        if (figure != null) gui.drawBlocks(this.figure.getBlocks());
        gui.drawBlocks(this.field.getBlocks());
    }

    private class FigureController implements ActionHandler {

        @Override
        public void moveDown() {
            try {
                figure.move(Direction.DOWN);
                field.detectCollision(figure);
                updateGUI();
            } catch (CollisionException e) {
                figure.move(Direction.UP);
                betweenTurns();
            }
        }

        @Override
        public void moveLeft() {
            try {
                figure.move(Direction.LEFT);
                field.detectCollision(figure);
                updateGUI();
            } catch (CollisionException e) {
                figure.move(Direction.RIGHT);
            }
        }

        @Override
        public void moveRight() {
            try {
                figure.move(Direction.RIGHT);
                field.detectCollision(figure);
                updateGUI();
            } catch (CollisionException e) {
                figure.move(Direction.LEFT);
            }
        }

        @Override
        public void rotateLeft() {
            try {
                figure.rotate(Direction.LEFT);
                field.detectCollision(figure);
                updateGUI();
            } catch (CollisionException e) {
                figure.rotate(Direction.RIGHT);
            }
        }

        @Override
        public void rotateRight() {
            try {
                figure.rotate(Direction.RIGHT);
                field.detectCollision(figure);
                updateGUI();
            } catch (CollisionException e) {
                figure.rotate(Direction.LEFT);
            }
        }

        @Override
        public void drop() {
            try {
                while (true) {
                    // uses exception handling in logic because the exercise made me implement it that way
                    figure.move(Direction.DOWN);
                    field.detectCollision(figure);
                    updateGUI();
                }
            } catch (CollisionException e) {
                figure.move(Direction.UP);
                betweenTurns();
            }
        }
    }
}
