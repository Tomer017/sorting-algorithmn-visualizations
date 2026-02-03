package main;

import rendering.Panel;

import javax.swing.*;
import java.awt.*;

/**
 * main.Main entry point for the program.
 * Sets up the window and initializes the panel.
 */

public class Main {
    public static JSlider slider;

    public static void main(String[] args) {
        JFrame window = new JFrame();
        JPanel controls = new JPanel();
        slider = new JSlider();
        JButton button = new JButton("Start");

        controls.add(slider);
        controls.add(button);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setTitle("Sorting Algorithmn Visualizations");

        Panel panel = new Panel();
        window.add(panel, BorderLayout.CENTER);
        window.add(controls, BorderLayout.NORTH);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.startGameThread();
    }
}