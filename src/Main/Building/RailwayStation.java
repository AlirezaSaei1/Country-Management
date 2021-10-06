package Main.Building;

import Main.City;
import Main.Person;
import Main.Travel.Travels;
import Main.Vehicle.Train;
import Main.Vehicle.Vehicle;

import javax.swing.*;
import java.util.*;

public class RailwayStation extends Terminal {
    static Scanner scan = new Scanner(System.in);
    ArrayList<Train> trains = new ArrayList<>();
    public ArrayList<Person> locomotives = new ArrayList<>();
    int arrivalRails;
    int departureRails;

    public RailwayStation(String name, String address, double price, double area, int arrivalRails, int departureRails, City city) {
        this.name = name;
        this.cityName = city.cityName;
        this.address = address;
        this.price = price;
        this.area = area;
        this.arrivalRails = arrivalRails;
        this.departureRails = departureRails;
        city.railwayStations.add(this);
        city.balance -= price;
    }

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public static void buildRailwayStation(City city) {
        double totalPrice = 350;
        if (city.balance >= totalPrice) {
            System.out.println("What Will Be Railway Station Name?");
            String name = scan.next();
            System.out.println("Set Address For Railway Station : ");
            scan.nextLine();
            String address = scan.nextLine();
            System.out.println("Enter Meters:\n1: 100-1500 (100$)\n2: 1500-2500 (150$)\n3: 2500< (200$)");
            double area = scan.nextDouble();
            if (area < 100) {
                System.out.println("Automatically Set to: 1000");
                totalPrice += 100;
            }
            if (area >= 100 && area < 1500) {
                totalPrice += 100;
            }
            if (area >= 1500 && area < 2500) {
                totalPrice += 150;
            }
            if (area >= 2500) {
                totalPrice += 200;
            }
            System.out.println("Set Number Of Arrival/Departure rails:");
            int arrDep = scan.nextInt();

            new RailwayStation(name, address, totalPrice, area, arrDep, arrDep, city);

            System.out.println("Construction Successful with " + totalPrice + "$");

        } else {
            System.out.println("Not Enough Money");
        }
    }

    public static void addTrain(Train t, City city) {
        System.out.println("Select A Railway Station For Delivery: ");
        int i = 1;
        for (RailwayStation a : city.railwayStations) {
            System.out.println("(" + i++ + ") Name : " + a.name + "\tAddress : " + a.address);
        }
        int choose = scan.nextInt();
        int counter = 1;
        for (RailwayStation a : city.railwayStations) {
            if (choose == 1) {
                a.trains.add(t);
                break;
            } else {
                counter++;
            }
        }
    }

    public static void addLocomotives(Person p, City city) {
        int listNum = 1;
        for (RailwayStation a : city.railwayStations) {
            System.out.println("(" + listNum++ + ") name : " + a.name + "\tAddress : " + a.address);
        }
        System.out.println("Select One Of Railway Stations above : ");
        int select = scan.nextInt();
        listNum = 1;
        for (RailwayStation a : city.railwayStations) {
            if (listNum == select) {
                a.locomotives.add(p);
                p.hired = true;
                city.balance -= p.getSalary();
                break;
            } else {
                listNum++;
            }
        }
    }

    @Override
    public void showHistory() {
        sortTravels();
        System.out.println("Outgoing Travels: ");
        for(Travels t : outTravel){
            System.out.printf("\033[0;33m" + "ID: %d \t City: %5s \t Destination: %5s \t Date: %5s \t Cost: %f", t.getTravelId(), t.getDepartureCity().cityName, t.getArrivalCity().cityName, t.getTravelDate(), t.getTripCost());
            System.out.println();
        }
        System.out.println("Incoming Travels: ");
        for(Travels t : inTravel){
            System.out.printf("\033[0;33m" + "ID: %d \t City: %5s \t ComingFrom: %5s \t Date: %5s \t Cost: %f", t.getTravelId(), t.getArrivalCity().cityName, t.getDepartureCity().cityName, t.getTravelDate(), t.getTripCost());
            System.out.println();
        }
    }

    @Override
    public void addTrip(Travels t) {
        //Travel
        t.getArrivalTerminal().inTravel.add(t);
        t.getDepartureTerminal().outTravel.add(t);
        //Passengers
        for(Person p : t.getPassengers()){
            p.city = t.arrivalCity;
        }
        t.arrivalCity.population.addAll(t.getPassengers());
        t.arrivalCity.cityPopulation += t.getPassengers().size();
        t.departureCity.population.removeAll(t.getPassengers());
        t.departureCity.cityPopulation -= t.getPassengers().size();
        //Cost
        t.departureCity.balance -= t.getTripCost();
        t.arrivalCity.balance += t.getTripCost();
        //Driver
        t.arrivalCity.population.add(t.getDriver());
        t.departureCity.population.remove(t.getDriver());
        //Vehicle
        Train train =(Train) t.getVehicle();
        ((RailwayStation)t.getArrivalTerminal()).trains.add(train);
        ((RailwayStation)t.getDepartureTerminal()).trains.remove(train);

        ((RailwayStation)t.getArrivalTerminal()).locomotives.add(t.getDriver());
        ((RailwayStation)t.getDepartureTerminal()).locomotives.remove(t.getDriver());
    }

    @Override
    public void sortTravels() {
        inTravel.sort(Travels::compareTo);
        outTravel.sort(Travels::compareTo);
    }

    @Override
    public double calculateCost(ArrayList<Person> passengers, Vehicle v) {
        double tripCost;
        if(((Train) v).ent.size()==1){
            tripCost = passengers.size()* ((Train) v).stars;
        }
        else if(((Train) v).ent.size()==2){
            tripCost = passengers.size()* ((Train) v).stars;
        }
        else if(((Train) v).ent.size()==3){
            tripCost = passengers.size()* ((Train) v).stars;
        }
        else if(((Train) v).ent.size()==4){
            tripCost = passengers.size()* ((Train) v).stars;
        }
        else if(((Train) v).ent.size()==5){
            tripCost = passengers.size()* ((Train) v).stars;
        }else{
            tripCost = passengers.size()*3;
        }
        return tripCost;
    }
}
