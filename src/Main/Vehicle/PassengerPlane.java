package Main.Vehicle;

import Main.Building.Airport;
import Main.City;
import java.util.*;

public class PassengerPlane extends AirVehicle {
    static Scanner scan = new Scanner(System.in);
    int staffCount;
    public int seatRating;
    static int pPlaneID = 1;

    public PassengerPlane(String companyName, double price, int capacity, double maxHeight, double runwayLength, int seatRate, int staffCount, City city) {
        super(companyName, price, capacity, maxHeight, runwayLength, pPlaneID);
        pPlaneID++;
        this.seatRating = seatRate;
        this.staffCount = staffCount;
        city.balance -= price;
    }

    public static void buyPassengerPlane(City city) {
        double totalPrice = 600;
        int multiplier;
        int cap = 0;
        if (city.balance >= 600) {
            System.out.println("Set Airplane Name : (Airplane Cost: 600$)");
            String name = scan.next();
            System.out.println("Set Seat Rating : (1 (10$ each)/2 (5$ each)(recommended)/3 (2$ each)");
            int m = scan.nextInt();
            switch (m) {
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
                    m = 2;
                    multiplier = 2;
                    System.out.println("Automatically Set To Rate: 2");
                }
            }
            while (true) {
                System.out.println("Set Number Of Seats For Airplane : ");
                int seatNum = scan.nextInt();
                cap += seatNum;
                totalPrice += seatNum * multiplier;
                System.out.println("Total Price By Now :" + totalPrice + "\t\tYour Balance:" + city.balance);
                System.out.println("Confirm Seats? (y/n)");
                String confirmation = scan.next();
                if (confirmation.equals("y")) {
                    break;
                } else {
                    continue;
                }
            }
            System.out.println("Set Staff Count To Your Airplane : ");
            int staffCount = scan.nextInt();
            cap += staffCount;
            System.out.println("Set Max Height For Flight : ");
            double maxHeight = scan.nextDouble();
            System.out.println("Set Runway Length Required : ");
            double runwayLength = scan.nextDouble();

            Airport.addPPlaneToHangar(new PassengerPlane(name, totalPrice, (cap + 1), maxHeight, runwayLength, m, staffCount, city), city);
        } else {
            System.out.println("Not Enough Money!");
        }
    }
}
