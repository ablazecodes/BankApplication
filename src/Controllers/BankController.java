/*
 *********************************************************************************************************
 *  @Java Class Name : BankController
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Manages various bank-related operations such as retrieving, creating banks etc.
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Controllers;

import Models.Bank;
import Services.BankServices;

import java.util.List;

public class BankController {

    /*
     *********************************************************
     *  @Method Name    : getBanks
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns list of all the banks
     *  @param          : none
     *  @return         : List<Bank>
     ********************************************************
     */

    public static List<Bank> getBanks()
    {
        return BankServices.getBanks();
    }

    /*
     *********************************************************
     *  @Method Name    : createBank
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : creates bank and returns the result
     *  @param          : String bank_name, int bank_code, String address, int contact_number, int min_balance
     *  @return         : boolean
     ********************************************************
     */

    public static boolean createBank(String bank_name, int bank_code, String address, int contact_number, int min_balance){
            return BankServices.createBank(bank_name, bank_code, address, contact_number, min_balance);
    }
}
