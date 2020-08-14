package comp3350.losr.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import comp3350.losr.objects.Profile;
import comp3350.losr.objects.Question;
import comp3350.losr.objects.User;

public class DataAccessObject implements DataAccess {

    private Connection c1 = null;
    private Statement s1;
    private ResultSet rs1;

    private String dbName;
    private String dbType;

    private User currentUser = new User("Michael", "Bathie", "mbathie@gmail.com", "password");

    private String cmdString;

    public DataAccessObject(String dbName) {
        this.dbName = dbName;
        currentUser.setUserProfile("hi", User.user_gender.Male, User.user_gender.Female, 1999, 1, 25, false);
        currentUser.setUserAllAnswers(true, false, false, true, true, 2, 2, 2, 2, 2);
    }

    public void openConnection(String dbPath) {

        String url;

        try {
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath;
            c1 = DriverManager.getConnection(url, "SA", "");
            s1 = c1.createStatement();

            if (c1 != null) {
                System.out.println("Successfully connected to the database");
            } else {
                System.out.println("Count not create database connection");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong when trying to connect to the database.");
            e.printStackTrace();
        }

        System.out.println("Opened connection to " + dbType + " database " + dbName + " --> " + dbPath);
    }

    public void closeConnection() {
        try {
            cmdString = "shutdown compact";
            rs1 = s1.executeQuery(cmdString);
            c1.close();
        } catch (Exception e) {
            System.out.println("Something went wrong when trying to close the database");
            e.printStackTrace();
        }

        System.out.println("closed connection to " + dbType + " database " + dbName);
    }

    public User addUser(User newUser) {
        String values;
        User registered = null;

        try {
            values = "'" + newUser.getUserEmail()
                    + "', '" + newUser.getUserPassword()
                    + "', '" + newUser.getUserFirstName()
                    + "', '" + newUser.getUserLastName()
                    + "', 'hi', 'losr', 'losr', 0, 0, 0, false, false, false, false, false, 2, 2, 2, 2, 2, '', false";

            cmdString = "Insert into USERS " + " Values(" + values + ")";
            s1.executeUpdate(cmdString);

            registered = new User(newUser.getUserFirstName(), newUser.getUserLastName(), newUser.getUserEmail(), newUser.getUserPassword());
            registered.setUserProfile("hi", User.user_gender.Losr, User.user_gender.Losr, 0, 0, 0, false);
            registered.setUserAllAnswers(false, false, false, false, false, 2, 2, 2, 2, 2);
            registered.setUserMode(false);
            registered.setUserPicture("");

            currentUser = registered;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registered;
    }

    public void deleteUser(User delete) {
        try {
            cmdString = "Delete from USERS where email= '" + delete.getUserEmail() + "'";
            s1.executeUpdate(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User update) {
        String values;

//        Profile p = update;
        String[] dob = update.getUserDateOfBirth().split("/");
        List<Question> answers = update.getUserAnswers();

        try {
            values = "email='" + update.getUserEmail()
                    + "', password='" + update.getUserPassword()
                    + "', fname='" + update.getUserFirstName()
                    + "', lname='" + update.getUserLastName()
                    + "', bio='" + update.getUserBio()
                    + "', gender='" + update.userGenderToString()
                    + "', genderp='" + update.userGenderPrefToString()
                    + "', year= " + dob[2]
                    + ", month= " + dob[1]
                    + ", day= " + dob[0]
                    + ", q1= " + answers.get(0).getAnswer()
                    + ", q2= " + answers.get(1).getAnswer()
                    + ", q3= " + answers.get(2).getAnswer()
                    + ", q4= " + answers.get(3).getAnswer()
                    + ", q5= " + answers.get(4).getAnswer()
                    + ", w1= " + answers.get(0).getWeight()
                    + ", w2= " + answers.get(1).getWeight()
                    + ", w3= " + answers.get(2).getWeight()
                    + ", w4= " + answers.get(3).getWeight()
                    + ", w5= " + answers.get(4).getWeight()
                    + ", picture= '" + update.getUserPicture()
                    + "', blindmode= " + update.getUserMode();

            //the only time you update a user is when you're currently logged in to that user
            cmdString = "Update USERS Set " + values + " where email = " + "'" + currentUser.getUserEmail() + "'";
            s1.executeUpdate(cmdString);

            currentUser = update;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public User getSpecificUser(String userEmail) {
        User specificUser = null;

        try {
            cmdString = "SELECT * FROM USERS WHERE email = " + "'" + userEmail + "'";
            rs1 = s1.executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (rs1.next()) {
                specificUser = getUser(rs1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return specificUser;
    }

    public String tryLogin(String userEmail, String userPassword) {
        String message = null;
        User returningUser;

        int check = 0;

        try {
            cmdString = "SELECT * FROM USERS WHERE email = " + "'" + userEmail + "'";
            rs1 = s1.executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (rs1.next()) {

                returningUser = getUser(rs1);

                if (userPassword.equals(returningUser.getUserPassword())) {
                    currentUser = returningUser;
                } else {
                    message = "Incorrect password";
                }

                check++;
            }

            if (check <= 0) {
                message = "Could not find an account with that email";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    public List<User> getGenderedUsers() {
        List<User> users = new ArrayList<>();
        User newUser;

        try {
            cmdString = "SELECT * FROM USERS WHERE gender = " + "'" + currentUser.userGenderPrefToString() + "'";
            rs1 = s1.executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (rs1.next()) {

                newUser = getUser(rs1);
                if (newUser.userGenderPrefToString().equals(currentUser.userGenderToString()) && !newUser.getUserEmail().equals(currentUser.getUserEmail())) {
                    users.add(newUser);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public void report(String reportee) {
        String values;

        try {
            values = "'" + currentUser.getUserEmail()
                    + "','" + reportee + "'";

            cmdString = "Insert into REPORT " + " Values(" + values + ")";
            s1.executeUpdate(cmdString);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<String> getReports() {
        List<String> reports = new ArrayList<>();

        String reportee;

        try {
            cmdString = "SELECT * FROM REPORT WHERE REPORTER = " + "'" + currentUser.getUserEmail() + "'";
            rs1 = s1.executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (rs1.next()) {
                reportee = rs1.getString("reportee");

                reports.add(reportee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reports;
    }

    public void clearReports() {
        try {
            cmdString = "Delete from REPORT where reporter= '" + currentUser.getUserEmail() + "'";
            s1.executeUpdate(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newMatch(String match) {
        String values;

        try {
            values = "'" + currentUser.getUserEmail()
                    + "','" + match + "'";
            cmdString = "Insert into MATCH " + " Values(" + values + ")";
            s1.executeUpdate(cmdString);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean checkMatch(String match) {
        boolean matchExists = false;

        try {
            cmdString = "SELECT * FROM MATCH WHERE USER = " + "'" + match + "' AND USERMATCH = " + "'" + currentUser.getUserEmail() + "'";
            rs1 = s1.executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (rs1.next()) {
                matchExists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matchExists;
    }

    public boolean checkMatchExists(String match) {
        boolean matchExists = false;

        try {
            cmdString = "SELECT * FROM MATCH WHERE USER = " + "'" + currentUser.getUserEmail() + "' AND USERMATCH = " + "'" + match + "'";
            rs1 = s1.executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (rs1.next()) {
                matchExists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matchExists;
    }

    private User getUser(ResultSet rs) {
        User u = new User("", "", "", "");

        String email, password, firstName, lastName, bio;
        User.user_gender genderEnum, genderPEnum;
        String gender, genderP;
        int day, month, year;
        boolean q1, q2, q3, q4, q5;
        int w1, w2, w3, w4, w5;
        boolean blindMode;
        String picturePath;

        try {
            email = rs.getString("email");
            password = rs.getString("password");
            firstName = rs.getString("fName");
            lastName = rs.getString("lName");
            bio = rs.getString("bio");
            gender = rs.getString("gender");
            genderP = rs.getString("genderP");
            day = rs.getInt("day");
            month = rs.getInt("month");
            year = rs.getInt("year");
            q1 = rs.getBoolean("Q1");
            q2 = rs.getBoolean("Q2");
            q3 = rs.getBoolean("Q3");
            q4 = rs.getBoolean("Q4");
            q5 = rs.getBoolean("Q5");
            w1 = rs.getInt("w1");
            w2 = rs.getInt("w2");
            w3 = rs.getInt("w3");
            w4 = rs.getInt("w4");
            w5 = rs.getInt("w5");
            blindMode = rs.getBoolean("blindmode");
            picturePath = rs.getString("picture");

            switch (gender) {
                case "Male":
                    genderEnum = User.user_gender.Male;
                    break;
                case "Female":
                    genderEnum = User.user_gender.Female;
                    break;
                default:
                    genderEnum = User.user_gender.Losr;
            }
            switch (genderP) {
                case "Male":
                    genderPEnum = User.user_gender.Male;
                    break;
                case "Female":
                    genderPEnum = User.user_gender.Female;
                    break;
                default:
                    genderPEnum = User.user_gender.Losr;
            }

            u = new User(firstName, lastName, email, password);
            u.setUserProfile(bio, genderEnum, genderPEnum, year, month, day, false);
            u.setUserAllAnswers(q1, q2, q3, q4, q5, w1, w2, w3, w4, w5);
            u.setUserMode(blindMode);
            u.setUserPicture(picturePath);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    public void changeBlindMode(boolean blindMode) {
        try {
            String values = "blindmode= " + blindMode;
            cmdString = "Update USERS Set " + values + " where email = " + "'" + currentUser.getUserEmail() + "'";
            s1.executeUpdate(cmdString);

            currentUser.setUserMode(blindMode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
