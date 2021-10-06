package Main.Exceptions;

public class CityExists extends RuntimeException{
    public CityExists(){
        super("City Exists");
    }
}
