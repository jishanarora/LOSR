package comp3350.losr.persistence;

import java.util.ArrayList;

public class DataAccessStub {

    private String dbName;
    private String dbType = "stub";


    public DataAccessStub(String name)
    {
        this.dbName = name;
    }

    public void openConnection()
    {
        System.out.println("Opened connection to "+dbType+" database "+dbName);
    }

    public void closeConnection()
    {
        System.out.println("closed connection to "+dbType+" database "+dbName);
    }

}
