package comp3350.losr.persistence;

import java.util.ArrayList;

import comp3350.losr.objects.User;

public class DataAccessStub {

    private String dbName;
    private String dbType = "stub";

    private ArrayList<User> users;

    public DataAccessStub(String name)
    {
        this.dbName = name;
    }

    public void openConnection()
    {
        User newUser;

        users = new ArrayList<>();

        newUser = new User("John", "Doe", "johndoe@gmail.com", "password", new ArrayList<Boolean>());
        users.add(newUser);
        newUser = new User("Mary", "Poppins", "marypoppins@gmail.com", "password", new ArrayList<Boolean>());
        users.add(newUser);
        newUser = new User("Gary", "Chalmers", "garychalmers@gmail.com", "password", new ArrayList<Boolean>());
        users.add(newUser);

        newUser = new User("Sean", "Lett", "seanlett@gmail.com", "password", new ArrayList<Boolean>());
        users.add(newUser);
        newUser = new User("Laura", "Stubbs", "laurastubbs@gmail.com", "password", new ArrayList<Boolean>());
        users.add(newUser);

        System.out.println("Opened connection to "+dbType+" database "+dbName);
    }

    public void addUser(User newUser)
    {
        users.add(newUser);
    }

    public void deleteUser(User selectedUser)
    {
        int index;

        index = users.indexOf(selectedUser);
        if(index >= 0)
        {
            users.remove(index);
        }
    }

    public void closeConnection()
    {
        System.out.println("closed connection to "+dbType+" database "+dbName);
    }

}
