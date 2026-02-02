package rendering;

import utility.KeyHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Main game panel that handles the game loop, rendering, and core game components.
 * This class extends JPanel and implements Runnable to manage the game thread.
 */
public class Panel extends JPanel implements Runnable {
    /** Target frames per second */
    private final int fps = 60;

    /**Screen Width and Height */
    private final int screenWidth = 800;
    private final int screenHeight = 600;

    /** Main thread */
    private Thread gameThread;

    /** Handles keyboard input */
    private final KeyHandler keyH = new KeyHandler();

    /**
     * Constructs a new Panel and initializes the game settings.
     * Sets up the panel's size, background, and input handling.
     */
    public Panel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    /**
     * Starts the game thread and begins the game loop.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Main game loop that handles timing and updates.
     * Implements a fixed time step game loop to maintain consistent game speed.
     */
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Updates the game state.
     * Currently only updates the player, but can be expanded for other game elements.
     */
    public void update() {
        // Update logic in here
    }

    /**
     * Renders the game state.
     * Draws the tiles first, then the player on top.
     *
     * @param g The Graphics object to paint on
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw things here

        g2.dispose();
    }
}