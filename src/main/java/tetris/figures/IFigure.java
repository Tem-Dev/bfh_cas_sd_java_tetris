package tetris.figures;

import tetris.gui.Block;

public class IFigure extends Figure {

    public IFigure() {
        this.color = 5;
        centerBlock = new Block(centerBlockX, centerBlockY, color);
        block2 = new Block(centerBlockX - 1, centerBlockY, color);
        block3 = new Block(centerBlockX + 1, centerBlockY, color);
        block4 = new Block(centerBlockX + 2, centerBlockY, color);
        this.blocks = new Block[] {centerBlock, block2, block3, block4};
    }
}
