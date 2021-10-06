package Main.Exceptions;

public class SameCity extends InvalidTravel{
    public SameCity() {
        super("Starting City And Destination City Cannot Be Same");
    }
}
