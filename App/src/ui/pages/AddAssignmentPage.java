package ui.pages;

import backend.CurrentSession;
import backend.DatabaseManager;
import holders.Assignment;
import holders.Question;
import ui.components.*;
import ui.UIManager;
import ui.components.Button;
import ui.components.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Page to add assignments. This page is not available to students.
 */
public class AddAssignmentPage extends JPanel implements MouseListener {

    private Button saveButton;

    private InputField assignmentInput;
    private List<Question> questionList;
    private Label[] questionLabels;
    private List<CheckBox> questionCheckBoxes;
    private int WINDOW_WIDTH = 800;
    private int WINDOW_HEIGHT = 680;
    private int LABEL_WIDTH = 600;
    private int questionPanelHeight = WINDOW_HEIGHT - 400;
    private Label title;
    private JLabel typeAssignment;
    private String typeDueDate, assignName, dueDate;
    private InputField dueDateInput;
    private Button backButton;
    private JPanel questionPanel;

    /**
     * Prepares the page to select questions for an assignment.
     *
     * @param questions the list of questions available
     */
    public AddAssignmentPage(List<Question> questions) {
        super();
        CurrentSession.addingAssignment = true;
        saveButton = new SaveQuestionButton();
        backButton = new Button("Back");
        assignmentInput = new InputField();
        questionList = questions;
        questionLabels = new Label[questions.size()];
        questionCheckBoxes = new ArrayList<>();
        dueDateInput = new InputField();
        questionPanel = new JPanel();
        questionPanel.setBackground(Color.WHITE);
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

    /**
     * Section to show questions to add to the assignment.
     */
    private void addQuestions() {
        int totalHeight = 10;
        for (int i = 0; i < questionList.size(); i++) {
            // increase label height to account for long assignment name
            String text = "<html>" + questionList.get(i).question + "</html>";
            int labelHeight = UIManager.getLabelHeight(text);

            questionCheckBoxes.add(new CheckBox());
            questionCheckBoxes.get(i).addMouseListener(this);

            questionLabels[i] = new Label(text, SwingConstants.LEFT);
            questionLabels[i].setIndex(i);
            questionLabels[i].setPreferredSize(new Dimension(LABEL_WIDTH, labelHeight));
            questionLabels[i].setFont(getFont().deriveFont(16f));
            questionLabels[i].addMouseListener(this);
            questionLabels[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            questionPanel.add(questionCheckBoxes.get(i));
            questionPanel.add(UIManager.getSpacing(10, 0));
            questionPanel.add(questionLabels[i]);
            questionPanel.add(UIManager.getSpacing(WINDOW_WIDTH, 1));

            // update the height required to display all questions
            totalHeight += (labelHeight + 15);
        }

        questionPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, totalHeight));
        if (totalHeight < questionPanelHeight) {
            add(questionPanel);
        } else {
            // not enough space to display all questions, add a scroll bar
            add(new ScrollPanel(questionPanel, questionPanelHeight));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.SAVE_QUESTION:
                if (validateInput()) {
                    createAssignment();
                } else {
                    showErrorMessage();
                }
                break;
            case ClickableObject.CHECKBOX:
                CheckBox clickedCheckBox = ((CheckBox) e.getSource());
                if (clickedCheckBox.isSelected()) {
                    clickedCheckBox.deselect();
                } else {
                    clickedCheckBox.select();
                }
                break;
            case ClickableObject.LABEL:
                int index = ((Label) e.getSource()).getIndex();
                UIManager.switchView(new ViewQuestionPage(questionList.get(index)));
                break;
            case ClickableObject.BACK_BUTTON:
                CurrentSession.addingAssignment = false;
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

        }
    }

    /**
     * Creates the assignment based on selected questions and assignment name,
     * and adds it to the database.
     */
    private void createAssignment() {
        List<Integer> selectedQuestion = new ArrayList<>();
        for (CheckBox cb : questionCheckBoxes) {
            if (cb.isSelected()) {
                int index = questionCheckBoxes.indexOf(cb);
                if (questionList.get(index).id != -1) {
                    selectedQuestion.add(questionList.get(index).id);
                } else {
                    System.out.println("Invalid Question id: " + questionList.get(index).question);
                }
            }
        }
        DatabaseManager.addAssignment(new Assignment(assignName, CurrentSession.user.courseID, selectedQuestion, dueDate));
    }

    /**
     * Ensures the name of the assignment and due date are filled out.
     *
     * @return true if filled out, false otherwise
     */
    private boolean validateInput() {
        assignName = assignmentInput.getText();
        dueDate = dueDateInput.getText();
        return !(assignName.equals("") && dueDate.equals(""));


    }

    /**
     * Notifies the user to enter the proper information.
     */
    private void showErrorMessage() {

        title.setText("Please enter assignment name and due date");
        title.setForeground(Color.WHITE);
        title.setBackground(Color.RED);
        title.setOpaque(true);

        Timer t = new Timer(5000, (actionEvent) -> {
            title.setText("Create Assignment");
            title.setPreferredSize(new Dimension(800, 50));
            title.setFont(getFont().deriveFont(24f));
            title.setForeground(Color.BLACK);
            title.setBackground(Color.WHITE);
        });
        t.setRepeats(false);
        t.start();
    }
}
