package ui.pages;

import backend.CurrentSession;
import holders.Assignment;
import ui.UIManager;
import ui.components.ClickableObject;
import ui.components.Button;
import ui.components.ScrollPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Page to display all existing assignments.
 */
public class ViewAllAssignmentsPage extends JPanel implements MouseListener {

    private JLabel[] assignLabels;
    private JLabel[] dueDateLabels;
    private List<Button> detailButton;
    private List<Assignment> assignList;
    private float labelTextSize = 18f;
    private int LABEL_WIDTH = 200;
    private int WINDOW_WIDTH = 800;
    private int WINDOW_HEIGHT = 680;
    private int numOfAssignments;
    private int assignPanelHeight = WINDOW_HEIGHT - 300;
    private JLabel title;
    private JLabel AssignNameLabel;
    private Button backButton;
    private JPanel assignPanel;

    /**
     * Prepares the page to display all assignments.
     *
     * @param assignments the list of assignments to be displayed
     */
    public ViewAllAssignmentsPage(List<Assignment> assignments) {
        super();
        numOfAssignments = assignments.size();
        assignList = assignments;
        assignLabels = new JLabel[numOfAssignments];
        dueDateLabels = new JLabel[numOfAssignments];
        detailButton = new ArrayList<>();
        assignPanel = new JPanel();
        assignPanel.setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.WHITE);

        backButton = new Button("Back");
        backButton.id = ClickableObject.BACK_BUTTON;
        backButton.addMouseListener(this);
        add(backButton);

        add(UIManager.getSpacing(WINDOW_WIDTH - 220, 1));

        title = new JLabel("View All Assignments", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(WINDOW_WIDTH, 40));

        AssignNameLabel = new JLabel("Assignment Name", SwingConstants.LEFT);
        AssignNameLabel.setFont(getFont().deriveFont(18f));
        AssignNameLabel.setPreferredSize(new Dimension(LABEL_WIDTH, 25));
        add(AssignNameLabel);

        JLabel AssignDateLabel = new JLabel("Due Date", SwingConstants.LEFT);
        AssignDateLabel.setPreferredSize(new Dimension(LABEL_WIDTH, 25));
        AssignDateLabel.setFont(getFont().deriveFont(18f));

        add(AssignDateLabel);

        add(UIManager.getSpacing(100, 1));
        add(UIManager.getSpacing(WINDOW_WIDTH, 20));

        addAssignmentPanel();
    }

    /**
     * Small panel to add more assignments to the database.
     */
    private void addAssignmentPanel() {
        int totalHeight = 0;
        for (int i = 0; i < numOfAssignments; i++) {
            // increase label height to deal with long assignment name
            String text = "<html>" + assignList.get(i).name + "</html>";
            int labelHeight = 25;
            if (text.length() > 99) {
                labelHeight = 65;
            } else if (text.length() > 39) {
                labelHeight = 45;
            }

            assignLabels[i] = new JLabel(text, SwingConstants.LEFT);
            assignLabels[i].setPreferredSize(new Dimension(LABEL_WIDTH, labelHeight));
            assignLabels[i].setFont(getFont().deriveFont(labelTextSize));

            dueDateLabels[i] = new JLabel(assignList.get(i).dueDate, SwingConstants.LEFT);
            dueDateLabels[i].setFont(getFont().deriveFont(labelTextSize));
            dueDateLabels[i].setPreferredSize(new Dimension(LABEL_WIDTH, labelHeight));

            detailButton.add(new Button("Detail"));
            detailButton.get(i).id = ClickableObject.VIEW_QUESTION;
            detailButton.get(i).setPreferredSize(new Dimension(100, Button.HEIGHT));
            detailButton.get(i).addMouseListener(this);

            assignPanel.add(assignLabels[i]);
            assignPanel.add(dueDateLabels[i]);
            assignPanel.add(detailButton.get(i));
            assignPanel.add(UIManager.getSpacing(WINDOW_WIDTH, 1));
            totalHeight += (Button.HEIGHT + 15);
        }

        assignPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, totalHeight));
        if (totalHeight < assignPanelHeight) {
            add(assignPanel);
        } else {
            // not enough space to display all questions, add a scroll bar
            add(new ScrollPanel(assignPanel, assignPanelHeight));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int clickedItem = ((ClickableObject) e.getSource()).getID();
        switch (clickedItem) {
            case ClickableObject.VIEW_QUESTION:
                Assignment assignment = assignList.get(detailButton.indexOf((e.getSource())));
                CurrentSession.assignment = assignment;
                UIManager.switchView(new ViewAssignmentPage(assignment));
                break;
            case ClickableObject.BACK_BUTTON:
                CurrentSession.assignment = null;
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
        int clickedItem = ((ClickableObject) e.getSource()).getID();
        switch (clickedItem) {
            case ClickableObject.VIEW_QUESTION:
                detailButton.get(detailButton.indexOf((e.getSource()))).setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
            case ClickableObject.BACK_BUTTON:
                backButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        int clickedItem = ((ClickableObject) e.getSource()).getID();
        switch (clickedItem) {
            case ClickableObject.VIEW_QUESTION:
                detailButton.get(detailButton.indexOf((e.getSource()))).setBackground(Button.BUTTON_COLOR_IDLE);
                break;
            case ClickableObject.BACK_BUTTON:
                backButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
        }
    }

}
