import tetris.gui.ActionEvent;
import tetris.gui.Block;
import tetris.gui.GUI;

public class Game {

    private final GUI gui;

    public Game(GUI gui) {
        this.gui = gui;
    }

    public void start() {
        Block block = new Block(5, 5, 5);
        gui.drawBlock(block);

        while(true) {
            ActionEvent event = gui.waitEvent();
            if (event.equals(ActionEvent.MOVE_LEFT) && block.x > 0)
                block.x--;
            if (event.equals(ActionEvent.MOVE_RIGHT) && block.x <= 8)
                block.x++;
            if (event.equals(ActionEvent.MOVE_DOWN) && block.y > 0)
                block.y--;
            if (event.equals(ActionEvent.ROTATE_LEFT) && block.y <= 13)
                block.y++;
            gui.clear();
            gui.drawBlock(block);
        }
    }
}
