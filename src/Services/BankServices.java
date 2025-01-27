/*
 *********************************************************************************************************
 *  @Java Class Name : BankServices
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Contains services related to the bank such as setting bank details etc.
 *
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Services;

import Models.Bank;
import Repositories.BankRepository;

import java.util.List;
import java.util.Scanner;

public class BankServices {

    static BankRepository bsr = BankRepository.getInstance();

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
        return bsr.getBanks();
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

    public static boolean createBank(String bank_name, int bank_code, String address, int contact_number, int min_balance)
    {
        return bsr.createBank(bank_name, bank_code, address, contact_number, min_balance);
    }

}
