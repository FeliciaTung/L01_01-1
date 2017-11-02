package ui;

import holders.Question;

import javax.swing.*;
import java.awt.*;

public class UIManager {

    private JFrame frame;

    public UIManager() {
        frame = new JFrame("CSCC01 - Team01 Project");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //TODO: update the parameters
//        Question[] list = DatabaseManager.getQuestionsList();

        //TODO: create question function and db function to retrieve existing question name by id
        Question one = new Question(1,"question1", "ans1", "algebra", null);
        Question two = new Question(2,"question2", "ans1", "algebra", null);
        Question three = new Question(3,"question3", "ans1", "algebra", null );

        Question[] list = {one, two, three};

        switchView(new AddAssignmentPage(list));

//        switchView(new AddQuestionPage());
    }

    public void switchView(JPanel view) {
        frame.setContentPane(view);
        frame.pack();
        view.updateUI();
    }

    public static JComponent getSpacing(int width, int height) {
        JComponent spacing = new JComponent() {};
        spacing.setPreferredSize(new Dimension(width, height));
        return spacing;
    }
}
