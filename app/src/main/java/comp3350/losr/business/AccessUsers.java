package comp3350.losr.business;

import java.util.List;

import comp3350.losr.application.DatabaseService;
import comp3350.losr.application.Main;
import comp3350.losr.objects.User;
import comp3350.losr.persistence.DataAccessObject;

public class AccessUsers
{
    private DataAccessObject dataAccess;

    public AccessUsers()
    {
        dataAccess = (DataAccessObject) DatabaseService.getDataAccess(Main.dbName);
    }

    public List<User> getGenderedUsers()
    {
        return dataAccess.getGenderedUsers();
    }

    //Just a way to retrieve the current "Logged in" user
    public User getCurrentUser() {return dataAccess.getCurrentUser();}

    public void setCurrentUser(User newUser) {dataAccess.setCurrentUser(newUser);}

    public void addUser(User newUser)
    {
        dataAccess.addUser(newUser);
    }

    public void deleteUser(User delete) {dataAccess.deleteUser(delete);}

    //Call when a user is trying to login. pass what they enter for the email and password into this.
    //If that user exists it will return a User object of that user. If the user does not exist null is returned.
    public User tryLogin(String email, String password)
    {
        return dataAccess.tryLogin(email, password);
    }
}
