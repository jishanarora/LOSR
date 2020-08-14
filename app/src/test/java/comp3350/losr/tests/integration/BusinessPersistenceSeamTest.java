package comp3350.losr.tests.integration;

import junit.framework.TestCase;

import java.util.List;

import comp3350.losr.application.DatabaseService;
import comp3350.losr.application.Main;
import comp3350.losr.business.AccessMatches;
import comp3350.losr.business.AccessReports;
import comp3350.losr.business.AccessUsers;
import comp3350.losr.objects.Match;
import comp3350.losr.objects.User;

public class BusinessPersistenceSeamTest extends TestCase {

    public BusinessPersistenceSeamTest(String arg0) {
        super(arg0);
    }

    public void testAccessUsers1() {
        AccessUsers au;
        User user;
        String result;
        List<User> genderedUsers;

        DatabaseService.closeDataAccess();

        System.out.println("\nStarting Integration test of AccessUsers1 to persistence");

        DatabaseService.createDataAccess(Main.dbName);

        au = new AccessUsers();

        //get the current user
        user = au.getCurrentUser();
        assertNotNull(user);
        result = au.tryLogin(user.getUserEmail(), user.getUserPassword());
        assertNull(result);

        assertEquals("Michael", user.getUserFirstName());
        assertEquals("Bathie", user.getUserLastName());
        assertEquals("mbathie@gmail.com", user.getUserEmail());
        assertEquals("password", user.getUserPassword());
        assertEquals("hi", user.getUserBio());
        assertEquals(User.user_gender.Male, user.getUserGender());
        assertEquals(User.user_gender.Female, user.getUserGenderPreference());
        assertEquals("25/01/1999", user.getUserDateOfBirth());
        assertTrue(user.getUserAnswers().get(0).getAnswer());
        assertFalse(user.getUserAnswers().get(1).getAnswer());
        assertFalse(user.getUserAnswers().get(2).getAnswer());
        assertTrue(user.getUserAnswers().get(3).getAnswer());
        assertTrue(user.getUserAnswers().get(4).getAnswer());

        //update the current user
        user.setUserFirstName("Mike");
        au.updateUser(user);
        user = au.getCurrentUser();
        assertEquals("Mike", user.getUserFirstName());

        user.setUserFirstName("Michael");
        au.updateUser(user);
        user = au.getCurrentUser();
        assertEquals("Michael", user.getUserFirstName());

        //get users whose gender match with the current user
        genderedUsers = au.getGenderedUsers();
        assertNotNull(genderedUsers);
        assertEquals(genderedUsers.get(0).getUserGender(), user.getUserGenderPreference());
        assertEquals(genderedUsers.get(1).getUserGender(), user.getUserGenderPreference());
        assertEquals(genderedUsers.get(2).getUserGender(), user.getUserGenderPreference());
        assertEquals(genderedUsers.get(3).getUserGender(), user.getUserGenderPreference());

        DatabaseService.closeDataAccess();

        System.out.println("Finished Integration test of AccessUsers1 to persistence");
    }

