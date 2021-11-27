package tetris.model.figures;

import tetris.model.Direction;
import tetris.gui.Block;

public class OFigure extends Figure {

    public OFigure() {
        this.color = 2;
        centerBlock = new Block(centerBlockX, centerBlockY, color);
        block2 = new Block(centerBlockX, centerBlockY + 1, color);
        block3 = new Block(centerBlockX + 1, centerBlockY, color);
        block4 = new Block(centerBlockX + 1 , centerBlockY + 1, color);
        this.blocks = new Block[] {centerBlock, block2, block3, block4};
    }

    @Override
    public void rotate(Direction direction) {
        // O figure should not rotate
    }
}
