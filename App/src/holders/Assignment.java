package holders;

import backend.DatabaseManager;

import java.util.List;

public class Assignment {

    public String name;
    public int courseID;
    public int id;
    public List<Integer> questions;
    public float mark;

    public Assignment(int id, String name, int courseID, List<Integer> questions, float mark) {
        this.id = id;
        this.name = name;
        this.courseID = courseID;
        this.questions = questions;
        this.mark = mark;
    }

    public Assignment(int id, String name, int courseID, List<Integer> questions) {
        this(id, name, courseID, questions, -1);
    }

    public Assignment(String name, int courseID, List<Integer> questions) {
        this(-1, name, courseID, questions);
    }

    public List<Question> getQuestions(){
        List<Question> questionList = DatabaseManager.getAllQuestions(id, false);
        return questionList;
    }
}
