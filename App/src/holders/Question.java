package holders;

public class Question {

    public String question;
    public String answer;
    public String tag;
    public int id;
    public String[] multipleChoices;


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
