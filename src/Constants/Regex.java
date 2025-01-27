/*
 *********************************************************************************************************
 *  @Java Class Name : Regex
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Contains the regex constants
 *
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */


package Constants;

public class Regex {
    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD = "admin123";

    public static final String USER_REGEX = "[A-Z]+(?=.*[a-z]){3,}(?=.*\\d).+";
    public static final String PASSWORD_REGEX = "^(?=.*[@#$&!<>/?%^()_+=:;,])(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,}$";
    public static final String NAME_REGEX = "[A-Z][a-z]+(?: [A-Z]?[a-z]*)*";
    public static final String AADHAR_REGEX = "\\d{12}";
    public static final String ACCOUNT_NUMBER_REGEX = "\\d{12}";
    public static final String PAN_REGEX = "[A-Z]{5}[0-9]{4}[A-Z]{1}";

}
