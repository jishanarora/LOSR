package comp3350.losr.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.losr.objects.User;

public class DataAccessObject implements DataAccess
{

    private Connection c1 = null;
    private Statement s1;
    private ResultSet rs1;

    private String dbName;
    private String dbType;

    private List<User> users;

    private String cmdString;

    public DataAccessObject(String dbName)
    {
        this.dbName = dbName;
    }

    public void openConnection(String dbPath)
    {

        String url;

        try
        {
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath;
            c1 = DriverManager.getConnection(url, "SA", "");
            s1 = c1.createStatement();

            if(c1 != null)
            {
                System.out.println("Successfully connected to the database");
            }
            else
            {
                System.out.println("Count not create database connection");
            }
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong when trying to connect to the database.");
            e.printStackTrace();
        }

        System.out.println("Opened connection to "+dbType+" database "+dbName+" --> "+dbPath);
    }

    public void closeConnection()
    {
        try
        {
            cmdString = "shutdown compact";
            rs1 = s1.executeQuery(cmdString);
            c1.close();
        }
        catch(Exception e)
        {
            System.out.println("Something went wrong when trying to close the database");
            e.printStackTrace();
        }

        System.out.println("closed connection to "+dbType+" database "+dbName);
    }

    public void addUser(User newUser){

    }

    public void deleteUser(User selectedUser)
    {

    }

    public User getCurrentUser()
    {
        return null;
    }

    public List<User> getUsers()
    {
        users = new ArrayList<>();

        String firstName;
        String lastName;

        try
        {
            cmdString = "SELECT * FROM USERS";
            rs1 = s1.executeQuery(cmdString);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            while(rs1.next())
            {
                firstName = rs1.getString("fName");
                lastName = rs1.getString("lName");
                System.out.println("My name is "+firstName+" "+lastName);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return users;
    }

    public List<User> getGenderedUsers()
    {
        return null;
    }

}
