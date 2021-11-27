package tetris.figures;

import tetris.Direction;
import tetris.Tetris;
import tetris.gui.Block;

public abstract class Figure {

    protected int color;
    protected int centerBlockX;
    protected int centerBlockY;
    protected Block[] blocks;
    protected Block centerBlock;
    protected Block block2, block3, block4;

    public Figure() {
        this.centerBlockX = Tetris.getDefaultWidth() / 2 - 1;
        this.centerBlockY = Tetris.getDefaultHeight() - 1;
    }

    public void move(Direction direction) {
        switch (direction) {
            case LEFT: for (Block b : blocks) b.x--; break;
            case RIGHT: for (Block b : blocks) b.x++; break;
            case DOWN: for (Block b : blocks) b.y--; break;
            case UP: for (Block b : blocks) b.y++; break;
            default: break;
        }
    }

    public void rotate(Direction direction)  {
        // TODO : what if i rotate a block out of playfield or into other blocks?
        switch (direction) {
            case LEFT: for (Block block : this.getBlocks()) rotateBlockLeft(block); break;
            case RIGHT: for (Block block : this.getBlocks()) rotateBlockRight(block); break;
            default: break;
        }
    }

    protected void rotateBlockLeft(Block block) {
        int move = 0;
        if (block.x == centerBlock.x) {
            move = centerBlock.y - block.y;
            block.y += move;
        } else if (block.y == centerBlock.y) {
            move = centerBlock.x - block.x;
            block.y += move * -1;
        } else {
            if ((block.x > centerBlock.x && block.y > centerBlock.y) || (block.x < centerBlock.x && block.y < centerBlock.y)) {
                move = (centerBlock.x - block.x) * 2;
            } else {
                block.y += (centerBlock.y - block.y) * 2;
            }
        }
        block.x += move;
    }

    protected void rotateBlockRight(Block block) {
        int move = 0;
        if (block.x == centerBlock.x) {
            move = centerBlock.y - block.y;
            block.x += move * -1;
        } else if (block.y == centerBlock.y) {
            move = centerBlock.x - block.x;
            block.x += move;
        } else {
            if ((block.x > centerBlock.x && block.y > centerBlock.y) || (block.x < centerBlock.x && block.y < centerBlock.y)) {
                move = (centerBlock.x - block.x) * 2;
            } else {
                block.x += (centerBlock.y - block.y) * -2;
            }
        }
        block.y += move;
    }

    public Block[] getBlocks() {
        return blocks;
    }
}
