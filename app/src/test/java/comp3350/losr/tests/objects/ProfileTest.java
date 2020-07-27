package comp3350.losr.tests.objects;

import junit.framework.TestCase;

import comp3350.losr.objects.Profile;
import comp3350.losr.objects.User;

public class ProfileTest extends TestCase
{
    Profile profile;

    public void setUp() {
        profile = new Profile();
    }

    public void testProfileEmpty()
    {
        System.out.println("Starting testProfileEmpty");

        assertNotNull(profile);
        assertEquals("Hi!", profile.getBio());
        assertEquals(User.user_gender.Losr, profile.getGender());
        assertEquals(User.user_gender.Losr, profile.getGenderPreference());
        assertEquals("00/00/0000", profile.dateOfBirth());
        assertEquals(0, profile.getAge());

        System.out.println("testProfileEmpty complete");
    }

    public void testProfileAverage1()
    {
        System.out.println("Starting testProfileAverage1");

        profile.setBio("My bio");
        profile.setGender(User.user_gender.Male);
        profile.setGenderPreference(User.user_gender.Female);
        profile.setDateOfBirth(1999, 5, 20);
        profile.updateAnswer(Boolean.TRUE, 2, 0);
        profile.updateAnswer(Boolean.TRUE, 2, 2);

        assertNotNull(profile);
        assertEquals("My bio", profile.getBio());
        assertEquals(User.user_gender.Male, profile.getGender());
        assertEquals(User.user_gender.Female, profile.getGenderPreference());
        assertEquals("20/05/1999", profile.dateOfBirth());
        assertEquals(21, profile.getAge());
        assertEquals(Boolean.TRUE, profile.getAnswers().get(0).getAnswer());
        assertEquals(Boolean.FALSE, profile.getAnswers().get(1).getAnswer());
        assertEquals(Boolean.TRUE, profile.getAnswers().get(2).getAnswer());
        assertEquals(Boolean.FALSE, profile.getAnswers().get(3).getAnswer());
        assertEquals(Boolean.FALSE, profile.getAnswers().get(4).getAnswer());

        System.out.println("testProfileAverage1 complete");
    }

    public void testProfileAverage2()
    {
        System.out.println("Starting testProfileAverage2");

        profile.setBio("Hello! This is my bio!");
        profile.setGender(User.user_gender.Female);
        profile.setGenderPreference(User.user_gender.Female);
        profile.setDateOfBirth(1950, 2, 15);
        profile.updateAnswer(Boolean.TRUE, 2, 1);
        profile.updateAnswer(Boolean.TRUE, 2, 3);

        assertNotNull(profile);
        assertEquals("Hello! This is my bio!", profile.getBio());
        assertEquals(User.user_gender.Female, profile.getGender());
        assertEquals(User.user_gender.Female, profile.getGenderPreference());
        assertEquals("15/02/1950", profile.dateOfBirth());
        assertEquals(70, profile.getAge());
        assertEquals(Boolean.FALSE, profile.getAnswers().get(0).getAnswer());
        assertEquals(Boolean.TRUE, profile.getAnswers().get(1).getAnswer());
        assertEquals(Boolean.FALSE, profile.getAnswers().get(2).getAnswer());
        assertEquals(Boolean.TRUE, profile.getAnswers().get(3).getAnswer());
        assertEquals(Boolean.FALSE, profile.getAnswers().get(4).getAnswer());

        System.out.println("testProfileAverage2 complete");
    }

    public void testProfileEquals()
    {
        Profile profile2;

        System.out.println("Starting testProfileEquals");

        profile2 = new Profile();

        profile.setBio("Hello! This is my bio!");
        profile.setGender(User.user_gender.Female);
        profile.setGenderPreference(User.user_gender.Female);
        profile.setDateOfBirth(1950, 2, 15);
        profile.updateAllAnswers(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,2,2,2,2,2);

        profile2.setBio("Hello! This is my bio!");
        profile2.setGender(User.user_gender.Female);
        profile2.setGenderPreference(User.user_gender.Female);
        profile2.setDateOfBirth(1950, 2, 15);
        profile2.updateAllAnswers(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,2,2,2,2,2);

        assertNotNull(profile);
        assertNotNull(profile2);
        //assertTrue(profile.equals(profile2));
        assertEquals(profile, profile2);

        System.out.println("testProfileEquals complete");
    }

    public void testDateOfBirth()
    {
        System.out.println("Starting testDateOfBirth");

        profile.setDateOfBirth(1999, 1, 25);
        assertEquals("25/01/1999", profile.dateOfBirth());
        assertEquals(21, profile.getAge());

        System.out.println("testDateOfBirth complete");
    }

    public void testDateOfBirthEdges1()
    {
        System.out.println("Starting testDateOfBirthEdges1");

        profile.setDateOfBirth(2020, 1, 1);
        assertEquals("01/01/2020", profile.dateOfBirth());
        assertEquals(0, profile.getAge());

        System.out.println("testDateOfBirthEdges1 complete");
    }

    public void testDateOfBirthEdges2()
    {
        System.out.println("Starting testDateOfBirthEdges2");

        profile.setDateOfBirth(1, 1, 1);
        assertEquals("01/01/0001", profile.dateOfBirth());
        assertEquals(2019, profile.getAge());

        System.out.println("testDateOfBirthEdges2 complete");
    }

    public void testSetBio()
    {
        System.out.println("Starting testSetBio");

        profile.setBio("this is a test");
        assertEquals("this is a test", profile.getBio());

        System.out.println("testSetBio complete");
    }

    public void testSetGenders()
    {
        System.out.println("Starting testSetGenders");

        profile.setGender(User.user_gender.Female);
        profile.setGenderPreference(User.user_gender.Female);

        assertEquals(User.user_gender.Female, profile.getGender());
        assertEquals(User.user_gender.Female, profile.getGenderPreference());

        System.out.println("testSetGender complete");
    }
}
