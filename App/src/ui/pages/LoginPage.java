package ui.pages;

import ui.UIManager;
import ui.components.Button;
import ui.components.ClickableObject;
import ui.components.Label;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// TODO: This is temporary, update this class to be a login page, where we automatically detect whether user is a student or prof
public class LoginPage extends JPanel implements MouseListener {

    private Button studentButton;
    private Button instructorButton;

    public LoginPage() {
        studentButton = new Button("student");
        instructorButton = new Button("instructor");

        studentButton.id = ClickableObject.STUDENT_BUTTON;
        studentButton.addMouseListener(this);
        add(studentButton);

        instructorButton.id = ClickableObject.INSTRUCTOR_BUTTON;
        instructorButton.addMouseListener(this);
        add(instructorButton);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.STUDENT_BUTTON:
                UIManager.switchView(new StudentHomePage());
                break;
            case ClickableObject.INSTRUCTOR_BUTTON:
                UIManager.switchView(new InstructorHomePage());
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
            case ClickableObject.STUDENT_BUTTON:
                studentButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
            case ClickableObject.INSTRUCTOR_BUTTON:
                instructorButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.STUDENT_BUTTON:
                studentButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
            case ClickableObject.INSTRUCTOR_BUTTON:
                instructorButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
        }
    }
}
