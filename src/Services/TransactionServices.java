/*
 *********************************************************************************************************
 *  @Java Class Name : TransactionServices
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Contains services related to transactions
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Services;

import Constants.Status;
import Constants.TransactionType;
import Models.Transaction;
import Repositories.TransactionRepository;

import java.sql.Timestamp;
import java.util.List;

public class TransactionServices {
    static TransactionRepository tsr = TransactionRepository.getInstance();
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
        tsr.insertTransaction(ac, amount, type, status.toString(), new Timestamp(System.currentTimeMillis()));
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
        return tsr.getTransactions(ac);
    }

    /*
     *********************************************************
     *  @Method Name    : undoTransaction
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : undoes a transaction
     *  @param          : int id
     *  @return         : boolean
     ********************************************************
     */

    public static boolean undoTransaction(int id)
    {
        return tsr.undoTransaction(id);
    }

}
