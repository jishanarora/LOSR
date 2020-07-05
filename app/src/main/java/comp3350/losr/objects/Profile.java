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
        if (gender.equals("Male") || gender.equals("Female"))
            this.gender = gender;
        else
            System.out.println("Please choose either Male or Female");
    }

    public void setGenderPreference(String preference)
    {
        if (preference.equals("Male") || preference.equals("Female"))
            genderPreference = preference;
        else
            System.out.println("Please choose either Male or Female");
    }

    public void setDateOfBirth(int year, int month, int day)
    {
        if ((month >= 1 && month <= 12 && day >= 1) &&
                (((month == 1 || month == 3 || month == 5 || month == 7
                        || month == 8 || month == 10 || month == 12) && (day <= 31))
                        || ((month == 2 || month == 4 || month == 6 || month == 9
                        || month == 11) && day <= 30))) {
            birthYear = year;
            birthMonth = month;
            birthDay = day;
            if(year == 0)
            {
                age = 0;
            }
            else
            {
                age = 2020 - year;
            }
        } else {
            System.out.println("Please enter a valid date.");
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
