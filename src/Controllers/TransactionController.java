/*
 *********************************************************************************************************
 *  @Java Class Name : TransactionController
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Manages various transaction-related operations such as retrieving, verifying transactions etc.
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */


package Controllers;

import Constants.Status;
import Constants.TransactionType;
import Models.Transaction;
import Services.TransactionServices;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;

public class TransactionController {

    /*
     *********************************************************
     *  @Method Name    : insertTransaction
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : insert a new transaction with the given fields
     *  @param          : long ac, int amount, TransactionType type, Status status
     *  @return         : void
     ********************************************************
     */

    public static void insertTransaction(long ac, int amount, TransactionType type, Status status)
    {
        TransactionServices.insertTransaction(ac, amount, type, status);
    }

    /*
     *********************************************************
     *  @Method Name    : getTransactions
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : return the List of all transaction related to the provided account number
     *  @param          : long ac
     *  @return         : List<Transaction>
     ********************************************************
     */

    public static List<Transaction> getTransactions(long ac)
    {
        return TransactionServices.getTransactions(ac);
    }

    /*
     *********************************************************
     *  @Method Name    : undoTransaction
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : undoes a transaction
     *  @param          : int id, long ac
     *  @return         : String
     ********************************************************
     */

    public static String undoTransaction(int id, long ac)
    {
        List<Transaction>transactions = TransactionServices.getTransactions(ac);

        for(var i: transactions)
        {
            if(i.id() == id)
            {
                Timestamp now = new Timestamp(System.currentTimeMillis());
                Duration duration = Duration.ofMillis(now.getTime() - i.timestamp().getTime());

                if (duration.toMinutes() < 10) {
                    TransactionServices.undoTransaction(id);
                    return "Transaction undone";
                }
                else return "Transaction cannot be undone";
            }
        }
        return "";
    }

}
