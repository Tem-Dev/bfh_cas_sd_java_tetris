package tetris.figures;

import tetris.Tetris;
import tetris.gui.Block;

public abstract class Figure {

    protected int color;
    protected int centerBlockX;
    protected int centerBlockY;
    protected Block[] blocks;

    public Figure() {
        this.centerBlockX = Tetris.getDefaultWidth() / 2 - 1;
        this.centerBlockY = Tetris.getDefaultHeight() - 1;
    }

    public void move(Direction direction) {
        switch (direction) {
            case LEFT:
                for (Block b : blocks) b.x--;
                break;
            case RIGHT:
                for (Block b : blocks) b.x++;
                break;
            case DOWN:
                for (Block b : blocks) b.y--;
                break;
            default:
                break;
        }
    }

    public abstract void rotate(Direction direction);

    public Block[] getBlocks() {
        return blocks;
    }
}
