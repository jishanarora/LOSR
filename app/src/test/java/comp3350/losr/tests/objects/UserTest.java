package comp3350.losr.tests.objects;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.losr.objects.User;

public class UserTest extends TestCase
{
    public void testUserEmpty()
    {
        User user;

        System.out.println("Starting testUserEmpty");

        user = new User("", "", "", new ArrayList<Boolean>());

        assertNotNull(user);
        assertEquals("", user.getUserFirstName());
        assertEquals("", user.getUserLastName());
        assertEquals("", user.getUserEmail());
        assertEquals(0, user.getAnswers().size());

        System.out.println("testUserEmpty completed");
    }

    public void testUserAverage()
    {
        User user;

        System.out.println("Starting testUserAverage");

        user = new User("John", "Doe", "johndoe@gmail.com", new ArrayList<Boolean>());
        user.updateAnswerList(Boolean.TRUE, 0);
        user.updateAnswerList(Boolean.TRUE, 1);
        user.updateAnswerList(Boolean.FALSE, 2);
        ArrayList<Boolean> userAnswers = user.getAnswers();

        assertNotNull(user);
        assertEquals("John", user.getUserFirstName());
        assertEquals("Doe", user.getUserLastName());
        assertEquals("johndoe@gmail.com", user.getUserEmail());
        assertTrue(userAnswers.get(1));
        assertFalse(userAnswers.get(2));
        assertEquals(3, userAnswers.size());

        System.out.println("testUserAverage completed");
    }

    public void testUserAverage2()
    {
        User user;

        System.out.println("Starting testUserAverage2");

        user = new User("Mary", "Poppins", "marypoppins@gmail.com", new ArrayList<Boolean>());
        user.updateAnswerList(Boolean.TRUE, 0);
        user.updateAnswerList(Boolean.FALSE, 1);
        user.updateAnswerList(Boolean.FALSE, 2);
        user.updateAnswerList(Boolean.TRUE, 3);
        ArrayList<Boolean> userAnswers = user.getAnswers();

        assertNotNull(user);
        assertEquals("Mary", user.getUserFirstName());
        assertEquals("Poppins", user.getUserLastName());
        assertEquals("marypoppins@gmail.com", user.getUserEmail());
        assertTrue(userAnswers.get(0));
        assertFalse(userAnswers.get(2));
        assertTrue(userAnswers.get(3));
        assertEquals(4, userAnswers.size());

        System.out.println("testUserAverage2 completed");
    }

    public void testUserAverage3()
    {
        User user;

        System.out.println("Starting testUserAverage3");

        user = new User("Sam", "Smith", "samsmith@gmail.com", new ArrayList<Boolean>());
        user.updateAnswerList(Boolean.FALSE, 0);
        user.updateAnswerList(Boolean.FALSE, 1);
        ArrayList<Boolean> userAnswers = user.getAnswers();

        assertNotNull(user);
        assertEquals("Sam", user.getUserFirstName());
        assertEquals("Smith", user.getUserLastName());
        assertEquals("samsmith@gmail.com", user.getUserEmail());
        assertFalse(userAnswers.get(0));
        assertFalse(userAnswers.get(1));
        assertEquals(2, userAnswers.size());

        System.out.println("testUserAverage3 completed");
    }

}
