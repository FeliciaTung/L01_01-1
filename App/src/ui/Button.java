package ui;

import javax.swing.*;
import java.awt.*;

public class Button extends JLabel implements ClickableObject {

    public static final Color BUTTON_COLOR = new Color(50, 50, 70);

    int id;

    Button(String text) {
        super(text, CENTER);
        setPreferredSize(new Dimension(200, 50));
        setBackground(BUTTON_COLOR);

        setFont(getFont().deriveFont(18f));
        setPreferredSize(new Dimension(200, 50));
    }

    @Override
    public int getID() {
        return id;
    }
}
