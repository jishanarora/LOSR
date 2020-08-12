package comp3350.losr.tests.persistence;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import comp3350.losr.application.Main;
import comp3350.losr.objects.Question;
import comp3350.losr.objects.User;
import comp3350.losr.persistence.DataAccess;
import comp3350.losr.persistence.DataAccessObject;

public class DataAccessTest extends TestCase {
    private DataAccess dataAccess;

    public void setUp() {
        // Use the following statements to run with the stub database:
        //dataAccess = new DataAccessStub("Stub");
        //dataAccess.openConnection("Stub");
        // or switch to the real database:
        dataAccess = new DataAccessObject(Main.dbName);
        dataAccess.openConnection(Main.getDBPathName());
    }

    public void resetRealDB() {
        dataAccess = new DataAccessObject(Main.dbName);
        dataAccess.openConnection(Main.getDBPathName());
    }

    public static void dataAccessTest(DataAccess dataAccess) {
        DataAccessTest dataAccessTest = new DataAccessTest();
        dataAccessTest.dataAccess = dataAccess;
        dataAccessTest.testGetGenderedUsers();
        dataAccessTest.resetRealDB();
        dataAccessTest.testTryLoginSuccess();
        dataAccessTest.resetRealDB();
        dataAccessTest.testTryLoginWrongEmail();
        dataAccessTest.resetRealDB();
        dataAccessTest.testTryLoginWrongPassword();
        dataAccessTest.resetRealDB();
        dataAccessTest.testTryLoginBothWrong();
        dataAccessTest.resetRealDB();
        dataAccessTest.testAddUser();
        dataAccessTest.resetRealDB();
        dataAccessTest.testUpdateUser();
        dataAccessTest.resetRealDB();
        dataAccessTest.testDeleteUser();
        dataAccessTest.resetRealDB();
        dataAccessTest.testRegistration();
        dataAccessTest.resetRealDB();
        dataAccessTest.testLogin();
        dataAccessTest.resetRealDB();
        dataAccessTest.testGetSpecifiedUser();
        dataAccessTest.resetRealDB();
        dataAccessTest.testGetSpecifiedUserFalse();
        dataAccessTest.resetRealDB();
        dataAccessTest.testReport();
        dataAccessTest.resetRealDB();
        dataAccessTest.testNewMatch();
        dataAccessTest.resetRealDB();
        dataAccessTest.testCheckMatch();
        dataAccessTest.resetRealDB();
        dataAccessTest.testCheckMatchExists();
    }

    public void testGetGenderedUsers() {
        System.out.println("Starting testGetGenderedUsers");

        List<User> test;

        test = dataAccess.getGenderedUsers();
        assertEquals(4, test.size());

        int spot = -1;
        for (int i = 0; i < test.size(); i++) {
            if (test.get(i).getUserEmail().equals("marypoppins@gmail.com")) {
                spot = i;
            }
        }

        ArrayList<Boolean> temp = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            temp.add(Boolean.TRUE);
        }

        assertEquals("Mary", test.get(spot).getUserFirstName());
        assertEquals("Poppins", test.get(spot).getUserLastName());
        assertEquals("marypoppins@gmail.com", test.get(spot).getUserEmail());
        assertEquals("password", test.get(spot).getUserPassword());
        assertEquals("hello there", test.get(spot).getUserProfile().getBio());
        assertEquals(User.user_gender.Female, test.get(spot).getUserProfile().getGender());
        assertEquals(User.user_gender.Male, test.get(spot).getUserProfile().getGenderPreference());
        assertEquals("16/11/1998", test.get(spot).getUserProfile().dateOfBirth());
        assertEquals(temp.get(0), test.get(spot).getAnswers().get(0).getAnswer());
        assertEquals(temp.get(1), test.get(spot).getAnswers().get(1).getAnswer());
        assertEquals(temp.get(2), test.get(spot).getAnswers().get(2).getAnswer());
        assertEquals(temp.get(3), test.get(spot).getAnswers().get(3).getAnswer());
        assertEquals(temp.get(4), test.get(spot).getAnswers().get(4).getAnswer());

