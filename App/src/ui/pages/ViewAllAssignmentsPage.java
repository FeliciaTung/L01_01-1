package ui.pages;

import holders.Assignment;
import ui.UIManager;
import ui.components.ClickableObject;
import ui.components.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;


public class ViewAllAssignmentsPage extends JPanel implements MouseListener {

    /*
    private EditQuestionButton[] editButton;
    private DeleteQuestionButton[] deleteButton;
    */

    private JLabel[] assignLabels;
    private JLabel[] dueDateLabels;
    private List<Button> detailButton;
    private List<Assignment> assignList;
    private float labelTextSize = 16f;
    private int LABEL_WIDTH = 200;
    private int WINDOW_WIDTH = 800;
    private int WINDOW_HEIGHT = 680;
    private JLabel title;
    private JLabel AssignNameLabel;
    private Button backButton;

    public ViewAllAssignmentsPage(List<Assignment> assignments) {
        super();
        int numOfAssignments = assignments.size();
        /*
        editButton = new EditQuestionButton[question_num];
        deleteButton = new DeleteQuestionButton[question_num];
        */

        assignList = assignments;
        assignLabels = new JLabel[numOfAssignments];
        dueDateLabels = new JLabel[numOfAssignments];
        detailButton = new ArrayList<>();

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


        for (int i = 0; i < numOfAssignments; i++) {
            // increase label height to deal with long assignment name
            String text = "<html>" + assignList.get(i).name + "</html>";
            int labelHeight = 25;
            if (text.length() > 199) {
                labelHeight = 65;
            } else if (text.length() > 99) {
                labelHeight = 45;
            }

            assignLabels[i] = new JLabel(text, SwingConstants.LEFT);
            assignLabels[i].setPreferredSize(new Dimension(LABEL_WIDTH, labelHeight));
            assignLabels[i].setFont(getFont().deriveFont(labelTextSize));
            assignLabels[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            assignLabels[i].setFont(getFont().deriveFont(18f));


            dueDateLabels[i] = new JLabel(assignList.get(i).dueDate, SwingConstants.LEFT);
            dueDateLabels[i].setFont(getFont().deriveFont(labelTextSize));
            dueDateLabels[i].setPreferredSize(new Dimension(LABEL_WIDTH, labelHeight));
            dueDateLabels[i].setFont(getFont().deriveFont(18f));

            detailButton.add(new Button("Detail"));
            detailButton.get(i).id = ClickableObject.EDIT_QUESTION;
            detailButton.get(i).setPreferredSize(new Dimension(100, Button.HEIGHT));
            detailButton.get(i).addMouseListener(this);

            add(assignLabels[i]);
            add(dueDateLabels[i]);
            add(detailButton.get(i));


            add(UIManager.getSpacing(WINDOW_WIDTH, 1));
        }

        add(UIManager.getSpacing(WINDOW_WIDTH, 40));


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int clickedItem = ((ClickableObject) e.getSource()).getID();
        switch(clickedItem){
            case ClickableObject.EDIT_QUESTION:
                gotoAssignment(assignList.get(detailButton.indexOf((e.getSource()))));
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
        int clickedItem = ((ClickableObject) e.getSource()).getID();
        switch(clickedItem){
            case ClickableObject.EDIT_QUESTION:
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
        switch(clickedItem){
            case ClickableObject.EDIT_QUESTION:
                detailButton.get(detailButton.indexOf((e.getSource()))).setBackground(Button.BUTTON_COLOR_IDLE);
                break;
            case ClickableObject.BACK_BUTTON:
                backButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
        }
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
