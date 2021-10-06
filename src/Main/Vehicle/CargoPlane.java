package Main.Vehicle;

import Main.Building.Airport;
import Main.City;

import java.util.*;

public class CargoPlane extends AirVehicle {
    static Scanner scan = new Scanner(System.in);
    double maxWeight;
    static int cPlaneID = 1;

    public CargoPlane(String name, double price, int capacity, double maxWeight, double maxHeight, double runwayLength, City city) {
        super(name, price, capacity, maxHeight, runwayLength, cPlaneID);
        cPlaneID++;
        this.maxWeight = maxWeight;
        city.balance -= price;
    }

    public static void buyCargoPlane(City city) {
        double totalPrice = 450;
        if (city.balance >= 450) {
            System.out.println("Enter Company Name : (AirPlane Cost : 450$)");
            String cName = scan.next();
            System.out.println("Select Capacity : ");
            int cap = scan.nextInt();
            double maxWeight = 0;
            while (true) {
                System.out.println("set Max Weight For Cargo Plane :\n1: 0-500 Kg (Cost : 100$)\n2: 500-1000 Kg(Cost : 150$)\n3: 1000-1500 kg (Cost : 200$)\n4: 1500-2000 Kg (Cost 250$)");
                double kg = scan.nextDouble();
                if (kg >= 0) {
                    if (kg >= 0 && kg < 500) {
                        maxWeight += kg;
                        totalPrice += 100;
                        break;
                    }
                    if (kg >= 500 && kg < 1000) {
                        maxWeight += kg;
                        totalPrice += 150;
                        break;
                    }
                    if (kg >= 1000 && kg < 1500) {
                        maxWeight += kg;
                        totalPrice += 200;
                        break;
                    }
                    if (kg >= 1500 && kg <= 2000) {
                        maxWeight += kg;
                        totalPrice += 250;
                        break;
                    }
                    if (kg > 2000) {
                        System.out.println("Airplane Cannot Take Off!");
                    }
                } else {
                    System.out.println("Select Weight Again!");
                }
            }
            System.out.println("Set Runway Length : (m)");
            double runwayLength = scan.nextDouble();
            System.out.println("Set Max height : (m)");
            double maxHeight = scan.nextDouble();

            Airport.addCPlaneToHangar(new CargoPlane(cName, totalPrice, cap, maxWeight, maxHeight, runwayLength, city), city);
        } else {
            System.out.println("Not Enough Money!");
        }
    }

}
