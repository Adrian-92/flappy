package game;

import java.awt.*;

public class Pipe {
    private int pipeX;
    private int pipeY;
    private int width;
    private int height;
    private Image image;
    private boolean passed = false;


    public Pipe(int pipeX, int pipeY, int width, int height, Image image) {
        this.pipeX = pipeX;
        this.pipeY = pipeY;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public int getPipeX() {
        return pipeX;
    }

    public void setPipeX(int pipeX) {
        this.pipeX += pipeX;
    }

    public int getPipeY() {
        return pipeY;
    }

    public void setPipeY(int pipeY) {
        this.pipeY = pipeY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }


    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
