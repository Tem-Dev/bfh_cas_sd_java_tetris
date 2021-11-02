import tetris.gui.GUI;

public class Tetris {

    private static final int DEFAULT_WIDTH = 10;
    private static final int DEFAULT_HEIGHT = 15;

    public static void main(String[] args) {
        int width, height;

        try {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            width = DEFAULT_WIDTH;
            height = DEFAULT_HEIGHT;
        }

        GUI gui = new GUI(width, height);
        Game game = new Game(gui, width, height);
        game.start();
    }
}
