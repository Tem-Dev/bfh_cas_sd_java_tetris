package tetris;

import tetris.figures.*;
import tetris.gui.ActionHandler;
import tetris.gui.GUI;

import java.util.Random;

public class Game {

    private Random random = new Random();

    private final GUI gui;
    private Figure figure;
    private Field field;

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

    private void updateGUI() {
        gui.clear();
        gui.drawBlocks(this.figure.getBlocks());
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
            while (field.detectCollisionDirection(figure) != Direction.DOWN) {
                figure.move(Direction.DOWN);
                updateGUI();
            }
        }
    }
}
