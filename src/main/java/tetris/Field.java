package tetris;

import tetris.figures.Figure;
import tetris.gui.Block;

import java.util.ArrayList;
import java.util.Arrays;

public class Field {

    private final int width, height;
    private ArrayList<Block> blockList;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.blockList = new ArrayList<>();
    }

    public void detectCollision(Figure figure) throws CollisionException {
        for (Block figureBlock : figure.getBlocks()) {
            if (figureBlock.x < 0 || figureBlock.x >= width || figureBlock.y < 0 || figureBlock.y >= height)
                // uses exception handling in logic because the exercise made me implement it that way
                // I suggest making this method return a bool and not moving out of border in the first place
                throw new CollisionException("Detected collision with field border!");
            for (Block fieldBlock : blockList) {
                if (figureBlock.x == fieldBlock.x && figureBlock.y == fieldBlock.y) {
                    throw new CollisionException("Detected collision with field border!");
                }
            }
        }
    }

    public Direction detectCollisionDirection(Figure figure) {
        for (Block figureBlock : figure.getBlocks()) {
            if (figureBlock.x < 0 ) return Direction.LEFT;
            if (figureBlock.x >= width) return Direction.RIGHT;
            if (figureBlock.y <= 0) return Direction.DOWN;
            if (figureBlock.y >= height) return Direction.UP;
            for (Block fieldBlock : blockList) {
                if (figureBlock.x == fieldBlock.x + 1 && figureBlock.y == fieldBlock.y) return Direction.LEFT;
                if (figureBlock.x == fieldBlock.x - 1 && figureBlock.y == fieldBlock.y) return Direction.RIGHT;
                if (figureBlock.x == fieldBlock.x && figureBlock.y == fieldBlock.y + 1) return Direction.DOWN;
                if (figureBlock.x == fieldBlock.x && figureBlock.y == fieldBlock.y - 1) return Direction.UP;
            }
        }
        return null;
    }

    public ArrayList<Block> getBlockList() {
        return blockList;
    }

    public void addBlocks(Block... blocks) {
        blockList.addAll(Arrays.asList(blocks));
    }

    public void removeAllBlocks() {
        blockList.clear();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
