package ui.pages;

import ui.UIManager;
import ui.components.Button;
import ui.components.ClickableObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StudentHomePage extends JPanel implements MouseListener {

    private static int WINDOW_WIDTH = 800;
    private static int WINDOW_HEIGHT = 680;

    private JLabel title;
    private Button viewAssignmentsButton;

    public StudentHomePage() {
        title = new JLabel("Welcome \"Student\"", SwingConstants.CENTER);
        viewAssignmentsButton = new Button("View Assignments");

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.WHITE);

        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(WINDOW_WIDTH, 100));

        viewAssignmentsButton.id = ClickableObject.VIEW_ASSIGNMENTS;
        viewAssignmentsButton.addMouseListener(this);
        add(viewAssignmentsButton);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.VIEW_ASSIGNMENTS:
                UIManager.switchView(new AvailableAssignments(null));
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
            case ClickableObject.VIEW_ASSIGNMENTS:
                viewAssignmentsButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.VIEW_ASSIGNMENTS:
                viewAssignmentsButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
        }
    }
}
