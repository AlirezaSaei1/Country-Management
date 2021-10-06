package Main.Travel;
import Main.Person;
import Main.Vehicle.Vehicle;
import java.util.ArrayList;

public interface Travelable {
    void showHistory();
    void addTrip(Travels t);
    void sortTravels();
    double calculateCost(ArrayList<Person> passengers, Vehicle v);

}
