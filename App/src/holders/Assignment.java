package holders;

import backend.DatabaseManager;

import java.util.List;

public class Assignment {

    public String name;
    public int courseID;
    public int id;
    public List<Integer> questions;

    public Assignment(String name, int courseID, List<Integer> questions) {
        this.id = -1; // assignment does not exist in database
        this.name = name;
        this.courseID = courseID;
        this.questions = questions;
    }

    public Assignment(int id, String name, int courseID, List<Integer> questions) {
        this.id = id;
        this.name = name;
        this.courseID = courseID;
        this.questions = questions;
    }

    public List<Question> getQuestions(){
        List<Question> questionList = DatabaseManager.getAllQuestions(id, false);
        return questionList;
    }
}
