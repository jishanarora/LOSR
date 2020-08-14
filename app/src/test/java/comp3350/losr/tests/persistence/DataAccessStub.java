package comp3350.losr.tests.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.losr.objects.Match;
import comp3350.losr.objects.Report;
import comp3350.losr.objects.User;
import comp3350.losr.persistence.DataAccess;

import static comp3350.losr.business.CheckMatches.areGenderCompatible;

public class DataAccessStub implements DataAccess {

    private String dbName;
    private String dbType = "stub";

    private ArrayList<User> users;
    private ArrayList<Report> reports = new ArrayList<>();
    private ArrayList<Match> matches = new ArrayList<>();
    private User currentUser;

    public DataAccessStub(String name) {
        this.dbName = name;
    }

    public void openConnection(String path) {
        User newUser;

        users = new ArrayList<>();

        //This is the placeholder user that "you are" when you use the app
        currentUser = new User("Michael", "Bathie", "mbathie@gmail.com", "password");
        currentUser.setUserProfile("Hi", User.user_gender.Male, User.user_gender.Female, 1999, 1, 25, true);
        currentUser.setUserAllAnswers(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, 5, 5, 5, 2, 1);
        users.add(currentUser);

        newUser = new User("John", "Doe", "johndoe@gmail.com", "password");
        newUser.setUserProfile("Hey", User.user_gender.Male, User.user_gender.Female, 1999, 8, 8, true);
        newUser.setUserAllAnswers(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, 1, 2, 3, 4, 2);
        users.add(newUser);

        newUser = new User("Mary", "Poppins", "marypoppins@gmail.com", "password");
        newUser.setUserProfile("hello there", User.user_gender.Female, User.user_gender.Male, 1998, 11, 16, true);
        newUser.setUserAllAnswers(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, 1, 2, 5, 5, 2);
        users.add(newUser);

        newUser = new User("Gary", "Chalmers", "garychalmers@gmail.com", "password");
        newUser.setUserProfile("Hey", User.user_gender.Male, User.user_gender.Female, 1998, 4, 13, false);
        newUser.setUserAllAnswers(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, 1, 3, 2, 2, 5);
        users.add(newUser);

        newUser = new User("Sean", "Lett", "seanlett@gmail.com", "password");
        newUser.setUserProfile("Hey", User.user_gender.Male, User.user_gender.Female, 1999, 9, 13, false);
        newUser.setUserAllAnswers(Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, 1, 2, 3, 2, 5);
        users.add(newUser);

        newUser = new User("Laura", "Stubbs", "laurastubbs@gmail.com", "password");
        newUser.setUserProfile("Hi", User.user_gender.Female, User.user_gender.Male, 2001, 9, 22, false);
        newUser.setUserAllAnswers(Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, 4, 2, 1, 3, 2);
        users.add(newUser);

        newUser = new User("Becca", "Stibs", "beccastibs@gmail.com", "password");
        newUser.setUserProfile("Hi", User.user_gender.Female, User.user_gender.Male, 2001, 9, 22, true);
        newUser.setUserAllAnswers(Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, 4, 2, 1, 3, 2);
        users.add(newUser);

        newUser = new User("Jessica", "Fie", "jessicafie@gmail.com", "password");
        newUser.setUserProfile("Hi", User.user_gender.Female, User.user_gender.Male, 2000, 5, 15, false);
        newUser.setUserAllAnswers(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, 2, 1, 2, 3, 5);
        users.add(newUser);

        newUser = new User("Amy", "Kowall", "amykowall@gmail.com", "password");
        newUser.setUserProfile("Yo", User.user_gender.Female, User.user_gender.Male, 1999, 1, 2, true);
        newUser.setUserAllAnswers(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, 3, 1, 5, 2, 4);
        users.add(newUser);

        matches.add(new Match(currentUser, getSpecificUser("laurastubbs@gmail.com")));
        matches.add(new Match(currentUser, getSpecificUser("jessicafie@gmail.com")));
        matches.add(new Match(getSpecificUser("laurastubbs@gmail.com"), currentUser));
        matches.add(new Match(getSpecificUser("laurastubbs@gmail.com"), getSpecificUser("seanlett@gmail.com")));
        matches.add(new Match(getSpecificUser("garychalmers@gmail.com"), getSpecificUser("marypoppins@gmail.com")));
        matches.add(new Match(getSpecificUser("garychalmers@gmail.com"), getSpecificUser("jessicafie@gmail.com")));
        matches.add(new Match(getSpecificUser("garychalmers@gmail.com"), getSpecificUser("amykowall@gmail.com")));
        matches.add(new Match(getSpecificUser("amykowall@gmail.com"), currentUser));
        matches.add(new Match(getSpecificUser("amykowall@gmail.com"), getSpecificUser("johndoe@gmail.com")));
        matches.add(new Match(getSpecificUser("johndoe@gmail.com"), getSpecificUser("jessicafie@gmail.com")));
        matches.add(new Match(getSpecificUser("johndoe@gmail.com"), getSpecificUser("amykowall@gmail.com")));
        matches.add(new Match(getSpecificUser("seanlett@gmail.com"), getSpecificUser("laurastubbs@gmail.com")));

        System.out.println("Opened connection to " + dbType + " database " + dbName);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public User getSpecificUser(String email) {
        User specifiedUser = null;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserEmail().equals(email)) {
                specifiedUser = users.get(i);
                break;
            }
        }
        return specifiedUser;
    }

    public User addUser(User newUser) {
        users.add(newUser);
        currentUser = newUser;
        return newUser;
    }

    public void deleteUser(User selectedUser) {
        int index;

        index = users.indexOf(selectedUser);
        if (index >= 0) {
            users.remove(index);
        }
    }

    public void updateUser(User update) {
        String email = update.getUserEmail();
        int spot = -1;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserEmail().equals(email)) {
                spot = i;
                break;
            }
        }

        if (spot > 0) {
            users.set(spot, update);
        }
    }

    public String tryLogin(String userEmail, String userPassword) {
        String message = "Could not find an account with that email";

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserEmail().equals(userEmail)) {
                if (users.get(i).getUserPassword().equals(userPassword)) {
                    currentUser = users.get(i);
                    message = null;
                } else {
                    message = "Incorrect password";
                }
                break;
            }
        }

        return message;
    }

    public List<User> getGenderedUsers() {
        ArrayList<User> gendered = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if (!users.get(i).equals(currentUser) && areGenderCompatible(currentUser, users.get(i))) {
                gendered.add(users.get(i));
            }
        }

        return gendered;
    }

    public void report(String reportee) {
        reports.add(new Report(currentUser.getUserEmail(), reportee));
    }

    public List<String> getReports() {
        List<String> currentUserReports = new ArrayList<>();
        String currentUserEmail = currentUser.getUserEmail();
        String userCheck;

        for(int i = 0; i < reports.size(); i++) {
            userCheck = reports.get(i).getReporter();

            if(userCheck.equals(currentUserEmail)) {
                currentUserReports.add(reports.get(i).getReportee());
            }
        }

        return currentUserReports;
    }

    public void clearReports(){}

    public void newMatch(String match){
        matches.add(new Match(currentUser, getSpecificUser(match)));
    }

    public boolean checkMatch(String match) {
        boolean matchExists = false;
        Match current;

        for(int i = 0; i < matches.size(); i++) {
            current = matches.get(i);

            if(current.getCurrentUser().getUserEmail().equals(match) && current.getMatchedUser().getUserEmail().equals(currentUser.getUserEmail())) {
                matchExists = true;
                break;
            }
        }
        return matchExists;
    }

    public boolean checkMatchExists(String match) {
        boolean matchExists = false;
        Match current;

        for(int i = 0; i < matches.size(); i++) {
            current = matches.get(i);

            if(current.getCurrentUser().getUserEmail().equals(currentUser.getUserEmail()) && current.getMatchedUser().getUserEmail().equals(match)) {
                matchExists = true;
                break;
            }
        }
        return matchExists;
    }

    public void changeBlindMode(boolean blindMode) {
        currentUser.setUserMode(blindMode);
    }

    public void closeConnection() {
        System.out.println("closed connection to " + dbType + " database " + dbName);
    }

}
