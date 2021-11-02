import tetris.gui.GUI;

public class Tetris {

    public static void main(String[] args) {
        GUI gui = new GUI(10, 15);
        Game game = new Game(gui);
        game.start();
    }
}
