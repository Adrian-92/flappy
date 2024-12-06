package game;

import game.Bird;
import game.Pipe;
import game.Score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;


public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    private Runnable returnToStartScreen;
    int boardWidth = 360;
    int boardHeight = 640;
    Image backgroundImage;
    Image topPipeImage;
    Image bottomPipeImage;

    int birdX = boardWidth / 8;
    int birdY = boardHeight / 2;
    int birdWidth = 34;
    int birdHeight = 24;
    Bird bird;

    Timer gameLoop;
    Timer placePipesTimer;

    // move up and down
    int velocityY = 0;
    int velocityX = -4;
    int gravity = 1;
    boolean gameOver = false;
    Score roundScore;

    /////////////////////// pipes /////////////////////////
    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;


    public FlappyBird(Score score, Runnable returnToStartScreen) {
        this.roundScore = score;
        this.returnToStartScreen = returnToStartScreen;
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);
        backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("../assets/flappybirdbg.png"))).getImage();
        topPipeImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("../assets/toppipe.png"))).getImage();
        bottomPipeImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("../assets/bottompipe.png"))).getImage();
        bird = new Bird(birdX, birdY, birdWidth, birdHeight);
        pipes = new ArrayList<>();
    }

    public void startGame() {
        // place pipes timer | new pipe every 1,5 sek
        placePipesTimer = new Timer(1500, e -> placePipes());
        placePipesTimer.start();
        // 60 fps
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public void resetGame() {
        gameOver = false;
        bird.resetBirdY(birdY);
        velocityY = 0;
        pipes.clear();
        roundScore.resetScore();
        gameLoop.start();
        placePipesTimer.start();
    }

    public void stopGame() {
        resetGame();
        gameLoop.stop();
        placePipesTimer.stop();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // background
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
        g.drawImage(bird.getImage(), bird.getBirdX(), bird.getBirdY(), bird.getBirdWidth(), bird.getBirdHeight(), null);
        for (Pipe pipe : pipes) {
            g.drawImage(pipe.getImage(), pipe.getPipeX(), pipe.getPipeY(), pipe.getWidth(), pipe.getHeight(), null);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " + ((int) roundScore.getScore()), 10, 35);
        } else {
            g.drawString(String.valueOf((int) roundScore.getScore()), 10, 35);
        }
    }

    ///////////////////// game logic //////////////////////

    public void move() {
        // bird
        velocityY += gravity;
        bird.setBirdY(velocityY);
        for (Pipe pipe : pipes) {
            pipe.setPipeX(velocityX);

            if (collision(bird, pipe)) {
                gameOver = true;
            }

            if (!pipe.isPassed() && bird.getBirdX() > pipe.getPipeX() + pipe.getWidth()) {
                pipe.setPassed(true);
                roundScore.addPoints(0.5);
            }
        }
        if (bird.getBirdY() > boardHeight) {
            gameOver = true;
        }

    }

    public void placePipes() {
        // random pipe placement height | (0 to 1) * (pipeHeight /2) -> (0 to 256)
        int randomPipeY = (int) (pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));

        int openingSpace = boardHeight / 4;

        Pipe topPipe = new Pipe(pipeX, pipeY, pipeWidth, pipeHeight, topPipeImage);
        topPipe.setPipeY(randomPipeY);
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(pipeX, pipeY, pipeWidth, pipeHeight, bottomPipeImage);
        bottomPipe.setPipeY(topPipe.getPipeY() + pipeHeight + openingSpace);
        pipes.add(bottomPipe);
    }


    public boolean collision(Bird bird, Pipe pipe) {
        return bird.getBirdX() < pipe.getPipeX() + pipe.getWidth() &&    // birds top left corner doesn't reach pipes top right corner
                bird.getBirdX() + bird.getBirdWidth() > pipe.getPipeX() && // birds top right corner doesn't reach pipes top left corner
                bird.getBirdY() < pipe.getPipeY() + pipe.getHeight() && // birds top left corner doesn't reach pipes bottom left corner
                bird.getBirdY() + bird.getBirdHeight() > pipe.getPipeY(); // birds bottom left corner doesn't reach pipes top left corner
    }

    // action performed every frame
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            placePipesTimer.stop();
            gameLoop.stop();
        }
    }

    // input handling

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                velocityY = -9;
                if (gameOver) {
                    resetGame();
                }
                break;
            case KeyEvent.VK_ENTER:
                resetGame();
                break;
            case KeyEvent.VK_ESCAPE:
                stopGame();
                if (returnToStartScreen != null) {
                    returnToStartScreen.run();
                }
                break;

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
