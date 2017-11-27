package ui.components;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel implements ClickableObject {

    public static final int WIDTH = 200;
    public static final int HEIGHT = 50;
    public static final Color LABEL_COLOR_IDLE = new Color(0, 0, 0);
    public static final Color LABEL_COLOR_PRESSED = new Color(170, 170, 170);

    private final int id = ClickableObject.LABEL;
    private int index = -1;

    public Label(String text, int position) {
        super(text, position);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setOpaque(false);
        setForeground(LABEL_COLOR_IDLE);

    }

    /**
     * Set an index if Label is initialized as a list. Index is to help to identify the label when an event occurs.
     *
     * @param index index of the label in the list
     */
    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    @Override
    public int getID() {
        return id;
    }
}
