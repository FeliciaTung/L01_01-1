package holders;

public class Question {

    public final String question;
    public final String answer;
    public final String tag;
    public final int id;
    public final String[] multipleChoices;
    

    /***
     * Question object constructor.
     * Used when retrieving from database.
     * 
     * @param id
     * @param question the question stored in the database
     * @param answer the correct answer in the database
     * @param tag the tag to use for reference to the question
     * @param multipleChoices the choices of this question. Empty list if short answer
     */
    public Question(int id, String question, String answer, String tag, String[] multipleChoices) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.tag = tag;
        this.multipleChoices = multipleChoices;
    }

    /***
     * Question object constructor. No id field.
     * Used for creation from UI to insert into database.
     * 
     * 
     * @param question the question asked for an answer to
     * @param answer the correct answer
     * @param tag the tag to use for reference to the question
     * @param multipleChoices the choices of this question. Empty list if short answer
     */
    public Question(String question, String answer, String tag, String[] multipleChoices) {
        this.id = -1;
        this.question = question;
        this.answer = answer;
        this.tag = tag;
        this.multipleChoices = multipleChoices;
    }
}
