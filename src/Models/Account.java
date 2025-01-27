/*
 *********************************************************************************************************
 *  @Java Class Name : Account
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Represents a bank account with attributes such as account number, type, holder, balance,
 *                     and verification status, and includes methods to retrieve these values.
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Models;

public class Account {
    private final long accountNumber;
    private final String accountType;
    private final String accountHolder;
    private int balance;
    private boolean isVerified;

    /*
     *********************************************************
     *  @Method Name    : getBalance
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Getter for balance
     *  @param          : none
     *  @return         : int
     ********************************************************
     */
    public int getBalance() {
        return balance;
    }

    /*
     *********************************************************
     *  @Method Name    : isVerified
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Getter for isVerified
     *  @param          : none
     *  @return         : boolean
     ********************************************************
     */
    public boolean isVerified() {
        return isVerified;
    }

    /*
     *********************************************************
     *  @Method Name    : Account
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Parameterized constructor of class Account
     *  @param          : long accountNumber, String accountType, String accountHolder, int balance, boolean isVerified
     *  @return         : void
     ********************************************************
     */
    public Account(long accountNumber, String accountType, String accountHolder, int balance, boolean isVerified) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.isVerified = isVerified;
    }

    /*
     *********************************************************
     *  @Method Name    : toString
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : overridden toString method for Account class objects
     *  @param          : none
     *  @return         : String
     ********************************************************
     */
    @Override
    public String toString() {
        return "Account Number " + accountNumber +
                ", Account Type= " + accountType +
                ", Account Holder= " + accountHolder +
                ", Account Balance= " + balance +
                ", Verification Status= " + isVerified;
    }

    /*
     *********************************************************
     *  @Method Name    : getAccountNumber
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Getter for account number
     *  @param          : none
     *  @return         : long
     ********************************************************
     */
    public long getAccountNumber() {
        return accountNumber;
    }

    /*
     *********************************************************
     *  @Method Name    : getAccountType
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Getter for account type
     *  @param          : none
     *  @return         : String
     ********************************************************
     */
    public String getAccountType() {
        return accountType;
    }

    /*
     *********************************************************
     *  @Method Name    : getAccountHolder
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Getter for account holder
     *  @param          : none
     *  @return         : String
     ********************************************************
     */
    public String getAccountHolder() {
        return accountHolder;
    }
}
