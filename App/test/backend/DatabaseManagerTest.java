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
    	DatabaseManager.addQuestion(testQuestion);
    	// Assignment creation and addition
    	testAssignment1 = new Assignment("testAssignment", 111, [1]);    	
	}

    @Test
    public void testgetQuestion() {
    	// Only 1 question
    	testQuestion2 = DatabaseManager.getQuestion(1);
    	// Fields should all be the same
        assertEquals(testQuestion1.question, testQuestion2.question);
        assertEquals(testQuestion1.answer, testQuestion2.answer);
        assertEquals(testQuestion1.tag, testQuestion2.tag);
        assertEquals(testQuestion1.multipleChoices, testQuestion2.multipleChoices);
    }
    
    @Test
    public void testgetAssignment() {
    	// Should only be 1 assignment
    	testAssignment2 = DatabaseManager.getAssignment(1);
    	// Fields should all be the same
        assertEquals(testAssignment1.name, testAssignment2.name);
        assertEquals(testAssignment1.courseID, testAssignment2.courseID);
        assertEquals(testAssignment1.questions.get(0), testAssignment2.questions.get(0));
    }
}