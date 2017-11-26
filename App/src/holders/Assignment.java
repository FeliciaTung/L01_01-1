package holders;

import backend.DatabaseManager;

import java.util.List;

public class Assignment {

    public String name;
    public int courseID;
    public int id;
    public List<Integer> questions;
    public float mark;
    public String dueDate;

    public Assignment(int id, String name, int courseID, List<Integer> questions, String date, float mark) {
        this.id = id;
        this.name = name;
        this.courseID = courseID;
        this.questions = questions;
        this.dueDate = date;
        this.mark = mark;
    }

    public Assignment(int id, String name, int courseID, List<Integer> questions, String date) {
        this(id, name, courseID, questions, date, -1);
    }

    public Assignment(String name, int courseID, List<Integer> questions, String date) {
        this(-1, name, courseID, questions, date);
    }

    public List<Question> getQuestions(){
        List<Question> questionList = DatabaseManager.getAllQuestions(id);
        return questionList;
    }
}
