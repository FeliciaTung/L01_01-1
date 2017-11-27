package ui;

import ui.pages.LoginPage;

import javax.swing.*;
import java.awt.*;

public class UIManager {

    private static JFrame frame;

    public static void createUI() {
        frame = new JFrame("SmarTWork");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        switchView(new LoginPage());
    }

    public static void switchView(JPanel view) {
        frame.setContentPane(view);
        frame.pack();
        view.updateUI();
    }

    public static JComponent getSpacing(int width, int height) {
        JComponent spacing = new JComponent() {
        };
        spacing.setPreferredSize(new Dimension(width, height));
        return spacing;
    }

    /**
     * Helper function to calculate the label height based on text length
     *
     * @param text
     * @return label height
     */
    public static int getLabelHeight(String text) {
        int labelHeight = 25;
        if (text.length() > 199) {
            labelHeight = 65;
        } else if (text.length() > 99) {
            labelHeight = 45;
        }
        return labelHeight;

    }
}
