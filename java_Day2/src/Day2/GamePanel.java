package Day2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
// Add these imports
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements ActionListener {
    // Declare backgroundImage
    private BufferedImage backgroundImage;
    private final int WIDTH = 800, HEIGHT = 600;
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private Timer timer;
    private Random random;
    private int score;
    private boolean gameOver;

    public GamePanel() {
        // Load background image from URL
        try {
            URL imageUrl = new URL("https://t4.ftcdn.net/jpg/06/06/54/49/360_F_606544986_2zeORxAa7x0pnUdfXNlBZof4QOB7qB43.jpg");
            backgroundImage = ImageIO.read(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            backgroundImage = null; // Fallback to solid color
        }
        
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        bird = new Bird();
        pipes = new ArrayList<>();
        random = new Random();
        timer = new Timer(16, this);
        score = 0;
        gameOver = false;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (!gameOver) bird.jump();
                    else resetGame();
                }
            }
        });

        spawnPipe();
        timer.start();
    }

    private void spawnPipe() {
        int height = 100 + random.nextInt(300);
        pipes.add(new Pipe(WIDTH, height));
    }

    private void resetGame() {
        bird.reset();
        pipes.clear();
        spawnPipe(); // Initial pipe after reset
        score = 0;
        gameOver = false;
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw background image (or fallback color)
        if (backgroundImage != null) {
            // Scale image to fill the entire panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        } else {
            // Fallback solid color background
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        // Draw game elements on top of background
        bird.draw(g);
        for (Pipe pipe : pipes) pipe.draw(g);
        
        // Draw score (with better visibility)
        g.setColor(Color.BLACK); // Changed to black for contrast
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString("Score: " + score, 20, 40);
        
        if (gameOver) {
            // Game Over text with background rectangle
            g.setColor(new Color(255, 0, 0, 200)); // Semi-transparent red
            g.fillRect(WIDTH/2 - 250, HEIGHT/2 - 50, 500, 100);
            g.setColor(Color.WHITE);
            g.drawString("Game Over! Press SPACE to restart", WIDTH/2 - 240, HEIGHT/2 + 15);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            bird.update();
            
            // Move pipes and check collisions
            for (int i = pipes.size() - 1; i >= 0; i--) {
                Pipe pipe = pipes.get(i);
                pipe.update();
                
                // Collision detection
                if (pipe.collidesWith(bird.getBounds())) gameOver = true;
                if (pipe.isOffScreen()) pipes.remove(i);
                if (pipe.passed(bird.getX())) score++;
            }
            
            // Spawn new pipes
            if (!pipes.isEmpty() && pipes.get(pipes.size() - 1).getX() < WIDTH - 300) {
                spawnPipe();
            }
            
            // Ground collision
            if (bird.getY() > HEIGHT - 50) gameOver = true;
        }
        repaint();
    }
}