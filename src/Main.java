import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // init window
        int width = 360;
        int height = 640;
        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(width,height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        // ignore titlebar height
        frame.pack();
        flappyBird.requestFocus();
        frame.setVisible(true);
    }


}