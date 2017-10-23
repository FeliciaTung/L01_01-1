package ui;

import javax.swing.*;
import java.awt.*;

public class Button extends JLabel implements ClickableObject {

    public static final int WIDTH = 200;
    public static final int HEIGHT = 50;
    public static final Color BUTTON_COLOR_IDLE = new Color(150, 190, 200);
    public static final Color BUTTON_COLOR_PRESSED = new Color(120, 150, 180);

    int id;

    Button(String text) {
        super(text, CENTER);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBackground(BUTTON_COLOR_IDLE);
        setFont(getFont().deriveFont(18f));
    }

    @Override
    public int getID() {
        return id;
    }
}
