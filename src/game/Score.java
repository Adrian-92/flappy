package game;

import java.io.*;

public class Score {
    private double score;
    private int highScore;
    private final String highScoreFile = "highscore.dat";

    public Score() {
        this.score = 0;
        loadHighScore();
    }

    public void addPoints(double points) {
        this.score += points;
        if (score > highScore) {
            highScore = (int) score;
            saveHighScore();
        }
    }

    public void resetScore() {
        this.score = 0;
    }

    public int getHighScore() {
        return highScore;
    }

    public double getScore() {
        return score;
    }

    private void saveHighScore() {
        try (FileWriter writer = new FileWriter(highScoreFile)) {
            writer.write(String.valueOf(highScore));
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern des Highscores: " + e.getMessage());
        }
    }

    private void loadHighScore() {
        File file = new File(highScoreFile);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                this.highScore = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.err.println("Fehler beim Laden des Highscores: " + e.getMessage());
                this.highScore = 0;
            }
        } else {
            this.highScore = 0;
        }
    }
}
