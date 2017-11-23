package ui.pages;

import backend.DatabaseManager;
import holders.Assignment;
import ui.UIManager;
import ui.components.Button;
import ui.components.ClickableObject;
import ui.components.ScrollPanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class AvailableAssignments extends JPanel implements MouseListener {

    private static int WINDOW_WIDTH = 800;
    private static int WINDOW_HEIGHT = 680;

    private List<Assignment> assignments;
    private JLabel title;
    private JLabel assignmentNames[];
    private JLabel assignmentMarks[];
    private JLabel assignmentDueDate[];
    private List<Button> takeAssignmentButtons;
    private Button backButton;
    private JPanel assignmentPanel;
    private int assignPanelHeight = WINDOW_HEIGHT - 200;

    public AvailableAssignments(List<Assignment> assignments) {
        this.assignments = assignments;

        title = new JLabel("Assignments", SwingConstants.CENTER);
        assignmentNames = new JLabel[assignments.size()];
        assignmentMarks = new JLabel[assignments.size()];
        assignmentDueDate = new JLabel[assignments.size()];
        takeAssignmentButtons = new ArrayList<>();
        backButton = new Button("Back");
        assignmentPanel = new JPanel();
        assignmentPanel.setBackground(Color.WHITE);

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.WHITE);

        backButton.id = ClickableObject.BACK_BUTTON;
        backButton.addMouseListener(this);
        add(backButton);
        add(UIManager.getSpacing(WINDOW_WIDTH - 220, 1));

        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(WINDOW_WIDTH, 75));

        addAssignments();
    }

    private void addAssignments() {
        int totalHeight = 10;
        for (int i = 0; i < assignments.size(); i++) {

            assignmentNames[i] = new JLabel(assignments.get(i).name, SwingConstants.LEFT);
            assignmentNames[i].setPreferredSize(new Dimension(150, 50));
            assignmentNames[i].setFont(getFont().deriveFont(18f));
            assignmentPanel.add(assignmentNames[i]);

            assignmentDueDate[i] = new JLabel(assignments.get(i).dueDate, SwingConstants.LEFT);
            assignmentDueDate[i].setPreferredSize(new Dimension(200, 50));
            assignmentDueDate[i].setFont(getFont().deriveFont(18f));
            assignmentPanel.add(assignmentDueDate[i]);


            boolean assignmentCompleted = assignments.get(i).mark >= 0;
            String mark = assignmentCompleted ? ("" + assignments.get(i).mark + "%") : "Not Completed";
            assignmentMarks[i] = new JLabel(mark, SwingConstants.LEFT);
            assignmentMarks[i].setPreferredSize(new Dimension(200, 50));
            assignmentMarks[i].setFont(getFont().deriveFont(18f));
            assignmentPanel.add(assignmentMarks[i]);

            takeAssignmentButtons.add(new Button(assignmentCompleted ? "Retake Assignment" : "Take Assignment"));
            takeAssignmentButtons.get(i).id = ClickableObject.DO_ASSIGNMENT;
            takeAssignmentButtons.get(i).addMouseListener(this);
            assignmentPanel.add(takeAssignmentButtons.get(i));

            assignmentPanel.add(UIManager.getSpacing(WINDOW_WIDTH, 10));
            totalHeight += (Button.HEIGHT + 20);
        }
        assignmentPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, totalHeight));
        if (totalHeight < assignPanelHeight) {
            add(assignmentPanel);
        } else {
            // not enough space to display all questions, add a scroll bar
            add(new ScrollPanel(assignmentPanel, assignPanelHeight));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.DO_ASSIGNMENT:
                UIManager.switchView(new AssignmentPage(assignments.get(takeAssignmentButtons.indexOf(e.getSource()))));
                break;
            case ClickableObject.BACK_BUTTON:
                UIManager.switchView(new StudentHomePage());
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
            case ClickableObject.DO_ASSIGNMENT:
                takeAssignmentButtons.get(takeAssignmentButtons.indexOf(e.getSource())).setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
            case ClickableObject.BACK_BUTTON:
                backButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.DO_ASSIGNMENT:
                takeAssignmentButtons.get(takeAssignmentButtons.indexOf(e.getSource())).setBackground(Button.BUTTON_COLOR_IDLE);
                break;
            case ClickableObject.BACK_BUTTON:
                backButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
        }
    }
}
