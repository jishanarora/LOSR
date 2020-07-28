package comp3350.losr.objects;

//simple object to hold question information and their weights.
public class Question {
    private int qNum;
    private String question;
    private Boolean answer;
    private int weight;

    //just for testing
    public Question(Boolean answer)
    {
        qNum = 0;
        question = "test";
        this.answer = answer;
        weight = 2;
    }

    public Question(int qNum, String question, Boolean answer, int weight)
    {
        this.qNum = qNum;
        this.question = question;
        this.answer = answer;
        this.weight = weight;
    }

    public boolean equals(Question q)
    {
        boolean equal = false;

        if(q.getqNum() == this.qNum && q.getQuestion().equals(this.question) && q.getAnswer().equals(this.answer) && q.getWeight() == this.weight)
        {
            equal = true;
        }

        return equal;
    }

    public String toString()
    {
        return answer.toString();
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