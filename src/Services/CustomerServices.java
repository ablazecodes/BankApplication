/*
 *********************************************************************************************************
 *  @Java Class Name : CustomerServices
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Contains services related to the Customer such as, creating accounts, applying loans etc.
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Services;

import Models.Customer;
import Repositories.AccountRepository;
import Repositories.CustomerRepository;
import Models.*;
import Repositories.LoanRepository;

import java.util.List;


public class CustomerServices {

    static CustomerRepository csr = CustomerRepository.getInstance();

    /*
     *********************************************************
     *  @Method Name    : getCustomers
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : return the list of all the customers present in the database
     *  @param          : none
     *  @return         : List<Customer>
     ********************************************************
     */

    public static List<Customer> getCustomers()
    {
        return csr.getCustomers();
    }

    /*
     *********************************************************
     *  @Method Name    : getCustomer
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns the customer with the provided username
     *  @param          : String username
     *  @return         : Customer
     ********************************************************
     */

    public static Customer getCustomer(String username)
    {
        return csr.getCustomerDetails(username);
    }

    /*
     *********************************************************
     *  @Method Name    : getAccountsForCustomer
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns all the account under the customer with the given username
     *  @param          : String username
     *  @return         : List<Account>
     ********************************************************
     */

    public static List<Account> getAccountsForCustomer(String username)
    {
        return csr.getAccountsForCustomer(username);
    }

    /*
     *********************************************************
     *  @Method Name    : createCustomer
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : creates a Customer with the given fields and return the result
     *  @param          : String username, String name, String aadhar, String pan, int creditScore
     *  @return         : boolean
     ********************************************************
     */

    public static boolean createCustomer(String username, String name, String aadhar, String pan, int creditScore)
    {
        return csr.createCustomer(username, name, aadhar, pan, creditScore);
    }

    /*
     *********************************************************
     *  @Method Name    : createAccount
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : creates an account with the given fields and return the result
     *  @param          : String type, String username, long acNo
     *  @return         : boolean
     ********************************************************
     */

    public static boolean createAccount(String type, String username, long acNo)
    {
        AccountRepository asr = AccountRepository.getInstance();
        return asr.createAccount(acNo, type, false, username);
    }

    /*
     *********************************************************
     *  @Method Name    : applyForLoan
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : creates a loan with the given fields and returns the result
     *  @param          : int amount, String cat, boolean requires, String username
     *  @return         : boolean
     ********************************************************
     */

    public static boolean applyForLoan(int amount, String cat, boolean requires, String username, int id)
    {
        LoanRepository lsr = LoanRepository.getInstance();
        return lsr.insertLoan(amount, cat, requires, username, id);
    }

}