    public void testAccessUsers2() {
        AccessUsers au;
        User user, temp;
        String result;

        DatabaseService.closeDataAccess();

        System.out.println("\nStarting Integration test of AccessUsers2 to persistence");

        DatabaseService.createDataAccess(Main.dbName);

        au = new AccessUsers();

        user = au.getSpecificUser("jessicafie@gmail.com");
        assertNotNull(user);
        temp = user;

        result = au.tryLogin("jessicafie@gmail.com", "password");
        assertNull(result);

        assertEquals("jessicafie@gmail.com", user.getUserEmail());
        assertEquals("Jessica", user.getUserFirstName());
        assertEquals("Fie", user.getUserLastName());
        assertEquals("password", user.getUserPassword());
        assertEquals("hi", user.getUserBio());
        assertEquals(User.user_gender.Female, user.getUserGender());
        assertEquals(User.user_gender.Male, user.getUserGenderPreference());
        assertEquals("15/05/2000", user.getUserDateOfBirth());
        assertFalse(user.getUserAnswers().get(0).getAnswer());
        assertFalse(user.getUserAnswers().get(1).getAnswer());
        assertFalse(user.getUserAnswers().get(2).getAnswer());
        assertTrue(user.getUserAnswers().get(3).getAnswer());
        assertTrue(user.getUserAnswers().get(4).getAnswer());

        au.deleteUser(user);
        user = au.getSpecificUser("jessicafie@gmail.com");
        assertNull(user);

        result = au.tryLogin("jessicafie@gmail.com", "password");
        assertEquals("Could not find an account with that email", result);

        user = temp;

        user = au.addUser(user);
        assertNotNull(user);

        result = au.tryLogin("jessicafie@gmail.com", "password");
        assertNull(result);

        assertEquals("jessicafie@gmail.com", user.getUserEmail());
        assertEquals("Jessica", user.getUserFirstName());
        assertEquals("Fie", user.getUserLastName());
        assertEquals("password", user.getUserPassword());

        user.setUserProfile("hi", User.user_gender.Female, User.user_gender.Male, 2000, 5, 15, false);
        user.setUserAllAnswers(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,
                2, 1, 2, 3, 5);

        au.updateUser(user);
        user = au.getSpecificUser("jessicafie@gmail.com");

        assertEquals("hi", user.getUserBio());
        assertEquals(User.user_gender.Female, user.getUserGender());
        assertEquals(User.user_gender.Male, user.getUserGenderPreference());
        assertEquals("15/05/2000", user.getUserDateOfBirth());
        assertFalse(user.getUserAnswers().get(0).getAnswer());
        assertFalse(user.getUserAnswers().get(1).getAnswer());
        assertFalse(user.getUserAnswers().get(2).getAnswer());
        assertTrue(user.getUserAnswers().get(3).getAnswer());
        assertTrue(user.getUserAnswers().get(4).getAnswer());

        DatabaseService.closeDataAccess();

        System.out.println("Finished Integration test of AccessUsers2 to persistence");
    }

    public void testAccessUsers3() {
        AccessUsers au;
        User user;
        String result;
        List<User> genderedUsers;

        DatabaseService.closeDataAccess();

        System.out.println("\nStarting Integration test of AccessUsers3 to persistence");

        DatabaseService.createDataAccess(Main.dbName);

        au = new AccessUsers();

        //get a user which does not exist
        user = au.getSpecificUser("noname@gmail.com");
        assertNull(user);

        result = au.tryLogin("noname@gmail.com", "password");
        assertEquals("Could not find an account with that email", result);

        //register for this user, and set it to the current user
        user = new User("No", "Name", "noname@gmail.com", "password");
        au.addUser(user);

        result = au.tryLogin("noname@gmail.com", "password");
        assertNull(result);

        user = au.getSpecificUser("noname@gmail.com");
        assertNotNull(user);

        assertEquals("No", user.getUserFirstName());
        assertEquals("Name", user.getUserLastName());
        assertEquals("noname@gmail.com", user.getUserEmail());
        assertEquals("password", user.getUserPassword());
        assertEquals("hi", user.getUserBio());
        assertEquals(User.user_gender.Losr, user.getUserGender());
        assertEquals(User.user_gender.Losr, user.getUserGenderPreference());
        assertEquals("00/00/0000", user.getUserDateOfBirth());
        assertFalse(user.getUserAnswers().get(2).getAnswer());
        assertFalse(user.getUserAnswers().get(1).getAnswer());
        assertFalse(user.getUserAnswers().get(0).getAnswer());
        assertFalse(user.getUserAnswers().get(3).getAnswer());
        assertFalse(user.getUserAnswers().get(4).getAnswer());

        //update this user
        user.setUserGender(User.user_gender.Female);
        user.setUserGenderPreference(User.user_gender.Male);
        au.updateUser(user);

        //get users whose gender match with the current user
        genderedUsers = au.getGenderedUsers();
        assertNotNull(genderedUsers);
        assertEquals(genderedUsers.get(0).getUserGender(), user.getUserGenderPreference());
        assertEquals(genderedUsers.get(1).getUserGender(), user.getUserGenderPreference());
        assertEquals(genderedUsers.get(2).getUserGender(), user.getUserGenderPreference());
        assertEquals(genderedUsers.get(3).getUserGender(), user.getUserGenderPreference());

        au.deleteUser(user);
        assertNull(au.getSpecificUser("noname@gmail.com"));

        result = au.tryLogin("noname@gmail.com", "password");
        assertEquals("Could not find an account with that email", result);

        DatabaseService.closeDataAccess();

        System.out.println("Finished Integration test of AccessUsers3 to persistence");
    }

