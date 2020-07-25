package comp3350.losr.tests.persistence;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import comp3350.losr.application.Main;
import comp3350.losr.objects.User;
import comp3350.losr.persistence.DataAccess;
import comp3350.losr.persistence.DataAccessObject;

public class DataAccessTest extends TestCase
{
    private DataAccess dataAccess;

    public DataAccessTest(){}

    public void setUp() {
        // Use the following statements to run with the stub database:
        //dataAccess = new DataAccessStub("Stub");
        //dataAccess.openConnection("Stub");
        // or switch to the real database:
        dataAccess = new DataAccessObject(Main.dbName);
        dataAccess.openConnection(Main.getDBPathName());
    }

    public void testGetGenderedUsers()
    {
        System.out.println("Starting testGetGenderedUsers");

        List<User> test;

        test = dataAccess.getGenderedUsers();
        assertEquals(4, test.size());

        int spot = -1;
        for(int i = 0; i < test.size(); i++)
        {
            if(test.get(i).getUserEmail().equals("marypoppins@gmail.com"))
            {
                spot = i;
            }
        }

        ArrayList<Boolean> temp = new ArrayList<>();
        for(int i = 0; i < 5; i ++)
        {
            temp.add(Boolean.TRUE);
        }

        assertEquals("mary", test.get(spot).getUserFirstName());
        assertEquals("poppins", test.get(spot).getUserLastName());
        assertEquals("marypoppins@gmail.com", test.get(spot).getUserEmail());
        assertEquals("password", test.get(spot).getUserPassword());
        assertEquals("hello there", test.get(spot).getUserProfile().getBio());
        assertEquals(User.user_gender.Female, test.get(spot).getUserProfile().getGender());
        assertEquals(User.user_gender.Male, test.get(spot).getUserProfile().getGenderPreference());
        assertEquals("16/11/1998", test.get(spot).getUserProfile().dateOfBirth());
        assertEquals(temp, test.get(spot).getAnswers());

        System.out.println("testGetGenderedUsers complete");
    }

    public void testAddUser()
    {
        System.out.println("Starting testAddUser");

        dataAccess.addUser(new User("test","test","test","test"));

        System.out.println("testAddUser complete");
    }
}
