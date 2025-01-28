/*
 *********************************************************************************************************
 *  @Java Class Name : CustomerController
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Manages various customer-related operations such as creating accounts, applying for loans etc.
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Controllers;

import Models.Account;
import Models.Customer;
import Models.Loan;
import Services.CustomerServices;
import Services.LoanServices;

import java.util.List;
import java.util.Random;

public class CustomerController {

    /*
     *********************************************************
     *  @Method Name    : getCustomers
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns list of customers
     *  @param          : none
     *  @return         : List<Customer>
     ********************************************************
     */

    public static List<Customer> getCustomers()
    {
        return CustomerServices.getCustomers();
    }

    /*
     *********************************************************
     *  @Method Name    : createCustomer
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : creates a customer with the given fields and returns the result
     *  @param          : String username, String name, String aadhar, String pan, int creditScore
     *  @return         : boolean
     ********************************************************
     */
    public static boolean createCustomer(String username, String name, String aadhar, String pan, int creditScore)
    {
            return CustomerServices.createCustomer(username, name, aadhar, pan, creditScore);
    }

    /*
     *********************************************************
     *  @Method Name    : getCustomerDetails
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns a customer with the provided username
     *  @param          : String username
     *  @return         : Customer
     ********************************************************
     */
    public static Customer getCustomerDetails(String username)
    {
        return CustomerServices.getCustomer(username);
    }

    /*
     *********************************************************
     *  @Method Name    : getAcccountsforCustomer
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns a list of all accounts under the customer
     *  @return         : List<Account>
     ********************************************************
     */
    public static List<Account> getAccountsforCustomer(String username)
    {
        return CustomerServices.getAccountsForCustomer(username);
    }

    /*
     *********************************************************
     *  @Method Name    : createAccount
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Creates an account and returns the result
     *  @param          : String type, String username
     *  @return         : boolean
     ********************************************************
     */
    public static boolean createAccount(String type, String username)
    {

        Random random = new Random();
        long accountNumber = 100000000000L + (long)(random.nextDouble() * 900000000000L);

        while(AccountController.getAccount(accountNumber)!=null)
        {
            accountNumber = 100000000000L + (long)(random.nextDouble() * 900000000000L);
        }
        return CustomerServices.createAccount(type, username, accountNumber);
    }

    /*
     *********************************************************
     *  @Method Name    : applyForLoan
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : creates a loan with the given fields and returns the result
     *  @param          : Customer customer, int amount, String cat
     *  @return         : boolean
     ********************************************************
     */
    public static boolean applyLoan(Customer customer, int amount, String cat)
    {
        Random random = new Random();
        int id = 10000 + (random.nextInt()*90000);

        boolean requires;
        requires = amount > 50_00_000;
        return CustomerServices.applyForLoan(amount, cat, requires, customer.getUsername(), id);

    }

    /*
     *********************************************************
     *  @Method Name    : getUnapprovedLoan
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns a list of all the loans under the customer with the given username
     *  @param          : String username
     *  @return         : List<Loan>
     ********************************************************
     */
    public static List<Loan> getUnapprovedLoan(String username)
    {
        return LoanServices.getUnapprovedLoans(username);
    }


}
