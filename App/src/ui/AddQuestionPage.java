package ui;

import backend.DatabaseManager;
import holders.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddQuestionPage extends JPanel implements MouseListener {

    private Button saveButton;
    private Button nextButton;
    private Button backButton;
    private InputField questionInput;
    private InputField answerInput = new InputField();
    private InputField[] multipleChoiceOptions = new InputField[4];
    private RadioButton[] multipleChoiceRadioButtons = new RadioButton[4];
    private RadioButton[] topMenuOptions;
    private int questionType = -1;
    public AddQuestionPage() {
        super();
        saveButton = new SaveQuestionButton();
        nextButton = new NextButton(ClickableObject.NEXT_CHOOSE_QUESTION_TYPE_BUTTON);
        backButton = new BackButton(ClickableObject.BACK_CHOOSE_QUESTION_TYPE_BUTTON);
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
        // user decided which type of question they want
        // show them the add question page for the selected question type
        if (typeChose) {
            if (questionType == ClickableObject.MC_BUTTON) {
                addMultipleChoice();
            } else {
                addShortAnswer();
            }
            add(UIManager.getSpacing(800, 30));

            backButton.addMouseListener(this);
            add(backButton);

            add(UIManager.getSpacing(60, 30));

            saveButton.addMouseListener(this);
            add(saveButton);

        } else { // user still needs to pick which question type they want
            addQuestionTypeSelection();
        }
        add(UIManager.getSpacing(800, 30));
        UIManager.switchView(this);

    }

    private void addQuestionTypeSelection(){
        add(UIManager.getSpacing(100, 1));
        for (int i = 0; i < topMenuOptions.length; i++) {
            topMenuOptions[i] = new RadioButton(ClickableObject.QUESTION_OPTIONS[i]);
            topMenuOptions[i].addMouseListener(this);
            add(topMenuOptions[i]);

            if (topMenuOptions[i].getID() == ClickableObject.MC_BUTTON) {
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

        nextButton.addMouseListener(this);
        add(nextButton);


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

        add(UIManager.getSpacing(800, 40));
        UIManager.switchView(this);
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
            case ClickableObject.NEXT_CHOOSE_QUESTION_TYPE_BUTTON:
                if (questionType != -1 ) {
                    // reset button color
                    nextButton.setBackground(Button.BUTTON_COLOR_IDLE);
                    addContent(true);
                }
                break;
            case ClickableObject.BACK_CHOOSE_QUESTION_TYPE_BUTTON:
                // reset button color and question type
                questionType = -1;
                backButton.setBackground(Button.BUTTON_COLOR_IDLE);
                addContent(false);
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
                        radioButton.select();
                        if (id == ClickableObject.MC_BUTTON) {
                            questionType = ClickableObject.MC_BUTTON;
                        } else {
                            questionType = ClickableObject.SA_BUTTON;
                        }
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
            case ClickableObject.NEXT_CHOOSE_QUESTION_TYPE_BUTTON:
                nextButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
            case ClickableObject.BACK_CHOOSE_QUESTION_TYPE_BUTTON:
                backButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.SAVE_QUESTION:
                saveButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
            case ClickableObject.NEXT_CHOOSE_QUESTION_TYPE_BUTTON:
                nextButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
            case ClickableObject.BACK_CHOOSE_QUESTION_TYPE_BUTTON:
                backButton.setBackground(Button.BUTTON_COLOR_IDLE);
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
