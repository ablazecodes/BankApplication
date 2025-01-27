/*
 *********************************************************************************************************
 *  @Java Class Name : AdminServices
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Contains services related to the Admin such as managing accounts and users
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Services;

import Constants.Role;

import Repositories.AdminRepository;
import Repositories.UserRepository;


public class AdminServices {

    static AdminRepository asr = AdminRepository.getInstance();

    /*
     *********************************************************
     *  @Method Name    : assignUserRole
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns the role corresponding to the given string
     *  @param          : String role
     *  @return         : Role
     ********************************************************
     */

    public static synchronized Role assignUserRole(String role)
    {
        return switch (role) {
            case "CUSTOMER" -> Role.CUSTOMER;
            case "CASHIER" -> Role.CASHIER;
            case "MANAGER" -> Role.MANAGER;
            default -> null;
        };
    }

    /*
     *********************************************************
     *  @Method Name    : deleteUser
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : deletes the user with the given username
     *  @param          : String username
     *  @return         : boolean
     ********************************************************
     */

    public static boolean deleteUser(String username)
    {
        UserRepository usr = UserRepository.getInstance();
        return usr.deleteUser(username);
    }

    /*
     *********************************************************
     *  @Method Name    : viewCustomerHistory
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : displays the history of a customer based on their pan card number
     *  @param          : String pan
     *  @return         : void
     ********************************************************
     */

    public static void viewCustomerHistory(String pan)
    {
        asr.getCustomerHistory(pan);
    }
}