    public void testAccessMatches() {

        DatabaseService.closeDataAccess();


        DatabaseService.createDataAccess(Main.dbName);


        AccessUsers au = new AccessUsers();
        AccessMatches am = new AccessMatches();
        List<Match> matchList;


        System.out.println("\nStarting Integration of testAccessMatches to persistence");

        au.tryLogin("mbathie@gmail.com", "password");
        assertEquals("mbathie@gmail.com", au.getCurrentUser().getUserEmail());

        matchList = am.getMatches();

        assertEquals(1, matchList.size());
        assertEquals(matchList.get(0).getCurrentUser().getUserEmail(), "mbathie@gmail.com");
        assertEquals(matchList.get(0).getMatchedUser().getUserEmail(), "laurastubbs@gmail.com");

        am.newMatch("amykowall@gmail.com");

        matchList = am.getMatches();

        assertEquals(2, matchList.size());

        am.deleteMatch("amykowall@gmail.com");

        System.out.println("\nFinished Integration of testAccessMatches to persistence");
    }

    public void testAccessMatchesBlind() {
        DatabaseService.closeDataAccess();

        DatabaseService.createDataAccess(Main.dbName);

        System.out.println("\nStarting Integration of testAccessMatchesBlind to persistence");

        AccessUsers au = new AccessUsers();
        AccessMatches am = new AccessMatches();
        List<Match> matchList;


        au.tryLogin("marypoppins@gmail.com", "password");
        assertEquals("marypoppins@gmail.com", au.getCurrentUser().getUserEmail());
        User temp = au.getCurrentUser();
        temp.setUserMode(Boolean.TRUE);
        au.updateUser(temp);
        am.newMatch("mbathie@gmail.com");

        au.tryLogin("mbathie@gmail.com", "password");
        assertEquals("mbathie@gmail.com", au.getCurrentUser().getUserEmail());
        temp = au.getCurrentUser();
        temp.setUserMode(Boolean.TRUE);
        au.updateUser(temp);

        am.newMatch("marypoppins@gmail.com");

        assertEquals(1, am.getMatches().size());
        assertEquals("marypoppins@gmail.com", am.getMatches().get(0).getMatchedUser().getUserEmail());

        am.deleteMatch("marypoppins@gmail.com");

        temp = au.getCurrentUser();
        temp.setUserMode(Boolean.FALSE);
        au.updateUser(temp);

        au.tryLogin("marypoppins@gmail.com", "password");
        assertEquals("marypoppins@gmail.com", au.getCurrentUser().getUserEmail());

        temp = au.getCurrentUser();
        temp.setUserMode(Boolean.FALSE);
        au.updateUser(temp);
        am.deleteMatch("mbathie@gmail.com");


        System.out.println("\nFinished Integration of testAccessMatchesBlind to persistence");

    }

    public void testAccessReports() {
        DatabaseService.closeDataAccess();


        DatabaseService.createDataAccess(Main.dbName);

        System.out.println("\nStarting Integration of testAccessReports to persistence");

        AccessUsers au = new AccessUsers();
        AccessMatches am = new AccessMatches();
        AccessReports ar = new AccessReports();
        List<Match> matchList;

        au.tryLogin("mbathie@gmail.com", "password");
        assertEquals("mbathie@gmail.com", au.getCurrentUser().getUserEmail());

        matchList = am.getMatches();

        assertEquals(1, matchList.size());
        assertEquals(matchList.get(0).getCurrentUser().getUserEmail(), "mbathie@gmail.com");
        assertEquals(matchList.get(0).getMatchedUser().getUserEmail(), "laurastubbs@gmail.com");

        ar.report("laurastubbs@gmail.com");

        assertEquals(1, ar.getReports().size());
        assertEquals(0, am.getMatches().size());

        ar.clearReports();

        assertEquals(0, ar.getReports().size());
        assertEquals(1, am.getMatches().size());

        assertEquals(am.getMatches().get(0).getCurrentUser().getUserEmail(), "mbathie@gmail.com");
        assertEquals(am.getMatches().get(0).getMatchedUser().getUserEmail(), "laurastubbs@gmail.com");


        System.out.println("\nFinished Integration of testAccessReports to persistence");
    }
}