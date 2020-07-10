package comp3350.losr.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.losr.application.DatabaseService;
import comp3350.losr.application.Main;
import comp3350.losr.objects.User;
import comp3350.losr.persistence.DataAccessStub;

public class AccessUsers
{
    private DataAccessStub dataAccess;

    public AccessUsers()
    {
        dataAccess = (DataAccessStub) DatabaseService.getDataAccess(Main.dbName);
    }

    public List<User> getGenderedUsers()
    {
        return dataAccess.getGenderedUsers();
    }

    //Just a way to retrieve the current "Logged in" user
    public User getCurrentUser() {return dataAccess.getCurrentUser();}

    public void addUser(User newUser)
    {
        dataAccess.addUser(newUser);
    }
}
