package holders;

public class Question {

    public final String question;
    public final String answer;
    public final String tag;
    public final String[] multipleChoices;

    public Question(String question, String answer, String tag, String[] multipleChoices) {
        this.question = question;
        this.answer = answer;
        this.tag = tag;
        this.multipleChoices = multipleChoices;
    }
}
