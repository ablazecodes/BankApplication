/*
 *********************************************************************************************************
 *  @Java Class Name : AdminRepository
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Manages database operations related to admin such as getting customer history
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

public class AdminRepository {

    Connection con = DatabaseConnection.getInstance().getConnection();

    /*
     *********************************************************
     *  @Method Name    : AdminRepository
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : prevents external instantiation of the repository class
     *  @param          : none
     *  @return         : none
     ********************************************************
     */

    private AdminRepository() {
    }

    private static AdminRepository instance;

    /*
     *********************************************************
     *  @Method Name    : getInstance
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns instance of the Admin Repository class
     *  @param          : none
     *  @return         : AdminRepository
     ********************************************************
     */

    public static AdminRepository getInstance() {
        if (instance == null) {
            synchronized (AdminRepository.class) {
                if (instance == null) {
                    instance = new AdminRepository();
                }
            }
        }
        return instance;
    }

    /*
     *********************************************************
     *  @Method Name    : getCustomerHistory
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes sql query to view Customer History based on their pancard
     *  @param          : String pan
     *  @return         : void
     ********************************************************
     */

    public void getCustomerHistory(String pan)
    {
        String sql = "SELECT a.account_number, a.account_type, a.balance, a.is_verified, b.bank_name FROM accounts a JOIN customers c ON a.username = c.username JOIN banks b ON a.bank_id = b.bank_code WHERE c.pan_card_number = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, pan);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                long accountNumber = rs.getLong("account_number");
                String accountType = rs.getString("account_type");
                int balance = rs.getInt("balance");
                boolean isVerified = rs.getBoolean("is_verified");
                String bankName = rs.getString("bank_name");

                System.out.println("Account Number: " + accountNumber);
                System.out.println("Account Type: " + accountType);
                System.out.println("Balance: " + balance);
                System.out.println("Is Verified: " + isVerified);
                System.out.println("Bank Name: " + bankName);
                System.out.println("-------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
