/*
 *********************************************************************************************************
 *  @Java Class Name : AdminUI
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : AdminUI contains menu for the admin, also the implementation of each of
 *                     the option
 *
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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Constants.Regex.*;

public class AdminUI {

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
            System.out.println("**** Admin Menu: ****");
            System.out.println("1. View all users");
            System.out.println("2. Manage accounts and users");
            System.out.println("3. View Customer History");

            boolean validInput = false;
            do {
                System.out.print("Choose an option (1-3): ");
                if (sc.hasNextInt()) {
                    option = sc.nextInt();
                    sc.nextLine();
                    if (option >= 1 && option <= 3) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid input! Please choose a valid option between 1 and 3.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.nextLine();
                }
            } while (!validInput);

            switch (option) {
                case 1:
                    viewAllUsers();
                    break;
                case 2:
                    manageAccounts(sc);
                    break;
                case 3:
                    viewCustomerHistory(sc);
                    break;
                default:
                    System.out.println("Please choose a valid menu option.");
                    break;
            }

            flag = InputValidator.menuInputValidation(sc);

        } while (flag);
    }

    /*
     *********************************************************
     *  @Method Name    : viewAllUsers
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Displays the username, password, and role of all users
     *  @param          : none
     *  @return         : void
     ********************************************************
     */
    private static void viewAllUsers() {
        System.out.println("*** User Data ****");
        List<User> users = UserController.getUsers();
        for (var i : users) {
            System.out.println("Username: " + i.getUsername() + ", Password: " + i.getPassword() + ", Role: " + i.getRole());
        }
    }

    /*
     *********************************************************
     *  @Method Name    : manageAccounts
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : submenu of Menu method which displays the submenu,
     *                    validates input and executes the corresponding method
     *  @param          : Scanner class object
     *  @return         : void
     ********************************************************
     */

    private static void manageAccounts(Scanner sc) {

        boolean flag;
        int option = 0;
        do {
            System.out.println("**** Manage Accounts & Users: ****");
            System.out.println("1. Add User");
            System.out.println("2. Delete User");
            System.out.println("3. Modify role of an existing user");
            System.out.println("4. Approve Accounts");
            System.out.println("5. Delete(Soft) Accounts");
            System.out.println("6. Return to main menu");
            System.out.println("7. Logout");

            boolean validInput = false;
            do {
                System.out.print("Choose an option (1-7): ");
                if (sc.hasNextInt()) {
                    option = sc.nextInt();
                    sc.nextLine();
                    if (option >= 1 && option <= 7) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid input! Please choose a valid option between 1 and 7.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.nextLine();
                }
            } while (!validInput);

            switch (option) {
                case 1:
                    addUser(sc);
                    break;
                case 2:
                    deleteUser(sc);
                    break;
                case 3:
                    modifyRole(sc);
                    break;
                case 4:
                    approveAccounts(sc);
                    break;
                case 5:
                    deleteAccounts(sc);
                    break;
                case 6:
                    Menu(sc);
                case 7:
                    Login.login();
                default:
                    System.out.println("Enter valid menu option");
                    break;
            }
            flag = InputValidator.menuInputValidation(sc);

        } while (flag);
    }

    /*
     *********************************************************
     *  @Method Name    : deleteAccounts
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Takes the input, and validates the account number
     *                    of the account to be deleted
     *  @param          : Scanner class object
     *  @return         : void
     ********************************************************
     */
    private static void deleteAccounts(Scanner sc) {
        System.out.println("Enter Account Number: ");
        long ac = sc.nextLong();
        sc.nextLine();
        while (!String.valueOf(ac).matches(ACCOUNT_NUMBER_REGEX)) {
            System.out.println("Enter proper 12 digit number: ");
            ac = sc.nextLong();
            sc.nextLine();
        }
        if (AccountController.deleteAccount(ac)) System.out.println("Account deleted");
        else System.out.println("Account doesn't exist");
    }

    /*
     *********************************************************
     *  @Method Name    : deleteAccounts
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Takes the input, and validates them for creating the user
     *                    with username, password, and role. Extra fields like aadhar, pan etc.
     *                    when the user role is customer
     *  @param          : Scanner class object
     *  @return         : void
     ********************************************************
     */

    static void addUser(Scanner sc) {
        String username;
        boolean flag = false;
        String password;
        boolean flag2 = false;
        Role role;
        boolean flag4 = false;
        do {
            System.out.println("Enter the username (Begin with uppercase, followed by min 3 lowercase and with at least one digit: ");
            username = sc.nextLine().trim();

            if (username.matches(USER_REGEX)) flag = true;
            else System.out.println("Please enter valid username");
        } while (!flag);

        do {
            System.out.println("Set password: ");
            password = sc.nextLine().trim();

            if (password.matches(PASSWORD_REGEX)) flag2 = true;
            else
                System.out.println("Error! The password should contain at least one upper-case, one lower-case, one special character and should be of length 8 minimum");
        } while (!flag2);

        String roleInput;
        boolean validRole = false;

        do {
            System.out.println("Enter the new role: ");
            roleInput = sc.nextLine().trim().toUpperCase();

            if (InputValidator.stringValidator(roleInput) &&
                    (roleInput.equalsIgnoreCase("CASHIER") ||
                            roleInput.equalsIgnoreCase("CUSTOMER") ||
                            roleInput.equalsIgnoreCase("MANAGER"))) {
                validRole = true;
            } else {
                System.out.println("Please enter a valid role (CASHIER, CUSTOMER, or MANAGER):");
            }
        } while (!validRole);

        role = switch (roleInput) {
            case "CASHIER" -> Role.CASHIER;
            case "CUSTOMER" -> Role.CUSTOMER;
            case "MANAGER" -> Role.MANAGER;
            default -> null;
        };

        if (role == null) {
            System.out.println("Invalid role assigned.");
        }

        if (role == Role.CUSTOMER) {

            List<Customer> customers = CustomerController.getCustomers();
            List<String> aadhars = new ArrayList<>();
            List<String> pans = new ArrayList<>();
            if (customers != null) {
                for (var i : customers) {
                    aadhars.add(i.getAadharCard());
                    pans.add(i.getPanCard());
                }
            }
            String aadhar;
            String pan;
            String name;
            do {
                System.out.println("Customer name: ");
                name = sc.nextLine().trim();

                if (name.matches(NAME_REGEX))
                    flag4 = true;
                else System.out.println("Please enter valid name (Uppercase Letter followed by Lowercase)");
            } while (!flag4);

            System.out.println("Enter Aadhar Number: ");
            aadhar = sc.nextLine().trim();

            while (!aadhar.matches(AADHAR_REGEX)) {
                System.out.println("please enter proper aadhar number");
                System.out.println("Enter Aadhar Number: ");
                aadhar = sc.nextLine().trim();
            }

            if (!aadhars.isEmpty()) {
                if (aadhars.contains(aadhar)) {
                    System.out.println("Customer with the aadhar card already exists");
                    return;
                }
            }

            System.out.println("Enter Pan Card: ");
            pan = sc.nextLine().trim();

            while (!pan.matches(PAN_REGEX)) {
                System.out.println("please enter proper PAN number");
                System.out.println("Enter PAN Number: ");
                pan = sc.nextLine().trim();
            }

            if (!pan.isEmpty()) {
                if (pans.contains(pan)) {
                    System.out.println("Customer with the pan card already exists");
                    return;
                }
            }

            if (CustomerController.createCustomer(username, name, aadhar, pan, 0) && UserController.createUser(username, password, role))
                System.out.println("Customer created successfully");

        } else {
            //otherwise it is not a customer
            if (UserController.createUser(username, password, role)) System.out.println("User created");
        }
    }

    /*
     *********************************************************
     *  @Method Name    : deleteUser
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Takes the input, and validates the username
     *                    of the user to be deleted
     *  @param          : Scanner class object
     *  @return         : void
     ********************************************************
     */

    private static void deleteUser(Scanner sc) {
        System.out.println("**** Delete User ****");
        String username;
        System.out.println("Please enter valid username");
        username = sc.nextLine().trim();

        while (!username.matches(USER_REGEX)) {
            System.out.println("Please enter valid username");
            username = sc.nextLine();
        }

        if (AdminController.deleteUser(username)) {
            System.out.println("User deleted successfully");
        } else System.out.println("User not found");

    }

    /*
     *********************************************************
     *  @Method Name    : modifyRole
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Takes the input, and validates the username
     *                    of the user whose role is to be modified
     *  @param          : Scanner class object
     *  @return         : void
     ********************************************************
     */

    private static void modifyRole(Scanner sc) {
        System.out.println("Enter the username to be modified: ");
        String username = sc.nextLine().trim();

        while (!InputValidator.stringValidator(username) && username.matches(USER_REGEX)) {
            System.out.println("please enter valid username");
            username = sc.nextLine();
        }

        List<User> users = UserController.getUsers();
        for (var i : users) {
            if (i.getUsername().equals(username)) {
                System.out.println("**** Initiating Modification ****");
                System.out.println("Enter the new role(Manager/Models.Cashier/Customer): ");
                String role = sc.nextLine().trim();

                while (!InputValidator.stringValidator(role)) {
                    System.out.println("Enter the new role(Manager/Models.Cashier/Customer) properly: ");
                    role = sc.nextLine().trim();
                }

                if (AdminController.assignUserRole(username, role)) {
                    System.out.println("User role has been successfully modified");
                } else System.out.println("Unable to change user role");
            }
            break;
        }
        System.out.println("User not found");
    }

    /*
     *********************************************************
     *  @Method Name    : viewCustomerHistory
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Takes the input, and validates the PAN card number
     *                    of the customer whose history is to be viewed
     *  @param          : Scanner class object
     *  @return         : void
     ********************************************************
     */

    static void viewCustomerHistory(Scanner sc) {
        System.out.println("****VIEW CUSTOMER HISTORY****\nEnter Pan Card: ");
        String pancard = sc.nextLine();
        while (!pancard.matches(PAN_REGEX)) {
            System.out.println("Please enter valid pancard number!");
            System.out.println("Enter pancard number: ");
            pancard = sc.nextLine();
        }
        AdminController.viewCustomerHistory(pancard);
    }

    /*
     *********************************************************
     *  @Method Name    : approveAccounts
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : submenu of manageAccounts method which displays the submenu,
     *                    validates input and executes the corresponding method
     *  @param          : Scanner class object
     *  @return         : void
     ********************************************************
     */

    static void approveAccounts(Scanner sc) {
        boolean flag;
        System.out.println("**ACCOUNT APPROVALS**");
        do {
            System.out.println("\n1. Approve an account\n2. View Unapproved Account List\n3. Return to main menu");
            boolean validInput = false;
            int option = 0;
            do {
                System.out.print("Choose an option (1-3): ");
                if (sc.hasNextInt()) {
                    option = sc.nextInt();
                    sc.nextLine();
                    if (option >= 1 && option <= 3) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid input! Please choose a valid option between 1 and 3.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.nextLine();
                }
            } while (!validInput);

            switch (option) {
                case 1:
                    System.out.println("Enter the account number: ");
                    long ac = sc.nextLong();
                    sc.nextLine();

                    while (!String.valueOf(ac).matches(ACCOUNT_NUMBER_REGEX)) {
                        System.out.println("Please enter 12 digit account number");
                        ac = sc.nextLong();
                        sc.nextLine();
                    }

                    Account account = AccountController.getAccount(ac);
                    if (account != null) {
                        Customer customer = CustomerController.getCustomerDetails(account.getAccountHolder());
                        System.out.println("Account Number: " + account.getAccountNumber() + "\n Account Type: " + account.getAccountType() + "\nAccount Holder: " + customer.getName() + "\nAadhar Number: " + customer.getAadharCard() + "\nPAN Card: " + customer.getPanCard() + "\nAccount Status: " + account.isVerified());

                        String ans;
                        boolean f1 = false, f2 = false;

                        do {
                            System.out.println("Change Verification Status? (Y/N)");
                            ans = sc.nextLine().trim();

                            if (ans.equalsIgnoreCase("Y") || ans.equalsIgnoreCase("N")) {
                                f1 = true;
                            } else System.out.println("please enter valid input");

                        } while (!f1);

                        boolean flag2 = false;

                        do {
                            System.out.println("Change to (True/False): ");
                            String ans1 = sc.nextLine().toLowerCase();

                            if (ans1.equals("true") || ans1.equals("false")) {
                                flag2 = ans1.equals("true");
                                f2 = true;

                            } else System.out.println("please enter valid input");

                        } while (!f2);

                        if (ans.equalsIgnoreCase("Y")) {
                            if (AccountController.changeAccountVerification(flag2, ac)) {
                                System.out.println("Status changed successfully");
                            } else System.out.println("Error changing the verification status of the account");
                        }

                    } else System.out.println("Account not found");
                    break;
                case 2:
                    System.out.println("****UNAPPROVED ACCOUNTS****");
                    List<Account> unapprovedAccounts = AccountController.getUnapprovedAccounts();

                    if (!unapprovedAccounts.isEmpty()) {
                        for (var i : unapprovedAccounts) {
                            System.out.println("Account Number: " + i.getAccountNumber() + "\n Account Type: " + i.getAccountType() + "\nAccount Status: " + i.isVerified() + "\n*******************\n");
                        }
                    } else {
                        System.out.println("No unapproved accounts found");
                    }
                    break;
                case 3:
                    Menu(sc);
                default:
                    System.out.println("PLease enter valid option");
            }

            flag = InputValidator.menuInputValidation(sc);

        } while (flag);
    }
}