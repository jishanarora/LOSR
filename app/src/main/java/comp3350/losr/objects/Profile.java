package comp3350.losr.objects;


public class Profile
{
    private int age;
    private String bio = "Hi!";
    private String gender = "Not Specified";
    private String genderPreference = "Not Specified";
    private int birthYear;
    private int birthMonth;
    private int birthDay;

    public Profile() {
    }

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

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public void setGenderPreference(String preference)
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

    public String getGenderPreference()
    {
        return genderPreference;
    }

    public String getGender()
    {
        return gender;
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
                    && p.birthDay == birthDay && p.birthMonth == birthMonth && p.birthYear == birthYear) {
                result = true;
            }
        }
        return result;
    }
}
