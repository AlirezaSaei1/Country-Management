package Main.Exceptions;

public class NoBank extends RuntimeException{
    public NoBank(){
        super("No Banks In City");
    }
}
