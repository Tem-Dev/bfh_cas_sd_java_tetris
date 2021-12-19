package tetris.model;

import java.io.*;
import java.util.Scanner;

public class Scoring {

    private int score = 0;
    private int highScore = 0;
    private int removedRows = 0;

    private static final String HIGHSCORE_FILE = "highscore.txt";
    private static final int ROWS_PER_LEVEL = 10;
    private static final int[] ROW_COUNT_REWARDS = {0, 40, 100, 300, 1200};

    public Scoring() {
        loadHighScore();
    }

    public int getLevel() {
        return removedRows / ROWS_PER_LEVEL + 1;
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public void updateScore(int removedRows) {
        this.removedRows += removedRows;
        score += ROW_COUNT_REWARDS[removedRows];
    }

    public void updateHighScore() {
        if (score > highScore) {
            highScore = score;
            saveHighScore();
        }
    }

    public void reset() {
        removedRows = 0;
        score = 0;
    }

    private void loadHighScore() {
        try (Scanner scanner = new Scanner(new FileInputStream(HIGHSCORE_FILE))) {
            highScore = Integer.parseInt(scanner.nextLine());
        } catch (FileNotFoundException e) {
            // ignore non existent highscore file
        } catch (NumberFormatException e) {
            System.err.println("Highscore could not be read");
        }
    }

    private void saveHighScore() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(HIGHSCORE_FILE))) {
            writer.println(highScore);
        } catch (IOException e) {
            System.err.println("Highscore could not be written");
        }
    }
}
