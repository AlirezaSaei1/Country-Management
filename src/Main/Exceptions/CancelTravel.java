package Main.Exceptions;

public class CancelTravel extends RuntimeException{
    public CancelTravel(String str){
        super(str);
    }
}
