package ui.pages;

import backend.DatabaseManager;
import holders.Assignment;
import holders.Question;
import ui.components.Button;
import ui.components.CheckBox;
import ui.components.ClickableObject;
import ui.components.InputField;
import ui.components.SaveQuestionButton;
import ui.components.Label;
import ui.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class AddAssignmentPage extends JPanel implements MouseListener {

    private Button saveButton;
    /*
    private EditQuestionButton[] editButton;
    private DeleteQuestionButton[] deleteButton;
    */
    private InputField assignmentInput;
    private Question[] questionList;
    private Label[] questionLabels;
    private CheckBox[] questionCheckBoxes;
    private int WINDOW_WIDTH = 800;
    private int LABEL_WIDTH = 600;
    private Label title;
    private Label typeAssignment;


    public AddAssignmentPage(Question[] questions) {
        super();

        saveButton = new SaveQuestionButton();
        /*
        editButton = new EditQuestionButton[question_num];
        deleteButton = new DeleteQuestionButton[question_num];
        */
        assignmentInput = new InputField();
        questionList = questions;
        questionLabels = new Label[questions.length];
        questionCheckBoxes = new CheckBox[questions.length];

        setPreferredSize(new Dimension(WINDOW_WIDTH, 600));
        setBackground(Color.WHITE);

        title = new Label("Available Questions", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(WINDOW_WIDTH, 40));

        typeAssignment = new Label("Create an Assignment:", SwingConstants.RIGHT);
        typeAssignment.setFont(getFont().deriveFont(18f));
        add(typeAssignment);

        add(assignmentInput);

        add(UIManager.getSpacing(WINDOW_WIDTH, 30));

        addQuestions();

        add(UIManager.getSpacing(WINDOW_WIDTH, 40));

        saveButton.addMouseListener(this);
        add(saveButton);

    }

    private void addQuestions() {
        for (int i = 0; i < questionList.length; i++) {
            // increase label height to deal with long assignment name
            String text = "<html>" + questionList[i].question + "</html>";
            int labelHeight = 25;
            if (text.length() > 199) {
                labelHeight = 65;
            } else if (text.length() > 99) {
                labelHeight = 45;
            }

            questionCheckBoxes[i] = new CheckBox(questionList[i].id);
            questionCheckBoxes[i].addMouseListener(this);
            add(questionCheckBoxes[i]);
            add(UIManager.getSpacing(10, 0));


            questionLabels[i] = new Label(text, SwingConstants.LEFT);
            questionLabels[i].setIndex(i);
            questionLabels[i].setPreferredSize(new Dimension(LABEL_WIDTH, labelHeight));
            questionLabels[i].setFont(getFont().deriveFont(16f));
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
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.SAVE_QUESTION:
                createAssignment();
                break;
            case ClickableObject.CHECKBOX:
                id = ((CheckBox) e.getSource()).getQuestionID();
                for (CheckBox checkbox : questionCheckBoxes) {
                    if (id == checkbox.getQuestionID()) {
                        if (!checkbox.isSelected())
                            checkbox.select();
                        else
                            checkbox.deselect();
                        break;
                    }
                }
            case ClickableObject.LABEL:
                int index = ((Label) e.getSource()).getIndex();
                gotoViewQuestionPage(questionList[index]);

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
            case ClickableObject.SAVE_QUESTION:
                saveButton.setBackground(Button.BUTTON_COLOR_PRESSED);
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
            case ClickableObject.SAVE_QUESTION:
                saveButton.setBackground(Button.BUTTON_COLOR_IDLE);
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

    private void createAssignment() {
        //TODO: get question list from db and add to the selected ones to assignment
        String aname = assignmentInput.getText();
        ArrayList<Integer> selectedQuestion = new ArrayList<>();

        for (int i = 0; i < questionCheckBoxes.length; i++) {
            if (questionCheckBoxes[i].isSelected()) {
                if (questionList[i].id != -1) {
                    selectedQuestion.add(questionList[i].id);

                } else {
                    System.out.println("Invalid Question id: " + questionList[i].question);
                }
            }


        }
        DatabaseManager.addAssignment(new Assignment(aname, 0, selectedQuestion));
    }

    private void gotoViewQuestionPage(Question question) {
        UIManager.switchToQuestionView(question);
    }
}
