import rendering.Panel;

import javax.swing.JFrame;

/**
 * Main entry point for the program.
 * Sets up the window and initializes the panel.
 */

public class Main {
    static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Untitled Project");

        Panel panel = new Panel();
        window.add(panel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.startGameThread();
    }
}