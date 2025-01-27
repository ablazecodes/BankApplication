/*
 *********************************************************************************************************
 *  @Java Class Name : BankUI
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : BankUI contains methods for setting up the bank details the first time application runs
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package View;

import Controllers.BankController;
import Models.Bank;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class BankUI {

    private static final String CONFIG_FILE = "C:\\Users\\DELL-2\\IdeaProjects\\BankApplication\\src\\config.properties";
    private static final String BANK_DETAILS_FILE = "C:\\Users\\DELL-2\\IdeaProjects\\BankApplication\\src\\bank_details.properties";  // New file for saving bank details
    static boolean isDetailSet = false;
    private static Bank bank;

    static {
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(CONFIG_FILE);
            properties.load(fis);
            isDetailSet = Boolean.parseBoolean(properties.getProperty("isDetailSet", "false"));
            fis.close();
            System.out.println("isDetailSet loaded: " + isDetailSet);

            if (isDetailSet) {
                FileInputStream bankFis = new FileInputStream(BANK_DETAILS_FILE);
                Properties bankProperties = new Properties();
                bankProperties.load(bankFis);
                bankFis.close();

                int min_balance = Integer.parseInt(bankProperties.getProperty("min_balance"));
                int bank_code = Integer.parseInt(bankProperties.getProperty("bank_code"));
                String bank_name = bankProperties.getProperty("bank_name");
                String address = bankProperties.getProperty("address");
                int contact_number = Integer.parseInt(bankProperties.getProperty("contact_number"));

                bank = new Bank(min_balance, bank_code, bank_name, address, contact_number);
                System.out.println("Bank loaded: " + bank);
            }

        } catch (IOException e) {
            System.out.println("Error loading config or bank details: " + e.getMessage());
        }
    }

    /*
     *********************************************************
     *  @Method Name    : isDetailsSet
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for the isDetailsSet static variable
     *  @param          : none
     *  @return         : boolean
     ********************************************************
     */

    public static boolean isDetailSet() {
        return isDetailSet;
    }

    /*
     *********************************************************
     *  @Method Name    : setBankDetails
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : method for setting the details of the bank and validating the input
     *                    when the user logs in for the first time
     *  @param          : none
     *  @return         : boolean
     ********************************************************
     */

    public static boolean setBankDetails() {
        if (isDetailSet) {
            System.out.println("Bank details are already set.");
            return true;
        }

        Scanner scanner = new Scanner(System.in);
        boolean isBankCreated = false;

        List<Bank> banks = BankController.getBanks();
        System.out.println("**** SET BANK DETAILS ****");

        System.out.print("Enter the bank name: ");
        String bank_name = scanner.nextLine().trim();
        while (!bank_name.matches("^[A-Za-z\\s]+$")) {
            System.out.println("Please enter a valid bank name (letters and spaces only).");
            bank_name = scanner.nextLine();
        }

        System.out.print("Enter the minimum balance: ");
        int min_balance;
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer for the minimum balance.");
            scanner.next();
        }
        min_balance = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the bank code: ");
        int bank_code;
        boolean isBankCodeUnique;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid integer for the bank code.");
                scanner.next();
            }
            bank_code = scanner.nextInt();
            scanner.nextLine();

            isBankCodeUnique = true;
            for (Bank existingBank : banks) {
                if (existingBank.getBankCode() == bank_code) {
                    System.out.println("Bank code already exists. Please enter a unique bank code.");
                    isBankCodeUnique = false;
                    break;
                }
            }
        } while (!isBankCodeUnique);

        System.out.print("Enter the bank address: ");
        String address = scanner.nextLine();
        while (!address.matches("^[A-Za-z0-9\\s,/.-]*$")) {
            System.out.println("Please enter a valid address (alphanumeric, space, comma, slash, dot, hyphen allowed).");
            address = scanner.nextLine();
        }

        System.out.print("Enter the bank contact number: ");
        int contact_number;
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid contact number (digits only).");
            scanner.next();
        }
        contact_number = scanner.nextInt();
        scanner.nextLine();
        while (!String.valueOf(contact_number).matches("^[0-9\\-\\s]{10}$")) {
            System.out.println("Please enter a valid contact number (10 digits with optional dashes or spaces).");
            contact_number = scanner.nextInt();
            scanner.nextLine();
        }

        if (BankController.createBank(bank_name, bank_code, address, contact_number, min_balance)) {
            isBankCreated = true;
            isDetailSet = true;
            bank = new Bank(min_balance, bank_code, bank_name, address, contact_number);
            System.out.println("Bank details have been set successfully.");
            saveBankDetailsFlag();
            saveBankDetails();
        } else {
            System.out.println("Failed to create the bank. Please check the details and try again.");
        }

        return isBankCreated;
    }

    /*
     *********************************************************
     *  @Method Name    : isDetailsSet
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : saves the isDetailsSet flag to true in a properties file
     *                    after the details are set
     *  @param          : none
     *  @return         : void
     ********************************************************
     */

    private static void saveBankDetailsFlag() {
        try {
            Properties properties = new Properties();
            properties.setProperty("isDetailSet", "true");
            FileOutputStream fos = new FileOutputStream(CONFIG_FILE);
            properties.store(fos, null);
            fos.close();
            System.out.println("Bank details flag saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving bank details flag: " + e.getMessage());
        }
    }

    /*
     *********************************************************
     *  @Method Name    : isDetailsSet
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : saves bank details for the bank in a properties file
     *  @param          : none
     *  @return         : void
     ********************************************************
     */

    private static void saveBankDetails() {
        try {
            Properties bankProperties = new Properties();
            bankProperties.setProperty("min_balance", String.valueOf(bank.getMinBalance()));
            bankProperties.setProperty("bank_code", String.valueOf(bank.getBankCode()));
            bankProperties.setProperty("bank_name", bank.getBankName());
            bankProperties.setProperty("address", bank.getAddress());
            bankProperties.setProperty("contact_number", String.valueOf(bank.getContactNumber()));

            FileOutputStream bankFos = new FileOutputStream(BANK_DETAILS_FILE);
            bankProperties.store(bankFos, null);
            bankFos.close();
            System.out.println("Bank details saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving bank details: " + e.getMessage());
        }
    }

    /*
     *********************************************************
     *  @Method Name    : isDetailsSet
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for the Bank variable
     *  @param          : none
     *  @return         : Bank
     ********************************************************
     */

    public static Bank getBank() {
        return bank;
    }

    /*
     *********************************************************
     *  @Method Name    : isDetailsSet
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for the bankCode variable
     *  @param          : none
     *  @return         : int
     ********************************************************
     */

    public static int getBankID() {
        return bank.getBankCode();
    }
}
