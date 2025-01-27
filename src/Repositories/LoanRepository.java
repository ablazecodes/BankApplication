/*
 *********************************************************************************************************
 *  @Java Class Name : LoanRepository
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Manages database operations related to Loans
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Repositories;

import Models.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static Constants.AccountConstants.*;
import static Constants.AccountConstants.COLUMN_USERNAME;
import static Constants.LoanConstants.*;
import static Constants.TransactionConstants.COLUMN_TYPE;

public class LoanRepository {
    Connection con = DatabaseConnection.getInstance().getConnection();

    private LoanRepository() {
    }

    private static LoanRepository instance;

    /*
     *********************************************************
     *  @Method Name    : getInstance
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns instance of the Loan Repository class
     *  @param          : none
     *  @return         : LoanRepository
     ********************************************************
     */

    public static LoanRepository getInstance() {
        if (instance == null) {
            synchronized (LoanRepository.class) {
                if (instance == null) {
                    instance = new LoanRepository();
                }
            }
        }
        return instance;
    }

    /*
     *********************************************************
     *  @Method Name    : getUnapprovedLoans
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes query to returns List of loans under the customer with the given username
     *  @param          : String username
     *  @return         : List<Loan>
     ********************************************************
     */

    public List<Loan> getUnapprovedLoans(String username) {
        String query = "SELECT * FROM " + TABLE_LOAN_APPLICATIONS + " WHERE " +  COLUMN_USERNAME + " = ? AND " + COLUMN_IS_VERIFIED + " = ?" ;
        List<Loan> loans = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setBoolean(2, false);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("application_id");
                int amount = rs.getInt("loan_amount");
                boolean requiresApproval = rs.getBoolean("manager_approval");
                boolean isApproved = rs.getBoolean("isApproved");
                String category = rs.getString("type");
                Loan loan = new Loan(id, amount, requiresApproval, isApproved, category);
                loans.add(loan);
            }
        } catch (SQLException e) {
            return null;
        }
        return loans;
    }

    /*
     *********************************************************
     *  @Method Name    : approveLoan
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes query to approve a loan with the given loan id and returns the result
     *  @param          : int id
     *  @return         : boolean
     ********************************************************
     */

    public synchronized boolean approveLoan(int loanId) {
        String query = "UPDATE " +  TABLE_LOAN_APPLICATIONS + " SET " + COLUMN_IS_VERIFIED + " = ?  WHERE " + COLUMN_APPLICATION_ID + " = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setBoolean(1, true);
            stmt.setInt(2, loanId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    /*
     *********************************************************
     *  @Method Name    : insertLoan
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes query to insert a loan with the given fields and returns the result
     *  @param          : int amount, String category, boolean requiresApproval, String username
     *  @return         : boolean
     ********************************************************
     */

    public synchronized boolean insertLoan(int amount, String category, boolean requiresApproval, String username) {
        String query = "INSERT INTO " +  TABLE_LOAN_APPLICATIONS +  " (" + COLUMN_USERNAME + ", " +  COLUMN_LOAN_AMOUNT + ", " + COLUMN_APPLICATION_DATE + ", " +  COLUMN_MANAGER_APPROVAL + ", " + COLUMN_TYPE + ", " + COLUMN_IS_APPROVED + ") VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setInt(2, amount);
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.setBoolean(4, requiresApproval);
            stmt.setString(5, category);
            stmt.setBoolean(6, !requiresApproval);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

}
