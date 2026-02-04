package sorting;

import utility.Frame;
import utility.Square;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the insertion sort algorithm and generates frames for visualization.
 */
public class InsertionSort {
    private static final Color SORTED_COLOR = Color.GREEN;
    private static final Color COMPARING_COLOR = Color.RED;
    private static final Color CURRENT_COLOR = Color.BLUE;

    /**
     * Performs insertion sort on a frame and generates a sequence of frames
     * showing each step of the sorting process.
     *
     * @param initialFrame The initial unsorted frame
     * @return A list of frames representing each step of the sorting process
     */
    public static List<Frame> sort(Frame initialFrame) {
        List<Frame> frames = new ArrayList<>();
        Frame workingFrame = copyFrame(initialFrame);

        recalculatePositions(workingFrame);
        frames.add(copyFrame(workingFrame));

        for (int i = 1; i < workingFrame.size(); i++) {
            Square key = workingFrame.get(i);
            int keyValue = key.getValue();
            int j = i - 1;

            setSquareColor(workingFrame.get(i), CURRENT_COLOR);
            frames.add(copyFrame(workingFrame));

            while (j >= 0 && workingFrame.get(j).getValue() > keyValue) {
                setSquareColor(workingFrame.get(j), COMPARING_COLOR);
                frames.add(copyFrame(workingFrame));

                workingFrame.set(j + 1, workingFrame.get(j));
                j--;

                workingFrame.set(j + 1, key);
                recalculatePositions(workingFrame);
                frames.add(copyFrame(workingFrame));
            }

            for (int k = 0; k <= i; k++) {
                if (!workingFrame.get(k).getColor().equals(SORTED_COLOR)) {
                    setSquareColor(workingFrame.get(k), SORTED_COLOR);
                }
            }

            frames.add(copyFrame(workingFrame));
        }

        for (Square square : workingFrame) {
            setSquareColor(square, SORTED_COLOR);
        }
        frames.add(copyFrame(workingFrame));

        return frames;
    }

    /**
     * Creates a deep copy of a frame.
     *
     * @param frame The frame to copy
     * @return A new frame with copied squares
     */
    private static Frame copyFrame(Frame frame) {
        Frame copy = new Frame(0);
        for (Square square : frame) {
            Square squareCopy = new Square(
                square.getValue(),
                square.getWidth(),
                square.getX(),
                square.getY()
            );
            squareCopy.setColor(getSquareColor(square));
            copy.add(squareCopy);
        }
        return copy;
    }

    /**
     * Recalculates the x positions of all squares in the frame based on their current indices.
     *
     * @param frame The frame to recalculate positions for
     */
    private static void recalculatePositions(Frame frame) {
        if (frame.isEmpty()) {
            return;
        }

        int screenWidth = rendering.Panel.getScreenWidth();
        int screenHeight = rendering.Panel.getScreenHeight();
        int squareWidth = frame.get(0).getWidth();
        int totalUsedWidth = frame.size() * squareWidth;
        int startX = (screenWidth - totalUsedWidth) / 2;

        for (int i = 0; i < frame.size(); i++) {
            Square square = frame.get(i);
            int x = startX + (i * squareWidth);
            int y = screenHeight - square.getValue();
            square.setPosition(x, y);
        }
    }

    private static void setSquareColor(Square square, Color color) {
        square.setColor(color);
    }

    private static Color getSquareColor(Square square) {
        return square.getColor();
    }
}
