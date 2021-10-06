package Main.Building;
import Main.*;
import Main.Travel.Travels;
import Main.Vehicle.*;
import java.util.*;

public class BusTerminal extends Terminal {
    static Scanner scan = new Scanner(System.in);
    public ArrayList<Person> drivers = new ArrayList<>();
    public ArrayList<Bus> buses = new ArrayList<>();

    public BusTerminal(String name, String address, double price, double area, int vehicleCount, City city) {
        this.name = name;
        this.cityName = city.cityName;
        this.address = address;
        this.price = price;
        this.area = area;
        this.vehicleCount = vehicleCount;
        city.busTerminals.add(this);
        city.balance -= price;
    }

    public static void buildBusTerminal(City city) {
        double totalPrice = 500;
        if (city.balance >= totalPrice) {
            System.out.println("What Will Be Bus Terminal Name?");
            String name = scan.next();
            System.out.println("Set Address For Bus Terminal : ");
            scan.nextLine();
            String address = scan.nextLine();
            System.out.println("Enter Meters:\n1: 100-2000 (100$)\n2: 2000-3000 (150$)\n3: 3000< (200$)");
            double area = scan.nextDouble();
            if (area < 100) {
                System.out.println("Automatically Set to: 1000");
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
            new BusTerminal(name, address, totalPrice, area, 10, city);

            System.out.println("Construction Successful with " + totalPrice + "$");
        } else {
            System.out.println("Not Enough Money!");
        }
    }

    public static void addBus(Bus b, City city) {
        System.out.println("Select A Bus Terminal For Delivery: ");
        int i = 1;
        for (BusTerminal a : city.busTerminals) {
            System.out.println("(" + i++ + ")   Name: " + a.name + "\tAddress: " + a.address);
        }
        int choose = scan.nextInt();
        int counter = 1;
        for (BusTerminal a : city.busTerminals) {
            if (choose == 1) {
                a.buses.add(b);
                break;
            } else {
                counter++;
            }
        }
    }

    public static void addDriver(Person p, City city) {
        int listNum = 1;
        for (BusTerminal a : city.busTerminals) {
            System.out.println("(" + listNum++ + ") name : " + a.name + "\tAddress : " + a.address);
        }
        System.out.println("Select One Of Bus Terminals above : ");
        int select = scan.nextInt();
        listNum = 1;
        for (BusTerminal a : city.busTerminals) {
            if (listNum == select) {
                a.drivers.add(p);
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
        Bus b = (Bus) t.getVehicle();
        ((BusTerminal)t.getArrivalTerminal()).buses.add(b);
        ((BusTerminal)t.getDepartureTerminal()).buses.remove(b);

        ((BusTerminal)t.getArrivalTerminal()).drivers.add(t.getDriver());
        ((BusTerminal)t.getDepartureTerminal()).drivers.remove(t.getDriver());
    }

    @Override
    public void sortTravels() {
        inTravel.sort(Travels::compareTo);
        outTravel.sort(Travels::compareTo);
    }

    @Override
    public double calculateCost(ArrayList<Person> passengers, Vehicle v) {
        double tripCost;
        if(((Bus) v).seatRate>=1 && ((Bus) v).seatRate <= 2){
            tripCost = passengers.size() * 10;
        }
        else if(((Bus) v).seatRate>2 && ((Bus) v).seatRate <= 4){
            tripCost = passengers.size() * 15;
        }
        else if(((Bus) v).seatRate>4 && ((Bus) v).seatRate <= 6){
            tripCost = passengers.size() * 20;
        }else{
            tripCost = passengers.size() * 15;
        }
        return tripCost;
    }
}
