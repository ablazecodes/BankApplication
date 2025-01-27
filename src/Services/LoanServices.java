/*
 *********************************************************************************************************
 *  @Java Class Name : LoanServices
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Contains services related to the Loans
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */

package Services;

import Models.Loan;
import Repositories.LoanRepository;

import java.util.List;

public class LoanServices {

    static LoanRepository lsr = LoanRepository.getInstance();

    /*
     *********************************************************
     *  @Method Name    : getUnapprovedLoans
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : retrieves all the unapproved loans
     *  @param          : String username
     *  @return         : List<Loan>
     ********************************************************
     */

    public static List<Loan> getUnapprovedLoans(String username)
    {
        return lsr.getUnapprovedLoans(username);
    }

    /*
     *********************************************************
     *  @Method Name    : approveLoan
     *  @author         : <Shreya Shukla>(shreya.shukla@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : approves loan with the given loan id and returns the result
     *  @param          : int id
     *  @return         : boolean
     ********************************************************
     */

    public static boolean approveLoan(int id)
    {
        return lsr.approveLoan(id);
    }
}
