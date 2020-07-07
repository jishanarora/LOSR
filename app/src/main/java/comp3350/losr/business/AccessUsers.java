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
    //private List<User> potentialMatches;

    public AccessUsers()
    {
        dataAccess = (DataAccessStub) DatabaseService.getDataAccess(Main.dbName);
    }

    public ArrayList<User> getUsers()
    {
        return dataAccess.getGenderedUsers();
    }
}
