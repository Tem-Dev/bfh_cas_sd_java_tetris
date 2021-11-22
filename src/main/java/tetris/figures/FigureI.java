package tetris.figures;

import tetris.gui.Block;

public class FigureI extends Figure {

    Block centerBlock, block2, block3, block4;

    public FigureI() {
        this.color = 5;
        centerBlock = new Block(centerBlockX, centerBlockY, color);
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
                if (block2.x < centerBlock.x) moveBlockTo(block2, centerBlock.x, centerBlock.y - 1); // if left of center
                else if (block2.y < centerBlock.y) moveBlockTo(block2, centerBlock.x + 1, centerBlock.y); // if over center
                else if (block2.x > centerBlock.x) moveBlockTo(block2, centerBlock.x, centerBlock.y + 1); // if right of center
                else if (block2.y > centerBlock.y) moveBlockTo(block2, centerBlock.x - 1, centerBlock.y); // if under center

                if (block3.x < centerBlock.x) moveBlockTo(block3, centerBlock.x, centerBlock.y - 1); // if left of center
                else if (block3.y < centerBlock.y) moveBlockTo(block3, centerBlock.x + 1, centerBlock.y); // if over center
                else if (block3.x > centerBlock.x) moveBlockTo(block3, centerBlock.x, centerBlock.y + 1); // if right of center
                else if (block3.y > centerBlock.y) moveBlockTo(block3, centerBlock.x - 1, centerBlock.y); // if under center

                if (block4.x < centerBlock.x) moveBlockTo(block4, centerBlock.x, centerBlock.y - 2); // if left of center
                else if (block4.y < centerBlock.y) moveBlockTo(block4, centerBlock.x + 2, centerBlock.y); // if over center
                else if (block4.x > centerBlock.x) moveBlockTo(block4, centerBlock.x, centerBlock.y + 2); // if right of center
                else if (block4.y > centerBlock.y) moveBlockTo(block4, centerBlock.x - 2, centerBlock.y); // if under center
                break;
            case RIGHT:
                // coming soon, just copy paste left case and reverse +-
                break;
            default:
                break;
        }
    }

    private void moveBlockTo(Block block, int x, int y) {
        block.x = x;
        block.y = y;
    }
}
