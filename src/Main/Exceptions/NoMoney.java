package Main.Exceptions;

public class NoMoney extends Exception{
    public NoMoney(){
        super("Insufficient Money");
    }
}
