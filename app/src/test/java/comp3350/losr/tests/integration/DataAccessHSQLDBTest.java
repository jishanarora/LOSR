package comp3350.losr.tests.integration;

import junit.framework.TestCase;

import comp3350.losr.application.Main;
import comp3350.losr.application.DatabaseService;

import comp3350.losr.persistence.DataAccess;
import comp3350.losr.tests.persistence.DataAccessTest;

public class DataAccessHSQLDBTest extends TestCase
{
    private static String dbname = Main.dbName;

    public DataAccessHSQLDBTest(String arg0)
    {
        super(arg0);
    }

    public void testDataAccess()
    {
        DataAccess dataAccess;

        DatabaseService.closeDataAccess();

        System.out.println("\nStarting Integration test DataAccess (using default DB)");

        DatabaseService.createDataAccess(dbname);
        dataAccess = DatabaseService.getDataAccess(dbname);

        DatabaseService.closeDataAccess();

        System.out.println("Finished Integration test DataAccess (using default DB)");
    }
}
