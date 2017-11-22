package ui.pages;

import backend.DatabaseManager;
import holders.User;
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
    private String[] userInfo;
    private JPasswordField password;
    private Label title;
    private Label newuser;

    public LoginPage() {

        loginButton = new LoginButton();
        userInfo = new String[]{"UTORid", "Password"};
        input = new InputField[2]; // UTORid and password
        password = new JPasswordField();
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

        for (int i = 0; i < userInfo.length; i++) {
            Label text = new Label(userInfo[i], SwingConstants.LEFT);
            text.setFont(getFont().deriveFont(16f));
            text.setPreferredSize(new Dimension(InputField.WIDTH, 25));
            add(text);
            if (i == 0) {
                input[i] = new InputField();
                add(input[i]);
            } else{
                password.setPreferredSize(new Dimension(InputField.WIDTH, InputField.HEIGHT));
                password.setBackground(new Color(240, 240, 240));
                password.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                password.setFont(getFont().deriveFont(24f));
                add(password);
            }

        }

        add(UIManager.getSpacing(800, 40));

        loginButton.addMouseListener(this);
        add(loginButton);

        add(UIManager.getSpacing(800, 40));

        newuser = new Label("Not a user? Click here", SwingConstants.CENTER);
        newuser.setPreferredSize(new Dimension(800, 50));
        newuser.setFont(getFont().deriveFont(24f));
        newuser.addMouseListener(this);
        add(newuser);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.LOGIN_BUTTON:
                if (validatePassword()) {
                    int login = loginButton() + 40;
                    switch (login) {
                        case ClickableObject.USER_TYPE_3:
                            UIManager.switchView(new StudentHomePage());
                            break;
                        case ClickableObject.USER_TYPE_2:
                        case ClickableObject.USER_TYPE_1:
                            UIManager.switchView(new InstructorHomePage());
                            break;
                        }
                } else {
                    pwErrorMessage();
                }
                break;
            case ClickableObject.LABEL:
                UIManager.switchView(new RegisterPage());
                break;
        }
    }

    private void pwErrorMessage() {
        title.setText("Please enter a valid password");
        title.setForeground(Color.WHITE);
        title.setBackground(Color.RED);
        title.setOpaque(true);
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
    }

    private boolean validatePassword() {
        boolean valid = true;
        String uname = input[0].getText();
        String pw = new String(password.getPassword());
        User user = DatabaseManager.getUser(uname, pw);
        if (user == null) {
            valid = false;
        }
        return valid;
    }

    private int loginButton() {
        String uname = input[0].getText();
        String pw = new String(password.getPassword());
        User user = DatabaseManager.getUser(uname, pw);
        int type = user.type;
        return type;
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
            case ClickableObject.LOGIN_BUTTON:
                loginButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
        }
    }

    public void mouseExited(MouseEvent e) {
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.LOGIN_BUTTON:
                loginButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
            }
        }
}
