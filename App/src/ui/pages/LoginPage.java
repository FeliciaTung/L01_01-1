package ui.pages;

import backend.DatabaseManager;
import ui.UIManager;

import ui.components.*;
import ui.components.Button;
import ui.components.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import ui.components.ClickableObject;
import ui.components.Label;

public class LoginPage extends JPanel implements MouseListener {

    //private Button studentButton;
    //private Button instructorButton;
    private Button loginButton;
    private InputField[] input;
    private String[] labelText;
    private JPasswordField[] password;
    private Label title;

    public LoginPage() {

        //studentButton = new Button("student");
        //instructorButton = new Button("instructor");

        //studentButton.id = ClickableObject.STUDENT_BUTTON;
        //studentButton.addMouseListener(this);
        //add(studentButton);

        //instructorButton.id = ClickableObject.INSTRUCTOR_BUTTON;
        //instructorButton.addMouseListener(this);
        //add(instructorButton);

        loginButton = new SaveQuestionButton();
        labelText = new String[]{"UTORid", "password"};
        input = new InputField[2]; // UTORid and password
        password = new JPasswordField[1];
        setPreferredSize(new Dimension(800, 680));
        setBackground(Color.WHITE);

        addContent();

        add(UIManager.getSpacing(800, 30));
    }

    private void addContent() {
        // clear everything
        removeAll();
        title = new Label("Login", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(800, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(800, 40));

        // add "UTORid" and "password"

        for (int i = 0; i < labelText.length; i++) {
            Label text = new Label(labelText[i], SwingConstants.CENTER);
            text.setFont(getFont().deriveFont(16f));
            text.setPreferredSize(new Dimension(InputField.WIDTH, 25));
            add(text);
        }

        add(UIManager.getSpacing(800, 40));

        loginButton.addMouseListener(this);
        add(loginButton);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.SAVE_QUESTION:
                if (validatePassword()) {
                    loginButton();
                } else {
                    showErrorMessage();
                }
            case ClickableObject.STUDENT_BUTTON:
                UIManager.switchView(new StudentHomePage());
                break;
            case ClickableObject.INSTRUCTOR_BUTTON:
                UIManager.switchView(new InstructorHomePage());
                break;
        }
    }

    private void showErrorMessage() {
        title.setText("Please enter a valid password");
        title.setForeground(Color.WHITE);
        title.setBackground(Color.RED);
        title.setOpaque(true);
        /*
        Timer t = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                title.setText("Login");
                title.setPreferredSize(new Dimension(800, 50));
                title.setFont(getFont().deriveFont(24f));
                title.setForeground(Color.BLACK);
                title.setBackground(Color.WHITE);
            }
        });
        t.setRepeats(false);
        t.start();
        */
    }

    private boolean validatePassword() {
        // TODO: validate password from backend
    }

    private void loginButton() {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.SAVE_QUESTION:
                loginButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
        }
    }

    public void mouseExited(MouseEvent e) {
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.SAVE_QUESTION:
                loginButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
            }
        }
}
