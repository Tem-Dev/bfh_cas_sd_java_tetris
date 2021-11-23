package tetris;

import tetris.figures.Direction;
import tetris.figures.*;
import tetris.gui.ActionEvent;
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
        while(true) {
            handleEvent(gui.waitEvent());
        }
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

    private void handleEvent(ActionEvent event) {
        // TODO : add border collision
        switch (event) {
            case MOVE_LEFT: figure.move(Direction.LEFT); break;
            case MOVE_RIGHT: figure.move(Direction.RIGHT); break;
            case MOVE_DOWN: figure.move(Direction.DOWN); break;
            case ROTATE_LEFT: figure.rotate(Direction.LEFT); break;
            case ROTATE_RIGHT: figure.rotate(Direction.RIGHT); break;
            default: break;
        }
        updateGUI();
    }

    private void updateGUI() {
        gui.clear();
        gui.drawBlocks(this.figure.getBlocks());
    }
}
