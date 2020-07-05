package comp3350.losr.tests.objects;

import comp3350.losr.objects.Profile;

public class ProfileTest {

    public void testProfileEmpty()
    {
        Profile profile;

        System.out.println("Starting testProfileEmpty");

        profile = new Profile();
        profile.setDateOfBirth(1999, 1, 25);
        profile.setBio("bio");
        profile.setGenderPreference("Male");
    }
}
