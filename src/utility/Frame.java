package utility;

import java.awt.*;
import java.util.ArrayList;
import rendering.Panel;

public class Frame extends ArrayList<Square> {
    private static int deltaX;

    public Frame(int numSquares) {
        deltaX = Panel.getScreenWidth() / numSquares;
        generateInitialFrame(numSquares);
    }

    public void generateInitialFrame(int numSquares) {
        int xPos = 0;
        for (int i = 0; i < numSquares; i++) {
            int randomValue = (int) ((Math.random() * 300)) + 20;
            this.add(new Square(randomValue, deltaX, xPos, Panel.getScreenHeight() - randomValue));
            xPos += deltaX;
        }
    }

    public void update() {

    }

    public void draw(Graphics2D g2d) {
        for (Square square : this) {
            square.draw(g2d);
        }
    }
}
