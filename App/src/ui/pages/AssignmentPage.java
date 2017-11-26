package ui.pages;

import backend.CurrentSession;
import backend.DatabaseManager;
import holders.Assignment;
import holders.Question;
import ui.UIManager;
import ui.components.InputField;
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
    private String progressString;
    private Assignment assignment;
    private JLabel progress;
    private JLabel question;
    private Button nextQuestion;
    private MultipleChoiceAnswer[] answerLabels;
    private InputField shortAnswerInput;

    public AssignmentPage(Assignment assignment) {
        super();
        this.assignment = assignment;
        questions = assignment.getQuestions();
        currentQuestion = -1;
        correctAnswers = 0;
        progress = new JLabel("", SwingConstants.RIGHT);
        question = new JLabel("", SwingConstants.CENTER);
        nextQuestion = new Button("Next Question");
        answerLabels = new MultipleChoiceAnswer[4];
        shortAnswerInput = new InputField();
        for (int i = 0; i < answerLabels.length; i++) {
            answerLabels[i] = new MultipleChoiceAnswer();
            answerLabels[i].addMouseListener(this);
        }

        setLayout(null);
        setPreferredSize(new Dimension(800, 680));
        setBackground(new Color(240, 240, 240));

        progress.setFont(getFont().deriveFont(16f));
        question.setFont(getFont().deriveFont(24f));

        shortAnswerInput.setBackground(Color.white);

        nextQuestion.id = ClickableObject.NEXT_QUESTION;
        nextQuestion.addMouseListener(this);

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
                UIManager.switchView(new AvailableAssignments(DatabaseManager
                        .getAllAssignment(CurrentSession.user.id, CurrentSession.user.courseID)));

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
        if (shortAnswerInput.getText() != null && !shortAnswerInput.getText().equals("")) {
            if (questions.get(currentQuestion).answer.equals(shortAnswerInput.getText())) {
                correctAnswers++;
            }

            shortAnswerInput.setText("");
        }

        currentQuestion++;
        progressString = "";
        removeAll();
        if (currentQuestion < questions.size()) {
            question.setText(questions.get(currentQuestion).question);
            if (questions.get(currentQuestion).multipleChoices != null) {
                showMultipleChoiceQuestion();
            } else {
                showShortAnswerQuestion();
            }

            progressString += "Question " + (currentQuestion + 1 + " of " + questions.size());
            if (currentQuestion > 0) {
                progressString += " - Current Score: " + correctAnswers + "/" + currentQuestion +
                        " (" + Math.round((float) correctAnswers / currentQuestion * 100) + "%)";
            }

            add(progress);
            add(question);
            add(nextQuestion);
        } else {
            showFinishedPage();
        }

        progress.setText(progressString + "     ");
        revalidate();
        repaint();
    }

    private void showMultipleChoiceQuestion() {
        List<String> answers = new ArrayList<>(Arrays.asList(questions.get(currentQuestion).multipleChoices));
        answers.add(questions.get(currentQuestion).answer);
        Collections.shuffle(answers);
        for (int i = 0; i < answers.size(); i++) {
            answerLabels[i].setText(answers.get(i));
            answerLabels[i].setBackground(Color.white);
            add(answerLabels[i]);
        }

        nextQuestion.setVisible(false);
    }

    private void showShortAnswerQuestion() {
        shortAnswerInput.setText("");
        add(shortAnswerInput);
        nextQuestion.setVisible(true);
    }

    private void showFinishedPage() {
        float mark = Math.round((float) correctAnswers / currentQuestion * 100);
        saveMark(mark);
        progressString += "Final Score: " + correctAnswers + "/" + currentQuestion + " (" + mark + "%)";
        add(question);
        add(progress);
        add(nextQuestion);
        question.setText("Assignment Complete!");
        nextQuestion.id = ClickableObject.BACK_BUTTON;
        nextQuestion.setText("DONE");
        nextQuestion.setVisible(true);
    }

    private void saveMark(float mark) {
        DatabaseManager.updateAssignmentMark(assignment.id, mark);
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

        shortAnswerInput.setBounds(getPreferredSize().width / 2 - 150,
                getPreferredSize().height / 2 - 60,
                300,120);
    }
}
