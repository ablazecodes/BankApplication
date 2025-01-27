/*
 *********************************************************************************************************
 *  @Java Class Name : AccountServices
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Contains services related to Accounts, such as withdraw, deposit etc
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Services;

import Models.*;
import Repositories.AccountRepository;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import static Constants.AccountConstants.*;

public class AccountServices {

    static AccountRepository asr = AccountRepository.getInstance();

    /*
     *********************************************************
     *  @Method Name    : withdraw
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : updates the account balance and returns if the process was successful
     *  @param          : long acNo, int balance
     *  @return         : boolean
     ********************************************************
     */

    public static boolean withdraw(long acNo, int balance) {
        return asr.updateAccountBalance(acNo, balance);
    }

    /*
     *********************************************************
     *  @Method Name    : deposit
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : updates the account balance and returns if the process was successful
     *  @param          : long acNo, int balance
     *  @return         : boolean
     ********************************************************
     */

    public static boolean deposit(long acNo, int balance) {
        return asr.updateAccountBalance(acNo, balance);
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

    public static synchronized boolean printPassbook(Customer cs, long acNo, List<Transaction>transactions) {
        File file = new File("C:\\Users\\DELL-2\\Downloads\\passbook.txt");

        Account ac = asr.getAccount(acNo);

        try (PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(file, false)))) {
            pr.printf("**** Passbook ****\n");
            pr.printf("Account Number: %d\n", acNo);
            pr.printf("Account Holder: %s\n", cs.getName());
            pr.printf("Account Type: %s\n", ac.getAccountType());
            pr.printf("*********************************************************************************************************\n");
            pr.printf("%-8s | %-10s | %-8s | %-10s | %-12s\n", "ID", "AMOUNT", "TYPE", "STATUS", "TIME");
            pr.printf("*********************************************************************************************************\n");

            for (var i : transactions) {
                pr.printf("%-8d | %-10d | %-8s | %-10s | %-12s\n",
                        i.id(),
                        i.amount(),
                        i.type(),
                        i.status(),
                        i.timestamp());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

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
        return asr.getAccount(ac);
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

    public static synchronized boolean changeAccountVerification(boolean flag, long acNo) {
        return asr.changeAccountVerification(flag, acNo);
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
        return asr.getUnapprovedAccounts();
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

    public static synchronized boolean deleteAccount(long ac)
    {
        return asr.deleteAccount(ac);
    }

}
