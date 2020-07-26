package comp3350.losr.tests.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.losr.objects.User;
import comp3350.losr.persistence.DataAccess;

import static comp3350.losr.business.CheckMatches.areGenderCompatible;

public class DataAccessStub implements DataAccess {

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
        currentUser = new User("Michael", "Bathie", "mbathie@gmail.com", "password");
        currentUser.setUserProfile("Hi", User.user_gender.Male, User.user_gender.Female, 1999, 1, 25);
        currentUser.updateAllAnswers(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE);
        users.add(currentUser);

        newUser = new User("John", "Doe", "johndoe@gmail.com", "password");
        newUser.setUserProfile("Hey", User.user_gender.Male, User.user_gender.Female, 1999, 8, 8);
        newUser.updateAllAnswers(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE);
        users.add(newUser);

        newUser = new User("mary", "poppins", "marypoppins@gmail.com", "password");
        newUser.setUserProfile("hello there", User.user_gender.Female, User.user_gender.Male, 1998, 11, 16);
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

    public User addUser(User newUser)
    {
        users.add(newUser);
        return newUser;
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

    public void updateUser(User update)
    {
        String email = update.getUserEmail();
        int spot = -1;

        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getUserEmail().equals(email))
            {
                spot = i;
                break;
            }
        }

        if(spot > 0)
        {
            users.set(spot, update);
        }
    }

    public String tryLogin(String userEmail, String userPassword)
    {
        String message = "Could not find an account with that email";

        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getUserEmail().equals(userEmail))
            {
                if(users.get(i).getUserPassword().equals(userPassword))
                {
                    currentUser = users.get(i);
                    message = null;
                }
                else
                {
                    message = "Incorrect password";
                }
                break;
            }
        }

        return message;
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
