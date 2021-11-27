package tetris;

import tetris.figures.*;
import tetris.gui.ActionHandler;
import tetris.gui.GUI;

import java.util.Random;

public class Game {

    private Random random = new Random();

    private final GUI gui;
    private final int width;
    private final int height;
    private Figure figure;

    public Game(GUI gui, int width, int height) {
        this.gui = gui;
        this.width = width;
        this.height = height;
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
            figure.move(Direction.DOWN);
            updateGUI();
        }

        @Override
        public void moveLeft() {
            figure.move(Direction.LEFT);
            updateGUI();
        }

        @Override
        public void moveRight() {
            figure.move(Direction.RIGHT);
            updateGUI();
        }

        @Override
        public void rotateLeft() {
            figure.rotate(Direction.LEFT);
            updateGUI();
        }

        @Override
        public void rotateRight() {
            figure.rotate(Direction.RIGHT);
            updateGUI();
        }

        @Override
        public void drop() {

        }
    }
}
