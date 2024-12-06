package screens;

import game.FlappyBird;
import game.Score;

import javax.swing.*;
import java.awt.*;

public class GameController {
    private int width = 360;
    private int height = 640;
    private JFrame frame;
    private JPanel panel;
    private CardLayout cardLayout;
    private FlappyBird flappyBird;
    private StartScreen startScreen;
    private Score score;

    public void startGame() {

        frame = new JFrame("Flappy Bird");
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        score = new Score();
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        panel.add(createStartScreen(score), "Start");
        panel.add(createGameScreen(score), "Game");

        frame.add(panel);
        frame.setVisible(true);
    }

    private JPanel createStartScreen(Score score) {
        startScreen = new StartScreen((e -> {
            cardLayout.show(panel, "Game");
            flappyBird.requestFocus();
            flappyBird.startGame();
        }),score);
        return startScreen;
    }

    private JPanel createGameScreen(Score score) {
        flappyBird = new FlappyBird(score);
        return flappyBird;
    }
}
