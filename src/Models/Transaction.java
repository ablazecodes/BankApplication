/*
 *********************************************************************************************************
 *  @Java Class Name : Transaction
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Represents a Transaction with attributes such as id, amount, type, timestamp etc.
 *                     and includes methods to retrieve these values.
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Models;
import Constants.*;
import java.sql.Timestamp;

public record Transaction(int id, int amount, TransactionType type, Status status, Timestamp timestamp) {

    /*
     *********************************************************
     *  @Method Name    : timestamp
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for timestamp
     *  @param          : none
     *  @return         : timestamp
     ********************************************************
     */
    @Override
    public Timestamp timestamp() {
        return timestamp;
    }

    /*
     *********************************************************
     *  @Method Name    : status
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for status
     *  @param          : none
     *  @return         : Status
     ********************************************************
     */
    @Override
    public Status status() {
        return status;
    }

    /*
     *********************************************************
     *  @Method Name    : type
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for type
     *  @param          : none
     *  @return         : TransactionType
     ********************************************************
     */
    @Override
    public TransactionType type() {
        return type;
    }

    /*
     *********************************************************
     *  @Method Name    : amount
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for amount
     *  @param          : none
     *  @return         : integer
     ********************************************************
     */
    @Override
    public int amount() {
        return amount;
    }

    /*
     *********************************************************
     *  @Method Name    : id
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for id
     *  @param          : none
     *  @return         : integer
     ********************************************************
     */
    @Override
    public int id() {
        return id;
    }

    /*
     *********************************************************
     *  @Method Name    : toString
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : overridden toString method for objects of Transaction type
     *  @param          : none
     *  @return         : String
     ********************************************************
     */
    @Override
    public String toString() {
        return "Transaction id=" + id +
                ", amount= " + amount +
                ", type= " + type +
                ", status= " + status +
                ", Timestamp= " + timestamp;
    }
}

