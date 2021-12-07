package tetris.model;

import tetris.gui.Block;
import tetris.model.figures.Figure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Field {

    private final int width, height;
    private final ArrayList<Block> blockList;

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

    public ArrayList<Block> getBlocks() {
        return blockList;
    }

    public void addBlocks(Block... blocks) {
        blockList.addAll(Arrays.asList(blocks));
    }

    public void removeAllBlocks() {
        blockList.clear();
    }

    public int removeFullRows() {
        int removedCount = 0;
        for (int y = height; y >= 0; y--) {
            if (isRowFull(y)) {
                removeRow(y);
                removedCount++;
            }
        }
        return removedCount;
    }

    private boolean isRowFull(int row) {
        int blockAmount = 0;
        for (Block block : blockList) {
            if (block.y == row) {
                blockAmount++;
            }
        }
        return blockAmount == width;
    }

    private void removeRow(int row) {
        Iterator<Block> iterator = blockList.iterator();
        while (iterator.hasNext()) {
            Block block = iterator.next();
            if (block.y == row) {
                iterator.remove(); // must be thru iterator, otherwise ConcurrentModificationException
            } else if (block.y > row) {
                block.y--;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
