package Day2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.IOException;

public class Bird {
    private int x = 100, y = 300;
    private int velocity = 0;
    private final int GRAVITY = 1, JUMP_STRENGTH = -9;
    private BufferedImage birdImage;

    public Bird() {
        try {
            URL imageUrl = new URL("https://img.poki-cdn.com/cdn-cgi/image/quality=78,width=1200,height=1200,fit=cover,f=png/5e0df231478aa0a331a4718d09dd91a2.png");
            birdImage = ImageIO.read(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            birdImage = null;
        }
    }

    public void jump() {
        velocity = JUMP_STRENGTH;
    }

    public void update() {
        velocity += GRAVITY;
        y += velocity;
        y = Math.max(y, 0);
    }

    public void draw(Graphics g) {
        if (birdImage != null) {
            g.drawImage(birdImage, x, y, 40, 40, null);
        } else {
            // Fallback design
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, 40, 40);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 40, 40);
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void reset() {
        y = 300;
        velocity = 0;
    }
}