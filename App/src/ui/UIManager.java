package ui;

import backend.DatabaseManager;
import holders.*;
import ui.pages.*;

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

//        ArrayList<Question> list = DatabaseManager.getAllQuestions(1, true);
//        Question[] qlist = list.toArray(new Question[list.size()]);
//        switchView(new AddAssignmentPage(qlist));

        ArrayList<Assignment> assignments = DatabaseManager.getAllAssignment(1);
        Assignment[] alist = assignments.toArray(new Assignment[assignments.size()]);
        switchView(new ViewAllAssignmentsPage(alist));

//        Assignment a = DatabaseManager.getAssignment(1);
//        switchView(new ViewAssignmentPage(a));

//        switchView(new AddQuestionPage());

//        Question q = DatabaseManager.getQuestion(1);
//        switchView(new ViewQuestionPage(q));
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

    public static void switchToAssignmentView(Assignment assign) {
        switchView(new ViewAssignmentPage(assign));
    }

    public static void switchToQuestionView(Question question) {
        switchView (new ViewQuestionPage(question));
    }
}
