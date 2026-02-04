package utility;

import java.awt.*;

/**
 * Represents a single bar/square in the sorting visualization.
 * Each square has a value (height), width, and position.
 */
public class Square {
    private final int value;
    private final int width;
    private int x;
    private int y;
    private Color color;

    /**
     * Constructs a new square with the specified dimensions and position.
     *
     * @param value The height/value of this square
     * @param width The width of this square
     * @param x The x-coordinate of the top-left corner
     * @param y The y-coordinate of the top-left corner
     */
    public Square(int value, int width, int x, int y) {
        this.value = value;
        this.width = width;
        this.x = x;
        this.y = y;
        this.color = Color.BLACK;
    }

    /**
     * Gets the width of this square.
     *
     * @return The width in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the value (height) of this square.
     *
     * @return The value/height in pixels
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the x-coordinate of this square.
     *
     * @return The x-coordinate in pixels
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of this square.
     *
     * @return The y-coordinate in pixels
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the position of this square.
     *
     * @param x The new x-coordinate
     * @param y The new y-coordinate
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the color of this square.
     *
     * @return The current color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of this square.
     *
     * @param color The new color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Updates the state of this square.
     * Currently unused, but can be implemented for animations.
     */
    public void update() {
    }

    /**
     * Renders this square.
     *
     * @param g2d The Graphics2D context to render with
     */
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect(x, y, width, value);
    }
}
