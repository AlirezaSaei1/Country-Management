package Main.Vehicle;

public abstract class WaterVehicle extends Vehicle {
    String fuelType;
    double minDepth;

    WaterVehicle(String name, double price, int capacity, String fuelType, double minDepth, int id) {
        super(name, price, capacity, id);
        this.fuelType = fuelType;
        this.minDepth = minDepth;
    }
}
