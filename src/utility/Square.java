package utility;

import java.awt.*;

public class Square {
    private final int value;
    private final int width;
    private int x;
    private int y;

    public Square(int value, int width, int x, int y){
        this.value = value;
        this.width = width;
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getValue() {
        return value;
    }
    public void update() {
    }

    public void draw(Graphics2D g2d) {
        // To be implemented
        g2d.fillRect(x, y, width, value);

    }
}
