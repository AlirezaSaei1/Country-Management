package Main.Travel;
import Main.Building.Terminal;
import Main.City;
import Main.Person;
import Main.Vehicle.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;

public class Travels implements Comparable, Serializable {
    public City arrivalCity;
    public City departureCity;
    Terminal arrivalTerminal;
    Terminal departureTerminal;
    ArrayList<Person> passengers = new ArrayList<>();
    Person driver;
    Vehicle vehicle;
    int travelId;
    static int idCounter = 1;
    String travelDate;
    double tripCost;

    public Travels(City c1, City c2, Terminal t1, Terminal t2, String date, ArrayList<Person> p, Person driver, Vehicle v, double tripCost){
        this.departureCity = c1;
        this.arrivalCity = c2;
        this.departureTerminal = t1;
        this.arrivalTerminal = t2;
        this.travelDate = date;
        this.passengers = p;
        this.driver = driver;
        this.vehicle = v;
        this.travelId = idCounter++;
        this.tripCost = tripCost;
        applyTravel();
    }


    public Travels(){}

    public City getArrivalCity(){
        return this.arrivalCity;
    }

    public City getDepartureCity(){
        return this.departureCity;
    }

    public String getTravelDate(){
        return this.travelDate;
    }

    public double getTripCost(){
        return tripCost;
    }

    public int getTravelId(){
        return travelId;
    }

    public Terminal getArrivalTerminal() {
        return arrivalTerminal;
    }

    public Terminal getDepartureTerminal() {
        return departureTerminal;
    }

    public ArrayList<Person> getPassengers() {
        return passengers;
    }

    public Person getDriver() {
        return driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    @Override
    public int compareTo(Object o) {
        if (this.getTravelDate().equals(((Travels)o).getTravelDate())){
            return (int) (((Travels)o).getTripCost() - this.getTripCost());
        }else{
            for(int i=0; i<this.getTravelDate().length(); i++){
                int str1_ch = this.getTravelDate().charAt(i);
                int str2_ch = ((Travels)o).getTravelDate().charAt(i);
                if(str1_ch != str2_ch){
                    return str1_ch - str2_ch;
                }
            }
        }
        return 0;
    }
    void applyTravel(){
        departureTerminal.addTrip(this);
    }
}
