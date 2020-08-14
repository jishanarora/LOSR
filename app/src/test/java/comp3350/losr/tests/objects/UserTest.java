package comp3350.losr.tests.objects;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.losr.objects.Question;
import comp3350.losr.objects.User;

import static org.junit.Assert.assertNotEquals;

public class UserTest extends TestCase {
    User user;
    User user1;

    public void testUserEmpty() {
        System.out.println("Starting testUserEmpty");

        user = new User("", "", "", "");

        assertNotNull(user);
        assertEquals("", user.getUserFirstName());
        assertEquals("", user.getUserLastName());
        assertEquals("", user.getUserEmail());
        assertEquals("", user.getUserPassword());
        assertEquals(5, user.getUserAnswers().size());

        System.out.println("testUserEmpty completed");
    }

    public void testUserAverage() {
        System.out.println("Starting testUserAverage");

        user = new User("John", "Doe", "johndoe@gmail.com", "password");
        user.setUserAnswer(Boolean.TRUE, 2, 0);
        user.setUserAnswer(Boolean.TRUE, 2, 2);
        ArrayList<Question> userAnswers = user.getUserAnswers();

        assertNotNull(user);
        assertEquals("John", user.getUserFirstName());
        assertEquals("Doe", user.getUserLastName());
        assertEquals("johndoe@gmail.com", user.getUserEmail());
        assertEquals("password", user.getUserPassword());
        assertTrue(userAnswers.get(0).getAnswer());
        assertTrue(userAnswers.get(2).getAnswer());
        assertEquals(5, userAnswers.size());

        System.out.println("testUserAverage completed");
    }

    public void testUserAverage2() {
        System.out.println("Starting testUserAverage2");

        user = new User("Mary", "Poppins", "marypoppins@gmail.com", "password1");
        user.setUserAnswer(Boolean.TRUE, 2, 0);
        user.setUserAnswer(Boolean.TRUE, 2, 3);
        ArrayList<Question> userAnswers = user.getUserAnswers();

        assertNotNull(user);
        assertEquals("Mary", user.getUserFirstName());
        assertEquals("Poppins", user.getUserLastName());
        assertEquals("marypoppins@gmail.com", user.getUserEmail());
        assertEquals("password1", user.getUserPassword());
        assertTrue(userAnswers.get(0).getAnswer());
        assertTrue(userAnswers.get(3).getAnswer());
        assertEquals(5, userAnswers.size());

        System.out.println("testUserAverage2 completed");
    }

    public void testUserAverage3() {
        System.out.println("Starting testUserAverage3");

        user = new User("Sam", "Smith", "samsmith@gmail.com", "password5");
        user.setUserAnswer(Boolean.TRUE, 2, 0);
        user.setUserAnswer(Boolean.TRUE, 2, 1);
        ArrayList<Question> userAnswers = user.getUserAnswers();

        assertNotNull(user);
        assertEquals("Sam", user.getUserFirstName());
        assertEquals("Smith", user.getUserLastName());
        assertEquals("samsmith@gmail.com", user.getUserEmail());
        assertEquals("password5", user.getUserPassword());
        assertTrue(userAnswers.get(0).getAnswer());
        assertTrue(userAnswers.get(1).getAnswer());
        assertEquals(5, userAnswers.size());

        System.out.println("testUserAverage3 completed");
    }

    public void testUserEquals() {
        System.out.println("Starting testUserEquals");

        user = new User("Sam", "Smith", "samsmith@gmail.com", "password");
        user.setUserAnswer(Boolean.FALSE, 2, 0);
        user.setUserAnswer(Boolean.FALSE, 2, 1);
        user.setUserDateOfBirth(1999, 1, 25);
        user.setUserBio("Hi! My name is Sam!");
        user.setUserGender(User.user_gender.Female);
        user.setUserGenderPreference(User.user_gender.Female);

        user1 = new User("Sam", "Smith", "samsmith@gmail.com", "password");
        user1.setUserAnswer(Boolean.FALSE, 2, 0);
        user1.setUserAnswer(Boolean.FALSE, 2, 1);
        user1.setUserDateOfBirth(1999, 1, 25);
        user1.setUserBio("Hi! My name is Sam!");
        user1.setUserGender(User.user_gender.Female);
        user1.setUserGenderPreference(User.user_gender.Female);

        //assertTrue(user.equals(user1));
        assertEquals(user, user1);

        System.out.println("testUserEquals complete");
    }

