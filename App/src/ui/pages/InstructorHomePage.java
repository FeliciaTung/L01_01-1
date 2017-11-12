package ui.pages;

import backend.DatabaseManager;
import holders.Assignment;
import holders.Question;
import ui.UIManager;
import ui.components.Button;
import ui.components.ClickableObject;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class InstructorHomePage extends JPanel implements MouseListener {

    private Button addQuestionButton;
    private Button addAssignmentButton;
    private Button viewAssignmentsButton;

    public InstructorHomePage() {
        addQuestionButton = new Button("Add Question");
        addAssignmentButton = new Button("Add Assignment");
        viewAssignmentsButton = new Button("View All Assignments");

        addQuestionButton.id = ClickableObject.ADD_QUESTION;
        addQuestionButton.addMouseListener(this);
        add(addQuestionButton);

        addAssignmentButton.id = ClickableObject.ADD_ASSIGNMENT;
        addAssignmentButton.addMouseListener(this);
        add(addAssignmentButton);

        viewAssignmentsButton.id = ClickableObject.VIEW_ASSIGNMENTS;
        viewAssignmentsButton.addMouseListener(this);
        add(viewAssignmentsButton);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.ADD_QUESTION:
                UIManager.switchView(new AddQuestionPage());
                break;
            case ClickableObject.ADD_ASSIGNMENT:
                List<Question> list = DatabaseManager.getAllQuestions(1, true);
                Question[] qlist = list.toArray(new Question[list.size()]);
                UIManager.switchView(new AddAssignmentPage(list));
                break;
            case ClickableObject.VIEW_ASSIGNMENTS:
                List<Assignment> assignments = DatabaseManager.getAllAssignment(1);
                UIManager.switchView(new ViewAllAssignmentsPage(assignments));
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.ADD_QUESTION:
                addQuestionButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
            case ClickableObject.ADD_ASSIGNMENT:
                addAssignmentButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
            case ClickableObject.VIEW_ASSIGNMENTS:
                viewAssignmentsButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.ADD_QUESTION:
                addQuestionButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
            case ClickableObject.ADD_ASSIGNMENT:
                addAssignmentButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
            case ClickableObject.VIEW_ASSIGNMENTS:
                viewAssignmentsButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
        }
    }
}
