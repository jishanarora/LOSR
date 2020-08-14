package comp3350.losr.tests.objects;

import junit.framework.TestCase;


import comp3350.losr.objects.User;

public class ProfileTest extends TestCase {
    User profile;

    public void setUp() {
        profile = new User("FirstName", "LastName", "name@email.com", "password");
    }

    public void testProfileEmpty() {
        System.out.println("Starting testProfileEmpty");

        assertNotNull(profile);
        assertEquals("hi", profile.getUserBio());
        assertEquals(User.user_gender.Losr, profile.getUserGender());
        assertEquals(User.user_gender.Losr, profile.getUserGenderPreference());
        assertEquals("00/00/0000", profile.getUserDateOfBirth());
        assertEquals(0, profile.getUserAge());

        System.out.println("testProfileEmpty complete");
    }

    public void testProfileAverage1() {
        System.out.println("Starting testProfileAverage1");

        profile.setUserBio("My bio");
        profile.setUserGender(User.user_gender.Male);
        profile.setUserGenderPreference(User.user_gender.Female);
        profile.setUserDateOfBirth(1999, 5, 20);
        profile.setUserAnswer(Boolean.TRUE, 2, 0);
        profile.setUserAnswer(Boolean.TRUE, 2, 2);

        assertNotNull(profile);
        assertEquals("My bio", profile.getUserBio());
        assertEquals(User.user_gender.Male, profile.getUserGender());
        assertEquals(User.user_gender.Female, profile.getUserGenderPreference());
        assertEquals("20/05/1999", profile.getUserDateOfBirth());
        assertEquals(21, profile.getUserAge());
        assertEquals(Boolean.TRUE, profile.getUserAnswers().get(0).getAnswer());
        assertEquals(Boolean.FALSE, profile.getUserAnswers().get(1).getAnswer());
        assertEquals(Boolean.TRUE, profile.getUserAnswers().get(2).getAnswer());
        assertEquals(Boolean.FALSE, profile.getUserAnswers().get(3).getAnswer());
        assertEquals(Boolean.FALSE, profile.getUserAnswers().get(4).getAnswer());

        System.out.println("testProfileAverage1 complete");
    }

    public void testProfileAverage2() {
        System.out.println("Starting testProfileAverage2");

        profile.setUserBio("Hello! This is my bio!");
        profile.setUserGender(User.user_gender.Female);
        profile.setUserGenderPreference(User.user_gender.Female);
        profile.setUserDateOfBirth(1950, 2, 15);
        profile.setUserAnswer(Boolean.TRUE, 2, 1);
        profile.setUserAnswer(Boolean.TRUE, 2, 3);

        assertNotNull(profile);
        assertEquals("Hello! This is my bio!", profile.getUserBio());
        assertEquals(User.user_gender.Female, profile.getUserGender());
        assertEquals(User.user_gender.Female, profile.getUserGenderPreference());
        assertEquals("15/02/1950", profile.getUserDateOfBirth());
        assertEquals(70, profile.getUserAge());
        assertEquals(Boolean.FALSE, profile.getUserAnswers().get(0).getAnswer());
        assertEquals(Boolean.TRUE, profile.getUserAnswers().get(1).getAnswer());
        assertEquals(Boolean.FALSE, profile.getUserAnswers().get(2).getAnswer());
        assertEquals(Boolean.TRUE, profile.getUserAnswers().get(3).getAnswer());
        assertEquals(Boolean.FALSE, profile.getUserAnswers().get(4).getAnswer());

        System.out.println("testProfileAverage2 complete");
    }

    public void testProfileEquals() {
        User profile2;

        System.out.println("Starting testProfileEquals");

        profile2 = new User("FirstName", "LastName", "name@email.com", "password");

        profile.setUserBio("Hello! This is my bio!");
        profile.setUserGender(User.user_gender.Female);
        profile.setUserGenderPreference(User.user_gender.Female);
        profile.setUserDateOfBirth(1950, 2, 15);
        profile.setUserAllAnswers(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, 2, 2, 2, 2, 2);

        profile2.setUserBio("Hello! This is my bio!");
        profile2.setUserGender(User.user_gender.Female);
        profile2.setUserGenderPreference(User.user_gender.Female);
        profile2.setUserDateOfBirth(1950, 2, 15);
        profile2.setUserAllAnswers(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, 2, 2, 2, 2, 2);

        assertNotNull(profile);
        assertNotNull(profile2);
        //assertTrue(profile.equals(profile2));
        assertEquals(profile, profile2);

        System.out.println("testProfileEquals complete");
    }

    public void testDateOfBirth() {
        System.out.println("Starting testDateOfBirth");

        profile.setUserDateOfBirth(1999, 1, 25);
        assertEquals("25/01/1999", profile.getUserDateOfBirth());
        assertEquals(21, profile.getUserAge());

        System.out.println("testDateOfBirth complete");
    }

    public void testDateOfBirthEdges1() {
        System.out.println("Starting testDateOfBirthEdges1");

        profile.setUserDateOfBirth(2020, 1, 1);
        assertEquals("01/01/2020", profile.getUserDateOfBirth());
        assertEquals(0, profile.getUserAge());

        System.out.println("testDateOfBirthEdges1 complete");
    }

    public void testDateOfBirthEdges2() {
        System.out.println("Starting testDateOfBirthEdges2");

        profile.setUserDateOfBirth(1, 1, 1);
        assertEquals("01/01/0001", profile.getUserDateOfBirth());
        assertEquals(2019, profile.getUserAge());

        System.out.println("testDateOfBirthEdges2 complete");
    }

    public void testSetBio() {
        System.out.println("Starting testSetBio");

        profile.setUserBio("this is a test");
        assertEquals("this is a test", profile.getUserBio());

        System.out.println("testSetBio complete");
    }

    public void testSetGenders() {
        System.out.println("Starting testSetGenders");

        profile.setUserGender(User.user_gender.Female);
        profile.setUserGenderPreference(User.user_gender.Female);

        assertEquals(User.user_gender.Female, profile.getUserGender());
        assertEquals(User.user_gender.Female, profile.getUserGenderPreference());

        System.out.println("testSetGender complete");
    }
}
