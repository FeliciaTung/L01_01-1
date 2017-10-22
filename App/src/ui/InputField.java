package ui;

import javax.swing.*;
import java.awt.*;

public class InputField extends JTextArea {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 75;

    InputField() {
        super(3, 20);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(240, 240, 240));
        setMargin(new Insets(10, 10, 10 , 10));
        setLineWrap(true);
    }
}