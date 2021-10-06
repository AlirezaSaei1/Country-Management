package Main.Exceptions;

public class NotEnoughCities extends InvalidTravel{
    public NotEnoughCities() {
        super("Insufficient Number Of Cities (Must Be More Than 1)");
    }
}
