/*
 *********************************************************************************************************
 *  @Java Class Name : AdminController
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Manages various admin-related operations such as adding, deleting users etc.
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Controllers;

import Models.User;
import Services.AdminServices;
import Services.UserServices;

public class AdminController {

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

    public static void viewCustomerHistory(String pancard)
    {
        AdminServices.viewCustomerHistory(pancard);
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
        return AdminServices.deleteUser(username);
    }

    /*
     *********************************************************
     *  @Method Name    : assignUserRole
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : assigns the user the provided role
     *  @param          : String username, String role
     *  @return         : boolean
     ********************************************************
     */

    public static boolean assignUserRole(String username, String role)
    {
        User user = UserServices.getUser(username);
        if(user!=null)
        {
            user.setRole(AdminServices.assignUserRole(role));
        }
        return false;
    }

}
