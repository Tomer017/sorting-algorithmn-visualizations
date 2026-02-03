package utility;

import java.awt.*;

public class Square {
    private final int value;
    private final int width;
    private int x;
    private int y;
    public static int index = 0;
    Color color = Color.BLACK;

    public Square(int value, int width, int x, int y){
        this.value = value;
        this.width = width;
        this.x = x;
        this.y = y;
        index++;
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
        if (index == 0) {
            g2d.setColor(Color.GREEN);
            g2d.fillRect(x, y, width, value);
        }
        g2d.fillRect(x, y, width, value);
    }
}
