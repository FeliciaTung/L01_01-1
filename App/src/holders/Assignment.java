package holders;

import java.util.ArrayList;

public class Assignment {

    public String name;
    public int courseID;
    public int id;
    public ArrayList<Integer> questions;

    public Assignment(String name, int courseID, ArrayList<Integer> questions) {
        this.id = -1; // assignment does not exist in database
        this.name = name;
        this.courseID = courseID;
        this.questions = questions;
    }

    public Assignment(int id, String name, int courseID, ArrayList<Integer> questions) {
        this.id = id;
        this.name = name;
        this.courseID = courseID;
        this.questions = questions;
    }
}
