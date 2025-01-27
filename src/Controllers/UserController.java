/*
 *********************************************************************************************************
 *  @Java Class Name : UserController
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Manages various user-related operations such as retrieving, verifying, and creating users
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Controllers;

import Constants.Role;
import Models.User;
import Services.UserServices;

import java.util.List;

public class UserController {


    /*
     *********************************************************
     *  @Method Name    : verifyLogin
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : verifies the pair of username and password
     *  @param          : String username, String password
     *  @return         : boolean
     ********************************************************
     */
    public static boolean verifyLogin(String username, String password) {
       return UserServices.verifyLogin(username, password);
    }

    /*
     *********************************************************
     *  @Method Name    : getUsers
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : returns the list of all the users
     *  @param          : none
     *  @return         : List<User>
     ********************************************************
     */
    public static List<User> getUsers()
    {
        return UserServices.getUsers();
    }

    /*
     *********************************************************
     *  @Method Name    : createUser
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : creates a user with the given fields and returns the result
     *  @param          : String username, String password, Role role
     *  @return         : boolean
     ********************************************************
     */
    public static boolean createUser(String username, String password, Role role)
    {
        return UserServices.createUser(username, password, role);
    }

    /*
     *********************************************************
     *  @Method Name    : getUser
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : retrieves the user with the provided username
     *  @param          : String username
     *  @return         : User
     ********************************************************
     */
    public static User getUser(String username)
    {
        return UserServices.getUser(username);
    }

}
