package ui.pages;

import holders.Assignment;
import ui.UIManager;
import ui.components.Button;
import ui.components.ClickableObject;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class AvailableAssignments extends JPanel implements MouseListener {

    private static int WINDOW_WIDTH = 800;
    private static int WINDOW_HEIGHT = 600;

    private List<Assignment> assignments;
    private JLabel title;
    private JLabel assignmentNames[];
    private JLabel assignmentMarks[];
    private List<Button> takeAssignmentButtons;


    public AvailableAssignments(List<Assignment> assignments) {
        //Dummy Data
        assignments = new ArrayList<>();
        assignments.add(new Assignment(-1, "Dummy Assignment 1", 0, null, 33.33f));
        assignments.add(new Assignment(-1, "Dummy Assignment 2", 0, null, 100f));
        assignments.add(new Assignment(-1, "Dummy Assignment 3", 0, null, -1));
        this.assignments = assignments;

        title = new JLabel("Assignments", SwingConstants.CENTER);
        assignmentNames = new JLabel[assignments.size()];
        assignmentMarks = new JLabel[assignments.size()];
        takeAssignmentButtons = new ArrayList<>();

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(WINDOW_WIDTH, 75));

        for (int i = 0; i < assignments.size(); i++) {

            assignmentNames[i] = new JLabel(assignments.get(i).name, SwingConstants.LEFT);
            assignmentNames[i].setPreferredSize(new Dimension(300, 50));
            assignmentNames[i].setFont(getFont().deriveFont(18f));
            add(assignmentNames[i]);

            boolean assignmentCompleted = assignments.get(i).mark >= 0;
            String mark = assignmentCompleted ? ("" + assignments.get(i).mark + "%") : "Not Completed";
            assignmentMarks[i] = new JLabel(mark, SwingConstants.LEFT);
            assignmentMarks[i].setPreferredSize(new Dimension(200, 50));
            assignmentMarks[i].setFont(getFont().deriveFont(18f));
            add(assignmentMarks[i]);

            takeAssignmentButtons.add(new Button(assignmentCompleted ? "Retake Assignment" : "Take Assignment"));
            takeAssignmentButtons.get(i).id = ClickableObject.DO_ASSIGNMENT;
            takeAssignmentButtons.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            takeAssignmentButtons.get(i).addMouseListener(this);
            add(takeAssignmentButtons.get(i));

            add(UIManager.getSpacing(WINDOW_WIDTH, 10));

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.DO_ASSIGNMENT:
                UIManager.switchView(new AssignmentPage(assignments.get(takeAssignmentButtons.indexOf(e.getSource()))));
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
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.DO_ASSIGNMENT:
                takeAssignmentButtons.get(takeAssignmentButtons.indexOf(e.getSource())).setBackground(Button.BUTTON_COLOR_IDLE);
        }
    }
}
