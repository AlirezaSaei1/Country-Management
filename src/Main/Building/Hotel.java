package Main.Building;

import Main.City;
import Main.Exceptions.InvalidInput;
import Main.Exceptions.NoMoney;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Hotel implements Serializable {
    static Scanner scan = new Scanner(System.in);
    public String hotelName;
    public double price;
    public String address;
    public int stars;
    public int roomCount;
    public ArrayList<Room> rooms;
    public ArrayList<Entertainment> ent;

    public Hotel(String hotelName, double price, String address, int stars, int roomCount, ArrayList<Room> rooms, ArrayList<Entertainment> entertainment, City city) throws Exception {
        if(hotelName.equals("")){
            throw new InvalidInput();
        }
        if(address.equals("")){
            throw new InvalidInput();
        }
        if(city.balance < price){
            throw new NoMoney();
        }
        this.price = price;
        this.address = address;
        this.hotelName = hotelName;
        this.stars = stars;
        this.roomCount = roomCount;
        this.rooms = rooms;
        this.ent = entertainment;
        city.balance -= price;
    }

    public static void setHotelInfo(City city) {
        if (city.balance >= 700) {
            System.out.println("Enter Hotel Name : ");
            String hotelName = scan.nextLine();
            System.out.println("Set Hotel Address : ");
            String address = scan.nextLine();
            System.out.println("Set Number Of Rooms : (Cost Of Each Room 100$)");
            int roomCount = scan.nextInt();
            ArrayList<Room> r = new ArrayList<>();
            for (int i = 0; i < roomCount; i++) {
                System.out.println("Number Of Beds For Room " + (i + 1) + ":");
                int counts = scan.nextInt();
                r.add(new Room(counts));
            }
            double p = 700.0 + (r.size() * 100);
            while (true) {
                System.out.println("Set Hotel Stars : ");
                int stars = scan.nextInt();
                System.out.println("The Hotel Price Up To Now Will Be : " + (p + (stars * 100)) + "$\nConfirm Stars? (y/n)");
                String confirmation = scan.next();
                if (confirmation.equals("n")) {
                    continue;
                } else {
                    p += (stars * 100);
                    ArrayList<Entertainment> e = new ArrayList<>();
                    System.out.println("Choose Entertainment For Hotel : ");
                    Entertainment[] ent = Entertainment.values();
                    for (int i = 0; i < 5; i++) {
                        System.out.printf("%d : %-10s \t%5d$\n", (i + 1), ent[i], 100);
                    }
                    while (true) {
                        System.out.println("Enter A Number :");
                        e.add(ent[scan.nextInt() - 1]);
                        p += 100;
                        System.out.println("Add Another One?(y/n)" + "Price Of Hotel Up To Now :" + p + "$");
                        String a = scan.next();
                        if (a.equals("y")) {
                            continue;
                        } else {
                            break;
                        }
                    }
                    try {
                        city.addHotel(new Hotel(hotelName, p, address, stars, roomCount, r, e, city), city);
                        System.out.println("Construction Successful with " + p + "$");
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }

                }
                break;
            }
        } else {
            System.out.println("Not Enough Money!");
        }
    }

}
