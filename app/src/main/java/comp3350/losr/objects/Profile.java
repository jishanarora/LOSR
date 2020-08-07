package comp3350.losr.objects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

//used to hold a lot of the user information
public class Profile {
    private String bio = "hi";

    private User.user_gender gender = User.user_gender.Losr;
    private User.user_gender genderPreference = User.user_gender.Losr;

    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private int age;

    private Boolean isBlindMode = false;

    private int numQuestions = 5;
    private ArrayList<Question> answers;

    public Profile() {
        answers = new ArrayList<>(numQuestions);

        for (int i = 0; i < numQuestions; i++) {
            answers.add(new Question(i + 1, "tempQ" + i + 1, Boolean.FALSE, 2));
        }
    }

    public String toString() {
        String message;

        message = "bio: " + bio + " gender: " + gender.toString() + " gender pref: " + genderPreference.toString() + " year: " + birthYear + " month: " + birthMonth + " day: " + birthDay + " answers: ";
        for (int i = 0; i < answers.size(); i++) {
            message += answers.get(i).toString() + " ";
        }

        return message;
    }

    public boolean equals(Object object) {
        boolean result;
        Profile p;

        result = false;

        if (object instanceof Profile) {
            p = (Profile) object;
            if (p.age == age && p.bio.equals(bio) && p.genderPreference.equals(genderPreference)
                    && p.birthDay == birthDay && p.birthMonth == birthMonth && p.birthYear == birthYear) {
                result = true;

                for (int i = 0; i < answers.size(); i++) {
                    if (!answers.get(i).equals(p.getAnswers().get(i))) {
                        result = false;
                    }
                }
            }
        }
        return result;
    }

    public String genderToString() {
        String result;

        if (gender == User.user_gender.Female) {
            result = "female";
        } else if (gender == User.user_gender.Male) {
            result = "male";
        } else {
            result = "losr";
        }

        return result;
    }

    public String genderPrefToString() {
        String result;

        if (genderPreference == User.user_gender.Female) {
            result = "female";
        } else if (genderPreference == User.user_gender.Male) {
            result = "male";
        } else {
            result = "losr";
        }

        return result;
    }


    public int getAge() {
        return age;
    }

    public Boolean getBlindMode() {
        return isBlindMode;
    }

    public String getBio() {
        return bio;
    }

    public User.user_gender getGender() {
        return gender;
    }

    public User.user_gender getGenderPreference() {
        return genderPreference;
    }

    public ArrayList<Question> getAnswers() {
        return answers;
    }

    public int getNumQuestions() {
        return numQuestions;
    }

    public String dateOfBirth() {
        String dateOfBirth;

        if (birthYear == 0 && birthMonth == 0 && birthDay == 0) {
            dateOfBirth = "00/00/0000";
        } else {
            Calendar myCal = Calendar.getInstance();
            myCal.set(Calendar.YEAR, birthYear);
            myCal.set(Calendar.MONTH, birthMonth - 1);
            myCal.set(Calendar.DAY_OF_MONTH, birthDay);
            Date date = myCal.getTime();

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            dateOfBirth = format.format(date);
        }

        return dateOfBirth;
    }

    //update a specific answer
    //bounds check is made from User
    public void updateAnswer(Boolean answer, int weight, int spot) {
        answers.get(spot).setAnswer(answer);
        answers.get(spot).setWeight(weight);
    }

    //different wants to set a users answers mainly for testing
    public void updateAllAnswers(Boolean a1, Boolean a2, Boolean a3, Boolean a4, Boolean a5, int w1, int w2, int w3, int w4, int w5) {
        answers.get(0).setAnswer(a1);
        answers.get(0).setWeight(w1);
        answers.get(1).setAnswer(a2);
        answers.get(1).setWeight(w2);
        answers.get(2).setAnswer(a3);
        answers.get(2).setWeight(w3);
        answers.get(3).setAnswer(a4);
        answers.get(3).setWeight(w4);
        answers.get(4).setAnswer(a5);
        answers.get(4).setWeight(w5);

    }

    public void setBio(String bio) {
        if (bio == null) {
            this.bio = "";
        } else {
            this.bio = bio;
        }
    }

    public void setBlindMode(Boolean blindMode) {
        isBlindMode = blindMode;
    }

    public void setGender(User.user_gender gender) {
        this.gender = gender;
    }

    public void setGenderPreference(User.user_gender preference) {
        genderPreference = preference;
    }

    public void setDateOfBirth(int year, int month, int day) {
        birthYear = year;
        birthMonth = month;
        birthDay = day;
        if (year == 0) {
            age = 0;
        } else {
            age = Calendar.getInstance().get(Calendar.YEAR) - year;
        }
    }
}
