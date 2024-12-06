package screens;

import game.Score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class StartScreen extends JPanel {
    private final int boardWidth = 360;
    private final int boardHeight = 640;
    private Image backgroundImage;

    public StartScreen(ActionListener startGameListener, Score score) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        JLabel titleLabel = new JLabel("Flappy Bird", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        add(titleLabel, BorderLayout.CENTER);
        JLabel highScoreLabel = new JLabel("Highscore: " + score.getHighScore(), JLabel.CENTER);
        highScoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        add(highScoreLabel, BorderLayout.NORTH);
        JButton startButton = new JButton("Spiel starten");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        startButton.addActionListener(startGameListener);
        add(startButton, BorderLayout.SOUTH);

        backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("../assets/flappybirdbg.png"))).getImage();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // background
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
    }
}
