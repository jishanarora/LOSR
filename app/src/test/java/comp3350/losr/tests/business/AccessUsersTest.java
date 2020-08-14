package comp3350.losr.tests.business;

import junit.framework.TestCase;

import comp3350.losr.application.DatabaseService;
import comp3350.losr.business.AccessUsers;
import comp3350.losr.objects.User;
import comp3350.losr.tests.persistence.DataAccessStub;

public class AccessUsersTest extends TestCase {
    AccessUsers au;

    public void setUp() {
        DatabaseService.createDataAccess(new DataAccessStub("Stub"));
        au = new AccessUsers();
    }

    public void testCreate() {
        System.out.println("Starting testCreate");
        assertNotNull(au);
        System.out.println("testCreate complete");
    }

    public void testTryLogin() {
        System.out.println("Starting testTryLogin");

        assertNull(au.tryLogin("laurastubbs@gmail.com", "password"));
        assertNull(au.tryLogin("jessicafie@gmail.com", "password"));
        assertNull(au.tryLogin("marypoppins@gmail.com", "password"));
        assertNull(au.tryLogin("amykowall@gmail.com", "password"));
        assertNull(au.tryLogin("mbathie@gmail.com", "password"));

        System.out.println("testTryLogin complete");
    }

    public void testGetGenderedUsers() {
        System.out.println("Starting testGetGenderedUsers");

        assertEquals(4, au.getGenderedUsers().size());
        System.out.println(au.getGenderedUsers().get(0).getUserEmail());
        assertEquals(User.user_gender.Female, au.getGenderedUsers().get(0).getUserGender());
        assertEquals(User.user_gender.Male, au.getGenderedUsers().get(0).getUserGenderPreference());
        assertEquals(User.user_gender.Female, au.getGenderedUsers().get(1).getUserGender());
        assertEquals(User.user_gender.Male, au.getGenderedUsers().get(1).getUserGenderPreference());
        assertEquals(User.user_gender.Female, au.getGenderedUsers().get(2).getUserGender());
        assertEquals(User.user_gender.Male, au.getGenderedUsers().get(2).getUserGenderPreference());
        assertEquals(User.user_gender.Female, au.getGenderedUsers().get(3).getUserGender());
        assertEquals(User.user_gender.Male, au.getGenderedUsers().get(3).getUserGenderPreference());

        System.out.println("testGetGenderedUsers complete");
    }

    public void testAddAndDelete() {
        System.out.println("Starting testAddAndDelete");

        User test = new User("", "", "", "");
        test.setUserProfile("", User.user_gender.Female, User.user_gender.Male, 1, 1, 1, false);
        au.addUser(test);

        //shouldn't increase to 5 because adding a user would mean they are registering, when you register you are set as the currentUser
        //so you would not be included in the list of potential matches. Also the currentUser is now female and there are only 4 males.
        assertEquals(4, au.getGenderedUsers().size());

        User test2 = new User("", "", "test", "");
        test2.setUserProfile("", User.user_gender.Male, User.user_gender.Female, 1, 1, 1, false);
        au.addUser(test2);

        //the currentUser is now male so there should be 5 females as we added 1 extra earlier
        assertEquals(5, au.getGenderedUsers().size());

        au.deleteUser(test);
        au.deleteUser(test2);

        //we now delete the first User we added which brings the female count back down to 4
        assertEquals(4, au.getGenderedUsers().size());

        System.out.println("testAddAndDelete complete");
    }

    public void testUpdate() {
        System.out.println("Starting testUpdate");

        User temp = au.getCurrentUser();
        temp.setUserLastName("new last name");
        au.updateUser(temp);

        assertEquals("new last name", au.getCurrentUser().getUserLastName());

        temp.setUserLastName("bathie");
        au.updateUser(temp);

        assertEquals("bathie", au.getCurrentUser().getUserLastName());

        System.out.println("testUpdate complete");
    }

    public void testGetSpecifiedUser() {
        System.out.println("Starting testGetSpecifiedUser");

        User test = au.getSpecificUser("marypoppins@gmail.com");

        assertEquals("marypoppins@gmail.com", test.getUserEmail());
        assertEquals("password", test.getUserPassword());
        assertEquals("Mary", test.getUserFirstName());
        assertEquals("Poppins", test.getUserLastName());

        System.out.println("testGetSpecifiedUser complete");
    }

    public void testGetSpecifiedUserFalse() {
        System.out.println("Starting testGetSpecifiedUserFalse");

        assertNull(au.getSpecificUser("marypoppns@gmail.com"));

        System.out.println("testGetSpecifiedUserFalse complete");
    }
}
