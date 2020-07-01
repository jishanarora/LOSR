package comp3350.losr.application;

public class Main {

    public static final String dbName = "Users";

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

}
