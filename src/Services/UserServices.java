/*
 *********************************************************************************************************
 *  @Java Class Name : UserServices
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Contains services related to Users
 *
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Services;

import Constants.Role;
import Models.User;
import Repositories.UserRepository;
import java.util.List;

public class UserServices {

    static UserRepository usr = UserRepository.getInstance();


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

    public static boolean verifyLogin(String username, String password)
    {
        List<User>users = usr.getUsers();
        if(users!=null)
        {
            for(var i: users)
            {
                if(i.getUsername().equals(username) && i.getPassword().equals(password))
                    return true;
            }
        }
        return false;
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
        return usr.getUsers();
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
        return usr.getUserDetails(username);
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
        return usr.createUser(username, password, role);
    }

}
