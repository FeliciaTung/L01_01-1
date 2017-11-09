package ui.components;

import javax.swing.*;
import java.awt.*;

public class InputField extends JTextArea {

    public static final int WIDTH = 270;
    public static final int HEIGHT = 60;

    public InputField() {
        super(1, 10);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(240, 240, 240));
        setMargin(new Insets(10, 10, 10 , 10));
        setLineWrap(true);
        setFont(getFont().deriveFont(24f));
    }
}