package main;

import rendering.Panel;

import javax.swing.*;
import java.awt.*;

/**
 * Main entry point for the sorting algorithm visualization program.
 * Sets up the application window and initializes UI components.
 */
public class Main {
    private static final int MIN_SQUARES = 5;
    private static final int MAX_SQUARES = 100;
    private static final int DEFAULT_SQUARES = 50;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame window = new JFrame("Sorting Algorithm Visualizer");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        JSlider squareCountSlider = createSquareCountSlider();
        JComboBox<String> algorithmSelector = createAlgorithmSelector();
        JButton startButton = new JButton("Start");

        Panel visualizationPanel = new Panel(squareCountSlider);

        setupStartButton(startButton, algorithmSelector, visualizationPanel);

        JPanel controlPanel = createControlPanel(squareCountSlider, algorithmSelector, startButton);

        window.add(visualizationPanel, BorderLayout.CENTER);
        window.add(controlPanel, BorderLayout.NORTH);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        visualizationPanel.startRenderThread();
    }

    private static void setupStartButton(JButton startButton, JComboBox<String> algorithmSelector, Panel visualizationPanel) {
        startButton.addActionListener(e -> {
            if (!visualizationPanel.isAnimating()) {
                String selectedAlgorithm = (String) algorithmSelector.getSelectedItem();
                if (selectedAlgorithm != null) {
                    startButton.setEnabled(false);
                    algorithmSelector.setEnabled(false);

                    visualizationPanel.startSorting(selectedAlgorithm);

                    new Thread(() -> {
                        while (visualizationPanel.isAnimating()) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                                break;
                            }
                        }

                        SwingUtilities.invokeLater(() -> {
                            startButton.setEnabled(true);
                            algorithmSelector.setEnabled(true);
                        });
                    }).start();
                }
            }
        });
    }

    private static JSlider createSquareCountSlider() {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, MIN_SQUARES, MAX_SQUARES, DEFAULT_SQUARES);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    private static JComboBox<String> createAlgorithmSelector() {
        String[] algorithms = {
            "Bubble Sort",
            "Insertion Sort",
            "Selection Sort",
            "Merge Sort",
            "Quick Sort"
        };
        return new JComboBox<>(algorithms);
    }

    private static JPanel createControlPanel(JSlider slider, JComboBox<String> algorithmSelector, JButton startButton) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        panel.add(new JLabel("Square Count:"));
        panel.add(slider);
        panel.add(new JLabel("Algorithm:"));
        panel.add(algorithmSelector);
        panel.add(startButton);

        return panel;
    }
}