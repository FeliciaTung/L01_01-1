package backend;

import holders.Assignment;
import holders.Question;
import holders.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static backend.DatabaseManager.conn;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class DatabaseManagerTest {
    private Question testQuestion1, testQuestionExtra;
    private Assignment testAssignment1, testAssignmentExtra;
    private List<Integer> questionList;
    private User testUser1;
    private String[] mc;

    @Before
    public void setUp() {
        questionList = new ArrayList<>();
        DatabaseManager.connectDB();
        // Question creation and addition
        testQuestion1 = new Question("testQ", "testA", "testTag", null);
        DatabaseManager.addQuestion(testQuestion1);
        // Assignment creation and addition
        questionList.add(1);
        testAssignment1 = new Assignment("testAssignment", 1, questionList);
        DatabaseManager.addAssignment(testAssignment1);
        // User creation and addition
        testUser1 = new User("testName", "testEmail", "testPass", 1, 1);
        DatabaseManager.addUser(testUser1);
        // Extra additions for getAll functions
        mc = new String[]{"mc choice1", "mc choice 2", "mc choice 3"};
        testQuestionExtra = new Question("ExtraQ", "ExtraA", "ExtraTag", mc);
        DatabaseManager.addQuestion(testQuestionExtra); // add question in database so it can be added to other assignments
        questionList = new ArrayList<>();
        questionList.add(1);
        questionList.add(2);
        testAssignmentExtra = new Assignment("ExtraAssignment", 1, questionList);
        // Adding these won't affect the first elements of the database (which are the first question and assignment)
        DatabaseManager.addAssignment(testAssignmentExtra);
    }

    @After
    public void tearDown() {
        String[] tables = new String[]{"question", "assignment", "related_question"};
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < tables.length; i++) {
                String query = String.format("DELETE FROM %s", tables[i]);
                st.executeUpdate(query);
                query = String.format("ALTER TABLE %s AUTO_INCREMENT = 1", tables[i]);
                st.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void testgetQuestion() {
        // First question from setup
        Question testQuestion2 = DatabaseManager.getQuestion(1);
        // Fields should all be the same
        assertEquals(testQuestion1.question, testQuestion2.question);
        assertEquals(testQuestion1.answer, testQuestion2.answer);
        assertArrayEquals(testQuestion1.multipleChoices, testQuestion2.multipleChoices);
    }

    @Test
    public void testgetAssignment() {
        // First assignment from setup
        Assignment testAssignment2 = DatabaseManager.getAssignment(1);
        // Fields should all be the same
        assertEquals(testAssignment1.name, testAssignment2.name);
        assertEquals(testAssignment1.courseID, testAssignment2.courseID);
        assertEquals(testAssignment1.questions.get(0), testAssignment2.questions.get(0));
    }

    @Test
    public void testgetUser() {
        // First user from setup
        User testUser2 = DatabaseManager.getUser(1);
        // Fields should all be the same
        assertEquals(testUser1.name, testUser2.name);
        assertEquals(testUser1.email, testUser2.email);
        assertEquals(testUser1.input_pass, testUser2.input_pass);
        assertEquals(testUser1.courseID, testUser2.courseID);
        assertEquals(testUser1.type, testUser2.type);
    }

    @Test
    public void testgetAllQuestions() {
        // First user from setup
    /* getAllQuestions() gets assignments for one course so it won't get both a1 and a2 in the same call */
        List<Question> testAllQuestions = DatabaseManager.getAllQuestions(1, true);
        Question q1 = testAllQuestions.get(0);
        Question q2 = testAllQuestions.get(1);
        // First question with q1
        assertEquals(testQuestion1.question, q1.question);
        assertEquals(testQuestion1.answer, q1.answer);
        assertArrayEquals(testQuestion1.multipleChoices, q1.multipleChoices);
        // Extra question with q2
        assertEquals(testQuestionExtra.question, q2.question);
        assertEquals(testQuestionExtra.answer, q2.answer);
        assertArrayEquals(testQuestionExtra.multipleChoices, q2.multipleChoices);

    }

    @Test
    public void testgetAllAssignment() {
        // First user from setup
        List<Assignment> testAllAssignment = DatabaseManager.getAllAssignment(1);
        Assignment a1 = testAllAssignment.get(0);
        Assignment a2 = testAllAssignment.get(1);

        // First assignment with a1
        assertEquals(testAssignment1.name, a1.name);
        assertEquals(testAssignment1.courseID, a1.courseID);
        assertEquals(testAssignment1.questions.get(0), a1.questions.get(0));


        // Second assignment with a2
        assertEquals(testAssignmentExtra.name, a2.name);
        assertEquals(testAssignmentExtra.courseID, a2.courseID);
        assertEquals(testAssignmentExtra.questions.get(0), a2.questions.get(0));
    }


}