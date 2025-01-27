/*
 *********************************************************************************************************
 *  @Java Class Name : Bank
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Represents a bank with attributes such as minimum balance, bank code, bank name, address etc.
 *                     and includes methods to retrieve these values.
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Models;

public class Bank {

    private int minBalance;
    private int bankCode;
    private String bankName;
    private String address;
    private int contactNumber;

    /*
     *********************************************************
     *  @Method Name    : Bank
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Parameterized constructor of class Bank
     *  @param          : int minBalance, int bankCode, String bankName, String address, int contactNumber
     *  @return         : void
     ********************************************************
     */
    public Bank(int minBalance, int bankCode, String bankName, String address, int contactNumber) {
        this.minBalance = minBalance;
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    /*
     *********************************************************
     *  @Method Name    : getMinBalance
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for minimum balance
     *  @param          : none
     *  @return         : int
     ********************************************************
     */
    public int getMinBalance() {
        return minBalance;
    }

    /*
     *********************************************************
     *  @Method Name    : getBankCode
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for bank code
     *  @param          : none
     *  @return         : int
     ********************************************************
     */
    public int getBankCode() {
        return bankCode;
    }

    /*
     *********************************************************
     *  @Method Name    : getBankName
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for bank name
     *  @param          : none
     *  @return         : String
     ********************************************************
     */
    public String getBankName() {
        return bankName;
    }

    /*
     *********************************************************
     *  @Method Name    : getAddress
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for bank address
     *  @param          : none
     *  @return         : String
     ********************************************************
     */
    public String getAddress() {
        return address;
    }

    /*
     *********************************************************
     *  @Method Name    : getContactNumber
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for contact number
     *  @param          : none
     *  @return         : int
     ********************************************************
     */
    public int getContactNumber() {
        return contactNumber;
    }

}
