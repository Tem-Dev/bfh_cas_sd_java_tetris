package tetris;

import tetris.figures.Figure;
import tetris.gui.Block;

public class Field {

    private final int width, height;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void detectCollision(Figure figure) throws CollisionException {
        for (Block block : figure.getBlocks()) {
            if (block.x < 0 || block.x >= width || block.y < 0 || block.y >= height)
                // uses exception handling in logic because the exercise made me implement it that way
                // I suggest making this method return a bool and not moving out of border in the first place
                throw new CollisionException("Detected collision with field border!");
        }
    }

    public Direction detectCollisionDirection(Figure figure) {
        for (Block block : figure.getBlocks()) {
            if (block.x < 0 ) return Direction.LEFT;
            if (block.x >= width) return Direction.RIGHT;
            if (block.y <= 0) return Direction.DOWN;
            if (block.y >= height) return Direction.UP;
        }
        return null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
