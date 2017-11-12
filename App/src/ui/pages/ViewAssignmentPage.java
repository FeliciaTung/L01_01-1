package ui.pages;

import backend.DatabaseManager;
import holders.Assignment;
import holders.Question;
import ui.UIManager;
import ui.components.Button;
import ui.components.*;
import ui.components.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class ViewAssignmentPage extends JPanel implements MouseListener {

    /*
    private EditQuestionButton[] editButton;
    private DeleteQuestionButton[] deleteButton;
    */
    private InputField assignmentInput;
    private ArrayList<Question> questionList;
    private Label[] questionLabels;
    private BackButton backButton;
    private int WINDOW_WIDTH = 800;
    private int LABEL_WIDTH = 600;
    private Label title;
    private Label assignmentLabel;

    public ViewAssignmentPage(Assignment assignment) {
        super();
        assignmentInput = new InputField();
        questionList = assignment.getQuestions();
        questionLabels = new Label[assignment.questions.size()];
        backButton = new BackButton(ClickableObject.BACK_TO_VIEW_ALL_ASSIGN);
        setPreferredSize(new Dimension(WINDOW_WIDTH, 600));
        setBackground(Color.WHITE);

        title = new Label("Assignment", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(WINDOW_WIDTH, 40));

        assignmentLabel = new Label("Assignment: " + assignment.name, SwingConstants.CENTER);
        assignmentLabel.setFont(getFont().deriveFont(18f));
        add(assignmentLabel);

        add(UIManager.getSpacing(WINDOW_WIDTH, 30));

        for (int i = 0; i < questionList.size(); i++) {
            // increase label height to deal with long question
            String text = "<html>" + (i + 1) + ". " + questionList.get(i).question + "</html>";
            int labelHeight = 25;
            if (text.length() > 199) {
                labelHeight = 65;
            } else if (text.length() > 99) {
                labelHeight = 45;
            }

            questionLabels[i] = new Label(text, SwingConstants.LEFT);
            questionLabels[i].setIndex(i);
            questionLabels[i].setPreferredSize(new Dimension(LABEL_WIDTH, labelHeight));
            questionLabels[i].setFont(getFont().deriveFont(16f));
            questionLabels[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            questionLabels[i].addMouseListener(this);
            add(questionLabels[i]);
            /*
            editButton[i] = new EditQuestionButton(i);
            deleteButton[i] = new DeleteQuestionButton(i);
            editButton[i].addMouseListener(this);
            deleteButton[i].addMouseListener(this);

            add(editButton[i]);
            add(deleteButton[i]);
            */
            add(UIManager.getSpacing(WINDOW_WIDTH, 1));
        }

        add(UIManager.getSpacing(WINDOW_WIDTH, 40));

        backButton.addMouseListener(this);
        add(backButton);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.BACK_TO_VIEW_ALL_ASSIGN:
                gotoViewAllAssignments();
                break;
            case ClickableObject.LABEL:
                int index = ((Label) e.getSource()).getIndex();
                gotoViewQuestion(questionList.get(index));
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
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.BACK_TO_VIEW_ALL_ASSIGN:
                backButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
            case ClickableObject.LABEL:
                int index = ((Label) e.getSource()).getIndex();
                questionLabels[index].setForeground(Label.LABEL_COLOR_PRESSED);
            /*case ClickableObject.EDIT_QUESTION:
                int editId = ((EditQuestionButton) e.getSource()).getEditButtonId();
                for (EditQuestionButton button : editButton) {
                    if (editId == button.getEditButtonId())
                        button.setBackground(Button.BUTTON_COLOR_PRESSED);
                }
                break;
            case ClickableObject.DELETE_QUESTION:
                int deleteId = ((DeleteQuestionButton) e.getSource()).getDeleteButtonId();
                for (DeleteQuestionButton button : deleteButton) {
                    if (deleteId == button.getDeleteButtonId())
                        button.setBackground(Button.BUTTON_COLOR_PRESSED);
                }
                break;*/
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.BACK_TO_VIEW_ALL_ASSIGN:
                backButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
            case ClickableObject.LABEL:
                int index = ((Label) e.getSource()).getIndex();
                questionLabels[index].setForeground(Label.LABEL_COLOR_IDLE);
            /*case ClickableObject.EDIT_QUESTION:
                int editId = ((EditQuestionButton) e.getSource()).getEditButtonId();
                for (EditQuestionButton button : editButton) {
                    if (editId == button.getEditButtonId())
                        button.setBackground(Button.BUTTON_COLOR_IDLE);
                }
                break;
            case ClickableObject.DELETE_QUESTION:
                int deleteId = ((DeleteQuestionButton) e.getSource()).getDeleteButtonId();
                for (DeleteQuestionButton button : deleteButton) {
                    if (deleteId == button.getDeleteButtonId())
                        button.setBackground(Button.BUTTON_COLOR_IDLE);
                }
                break;*/
        }
    }

    private void editQuestion() {
        //TODO: switch to edit question page
    }


    private void deleteQuestion() {
        //TODO: trigger delete question function

    }

    private void gotoViewAllAssignments() {
        //TODO: Go back to view all assignment page
    }

    private void gotoViewQuestion(Question question) {
        UIManager.switchToQuestionView(question);
    }
}
