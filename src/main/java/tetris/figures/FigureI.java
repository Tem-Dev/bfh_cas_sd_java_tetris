package tetris.figures;

import tetris.gui.Block;

public class FigureI extends Figure {

    Block block1, block2, block3, block4;

    public FigureI() {
        this.color = 5;
        block1 = new Block(centerBlockX, centerBlockY, color);
        block2 = new Block(centerBlockX - 1, centerBlockY, color);
        block3 = new Block(centerBlockX + 1, centerBlockY, color);
        block4 = new Block(centerBlockX + 2, centerBlockY, color);
        this.blocks = new Block[] {block1, block2, block3, block4};
    }

    @Override
    public void rotate(Direction direction)  {
        // soll bei 4 Rotationen in dieselbe Richtung am gleichen Ort landen
        switch (direction) {
            case LEFT:
                //for (Block b : blocks) b.x++;
                break;
            case RIGHT:
                //for (Block b : blocks) b.x++;
                break;
            default:
                break;
        }
    }
}
