package Main.Exceptions;

public class AlreadyUsedLoan extends Exception{
    public AlreadyUsedLoan(){
        super("You Have Already Used Loan");
    }
}
