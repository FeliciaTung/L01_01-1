package ui.pages;

import backend.DatabaseManager;
import holders.Question;
import ui.UIManager;
import ui.components.Button;
import ui.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Page to add questions, not accessible by students.
 */
public class AddQuestionPage extends JPanel implements MouseListener {

    private Button saveButton;
    private JLabel typeQuestion;
    private InputField questionInput;
    private InputField answerInput = new InputField();
    private InputField[] multipleChoiceOptions = new InputField[4];
    private RadioButton[] multipleChoiceRadioButtons = new RadioButton[4];
    private RadioButton[] topMenuOptions;
    private RadioButton[] randMenuOptions;
    private RadioButton[] randButtonOptions;
    private JLabel saveMessage;
    private int questionType = -1;
    private Button backButton;
    private int WINDOW_WIDTH = 800;
    private int WINDOW_HEIGHT = 680;
    String[] randLabels = {"Simple Math", "Intermediate Statistics Question", "Expert Statistics Question"};

    /**
     * Prepares the page by adding the required buttons.
     */
    public AddQuestionPage() {
        super();
        saveButton = new SaveQuestionButton();
        saveButton.addMouseListener(this);
        questionInput = new InputField();
        topMenuOptions = new RadioButton[2];
        saveMessage = new JLabel("", SwingConstants.CENTER);
        topMenuOptions = new RadioButton[3];
        randMenuOptions = new RadioButton[3];
        randButtonOptions = new RadioButton[2];
        backButton = new Button("Back");

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.WHITE);

