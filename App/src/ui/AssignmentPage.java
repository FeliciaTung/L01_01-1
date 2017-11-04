package ui;

import holders.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AssignmentPage extends JPanel implements MouseListener {

    private Question[] questionsLabel;
    private int currentQuestion;
    private int correctAnswers;

    private JLabel progress;
    private JLabel question;
    private Button nextQuestion;
    private MultipleChoiceAnswer[] answerLabels;

    public AssignmentPage() {
        super();
        questionsLabel = new Question[]{new Question("What's 2 + 2?", "4", "", new String[]{"1", "5", "42"}),
                new Question("What's 4 * 2?", "8", "", new String[]{"16", "6", "24"}),
                new Question("What's 30 / 5?", "6", "", new String[]{"4", "5", "25"})};
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
        setPreferredSize(new Dimension(800, 600));
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

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch (((ClickableObject) e.getSource()).getID()) {

        }
    }

    private void answerSelected(MultipleChoiceAnswer selectedAnswer) {
        if (questionsLabel[currentQuestion].answer.equals(selectedAnswer.getText())) {
            selectedAnswer.setBackground(Color.green);
            correctAnswers++;
        } else {
            selectedAnswer.setBackground(Color.red);
            for (MultipleChoiceAnswer answer: answerLabels) {
                if (questionsLabel[currentQuestion].answer.equals(answer.getText())) {
                    answer.setBackground(Color.green);
                }
            }
        }

        nextQuestion.setVisible(true);
    }

    private void setNextQuestion() {
        currentQuestion++;
        String progressString = "";
        if (currentQuestion < questionsLabel.length) {
            question.setText(questionsLabel[currentQuestion].question);

            List<String> answers = new ArrayList<>(Arrays.asList(questionsLabel[currentQuestion].multipleChoices));
            answers.add(questionsLabel[currentQuestion].answer);
            Collections.shuffle(answers);
            for (int i = 0; i < answers.size(); i++) {
                answerLabels[i].setText(answers.get(i));
                answerLabels[i].setBackground(Color.white);
            }

            progressString += "Question " + (currentQuestion + 1 + " of " + questionsLabel.length);
        } else {
            nextQuestion.setText("DONE");
            question.setVisible(false);
            for (MultipleChoiceAnswer answerLabel: answerLabels) {
                answerLabel.setVisible(false);
            }
        }

        if (currentQuestion > 0) {
            if (progressString.equals("")) {
                progressString += "Final Score: " + correctAnswers + "/" + currentQuestion +
                        " (" + ((int) ((float) correctAnswers / currentQuestion * 100)) + "%)";
            } else {
                progressString += " - Current Score: " + correctAnswers + "/" + currentQuestion +
                        " (" + ((int) ((float) correctAnswers / currentQuestion * 100)) + "%)";
            }
        }

        progress.setText(progressString + "     ");

        nextQuestion.setVisible(false);
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