    public void testUserEqualsDiffAnswers() {
        System.out.println("Starting testUserEqualsDiffAnswers");

        user = new User("Sam", "Smith", "samsmith@gmail.com", "password");
        user.setUserAnswer(Boolean.FALSE, 2, 0);
        user.setUserAnswer(Boolean.FALSE, 2, 1);
        user.setUserDateOfBirth(1999, 1, 25);
        user.setUserBio("Hi! My name is Sam!");
        user.setUserGender(User.user_gender.Female);
        user.setUserGenderPreference(User.user_gender.Female);

        user1 = new User("Sam", "Smith", "samsmith@gmail.com", "password");
        user1.setUserAnswer(Boolean.TRUE, 2, 0);
        user1.setUserAnswer(Boolean.FALSE, 2, 1);
        user1.setUserDateOfBirth(1999, 1, 25);
        user1.setUserBio("Hi! My name is Sam!");
        user1.setUserGender(User.user_gender.Female);
        user1.setUserGenderPreference(User.user_gender.Female);

        assertNotEquals(user, user1);

        System.out.println("testUserEqualsDiffAnswers complete");
    }

    public void testSetUserProfile() {
        System.out.println("Starting testSetUserProfile");

        user = new User("", "", "", "");
        user.setUserProfile("testing bio", User.user_gender.Female, User.user_gender.Female, 1999, 1, 25, false);

        assertEquals("testing bio", user.getUserBio());
        assertEquals(User.user_gender.Female, user.getUserGender());
        assertEquals(User.user_gender.Female, user.getUserGenderPreference());
        assertEquals("25/01/1999", user.getUserDateOfBirth());
        assertEquals(21, user.getUserAge());

        System.out.println("testSetUserProfile complete");
    }

    public void testUpdateAnswer() {
        System.out.println("Starting testUpdateAnswer");

        user = new User("", "", "", "");
        user.setUserAnswer(Boolean.TRUE, 2, 2);
        user.setUserAnswer(Boolean.TRUE, 2, 4);
        //this is out of bounds and should do nothing
        user.setUserAnswer(Boolean.TRUE, 2, 12);

        assertEquals(Boolean.FALSE, user.getUserAnswers().get(0).getAnswer());
        assertEquals(Boolean.FALSE, user.getUserAnswers().get(1).getAnswer());
        assertEquals(Boolean.TRUE, user.getUserAnswers().get(2).getAnswer());
        assertEquals(Boolean.FALSE, user.getUserAnswers().get(3).getAnswer());
        assertEquals(Boolean.TRUE, user.getUserAnswers().get(4).getAnswer());

        System.out.println("testUpdateAnswer complete");
    }

    public void testUpdateAllAnswers() {
        System.out.println("Starting testUpdateAllAnswers");

        user = new User("", "", "", "");
        user.setUserAllAnswers(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, 2, 2, 2, 2, 2);

        assertEquals(Boolean.TRUE, user.getUserAnswers().get(0).getAnswer());
        assertEquals(Boolean.TRUE, user.getUserAnswers().get(1).getAnswer());
        assertEquals(Boolean.TRUE, user.getUserAnswers().get(2).getAnswer());
        assertEquals(Boolean.TRUE, user.getUserAnswers().get(3).getAnswer());
        assertEquals(Boolean.TRUE, user.getUserAnswers().get(4).getAnswer());

        System.out.println("testUpdateAllAnswers complete");
    }

    public void testUpdateDateOfBirth() {
        System.out.println("Starting testUpdateDateOfBirth");

        user = new User("", "", "", "");
        user.setUserDateOfBirth(1988, 1, 12);

        assertEquals("12/01/1988", user.getUserDateOfBirth());
        assertEquals(32, user.getUserAge());

        System.out.println("testUpdateDateOfBirth complete");
    }

    public void testUpdateBio() {
        System.out.println("Starting testUpdateBio");

        user = new User("", "", "", "");
        user.setUserBio("new test bio");

        assertEquals("new test bio", user.getUserBio());

        System.out.println("testUpdateBio complete");
    }

}
