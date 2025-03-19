package Day2;

import java.awt.*;

public class Pipe {
    private int x, gapY, gapHeight = 150;
    private static final int WIDTH = 50, SPEED = 5;

    public Pipe(int startX, int gapY) {
        this.x = startX;
        this.gapY = gapY;
    }

    public void update() {
        x -= SPEED;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        // Top pipe
        g.fillRect(x, 0, WIDTH, gapY);
        // Bottom pipe
        g.fillRect(x, gapY + gapHeight, WIDTH, 600 - gapY - gapHeight);
    }

    public boolean collidesWith(Rectangle bounds) {
        return new Rectangle(x, 0, WIDTH, gapY).intersects(bounds) ||
               new Rectangle(x, gapY + gapHeight, WIDTH, 600 - gapY - gapHeight).intersects(bounds);
    }

    public boolean isOffScreen() {
        return x < -WIDTH;
    }

    public boolean passed(int birdX) {
        return x + WIDTH < birdX && !isOffScreen();
    }

    public int getX() { return x; }
}