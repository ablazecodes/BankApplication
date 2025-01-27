/*
 *********************************************************************************************************
 *  @Java Class Name : DatabaseConnection
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Provides connection to the database
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static Constants.DatabaseConstants.CONNECTION_STRING;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    Connection con;

    private DatabaseConnection() {
        if (open()) System.out.println("Database opened successfully");
        else System.out.println("Error opening the db");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) instance = new DatabaseConnection();
            }
        }
        return instance;
    }

    public synchronized boolean open() {
        if (con != null) {
            return true;
        }
        try {
            con = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public synchronized void close() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public synchronized Connection getConnection() {
        return con;
    }

}
