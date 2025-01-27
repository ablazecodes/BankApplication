/*
 *********************************************************************************************************
 *  @Java Class Name : TransactionRepository
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Manages database operations related to transactions such as undoing, inserting etc.
 *
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Repositories;

import Constants.*;
import Models.*;

import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static Constants.AccountConstants.COLUMN_ACCOUNT_NUMBER;
import static Constants.LoanConstants.COLUMN_IS_APPROVED;
import static Constants.TransactionConstants.*;

public class TransactionRepository {

    Connection con = DatabaseConnection.getInstance().getConnection();

    /*
     *********************************************************
     *  @Method Name    : TransactionRepository
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : prevents external instantiation of the repository class
     *  @param          : none
     *  @return         : none
     ********************************************************
     */

    private TransactionRepository() {
    }

    private static TransactionRepository instance;

    /*
     *********************************************************
     *  @Method Name    : getInstance
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns instance of the Transaction Repository class
     *  @param          : none
     *  @return         : TransactionRepository
     ********************************************************
     */

    public static TransactionRepository getInstance() {
        if (instance == null) {
            synchronized (TransactionRepository.class) {
                if (instance == null) {
                    instance = new TransactionRepository();
                }
            }
        }
        return instance;
    }

    /*
     *********************************************************
     *  @Method Name    : insertTransaction
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes the query to insert a new transaction with the given fields
     *  @param          : long ac, int amount, TransactionType type, Status status
     *  @return         : boolean
     ********************************************************
     */

    public synchronized boolean insertTransaction(long account_number, int amount, TransactionType type, String status, Timestamp timestamp) {
        String query = "INSERT INTO " + TABLE_TRANSACTIONS +
                " (" + COLUMN_ACCOUNT_NUMBER + ", " + COLUMN_AMOUNT + ", " + COLUMN_TYPE + ", " + COLUMN_STATUS + ", " + COLUMN_TRANSACTION_DATE + ") " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, account_number);
            stmt.setInt(2, amount);
            stmt.setString(3, type.toString());
            stmt.setString(4, status);
            stmt.setTimestamp(5, timestamp);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /*
     *********************************************************
     *  @Method Name    : undoTransaction
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes a query to undo a transaction
     *  @param          : int id
     *  @return         : boolean
     ********************************************************
     */

    public synchronized boolean undoTransaction(int id) {
        boolean flag = false;

        String query = "DELETE FROM " + TABLE_TRANSACTIONS + " WHERE" + COLUMN_TRANSACTION_ID + " = ?;";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            if (stmt.executeUpdate() > 0) flag = true;
        } catch (SQLException e) {
            flag = false;
        }

        return flag;
    }

    /*
     *********************************************************
     *  @Method Name    : getTransactions
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Executes the query to retrieve the List of all transaction
                          related to the provided account number
     *  @param          : long ac
     *  @return         : List<Transaction>
     ********************************************************
     */

    public List<Transaction> getTransactions(long acNo) {
        String query = "SELECT * FROM " +  TABLE_TRANSACTIONS + " WHERE " + COLUMN_ACCOUNT_NUMBER + " = ?";
        List<Transaction> transactions = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, acNo);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("transactionID");
                int amount = rs.getInt("amount");
                String type = rs.getString("type");
                String status = rs.getString("status");
                Timestamp date = rs.getTimestamp("transaction_date");
                transactions.add(new Transaction(id, amount, TransactionType.valueOf(type), Status.valueOf(status), date));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving account");
        }
        return transactions;
    }

}
