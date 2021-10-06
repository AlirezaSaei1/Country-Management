package Main.Building;

import Main.Person;
import Main.Travel.Travelable;
import Main.Travel.Travels;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Terminal extends Travels implements Travelable, Serializable {

    public String name;
    public String address;
    public String cityName;
    public double price;
    public double area;
    public int vehicleCount;
    public ArrayList<Person> ppl = new ArrayList<>();
    public ArrayList<Travels> inTravel = new ArrayList<>();
    public ArrayList<Travels> outTravel = new ArrayList<>();

}
