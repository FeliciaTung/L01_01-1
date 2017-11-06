package backend;

import holders.Assignment;
import holders.Question;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DatabaseManager {
    public static Connection conn;
    private static String sql;
    private static PreparedStatement pstmt;
    private static ResultSet rs;
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
            sql = "INSERT INTO question(question, answer) VALUES(?, ?)";
            pstmt = conn.prepareStatement(sql);
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
            sql = "SELECT question, answer, qtype, qid FROM question WHERE qid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, questionID);
            rs = pstmt.executeQuery();
            String question = null, answer = null;
            int qtype = 0, qid = 0;
            while (rs.next()) {
            	question = rs.getString(1);
            	answer = rs.getString(2);
            	qtype = rs.getInt(3);
            	qid = rs.getInt(4);
            }
            String[] mc_choices = null;
            // If multiple choice, need mc_choices.
            if (qtype == 1) {
            	String sql_mc = "SELECT choice FROM mc WHERE qid=?";
                PreparedStatement pstmt_mc = conn.prepareStatement(sql_mc);
                pstmt_mc.setInt(1, questionID);
                ResultSet rs_mc = pstmt_mc.executeQuery();
                String string_mc = null;
                while (rs_mc.next()) {
                	string_mc = rs_mc.getString(1);
                }
                mc_choices = string_mc.split(",");
            }
            
            Question res_question = new Question(qid, question, answer, null, mc_choices);
            return res_question;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Question> getAllQuestions(int courseID) {
        try {
            sql = "SELECT question, answer, qtype, qid FROM question WHERE course=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, courseID);
            rs = pstmt.executeQuery();
            String question = null, answer = null;
            int qtype = 0, qid = 0;
            ArrayList<Question> question_list = new ArrayList<Question>();
            String[] mc_choices = null;

            while (rs.next()) {
            	question = rs.getString(1);
            	answer = rs.getString(2);
            	qtype = rs.getInt(3);
            	qid = rs.getInt(4);
                // Again check for multiple choice.
                if (qtype == 1) {
                	String sql_mc = "SELECT choice FROM mc WHERE qid=?";
                    PreparedStatement pstmt_mc = conn.prepareStatement(sql_mc);
                    pstmt_mc.setInt(1, qid);
                    ResultSet rs_mc = pstmt_mc.executeQuery();
                    String string_mc = null;
                    while (rs_mc.next()) {
                    	string_mc = rs_mc.getString(1);
                    }
                    mc_choices = string_mc.split(",");
                }
                question_list.add(new Question(qid, question, answer, null, mc_choices));
            }
            return question_list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addAssignment(Assignment assignment) {
        int aid = -1;

        try {
            // add assignment to table
            //TODO: add courseID
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
        String aname = "";
        ArrayList<Integer> qids  = new ArrayList<>();
        int aid = -1;
        Assignment assignment = null;
        try {
            // get assignment name and id
            sql = "SELECT aid, aname FROM assignment WHERE aid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, assignmentID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                aid = rs.getInt(1);
                aname = rs.getString(2);
            }
            // get question list for this assignment
            sql = "SELECT qid FROM assignment WHERE aid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, assignmentID);
            rs = pstmt.executeQuery();
            while(rs.next()){
                qids.add(rs.getInt(1));
            }

            assignment = new Assignment(aid,aname, -1, qids);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return assignment;
    }

}
