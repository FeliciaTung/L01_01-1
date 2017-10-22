package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddQuestionPage extends JPanel implements MouseListener {

    private Button saveButon;
    private InputField questionInput;
    private InputField[] multipleChoiceOptions;

    public AddQuestionPage() {
        super();
        saveButon = new SaveQuestionButton();
        questionInput = new InputField();
        multipleChoiceOptions = new InputField[4];

        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Add a Question", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(800, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(800, 25));

        JLabel typeQuestion = new JLabel("Type your Question:", SwingConstants.RIGHT);
        typeQuestion.setPreferredSize(new Dimension(300, 50));
        typeQuestion.setFont(getFont().deriveFont(18f));
        add(typeQuestion);

        add(questionInput);

        add(UIManager.getSpacing(800, 50));

        for (int i = 0; i < multipleChoiceOptions.length; i++) {
            if (i % 2 == 0) {
                for (int j = i; j < i + 2; j++) {
                    if (j % 2 == 1) {
                        add(UIManager.getSpacing(20, 1));
                    }
                    JLabel optionText = new JLabel("Option " + j, SwingConstants.LEFT);
                    optionText.setFont(getFont().deriveFont(16f));
                    optionText.setPreferredSize(new Dimension(InputField.WIDTH, 30));
                    add(optionText);
                }
            } else {
                add(UIManager.getSpacing(20, 1));
            }

            multipleChoiceOptions[i] = new InputField();
            add(multipleChoiceOptions[i]);
        }

        add(UIManager.getSpacing(800, 50));

        saveButon.addMouseListener(this);
        add(saveButon);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.SAVE_QUESTION:
                saveQuestion();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void saveQuestion() {

    }
}
