/*
 *********************************************************************************************************
 *  @Java Class Name : CashierUI
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : CashierUI contains menu for the Cashier, also the implementation of each of
 *                     the option
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package View;

import Constants.*;
import Controllers.AccountController;
import Models.Account;
import Utilities.InputValidator;

import java.util.Scanner;

public class CashierUI {

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

    static void Menu(Scanner sc) {
        boolean flag;
        int option = 0;

        do {
            System.out.println("**** Models.Cashier Menu: ****");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Undo Transaction");
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
                    System.out.println("****DEPOSIT****\nEnter Account Number: ");
                    long acNo = sc.nextLong();
                    while (!String.valueOf(acNo).matches(Regex.ACCOUNT_NUMBER_REGEX)) {
                        System.out.println("Please enter a 12-digit account number");
                        acNo = sc.nextLong();
                    }
                    Account account = AccountController.getAccount(acNo);
                    if (account != null) {
                        System.out.println("Enter the amount to be deposited: ");
                        int amount = sc.nextInt();

                        while(!InputValidator.numberValidator(sc, amount) && amount<=0)
                        {
                            System.out.println("Please enter a number>=0");
                            amount = sc.nextInt();
                        }
                        AccountController.transferMoney(acNo, amount, TransactionType.CREDIT);
                    } else {
                        System.out.println("Account not found");
                    }
                    break;

                case 2:
                    System.out.println("****WITHDRAW****\nEnter Account Number: ");
                    acNo = sc.nextLong();
                    while (!String.valueOf(acNo).matches(Regex.ACCOUNT_NUMBER_REGEX)) {
                        System.out.println("Please enter a 12-digit account number");
                        acNo = sc.nextLong();
                    }
                    account = AccountController.getAccount(acNo);
                    if (account != null) {
                        System.out.println("Enter the amount to be withdrawn: ");
                        int amount = sc.nextInt();

                        while(!InputValidator.numberValidator(sc, amount) && amount<=0)
                        {
                            System.out.println("Please enter a number>=0");
                            amount = sc.nextInt();
                        }
                        AccountController.transferMoney(acNo, amount, TransactionType.DEBIT);
                    } else {
                        System.out.println("Account not found");
                    }
                    break;

                case 3:
                    System.out.println("****CHECK-BALANCE****\nEnter Account Number: ");
                    acNo = sc.nextLong();

                    while(!String.valueOf(acNo).matches(Regex.ACCOUNT_NUMBER_REGEX))
                    {
                        System.out.println("Please enter 12-digit account number");
                        acNo = sc.nextLong();
                    }
                    sc.nextLine();
                    System.out.println("Current balance: " + AccountController.getAccount(acNo).getBalance());
                    break;

                case 4:
                    CustomerUI.undoTransaction(sc);
                    break;

                case 5:
                    Login.login();
                    break;

                default:
                    System.out.println("Please choose a valid menu option");
                    break;
            }

            flag = InputValidator.menuInputValidation(sc);

        } while (flag);
    }

}
