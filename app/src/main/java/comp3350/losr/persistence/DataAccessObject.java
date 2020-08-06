package comp3350.losr.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.losr.objects.Profile;
import comp3350.losr.objects.Question;
import comp3350.losr.objects.User;

public class DataAccessObject implements DataAccess {

    private Connection c1 = null;
    private Statement s1;
    private ResultSet rs1;

    private String dbName;
    private String dbType;

    private List<User> users;
    private User currentUser = new User("Michael", "Bathie", "mbathie@gmail.com", "password");

    private String cmdString;

    public DataAccessObject(String dbName) {
        this.dbName = dbName;
        currentUser.setUserProfile("hi", User.user_gender.Male, User.user_gender.Female, 1999, 1, 25);
        currentUser.updateAllAnswers(true, false, false, true, true, 2, 2, 2, 2, 2);
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
                    + "', 'hi', 'losr', 'losr', 0, 0, 0, false, false, false, false, false, 2, 2, 2, 2, 2";

            cmdString = "Insert into USERS " + " Values(" + values + ")";
            s1.executeUpdate(cmdString);

            registered = new User(newUser.getUserFirstName(), newUser.getUserLastName(), newUser.getUserEmail(), newUser.getUserPassword());
            registered.setUserProfile("hi", User.user_gender.Losr, User.user_gender.Losr, 0, 0, 0);
            registered.updateAllAnswers(false, false, false, false, false, 2, 2, 2, 2, 2);

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

        Profile p = update.getUserProfile();
        String[] dob = p.dateOfBirth().split("/");
        System.out.println(update.getUserProfile().dateOfBirth());
        List<Question> answers = p.getAnswers();

        try {
            values = "email='" + update.getUserEmail()
                    + "', password='" + update.getUserPassword()
                    + "', fname='" + update.getUserFirstName()
                    + "', lname='" + update.getUserLastName()
                    + "', bio='" + p.getBio()
                    + "', gender='" + p.genderToString()
                    + "', genderp='" + p.genderPrefToString()
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
                    + ", w5= " + answers.get(4).getWeight();

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
        User specifiedUser = null;

        String email, password, firstName, lastName, bio;
        User.user_gender genderEnum, genderPEnum;
        String gender, genderP;
        int day, month, year;
        boolean q1, q2, q3, q4, q5;
        int w1, w2, w3, w4, w5;

        try {
            cmdString = "SELECT * FROM USERS WHERE email = " + "'" + userEmail + "'";
            rs1 = s1.executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (rs1.next()) {
                email = rs1.getString("email");
                password = rs1.getString("password");
                firstName = rs1.getString("fName");
                lastName = rs1.getString("lName");
                bio = rs1.getString("bio");
                gender = rs1.getString("gender");
                genderP = rs1.getString("genderP");
                day = rs1.getInt("day");
                month = rs1.getInt("month");
                year = rs1.getInt("year");
                q1 = rs1.getBoolean("Q1");
                q2 = rs1.getBoolean("Q2");
                q3 = rs1.getBoolean("Q3");
                q4 = rs1.getBoolean("Q4");
                q5 = rs1.getBoolean("Q5");
                w1 = rs1.getInt("w1");
                w2 = rs1.getInt("w2");
                w3 = rs1.getInt("w3");
                w4 = rs1.getInt("w4");
                w5 = rs1.getInt("w5");

                switch (gender) {
                    case "male":
                        genderEnum = User.user_gender.Male;
                        break;
                    case "female":
                        genderEnum = User.user_gender.Female;
                        break;
                    default:
                        genderEnum = User.user_gender.Losr;
                }
                switch (genderP) {
                    case "male":
                        genderPEnum = User.user_gender.Male;
                        break;
                    case "female":
                        genderPEnum = User.user_gender.Female;
                        break;
                    default:
                        genderPEnum = User.user_gender.Losr;
                }

                specifiedUser = new User(firstName, lastName, email, password);
                specifiedUser.setUserProfile(bio, genderEnum, genderPEnum, year, month, day);
                specifiedUser.updateAllAnswers(q1, q2, q3, q4, q5, w1, w2, w3, w4, w5);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return specifiedUser;
    }

    public String tryLogin(String userEmail, String userPassword) {
        String message = null;
        User returningUser;

        int check = 0;

        String email, password, firstName, lastName, bio;
        User.user_gender genderEnum, genderPEnum;
        String gender, genderP;
        int day, month, year;
        boolean q1, q2, q3, q4, q5;
        int w1, w2, w3, w4, w5;

        try {
            cmdString = "SELECT * FROM USERS WHERE email = " + "'" + userEmail + "'";
            rs1 = s1.executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (rs1.next()) {
                email = rs1.getString("email");
                password = rs1.getString("password");
                firstName = rs1.getString("fName");
                lastName = rs1.getString("lName");
                bio = rs1.getString("bio");
                gender = rs1.getString("gender");
                genderP = rs1.getString("genderP");
                day = rs1.getInt("day");
                month = rs1.getInt("month");
                year = rs1.getInt("year");
                q1 = rs1.getBoolean("Q1");
                q2 = rs1.getBoolean("Q2");
                q3 = rs1.getBoolean("Q3");
                q4 = rs1.getBoolean("Q4");
                q5 = rs1.getBoolean("Q5");
                w1 = rs1.getInt("w1");
                w2 = rs1.getInt("w2");
                w3 = rs1.getInt("w3");
                w4 = rs1.getInt("w4");
                w5 = rs1.getInt("w5");

                switch (gender) {
                    case "male":
                        genderEnum = User.user_gender.Male;
                        break;
                    case "female":
                        genderEnum = User.user_gender.Female;
                        break;
                    default:
                        genderEnum = User.user_gender.Losr;
                }
                switch (genderP) {
                    case "male":
                        genderPEnum = User.user_gender.Male;
                        break;
                    case "female":
                        genderPEnum = User.user_gender.Female;
                        break;
                    default:
                        genderPEnum = User.user_gender.Losr;
                }

                if (userPassword.equals(password)) {
                    returningUser = new User(firstName, lastName, email, password);
                    returningUser.setUserProfile(bio, genderEnum, genderPEnum, year, month, day);
                    returningUser.updateAllAnswers(q1, q2, q3, q4, q5, w1, w2, w3, w4, w5);

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
        users = new ArrayList<>();

        String email, password, firstName, lastName, bio;
        User.user_gender genderEnum, genderPEnum;
        String gender, genderP;
        int day, month, year;
        boolean q1, q2, q3, q4, q5;
        int w1, w2, w3, w4, w5;

        try {
            cmdString = "SELECT * FROM USERS WHERE gender = " + "'" + currentUser.getUserProfile().genderPrefToString() + "'";
            rs1 = s1.executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (rs1.next()) {
                email = rs1.getString("email");
                password = rs1.getString("password");
                firstName = rs1.getString("fName");
                lastName = rs1.getString("lName");
                bio = rs1.getString("bio");
                gender = rs1.getString("gender");
                genderP = rs1.getString("genderP");
                day = rs1.getInt("day");
                month = rs1.getInt("month");
                year = rs1.getInt("year");
                q1 = rs1.getBoolean("Q1");
                q2 = rs1.getBoolean("Q2");
                q3 = rs1.getBoolean("Q3");
                q4 = rs1.getBoolean("Q4");
                q5 = rs1.getBoolean("Q5");
                w1 = rs1.getInt("w1");
                w2 = rs1.getInt("w2");
                w3 = rs1.getInt("w3");
                w4 = rs1.getInt("w4");
                w5 = rs1.getInt("w5");

                switch (gender) {
                    case "male":
                        genderEnum = User.user_gender.Male;
                        break;
                    case "female":
                        genderEnum = User.user_gender.Female;
                        break;
                    default:
                        genderEnum = User.user_gender.Losr;
                }
                switch (genderP) {
                    case "male":
                        genderPEnum = User.user_gender.Male;
                        break;
                    case "female":
                        genderPEnum = User.user_gender.Female;
                        break;
                    default:
                        genderPEnum = User.user_gender.Losr;
                }

                if (genderPEnum.toString().equals(currentUser.getUserProfile().getGender().toString()) && !email.equals(currentUser.getUserEmail())) {
                    User newUser = new User(firstName, lastName, email, password);
                    newUser.setUserProfile(bio, genderEnum, genderPEnum, year, month, day);
                    newUser.updateAllAnswers(q1, q2, q3, q4, q5, w1, w2, w3, w4, w5);
                    users.add(newUser);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public void report(String reportee)
    {

    }

    public List<String> getReports()
    {
        List<String> reportees = new ArrayList<>();

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

                reportees.add(reportee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reportees;
    }
}
