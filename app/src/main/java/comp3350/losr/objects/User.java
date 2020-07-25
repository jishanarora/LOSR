package comp3350.losr.objects;

import java.util.ArrayList;

public class User
{

    public enum user_gender {Male, Female, Losr}

    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private Profile userProfile;

    public User(String userFirstName, String userLastName, String userEmail, String userPassword)
    {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        userProfile = new Profile();
    }

    public String toString()
    {
        String message;

        message = "email: "+userEmail+" password: "+userPassword+" first name: "+userFirstName+" last name: "+userLastName+" ";
        message += this.userProfile.toString();

        return message;
    }

    public boolean equals(Object object)
    {
        boolean result;
        User u;

        result = false;

        if (object instanceof User)
        {
            u = (User) object;
            if (u.userFirstName.equals(userFirstName) && u.userLastName.equals(userLastName) &&
                    u.userEmail.equals(userEmail) && u.userProfile.equals(userProfile))
            {
                result = true;
            }
        }
        return result;
    }




    public String getUserFirstName() { return userFirstName; }

    public String getUserLastName() { return userLastName; }

    public String getUserEmail() { return userEmail; }

    public String getUserPassword() {return userPassword;}

    public Profile getUserProfile() {return userProfile;}

    public ArrayList<Boolean> getAnswers() {return userProfile.getAnswers();}



    public void setUserFirstName(String name) { userFirstName = name; }

    public void setUserLastName(String name) { userLastName = name; }

    public void setUserEmail(String email) {userEmail = email; }

    public void setUserPassword(String password) {userPassword = password; }

    public void setUserProfile(String bio, user_gender gender, user_gender preference, int year, int month, int day)
    {
        userProfile.setBio(bio);
        userProfile.setGender(gender);
        userProfile.setGenderPreference(preference);
        userProfile.setDateOfBirth(year, month, day);
    }

    public void updateDateOfBirth(int year, int month, int day) { userProfile.setDateOfBirth(year, month, day); }

    public void updateBio(String bio) { userProfile.setBio(bio); }

    public void updateGender(user_gender gender) { userProfile.setGender(gender); }

    public void updatePreference(user_gender pref){
        userProfile.setGenderPreference(pref);
    }

    //update a specific answer
    public String updateAnswer(Boolean answer, int spot)
    {
        String message = null;
        int numQuestions = this.userProfile.getNumQuestions();

        //if this fails the spot is out of bounds
        if(spot >= 0 && spot < numQuestions) {
            userProfile.updateAnswer(answer,spot);
            message = "success";
        }

        return message;
    }

    //different wants to set a users answers mainly for testing
    public void updateAllAnswers(Boolean a1, Boolean a2, Boolean a3, Boolean a4, Boolean a5) { userProfile.updateAllAnswers(a1,a2,a3,a4,a5); }
}