        System.out.println("testGetGenderedUsers complete");
    }

    public void testTryLoginSuccess() {
        System.out.println("Starting testTryLoginSuccess");
        assertNull(dataAccess.tryLogin("mbathie@gmail.com", "password"));
        System.out.println("testTryLoginSuccess complete");
    }

    public void testTryLoginWrongEmail() {
        System.out.println("Starting testTryLoginWrongEmail");
        assertEquals("Could not find an account with that email", dataAccess.tryLogin("mbathie@gmail.co", "password"));
        System.out.println("testTryLoginWrongEmail complete");
    }

    public void testTryLoginWrongPassword() {
        System.out.println("Starting testTryLoginWrongPassword");
        assertEquals("Incorrect password", dataAccess.tryLogin("mbathie@gmail.com", "pasword"));
        System.out.println("testTryLoginWrongPassword complete");
    }

    public void testTryLoginBothWrong() {
        System.out.println("Starting testTryLoginBothWrong");
        assertEquals("Could not find an account with that email", dataAccess.tryLogin("mbathie@gail.com", "passord"));
        System.out.println("testTryLoginBothWrong complete");
    }

    public void testAddUser() {
        System.out.println("Starting testAddUser");

        dataAccess.addUser(new User("test", "test", "test", "test"));
        assertNull(dataAccess.tryLogin("test", "test"));

        System.out.println("testAddUser complete");
    }

    public void testUpdateUser() {
        System.out.println("Starting testUpdateUser");

        User test = dataAccess.getCurrentUser();
        test.updateBio("new bio");
        test.updateGender(User.user_gender.Female);
        test.updateDateOfBirth(1998, 1, 25);
        test.updateAllAnswers(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, 2, 2, 2, 2, 2);
        dataAccess.updateUser(test);

        ArrayList<Question> temp = new ArrayList<>();
        temp.add(new Question(1, "tempQ1", Boolean.TRUE, 2));
        temp.add(new Question(2, "tempQ2", Boolean.FALSE, 2));
        temp.add(new Question(3, "tempQ3", Boolean.TRUE, 2));
        temp.add(new Question(4, "tempQ4", Boolean.TRUE, 2));
        temp.add(new Question(5, "tempQ5", Boolean.TRUE, 2));

        assertEquals("new bio", dataAccess.getCurrentUser().getUserProfile().getBio());
        assertEquals(User.user_gender.Female, dataAccess.getCurrentUser().getUserProfile().getGender());
        assertEquals(22, dataAccess.getCurrentUser().getUserProfile().getAge());
        assertEquals("25/01/1998", dataAccess.getCurrentUser().getUserProfile().dateOfBirth());
        assertEquals(temp.get(0).getAnswer(), dataAccess.getCurrentUser().getAnswers().get(0).getAnswer());
        assertEquals(temp.get(1).getAnswer(), dataAccess.getCurrentUser().getAnswers().get(1).getAnswer());
        assertEquals(temp.get(2).getAnswer(), dataAccess.getCurrentUser().getAnswers().get(2).getAnswer());
        assertEquals(temp.get(3).getAnswer(), dataAccess.getCurrentUser().getAnswers().get(3).getAnswer());
        assertEquals(temp.get(4).getAnswer(), dataAccess.getCurrentUser().getAnswers().get(4).getAnswer());

        System.out.println("testUpdateUser complete");
    }

    public void testDeleteUser() {
        System.out.println("Starting testDeleteUser");

        dataAccess.deleteUser(dataAccess.getCurrentUser());
        assertEquals("Could not find an account with that email", dataAccess.tryLogin("mbathie@gmail.com", "password"));

        System.out.println("testDeleteUser complete");
    }

    public void testRegistration() {
        System.out.println("Starting testRegistration");

        assertEquals(new User("testFirstName", "testLastName", "testEmail", "testPassword"), dataAccess.addUser(new User("testFirstName", "testLastName", "testEmail", "testPassword")));
        assertEquals(new User("testFirstName", "testLastName", "testEmail", "testPassword"), dataAccess.getCurrentUser());

        System.out.println("testRegistration complete");
    }

    public void testLogin() {
        System.out.println("Starting testLogin");

        assertNull(dataAccess.tryLogin("marypoppins@gmail.com", "password"));
        assertEquals("marypoppins@gmail.com", dataAccess.getCurrentUser().getUserEmail());

        System.out.println("testLogin complete");
    }

    public void testGetSpecifiedUser() {
        System.out.println("Starting testGetSpecifiedUser");

        User test = dataAccess.getSpecificUser("marypoppins@gmail.com");

        assertEquals("marypoppins@gmail.com", test.getUserEmail());
        assertEquals("password", test.getUserPassword());
        assertEquals("Mary", test.getUserFirstName());
        assertEquals("Poppins", test.getUserLastName());

        System.out.println("testGetSpecifiedUser complete");
    }

    public void testGetSpecifiedUserFalse() {
        System.out.println("Starting testGetSpecifiedUserFalse");

        assertNull(dataAccess.getSpecificUser("marypoppns@gmail.com"));

        System.out.println("testGetSpecifiedUserFalse complete");
    }

    public void testReport() {
        System.out.println("Starting testReport");

        dataAccess.report("marypoppins@gmail.com");
        assertEquals(1, dataAccess.getReports().size());
        assertEquals("marypoppins@gmail.com", dataAccess.getReports().get(0));

        dataAccess.report("amykowall@gmail.com");
        assertEquals(2, dataAccess.getReports().size());

        System.out.println("testReport complete");
    }

    public void testNewMatch() {
        System.out.println("Starting testNewMatch");

        dataAccess.newMatch("marypoppins@gmail.com");
        //mary poppins has not matched with anyone in the db so this will always fail
        assertFalse(dataAccess.checkMatch("marypoppins@gmail.com"));

        dataAccess.newMatch("amykowall@gmail.com");
        //amy kowall was matched with michael (the current user) but michael had yet to match back,
        //when the current user adds the new match it will then result with true for match.
        assertTrue(dataAccess.checkMatch("amykowall@gmail.com"));

        System.out.println("testNewMatch complete");
    }

    public void testCheckMatch() {
        System.out.println("Starting testCheckMatch");

        assertTrue(dataAccess.checkMatch("laurastubbs@gmail.com"));
        assertFalse(dataAccess.checkMatch("jessicafie@gmail.com"));

        System.out.println("testCheckMatch complete");
    }

    public void testCheckMatchExists() {
        System.out.println("Starting testCheckMatchExists");

        assertTrue(dataAccess.checkMatchExists("laurastubbs@gmail.com"));
        assertFalse(dataAccess.checkMatchExists("seanlett@gmail.com"));

        dataAccess.newMatch("marypoppins@gmail.com");
        assertTrue(dataAccess.checkMatchExists("marypoppins@gmail.com"));

        System.out.println("testCheckMatchExists Complete");
    }

    public void testChangeBlindMode() {
        System.out.println("Starting testChangeBlindMode");

        dataAccess.changeBlindMode(true);
        assertTrue(dataAccess.getCurrentUser().getUserProfile().getBlindMode());

        dataAccess.changeBlindMode(false);
        assertFalse(dataAccess.getCurrentUser().getUserProfile().getBlindMode());

        System.out.println("testChangeBlindMode complete");
    }
}
