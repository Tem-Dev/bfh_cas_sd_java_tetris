import tetris.gui.ActionEvent;
import tetris.gui.Block;
import tetris.gui.GUI;

public class Game {

    private final GUI gui;
    private final int width;
    private final int height;
    private Block block;

    public Game(GUI gui, int width, int height) {
        this.gui = gui;
        this.width = width;
        this.height = height;
    }

    public void start() {
        createBlock();
        while(true) {
            handleEvent(gui.waitEvent());
        }
    }

    private void createBlock() {
        this.block = new Block(width / 2, height - 1, 5);
        gui.drawBlock(this.block);
    }

    private void handleEvent(ActionEvent event) {
        // TODO: rewrite to switch (new switch?)
        if (event.equals(ActionEvent.MOVE_LEFT) && block.x > 0)
            block.x--;
        if (event.equals(ActionEvent.MOVE_RIGHT) && block.x < this.width - 1)
            block.x++;
        if (event.equals(ActionEvent.MOVE_DOWN) && block.y > 0)
            block.y--;
        if (event.equals(ActionEvent.ROTATE_LEFT) && block.y < this.height - 1)
            block.y++;
        updateGUI();
    }

    private void updateGUI() {
        gui.clear();
        gui.drawBlock(this.block);
    }
}