        addContent(false);
    }

    /**
     * Prepares the page to add a question based on selection. Used for
     * multiple choice or short answer selection.
     *
     * @param typeChose determines whether a question will be added or not
     */
    private void addContent(boolean typeChose) {
        // clear everything to show different question type interfaces
        removeAll();
        backButton.id = ClickableObject.BACK_BUTTON;
        backButton.addMouseListener(this);
        add(backButton);
        add(UIManager.getSpacing(WINDOW_WIDTH - 220, 1));

        JLabel title = new JLabel("Add a Question", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(WINDOW_WIDTH, 40));

        typeQuestion = new JLabel("Question:", SwingConstants.RIGHT);
        typeQuestion.setFont(getFont().deriveFont(18f));
        add(typeQuestion);
        add(questionInput);

        add(UIManager.getSpacing(WINDOW_WIDTH, 30));

        addQuestionTypeSelection();
        add(UIManager.getSpacing(WINDOW_WIDTH, 30));

        // displays the add question fields for the selected question type
        if (typeChose) {
            if (questionType == ClickableObject.MC_BUTTON) {
                addMultipleChoice();
            } else if (questionType == ClickableObject.SA_BUTTON) {
                addShortAnswer();
            } else {
                addRandom();
            }
            add(UIManager.getSpacing(WINDOW_WIDTH, 30));
            add(saveButton);
        }

        saveMessage.setPreferredSize(new Dimension(WINDOW_WIDTH, 30));
        add(saveMessage);

        UIManager.switchView(this);

    }

    /**
     * Selects whether it will be a short answer or multiple choice.
     */
    private void addQuestionTypeSelection() {
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
            JLabel menuText = new JLabel("", SwingConstants.LEFT);
            if (optionId == ClickableObject.MC_BUTTON) {
                menuText.setText("Multiple Choice");
            } else if (optionId == ClickableObject.SA_BUTTON) {
                menuText.setText("Short Answer");
            } else {
                menuText.setText("Random");
            }
            menuText.setFont(getFont().deriveFont(16f));
            menuText.setPreferredSize(new Dimension(InputField.WIDTH - 100, 25));
            add(menuText);

        }
    }

    /**
     * Interface for multiple choice questions. Allows the addition of
     * multiple choice options.
     */
    public void addMultipleChoice() {
        for (int i = 0; i < multipleChoiceOptions.length; i++) {
            if (i % 2 == 0) {
                if (i > 0) {
                    add(UIManager.getSpacing(WINDOW_WIDTH, 20));
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

    /**
     * Interface for short answer questions.
     */
    public void addShortAnswer() {
        add(UIManager.getSpacing(100, 1));

        JLabel typeAnswer = new JLabel("Answer:", SwingConstants.RIGHT);
        typeAnswer.setFont(getFont().deriveFont(18f));
        add(typeAnswer);
        add(answerInput);
    }

    /**
     * Interface for randomized questions
     */
    public void addRandom() {
        remove(typeQuestion);
        remove(questionInput);

        JLabel typeAnswer = new JLabel("Select from the following options:", SwingConstants.RIGHT);
        typeAnswer.setFont(getFont().deriveFont(16f));
        JLabel chooseType = new JLabel("Would you like this question to be MC or SA?", SwingConstants.RIGHT);
        chooseType.setFont(getFont().deriveFont(16f));

        add(typeAnswer);
        add(UIManager.getSpacing(WINDOW_WIDTH, 1));

        for (int i = 0; i < randMenuOptions.length; i++) {
            randMenuOptions[i] = new RadioButton(ClickableObject.RAND_OPTIONS[i]);
            randMenuOptions[i].addMouseListener(this);

            add(UIManager.getSpacing(WINDOW_WIDTH / 20, 1));
            add(randMenuOptions[i]);

            JLabel menuText = new JLabel("", SwingConstants.LEFT);
            menuText.setText(randLabels[i]);
            menuText.setFont(getFont().deriveFont(16f));
            menuText.setPreferredSize(new Dimension(WINDOW_WIDTH / 3, 50));
            add(menuText);

            add(UIManager.getSpacing(WINDOW_WIDTH, 1));
        }

        add(UIManager.getSpacing(WINDOW_WIDTH, 25));
        add(chooseType);
        add(UIManager.getSpacing(1, 25));

        for (int i = 0; i < randButtonOptions.length; i++) {
            randButtonOptions[i] = new RadioButton(ClickableObject.RAND_BUTTONS[i]);
            randButtonOptions[i].addMouseListener(this);
            add(randButtonOptions[i]);

            JLabel menuText = new JLabel("", SwingConstants.LEFT);
            ;
            if (randButtonOptions[i].getID() == ClickableObject.RMC_BUTTON) {
                menuText.setText("Multiple Choice");
            } else {
                menuText.setText("Short Answer");
            }
            menuText.setFont(getFont().deriveFont(16f));
            menuText.setPreferredSize(new Dimension(InputField.WIDTH - 135, 20));
            add(menuText);
        }
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
            case ClickableObject.SIMPLE_MATH:
            case ClickableObject.STATS1:
            case ClickableObject.STATS2:
                for (RadioButton randMenu : randMenuOptions) {
                    if (id == randMenu.getID()) {
                        randMenu.select();
                    } else {
                        randMenu.deselect();
                    }
                }
                break;
            case ClickableObject.RMC_BUTTON:
            case ClickableObject.RSA_BUTTON:
                for (RadioButton randButton : randButtonOptions) {
                    if (id == randButton.getID()) {
                        randButton.select();
                    } else {
                        randButton.deselect();
                    }
                }
                break;
            case ClickableObject.MC_BUTTON:
            case ClickableObject.SA_BUTTON:
            case ClickableObject.RAND_OPTION:
                for (RadioButton radioButton : topMenuOptions) {
                    if (id == radioButton.getID()) {
                        if (id == ClickableObject.MC_BUTTON) {
                            questionType = ClickableObject.MC_BUTTON;
                        } else if (id == ClickableObject.SA_BUTTON) {
                            questionType = ClickableObject.SA_BUTTON;
                        } else {
                            questionType = ClickableObject.RAND_OPTION;
                        }
                        addContent(true);
                    } else {
                        radioButton.deselect();
                    }
                }
                break;

            case ClickableObject.BACK_BUTTON:
                UIManager.switchView(new InstructorHomePage());
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

            case ClickableObject.BACK_BUTTON:
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
            case ClickableObject.BACK_BUTTON:
                backButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
        }
    }

    /**
     * Saves the question and adds it to the database.
     */
    private void saveQuestion() {
        String question = questionInput.getText();
        questionInput.setText("");
        String correctAnswer = null;
        String[] answerChoices = new String[3];
        if (questionType == ClickableObject.MC_BUTTON) {
            for (int i = 0; i < multipleChoiceRadioButtons.length; i++) {
                if (multipleChoiceRadioButtons[i].isSelected()) {
                    correctAnswer = multipleChoiceOptions[i].getText();
                } else {
                    answerChoices[correctAnswer == null ? i : i - 1] = multipleChoiceOptions[i].getText();
                }

                multipleChoiceOptions[i].setText("");
            }
        } else if (questionType == ClickableObject.SA_BUTTON) {
            correctAnswer = answerInput.getText();
            answerInput.setText("");
            answerChoices = null;
        } else {
            correctAnswer = "Correct answer will be randomized once assignment is generated";
            for (int i = 0; i < randMenuOptions.length; i++) {
                if (randMenuOptions[i].isSelected()) {
                    question = randLabels[i];
                }
            }
            if (randButtonOptions[0].isSelected()) {
                answerChoices[0] = "0";
                answerChoices[1] = "0";
                answerChoices[2] = "0";
            } else {
                answerChoices = null;
            }
        }

        DatabaseManager.addQuestion(new Question(question, correctAnswer, "", answerChoices));
        saveMessage.setText("Question Saved");
        Timer timer = new Timer(4000, (actionEvent) -> saveMessage.setText(""));
        timer.setRepeats(false);
        timer.setCoalesce(true);
        timer.start();
    }
}
