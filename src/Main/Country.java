package Main;

import java.io.Serializable;
import java.util.ArrayList;

public class Country implements Serializable {
    double BankedCash;
    public static ArrayList<City> country = new ArrayList<>();
    public static ArrayList<Person> totalPopulation = new ArrayList<>();

    public static void addCity(City c){
        country.add(c);
    }
    public static double calculateTotalMoney(){
        double total = 0;
        for(int i=0; i<country.size(); i++){
            total+=country.get(i).balance;
        }
        return total;
    }
    public static int calculateTotalPopulation(){
        int totalPopulation = 0;
        for(int i=0; i<country.size(); i++){
            totalPopulation+=country.get(i).population.size();
        }
        return totalPopulation;
    }
}
