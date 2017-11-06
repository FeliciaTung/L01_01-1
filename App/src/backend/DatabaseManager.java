package backend;

import holders.Assignment;
import holders.Question;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            String sql = "INSERT INTO question(question, answer) VALUES(?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, question.question);
            pstmt.setString(2, question.answer);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If true we know the question is a multiple choice question
        if (question.multipleChoices != null) {
            try {
                String sql_get = "SELECT qid FROM question WHERE question=?";
				PreparedStatement ret_id = conn.prepareStatement(sql_get);
				ret_id = conn.prepareStatement(sql_get);
				ret_id.setString(1, question.question);
				// Result set for desired qid
		        ResultSet rs = ret_id.executeQuery();
            	
		        
		        Integer qid = null;
		        if (rs.next()) {
		            qid = rs.getInt(1);
		        }
		        
                String sql = "INSERT INTO mc(qid, choice) VALUES(?, ?)";
                PreparedStatement add_mc = conn.prepareStatement(sql);
                add_mc.setInt(1, qid);
                for (int i = 0; i < (question.multipleChoices).length; i++) {
                    add_mc.setString(2, question.multipleChoices[i]);
                    add_mc.executeUpdate();

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static Question getQuestion(int questionID) {


        try {
            String sql = "SELECT question, answer, qtype FROM question WHERE questionID=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, questionID);
            ResultSet rs = pstmt.executeQuery();
            String question = null, answer = null;
            int qtype = 0;
            while (rs.next()) {
            	question = rs.getString(1);
            	answer = rs.getString(2);
            	qtype = rs.getInt(3);
            }
            String[] mc_choices = null;
            // If multiple choice, need mc_choices.
            if (qtype == 1) {
            	String sql_mc = "SELECT choice FROM mc WHERE questionID=?";
                PreparedStatement pstmt_mc = conn.prepareStatement(sql_mc);
                pstmt_mc.setInt(1, questionID);
                ResultSet rs_mc = pstmt_mc.executeQuery();
                String string_mc = null;
                while (rs_mc.next()) {
                	string_mc = rs_mc.getString(1);
                }
                mc_choices = string_mc.split(",");
            }
            
            Question res_question = new Question(question, answer, null, mc_choices);
            return res_question;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Question[] getAllQuestions(int courseID) {

        // Dummy return value
        return new Question[]{new Question(0, "", "", "", new String[]{""})};
    }

    public static void addAssignment(Assignment assignment) {
        // SQL Query
        String sql;
        PreparedStatement pstmt;
        int aid = -1;
        try {
            // add assignment to table
            sql = "INSERT INTO assignment(aname) VALUES(?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, assignment.name);
            pstmt.executeUpdate();

            // get the ID of the new assignment
            sql = "SELECT aid FROM assignment ORDER BY aid DESC LIMIT 1";
            pstmt  = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.first()) {
                aid = rs.getInt(1);
            }

            // add related questions to the assignment
            for (int qid : assignment.questions) {

                sql = "INSERT INTO related_question(aid,qid) VALUES(?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, aid);
                pstmt.setInt(2, qid);
                pstmt.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Assignment getAssignment(int assignmentID) {

        return new Assignment("Assignment", 0, new int[]{});
    }

}
