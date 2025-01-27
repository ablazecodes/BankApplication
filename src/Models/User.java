/*
 *********************************************************************************************************
 *  @Java Class Name : User
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Represents a user with attributes such as username, password and role
 *                     and includes methods to retrieve these values.
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Models;

import Constants.Role;

public class User {
    private final String username;
    private final String password;
    private Role role;

    /*
     *********************************************************
     *  @Method Name    : User
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Parameterized constructor of class User
     *  @param          : String username, String password, Role role
     *  @return         : void
     ********************************************************
     */
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /*
     *********************************************************
     *  @Method Name    : toString
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : overridden toString method for User class objects
     *  @param          : none
     *  @return         : String
     ********************************************************
     */
    @Override
    public String toString() {
        return "Username= " + username +
                ", role= " + role +
                '\n';
    }

    /*
     *********************************************************
     *  @Method Name    : getUsername
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Getter for username
     *  @param          : none
     *  @return         : String
     ********************************************************
     */
    public String getUsername() {
        return username;
    }

    /*
     *********************************************************
     *  @Method Name    : getPassword
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for password
     *  @param          : none
     *  @return         : String
     ********************************************************
     */
    public String getPassword() {
        return password;
    }

    /*
     *********************************************************
     *  @Method Name    : getRole
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Getter for role
     *  @param          : none
     *  @return         : Role
     ********************************************************
     */
    public Role getRole() {
        return role;
    }

    /*
     *********************************************************
     *  @Method Name    : setRole
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Setter for Role
     *  @param          : Role role
     *  @return         : void
     ********************************************************
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
