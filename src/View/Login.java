/*
 *********************************************************************************************************
 *  @Java Class Name : Login
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Login contains the entrypoint of the login page along with credential validation
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

import Models.Customer;

import java.util.List;
import java.util.Scanner;

public class Login {

    /*
     *********************************************************
     *  @Method Name    : login
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Manages the user login process, validates credentials,
     *                    and navigates to the appropriate role-based menu upon successful authentication.
     *  @param          : none
     *  @return         : void
     ********************************************************
     */

    static void login() {
        Scanner sc = new Scanner(System.in);

        if (!BankUI.isDetailSet()) {
            System.out.println("Bank details are not set. Please set them first.");
            if (!BankUI.setBankDetails()) {
                System.out.println("Failed to set up the bank details.");
                return;
            }
        }

        String username, password;
        System.out.println("**** LOGIN ****");

        int choice = -1;
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.print("Choose an option (1 or 2): ");

            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
                continue;
            }

            try {
                choice = Integer.parseInt(input);
                if (choice == 1 || choice == 2) {
                    validChoice = true;
                } else {
                    System.out.println("Invalid choice. Please select 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number (1 or 2).");
            }
        }

        boolean isAdmin = (choice == 1);
        boolean isValid = false;

        do {
            System.out.println(isAdmin ? "Enter admin username: " : "Enter username: ");
            username = sc.nextLine().trim();

            if (username.isEmpty()) {
                System.out.println("Username cannot be empty. Please try again.");
            } else if (isAdmin && !username.equals(Regex.ADMIN_USERNAME)) {
                System.out.println("Invalid admin username.");
            } else if (!isAdmin && !username.matches(Regex.USER_REGEX)) {
                System.out.println("Username must start with uppercase, contain at least 3 lowercase letters, and at least one digit.");
            } else {
                isValid = true;
            }
        } while (!isValid);

        boolean isPasswordValid = false;
        do {
            System.out.println("Enter password: ");
            password = sc.nextLine().trim();

            if (password.isEmpty()) {
                System.out.println("Password cannot be empty. Please try again.");
            } else if (isAdmin && !password.matches(Regex.ADMIN_PASSWORD)) {
                System.out.println("Invalid admin password.");
            } else if (!isAdmin && !password.matches(Regex.PASSWORD_REGEX)) {
                System.out.println("Password must be at least 8 characters long, contain at least one special character, one uppercase letter, one lowercase letter, and one number.");
            } else {
                isPasswordValid = true;
            }
        } while (!isPasswordValid);

        if (authenticate(username, password, isAdmin)) {
            System.out.println("Login successful! Welcome, " + username);

            if (isAdmin) {
                AdminUI.Menu(sc);
            } else {
                List<User> users = UserController.getUsers();
                User currentUser = null;

                for (User user : users) {
                    if (user.getUsername().equals(username)) {
                        currentUser = user;
                        break;
                    }
                }

                if (currentUser != null) {
                    showRoleBasedMenu(currentUser);
                } else {
                    System.out.println("User not found.");
                }
            }

        } else {
            System.out.println("Invalid credentials!");
        }
    }

    /*
     *********************************************************
     *  @Method Name    : authenticate
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Verifies the provided username and password for either admin or user login.
     *  @param          : String username, String password, boolean isAdmin
     *  @return         : boolean
     ********************************************************
     */

    private static boolean authenticate(String username, String password, boolean isAdmin) {
        if (isAdmin) {
            return username.equals(Regex.ADMIN_USERNAME) && password.equals(Regex.ADMIN_PASSWORD);
        } else {
            return UserController.verifyLogin(username, password);
        }
    }

    /*
     *********************************************************
     *  @Method Name    : showRoleBasedMenu
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Displays menu based on the role of the logged-in user
     *  @param          : User user
     *  @return         : void
     ********************************************************
     */

    private static void showRoleBasedMenu(User user) {
        Scanner sc = new Scanner(System.in);
        Role role = user.getRole();
        Customer customer = null;
        if(role == Role.CUSTOMER)
        {
            customer = CustomerController.getCustomerDetails(user.getUsername());
        }

        String flag;
        do {
            switch (role) {
                case ADMIN:
                    AdminUI.Menu(sc);
                    break;
                case CUSTOMER:
                    CustomerUI.Menu(sc, customer);
                    break;
                case MANAGER:
                    ManagerUI.Menu(sc);
                    break;
                case CASHIER:
                    CashierUI.Menu(sc);
                    break;
                default:
                    System.out.println("Invalid role!");
            }
            do {
                System.out.println("Do you want to go back to the main menu? (Y/N) ");
                flag = sc.nextLine().trim().toUpperCase();
                if (!flag.equalsIgnoreCase("Y") && !flag.equalsIgnoreCase("N")) {
                    System.out.println("Please enter valid option (Y or N)");
                }
            } while (!flag.equalsIgnoreCase("Y") && !flag.equalsIgnoreCase("N"));
        } while (flag.equalsIgnoreCase("Y"));
    }
}