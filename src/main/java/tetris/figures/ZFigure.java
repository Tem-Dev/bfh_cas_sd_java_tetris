package tetris.figures;

import tetris.gui.Block;

public class ZFigure extends Figure {

    Block block2, block3, block4;

    public ZFigure() {
        this.color = 1;
        centerBlock = new Block(centerBlockX, centerBlockY, color);
        block2 = new Block(centerBlockX, centerBlockY + 1, color);
        block3 = new Block(centerBlockX - 1, centerBlockY + 1, color);
        block4 = new Block(centerBlockX + 1 , centerBlockY, color);
        this.blocks = new Block[] {centerBlock, block2, block3, block4};
    }
}
