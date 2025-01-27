/*
 *********************************************************************************************************
 *  @Java Class Name : CustomerUI
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : CustomerUI contains menu for the Customer, also the implementation of each of
 *                     the option
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Constants.*;
import Controllers.*;
import Models.*;


public class CustomerUI {

    /*
     *********************************************************
     *  @Method Name    : Menu
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Displays input, validates input and executes the
     *                    corresponding method
     *  @param          : Scanner class object, current customer instance of type Customer
     *  @return         : void
     ********************************************************
     */

    static void Menu(Scanner sc, Customer customer)
    {
        System.out.println("Customer Menu: \n1. View account details \n2. Transfer money \n3. Create Account \n4. Check Account Balance/Credit Score \n5. Apply for Loan\n6. Print Passbook \n7. View Last 10 Transactions \n8. Logout");

        String option = "";

        boolean validInput = false;
        do {
            System.out.print("Choose an option (1-8): ");
            if (sc.hasNextLine()) {
                option = sc.nextLine().trim();

                if (option.matches("[1-8]")) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input! Please choose a valid option between 1 and 8.");
                }
            } else {
                sc.nextLine();
                System.out.println("Invalid input! Please enter a valid option between 1 and 8.");
            }
        } while (!validInput);

        switch (option) {
            case "1": viewAccountDetails(sc, customer); break;
            case "2": transferMoney(sc, customer); break;
            case "3": createAccount(sc, customer); break;
            case "4": checkBalanceAndCreditScore(sc, customer); break;
            case "5": applyForLoan(sc, customer); break;
            case "6": printPassbook(sc, customer); break;
            case "7": viewLastTenTransactions(sc, customer); break;
            case "8": Login.login(); break;
        }
    }

    /*
     *********************************************************
     *  @Method Name    : viewAccountDetails
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : submenu of Menu method which displays the submenu,
     *                    validates input and executes the corresponding method
     *  @param          : Scanner class object, current customer instance of type Customer
     *  @return         : void
     ********************************************************
     */

    private static void viewAccountDetails(Scanner sc, Customer customer)
    {
        System.out.println("Account Details Menu: \n1. View All Accounts Details \n2. View a specific Account details\n");
        String option = "";
        boolean validInput = false;
        do {
            System.out.print("Choose an option (1-2): ");
            if (sc.hasNextLine()) {
                option = sc.nextLine().trim();

                if (option.matches("[1-2]")) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input! Please choose a valid option between 1 and 2.");
                }
            } else {
                sc.nextLine();
                System.out.println("Invalid input! Please enter a valid option between 1 and 2.");
            }
        } while (!validInput);

        if(option.matches("1"))
        {
            List<Account> accounts = CustomerController.getAccountsforCustomer(customer.getUsername());
            for(var i: accounts)
            {
                System.out.println("Account Holder Name: " + i.getAccountHolder() + "\nAccount Number: " + i.getAccountNumber() + "\nAccount Type: " + i.getAccountType() + "\nAccount Balance: " + i.getBalance());
                System.out.println("---------------------------");
            }
        }
        else {
            System.out.println("Account number: ");
            long acNo = sc.nextLong();

            while(!String.valueOf(acNo).matches(Regex.ACCOUNT_NUMBER_REGEX))
            {
                System.out.println("Please enter 12-digit account number");
                acNo = sc.nextLong();
            }
            sc.nextLine();

            Account ac = AccountController.getAccount(acNo);

            if(ac!=null)
            {
                System.out.println("Account Number: " + ac.getAccountNumber() + "\nAccount Type: " + ac.getAccountType() + "\nAccount Balance: " + ac.getAccountType());
                System.out.println("---------------------------");
            }
            else System.out.println("ACCOUNT NOT FOUND");
        }
    }

    /*
     *********************************************************
     *  @Method Name    : transferMoney
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : displays different money transfer options, validates input and
     *                    then executes the corresponding logic
     *  @param          : Scanner class object, current customer instance of type Customer
     *  @return         : void
     ********************************************************
     */

    private static void transferMoney(Scanner sc, Customer customer) {

        System.out.println("Transfer Money Menu: \n1. Self Transfer \n2. Transfer to other account\n3. Withdraw Money\n4. Undo Transaction");

        int option = 0;
        boolean validInput = false;

        do {
            System.out.print("Choose an option (1-4): ");
            if (sc.hasNextInt()) {
                option = sc.nextInt();
                sc.nextLine();
                if (option >= 1 && option <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input! Please choose a valid option between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
            }
        } while (!validInput);


        if (option == 1) {
            long acNo = -1;

            while (true) {
                System.out.println("Enter the account number: ");

                String input = sc.nextLine().trim();

                if (input.matches(Regex.ACCOUNT_NUMBER_REGEX)) {
                    acNo = Long.parseLong(input);
                    break;
                } else {
                    System.out.println("Invalid account number. Please enter a 12-digit account number.");
                }
            }

            Account senderAccount = AccountController.getAccount(acNo);

            if (senderAccount == null) {
                System.out.println("Account not found!");
                return;
            } else if (!senderAccount.isVerified())
            {
                System.out.println("Account status not verified");
                return;
            }
            System.out.println("****Self-Transfer****\nReceiver Account Details:\n" +
                    "Name: " + senderAccount.getAccountHolder() +
                    "\nAccount No: " + senderAccount.getAccountNumber() +
                    "\nType: " + senderAccount.getAccountType() +
                    "\nBalance: " + senderAccount.getBalance() +
                    "\n---------------------------");

            int amount = 0;
            while (true) {
                System.out.println("Enter the amount: ");

                String input = sc.nextLine().trim();

                if (input.matches("[0-9]{1,10}")) {
                    amount = Integer.parseInt(input);
                    break;
                } else {
                    System.out.println("Enter amount > 0");
                }
            }
            System.out.println(AccountController.transferMoney(senderAccount.getAccountNumber(), amount+senderAccount.getBalance(), TransactionType.DEPOSIT));

        } else if (option == 2) {
            System.out.println("****Account-Transfer****\nAccount details of receiver:\n");

            long receiverAcNo = -1;

            while (true) {
                System.out.println("Enter the account number: ");

                String input = sc.nextLine().trim();

                if (input.matches(Regex.ACCOUNT_NUMBER_REGEX)) {
                    receiverAcNo = Long.parseLong(input);
                    break;
                } else {
                    System.out.println("Invalid account number. Please enter a 12-digit account number.");
                }
            }

            Account receiverAccount = AccountController.getAccount(receiverAcNo);

            if (receiverAccount == null) {
                System.out.println("Account not found!");
                return;
            } else if (!receiverAccount.isVerified())
            {
                System.out.println("Account status not verified");
                return;
            }

            System.out.println("Receiver Account Number: " + receiverAccount.getAccountNumber());
            System.out.println("Receiver Account Type: " + receiverAccount.getAccountType());
            System.out.println("Receiver Account Balance: " + receiverAccount.getBalance());
            System.out.println("---------------------------");

            int amount = 0;
            while (true) {
                System.out.println("Enter the amount: ");

                String input = sc.nextLine().trim();

                if (input.matches("[0-9]{1,10}")) {
                    amount = Integer.parseInt(input);
                    break;
                } else {
                    System.out.println("Enter amount > 0");
                }
            }

            System.out.println(AccountController.transferMoney(receiverAccount.getAccountNumber(), amount+receiverAccount.getBalance(), TransactionType.DEPOSIT));


        } else if (option == 3) {
            System.out.println("****WITHDRAW****\nAccount details:");

            List<Account> accounts = CustomerController.getAccountsforCustomer(customer.getUsername());
            accounts.forEach(account ->
                    System.out.println("Account Number: " + account.getAccountNumber() +
                            " | Balance: " + account.getBalance() +
                            " | Account Type: " + account.getAccountType() + " | Account Status: " +account.isVerified()));

            long withdrawingAcNo = -1;
            while (true) {
                System.out.println("Enter the account number: ");

                String input = sc.nextLine().trim();

                if (input.matches(Regex.ACCOUNT_NUMBER_REGEX)) {
                    withdrawingAcNo = Long.parseLong(input);
                    break;
                } else {
                    System.out.println("Invalid account number. Please enter a 12-digit account number.");
                }
            }

            Account withAc = AccountController.getAccount(withdrawingAcNo);

            if (withAc == null) {
                System.out.println("Account not found!");
                return;
            } else if(!withAc.isVerified())
            {
                System.out.println("Account not verified");
                return;
            }

            int amount = 0;
            while (true) {
                System.out.println("Enter the amount: ");

                String input = sc.nextLine().trim();

                if (input.matches("[0-9]{1,10}")) {
                    amount = Integer.parseInt(input);
                    break;
                } else {
                    System.out.println("Enter amount > 0");
                }
            }
            if (amount > withAc.getBalance()) {
                System.out.println("Insufficient balance.");
            } else {
                System.out.println(AccountController.transferMoney(withAc.getAccountNumber(), withAc.getBalance() - amount, TransactionType.WITHDRAW));
            }
        }
        else {
            undoTransaction(sc);
            }
    }

    /*
     *********************************************************
     *  @Method Name    : undoTransaction
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : takes input of the account number, displays all the transactions under that accounts
     *                    whose transaction is to be undone, and takes input of the transaction id
     *  @param          : Scanner class object
     *  @return         : void
     ********************************************************
     */

    static void undoTransaction(Scanner sc) {
        System.out.println("****Undo_Transaction****\nAccount number:\n");
        long undoAccount = sc.nextLong();
        sc.nextLine();

        while (!String.valueOf(undoAccount).matches(Regex.ACCOUNT_NUMBER_REGEX)) {
            System.out.println("Please enter a 12-digit account number");
            undoAccount = sc.nextLong();
            sc.nextLine();
        }

        Account account = AccountController.getAccount(undoAccount);
        if (account == null) {
            System.out.println("Receiver account not found!");
        } else {
            List<Transaction> transactions = TransactionController.getTransactions(undoAccount);

            if (transactions.isEmpty()) {
                System.out.println("No transactions found for this account.");
                return;
            }

            System.out.println("Transactions: ");
            for (Transaction t : transactions) {
                System.out.println("ID: " + t.id() + ", Timestamp: " + t.timestamp() + ", Amount: " + t.amount());
            }

            System.out.println("Enter the ID of the transaction to be undone: ");

            int id = -1;
            boolean valid = false;
            while (!valid) {
                if (sc.hasNextInt()) {
                    id = sc.nextInt();
                    sc.nextLine();
                    valid = true;
                } else {
                    System.out.println("Please enter a valid number.");
                    sc.nextLine();
                }
            }

            Transaction transaction = null;
            for (Transaction t : transactions) {
                if (t.id() == id) {
                    transaction = t;
                    break;
                }
            }

            if (transaction == null) {
                System.out.println("Transaction ID invalid");
            } else {
                System.out.println(TransactionController.undoTransaction(transaction.id(), undoAccount));
            }
        }
    }

    /*
     *********************************************************
     *  @Method Name    : createAccount
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : displays different account type options, validates input and
     *                    then executes the corresponding logic of the account creation
     *  @param          : Scanner class object, current customer instance of type Customer
     *  @return         : void
     ********************************************************
     */

    private static void createAccount(Scanner sc, Customer customer)
    {
        System.out.println("****ACCOUNT CREATION****");
        System.out.println("Select the account type:\n1. Savings\n2. Current\n3. Joint");
        String accountType = sc.nextLine();
        boolean flag2 = false;
        while(!accountType.equalsIgnoreCase("Savings") && !accountType.equalsIgnoreCase("Joint")  && !accountType.equalsIgnoreCase("Current"))
        {
            System.out.println("Please enter valid type");
            accountType = sc.nextLine();
        }

        List<String>owners = new ArrayList<>();

        if(accountType.equalsIgnoreCase("joint"))
        {
            boolean valid = false; int option;
            System.out.println("Enter the number of members: ");
            do{
                option = sc.nextInt();
                sc.nextLine();
                if (option >= 2) {
                    valid = true;
                } else {
                    System.out.println("Please enter a number greater than or equal to 2.");
                }
                if(sc.hasNextInt())
                {
                    valid = true;
                } else System.out.println("please enter valid number");
            }while(!valid);

            owners.add(customer.getUsername());

            for(int i = 1; i<option; i++)
            {
                boolean flag = false;
                System.out.println("Enter username of owner" + i+1 + ": ");
                String username = sc.nextLine();
                do{
                    if(username.matches(Regex.USER_REGEX))
                    {
                        flag = true;
                    } else {
                        System.out.println("Please enter valid username!");
                    }

                }while(!flag);


                if(CustomerController.getCustomerDetails(username)==null && UserController.getUser(username)==null)
                {
                    AdminUI.addUser(sc);

                } else
                {
                    System.out.println("User already exists");
                    System.out.println("Do you want a add the new joint account to the already existing customer?(Y/N) ");
                    String ans;
                    do {
                        System.out.println("Do you want to add the new joint account to the already existing customer? (Y/N)");
                        ans = sc.nextLine().toLowerCase().trim();

                        if (ans.equals("y") || ans.equals("yes")) {
                            flag2 = CustomerController.createAccount(accountType, username);
                            break;
                        } else if (ans.equals("n") || ans.equals("no")) {
                            break;
                        } else {
                            System.out.println("Please enter a valid option (Y or N).");
                        }
                    } while (true);


                    if(ans.matches("^(y|yes|Y)$"))
                    {
                        flag2 = CustomerController.createAccount(accountType, username);
                    }
                }
            }

            for(var i: owners)
            {
                flag2 = CustomerController.createAccount(accountType, i);
            }

        } else {
            flag2 = CustomerController.createAccount(accountType, customer.getUsername());
        }
        if(flag2) System.out.println("** Account creation successful. Submitted for verification **");
        else System.out.println("Account creation unsuccessful");
    }

    /*
     *********************************************************
     *  @Method Name    : checkBalanceAndCreditScore
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : displays balance and credit score, validates input and
     *                    then executes the corresponding logic
     *  @param          : Scanner class object, current customer instance of type Customer
     *  @return         : void
     ********************************************************
     */

    static void checkBalanceAndCreditScore(Scanner sc, Customer customer) {
        System.out.println("****CHECK BALANCE AND CREDIT SCORE****");
        boolean flag = true;
        do {
            System.out.println("Menu: \n1. Check Balance\n2. Check Credit Score\n");

            int option = sc.nextInt();
            sc.nextLine();
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
                    System.out.println("****BALANCE ENQUIRY****\n1. Check balance of a single account\n2. Check balance of all active accounts");

                    int option2 = sc.nextInt();
                    sc.nextLine();
                    boolean validInput2 = false;

                    do {
                        System.out.print("Choose an option (1-2): ");
                        if (sc.hasNextInt()) {
                            option2 = sc.nextInt();
                            sc.nextLine();
                            if (option2 >= 1 && option2 <= 2) {
                                validInput2 = true;
                            } else {
                                System.out.println("Invalid input! Please choose a valid option between 1 and 2.");
                            }
                        } else {
                            System.out.println("Invalid input! Please enter a number.");
                            sc.nextLine();
                        }
                    } while (!validInput2);

                    switch (option2) {
                        case 1:
                            System.out.println("Enter account number: ");
                            long acNo = sc.nextLong();
                            while(!String.valueOf(acNo).matches(Regex.ACCOUNT_NUMBER_REGEX)) {
                                System.out.println("Please enter a 12-digit account number");
                                acNo = sc.nextLong();
                            }
                            sc.nextLine();
                            Account ac = AccountController.getAccount(acNo);

                            if(ac != null) {
                                System.out.println("Balance: " + ac.getBalance());
                            } else {
                                System.out.println("Account not found.");
                            }
                            break;

                        case 2:
                            for (Account account : CustomerController.getAccountsforCustomer(customer.getUsername())) {
                                System.out.println("Account Number: " + account.getAccountNumber() + " | Balance: " + account.getBalance());
                            }
                            break;

                        default:
                            System.out.println("Please enter a valid option.");
                    }
                    break;

                case 2:
                    System.out.println("****CREDIT SCORE****");
                    System.out.println("Customer Name: " + customer.getName() + "\nCredit Score: " + customer.getCreditScore());
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1 or 2.");
            }

            String temp;
            do {
                System.out.println("Go back to menu? (Y/N): ");
                temp = sc.nextLine().trim().toLowerCase();
                switch (temp) {
                    case "y":
                    case "yes":
                        break;
                    case "n":
                    case "no":
                        flag = false;
                        break;
                    default:
                        System.out.println("Invalid input! Please enter 'Y' or 'N'.");
                }
            } while (!temp.equals("y") && !temp.equals("yes") && !temp.equals("n") && !temp.equals("no"));

        } while (flag);
    }

    /*
     *********************************************************
     *  @Method Name    : applyForLoan
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : displays different loan category options, validates input and
     *                    then executes the corresponding logic for loan application
     *  @param          : Scanner class object, current customer instance of type Customer
     *  @return         : void
     ********************************************************
     */

    private static void applyForLoan(Scanner sc, Customer customer)
    {
        System.out.println("****LOAN APPLICATION****");
        System.out.println("Select the loan category (Personal/Mortgage/Business)");
        String cat = sc.nextLine().trim().toLowerCase().split(" ")[0];
        while(!cat.equals("personal") && !cat.equals("mortgage") && !cat.equals("Business"))
        {
            System.out.println("please enter appropriate category");
            cat = sc.nextLine().trim().toLowerCase().split(" ")[0];
        }
        int amount;

        while (true) {
            System.out.print("Please enter a valid amount: ");
            if (sc.hasNextInt()) {
                amount = sc.nextInt();
                sc.nextLine();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine();
            }
        }

        if(amount>50_00_000 && (CustomerController.applyLoan(customer, amount, cat)))
        {
            System.out.println("Loan applied");
        }
        else if(amount<50_00_000) System.out.println("Loan Approved");
        else System.out.println("Error applying for the loan");
    }

    /*
     *********************************************************
     *  @Method Name    : printPassbook
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : saves all the transactions of an account to a file and saves it locally
     *  @param          : Scanner class object, current customer instance of type Customer
     *  @return         : void
     ********************************************************
     */

    private static void printPassbook(Scanner sc, Customer customer)
    {
        long acNo = -1;
        String input;
        while (true) {
            System.out.println("Enter the account number: ");

             input = sc.nextLine().trim();

            if (input.matches(Regex.ACCOUNT_NUMBER_REGEX)) {
                acNo = Long.parseLong(input);
                break;
            } else {
                System.out.println("Invalid account number. Please enter a 12-digit account number.");
            }
        }

        if(!AccountController.getAccount(acNo).isVerified())
        {
            System.out.println("Account not yet verified");
            return;
        }
        while(!String.valueOf(acNo).matches(Regex.ACCOUNT_NUMBER_REGEX))
        {
            System.out.println("Please enter 12-digit account number");
            acNo = sc.nextLong();
        }
        sc.nextLine();
        System.out.println(AccountController.printPassbook(customer, acNo));
    }

    /*
     *********************************************************
     *  @Method Name    : viewLastTenTransaction
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : displays the last ten transactions in an account
     *  @param          : Scanner class object, current customer instance of type Customer
     *  @return         : void
     ********************************************************
     */

    private static void viewLastTenTransactions(Scanner sc, Customer customer) {
        long acNo = -1;

        while (true) {
            System.out.println("Enter the account number: ");
            String input = sc.nextLine().trim();

            if (input.matches(Regex.ACCOUNT_NUMBER_REGEX)) {
                acNo = Long.parseLong(input);
                break;
            } else {
                System.out.println("Invalid account number. Please enter a 12-digit account number.");
            }
        }
            List<Account> accounts = CustomerController.getAccountsforCustomer(customer.getUsername());

            Account ac = AccountController.getAccount(acNo);

            if (ac == null || accounts.contains(ac)) {
                System.out.println("Account not found!");
                return;
            } else if (!ac.isVerified()) {
                System.out.println("Account status not verified");
                return;
            }

            List<Transaction> tn = TransactionController.getTransactions(acNo);
            int numTransactions = Math.min(tn.size(), 10);

            for (int i = 1; i <= numTransactions; i++) {
                System.out.println(tn.get(tn.size() - i).toString());
            }
        }
}
