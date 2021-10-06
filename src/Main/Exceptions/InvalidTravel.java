package Main.Exceptions;

public class InvalidTravel extends RuntimeException{
    public InvalidTravel(String str){
        super(str);
    }
}
