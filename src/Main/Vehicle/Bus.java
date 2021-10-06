package Main.Vehicle;

import Main.Building.BusTerminal;
import Main.City;

import java.util.Scanner;


public class Bus extends GroundVehicle {
    static Scanner scan = new Scanner(System.in);
    int seatRows;
    public int seatRate;
    static int BusID = 1;

    public Bus(String name, double price, int capacity, int seatRows, int seatRate, double maxSpeed, String Color, City city) {
        super(name, price, capacity, maxSpeed, Color, BusID);
        BusID++;
        this.seatRate = seatRate;
        this.seatRows = seatRows;
        city.balance -= price;
    }

    public static void buyBus(City city) {
        if (city.balance >= 250) {
            double totalPrice = 250;
            int multiplier = 1;
            System.out.println("Select Company Name : ");
            String name = scan.next();
            System.out.println("Select Color : ");
            String color = scan.next();
            int n = 2;
            while (true) {
                System.out.println("Set Seat Rate : (1 (10$ each row)/2 (5$ each row)(recommended)/3 (2$ each row)");
                n = scan.nextInt();
                switch (n) {
                    case 1: {
                        multiplier = 10;
                        break;
                    }
                    case 2: {
                        multiplier = 5;
                        break;
                    }
                    case 3: {
                        multiplier = 2;
                        break;
                    }
                    default: {
                        n = 2;
                        multiplier = 2;
                        System.out.println("Automatically Set To Rate: 2");
                    }
                }
                break;
            }
            int cap = 0;
            int seatRow;
            while (true) {
                System.out.println("Set Number Of Seat Rows For Bus : (Each Row Has 2 Seats)");
                seatRow = scan.nextInt();
                cap += seatRow * 2;
                totalPrice += cap * multiplier;
                System.out.println("Total Price By Now :" + totalPrice + "\tYour Balance :" + city.balance);
                System.out.println("Confirm Seats? (y/n)");
                String confirmation = scan.next();
                if (confirmation.equals("y")) {
                    break;
                } else {
                    continue;
                }
            }
            System.out.println("Select Max Speed : ");
            double maxSpeed = scan.nextDouble();
            BusTerminal.addBus(new Bus(name, totalPrice, (cap + 1), seatRow, n, maxSpeed, color, city), city);
        } else {
            System.out.println("Not Enough Money!");
        }
    }
}
