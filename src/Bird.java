import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Bird {

    private int birdX;

    private int birdY;

    private int birdWidth;
    private int birdHeight;
    private Image image;
    Image birdImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/flappybird.png"))).getImage();

    public Bird(int birdX, int birdY, int birdWidth, int birdHeight) {
        this.birdX = birdX;
        this.birdY = birdY;
        this.birdWidth = birdWidth;
        this.birdHeight = birdHeight;
        this.image = birdImage;
    }

    public int getBirdX() {
        return birdX;
    }

    public int getBirdY() {
        return birdY;
    }

    public void resetBirdY(int birdY){
        this.birdY = birdY;
    }
    public void setBirdY(int birdY) {
        this.birdY += birdY;
        this.birdY = Math.max(this.birdY, 0);
    }

    public int getBirdWidth() {
        return birdWidth;
    }

    public int getBirdHeight() {
        return birdHeight;
    }

    public Image getImage() {
        return image;
    }

}
