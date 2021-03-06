package ui.pages;

import backend.CurrentSession;
import backend.DatabaseManager;
import holders.Question;
import ui.UIManager;
import ui.components.Button;
import ui.components.ClickableObject;
import ui.components.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Page to view the question to answer.
 */
public class ViewQuestionPage extends JPanel implements MouseListener {
    private Label[] multipleChoices = new Label[3];
    private int questionType;
    private Question question;
    private Label questionLabel;
    private Label questionDesc;
    private Label answerLabel;
    private Label answer;
    private Label otherChioce;
    private Button backButton;
    private int WINDOW_WIDTH = 800;
    private int WINDOW_HEIGHT = 680;
    private int LABEL_WIDTH = 600;
    private float PRIMARY_FONT = 18f;
    private float SECONDARY_FONT = 16F;

    /**
     * Prepares the page to show the desired question by creating the
     * UI elements.
     *
     * @param q the question to display
     */
    public ViewQuestionPage(Question q) {
        super();
        question = q;
        if (question.multipleChoices == null)
            questionType = ClickableObject.SA_BUTTON;
        else
            questionType = ClickableObject.MC_BUTTON;

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.WHITE);

        addContent();

        add(UIManager.getSpacing(WINDOW_WIDTH, 30));


    }

    /**
     * Displays the question to be answered.
     */
    private void addContent() {
        // reset panel
        backButton = new Button("Back");
        backButton.id = ClickableObject.BACK_BUTTON;
        backButton.addMouseListener(this);
        add(backButton);
        add(UIManager.getSpacing(WINDOW_WIDTH - 220, 1));

        Label title = new Label("View Question", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(WINDOW_WIDTH, 40));

        // adjust label space  based on question length
        int descriptionHeight = UIManager.getLabelHeight(question.question);
        int answerHeight = UIManager.getLabelHeight(question.answer);
        int[] choiceHeight = new int[3];

        // display question
        questionLabel = new Label("Question: ", SwingConstants.CENTER);
        questionLabel.setFont(getFont().deriveFont(PRIMARY_FONT));
        add(questionLabel);

        String questionText = "<html> " + question.question + "</html>";
        questionDesc = new Label(questionText, SwingConstants.LEFT);
        questionDesc.setPreferredSize(new Dimension(LABEL_WIDTH, descriptionHeight));
        questionDesc.setFont(getFont().deriveFont(SECONDARY_FONT));
        add(questionDesc);

        add(UIManager.getSpacing(WINDOW_WIDTH, 10));

        // display answer
        answerLabel = new Label("Answer: ", SwingConstants.CENTER);
        answerLabel.setFont(getFont().deriveFont(PRIMARY_FONT));
        add(answerLabel);

        String answerText = "<html> " + question.answer + "</html>";
        answer = new Label(answerText, SwingConstants.LEFT);
        answer.setPreferredSize(new Dimension(LABEL_WIDTH, answerHeight));
        answer.setFont(getFont().deriveFont(SECONDARY_FONT));

        add(answer);

        if (questionType == ClickableObject.MC_BUTTON) {
            add(UIManager.getSpacing(WINDOW_WIDTH, 10));
            otherChioce = new Label("Other Choices: ", SwingConstants.CENTER);
            otherChioce.setPreferredSize(new Dimension(LABEL_WIDTH, 25));
            otherChioce.setFont(getFont().deriveFont(PRIMARY_FONT));
            add(otherChioce);
            add(UIManager.getSpacing(WINDOW_WIDTH, 10));
            for (int i = 0; i < multipleChoices.length; i++) {
                String choice = "<html> " + (i + 1) + ". " + question.multipleChoices[i] + "</html>";
                choiceHeight[i] = UIManager.getLabelHeight(question.multipleChoices[i]);
                multipleChoices[i] = new Label(choice, SwingConstants.LEFT);
                multipleChoices[i].setPreferredSize(new Dimension(LABEL_WIDTH, choiceHeight[i]));
                multipleChoices[i].setFont(getFont().deriveFont(SECONDARY_FONT));
                add(multipleChoices[i]);
                add(UIManager.getSpacing(WINDOW_WIDTH, 10));


            }

        }


        add(UIManager.getSpacing(WINDOW_WIDTH, 30));
        UIManager.switchView(this);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.BACK_BUTTON:
                if (CurrentSession.assignment == null) {
                    UIManager.switchView(new AddAssignmentPage(DatabaseManager.getAllQuestions(-1)));
                } else {
                    UIManager.switchView(new ViewAssignmentPage(CurrentSession.assignment));
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
                backButton.setBackground(Button.BUTTON_COLOR_PRESSED);
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
                backButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
            case ClickableObject.BACK_BUTTON:
                backButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
        }
    }
}
