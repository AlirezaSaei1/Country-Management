package Main;

import Main.Building.Airport;
import Main.Building.BusTerminal;
import Main.Travel.Travels;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TravelListController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ListView<String> InG;
    @FXML
    ListView<String> InA;
    @FXML
    ListView<String> OutG;
    @FXML
    ListView<String> OutA;

    ArrayList<Travels> airOut = new ArrayList<>();
    ArrayList<Travels> groundOut = new ArrayList<>();
    ArrayList<Travels> airIn = new ArrayList<>();
    ArrayList<Travels> groundIn = new ArrayList<>();

    ArrayList<String> aO = new ArrayList<>();
    ArrayList<String> gO = new ArrayList<>();
    ArrayList<String> aI = new ArrayList<>();
    ArrayList<String> gI = new ArrayList<>();

    public void showT(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/TravelsList.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Travels");
        stage.show();
    }
    public void returnToMenu(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (BusTerminal t : Controller.enteredCity.busTerminals) {
            groundOut.addAll(t.outTravel);
            groundIn.addAll(t.inTravel);
        }
        for (Airport a : Controller.enteredCity.airports) {
            airOut.addAll(a.outTravel);
            airIn.addAll(a.inTravel);
        }
        airOut.sort(Travels::compareTo);
        groundOut.sort(Travels::compareTo);
        airOut.sort(Travels::compareTo);
        groundOut.sort(Travels::compareTo);

        for (Travels t : airOut) {
            aO.add(String.format("ID: %d \t City: %5s \t Destination: %5s \t Date: %5s \t Cost: %f", t.getTravelId(), t.getDepartureCity().cityName, t.getArrivalCity().cityName, t.getTravelDate(), t.getTripCost()));
        }
        for (Travels t : groundOut) {
            gO.add(String.format("ID: %d \t City: %5s \t Destination: %5s \t Date : %5s \t Cost: %f", t.getTravelId(), t.getDepartureCity().cityName, t.getArrivalCity().cityName, t.getTravelDate(), t.getTripCost()));
        }
        for (Travels t : airIn) {
            aI.add(String.format("ID: %d \t City: %5s \t ComingFrom: %5s \t Date: %5s \t Cost: %f", t.getTravelId(), t.getArrivalCity().cityName, t.getDepartureCity().cityName, t.getTravelDate(), t.getTripCost()));
        }
        for (Travels t : groundIn) {
            gI.add(String.format("ID: %d \t City: %5s \t ComingFrom: %5s \t Date : %5s \t Cost: %f", t.getTravelId(), t.getArrivalCity().cityName, t.getDepartureCity().cityName, t.getTravelDate(), t.getTripCost()));
        }
        InG.getItems().addAll(gI);
        OutG.getItems().addAll(gO);
        InA.getItems().addAll(aI);
        OutA.getItems().addAll(aO);
    }
}
