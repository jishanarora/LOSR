package comp3350.losr.objects;

import java.util.ArrayList;

public class User {

    public enum user_gender {Male, Female, Losr}

    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private ArrayList<Boolean> answers;
    private Profile userProfile;

    public User(String userFirstName, String userLastName, String userEmail, String userPassword, ArrayList<Boolean> answers) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.answers = answers;
        userProfile = new Profile();
    }

    //set methods
    public void setUserProfile(String bio, user_gender gender, user_gender preference, int year, int month, int day){
        userProfile.setBio(bio);
        userProfile.setGender(gender);
        userProfile.setGenderPreference(preference);
        userProfile.setDateOfBirth(year, month, day);
    }

    //update a specific answer
    public void updateAnswerList(Boolean answer, int spot) {
        answers.add(spot, answer);
    }

    public void updateAllAnswers(Boolean a1, Boolean a2, Boolean a3, Boolean a4, Boolean a5)
    {
        answers.clear();
        answers.add(a1);
        answers.add(a2);
        answers.add(a3);
        answers.add(a4);
        answers.add(a5);
    }

    public void updateDateOfBirth(int year, int month, int day) {
        userProfile.setDateOfBirth(year, month, day);
    }

    public void updateBio(String bio) { userProfile.setBio(bio); }

    public void updatePreference(user_gender pref){
        userProfile.setGenderPreference(pref);
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

    public String getUserPassword() {return userPassword;}

    public ArrayList<Boolean> getAnswers() {return answers;}

    public Profile getUserProfile() {return userProfile;}

    public boolean equals(Object object) {
        boolean result;
        User u;

        result = false;

        if (object instanceof User) {
            u = (User) object;
            if (u.userFirstName.equals(userFirstName) && u.userLastName.equals(userLastName) &&
                    u.userEmail.equals(userEmail) && u.answers.equals(answers) && u.userProfile.equals(userProfile)) {
                result = true;
            }
        }
        return result;
    }

}
