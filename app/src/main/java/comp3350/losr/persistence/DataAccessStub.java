package comp3350.losr.persistence;

import java.util.ArrayList;

import comp3350.losr.objects.User;

public class DataAccessStub {

    private String dbName;
    private String dbType = "stub";


    public DataAccessStub(String name)
    {
        this.dbName = name;
    }

    public void openConnection()
    {
        User newUser;
        ArrayList<User> stub = new ArrayList<User>();

        newUser = new User("John", "Doe", "johndoe@gmail.com", new ArrayList<Boolean>());
        stub.add(newUser);
        newUser = new User("Mary", "Poppins", "marypoppins@gmail.com", new ArrayList<Boolean>());
        stub.add(newUser);
        newUser = new User("Gary", "Chalmers", "garychalmers@gmail.com", new ArrayList<Boolean>());
        stub.add(newUser);
        newUser = new User("Sean", "Lett", "seanlett@gmail.com", new ArrayList<Boolean>());
        stub.add(newUser);
        newUser = new User("Laura", "Stubbs", "laurastubbs@gmail.com", new ArrayList<Boolean>());
        stub.add(newUser);

        System.out.println("Opened connection to "+dbType+" database "+dbName);
    }

    public void closeConnection()
    {
        System.out.println("closed connection to "+dbType+" database "+dbName);
    }

}
