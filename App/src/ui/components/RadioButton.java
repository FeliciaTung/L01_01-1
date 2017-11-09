package ui.components;

import javax.swing.*;
import java.awt.*;

public class RadioButton extends JComponent implements ClickableObject {

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    private final int id;

    private boolean isSelected;

    public RadioButton(int id) {
        super();
        this.id = id;
        isSelected = false;

        setFocusable(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        g.fillOval(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.WHITE);
        g.fillOval(2, 2, WIDTH - 4, HEIGHT - 4);

        if (isSelected) {
            g.setColor(Color.BLACK);
            g.fillOval(WIDTH / 3, HEIGHT / 3, WIDTH / 3, HEIGHT / 3);
        }

    }

    public void select() {
        isSelected = true;
        repaint();
        updateUI();
    }

    public void deselect() {
        isSelected = false;
        repaint();
        updateUI();
    }

    public boolean isSelected() {
        return isSelected;
    }
}
