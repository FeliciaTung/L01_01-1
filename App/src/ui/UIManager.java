package ui;

import backend.DatabaseManager;
import holders.Assignment;
import holders.Question;
import ui.pages.*;
import ui.pages.AddQuestionPage;

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



        // get array list of question from database and convert into array
//        ArrayList<Question> list = DatabaseManager.getAllQuestions(1, true);
//        Question[] qlist = list.toArray(new Question[list.size()]);

//        switchView(new AddAssignmentPage(qlist));

        ArrayList<Assignment> assignments = DatabaseManager.getAllAssignment(1);
        Assignment[] alist = assignments.toArray(new Assignment[assignments.size()]);


        switchView(new ViewAllAssignmentsPage(alist));

//        switchView(new AddQuestionPage());
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
