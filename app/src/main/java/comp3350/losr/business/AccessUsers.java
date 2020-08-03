package comp3350.losr.business;

import java.util.List;

import comp3350.losr.application.DatabaseService;
import comp3350.losr.application.Main;
import comp3350.losr.objects.User;
import comp3350.losr.persistence.DataAccess;

public class AccessUsers {
    private DataAccess dataAccess;

    public AccessUsers() {
        dataAccess = DatabaseService.getDataAccess(Main.dbName);
    }

    public List<User> getGenderedUsers() {
        return dataAccess.getGenderedUsers();
    }


    //Just a way to retrieve the current "Logged in" user
    public User getCurrentUser() {
        return dataAccess.getCurrentUser();
    }

    //returns null if something went wrong and it can't find that user, otherwise is returns the user with the
    //specified email
    public User getSpecificUser(String email) {
        return dataAccess.getSpecificUser(email);
    }

    //Call this method when a user is registering. Take the email, password, first name, and last name
    //that they enter and create a User object from it, then pass it to this method. If the registration is successful
    //the method will return a fresh User object will the information that was added to the db (all the default values).
    //If unsuccessful it returns null.
    //*** If the registration is successful the passed user will be automatically set as the current user.
    public User addUser(User newUser) {
        return dataAccess.addUser(newUser);
    }

    public void deleteUser(User delete) {
        dataAccess.deleteUser(delete);
    }

    //Updates the current user as you should only be able to update an account if you're currently
    //logged into it.
    //** Important: this means that you should call getCurrentUser, make changes to that object, then pass it
    //to updateUser.
    public void updateUser(User update) {
        dataAccess.updateUser(update);
    }

    //Call when a user is trying to login. pass what they enter for the email and password into this.
    //If that user exists it will return null. If the user does not exist an error message is returned.
    //*** If the user exists the database will automatically set that account as the current user.
    public String tryLogin(String email, String password) {
        return dataAccess.tryLogin(email, password);
    }
}
