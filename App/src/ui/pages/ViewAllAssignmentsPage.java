package ui.pages;

import backend.DatabaseManager;
import holders.Assignment;
import holders.Question;
import ui.UIManager;
import ui.components.Button;
import ui.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class ViewAllAssignmentsPage extends JPanel implements MouseListener {

    /*
    private EditQuestionButton[] editButton;
    private DeleteQuestionButton[] deleteButton;
    */


    private JLabel[] assignLabels;
    private CheckBox[] assignCheckBoxes;
    private Assignment[] assignList;
    private float labelTextSize = 20f;

    public ViewAllAssignmentsPage(Assignment[] assignments) {
        super();
        int assign_num = assignments.length;
        /*
        editButton = new EditQuestionButton[question_num];
        deleteButton = new DeleteQuestionButton[question_num];
        */

        assignList = assignments;
        assignLabels = new JLabel[assign_num];
        assignCheckBoxes = new CheckBox[assign_num];

        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Available Assignments", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(800, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(800, 40));

        for (int i = 0; i < assignList.length; i++) {

            assignLabels[i] = new JLabel(assignList[i].name, SwingConstants.LEFT);
            assignLabels[i].setPreferredSize(new Dimension(InputField.WIDTH, 25));
            assignLabels[i].setFont(getFont().deriveFont(labelTextSize));
            assignLabels[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            assignLabels[i].addMouseListener(this);
            add(assignLabels[i]);

            add(UIManager.getSpacing(800, 1));
        }

        add(UIManager.getSpacing(800, 40));


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel clickedLabel = (JLabel) e.getSource();
        for (int i = 0; i < assignLabels.length; i++){
            if (clickedLabel == assignLabels[i]){
                gotoAssignment(assignList[i]);
            }
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
        JLabel clickedLabel = (JLabel) e.getSource();
        for (int i = 0; i < assignLabels.length; i++){
            if (clickedLabel == assignLabels[i]){
                assignLabels[i].setForeground(Color.BLUE);
                break;
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel clickedLabel = (JLabel) e.getSource();
        for (int i = 0; i < assignLabels.length; i++){
            if (clickedLabel == assignLabels[i]){
                assignLabels[i].setForeground(Color.BLACK);
                break;
            }
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
    }
}
