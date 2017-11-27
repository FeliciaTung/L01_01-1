package holders;

import backend.DatabaseManager;

import java.util.List;

/**
 * Representation of an Assignment object.
 * Assignment will contain a list of questions to be answered.
 */
public class Assignment {

    public String name;
    public int courseID;
    public int id;
    public List<Integer> questions;
    public float mark;
    public String dueDate;

    /**
     * Assignment object constructor.
     * This contains all possible fields an assignment may have.
     * Used when retrieving from database.
     *
     * @param id        id of the assignment from database
     * @param name      the given assignment name
     * @param courseID  the id of the course the assignment associated with
     * @param questions the list of questions in the assignment
     * @param date      the due for the assignment
     * @param mark      the mark (if a student) for this assignment
     */
    public Assignment(int id, String name, int courseID, List<Integer> questions, String date, float mark) {
        this.id = id;
        this.name = name;
        this.courseID = courseID;
        this.questions = questions;
        this.dueDate = date;
        this.mark = mark;
    }

    /**
     * Assignment object constructor. No mark yet given, so no mark field.
     *
     * @param id        id of the assignment from database
     * @param name      the given assignment name
     * @param courseID  the id of the course the assignment associated with
     * @param questions the list of questions in the assignment
     * @param date      the due for the assignment
     */
    public Assignment(int id, String name, int courseID, List<Integer> questions, String date) {
        this(id, name, courseID, questions, date, -1);
    }

    /**
     * Assignment object constructor. No mark or id.
     * Used for creation from UI to insert into database.
     *
     * @param name      the given assignment name
     * @param courseID  the id of the course the assignment associated with
     * @param questions the list of questions in the assignment
     * @param date      the due for the assignment
     */
    public Assignment(String name, int courseID, List<Integer> questions, String date) {
        this(-1, name, courseID, questions, date);
    }

    /**
     * Function to retrieve all the questions in the assignment.
     *
     * @return the list of all questions in this assignment object
     */
    public List<Question> getQuestions() {
        List<Question> questionList = DatabaseManager.getAllQuestions(id);
        return questionList;
    }
}
