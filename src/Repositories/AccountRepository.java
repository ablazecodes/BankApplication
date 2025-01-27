/*
 *********************************************************************************************************
 *  @Java Class Name : AccountRepository
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Manages database operations related to bank accounts, including account creation etc.
 *
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Repositories;

import Models.*;
import View.BankUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Constants.AccountConstants.*;

public class AccountRepository {
    Connection con = DatabaseConnection.getInstance().getConnection();

    private static AccountRepository instance;

    /*
     *********************************************************
     *  @Method Name    : getInstance
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns instance of the Account Repository class
     *  @param          : none
     *  @return         : AccountRepository
     ********************************************************
     */

    public static AccountRepository getInstance() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new AccountRepository();
                }
            }
        }
        return instance;
    }

    /*
     *********************************************************
     *  @Method Name    : AccountRepository
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : prevents external instantiation of the repository class
     *  @param          : none
     *  @return         : none
     ********************************************************
     */

    private AccountRepository() {
    }

    /*
     *********************************************************
     *  @Method Name    : createAccount
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes sql query to create an account with the given fields
     *  @param          : long accountNumber, String accountType, boolean isVerified, String username
     *  @return         : boolean
     ********************************************************
     */

    public synchronized boolean createAccount(long accountNumber, String accountType, boolean isVerified, String username) {
        String query = "INSERT INTO " + TABLE_ACCOUNTS +
                " (" + COLUMN_ACCOUNT_NUMBER + ", " + COLUMN_ACCOUNT_TYPE + ", " + COLUMN_BALANCE + ", " + COLUMN_IS_VERIFIED + ", " + COLUMN_USERNAME + ", bank_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, accountNumber);
            stmt.setString(2, accountType);
            stmt.setInt(3, BankUI.getBank().getMinBalance());
            stmt.setBoolean(4, isVerified);
            stmt.setString(5, username);
            stmt.setInt(6,
                    BankUI.getBank().getBankCode());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
     *********************************************************
     *  @Method Name    : updateAccountBalance
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes query to update the account balance
     *  @param          : long accountNumber, int newBalance
     *  @return         : boolean
     ********************************************************
     */

    public synchronized boolean updateAccountBalance(long accountNumber, int newBalance) {
        String query = "UPDATE  " +  TABLE_ACCOUNTS +  " SET " + COLUMN_BALANCE + " = ? WHERE " +  COLUMN_ACCOUNT_NUMBER + " = ?; " ;

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, newBalance);
            stmt.setLong(2, accountNumber);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    /*
     *********************************************************
     *  @Method Name    : deleteAccount
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes query to delete the account with the given account number
     *  @param          : long accountNumber
     *  @return         : boolean
     ********************************************************
     */

    public synchronized boolean deleteAccount(long accountNumber) {
        String query = "UPDATE " + TABLE_ACCOUNTS + " SET " + COLUMN_IS_VERIFIED + " = ? WHERE " +  COLUMN_ACCOUNT_NUMBER + " = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setBoolean(1, false);
            stmt.setLong(2, accountNumber);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    /*
     *********************************************************
     *  @Method Name    : getUnapprovedAccounts
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes query to retrieve all the unapproved accounts
     *  @param          : none
     *  @return         : List<Account>
     ********************************************************
     */

    public List<Account> getUnapprovedAccounts() {
        String query = "SELECT * FROM " + TABLE_ACCOUNTS + " WHERE " + COLUMN_IS_VERIFIED + " = ?";
        List<Account> accounts = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setBoolean(1, false);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    long account_number = rs.getLong("account_number");
                    String account_type = rs.getString("account_type");
                    int balance = rs.getInt("balance");
                    boolean isVerified = rs.getBoolean("is_verified");
                    String username = rs.getString("username");

                    accounts.add(new Account(account_number, account_type, username, balance, isVerified));
                }
            }
            return accounts;

        } catch (SQLException e) {
            System.out.println("Error fetching unapproved accounts: " + e.getMessage());
            return null;
        }
    }

    /*
     *********************************************************
     *  @Method Name    : getAccount
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes the query to retrieve the account with the given account number
     *  @param          : long acNo
     *  @return         : Account
     ********************************************************
     */

    public Account getAccount(long acNo) {
        String query = "SELECT * FROM " +  TABLE_ACCOUNTS + " WHERE " + COLUMN_ACCOUNT_NUMBER + " = ?";
        Account account = null;
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, acNo);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                long account_number = rs.getLong("account_number");
                String account_type = rs.getString("account_type");
                int balance = rs.getInt("balance");
                boolean isVerified = rs.getBoolean("is_verified");

                String username = rs.getString("username");
                account = new Account(account_number, account_type, username, balance, isVerified);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving account");
        }
        return account;
    }

    /*
     *********************************************************
     *  @Method Name    : changeAccountVerification
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes the query to change the account verification status to the given boolean flag
     *  @param          : boolean flag, long acNo
     *  @return         : boolean
     ********************************************************
     */

    public synchronized boolean changeAccountVerification(boolean flag, long acNo) {
        String query = "UPDATE " + TABLE_ACCOUNTS + " SET " + COLUMN_IS_VERIFIED + " = ? WHERE " + COLUMN_ACCOUNT_NUMBER + " = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setBoolean(1, flag);
            stmt.setLong(2, acNo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating account verification: " + e.getMessage());
            return false;
        }
    }

}
