package ui.components;

import javax.swing.*;
import java.awt.*;

public class MultipleChoiceAnswer extends JLabel implements ClickableObject {

    public static final int MULTIPLE_CHOICE_ANSWER = 53;

    public MultipleChoiceAnswer() {
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBackground(Color.white);
        setFont(getFont().deriveFont(18f));
    }

    public int getID() {
        return MULTIPLE_CHOICE_ANSWER;
    }
}
