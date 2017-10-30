package backend;

import holders.Assignment;
import holders.Question;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DatabaseManager {
    public static Connection conn;

    public static void connectDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/C01ProjectDB", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addQuestion(Question question) {
        // SQL Query
        try {
            String sql = "INSERT INTO question(question) VALUES(?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, question.question);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If true we know the question is a multiple choice question
        if (question.multipleChoices != null) {

        }
    }

    public static Question getQuestion(int questionID) {

        // Dummy return value
        return new Question("", "", "", new String[]{""});
    }

    public static Question[] getAllQuestions(int courseID) {

        // Dummy return value
        return new Question[]{new Question("", "", "", new String[]{""})};
    }

    public static void addAssignment(Assignment assignment) {

    }

    public static Assignment getAssignment(int assignmentID) {

        return new Assignment("Assignment", 0, new int[]{});
    }

}
