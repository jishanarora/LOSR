package comp3350.losr.objects;

//simple object to hold question information and their weights.
public class Question {
    private int qNum;
    private String question;
    private Boolean answer;
    private int weight;

    public Question(int qNum, String question, Boolean answer, int weight) {
        this.qNum = qNum;
        this.question = question;
        this.answer = answer;
        this.weight = weight;

    }

    // Get Methods
    public int getqNum() {
        return qNum;
    }

    public String getQuestion() {
        return question;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public int getWeight() {
        return weight;
    }


    // Set methods
    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}