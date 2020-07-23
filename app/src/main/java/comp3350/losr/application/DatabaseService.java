package comp3350.losr.application;

import comp3350.losr.persistence.DataAccess;
import comp3350.losr.persistence.DataAccessObject;

public class DatabaseService {

    //Initially the database has nothing
    private static DataAccess dataService = null;

    //create and/or send db pointer to the caller
    public static DataAccess createDataAccess(String dbName)
    {
        //if our database is "empty"/not there
        if (dataService == null)
        {
            //create one
            dataService = new DataAccessObject(dbName);
            dataService.openConnection(Main.getDBPathName());
        }
        //return to whoever called it
        return dataService;
    }

    public static DataAccess createDataAccess(DataAccess newDataService)
    {
        if (dataService == null)
        {
            dataService = newDataService;
            dataService.openConnection(Main.getDBPathName());
        }
        return dataService;
    }

    public static DataAccess getDataAccess(String dbName)
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
