package Main.Vehicle;

import Main.Building.*;
import Main.City;

import java.util.Scanner;

public class Boat extends WaterVehicle {
    static Scanner scan = new Scanner(System.in);
    String color;
    public boolean privateBoat;
    static int BoatID = 1;

    public Boat(String name, double price, int capacity, String fuelType, double minDepth, String color, boolean privateBoat, City city) {
        super(name, price, capacity, fuelType, minDepth, BoatID);
        BoatID++;
        this.color = color;
        this.privateBoat = privateBoat;
        city.balance -= price;
    }

    public static void buyBoat(City city) {
        String fuelType;
        double minDepth;
        double totalPrice = 0;
        boolean pvBoat = false;
        int cap = 0;
        String color = "White";
        System.out.println("Is It Going TO Be A Private Boat? (y/n)");
        System.out.println("Note : Default Boats Are Not Private!");
        String pv = scan.next();
        if (pv.equals("y")) {
            totalPrice = 800;
            pvBoat = true;
            if (city.balance >= 800) {
                System.out.println("Set Company Name : ");
                String companyName = scan.next();
                System.out.println("Select Color : (Super Luxury : Gold ----> Extra Cost: 200$)");
                color = scan.next();
                if (color.equals("Gold")) {
                    totalPrice += 200;
                }
                while (true) {
                    System.out.println("Set Capacity For Beautiful " + color + " Private Boat");
                    cap = scan.nextInt();
                    if (cap < 1) {
                        System.out.println("A Ghost Boat? :/");
                        continue;
                    }
                    if (cap >= 1 && cap <= 5) {
                        minDepth = 10;
                        break;
                    }
                    if (cap > 5 && cap <= 10) {
                        minDepth = 20;
                        break;
                    }
                    if (cap > 10) {
                        minDepth = 30;
                        break;
                    }
                }
                while (true) {
                    System.out.println("Set Your Boats fuel Type: (1:Coal, 2:Oil, 3:Gas, 4:Electricity(Extra Will Be Applied : 100$))");
                    int in = scan.nextInt();
                    if (in == 1) {
                        fuelType = "Coal";
                        break;
                    }
                    if (in == 2) {
                        fuelType = "Oil";
                        break;
                    }
                    if (in == 3) {
                        fuelType = "Gas";
                        break;
                    }
                    if (in == 4) {
                        fuelType = "Electricity";
                        totalPrice += 100;
                        break;
                    }
                    if (in != 1 && in != 2 && in != 3 && in != 4) {
                        System.out.println("Do You Need Paddles To Row Yourself? :/");
                        continue;
                    }
                }
                Harbor.addBoat(new Boat(companyName, totalPrice, cap, fuelType, minDepth, color, pvBoat, city), city);
            } else {
                System.out.println("Not Enough Money!");
            }
        } else {
            pvBoat = false;
            totalPrice = 600;
            if (city.balance >= 600) {
                System.out.println("Set Company Name : ");
                String companyName = scan.next();
                System.out.println("Select Color : ");
                color = scan.next();
                while (true) {
                    System.out.println("Set Capacity For Boat : ");
                    cap = scan.nextInt();
                    if (cap < 1) {
                        System.out.println("A Ghost Boat? :/");
                        continue;
                    }
                    if (cap >= 1 && cap <= 5) {
                        minDepth = 10;
                        break;
                    }
                    if (cap > 5 && cap <= 10) {
                        minDepth = 20;
                        break;
                    }
                    if (cap > 10) {
                        minDepth = 30;
                        break;
                    }
                }
                while (true) {
                    System.out.println("Set Your Boats fuel Type: (1:Coal, 2:Oil, 3:Gas, 4:Electricity(Extra Will Be Applied : 100$))");
                    int in = scan.nextInt();
                    if (in == 1) {
                        fuelType = "Coal";
                        break;
                    }
                    if (in == 2) {
                        fuelType = "Oil";
                        break;
                    }
                    if (in == 3) {
                        fuelType = "Gas";
                        break;
                    }
                    if (in == 4) {
                        fuelType = "Electricity";
                        totalPrice += 100;
                        break;
                    }
                    if (in != 1 && in != 2 && in != 3 && in != 4) {
                        System.out.println("Do You Need Paddles To Row Yourself? :/");
                        continue;
                    }
                }
                Harbor.addBoat(new Boat(companyName, totalPrice, cap, fuelType, minDepth, color, pvBoat, city), city);
            } else {
                System.out.println("Not Enough Money!");
            }
        }
    }
}
