package Main.Exceptions;

public class PassengerError extends CancelTravel{
    public PassengerError() {
        super("Error: Selected 1 Person More Than 1 time");
    }
}
