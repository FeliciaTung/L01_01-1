package ui.pages;

import backend.DatabaseManager;
import holders.Assignment;
import holders.Question;
import holders.User;
import ui.UIManager;
import ui.components.MultipleChoiceAnswer;
import ui.components.Button;
import ui.components.ClickableObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AssignmentPage extends JPanel implements MouseListener {

    private List<Question> questions;
    private int currentQuestion;
    private int correctAnswers;
    private Assignment assignment;
    private JLabel progress;
    private JLabel question;
    private Button nextQuestion;
    private MultipleChoiceAnswer[] answerLabels;

    public AssignmentPage(Assignment assignment) {
        super();
        this.assignment = assignment;
        questions = assignment.getQuestions();
//        questions = new ArrayList<>(Arrays.asList(new Question("What's 2 + 2?", "4", "", new String[]{"1", "5", "42"}),
//                new Question("What's 4 * 2?", "8", "", new String[]{"16", "6", "24"}),
//                new Question("What's 30 / 5?", "6", "", new String[]{"4", "5", "25"})));
        currentQuestion = -1;
        correctAnswers = 0;
        progress = new JLabel("", SwingConstants.RIGHT);
        question = new JLabel("", SwingConstants.CENTER);
        nextQuestion = new Button("Next Question");
        answerLabels = new MultipleChoiceAnswer[4];
        for (int i = 0; i < answerLabels.length; i++) {
            answerLabels[i] = new MultipleChoiceAnswer();
            answerLabels[i].addMouseListener(this);
            add(answerLabels[i]);
        }

        setLayout(null);
        setPreferredSize(new Dimension(800, 680));
        setBackground(new Color(240, 240, 240));

        progress.setFont(getFont().deriveFont(16f));
        add(progress);

        question.setFont(getFont().deriveFont(24f));
        add(question);

        nextQuestion.id = ClickableObject.NEXT_QUESTION;
        nextQuestion.addMouseListener(this);
        add(nextQuestion);

        resize();
        setNextQuestion();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = ((ClickableObject) e.getSource()).getID();
        switch (id) {
            case MultipleChoiceAnswer.MULTIPLE_CHOICE_ANSWER:
                if (!nextQuestion.isVisible()) {
                    answerSelected((MultipleChoiceAnswer) e.getSource());
                }
                break;
            case ClickableObject.NEXT_QUESTION:
                setNextQuestion();
                break;
            case ClickableObject.BACK_BUTTON:
                UIManager.switchView(new AvailableAssignments(null));
                break;
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
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.BACK_BUTTON:
                nextQuestion.setBackground(Button.BUTTON_COLOR_PRESSED);
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch (((ClickableObject) e.getSource()).getID()) {
            case ClickableObject.NEXT_QUESTION:
            case ClickableObject.BACK_BUTTON:
                nextQuestion.setBackground(Button.BUTTON_COLOR_IDLE);
                break;
        }
    }

    private void answerSelected(MultipleChoiceAnswer selectedAnswer) {
        if (questions.get(currentQuestion).answer.equals(selectedAnswer.getText())) {
            selectedAnswer.setBackground(Color.green);
            correctAnswers++;
        } else {
            selectedAnswer.setBackground(Color.red);
            for (MultipleChoiceAnswer answer: answerLabels) {
                if (questions.get(currentQuestion).answer.equals(answer.getText())) {
                    answer.setBackground(Color.green);
                }
            }
        }

        nextQuestion.setVisible(true);
    }

    private void setNextQuestion() {
        currentQuestion++;
        String progressString = "";
        if (currentQuestion < questions.size()) {
            question.setText(questions.get(currentQuestion).question);

            List<String> answers = new ArrayList<>(Arrays.asList(questions.get(currentQuestion).multipleChoices));
            answers.add(questions.get(currentQuestion).answer);
            Collections.shuffle(answers);
            for (int i = 0; i < answers.size(); i++) {
                answerLabels[i].setText(answers.get(i));
                answerLabels[i].setBackground(Color.white);
            }

            progressString += "Question " + (currentQuestion + 1 + " of " + questions.size());
            nextQuestion.setVisible(false);
        } else {
            removeAll();
            revalidate();
            repaint();
            add(question);
            add(progress);
            add(nextQuestion);
            question.setText("Assignment Complete!");
            nextQuestion.id = ClickableObject.BACK_BUTTON;
            nextQuestion.setText("DONE");
        }
        float mark;
        if (currentQuestion > 0) {
            mark = Math.round((float) correctAnswers / currentQuestion * 100);
            if (progressString.equals("")) {
                progressString += "Final Score: " + correctAnswers + "/" + currentQuestion +
                        " (" + Math.round((float) correctAnswers / currentQuestion * 100) + "%)";
                saveMark(mark);
            } else {
                progressString += " - Current Score: " + correctAnswers + "/" + currentQuestion +
                        " (" + Math.round((float) correctAnswers / currentQuestion * 100) + "%)";
            }
        }
        progress.setText(progressString + "     ");
    }

    private void saveMark(float mark) {
        // mock user data
        User currentUser = DatabaseManager.getUser("student", "test");
        DatabaseManager.updateAssignmentMark(assignment.id, currentUser.id, mark);
    }

    private void resize() {
        progress.setBounds(0, 0, getPreferredSize().width, 50);
        question.setBounds(0, 50, getPreferredSize().width, 50);
        nextQuestion.setBounds((getPreferredSize().width - Button.WIDTH) / 2,
                getPreferredSize().height - Button.HEIGHT - 50,
                Button.WIDTH, Button.HEIGHT);
        for (int i = 0; i < answerLabels.length; i++) {
            answerLabels[i].setBounds(getPreferredSize().width / 2 - 150 - 175 * (i % 2 == 0 ? 1 : -1),
                    getPreferredSize().height / 4 + 180 * (i < 2 ? 0 : 1),
                    300,120);
        }
    }
}
