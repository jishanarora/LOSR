package comp3350.losr.objects;

import java.util.ArrayList;

public class User
{
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private ArrayList<Boolean> answers;


    public User(String userFirstName, String userLastName, String userEmail, ArrayList<Boolean> answers) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.answers = answers;
    }

    //update a specific answer
    public void updateAnswerList(Boolean answer, int spot){
        answers.add(spot, answer);
    }
}
