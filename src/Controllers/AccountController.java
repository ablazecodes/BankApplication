/*
 *********************************************************************************************************
 *  @Java Class Name : AccountController
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Manages various account-related operations such as retrieving, verifying,
 *                     deleting accounts and printing passbooks.
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Controllers;

import Constants.Status;
import Constants.TransactionType;
import Models.*;
import Models.Customer;
import Repositories.TransactionRepository;
import Services.AccountServices;

import java.util.List;

public class AccountController {

    /*
     *********************************************************
     *  @Method Name    : getAccount
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : return the Account with the specified Account Number
     *  @param          : long acNo
     *  @return         : Account
     ********************************************************
     */

    public static Account getAccount(long ac)
    {
        return AccountServices.getAccount(ac);
    }

    /*
     *********************************************************
     *  @Method Name    : changeAccountVerification
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : changes the verification status of the account and returns the result
     *  @param          : boolean flag, long acNo
     *  @return         : boolean
     ********************************************************
     */

    public static boolean changeAccountVerification(boolean flag, long ac)
    {
        if(AccountServices.getAccount(ac)==null) return false;
        return AccountServices.changeAccountVerification(flag, ac);
    }

    /*
     *********************************************************
     *  @Method Name    : getUnapprovedAccounts
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns the list of all unapproved accounts
     *  @param          : none
     *  @return         : List<Account>
     ********************************************************
     */


    public static List<Account> getUnapprovedAccounts()
    {
        return AccountServices.getUnapprovedAccounts();
    }

    /*
     *********************************************************
     *  @Method Name    : deleteAccount
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : deletes the account and return the result of the process
     *  @param          : long acNo
     *  @return         : boolean
     ********************************************************
     */

    public static boolean deleteAccount(long ac)
    {
        return AccountServices.deleteAccount(ac);
    }
    /*
     *********************************************************
     *  @Method Name    : transferMoney
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : processes withdrawal, deposit, or credit transactions,
     *                    updates the account balance, and returns a message
     *  @param          : long acNo, int TotAmount, TransactionType type
     *  @return         : String
     ********************************************************
     */

    public static String transferMoney(long acNo, int TotAmount, TransactionType type)
    {
        if(type==TransactionType.WITHDRAW)
        {
            if(AccountServices.withdraw(acNo, TotAmount))
            {
                TransactionController.insertTransaction(acNo, TotAmount, type, Status.COMPLETED);
                return "Money withdrawn successfully";
            }
            else
            {
                TransactionController.insertTransaction(acNo, TotAmount, type, Status.FAILED);
                return "Unable to withdraw money";
            }
        }
        else if(type==TransactionType.DEPOSIT){
            if(AccountServices.deposit(acNo, TotAmount))
            {
                TransactionController.insertTransaction(acNo, TotAmount, type, Status.COMPLETED);
                return "Money deposited successfully";
            }
            else
                {
                    TransactionController.insertTransaction(acNo, TotAmount, type, Status.FAILED);
                    return "Unable to deposit money";
                }
        }
        else if (type == TransactionType.CREDIT)
        {
            if(AccountServices.deposit(acNo, TotAmount))
            {
                TransactionController.insertTransaction(acNo, TotAmount, type, Status.COMPLETED);
                return "Money deposited successfully";
            }
            else
            {
                TransactionController.insertTransaction(acNo, TotAmount, type, Status.FAILED);
                return "Unable to deposit money";
            }
        }
        else
        {
            if(AccountServices.withdraw(acNo, TotAmount))
            {
                TransactionController.insertTransaction(acNo, TotAmount, type, Status.COMPLETED);
                return "Money deposited successfully";
            }
            else
            {
                TransactionController.insertTransaction(acNo, TotAmount, type, Status.FAILED);
                return "Unable to deposit money";
            }
        }
    }

    /*
     *********************************************************
     *  @Method Name    : printPassbook
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : saves all the transactions of an account to a file and saves it locally
     *  @param          : Customer cs, long acNo, List<Transaction>transactions
     *  @return         : boolean
     ********************************************************
     */

    public static synchronized String printPassbook(Customer cs, long acNo) {
        TransactionRepository tsr = TransactionRepository.getInstance();

        if(tsr.getTransactions(acNo)!=null)
        {
            if(AccountServices.printPassbook(cs, acNo, tsr.getTransactions(acNo)))
            {
                return "Passbook printed successfully";
            } else {
                return "Error printing the passbook";
            }
        } else return "No transactions";
    }

}
