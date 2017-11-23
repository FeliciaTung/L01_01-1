package ui.pages;

import backend.CurrentSession;
import backend.DatabaseManager;
import holders.Question;
import ui.UIManager;
import ui.components.Button;
import ui.components.ClickableObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class InstructorHomePage extends JPanel implements MouseListener {

    private static int WINDOW_WIDTH = 800;
    private static int WINDOW_HEIGHT = 680;

    private JLabel title;
    private Button addQuestionButton;
    private Button addAssignmentButton;
    private Button viewAssignmentsButton;

    public InstructorHomePage() {
        title = new JLabel("Welcome \"Instructor\"", SwingConstants.CENTER);
        addQuestionButton = new Button("Add Question");
        addAssignmentButton = new Button("Add Assignment");
        viewAssignmentsButton = new Button("View All Assignments");

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);
        setBackground(Color.WHITE);

        add(UIManager.getSpacing(WINDOW_WIDTH, 100));

        addQuestionButton.id = ClickableObject.ADD_QUESTION;
        addQuestionButton.addMouseListener(this);
        add(UIManager.getSpacing(250, 60));
        add(addQuestionButton);
        add(UIManager.getSpacing(250, 60));

        addAssignmentButton.id = ClickableObject.ADD_ASSIGNMENT;
        addAssignmentButton.addMouseListener(this);
        add(UIManager.getSpacing(250, 80));
        add(addAssignmentButton);
        add(UIManager.getSpacing(250, 80));

        viewAssignmentsButton.id = ClickableObject.VIEW_ASSIGNMENTS;
        viewAssignmentsButton.addMouseListener(this);
        viewAssignmentsButton.setPreferredSize(new Dimension(250, Button.HEIGHT));
        add(UIManager.getSpacing(250, 60));
        add(viewAssignmentsButton);
        add(UIManager.getSpacing(250, 60));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.ADD_QUESTION:
                UIManager.switchView(new AddQuestionPage());
                break;
            case ClickableObject.ADD_ASSIGNMENT:
                List<Question> list = DatabaseManager.getAllQuestions(-1);
                UIManager.switchView(new AddAssignmentPage(list));
                break;
            case ClickableObject.VIEW_ASSIGNMENTS:

                UIManager.switchView(new ViewAllAssignmentsPage(DatabaseManager
                        .getAllAssignment(CurrentSession.user.id, CurrentSession.user.courseID)));
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
