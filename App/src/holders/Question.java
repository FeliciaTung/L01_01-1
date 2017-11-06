package holders;

public class Question {

    public final String question;
    public final String answer;
    public final String tag;
    public final int id;
    public final String[] multipleChoices;


    /*
    * Call this when adding new question to table
    *
    * */
    public Question(String question, String answer, String tag, String[] multipleChoices) {
        this.id = -1;
        this.question = question;
        this.answer = answer;
        this.tag = tag;
        this.multipleChoices = multipleChoices;
    }

    /*
    *
    * Call this when doing select question from table
    * */
    public Question(int id, String question, String answer, String tag, String[] multipleChoices) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.tag = tag;
        this.multipleChoices = multipleChoices;
    }


}
