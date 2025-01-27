/*
 *********************************************************************************************************
 *  @Java Class Name : Customer
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Represents a Customer with attributes such as name, username, aadhar, panCard etc.
 *                     and includes methods to retrieve these values.
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Models;

public class Customer {
    private String name;
    private String username;
    private String aadharCard;
    private String panCard;
    private int creditScore;

    /*
     *********************************************************
     *  @Method Name    : Customer
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Parameterized constructor of class Customer
     *  @param          : String username, String name, String aadharCard, String panCard, int creditScore
     *  @return         : void
     ********************************************************
     */
    public Customer(String username, String name, String aadharCard, String panCard, int creditScore) {
        this.username = username;
        this.name = name;
        this.aadharCard = aadharCard;
        this.panCard = panCard;
        this.creditScore = 0;
    }

    /*
     *********************************************************
     *  @Method Name    : Customer
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Default constructor of class Customer
     *  @param          : none
     *  @return         : void
     ********************************************************
     */
    public Customer() {}


    /*
     *********************************************************
     *  @Method Name    : toString
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : overridden toString method for objects of Customer type
     *  @param          : none
     *  @return         : String
     ********************************************************
     */
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", aadharCard='" + aadharCard + '\'' +
                ", panCard='" + panCard + '\'' +
                ", creditScore=" + creditScore +
                '}';
    }

    /*
     *********************************************************
     *  @Method Name    : getAadharCard
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for aadhar card
     *  @param          : none
     *  @return         : String
     ********************************************************
     */
    public String getAadharCard() {
        return aadharCard;
    }

    /*
     *********************************************************
     *  @Method Name    : getPanCard
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for pan card
     *  @param          : none
     *  @return         : String
     ********************************************************
     */
    public String getPanCard() {
        return panCard;
    }

    /*
     *********************************************************
     *  @Method Name    : getName
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for customer name
     *  @param          : none
     *  @return         : String
     ********************************************************
     */
    public String getName() {
        return name;
    }

    /*
     *********************************************************
     *  @Method Name    : getAadharCard
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for customer username
     *  @param          : none
     *  @return         : String
     ********************************************************
     */
    public String getUsername() {
        return username;
    }

    /*
     *********************************************************
     *  @Method Name    : getCreditScore
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : getter for credit score
     *  @param          : none
     *  @return         : int
     ********************************************************
     */
    public int getCreditScore() {
        return creditScore;
    }
}
