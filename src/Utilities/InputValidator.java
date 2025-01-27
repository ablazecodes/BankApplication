/*
 *********************************************************************************************************
 *  @Java Class Name : InputValidator
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Provides methods to validate user inputs such as number, string etc.
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Utilities;

import java.util.Scanner;

public class InputValidator {
    /*
     *********************************************************
     *  @Method Name    : menuInputValidator
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : prompts the user to confirm if they want to return to the previous menu
     *  @param          : Scanner class object sc
     *  @return         : boolean
     ********************************************************
     */
    public static boolean menuInputValidation(Scanner sc) {
        String flag;
        while (true) {
            System.out.println("Do you want to return to the previous menu? (y/yes/n/no): ");
            flag = sc.nextLine().trim().toLowerCase();

            if (flag.matches("^(y|yes)$")) {
                return true;
            } else if (flag.matches("^(n|no)$")) {
                return false;
            } else {
                System.out.println("Invalid input! Please enter 'y' for Yes or 'n' for No.");
            }
        }
    }

    /*
     *********************************************************
     *  @Method Name    : numberValidator
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : validates if the number is a number lies in the required range
     *  @param          : Scanner class object sc, integer num
     *  @return         : boolean
     ********************************************************
     */

    public static boolean numberValidator(Scanner sc, int num)
    {
        if (sc.hasNextInt()) {
            if (num > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            sc.next();
            return false;
        }
    }

    /*
     *********************************************************
     *  @Method Name    : stringValidator
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : validates if the String contains only alphabets
     *  @param          : Scanner class object sc, integer num
     *  @return         : boolean
     ********************************************************
     */

    public static boolean stringValidator(String str)
    {
        return str.matches("^[A-Za-z]+$");
    }

}
