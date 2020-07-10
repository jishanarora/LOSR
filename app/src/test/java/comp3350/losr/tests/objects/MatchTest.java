package comp3350.losr.tests.objects;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import comp3350.losr.application.Main;
import comp3350.losr.business.AccessMatches;
import comp3350.losr.objects.Match;
import comp3350.losr.objects.User;

public class MatchTest extends TestCase {

    public void testEmpty()
    {
        User user1 = new User("","","","",new ArrayList<Boolean>());
        User user2 = new User("","","","",new ArrayList<Boolean>());

        Match match = new Match(user1, user2);

        try
        {
            assertEquals(user1, match.getCurrentUser());
            fail();
        }
        catch(NullPointerException npe){}
    }

    public void testAverage()
    {
        User user1 = new User("firstName","lastName","email@email.com","password", new ArrayList<Boolean>());
        User user2 = new User("firstName1","lastName1","email1@email.com","password1",new ArrayList<Boolean>());
        user1.getAnswers().add(Boolean.FALSE);
        user1.getAnswers().add(Boolean.FALSE);
        user2.getAnswers().add(Boolean.FALSE);
        user2.getAnswers().add(Boolean.TRUE);
        user1.setUserProfile("bio here", User.user_gender.Female, User.user_gender.Male, 1999, 1, 25);
        user2.setUserProfile("bio here", User.user_gender.Male, User.user_gender.Male, 1998, 2, 22);

        Match match = new Match(user1, user2);

        assertEquals(user1, match.getCurrentUser());
        assertEquals(user2, match.getMatchedUser());
        assertEquals(50, match.getMatchPercent());
    }

}
