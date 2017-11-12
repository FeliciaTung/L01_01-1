package ui.pages;

import holders.Question;
import ui.components.*;
import ui.UIManager;
import ui.components.Button;
import ui.components.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ViewQuestionPage extends JPanel implements MouseListener {
    //TODO: add back button to go back to the previous page
    public static BackButton backButton = new BackButton(ClickableObject.BACK_TO_VIEW_ASSIGN);
    private Label[] multipleChoices = new Label[3];
    private int questionType;
    private Question question;
    private Label questionLabel;
    private Label questionDesc;
    private Label answerLabel;
    private Label answer;
    private Label otherChioce;
    private int WINDOW_WIDTH = 800;
    private int LABEL_WIDTH = 600;
    private float PRIMARY_FONT = 18f;
    private float SECONDARY_FONT = 16F;

    public ViewQuestionPage(Question q) {
        super();
        question = q;
        if (question.multipleChoices == null)
            questionType = ClickableObject.SA_BUTTON;
        else
            questionType = ClickableObject.MC_BUTTON;

        setPreferredSize(new Dimension(WINDOW_WIDTH, 600));
        setBackground(Color.WHITE);

        addContent();

        add(UIManager.getSpacing(WINDOW_WIDTH, 30));


    }

    private void addContent() {
        // reset panel
        removeAll();

        Label title = new Label("View Question", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(WINDOW_WIDTH, 40));


        // adjust label space  based on question length
        int descriptionHeight = setLabelHeight(question.question);
        int answerHeight = setLabelHeight(question.answer);
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
                choiceHeight[i] = setLabelHeight(question.multipleChoices[i]);
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
            case ClickableObject.BACK_TO_VIEW_ALL_ASSIGN:
                gotoPreviousPage();
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

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.SAVE_QUESTION:
                backButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
        }
    }

    public void gotoPreviousPage() {
        //TODO: implement this
    }

    /**
     * Helper function to calculate the label height based on text length
     *
     * @param text
     * @return label height
     */
    private int setLabelHeight(String text) {
        int labelHeight = 25;
        if (text.length() > 199) {
            labelHeight = 65;
        } else if (text.length() > 99) {
            labelHeight = 45;
        }
        return labelHeight;

    }
}
