package edu.matc.persistence;

import edu.matc.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;

/**
 * Access users in the user table.
 *
 * @author pwaite
 */
public class UserData {

    private final Logger logger = Logger.getLogger(this.getClass());

    private Connection connection;
    private Database database;

    private void connectToDatabase() {
        connection = null;
        database = Database.getInstance();

        try {
            database.connect();
            connection = database.getConnection();
        } catch (SQLException sqlexception) {
            System.out.println("SQL error generating database connection");
        } catch (Exception exception) {
            System.out.println("Generic Exception while generating database connection");
        }
    }


    private List<User> runQuery(String sqlStatment) {
        connectToDatabase();
        List<User> users = new ArrayList<User>();

        try {
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sqlStatment);
            while (results.next()) {
                User employee = createUserFromResults(results);
                users.add(employee);
            }
            database.disconnect();
        } catch (SQLException e) {
            logger.error("SQL exception is: ", e);
        } catch (Exception e) {
            logger.error("Generic exception in runQuery is: ", e);
        }
        return users;
    }

    public List<User> searchByFirstName (String firstName) {
        String sql = "SELECT * FROM users WHERE first_name like '" + firstName + "'";

        return runQuery(sql);
    }

    public List<User> searchByLastName (String lastName) {
        String sql = "SELECT * FROM users WHERE last_name like '" + lastName + "'";

        return runQuery(sql);
    }

    public List<User> searchByUserId (String userId) {
        String sql = "SELECT * FROM users WHERE id = " + userId;

        return runQuery(sql);
    }

    public List<User> searchByDateOfBirth (String dateOfBirth) {
        String sql = "SELECT * FROM users WHERE date_of_birth = '" + dateOfBirth + "'";

        return runQuery(sql);
    }

    public List<User> searchByAge (String age) {
        List<User> fullList = getAllUsers();
        List<User> matchList = new ArrayList<User>();

        for (User currentUser : fullList) {
            if (currentUser.calculateAge().equals(age)) {
                matchList.add(currentUser);
            }
        }

        return matchList;
    }

    public List<User> getAllUsers() {
        connectToDatabase();
        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM users";

        try {
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);
            while (results.next()) {
                User employee = createUserFromResults(results);
                users.add(employee);
            }
            database.disconnect();
        } catch (SQLException e) {
            logger.error("SQL error in getAllUsers: ", e);
        } catch (Exception e) {
            logger.error("Generic exception in getAllUsers: ", e);
        }
        return users;
    }


    private User createUserFromResults(ResultSet results) throws SQLException {
        User user = new User();
        user.setLastName(results.getString("last_name"));
        user.setDateOfBirth(results.getString("date_of_birth"));
        user.setFirstName(results.getString("first_name"));
        user.setUserid(results.getString("id"));
        return user;
    }

}