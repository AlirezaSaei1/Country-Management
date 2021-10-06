package Main.Vehicle;

public abstract class GroundVehicle extends Vehicle {
    double maxSpeed;
    String Color;

    GroundVehicle(String name, double price, int capacity, double maxSpeed, String Color, int id) {
        super(name, price, capacity, id);
        this.maxSpeed = maxSpeed;
        this.Color = Color;
    }
}
