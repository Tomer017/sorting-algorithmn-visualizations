package rendering;

import sorting.InsertionSort;
import utility.Frame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Visualization panel that renders sorting algorithm animations.
 * Manages the render loop and displays frames of the sorting process.
 */
public class Panel extends JPanel implements Runnable {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int TARGET_FPS = 240;
    private static final int ANIMATION_FRAME_DELAY = 5;
    private static final long NANOSECONDS_PER_SECOND = 1_000_000_000L;
    private static final long NANOSECONDS_TO_MILLISECONDS = 1_000_000L;

    private Thread renderThread;
    private Frame currentFrame;
    private final JSlider squareCountSlider;

    private List<Frame> animationFrames;
    private int currentAnimationFrameIndex;
    private boolean isAnimating;
    private int frameDelayCounter;

    /**
     * Constructs a new visualization panel.
     *
     * @param squareCountSlider The slider controlling the number of squares to display
     */
    public Panel(JSlider squareCountSlider) {
        this.squareCountSlider = squareCountSlider;
        this.currentFrame = new Frame(squareCountSlider.getValue());
        this.animationFrames = new ArrayList<>();
        this.currentAnimationFrameIndex = 0;
        this.isAnimating = false;
        this.frameDelayCounter = 0;

        initializePanel();
        setupSliderListener();
    }

    private void initializePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
    }

    private void setupSliderListener() {
        squareCountSlider.addChangeListener(e -> {
            if (!squareCountSlider.getValueIsAdjusting() && !isAnimating) {
                int squareCount = squareCountSlider.getValue();
                currentFrame.regenerate(squareCount);
                repaint();
            }
        });
    }

    /**
     * Starts the sorting animation with the specified algorithm.
     *
     * @param algorithmName The name of the sorting algorithm to use
     */
    public void startSorting(String algorithmName) {
        if (isAnimating) {
            return;
        }

        switch (algorithmName) {
            case "Insertion Sort":
                animationFrames = InsertionSort.sort(currentFrame);
                break;
            case "Bubble Sort":
            case "Selection Sort":
            case "Merge Sort":
            case "Quick Sort":
                JOptionPane.showMessageDialog(this,
                    algorithmName + " is not yet implemented.",
                    "Not Implemented",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            default:
                return;
        }

        if (!animationFrames.isEmpty()) {
            currentAnimationFrameIndex = 0;
            isAnimating = true;
            squareCountSlider.setEnabled(false);
        }
    }

    /**
     * Gets the current frame being displayed.
     *
     * @return The current frame
     */
    public Frame getCurrentFrame() {
        return currentFrame;
    }

    /**
     * Checks if an animation is currently playing.
     *
     * @return true if animating, false otherwise
     */
    public boolean isAnimating() {
        return isAnimating;
    }

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    /**
     * Starts the rendering thread.
     */
    public void startRenderThread() {
        renderThread = new Thread(this);
        renderThread.start();
    }

    /**
     * Main render loop that maintains consistent frame rate.
     * Implements fixed time step rendering at the target FPS.
     */
    @Override
    public void run() {
        long drawInterval = NANOSECONDS_PER_SECOND / TARGET_FPS;
        long nextDrawTime = System.nanoTime() + drawInterval;

        while (renderThread != null) {
            update();
            repaint();

            try {
                long remainingTime = nextDrawTime - System.nanoTime();
                long sleepTimeMillis = remainingTime / NANOSECONDS_TO_MILLISECONDS;

                if (sleepTimeMillis > 0) {
                    Thread.sleep(sleepTimeMillis);
                }

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Updates the visualization state.
     */
    private void update() {
        if (isAnimating) {
            frameDelayCounter++;

            if (frameDelayCounter >= ANIMATION_FRAME_DELAY) {
                frameDelayCounter = 0;

                if (currentAnimationFrameIndex < animationFrames.size()) {
                    currentFrame = animationFrames.get(currentAnimationFrameIndex);
                    currentAnimationFrameIndex++;
                } else {
                    isAnimating = false;
                    squareCountSlider.setEnabled(true);
                    animationFrames.clear();
                }
            }
        } else {
            currentFrame.update();
        }
    }

    /**
     * Renders the current frame.
     *
     * @param g The Graphics object to render with
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        currentFrame.draw(g2d);

        g2d.dispose();
    }
}