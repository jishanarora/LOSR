package comp3350.losr.objects;

import java.util.ArrayList;

public class User {
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private ArrayList<Boolean> answers;
    private Profile userProfile;


    public User(String userFirstName, String userLastName, String userEmail, ArrayList<Boolean> answers) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.answers = answers;
        userProfile = new Profile();
    }

    //update a specific answer
    public void updateAnswerList(Boolean answer, int spot) {
        answers.add(spot, answer);
    }

    public void updateDateOfBirth(int year, int month, int day) {
        userProfile.setDateOfBirth(year, month, day);
    }

    public void updateBio(String bio) { userProfile.setBio(bio); }

    public void updatePreference(String preference) {
        userProfile.setGenderPreference(preference);
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public ArrayList<Boolean> getAnswers() {return answers;}

    public Profile getUserProfile() {return userProfile;}

    public boolean equals(Object object) {
        boolean result;
        User u;

        result = false;

        if (object instanceof User) {
            u = (User) object;
            if (u.userFirstName.equals(userFirstName) && u.userLastName.equals(userLastName) &&
                    u.userEmail.equals(userEmail) && u.userProfile.equals(userProfile)) {
                result = true;
            }
        }
        return result;
    }

}
