package tetris.model.figures;

import tetris.gui.Block;

public class LFigure extends Figure {

    public LFigure() {
        this.color = 3;
        centerBlock = new Block(centerBlockX, centerBlockY, color);
        block2 = new Block(centerBlockX - 1, centerBlockY, color);
        block3 = new Block(centerBlockX + 1, centerBlockY, color);
        block4 = new Block(centerBlockX + 1 , centerBlockY + 1, color);
        this.blocks = new Block[] {centerBlock, block2, block3, block4};
    }
}
