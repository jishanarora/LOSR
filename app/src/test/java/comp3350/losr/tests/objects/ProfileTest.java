package comp3350.losr.tests.objects;

import junit.framework.TestCase;

import java.util.PriorityQueue;

import comp3350.losr.objects.Profile;

public class ProfileTest extends TestCase {

    public void testProfileEmpty()
    {
        Profile profile;

        System.out.println("Starting testProfileEmpty");

        profile = new Profile();

        assertNotNull(profile);
        assertEquals("Hi!", profile.getBio());
        assertEquals("Not Specified", profile.getGender());
        assertEquals("Not Specified", profile.getGenderPreference());
        assertEquals("00/00/0", profile.dateOfBirth());
        assertEquals(0, profile.getAge());

        System.out.println("testProfileEmpty complete");
    }

    public void testProfileAverage1()
    {
        Profile profile;

        System.out.println("Starting testProfileAverage1");

        profile = new Profile();

        profile.setBio("My bio");
        profile.setGender("Male");
        profile.setGenderPreference("Female");
        profile.setDateOfBirth(1999, 5, 20);

        assertNotNull(profile);
        assertEquals("My bio", profile.getBio());
        assertEquals("Male", profile.getGender());
        assertEquals("Female", profile.getGenderPreference());
        assertEquals("20/05/1999", profile.dateOfBirth());
        assertEquals(21, profile.getAge());

        System.out.println("testProfileAverage1 complete");
    }

    public void testProfileAverage2()
    {
        Profile profile;

        System.out.println("Starting testProfileAverage2");

        profile = new Profile();

        profile.setBio("Hello! This is my bio!");
        profile.setGender("Female");
        profile.setGenderPreference("Female");
        profile.setDateOfBirth(1950, 2, 15);

        assertNotNull(profile);
        assertEquals("Hello! This is my bio!", profile.getBio());
        assertEquals("Female", profile.getGender());
        assertEquals("Female", profile.getGenderPreference());
        assertEquals("15/02/1950", profile.dateOfBirth());
        assertEquals(70, profile.getAge());

        System.out.println("testProfileAverage2 complete");
    }

    public void testProfileEquals()
    {
        Profile profile;
        Profile profile2;

        System.out.println("Starting testProfileEquals");

        profile = new Profile();
        profile2 = new Profile();

        profile.setBio("Hello! This is my bio!");
        profile.setGender("Female");
        profile.setGenderPreference("Female");
        profile.setDateOfBirth(1950, 2, 15);

        profile2.setBio("Hello! This is my bio!");
        profile2.setGender("Female");
        profile2.setGenderPreference("Female");
        profile2.setDateOfBirth(1950, 2, 15);

        assertNotNull(profile);
        assertNotNull(profile2);
        assertTrue(profile.equals(profile2));

        System.out.println("testProfileEquals complete");
    }

    public void testProfileNullVals()
    {
        Profile profile;

        System.out.println("Starting testProfileNullVals");

        profile = new Profile();

        profile.setBio(null);
        try
        {
            profile.setGender(null);
            fail();
        }
        catch(NullPointerException npe) {}
        try
        {
            profile.setGenderPreference(null);
            fail();
        }
        catch(NullPointerException npe) {}

        assertEquals("", profile.getBio());
        assertEquals("Not Specified", profile.getGender());
        assertEquals("Not Specified", profile.getGenderPreference());

        System.out.println("testProfileNullVals complete");
    }

    public void testProfileInvalidGender()
    {
        Profile profile;

        System.out.println("Starting testProfileInvalidGender");

        profile = new Profile();

        profile.setGenderPreference("xyz");
        profile.setGender("zxy");

        assertEquals("Not Specified", profile.getGenderPreference());
        assertEquals("Not Specified", profile.getGender());

        profile.setGenderPreference("~@$$%^%$^");
        profile.setGender("!~@^^**%^");

        assertEquals("Not Specified", profile.getGenderPreference());
        assertEquals("Not Specified", profile.getGender());

        System.out.println("testProfileInvalidGender complete");
    }
}
