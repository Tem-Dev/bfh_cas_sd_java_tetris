package tetris.figures;

import tetris.gui.Block;

public class FigureI extends Figure {

    Block centerBlock, block2, block3, block4;

    public FigureI() {
        this.color = 5;
        centerBlock = new Block(centerBlockX, centerBlockY, 1);
        block2 = new Block(centerBlockX - 1, centerBlockY, color);
        block3 = new Block(centerBlockX + 1, centerBlockY, color);
        block4 = new Block(centerBlockX + 2, centerBlockY, color);
        this.blocks = new Block[] {centerBlock, block2, block3, block4};
    }

    @Override
    public void rotate(Direction direction)  {
        // soll bei 4 Rotationen in dieselbe Richtung am gleichen Ort landen
        // TODO : what if i rotate a block out of playfield?
        switch (direction) {
            case LEFT:
                for (Block block : this.getBlocks()) rotateBlockLeft(block);
                break;
            case RIGHT:
                for (Block block : this.getBlocks()) rotateBlockRight(block);
                break;
            default:
                break;
        }
    }

    private void rotateBlockLeft(Block block) {
        int move;
        if (block.x == centerBlock.x) {
            move = centerBlock.y - block.y;
            block.y += move;
        } else {
            move = centerBlock.x - block.x;
            block.y += move * -1;
        }
        block.x += move;
    }

    private void rotateBlockRight(Block block) {
        int move;
        if (block.x == centerBlock.x) {
            move = centerBlock.y - block.y;
            block.x += move * -1;
        } else {
            move = centerBlock.x - block.x;
            block.x += move;
        }
        block.y += move;
    }

    private void moveBlockTo(Block block, int x, int y) {
        block.x = x;
        block.y = y;
    }
}
