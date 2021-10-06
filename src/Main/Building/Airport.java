package Main.Building;

import Main.City;

import Main.Person;
import Main.Travel.Travels;
import Main.Vehicle.*;

import java.util.*;


public class Airport extends Terminal {
    static Scanner scan = new Scanner(System.in);
    public ArrayList<AirVehicle> AllPlane = new ArrayList<>();
    public ArrayList<Person> pilots = new ArrayList<>();

    public int runwayCount;
    public boolean international;

    public Airport(String name, String address, double price, double area, int runwayCount, boolean international, City city) {
        this.name = name;
        this.cityName = city.cityName;
        this.address = address;
        this.price = price;
        this.area = area;
        this.runwayCount = runwayCount;
        this.international = international;
        city.airports.add(this);
        city.balance -= price;
    }

    public static void buildAirport(City city) {
        double totalPrice = 800;
        if (city.balance >= totalPrice) {
            System.out.println("What Will Be Airport Name?");
            String name = scan.next();
            System.out.println("Set Address For Airport : ");
            scan.nextLine();
            String address = scan.nextLine();
            System.out.println("Enter Meters:\n1: 100-2000 (100$)\n2: 2000-3000 (150$)\n3: 3000< (200$)");
            double area = scan.nextDouble();
            if (area < 100) {
                System.out.println("Ants Airports On Its Way!\n Set to: 1000");
                totalPrice += 100;
            }
            if (area >= 100 && area < 2000) {
                totalPrice += 100;
            }
            if (area >= 2000 && area < 3000) {
                totalPrice += 150;
            }
            if (area >= 3000) {
                totalPrice += 200;
            }
            System.out.println("Number Of Runways: (100$ each)" + "     Price Up To Now : " + totalPrice);
            int runwayCount = scan.nextInt();
            totalPrice += (runwayCount * 100);
            System.out.println("Make It International: (y/n)(50$)");
            System.out.println("Price Up To Now: " + totalPrice + "\t\tPrice Will Be : " + (totalPrice + 50));
            String yesNo = scan.next();
            if (yesNo.equals("y")) {
                totalPrice += 50;
                new Airport(name, address, totalPrice, area, runwayCount, true, city);
            } else {
                new Airport(name, address, totalPrice, area, runwayCount, false, city);
            }
            System.out.println("Construction Successful with " + totalPrice + "$");
        } else {
            System.out.println("Not Enough Money!");
        }
    }

    public static void addPPlaneToHangar(PassengerPlane p, City city) {
        System.out.println("Choose An Airport For Delivery : ");
        int i = 1;
        for (Airport a : city.airports) {
            System.out.println("(" + i++ + ") Name : " + a.name + "\tAddress : " + a.address);
        }
        int choose = scan.nextInt();
        int counter = 1;
        for (Airport a : city.airports) {
            if (choose == counter) {
                a.AllPlane.add(p);
                break;
            } else {
                counter++;
            }
        }
    }

    public static void addCPlaneToHangar(CargoPlane c, City city) {
        System.out.println("Choose An Airport For Your Delivery : ");
        int i = 1;
        for (Airport a : city.airports) {
            System.out.println("(" + i++ + ") Name : " + a.name + "\tAddress : " + a.address);
        }
        int choose = scan.nextInt();
        int counter = 1;
        for (Airport a : city.airports) {
            if (choose == counter) {
                a.AllPlane.add(c);
                break;
            } else {
                counter++;
            }
        }
    }

    public static void addPilot(Person p, City city) {
        int listNum = 1;
        for (Airport a : city.airports) {
            System.out.println("(" + listNum++ + ") name : " + a.name + "\tAddress : " + a.address);
        }
        System.out.println("Select One Of Airports above : ");
        int select = scan.nextInt();
        listNum = 1;
        for (Airport a : city.airports) {
            if (listNum == select) {
                a.pilots.add(p);
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
        PassengerPlane p = (PassengerPlane) t.getVehicle();
        ((Airport) t.getArrivalTerminal()).AllPlane.add(p);
        ((Airport) t.getDepartureTerminal()).AllPlane.remove(p);

        ((Airport) t.getArrivalTerminal()).pilots.add(t.getDriver());
        ((Airport) t.getDepartureTerminal()).pilots.remove(t.getDriver());
    }

    @Override
    public void sortTravels() {
        inTravel.sort(Travels::compareTo);
        outTravel.sort(Travels::compareTo);
    }

    @Override
    public double calculateCost(ArrayList<Person> passengers, Vehicle v) {
        double tripCost;
        if(((PassengerPlane) v).seatRating>=1 && ((PassengerPlane) v).seatRating <= 2){
            tripCost = passengers.size() * 10;
        }
        else if(((PassengerPlane) v).seatRating>2 && ((PassengerPlane) v).seatRating <= 4){
            tripCost = passengers.size() * 15;
        }
        else if(((PassengerPlane) v).seatRating>4 && ((PassengerPlane) v).seatRating <= 6){
            tripCost = passengers.size() * 20;
        }else{
            tripCost = passengers.size() * 15;
        }
        return tripCost;
    }
}

