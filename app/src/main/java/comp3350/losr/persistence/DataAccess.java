package comp3350.losr.persistence;

import java.util.List;

import comp3350.losr.objects.Report;
import comp3350.losr.objects.User;

public interface DataAccess {

    void openConnection(String string);

    void closeConnection();

    User getCurrentUser();

    User getSpecificUser(String email);

    User addUser(User newUser);

    void deleteUser(User selectedUser);

    void updateUser(User update);

    String tryLogin(String userEmail, String userPassword);

    List<User> getGenderedUsers();

    void report(String reportee);

    List<Report> getReports();

    void newMatch(String match);

    boolean checkMatch(String match);

    boolean checkMatchExists(String match);
}
