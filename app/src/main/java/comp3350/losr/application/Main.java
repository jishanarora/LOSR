package comp3350.losr.application;

public class Main
{

    public static final String dbName = "Users";
    public static String dbPathName = "database/Users";

    public static void main(String[] args)
    {
        startUp();

        shutDown();

        System.out.println("Application closed");
    }

    public static void startUp()
    {
        DatabaseService.createDataAccess(dbName);
    }

    public static void shutDown()
    {
        DatabaseService.closeDataAccess();
    }

    public static String getDBPathName() {
        if (dbPathName == null)
            return dbName;
        else
            return dbPathName;
    }

    public static void setDBPathName(String pathName) {
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    }

}
