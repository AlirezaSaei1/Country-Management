package Main.Vehicle;

import Main.Building.RailwayStation;
import Main.City;
import java.util.ArrayList;
import java.util.Scanner;

public class Train extends GroundVehicle {
    static Scanner scan = new Scanner(System.in);
    int wagonCount;
    public ArrayList<Main.Vehicle.Entertainment> ent;
    public int stars;
    static int TrainID = 1;

    public Train(String name, double price, ArrayList<Entertainment> ent, int capacity, double maxSpeed, String Color, int wagonCount, int stars, City city) {
        super(name, price, capacity, maxSpeed, Color, TrainID);
        TrainID++;
        this.stars = stars;
        this.ent = ent;
        this.wagonCount = wagonCount;
        city.balance -= price;
    }


    public static void buyTrain(City city) {
        int star;
        int cap ;
        double totalPrice = 200;
        double multiplier = 1;
        double maxSpeed = 50;
        if (city.balance >= 200) {
            System.out.println("Select Company Name : (Current Price 200$)");
            String name = scan.next();
            System.out.println("Select Color : ");
            String color = scan.next();
            while (true) {
                System.out.println("Set Stars For Wagons :\n1: 1star -------> cap : 5\n2: 2star -------> cap : 10\n3: 3star -------> cap : 15\n4: 4star -------> cap : 20\n5: 5star -------> cap : 25");
                star = scan.nextInt();
                if (star == 1) {
                    multiplier = 0.25;
                    cap = 5;
                    break;
                }
                if (star == 2) {
                    multiplier = 0.5;
                    cap = 10;
                    break;
                }
                if (star == 3) {
                    multiplier = 1;
                    cap = 15;
                    break;
                }
                if (star == 4) {
                    multiplier = 1.25;
                    cap = 20;
                    break;
                }
                if (star == 5) {
                    multiplier = 1.5;
                    cap = 25;
                    break;
                }
                if (star != 1 && star != 2 && star != 3 && star != 4 && star != 5) {
                    System.out.println("Invalid Number Of Stars!");
                }
            }
            System.out.println("Buy Number Of Wagons For Train : (" + (100 * multiplier) + "$ Each One)");
            int wagon = scan.nextInt();
            totalPrice += wagon * 100;
            if (wagon < 0) {
                System.out.println("Are You Serious? :/");
                wagon = 0;
            }
            if (wagon >= 0 && wagon < 3) {
                maxSpeed = 100;
            }
            if (wagon >= 3 && wagon < 6) {
                maxSpeed = 80;
            }
            if (wagon >= 6 && wagon < 10) {
                maxSpeed = 60;
            }
            if (wagon >= 10) {
                maxSpeed = 40;
            }

            ArrayList<Main.Vehicle.Entertainment> e = new ArrayList<>();
            System.out.println("Choose Entertainment For Train : ");
            Main.Vehicle.Entertainment[] ent = Main.Vehicle.Entertainment.values();
            for (int i = 0; i < 4; i++) {
                System.out.printf("%d : %-10s \t%5d$\n", (i + 1), ent[i], 100);
            }
            while (true) {
                System.out.println("Enter A Number :");
                e.add(ent[scan.nextInt() - 1]);
                totalPrice += 100;
                System.out.println("Add Another One?(y/n)" + "Price Of Train Up To Now : " + totalPrice + "$");
                String a = scan.next();
                if (a.equals("y")) {
                    continue;
                } else {
                    break;
                }
            }
            RailwayStation.addTrain(new Train(name, totalPrice, e, (cap * wagon), maxSpeed, color, wagon, star, city), city);
        } else {
            System.out.println("Not Enough Money!");
        }
    }
}
