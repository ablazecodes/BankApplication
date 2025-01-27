/*
 *********************************************************************************************************
 *  @Java Class Name : Loan
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Represents a Loan with attributes such as id, amount, category, if approval is required
 *                     and includes methods to retrieve these values.
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Models;

public class Loan {
    private final int id;
    private final int amount;
    private boolean requiresApproval;
    private boolean isApproved;
    private final String category;

    /*
     *********************************************************
     *  @Method Name    : Loan
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Parameterized constructor of class Loan
     *  @param          : int id, int amount, boolean requiresApproval, boolean isApproved, String category
     *  @return         : none
     ********************************************************
     */
    public Loan(int id, int amount, boolean requiresApproval, boolean isApproved, String category) {
        this.id = id;
        this.amount = amount;
        this.requiresApproval = requiresApproval;
        this.isApproved = isApproved;
        this.category = category;
    }

    /*
     *********************************************************
     *  @Method Name    : toString
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : overridden toString method for objects of Loan type
     *  @param          : none
     *  @return         : String
     ********************************************************
     */
    @Override
    public String toString() {
        return "Loan id=" + id +
                ", amount=" + amount +
                ", requiresApproval=" + requiresApproval +
                ", isApproved=" + isApproved +
                ", category='" + category + '\n' +
                '}';
    }

}
