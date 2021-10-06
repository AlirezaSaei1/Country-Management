package Main.Building;

import Main.City;
import Main.Person;
import Main.Travel.Travels;
import Main.Vehicle.Boat;
import Main.Vehicle.Ship;
import Main.Vehicle.Train;
import Main.Vehicle.Vehicle;

import java.util.*;

public class Harbor extends Terminal {
    static Scanner scan = new Scanner(System.in);
    public ArrayList<Boat> boats = new ArrayList<>();
    public ArrayList<Ship> ships = new ArrayList<>();
    public ArrayList<Person> cpt = new ArrayList<>();
    public int harborCount;

    public Harbor(String name, String address, double price, double area, int harborCount, City city) {
        this.name = name;
        this.cityName = city.cityName;
        this.address = address;
        this.price = price;
        this.area = area;
        this.harborCount = harborCount;
        city.harbour.add(this);
        city.balance -= price;
    }

    public static void buildHarbor(City city) {
        double totalPrice = 400;
        if (city.balance >= totalPrice) {
            System.out.println("What Will Be Harbor Name?");
            String name = scan.next();
            System.out.println("Set Address For Harbor : ");
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
            System.out.println("set Number Of Harbors: (Each Place 50$)");
            int harborCount = scan.nextInt();
            totalPrice += harborCount * 50;
            System.out.println("Construction Successful with " + totalPrice + "$");
            new Harbor(name, address, totalPrice, area, harborCount, city);
        } else {
            System.out.println("Not enough Money");
        }
    }

    public static void addBoat(Boat b, City city) {
        System.out.println("Choose A Harbor For Delivery : ");
        int i = 1;
        for (Harbor a : city.harbour) {
            System.out.println("(" + i++ + ") Name :" + a.name + "\t Address : " + a.address);
        }
        int choose = scan.nextInt();
        int counter = 1;
        for (Harbor a : city.harbour) {
            if (choose == 1) {
                a.boats.add(b);
                break;
            } else {
                counter++;
            }
        }
    }

    public static void addShip(Ship s, City city) {
        System.out.println("Choose A Harbor For Your Delivery : ");
        int i = 1;
        for (Harbor a : city.harbour) {
            System.out.println("(" + i++ + ") Name :" + a.name + "\t Address : " + a.address);
        }
        int choose = scan.nextInt();
        int counter = 1;
        for (Harbor a : city.harbour) {
            if (choose == 1) {
                a.ships.add(s);
                break;
            } else {
                counter++;
            }
        }
    }

    public static void addCpt(Person p, City city) {
        int listNum = 1;
        for (Harbor a : city.harbour) {
            System.out.println("(" + listNum++ + ") name : " + a.name + "\tAddress : " + a.address);
        }
        System.out.println("Select One Of harbors above : ");
        int select = scan.nextInt();
        listNum = 1;
        for (Harbor a : city.harbour) {
            if (listNum == select) {
                a.cpt.add(p);
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
        if (t.getVehicle() instanceof Boat){
            Boat boat =(Boat) t.getVehicle();
            ((Harbor)t.getArrivalTerminal()).boats.add(boat);
            ((Harbor)t.getDepartureTerminal()).boats.remove(boat);

            ((Harbor)t.getArrivalTerminal()).cpt.add(t.getDriver());
            ((Harbor)t.getDepartureTerminal()).cpt.remove(t.getDriver());
        }else{
            Ship ship =(Ship) t.getVehicle();
            ((Harbor)t.getArrivalTerminal()).ships.add(ship);
            ((Harbor)t.getDepartureTerminal()).ships.remove(ship);

            ((Harbor)t.getArrivalTerminal()).cpt.add(t.getDriver());
            ((Harbor)t.getDepartureTerminal()).cpt.remove(t.getDriver());
        }
    }

    @Override
    public void sortTravels() {
        inTravel.sort(Travels::compareTo);
        outTravel.sort(Travels::compareTo);
    }

    @Override
    public double calculateCost(ArrayList<Person> passengers, Vehicle v) {
        return 0;
    }
}
