package comp3350.losr.tests.objects;

import junit.framework.TestCase;

import comp3350.losr.objects.Match;
import comp3350.losr.objects.User;

public class MatchTest extends TestCase {

    public void testEmpty()
    {
        User user1 = new User("","","","");
        User user2 = new User("","","","");

        Match match = new Match(user1, user2);


        assertEquals(user1, match.getCurrentUser());
        assertEquals(user2, match.getMatchedUser());

    }

    public void testAverage()
    {
        User user1 = new User("firstName","lastName","email@email.com","password");
        User user2 = new User("firstName1","lastName1","email1@email.com","password1");
        user1.updateAnswer(Boolean.TRUE, 2, 2);
        user1.updateAnswer(Boolean.TRUE, 2, 3);
        user2.updateAnswer(Boolean.TRUE, 2, 2);
        user1.setUserProfile("bio here", User.user_gender.Female, User.user_gender.Male, 1999, 1, 25);
        user2.setUserProfile("bio here", User.user_gender.Male, User.user_gender.Male, 1998, 2, 22);

        Match match = new Match(user1, user2);

        assertEquals(user1, match.getCurrentUser());
        assertEquals(user2, match.getMatchedUser());
        assertEquals(80, match.getMatchPercent());
    }

    public void testFullMatch()
    {
        User user1 = new User("firstName","lastName","email@email.com","password");
        User user2 = new User("firstName1","lastName1","email1@email.com","password1");

        user1.setUserProfile("bio here", User.user_gender.Female, User.user_gender.Male, 1999, 1, 25);
        user2.setUserProfile("bio here", User.user_gender.Male, User.user_gender.Male, 1998, 2, 22);

        Match match = new Match(user1, user2);

        assertEquals(user1, match.getCurrentUser());
        assertEquals(user2, match.getMatchedUser());
        assertEquals(100, match.getMatchPercent());
    }

    public void test0Match()
    {
        User user1 = new User("firstName","lastName","email@email.com","password");
        User user2 = new User("firstName1","lastName1","email1@email.com","password1");

        user1.updateAllAnswers(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,2,2,2,2,2);

        user1.setUserProfile("bio here", User.user_gender.Female, User.user_gender.Male, 1999, 1, 25);
        user2.setUserProfile("bio here", User.user_gender.Male, User.user_gender.Male, 1998, 2, 22);

        Match match = new Match(user1, user2);

        assertEquals(user1, match.getCurrentUser());
        assertEquals(user2, match.getMatchedUser());
        assertEquals(0, match.getMatchPercent());
    }

}
