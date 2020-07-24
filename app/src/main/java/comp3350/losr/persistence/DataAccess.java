package comp3350.losr.persistence;

import java.util.List;

import comp3350.losr.objects.User;

public interface DataAccess {

    void openConnection(String string);

    void closeConnection();

    User addUser(User newUser);

    void deleteUser(User selectedUser);

    List<User> getGenderedUsers();

}
