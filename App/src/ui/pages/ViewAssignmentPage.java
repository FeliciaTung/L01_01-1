package ui.pages;

import backend.CurrentSession;
import backend.DatabaseManager;
import holders.Assignment;
import holders.Question;
import ui.UIManager;
import ui.components.Button;
import ui.components.ClickableObject;
import ui.components.Label;
import ui.components.ScrollPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * Page to view an assignment.
 */
public class ViewAssignmentPage extends JPanel implements MouseListener {


    private List<Question> questionList;
    private Label[] questionLabels;
    private Button backButton;
    private int WINDOW_WIDTH = 800;
    private int WINDOW_HEIGHT = 680;
    private int LABEL_WIDTH = 600;
    private int questionPanelHeight = WINDOW_HEIGHT - 350;
    private Label title;
    private Label assignmentLabel;
    private JPanel questionPanel;

    /**
     * Prepares the page to display the questions in the assignment.
     *
     * @param assignment the assignment to load information from
     */
    public ViewAssignmentPage(Assignment assignment) {
        super();
        questionList = assignment.getQuestions();
        questionLabels = new Label[assignment.questions.size()];
        backButton = new Button("Back");
        questionPanel = new JPanel();
        questionPanel.setBackground(Color.WHITE);

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.WHITE);

        backButton.id = ClickableObject.BACK_BUTTON;
        backButton.addMouseListener(this);
        add(backButton);
        add(UIManager.getSpacing(WINDOW_WIDTH - 220, 1));

        title = new Label("Assignment", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        title.setFont(getFont().deriveFont(24f));
        add(title);

        add(UIManager.getSpacing(WINDOW_WIDTH, 40));

        assignmentLabel = new Label("Assignment: " + assignment.name, SwingConstants.CENTER);
        assignmentLabel.setPreferredSize(new Dimension(LABEL_WIDTH, 50));
        assignmentLabel.setFont(getFont().deriveFont(18f));
        add(assignmentLabel);

        add(UIManager.getSpacing(WINDOW_WIDTH, 30));

        addQuestions();

        add(UIManager.getSpacing(WINDOW_WIDTH, 40));

        backButton.addMouseListener(this);
        add(backButton);
    }

    /**
     * Adds the questions to display in the page.
     */
    private void addQuestions() {
        int totalHeight = 10;
        for (int i = 0; i < questionList.size(); i++) {
            // increase label height to deal with long question
            String text = "<html>" + (i + 1) + ". " + questionList.get(i).question + "</html>";
            int labelHeight = UIManager.getLabelHeight(text);

            questionLabels[i] = new Label(text, SwingConstants.LEFT);
            questionLabels[i].setIndex(i);
            questionLabels[i].setPreferredSize(new Dimension(LABEL_WIDTH, labelHeight));
            questionLabels[i].setFont(getFont().deriveFont(16f));
            questionLabels[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            questionLabels[i].addMouseListener(this);
            questionPanel.add(questionLabels[i]);
            questionPanel.add(UIManager.getSpacing(WINDOW_WIDTH, 1));
            totalHeight += (labelHeight + 10);
        }
        questionPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, totalHeight));
        if (totalHeight < questionPanelHeight) {
            add(questionPanel);
        } else {
            // not enough space to display all questions, add a scroll bar
            add(new ScrollPanel(questionPanel, questionPanelHeight));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.BACK_BUTTON:
                UIManager.switchView(new ViewAllAssignmentsPage(
                        DatabaseManager.getAllAssignment(CurrentSession.user.id, CurrentSession.user.courseID)));
                break;
            case ClickableObject.LABEL:
                int index = ((Label) e.getSource()).getIndex();
                UIManager.switchView(new ViewQuestionPage(questionList.get(index)));
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
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case ClickableObject.BACK_BUTTON:
                backButton.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
            case ClickableObject.LABEL:
                int index = ((Label) e.getSource()).getIndex();
                questionLabels[index].setForeground(Label.LABEL_COLOR_PRESSED);
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.BACK_BUTTON:
                backButton.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
            case ClickableObject.LABEL:
                int index = ((Label) e.getSource()).getIndex();
                questionLabels[index].setForeground(Label.LABEL_COLOR_IDLE);
        }
    }
}
