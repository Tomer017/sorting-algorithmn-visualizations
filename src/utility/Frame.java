package utility;

import java.awt.*;
import java.util.ArrayList;
import rendering.Panel;

/**
 * Represents a single frame in the sorting visualization.
 * A frame contains a collection of squares to be rendered.
 */
public class Frame extends ArrayList<Square> {
    private static final int MIN_SQUARE_HEIGHT = 20;
    private static final int MAX_SQUARE_HEIGHT = 320;

    /**
     * Constructs a new frame with the specified number of squares.
     *
     * @param squareCount The number of squares to generate
     */
    public Frame(int squareCount) {
        regenerate(squareCount);
    }

    /**
     * Regenerates the frame with a new set of randomly sized squares.
     * Clears existing squares and creates new ones with random heights,
     * centering them if the screen width doesn't divide evenly.
     *
     * @param squareCount The number of squares to generate
     */
    public void regenerate(int squareCount) {
        if (squareCount <= 0) {
            return;
        }

        clear();

        int screenWidth = Panel.getScreenWidth();
        int screenHeight = Panel.getScreenHeight();
        int squareWidth = screenWidth / squareCount;
        int totalUsedWidth = squareCount * squareWidth;
        int startX = (screenWidth - totalUsedWidth) / 2;

        for (int i = 0; i < squareCount; i++) {
            int height = generateRandomHeight();
            int x = startX + (i * squareWidth);
            int y = screenHeight - height;

            add(new Square(height, squareWidth, x, y));
        }
    }

    private int generateRandomHeight() {
        int range = MAX_SQUARE_HEIGHT - MIN_SQUARE_HEIGHT;
        return (int) (Math.random() * range) + MIN_SQUARE_HEIGHT;
    }

    /**
     * Updates the state of all squares in this frame.
     */
    public void update() {
        for (Square square : this) {
            square.update();
        }
    }

    /**
     * Renders all squares in this frame.
     *
     * @param g2d The Graphics2D context to render with
     */
    public void draw(Graphics2D g2d) {
        for (Square square : this) {
            square.draw(g2d);
        }
    }
}
