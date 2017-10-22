package ui;

import javax.swing.*;

public class RadioButton extends JComponent implements ClickableObject {

    private final int id;

    RadioButton(int id) {
        super();
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }
}
