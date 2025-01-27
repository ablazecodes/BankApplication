/*
 *********************************************************************************************************
 *  @Java Class Name : LoanController
 *  @Author          : <Shreya Shukla>(shreya.shukla@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 24-01-2025
 *  @Description     : Manages various loan-related operations such as approval of loans
 *
 *******************************************************************************************************
 *  JIRA ID   AWC-0002607
 *  TITLE     Training-Shreya Shukla
 *  AWC      <Shreya Shukla>(shreya.shukla@antrazal.com)
 ********************************************************************************************************
 */


package Controllers;

import Services.LoanServices;

public class LoanController {

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
        return LoanServices.approveLoan(id);
    }
}
