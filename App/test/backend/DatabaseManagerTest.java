package backend;

import holders.Assignment;
import holders.Question;
import holders.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;


public class DatabaseManagerTest {
	
	@Before
	public void testPrepare() {
    	// Assumption of empty database
    	DatabaseManager.connectDB();
    	// Question creation and addition
    	Question testQuestion1 = new Question("testQ", "testA", "testTag", null);
    	DatabaseManager.addQuestion(testQuestion1);
    	// Assignment creation and addition
    	Assignment testAssignment1 = new Assignment("testAssignment", 111, [1]);  
    	DatabaseManager.addAssignment(testAssignment1);
    	// User creation and addition
    	User testUser1 = new User("testName", "testEmail", "testPass", 111, 1);
    	DatabaseManager.addUser(testUser1);
    	// Extra additions for getAll functions
    	Question testQuestionExtra = new Question("ExtraQ", "ExtraA", "ExtraTag", ["mc choice1", "mc choice 2", "mc choice 3"]);
    	Assignment testAssignmentExtra = new Assignment("ExtraAssignment", 123, [1,2]);
    	// Adding these won't affect the first elements of the database (which are the first question and assignment)
    	DatabaseManager.addQuestion(testQuestionExtra);
    	DatabaseManager.addAssignment(testAssignmentExtra);
	}

    @Test
    public void testgetQuestion() {
    	// First question from setup
    	Question testQuestion2 = DatabaseManager.getQuestion(1);
    	// Fields should all be the same
        assertEquals(testQuestion1.question, testQuestion2.question);
        assertEquals(testQuestion1.answer, testQuestion2.answer);
        assertEquals(testQuestion1.tag, testQuestion2.tag);
        assertEquals(testQuestion1.multipleChoices, testQuestion2.multipleChoices);
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
    	List<Question> testAllQuestions = DatabaseManager.getAllQuestions();
    	Question q1 = testAllQuestions.get(0);
    	Question q2 = testAllQuestions.get(1);
    	// First question with q1
        assertEquals(testQuestion1.question, q1.question);
        assertEquals(testQuestion1.answer, q1.answer);
        assertEquals(testQuestion1.tag, q1.tag);
        assertEquals(testQuestion1.multipleChoices, q1.multipleChoices);
        // Extra question with q2
        assertEquals(testQuestionExtra.question, q2.question);
        assertEquals(testQuestionExtra.answer, q2.answer);
        assertEquals(testQuestionExtra.tag, q2.tag);
        assertEquals(testQuestionExtra.multipleChoices, q2.multipleChoices);
        
    }
    
    @Test
    public void testgetAllAssignment() {
    	// First user from setup
    	List<Assignment> testAllAssignment = DatabaseManager.getAllAssignment();
    	Assignment a1 = testAllAssignment.get(0);
    	Assignment a2 = testAllAssignment.get(1);
    	// First assignment with a1
        assertEquals(testAssignment1.name, a1.name);
        assertEquals(testAssignment1.courseID, a1.courseID);
        assertEquals(testAssignment1.questions.get(0), a1.questions.get(0));
        assertEquals(testAssignment1.questions.get(1), a1.questions.get(1));
        // Extra assignment with a2
        assertEquals(testAssignmentExtra.name, a2.name);
        assertEquals(testAssignmentExtra.courseID, a2.courseID);
        assertEquals(testAssignmentExtra.questions.get(0), a2.questions.get(0));
        assertEquals(testAssignmentExtra.questions.get(1), a2.questions.get(1));
    }
    
    
}