package backend;

import holders.Assignment;
import holders.Question;
import holders.User;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
        int questionType = (question.multipleChoices == null)? 2: 1;
            try {
            sql = "INSERT INTO question(question, answer, course, qtype) VALUES(?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, question.question);
            pstmt.setString(2, question.answer);
            pstmt.setInt(3, 1); //dummy courseID
            pstmt.setInt(4, questionType);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If true we know the question is a multiple choice question
        if (questionType == 1) {
            try {
                String sql_get = "SELECT qid FROM question WHERE question=?";
				PreparedStatement ret_id = conn.prepareStatement(sql_get);
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

                ArrayList<String> mc_list = new ArrayList<>();

                while (rs_mc.next()) {
                	mc_list.add(rs_mc.getString(1));
                }
                mc_choices = mc_list.toArray(new String[mc_list.size()]);
            }

            Question res_question = new Question(qid, question, answer, null, mc_choices);
            return res_question;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all questions that are related to a course or a course
     *
     * @param id    id of course or course
     * @param useCourseid   true if using courseid, false if using assignemnt id
     * @return  questions
     */
    public static List<Question> getAllQuestions(int id, boolean useCourseid) {
        try {
            if (useCourseid) {
                sql = "SELECT question, answer, qtype, qid FROM question WHERE course=?";
            } else {
                sql = "SELECT question, answer, qtype, q.qid FROM question q JOIN " +
                        "related_question rq ON rq.qid=q.qid where rq.aid=?";
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            String question = null, answer = null;
            int qtype = 0, qid = 0;
            List<Question> question_list = new ArrayList<Question>();
            List<String> mc_list = new ArrayList<>();
            String[] mc_choices;

            while (rs.next()) {
            	question = rs.getString(1);
            	answer = rs.getString(2);
            	qtype = rs.getInt(3);
            	qid = rs.getInt(4);
            	mc_choices = null; // reset for each question
            	mc_list = new ArrayList<>(); // reset for each question
                // Again check for multiple choice.
                if (qtype == 1) {
                	String sql_mc = "SELECT choice FROM mc WHERE qid=?";
                    PreparedStatement pstmt_mc = conn.prepareStatement(sql_mc);
                    pstmt_mc.setInt(1, qid);
                    ResultSet rs_mc = pstmt_mc.executeQuery();
                    while (rs_mc.next()) {
                    	mc_list.add(rs_mc.getString(1));

                    }
                    mc_choices = mc_list.toArray(new String[mc_list.size()]);
                }
                question_list.add(new Question(qid, question, answer, null, mc_choices));
            }
            return question_list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addUser(User user) {
    	try {
            sql = "INSERT INTO users(uname, email, password, cid, type) VALUES(?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.name);
            pstmt.setString(2, user.email);
            // This may need to change. Should we really store raw password text?
            pstmt.setString(3, user.input_pass);
            pstmt.setInt(4, user.courseID);
            pstmt.setInt(5, user.type);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String user, String pass) {
        try {
            sql = "SELECT uid, uname, email, password, cid, type FROM users WHERE uname=? AND password=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            rs = pstmt.executeQuery();
            String uname = null, email = null, password = null;
            int cid = -1, type = -1, userid = -1 ;
            while (rs.next()) {
                userid = rs.getInt(1);
                uname = rs.getString(2);
                email = rs.getString(3);
                password = rs.getString(4);
                cid = rs.getInt(5);
                type = rs.getInt(6);
            }
            User res_user = new User(userid, uname, email, password, cid, type);
            return res_user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addAssignment(Assignment assignment) {
        int aid = -1;

        try {
            // add assignment to table
            sql = "INSERT INTO assignment(aname,cid, due_date) VALUES(?, ?, DATE(?))";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, assignment.name);
            pstmt.setInt(2, assignment.courseID);
            pstmt.setString(3, assignment.dueDate);
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
        String date = "";
        List<Integer> qids  = new ArrayList<>();
        Date dueDate;
        int aid = -1, courseID = -1;
        Assignment assignment = null;
        try {
            // get assignment info
            sql = "SELECT aid, aname, cid, due_date  FROM assignment WHERE aid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, assignmentID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                aid = rs.getInt(1);
                aname = rs.getString(2);
                courseID = rs.getInt(3);
                dueDate = rs.getDate(4);
                date = convertDateToString(dueDate);
            }
            // get question list for this assignment
            sql = "SELECT qid FROM related_question WHERE aid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, assignmentID);
            rs = pstmt.executeQuery();
            while(rs.next()){
                qids.add(rs.getInt(1));
            }

            assignment = new Assignment(aid,aname, courseID, qids, date);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return assignment;
    }

    /**
     * get all assignments that are associated with a course/user. If userID is invalid, then get assignments based on courseID
     * and no mark is given to the assignments return.
     * If userID is valid, then return Assignments with user's marks on each assignment
     * @param userID
     * @param courseID
     * @return
     */
    public static List<Assignment> getAllAssignment(int userID, int courseID) {
        String aname = null;
        String date;
        int assignid = -1, tempId;
        float mark = -1;
        Date dueDate = new Date();
        List<Integer >qid = new ArrayList<>();
        List<Assignment> assign_list = new ArrayList<>();
        try {
            if (userID < 1) {
                sql = "SELECT a.aid, aname, due_date, qid FROM assignment a LEFT OUTER JOIN related_question rq ON " +
                        "rq.aid=a.aid WHERE cid=?";

                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, courseID);
            } else {
                sql = "SELECT a.aid, aname, due_date, qid, m.mark FROM assignment a JOIN related_question rq ON " +
                        "rq.aid=a.aid LEFT JOIN marks m ON a.cid = m.cid AND a.aid=m.aid and student = ? WHERE a.cid = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, userID);
                pstmt.setInt(2, courseID);
            }
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tempId = rs.getInt(1);

                if (tempId != assignid){
                    // this is new assignment, store previous one to list if there is one
                    if (assignid != -1) {
                        date = convertDateToString(dueDate);
                        assign_list.add(new Assignment(assignid, aname, courseID, qid, date, mark));
                    }
                    // clear question list for new assignment
                    assignid = tempId;
                    qid = new ArrayList<>();
                }
                aname = rs.getString(2);
                dueDate = rs.getDate(3  );
                qid.add(rs.getInt(4));
                if (userID >= 1){
                    mark = rs.getFloat(5);
                    if (rs.wasNull()) {mark = -1;}
                }
            }
            // loop ends before storing the last assignment
            date = convertDateToString(dueDate);
            assign_list.add(new Assignment(assignid, aname, courseID, qid, date, mark));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assign_list;
    }

    public static void updateAssignmentMark(int assignId, int userId, float mark){
        try {
            if (!passedDueDate(assignId)){
                sql = "SELECT * FROM marks WHERE student =? AND aid=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, userId);
                pstmt.setInt(2, assignId);
                rs = pstmt.executeQuery();
                if (rs.next()){
                    sql = "UPDATE marks SET mark = ? WHERE student = ? AND aid =?";
                } else {
                    sql = "INSERT INTO marks(mark, student, cid, aid) VALUES (?,?,1,?)"; // course id = 1
                }
                pstmt = conn.prepareStatement(sql);
                pstmt.setFloat(1, mark);
                pstmt.setInt(3, assignId);
                pstmt.setInt(2, userId);
                pstmt.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static boolean passedDueDate(int assignId){
        Date dueDate = new Date();
        Date today = new Date();
        try{
            sql = "SELECT due_date FROM assignment where aid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, assignId);
            rs = pstmt.executeQuery();
            if (rs.next()){
                dueDate = rs.getDate(1);
            }
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            df.format(today);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return (dueDate.before(today));

    }
    private static String convertDateToString(Date date){
        String result = "";
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        result = df.format(date);
        return result;
    }
}
