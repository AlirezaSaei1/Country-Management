package Main.Exceptions;

public class BankExists extends Exception{
    public BankExists(){
        super("Bank Already Exists!");
    }
}
