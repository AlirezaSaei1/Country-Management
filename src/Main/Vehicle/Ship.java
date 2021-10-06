package Main.Vehicle;

import Main.Building.Harbor;
import Main.City;
import java.util.Scanner;

public class Ship extends WaterVehicle {
    static Scanner scan = new Scanner(System.in);
    int staffCount;
    public boolean luxury;
    static int ShipID = 1;

    public Ship(String name, double price, int capacity, String fuelType, boolean luxury, City city) {
        super(name, price, capacity, fuelType, 100, ShipID);
        ShipID++;
        this.staffCount = 20;
        this.luxury = luxury;
        city.balance -= price;
    }

    public static void buyShip(City city) {
        String fuelType;
        double totalPrice = 300;
        if (city.balance >= totalPrice) {
            System.out.println("Set Name: ");
            String name = scan.next();
            System.out.println("Set Capacity: ");
            int capacity = scan.nextInt();
            while (true) {
                System.out.println("Set Your Ship's fuel Type: (1:Coal, 2:Oil, 3:Gas, 4:Electricity(Extra Will Be Applied : 100$))");
                int input = scan.nextInt();
                if (input == 1) {
                    fuelType = "Coal";
                    break;
                }
                if (input == 2) {
                    fuelType = "Oil";
                    break;
                }
                if (input == 3) {
                    fuelType = "Gas";
                    break;
                }
                if (input == 4) {
                    fuelType = "Electricity";
                    totalPrice += 100;
                    break;
                }
                if (input != 1 && input != 2 && input != 3 && input != 4) {
                    System.out.println("Invalid Input! Try Again");
                    continue;
                }
            }
            System.out.println("Is It A Luxury Ship?(y/n) (Extra Cost: 200$)");
            String yesNo = scan.next();
            if (yesNo.equals("y")) {
                Harbor.addShip(new Ship(name, totalPrice, capacity, fuelType, true, city), city);
            } else {
                Harbor.addShip(new Ship(name, totalPrice, capacity, fuelType, false, city), city);
            }
        } else {
            System.out.println("Not Enough Money!");
        }
    }
}
