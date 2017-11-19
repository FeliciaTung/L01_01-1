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
    	testQuestion1 = new Question("testQ", "testA", "testTag", null);
    	DatabaseManager.addQuestion(testQuestion1);
    	// Assignment creation and addition
    	testAssignment1 = new Assignment("testAssignment", 111, [1]);  
    	DatabaseManager.addAssignment(testAssignment1);
    	// User creation and addition
    	testUser1 = new User("testName", "testEmail", "testPass", 111, 1);
    	DatabaseManager.addUser(testUser1);
	}

    @Test
    public void testgetQuestion() {
    	// Only 1 question from setup
    	testQuestion2 = DatabaseManager.getQuestion(1);
    	// Fields should all be the same
        assertEquals(testQuestion1.question, testQuestion2.question);
        assertEquals(testQuestion1.answer, testQuestion2.answer);
        assertEquals(testQuestion1.tag, testQuestion2.tag);
        assertEquals(testQuestion1.multipleChoices, testQuestion2.multipleChoices);
    }
    
    @Test
    public void testgetAssignment() {
    	// Should only be 1 assignment from setup
    	testAssignment2 = DatabaseManager.getAssignment(1);
    	// Fields should all be the same
        assertEquals(testAssignment1.name, testAssignment2.name);
        assertEquals(testAssignment1.courseID, testAssignment2.courseID);
        assertEquals(testAssignment1.questions.get(0), testAssignment2.questions.get(0));
    }
    
    @Test
    public void testgetUser() {
    	// Should only be 1 user from setup
    	testUser2 = DatabaseManager.getUser(1);
    	// Fields should all be the same
        assertEquals(testUser1.name, testUser2.name);
        assertEquals(testUser1.email, testUser2.email);
        assertEquals(testUser1.input_pass, testUser2.input_pass);
        assertEquals(testUser1.courseID, testUser2.courseID);
        assertEquals(testUser1.type, testUser2.type);
    }
}