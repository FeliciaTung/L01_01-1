package ui.pages;

import backend.DatabaseManager;
import holders.Question;
import ui.components.Button;
import ui.components.ClickableObject;
import ui.components.InputField;
import ui.components.RadioButton;
import ui.components.SaveQuestionButton;
import ui.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddQuestionPage extends JPanel implements MouseListener {

    private Button saveButton;
    private InputField questionInput;
    private InputField answerInput = new InputField();
    private InputField[] multipleChoiceOptions = new InputField[4];
    private RadioButton[] multipleChoiceRadioButtons = new RadioButton[4];
    private RadioButton[] topMenuOptions;
    private int questionType = -1;
    public AddQuestionPage() {
        super();
        saveButton = new SaveQuestionButton();
        saveButton.addMouseListener(this);
        questionInput = new InputField();
        topMenuOptions = new RadioButton[2];

        setPreferredSize(new Dimension(800, 680));
        setBackground(Color.WHITE);

        addContent(false);

        add(UIManager.getSpacing(800, 30));


    }

    private void addContent(boolean typeChose){
        // clear everything
        removeAll();
        JLabel title = new JLabel("Add a Question", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(800, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(800, 40));

        JLabel typeQuestion = new JLabel("Question:", SwingConstants.RIGHT);
        typeQuestion.setFont(getFont().deriveFont(18f));
        add(typeQuestion);
        add(questionInput);

        add(UIManager.getSpacing(800, 30));

        addQuestionTypeSelection();
        add(UIManager.getSpacing(800, 30));

        // user decided which type of question they want
        // show them the add question page for the selected question type
        if (typeChose) {
            if (questionType == ClickableObject.MC_BUTTON) {
                addMultipleChoice();
            } else {
                addShortAnswer();
            }
            add(UIManager.getSpacing(800, 30));

            add(saveButton);

        }
        add(UIManager.getSpacing(800, 30));
        UIManager.switchView(this);

    }

    private void addQuestionTypeSelection(){
        add(UIManager.getSpacing(100, 1));
        for (int i = 0; i < topMenuOptions.length; i++) {
            topMenuOptions[i] = new RadioButton(ClickableObject.QUESTION_OPTIONS[i]);
            topMenuOptions[i].addMouseListener(this);
            int optionId = topMenuOptions[i].getID();
            // radio button resets to deselected when question type is updated
            if (optionId == questionType) {
                topMenuOptions[i].select();
            }
            add(topMenuOptions[i]);

            if (optionId == ClickableObject.MC_BUTTON) {
                JLabel menuText = new JLabel("Multiple Choice", SwingConstants.LEFT);
                menuText.setFont(getFont().deriveFont(16f));
                menuText.setPreferredSize(new Dimension(InputField.WIDTH, 25));
                add(menuText);
            } else {
                JLabel menuText = new JLabel("Short Answer", SwingConstants.LEFT);
                menuText.setFont(getFont().deriveFont(16f));
                menuText.setPreferredSize(new Dimension(InputField.WIDTH, 25));
                add(menuText);
            }
        }

        add(UIManager.getSpacing(800, 30));


    }

    public void addMultipleChoice() {
        for (int i = 0; i < multipleChoiceOptions.length; i++) {
            if (i % 2 == 0) {
                if (i > 0) {
                    add(UIManager.getSpacing(800, 20));
                }

                add(UIManager.getSpacing(60, 1));

                for (int j = i; j < i + 2; j++) {
                    if (j % 2 == 1) {
                        add(UIManager.getSpacing(20, 1));
                    }
                    add(UIManager.getSpacing(RadioButton.WIDTH, 1));

                    JLabel optionText = new JLabel("Option " + j + ":", SwingConstants.LEFT);
                    optionText.setFont(getFont().deriveFont(16f));
                    optionText.setPreferredSize(new Dimension(InputField.WIDTH, 25));
                    add(optionText);
                }

                add(UIManager.getSpacing(60, 1));
            } else {
                add(UIManager.getSpacing(20, 1));
            }

            multipleChoiceRadioButtons[i] = new RadioButton(ClickableObject.MULTIPLE_CHOICE_OPTIONS[i]);
            multipleChoiceRadioButtons[i].addMouseListener(this);
            add(multipleChoiceRadioButtons[i]);

            multipleChoiceOptions[i] = new InputField();
            add(multipleChoiceOptions[i]);
        }

        multipleChoiceRadioButtons[0].select();

    }

    public void addShortAnswer() {
        JLabel typeAnswer = new JLabel("Answer:", SwingConstants.RIGHT);
        typeAnswer.setFont(getFont().deriveFont(18f));
        add(typeAnswer);
        add(answerInput);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.SAVE_QUESTION:
                saveQuestion();
                break;
            case ClickableObject.MULTIPLE_CHOICE_OPTION_1:
            case ClickableObject.MULTIPLE_CHOICE_OPTION_2:
            case ClickableObject.MULTIPLE_CHOICE_OPTION_3:
            case ClickableObject.MULTIPLE_CHOICE_OPTION_4:
                for (RadioButton radioButton : multipleChoiceRadioButtons) {
                    if (id == radioButton.getID()) {
                        radioButton.select();
                    } else {
                        radioButton.deselect();
                    }
                }
                break;
            case ClickableObject.MC_BUTTON:
            case ClickableObject.SA_BUTTON:
                for (RadioButton radioButton : topMenuOptions) {
                    if (id == radioButton.getID()) {
                        if (id == ClickableObject.MC_BUTTON) {
                            questionType = ClickableObject.MC_BUTTON;
                        } else {
                            questionType = ClickableObject.SA_BUTTON;
                        }
                        addContent(true);
                    } else {
                        radioButton.deselect();
                    }
                }
                break;
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
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.SAVE_QUESTION:
                saveButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.SAVE_QUESTION:
                saveButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
        }
    }

    private void saveQuestion() {
        String question = questionInput.getText();
        String correctAnswer = null;
        String[] answerChoices = new String[3];
        if (questionType == ClickableObject.MC_BUTTON) {
            for (int i = 0; i < multipleChoiceRadioButtons.length; i++) {
                if (multipleChoiceRadioButtons[i].isSelected()) {
                    correctAnswer = multipleChoiceOptions[i].getText();
                } else {
                    answerChoices[correctAnswer == null ? i : i - 1] = multipleChoiceOptions[i].getText();
                }
            }
        } else {
            correctAnswer = answerInput.getText();
            answerChoices = null;
        }
        DatabaseManager.addQuestion(new Question(question, correctAnswer, "", answerChoices));
    }
}
