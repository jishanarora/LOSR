package comp3350.losr.tests.objects;

import junit.framework.TestCase;

import comp3350.losr.objects.Profile;
import comp3350.losr.objects.User;

public class ProfileTest extends TestCase {
    Profile profile;

    public void setUp()
    {
        profile = new Profile();
    }

    public void testProfileEmpty() {
        System.out.println("Starting testProfileEmpty");

        assertNotNull(profile);
        assertEquals("Hi!", profile.getBio());
        assertEquals(User.user_gender.Losr, profile.getGender());
        assertEquals(User.user_gender.Losr, profile.getGenderPreference());
        assertEquals("00/00/0", profile.dateOfBirth());
        assertEquals(0, profile.getAge());

        System.out.println("testProfileEmpty complete");
    }

    public void testProfileAverage1() {
        System.out.println("Starting testProfileAverage1");

        profile.setBio("My bio");
        profile.setGender(User.user_gender.Male);
        profile.setGenderPreference(User.user_gender.Female);
        profile.setDateOfBirth(1999, 5, 20);

        assertNotNull(profile);
        assertEquals("My bio", profile.getBio());
        assertEquals(User.user_gender.Male, profile.getGender());
        assertEquals(User.user_gender.Female, profile.getGenderPreference());
        assertEquals("20/05/1999", profile.dateOfBirth());
        assertEquals(21, profile.getAge());

        System.out.println("testProfileAverage1 complete");
    }

    public void testProfileAverage2() {
        System.out.println("Starting testProfileAverage2");

        profile.setBio("Hello! This is my bio!");
        profile.setGender(User.user_gender.Female);
        profile.setGenderPreference(User.user_gender.Female);
        profile.setDateOfBirth(1950, 2, 15);

        assertNotNull(profile);
        assertEquals("Hello! This is my bio!", profile.getBio());
        assertEquals(User.user_gender.Female, profile.getGender());
        assertEquals(User.user_gender.Female, profile.getGenderPreference());
        assertEquals("15/02/1950", profile.dateOfBirth());
        assertEquals(70, profile.getAge());

        System.out.println("testProfileAverage2 complete");
    }

    public void testProfileEquals() {
        Profile profile2;

        System.out.println("Starting testProfileEquals");

        profile2 = new Profile();

        profile.setBio("Hello! This is my bio!");
        profile.setGender(User.user_gender.Female);
        profile.setGenderPreference(User.user_gender.Female);
        profile.setDateOfBirth(1950, 2, 15);

        profile2.setBio("Hello! This is my bio!");
        profile2.setGender(User.user_gender.Female);
        profile2.setGenderPreference(User.user_gender.Female);
        profile2.setDateOfBirth(1950, 2, 15);

        assertNotNull(profile);
        assertNotNull(profile2);
        //assertTrue(profile.equals(profile2));
        assertEquals(profile, profile2);

        System.out.println("testProfileEquals complete");
    }

    public void testProfileNullVals() {

        System.out.println("Starting testProfileNullVals");

        profile.setBio(null);

        assertEquals("", profile.getBio());

        System.out.println("testProfileNullVals complete");
    }
}
