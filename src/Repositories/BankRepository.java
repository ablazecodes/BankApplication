/*
 *********************************************************************************************************
 *  @Java Class Name : BankRepository
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Manages database operations related to banks such as creating banks.
 *
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Repositories;
import Models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Constants.BankConstants.*;

public class BankRepository {

    Connection con = DatabaseConnection.getInstance().getConnection();

    private static BankRepository instance;

    /*
     *********************************************************
     *  @Method Name    : getInstance
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns instance of the Bank Repository class
     *  @param          : none
     *  @return         : BankRepository
     ********************************************************
     */

    public static BankRepository getInstance() {
        if (instance == null) {
            synchronized (BankRepository.class) {
                if (instance == null) {
                    instance = new BankRepository() ;
                }
            }
        }
        return instance;
    }

    /*
     *********************************************************
     *  @Method Name    : createBank
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes sql query to create a Bank with the given fields
     *  @param          : String bank_name, int bank_code, String address, int contact_number, int min_balance
     *  @return         : boolean
     ********************************************************
     */

    public synchronized boolean createBank(String bank_name, int bank_code, String address, int contact_number, int min_balance) {
        String query = "INSERT INTO " +  TABLE_BANKS +  " (" + COLUMN_BANK_NAME + ", " + COLUMN_BANK_ID + ", " + COLUMN_BANK_ADDRESS + ", " +  COLUMN_BANK_CONTACT_NUMBER + ", " + COLUMN_BANK_MIN_BALANCE + ") VALUES (?, ?, ?, ?, ?); ";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, bank_name);
            stmt.setInt(2, bank_code);
            stmt.setString(3, address);
            stmt.setInt(4, contact_number);
            stmt.setInt(5, min_balance);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    /*
     *********************************************************
     *  @Method Name    : getBanks
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : executes query to retrieve all the Banks
     *  @param          : none
     *  @return         : List<Bank>
     ********************************************************
     */

    public List<Bank> getBanks() {
        String query = "SELECT * FROM " +  TABLE_BANKS;

        List<Bank> banks = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int min_balance = rs.getInt("min_balance");
                int bank_code = rs.getInt("bank_code");
                String address = rs.getString("address");
                int contact_number = rs.getInt("contact_number");
                String bank_name = rs.getString("bank_name");
                Bank bank = new Bank(min_balance, bank_code, bank_name, address, contact_number);
                banks.add(bank);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return banks;
    }


}
