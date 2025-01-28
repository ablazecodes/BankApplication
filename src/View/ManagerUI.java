/*
 *********************************************************************************************************
 *  @Java Class Name : ManagerUI
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : ManagerUI contains menu for the Manager, also the implementation of each of
 *                     the option
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package View;

import Constants.*;
import Controllers.*;
import Models.*;
import Utilities.InputValidator;

import java.util.List;
import java.util.Scanner;

public class ManagerUI {

    /*
     *********************************************************
     *  @Method Name    : Menu
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Displays input, validates input and executes the
     *                    corresponding method
     *  @param          : Scanner class object
     *  @return         : void
     ********************************************************
     */

    static void Menu(Scanner sc)
    {
        boolean flag;
        int option = 0;
        do {
            System.out.println("**** Manager Menu: ****");
            System.out.println("1. Manage Loans");
            System.out.println("2. Review Transaction Report");
            System.out.println("3. View Employees");
            System.out.println("4. Get Customer History");
            System.out.println("5. Logout");

            boolean validInput = false;
            do {
                System.out.print("Choose an option (1-5): ");
                if (sc.hasNextInt()) {
                    option = sc.nextInt();
                    sc.nextLine();
                    if (option >= 1 && option <= 5) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid input! Please choose a valid option between 1 and 5.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.nextLine();
                }
            } while (!validInput);


            switch (option) {
                case 1:
                    manageLoans(sc);
                    break;
                case 2:
                    reviewTransactionReport(sc);
                    break;
                case 3:
                    viewEmployees();
                    break;
                case 4:
                    AdminUI.viewCustomerHistory(sc);
                    break;
                case 5:
                    Login.login();
                    break;
                default:
                    System.out.println("Please choose valid menu option");
                    break;
            }

            flag = InputValidator.menuInputValidation(sc);

        } while (flag);
    }

    /*
     *********************************************************
     *  @Method Name    : manageLoans
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : submenu of Menu method which displays the submenu,
     *                    validates input and executes the corresponding method
     *  @param          : Scanner class object
     *  @return         : void
     ********************************************************
     */

    static void manageLoans(Scanner sc)
    {
        boolean flag;
        int option = 0;
        do{
            System.out.println("**** Manage Loans ****\n1. View all Unapproved Loans\n2. Approve Loan");
            boolean validInput = false;

            do {
                System.out.print("Choose an option (1-2): ");
                if (sc.hasNextInt()) {
                    option = sc.nextInt();
                    sc.nextLine();
                    if (option >= 1 && option <= 2) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid input! Please choose a valid option between 1 and 2.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.nextLine();
                }
            } while (!validInput);

            switch (option) {

                case 1:
                    System.out.println("***UNAPPROVED LOANS***");
                    System.out.println("Enter customer username: ");
                    String username = sc.nextLine().trim();
                    while(!username.matches(Regex.USER_REGEX))
                    {
                        System.out.println("Please enter valid username");
                        username = sc.nextLine().trim();
                    }
                    List<Loan> unapproved = CustomerController.getUnapprovedLoan(username);
                    if(unapproved!=null)
                    {
                        for(var i: unapproved)
                            System.out.println(i.toString());
                    }
                    else System.out.println("No unapproved loans found");
                    break;

                case 2:
                    System.out.println("****APPROVE LOANS****");
                    System.out.println("Enter Loan ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    while(id<=0)
                    {
                        System.out.println("Loan ID cannot be blank");
                        id = sc.nextInt();
                        sc.nextLine();
                    }
                    if(LoanController.approveLoan(id)) System.out.println("Approved");
                    else System.out.println("Error approving the loan");
                    break;
                default:
                    System.out.println("Please choose valid menu option");
                    break;
            }
            flag = InputValidator.menuInputValidation(sc);
        } while(flag);
    }

    /*
     *********************************************************
     *  @Method Name    : reviewTransactionReport
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Displays the details of a transaction
     *  @param          : Scanner class object
     *  @return         : void
     ********************************************************
     */

    static void reviewTransactionReport(Scanner sc) {
        System.out.println("****TRANSACTION REPORT****");

        System.out.println("Enter Account Number: ");
        long acNo = sc.nextLong();

        while(!String.valueOf(acNo).matches(Regex.ACCOUNT_NUMBER_REGEX))
        {
            System.out.println("Please enter 12-digit account number");
            acNo = sc.nextLong();
        }
        sc.nextLine();

        System.out.println("**** Transactions ****\n");

        if(TransactionController.getTransactions(acNo).isEmpty())
        {
            System.out.println("No transactions for account " + acNo);
        }
        else {
            System.out.println("*************************************************************");
            System.out.printf("%-10s | %-8s | %-10s | %-12s\n", "AMOUNT", "TYPE", "STATUS", "TIME");
            System.out.println("*************************************************************\n");

            for (var i : TransactionController.getTransactions(acNo)) {
                System.out.printf("%-10d | %-8s | %-10s | %-12s\n",
                        i.amount(),
                        i.type(),
                        i.status(),
                        i.timestamp());
            }
        }
    }

    /*
     *********************************************************
     *  @Method Name    : viewEmployees
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : displays all the employees with their username, and role
     *  @param          : none
     *  @return         : void
     ********************************************************
     */

    static void viewEmployees()
    {
        List<User>users = UserController.getUsers();
        System.out.println("****EMPLOYEES****");
        for(var i: users)
        {
            if(i.getRole()!= Role.CUSTOMER)
            {
                System.out.println("Username: " + i.getUsername() + " | " + "Role: " + i.getRole().toString());
            }
        }
    }

}
