package tetris;

import tetris.figures.Direction;
import tetris.figures.Figure;
import tetris.figures.FigureI;
import tetris.gui.ActionEvent;
import tetris.gui.GUI;

public class Game {

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
        this.figure = new FigureI();
        gui.drawBlocks(this.figure.getBlocks());
    }

    private void handleEvent(ActionEvent event) {
        // TODO : add border collision
        switch (event) {
            case MOVE_LEFT:
                figure.move(Direction.LEFT);
                break;
            case MOVE_RIGHT:
                figure.move(Direction.RIGHT);
                break;
            case MOVE_DOWN:
                figure.move(Direction.DOWN);
                break;
            case ROTATE_LEFT:
                figure.rotate(Direction.LEFT);
                break;
            case ROTATE_RIGHT:
                figure.rotate(Direction.RIGHT);
                break;
            default:
                break;
        }
        updateGUI();
    }

    private void updateGUI() {
        gui.clear();
        gui.drawBlocks(this.figure.getBlocks());
    }
}
