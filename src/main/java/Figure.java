import tetris.gui.Block;

public class Figure {

    private final int color;
    private Block[] blocks;

    public Figure(int color, Block[] blocks) {
        this.color = color;
        this.blocks = blocks;
    }

    public void move(Direction direction) {
        switch (direction) {
            case LEFT:
                for (Block b : blocks) b.x--;
                break;
            case RIGHT:
                for (Block b : blocks) b.x++;
                break;
            case DOWN:
                for (Block b : blocks) b.y--;
                break;
            default:
                break;
        }
    }

    public void rotate(Direction direction) {
        // soll bei 4 Rotationen in dieselbe Richtung am gleichen Ort landen
        switch (direction) {
            case LEFT:
                //for (Block b : blocks) b.x--;
                break;
            case RIGHT:
                //for (Block b : blocks) b.x++;
                break;
            default:
                break;
        }
    }

    public Block[] getBlocks() {
        return blocks;
    }
}
