package comp3350.losr.tests.business;

import junit.framework.TestCase;

import comp3350.losr.application.DatabaseService;
import comp3350.losr.business.AccessUsers;
import comp3350.losr.objects.User;
import comp3350.losr.tests.persistence.DataAccessStub;

public class AccessUsersTest extends TestCase
{
    AccessUsers au;

    public void setUp()
    {
        DatabaseService.createDataAccess(new DataAccessStub("Stub"));
        au = new AccessUsers();
    }

    public void testCreate()
    {
        System.out.println("Starting testCreate");
        assertNotNull(au);
        System.out.println("testCreate complete");
    }

    public void testTryLogin()
    {
        System.out.println("Starting testTryLogin");

        assertNull(au.tryLogin("laurastubbs@gmail.com", "password"));
        assertNull(au.tryLogin("jessicafie@gmail.com", "password"));
        assertNull(au.tryLogin("marypoppins@gmail.com", "password"));
        assertNull(au.tryLogin("amykowall@gmail.com", "password"));
        assertNull(au.tryLogin("mbathie@gmail.com", "password"));

        System.out.println("testTryLogin complete");
    }

    public void testGetGenderedUsers()
    {
        System.out.println("Starting testGetGenderedUsers");

        assertEquals(4, au.getGenderedUsers().size());
        System.out.println(au.getGenderedUsers().get(0).getUserEmail());
        assertEquals(User.user_gender.Female, au.getGenderedUsers().get(0).getUserProfile().getGender());
        assertEquals(User.user_gender.Male, au.getGenderedUsers().get(0).getUserProfile().getGenderPreference());
        assertEquals(User.user_gender.Female, au.getGenderedUsers().get(1).getUserProfile().getGender());
        assertEquals(User.user_gender.Male, au.getGenderedUsers().get(1).getUserProfile().getGenderPreference());
        assertEquals(User.user_gender.Female, au.getGenderedUsers().get(2).getUserProfile().getGender());
        assertEquals(User.user_gender.Male, au.getGenderedUsers().get(2).getUserProfile().getGenderPreference());
        assertEquals(User.user_gender.Female, au.getGenderedUsers().get(3).getUserProfile().getGender());
        assertEquals(User.user_gender.Male, au.getGenderedUsers().get(3).getUserProfile().getGenderPreference());

        System.out.println("testGetGenderedUsers complete");
    }

    public void testAddAndDelete()
    {
        System.out.println("Starting testAddAndDelete");

        User test = new User("","","","");
        test.setUserProfile("", User.user_gender.Female, User.user_gender.Male, 1,1,1);
        au.addUser(test);

        assertEquals(5, au.getGenderedUsers().size());

        au.deleteUser(test);

        assertEquals(4, au.getGenderedUsers().size());

        System.out.println("testAddAndDelete complete");
    }

    public void testUpdate()
    {
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
}
