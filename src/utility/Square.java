package utility;

import java.awt.*;

public class Square {
    private final int height;
    private final int width;
    private int x;
    private int y;

    public Square(int height, int width){
        this.height = height;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(Graphics2D g2d) {
        // To be implemented

    }
}
