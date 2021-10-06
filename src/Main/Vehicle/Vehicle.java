package Main.Vehicle;

import java.io.Serializable;

public abstract class Vehicle implements Serializable {
    public String companyName;
    public double price;
    int capacity;
    int id;

    Vehicle(String name, double price, int capacity, int id){
        this.companyName = name;
        this.price = price;
        this.capacity = capacity;
        this.id= id;
    }

    public String getCompanyName() {
        return companyName;
    }

}
