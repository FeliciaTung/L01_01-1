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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;


public class AddAssignmentPage extends JPanel implements MouseListener {

    private Button saveButton;
    /*
    private EditQuestionButton[] editButton;
    private DeleteQuestionButton[] deleteButton;
    */
    private InputField assignmentInput;
    private List<Question> questionList;
    private Label[] questionLabels;
    private List<CheckBox> questionCheckBoxes;
    private int WINDOW_WIDTH = 800;
    private int WINDOW_HEIGHT = 680;
    private int LABEL_WIDTH = 600;
    private Label title;
    private JLabel typeAssignment;
    private String typeDueDate, assignName, dueDate;
    private InputField dueDateInput;
    private Button backButton;


    public AddAssignmentPage(List<Question> questions) {
        super();

        saveButton = new SaveQuestionButton();
        /*
        editButton = new EditQuestionButton[question_num];
        deleteButton = new DeleteQuestionButton[question_num];
        */
        backButton = new Button("Back");
        assignmentInput = new InputField();
        questionList = questions;
        questionLabels = new Label[questions.size()];
        questionCheckBoxes = new ArrayList<>();
        dueDateInput = new InputField();
        typeDueDate = "Due Date (yyyy/mm/dd)";

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.WHITE);

        backButton.id = ClickableObject.BACK_BUTTON;
        backButton.addMouseListener(this);
        add(backButton);
        add(UIManager.getSpacing(WINDOW_WIDTH - 220, 1));

        title = new Label("Create Assignment", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(WINDOW_WIDTH, 40));

        typeAssignment = new JLabel("Assignment name:", SwingConstants.LEFT);

        typeAssignment.setFont(getFont().deriveFont(16f));
        typeAssignment.setPreferredSize(new Dimension(InputField.WIDTH, 25));
        add(typeAssignment);

        add(UIManager.getSpacing(20, 1));

        JLabel dueDateLabel = new JLabel(typeDueDate, SwingConstants.LEFT);
        dueDateLabel.setFont(getFont().deriveFont(16f));
        dueDateLabel.setPreferredSize(new Dimension(InputField.WIDTH, 25));
        add(dueDateLabel);

        add(assignmentInput);
        add(UIManager.getSpacing(20, 1));
        add(dueDateInput);
        add(UIManager.getSpacing(WINDOW_WIDTH, 20));

        addQuestions();

        add(UIManager.getSpacing(WINDOW_WIDTH, 40));

        saveButton.addMouseListener(this);
        add(saveButton);

    }

    private void addQuestions() {
        for (int i = 0; i < questionList.size(); i++) {
            // increase label height to deal with long assignment name
            String text = "<html>" + questionList.get(i).question + "</html>";
            int labelHeight = 25;
            if (text.length() > 199) {
                labelHeight = 65;
            } else if (text.length() > 99) {
                labelHeight = 45;
            }

            questionCheckBoxes.add(new CheckBox());
            questionCheckBoxes.get(i).addMouseListener(this);
            add(questionCheckBoxes.get(i));
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
                if (validateInput()) {
                    createAssignment();
                } else {showErrorMessage();}
                break;
            case ClickableObject.CHECKBOX:
                CheckBox clickedCheckBox = ((CheckBox)e.getSource());
                if (clickedCheckBox.isSelected()) {
                    clickedCheckBox.deselect();
                } else {
                    clickedCheckBox.select();
                }
                break;
            case ClickableObject.LABEL:
                int index = ((Label) e.getSource()).getIndex();
                gotoViewQuestionPage(questionList.get(index));
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
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.SAVE_QUESTION:
                saveButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
            case ClickableObject.LABEL:
                int index = ((Label) e.getSource()).getIndex();
                questionLabels[index].setForeground(Label.LABEL_COLOR_PRESSED);
                break;
            case ClickableObject.BACK_BUTTON:
                backButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
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
                break;
            case ClickableObject.BACK_BUTTON:
                backButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;

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
        List<Integer> selectedQuestion = new ArrayList<>();
        for (CheckBox cb : questionCheckBoxes){
            if (cb.isSelected()) {
                int index = questionCheckBoxes.indexOf(cb);
                if (questionList.get(index).id != -1) {
                    selectedQuestion.add(questionList.get(index).id);
                } else {
                    System.out.println("Invalid Question id: " + questionList.get(index).question);
                }
            }
        }
        DatabaseManager.addAssignment(new Assignment(assignName, 1, selectedQuestion, dueDate));
    }

    private void gotoViewQuestionPage(Question question) {
        UIManager.switchToQuestionView(question);
    }

    private boolean validateInput(){
        assignName = assignmentInput.getText();
        dueDate = dueDateInput.getText();
        return !(assignName.equals("")  && dueDate.equals(""));


    }
    private void showErrorMessage() {

        title.setText("Please enter assignment name and due date");
        title.setForeground(Color.WHITE);
        title.setBackground(Color.RED);
        title.setOpaque(true);

        Timer t = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                title.setText("Sign Up");
                title.setPreferredSize(new Dimension(800, 50));
                title.setFont(getFont().deriveFont(24f));
                title.setForeground(Color.BLACK);
                title.setBackground(Color.WHITE);
            }
        });
        t.setRepeats(false);
        t.start();
    }
}
