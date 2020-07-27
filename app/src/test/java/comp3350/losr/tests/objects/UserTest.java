package comp3350.losr.tests.objects;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.losr.objects.Question;
import comp3350.losr.objects.User;

import static org.junit.Assert.assertNotEquals;

public class UserTest extends TestCase
{
    User user;
    User user1;

    public void testUserEmpty()
    {
        System.out.println("Starting testUserEmpty");

        user = new User("", "", "", "");

        assertNotNull(user);
        assertEquals("", user.getUserFirstName());
        assertEquals("", user.getUserLastName());
        assertEquals("", user.getUserEmail());
        assertEquals("", user.getUserPassword());
        assertEquals(5, user.getAnswers().size());

        System.out.println("testUserEmpty completed");
    }

    public void testUserAverage()
    {
        System.out.println("Starting testUserAverage");

        user = new User("John", "Doe", "johndoe@gmail.com", "password");
        user.updateAnswer(Boolean.TRUE, 2, 0);
        user.updateAnswer(Boolean.TRUE, 2, 2);
        ArrayList<Question> userAnswers = user.getAnswers();

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

    public void testUserAverage2()
    {
        System.out.println("Starting testUserAverage2");

        user = new User("Mary", "Poppins", "marypoppins@gmail.com", "password1");
        user.updateAnswer(Boolean.TRUE, 2, 0);
        user.updateAnswer(Boolean.TRUE, 2, 3);
        ArrayList<Question> userAnswers = user.getAnswers();

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

    public void testUserAverage3()
    {
        System.out.println("Starting testUserAverage3");

        user = new User("Sam", "Smith", "samsmith@gmail.com", "password5");
        user.updateAnswer(Boolean.TRUE, 2, 0);
        user.updateAnswer(Boolean.TRUE, 2, 1);
        ArrayList<Question> userAnswers = user.getAnswers();

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

    public void testUserEquals()
    {
        System.out.println("Starting testUserEquals");

        user = new User("Sam", "Smith", "samsmith@gmail.com", "password");
        user.updateAnswer(Boolean.FALSE, 2, 0);
        user.updateAnswer(Boolean.FALSE, 2, 1);
        user.updateDateOfBirth(1999, 1, 25);
        user.updateBio("Hi! My name is Sam!");
        user.updateGender(User.user_gender.Female);
        user.updatePreference(User.user_gender.Female);

        user1 = new User("Sam", "Smith", "samsmith@gmail.com", "password");
        user1.updateAnswer(Boolean.FALSE, 2, 0);
        user1.updateAnswer(Boolean.FALSE, 2, 1);
        user1.updateDateOfBirth(1999, 1, 25);
        user1.updateBio("Hi! My name is Sam!");
        user1.updateGender(User.user_gender.Female);
        user1.updatePreference(User.user_gender.Female);

        //assertTrue(user.equals(user1));
        assertEquals(user, user1);

        System.out.println("testUserEquals complete");
    }

    public void testUserEqualsDiffAnswers()
    {
        System.out.println("Starting testUserEqualsDiffAnswers");

        user = new User("Sam", "Smith", "samsmith@gmail.com", "password");
        user.updateAnswer(Boolean.FALSE, 2, 0);
        user.updateAnswer(Boolean.FALSE, 2, 1);
        user.updateDateOfBirth(1999, 1, 25);
        user.updateBio("Hi! My name is Sam!");
        user.updateGender(User.user_gender.Female);
        user.updatePreference(User.user_gender.Female);

        user1 = new User("Sam", "Smith", "samsmith@gmail.com", "password");
        user1.updateAnswer(Boolean.TRUE, 2, 0);
        user1.updateAnswer(Boolean.FALSE, 2, 1);
        user1.updateDateOfBirth(1999, 1, 25);
        user1.updateBio("Hi! My name is Sam!");
        user1.updateGender(User.user_gender.Female);
        user1.updatePreference(User.user_gender.Female);

        assertNotEquals(user, user1);

        System.out.println("testUserEqualsDiffAnswers complete");
    }

    public void testSetUserProfile()
    {
        System.out.println("Starting testSetUserProfile");

        user = new User("","","","");
        user.setUserProfile("testing bio", User.user_gender.Female, User.user_gender.Female, 1999, 1, 25);

        assertEquals("testing bio", user.getUserProfile().getBio());
        assertEquals(User.user_gender.Female, user.getUserProfile().getGender());
        assertEquals(User.user_gender.Female, user.getUserProfile().getGenderPreference());
        assertEquals("25/01/1999", user.getUserProfile().dateOfBirth());
        assertEquals(21, user.getUserProfile().getAge());

        System.out.println("testSetUserProfile complete");
    }

    public void testUpdateAnswer()
    {
        System.out.println("Starting testUpdateAnswer");

        user = new User("","","","");
        user.updateAnswer(Boolean.TRUE, 2, 2);
        user.updateAnswer(Boolean.TRUE, 2, 4);
        //this is out of bounds and should do nothing
        user.updateAnswer(Boolean.TRUE, 2, 12);

        assertEquals(Boolean.FALSE, user.getAnswers().get(0).getAnswer());
        assertEquals(Boolean.FALSE, user.getAnswers().get(1).getAnswer());
        assertEquals(Boolean.TRUE, user.getAnswers().get(2).getAnswer());
        assertEquals(Boolean.FALSE, user.getAnswers().get(3).getAnswer());
        assertEquals(Boolean.TRUE, user.getAnswers().get(4).getAnswer());

        System.out.println("testUpdateAnswer complete");
    }

    public void testUpdateAllAnswers()
    {
        System.out.println("Starting testUpdateAllAnswers");

        user = new User("","","","");
        user.updateAllAnswers(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,2,2,2,2,2);

        assertEquals(Boolean.TRUE, user.getAnswers().get(0).getAnswer());
        assertEquals(Boolean.TRUE, user.getAnswers().get(1).getAnswer());
        assertEquals(Boolean.TRUE, user.getAnswers().get(2).getAnswer());
        assertEquals(Boolean.TRUE, user.getAnswers().get(3).getAnswer());
        assertEquals(Boolean.TRUE, user.getAnswers().get(4).getAnswer());

        System.out.println("testUpdateAllAnswers complete");
    }

    public void testUpdateDateOfBirth()
    {
        System.out.println("Starting testUpdateDateOfBirth");

        user = new User("","","","");
        user.updateDateOfBirth(1988, 1, 12);

        assertEquals("12/01/1988", user.getUserProfile().dateOfBirth());
        assertEquals(32, user.getUserProfile().getAge());

        System.out.println("testUpdateDateOfBirth complete");
    }

    public void testUpdateBio()
    {
        System.out.println("Starting testUpdateBio");

        user = new User("","","","");
        user.updateBio("new test bio");

        assertEquals("new test bio", user.getUserProfile().getBio());

        System.out.println("testUpdateBio complete");
    }

}
