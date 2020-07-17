package comp3350.losr.objects;

import java.util.ArrayList;
import java.util.Random;

//used to hold a lot of the user information
public class Profile
{
    private int age;
    private String bio = "Hi!";
    private User.user_gender gender = User.user_gender.Losr;
    private User.user_gender genderPreference = User.user_gender.Losr;
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private ArrayList<Boolean> answers;

    public Profile() {
        answers = new ArrayList<>();
    }

    //update a specific answer
    public void updateAnswerList(Boolean answer, int spot) {
        answers.add(spot, answer);
    }

    //different wants to set a users answers mainly for testing
    public void updateAllAnswers(Boolean a1, Boolean a2, Boolean a3, Boolean a4, Boolean a5)
    {
        answers.clear();
        answers.add(a1);
        answers.add(a2);
        answers.add(a3);
        answers.add(a4);
        answers.add(a5);
    }

    public void randomAnswers()
    {
        Random random;
        int num;
        for(int i = 0; i < 5; i++)
        {
            random = new Random();
            num = random.nextInt(2);

            if(num == 0)
            {
                answers.add(Boolean.TRUE);
            }
            else
            {
                answers.add(Boolean.FALSE);
            }
        }
    }

    public ArrayList<Boolean> getAnswers() {return answers;}

    public void setBio(String bio)
    {
        if(bio == null)
        {
            this.bio = "";
        }
        else
        {
            this.bio = bio;
        }
    }

    public void setGender(User.user_gender gender)
    {
        this.gender = gender;
    }

    public void setGenderPreference(User.user_gender preference)
    {
        genderPreference = preference;
    }

    public void setDateOfBirth(int year, int month, int day)
    {
        birthYear = year;
        birthMonth = month;
        birthDay = day;
        if(year == 0)
        {
            age = 0;
        }
        else {
            age = 2020 - year;
        }
    }

    public int getAge()
    {
        return age;
    }

    public String getBio()
    {
        return bio;
    }

    public User.user_gender getGender()
    {
        return gender;
    }

    public User.user_gender getGenderPreference()
    {
        return genderPreference;
    }

    public String dateOfBirth()
    {
        String year = birthYear + "";
        String month, day;

        if (birthMonth < 10)
            month = "0" + birthMonth;
        else
            month = birthMonth + "";

        if (birthDay < 10)
            day = "0" + birthDay;
        else
            day = birthDay + "";

        return day + "/" + month + "/" + year;
    }

    public boolean equals(Object object)
    {
        boolean result;
        Profile p;

        result = false;

        if (object instanceof Profile) {
            p = (Profile) object;
            if (p.age == age && p.bio.equals(bio) && p.genderPreference.equals(genderPreference)
                    && p.birthDay == birthDay && p.birthMonth == birthMonth && p.answers.equals(answers) && p.birthYear == birthYear) {
                result = true;
            }
        }
        return result;
    }
}
