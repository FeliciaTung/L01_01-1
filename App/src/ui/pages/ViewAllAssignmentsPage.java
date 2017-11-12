package ui.pages;

import holders.Assignment;
import ui.UIManager;
import ui.components.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ViewAllAssignmentsPage extends JPanel implements MouseListener {

    /*
    private EditQuestionButton[] editButton;
    private DeleteQuestionButton[] deleteButton;
    */


    private Label[] assignLabels;
    private Assignment[] assignList;
    private float labelTextSize = 16f;
    private int LABEL_WIDTH = 600;
    private int WINDOW_WIDTH = 800;
    private Label title;
    private Label viewAssignLabel;

    public ViewAllAssignmentsPage(Assignment[] assignments) {
        super();
        int assign_num = assignments.length;
        /*
        editButton = new EditQuestionButton[question_num];
        deleteButton = new DeleteQuestionButton[question_num];
        */

        assignList = assignments;
        assignLabels = new Label[assign_num];

        setPreferredSize(new Dimension(WINDOW_WIDTH, 600));
        setBackground(Color.WHITE);

        title = new Label("View All Assignments", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(WINDOW_WIDTH, 40));

        viewAssignLabel = new Label("Available Assignments", SwingConstants.CENTER);
        viewAssignLabel.setFont(getFont().deriveFont(18f));
        add(viewAssignLabel);


        for (int i = 0; i < assignList.length; i++) {
            // increase label height to deal with long assignment name
            String text = "<html>" + (i + 1) + ". " + assignList[i].name + "</html>";
            int labelHeight = 25;
            if (text.length() > 199) {
                labelHeight = 65;
            } else if (text.length() > 99) {
                labelHeight = 45;
            }

            assignLabels[i] = new Label(text, SwingConstants.LEFT);
            assignLabels[i].setIndex(i);
            assignLabels[i].setPreferredSize(new Dimension(LABEL_WIDTH, labelHeight));
            assignLabels[i].setFont(getFont().deriveFont(labelTextSize));
            assignLabels[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            assignLabels[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            assignLabels[i].addMouseListener(this);
            add(assignLabels[i]);

            add(UIManager.getSpacing(WINDOW_WIDTH, 1));
        }

        add(UIManager.getSpacing(WINDOW_WIDTH, 40));


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int clickedLabelIndex = ((Label) e.getSource()).getIndex();
        gotoAssignment(assignList[clickedLabelIndex]);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        int clickedLabelIndex = ((Label) e.getSource()).getIndex();
        assignLabels[clickedLabelIndex].setForeground(Label.LABEL_COLOR_PRESSED);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        int clickedLabelIndex = ((Label) e.getSource()).getIndex();
        assignLabels[clickedLabelIndex].setForeground(Label.LABEL_COLOR_IDLE);
    }

    private void editQuestion() {
        //TODO: switch to edit question page
    }


    private void deleteQuestion() {
        //TODO: trigger delete question function

    }

    private void gotoAssignment(Assignment assign) {
        //TODO: switch view to individual assignment page
        UIManager.switchToAssignmentView(assign);
    }
}
