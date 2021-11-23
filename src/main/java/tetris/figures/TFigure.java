package tetris.figures;

import tetris.gui.Block;

public class TFigure extends Figure {

    public TFigure() {
        this.color = 7;
        centerBlock = new Block(centerBlockX, centerBlockY, color);
        block2 = new Block(centerBlockX - 1, centerBlockY, color);
        block3 = new Block(centerBlockX, centerBlockY + 1, color);
        block4 = new Block(centerBlockX + 1 , centerBlockY, color);
        this.blocks = new Block[] {centerBlock, block2, block3, block4};
    }
}
