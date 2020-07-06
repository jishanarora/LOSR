package comp3350.losr.persistence;

import java.util.ArrayList;

import comp3350.losr.objects.User;

public class DataAccessStub {

    private String dbName;
    private String dbType = "stub";

    private ArrayList<User> users;
    private User currentUser;

    public DataAccessStub(String name)
    {
        this.dbName = name;
    }

    public void openConnection()
    {
        User newUser;

        users = new ArrayList<>();

        newUser = new User("John", "Doe", "johndoe@gmail.com", "password", new ArrayList<Boolean>());
        newUser.setUserProfile(20, "Hey", User.user_gender.Male, User.user_gender.Female, 1999, 8, 8);
        users.add(newUser);

        newUser = new User("Mary", "Poppins", "marypoppins@gmail.com", "password", new ArrayList<Boolean>());
        newUser.setUserProfile(21, "Hello there :)", User.user_gender.Female, User.user_gender.Female, 1998, 11, 16);
        users.add(newUser);

        newUser = new User("Gary", "Chalmers", "garychalmers@gmail.com", "password", new ArrayList<Boolean>());
        newUser.setUserProfile(22, "Hey", User.user_gender.Male, User.user_gender.Female, 1998, 4, 13);
        users.add(newUser);

        newUser = new User("Sean", "Lett", "seanlett@gmail.com", "password", new ArrayList<Boolean>());
        newUser.setUserProfile(20, "Hey", User.user_gender.Male, User.user_gender.Female, 1999, 9, 13);
        users.add(newUser);

        newUser = new User("Laura", "Stubbs", "laurastubbs@gmail.com", "password", new ArrayList<Boolean>());
        newUser.setUserProfile(18, "Hi", User.user_gender.Female, User.user_gender.Male, 2001, 9, 22);
        users.add(newUser);

        System.out.println("Opened connection to "+dbType+" database "+dbName);
    }

    public User getCurrentUser(){return currentUser;}

    public void addUser(User newUser)
    {
        users.add(newUser);
        currentUser = newUser;
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

    public ArrayList<User> getGenderedUsers(User.user_gender gender){
        ArrayList<User> genderedUsers = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserProfile().getGender() == gender){
                genderedUsers.add(users.get(i));
            }
        }

        return genderedUsers;
    }

    public void closeConnection()
    {
        System.out.println("closed connection to "+dbType+" database "+dbName);
    }

}
