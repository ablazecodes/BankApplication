/*
 *********************************************************************************************************
 *  @Java Class Name : CustomerRepository
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Manages database operations related to customers, including creating accounts,
 *                     applying for loans etc.
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
import View.BankUI;

import static Constants.AccountConstants.*;
import static Constants.CustomerConstants.*;

public class CustomerRepository {

    Connection con = DatabaseConnection.getInstance().getConnection();

    /*
     *********************************************************
     *  @Method Name    : CustomerRepository
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : prevents external instantiation of the repository class
     *  @param          : none
     *  @return         : none
     ********************************************************
     */

    private CustomerRepository() {
    }

    private static CustomerRepository instance;

    /*
     *********************************************************
     *  @Method Name    : getInstance
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns instance of the Customer Repository class
     *  @param          : none
     *  @return         : CustomerRepository
     ********************************************************
     */

    public static CustomerRepository getInstance() {
        if (instance == null) {
            synchronized (CustomerRepository.class) {
                if (instance == null) {
                    instance = new CustomerRepository();
                }
            }
        }
        return instance;
    }

    /*
     *********************************************************
     *  @Method Name    : createCustomer
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes sql query to create customer with the given fields
     *  @param          : String username, String name, String aadhar, String pan, int creditScore
     *  @return         : boolean
     ********************************************************
     */

    public synchronized boolean createCustomer(String username, String name, String aadhar, String pan, int creditScore) {
        String query = "INSERT INTO " +  TABLE_CUSTOMERS +  " (" + COLUMN_USERNAME + ", " +  COLUMN_NAME + ", " + COLUMN_AADHAR_NUMBER + ", " +  COLUMN_PAN_CARD_NUMBER + ", " + COLUMN_CREDIT_SCORE + ", " + COLUMN_BANK_CODE + ") VALUES (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, name);
            stmt.setString(3, aadhar);
            stmt.setString(4, pan);
            stmt.setInt(5, creditScore);
            stmt.setInt(6, BankUI.getBankID());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    /*
     *********************************************************
     *  @Method Name    : getCustomerDetails
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes sql query to get customer with the given username
     *  @param          : String username
     *  @return         : Customer
     ********************************************************
     */

    public Customer getCustomerDetails(String username) {
        String query = "SELECT * FROM " + TABLE_CUSTOMERS + " WHERE " + COLUMN_USERNAME + " = ? AND " + COLUMN_BANK_CODE + " = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setInt(2, BankUI.getBankID());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String aadhar = rs.getString("aadhar_number");
                String pan = rs.getString("pan_card_number");
                int creditScore = rs.getInt("credit_score");
                return new Customer(username, name, aadhar, pan, creditScore);
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    /*
     *********************************************************
     *  @Method Name    : getCustomers
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes query to retrieve all the Customers
     *  @param          : none
     *  @return         : List<Account>
     ********************************************************
     */

    public List<Customer> getCustomers() {
        String query = "SELECT * FROM " + TABLE_CUSTOMERS + " WHERE " + COLUMN_BANK_CODE + " = ?";
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, BankUI.getBankID());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String username = rs.getString("username");
                String aadhar = rs.getString("aadhar_number");
                String pan = rs.getString("pan_card_number");
                int creditScore = rs.getInt("credit_score");

                customers.add(new Customer(username, name, aadhar, pan, creditScore));
            }
            return customers;
        } catch (SQLException e) {
            return null;
        }
    }

    /*
     *********************************************************
     *  @Method Name    : getAccountsForAccounts
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes query to retrieve all the accounts under the customer with the given username
     *  @param          : String username
     *  @return         : List<Account>
     ********************************************************
     */
    public List<Account> getAccountsForCustomer(String username) {
        String query = "SELECT * FROM " +  TABLE_ACCOUNTS + " WHERE " + COLUMN_USERNAME + " = ? AND bank_id = ?";

        List<Account> accounts = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setInt(2, BankUI.getBankID());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                long account_number = rs.getLong("account_number");
                String account_type = rs.getString("account_type");
                String cust_username = rs.getString("username");
                int balance = rs.getInt("balance");
                boolean isVerified = rs.getBoolean("is_verified");
                Account account = new Account(account_number, account_type, cust_username, balance, isVerified);
                accounts.add(account);
            }
        } catch (SQLException e) {
            return null;
        }
        return accounts;
    }

}
