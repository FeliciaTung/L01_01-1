package ui;

import backend.DatabaseManager;
import holders.Question;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UIManager {

    private static JFrame frame;

    public static void createUI() {
        frame = new JFrame("CSCC01 - Team01 Project");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        /*
        Question one = new Question(1,"question1", "ans1", "algebra", null);
        Question two = new Question(2,"question2", "ans1", "algebra", null);
        Question three = new Question(3,"question3", "ans1", "algebra", null );
*/
        // get array list of question from database and convert into array
        ArrayList<Question> list = DatabaseManager.getAllQuestions(1);
        Question[] qlist = list.toArray(new Question[list.size()]);

//        switchView(new AddAssignmentPage(qlist));

        switchView(new AddQuestionPage());
    }

    public static void switchView(JPanel view) {
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
