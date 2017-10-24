package backend;

import holders.Question;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DatabaseManager {
	
    public static void addQuestion(Question question) {
    	try {
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/C01ProjectDB");
        	// SQL Query
        	String sql = "INSERT INTO question(question) VALUES(?)";
        	PreparedStatement pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1, question.question);
        	pstmt.executeUpdate();
    	} catch(SQLException e) {
    		System.out.println(e.getMessage());
    	}

        // If true we know the question is a multiple choice question
        if (question.multipleChoices != null) {

        }
    }
}
