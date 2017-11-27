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
import java.util.*;
import java.util.List;

/***
 * Page to take an assignment. Also displays correct and wrong answers
 * by using colors.
 */
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
    Random rand = new Random();

    /***
     * Creates the page and displays the first question. Goes through to show
     * other questions too on clicks.
     * 
     * @param assignment the assignment object to get information from
     */
    public AssignmentPage(Assignment assignment) {
        super();
        this.assignment = assignment;
        questions = assignment.getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            // randomize question if the question was selected to be random (based off of input from AddQuestionPage)
            if (questions.get(i).question.equals("Simple Math")) {
                simpleMath(questions.get(i));
            } else if (questions.get(i).question.equals("Intermediate Statistics Question")) {
                statsOne(questions.get(i));
            } else if (questions.get(i).question.equals("Expert Statistics Question")){
                statsTwo(questions.get(i));
            }
        }

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

    private void simpleMath(Question q) {
        int num1 = rand.nextInt(50) + 1;
        int num2 = rand.nextInt(50) + 1;
        int sum = num1 + num2;
        String[] answerChoices = new String[3];

        String question = String.format("What is %d + %d?", num1, num2);
        q.question = question;
        String correctAnswer = Integer.toString(sum);
        q.answer = correctAnswer;

        String op1, op2, op3;
        op1 = Integer.toString(sum+1);
        op2 = Integer.toString(sum-3);
        op3 = Integer.toString(sum+2);

        answerChoices[0] = op1;
        answerChoices[1] = op2;
        answerChoices[2] = op3;

        if (q.multipleChoices != null) {
            q.multipleChoices = answerChoices;
        }
    }

    private void statsOne(Question q) {
        double num1 = rand.nextInt(15) + 1;
        double num2 = rand.nextInt(15) + 1;
        double num3 = rand.nextInt(15) + 1;
        double sum = num1 + num2 + num3;
        double ans = round(num3/sum, 2);
        String[] answerChoices = new String[3];

        String question = String.format("A jar contains %d red marbles, %d green marbles and %d white marbles. " +
                "If a marble is drawn from the jar at random, " +
                "what is the probability that this marble is white? Please round to 2 decimal places",
                (int)num1, (int)num2, (int)num3);
        q.question = question;
        String correctAnswer = Double.toString(ans);
        q.answer = correctAnswer;

        String op1, op2, op3;
        op1 = Double.toString(round(num1/sum,2));
        op2 = Double.toString(round(num2/sum,2));
        op3 = Double.toString(round(ans-0.05,2));

        answerChoices[0] = op1;
        answerChoices[1] = op2;
        answerChoices[2] = op3;

        if (q.multipleChoices != null) {
            q.multipleChoices = answerChoices;
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private void statsTwo(Question q) {
        int num1 = rand.nextInt(50) + 1;
        int num2 = rand.nextInt(50) + 1;
        int num3 = rand.nextInt(50) + 1;
        int num4 = rand.nextInt(50) + 1;
        int num5 = rand.nextInt(50) + 1;
        int num6 = rand.nextInt(50) + 1;
        int num7 = rand.nextInt(50) + 1;
        int num8 = rand.nextInt(50) + 1;
        int num9 = rand.nextInt(50) + 1;
        int num10 = rand.nextInt(50) + 1;
        int sum = num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9 + num10;
        int mean = sum/10;
        String[] answerChoices = new String[3];

        String question = String.format("Find x and y such that the data set S has " +
                "a mean of %d. S = {%d, %d, %d, %d, %d, x, %d, %d, %d, y}. " +
                        "Answer should be in the form: x = ?, y = ?", mean, num1, num2, num3, num4, num5, num7,
                num8, num9);
        q.question = question;
        String correctAnswer = "x = " + Integer.toString(num6) + ", y = " + Integer.toString(num10);
        q.answer = correctAnswer;

        String op1, op2, op3;
        op1 = "x = " + Integer.toString(num6-3) + ", y = " + Integer.toString(num10+7);
        op2 = "x = " + Integer.toString(num6+6) + ", y = " + Integer.toString(num10+1);
        op3 = "x = " + Integer.toString(num6-4) + ", y = " + Integer.toString(num10+3);

        answerChoices[0] = op1;
        answerChoices[1] = op2;
        answerChoices[2] = op3;

        if (q.multipleChoices != null) {
            q.multipleChoices = answerChoices;
        }
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

    /***
     * Highlights the background depending on the answer selected, to show
     * whether it was correct or not.
     * 
     * @param selectedAnswer the answer selected by the user
     */
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

    /***
     * Sets the next question and displays it after the one previously was completed.
     */
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
            question.setText("<html>" + questions.get(currentQuestion).question + "</html>");
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

    /***
     * Shows the possible answers for multiple choice questions.
     */
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

    /***
     * Shows the short answer question, making it visible.
     */
    private void showShortAnswerQuestion() {
        shortAnswerInput.setText("");
        add(shortAnswerInput);
        nextQuestion.setVisible(true);
    }

    /***
     * Displays the finished page after all the information has been determined.
     */
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

    /***
     * Saves the mark to the database.
     * 
     * @param mark the mark to be saved
     */
    private void saveMark(float mark) {
        DatabaseManager.updateAssignmentMark(assignment.id, mark);
    }

    /***
     * Resizes the UI bounds to fit the displayed elements.
     */
    private void resize() {
        progress.setBounds(0, 0, getPreferredSize().width, 50);
        question.setBounds(0, 50, getPreferredSize().width, 125);
        nextQuestion.setBounds((getPreferredSize().width - Button.WIDTH) / 2,
                getPreferredSize().height - Button.HEIGHT - 50,
                Button.WIDTH, Button.HEIGHT);
        for (int i = 0; i < answerLabels.length; i++) {
            answerLabels[i].setBounds(getPreferredSize().width / 2 - 150 - 175 * (i % 2 == 0 ? 1 : -1),
                    getPreferredSize().height / 3 + 180 * (i < 2 ? 0 : 1),
                    300,120);
        }

        shortAnswerInput.setBounds(getPreferredSize().width / 2 - 150,
                getPreferredSize().height / 2 - 60,
                300,120);
    }
}
