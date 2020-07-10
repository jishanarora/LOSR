package comp3350.losr.application;

import comp3350.losr.persistence.DataAccessStub;

public class DatabaseService {

    //Initially the database has nothing
    private static DataAccessStub dataService = null;

    //create and/or send db pointer to the caller
    public static DataAccessStub createDataAccess(String dbName)
    {
        //if our database is "empty"/not there
        if (dataService == null)
        {
            //create one
            dataService = new DataAccessStub(dbName);
            dataService.openConnection();
        }
        //return to whoever called it
        return dataService;
    }

    public static DataAccessStub getDataAccess(String dbName)
    {
        if (dataService == null)
        {
            //should not be here before calling createDataAccess at least once
            System.out.println("tried to access data when you hadn't created any data to access");
            System.exit(1);
        }
        return dataService;
    }

    public static void closeDataAccess()
    {
        if (dataService != null)
        {
            dataService.closeConnection();
        }
        dataService = null;
    }
}
