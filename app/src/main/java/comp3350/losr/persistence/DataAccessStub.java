package comp3350.losr.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.losr.objects.User;

import static comp3350.losr.business.CheckMatches.areGenderCompatible;

public class DataAccessStub implements DataAccess{

    private String dbName;
    private String dbType = "stub";

    private ArrayList<User> users;
    private User currentUser;

    public DataAccessStub(String name)
    {
        this.dbName = name;
    }

    public void openConnection(String path)
    {
        User newUser;

        users = new ArrayList<>();

        //This is the placeholder user that "you are" when you use the app
        currentUser = new User("Sam", "Green", "samgreen@gamil.com", "password");
        currentUser.setUserProfile("Hey", User.user_gender.Male, User.user_gender.Female, 1997, 7, 20);
        currentUser.updateAllAnswers(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);

        newUser = new User("John", "Doe", "johndoe@gmail.com", "password");
        newUser.setUserProfile("Hey", User.user_gender.Male, User.user_gender.Female, 1999, 8, 8);
        newUser.updateAllAnswers(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE);
        users.add(newUser);

        newUser = new User("Mary", "Poppins", "marypoppins@gmail.com", "password");
        newUser.setUserProfile("Hello there :)", User.user_gender.Female, User.user_gender.Female, 1998, 11, 16);
        newUser.updateAllAnswers(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE);
        users.add(newUser);

        newUser = new User("Gary", "Chalmers", "garychalmers@gmail.com", "password");
        newUser.setUserProfile("Hey", User.user_gender.Male, User.user_gender.Female, 1998, 4, 13);
        newUser.updateAllAnswers(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
        users.add(newUser);

        newUser = new User("Sean", "Lett", "seanlett@gmail.com", "password");
        newUser.setUserProfile("Hey", User.user_gender.Male, User.user_gender.Female, 1999, 9, 13);
        newUser.updateAllAnswers(Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
        users.add(newUser);

        newUser = new User("Laura", "Stubbs", "laurastubbs@gmail.com", "password");
        newUser.setUserProfile("Hi", User.user_gender.Female, User.user_gender.Male, 2001, 9, 22);
        newUser.updateAllAnswers(Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
        users.add(newUser);

        newUser = new User("Jessica", "Fie", "jessicafie@gmail.com", "password");
        newUser.setUserProfile("Hi", User.user_gender.Female, User.user_gender.Male, 2000, 5, 15);
        newUser.updateAllAnswers(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE);
        users.add(newUser);

        newUser = new User("Amy", "Kowall", "amykowall@gmail.com", "password");
        newUser.setUserProfile("Yo", User.user_gender.Female, User.user_gender.Male, 1999, 1, 2);
        newUser.updateAllAnswers(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE);
        users.add(newUser);

        System.out.println("Opened connection to "+dbType+" database "+dbName);
    }

    public User getCurrentUser(){return currentUser;}

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

    public List<User> getUsers()
    {
        return null;
    }

    public List<User> getGenderedUsers(){
        ArrayList<User> gendered = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if(!users.get(i).equals(currentUser) && areGenderCompatible(currentUser, users.get(i))) {
                gendered.add(users.get(i));
            }
        }

        return gendered;
    }

    public void closeConnection()
    {
        System.out.println("closed connection to "+dbType+" database "+dbName);
    }

}
