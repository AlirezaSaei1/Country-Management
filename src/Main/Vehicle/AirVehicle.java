package Main.Vehicle;

import java.util.ArrayList;

public abstract class AirVehicle extends Vehicle{
    double maxHeight;
    double runwayLength;

    public AirVehicle(String name,double price, int capacity, double maxHeight, double runwayLength, int id){
        super(name, price, capacity, id);
        this.maxHeight = maxHeight;
        this.runwayLength = runwayLength;
    }

}
