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
import Services.UserServices;

import java.util.List;
import java.util.Random;

public class CustomerController {

    public static List<Customer> getCustomers()
    {
        return CustomerServices.getCustomers();
    }

    public static boolean createCustomer(String username, String name, String aadhar, String pan, int creditScore)
    {
            return CustomerServices.createCustomer(username, name, aadhar, pan, creditScore);
    }

    public static Customer getCustomerDetails(String username)
    {
        return CustomerServices.getCustomer(username);
    }

    public static List<Account> getAccountsforCustomer(String username)
    {
        return CustomerServices.getAccountsForCustomer(username);
    }

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

    public static boolean applyLoan(Customer customer, int amount, String cat)
    {
        boolean requires;
        requires = amount > 50_00_000;
        return CustomerServices.applyForLoan(amount, cat, requires, customer.getUsername());

    }

    public static List<Loan> getUnapprovedLoan(String username)
    {
        return LoanServices.getUnapprovedLoans(username);
    }


}
