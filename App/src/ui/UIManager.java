package ui;

import javax.swing.*;
import java.awt.*;

public class UIManager {

    private JFrame frame;

    public UIManager() {
        frame = new JFrame("CSCC01 - Team01 Project");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        switchView(new AssignmentPage());
    }

    public void switchView(JPanel view) {
        frame.setContentPane(view);
        frame.pack();
        view.updateUI();
    }

    public static JComponent getSpacing(int width, int height) {
        JComponent spacing = new JComponent() {};
        spacing.setPreferredSize(new Dimension(width, height));
        return spacing;
    }
}
