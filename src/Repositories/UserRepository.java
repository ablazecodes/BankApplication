/*
 *********************************************************************************************************
 *  @Java Class Name : UserRepository
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Manages database operations related to users, such as user creation, deletion etc.
 *
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.*;
import Constants.*;
import View.BankUI;


import static Constants.AccountConstants.COLUMN_BANK_CODE;
import static Constants.AccountConstants.COLUMN_USERNAME;
import static Constants.UserConstants.*;

public class UserRepository {

    Connection con = DatabaseConnection.getInstance().getConnection();

    private static UserRepository instance;

    /*
     *********************************************************
     *  @Method Name    : UserRepository
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : prevents external instantiation of the repository class
     *  @param          : none
     *  @return         : none
     ********************************************************
     */
    private UserRepository() {
    }

    /*
     *********************************************************
     *  @Method Name    : getInstance
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns instance of the User Repository class
     *  @param          : none
     *  @return         : UserRepository
     ********************************************************
     */
    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    /*
     *********************************************************
     *  @Method Name    : createUser
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes the query to create a user with the given fields and returns the result
     *  @param          : String username, String password, Role role
     *  @return         : boolean
     ********************************************************
     */
    public boolean createUser(String username, String password, Role role) {
        String query = "INSERT INTO " +  TABLE_USERS +  " (" + COLUMN_USERNAME + ", " +  COLUMN_PASSWORD + ", " + COLUMN_ROLE + ", " + COLUMN_BANK_CODE + ") VALUES (?, ?, ?, ?);";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role.toString());
            stmt.setInt(4, BankUI.getBankID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    /*
     *********************************************************
     *  @Method Name    : deleteUser
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes a query to delete a user with the given fields and returns the result
     *  @param          : String username
     *  @return         : boolean
     ********************************************************
     */
    public synchronized boolean deleteUser(String username) {
        String query = "DELETE FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ? AND " + COLUMN_BANK_CODE + " = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setInt(2, BankUI.getBankID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    /*
     *********************************************************
     *  @Method Name    : getUserDetails
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes the query to retrieve a user with the given username
     *  @param          : String username
     *  @return         : boolean
     ********************************************************
     */
    public User getUserDetails(String username) {
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ? AND " + COLUMN_BANK_CODE + " = ?";
        User user = null;
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setInt(2, BankUI.getBankID());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String username1 = rs.getString("username");
                String password = rs.getString("password");
                Role role = Role.valueOf(rs.getString("role"));
                user = new User(username1, password, role);
            }
        } catch (SQLException e) {
            return null;
        }
        return user;
    }

    /*
     *********************************************************
     *  @Method Name    : getUsers
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes the query to retrieve the list of all the users
     *  @param          : none
     *  @return         : List<User>
     ********************************************************
     */
    public List<User> getUsers() {
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_BANK_CODE + " = ?";
        List<User> users = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, BankUI.getBankID());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                Role role = Role.valueOf(rs.getString("role"));
                users.add(new User(username, password, role));
            }
            return users;
        } catch (SQLException e) {
            return null;
        }
    }
}
