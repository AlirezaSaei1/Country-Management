package Main;

import Main.Building.*;
import Main.Finance.Bank;
import Main.Travel.Travels;

import java.io.Serializable;
import java.util.*;

public class City implements Serializable {
    public String cityName;
    public double balance;
    public ArrayList<Person> population = new ArrayList<>();
    public ArrayList<Hotel> hotels = new ArrayList<>();
    public ArrayList<Airport> airports = new ArrayList<>();
    public ArrayList<Harbor> harbour = new ArrayList<>();
    public ArrayList<RailwayStation> railwayStations = new ArrayList<>();
    public ArrayList<BusTerminal> busTerminals = new ArrayList<>();
    public ArrayList<Bank> banks = new ArrayList<>();
    public int cityPopulation = 0;
    City(String cityName, double balance){
        this.cityName = cityName;
        this.balance = balance;
    }

    public void addHotel(Hotel h, City c) {
        c.hotels.add(h);
    }

}
