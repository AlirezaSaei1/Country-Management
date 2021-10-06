package Main;

import Main.Building.Airport;
import Main.Building.BusTerminal;
import Main.Vehicle.AirVehicle;
import Main.Vehicle.Bus;
import Main.Vehicle.PassengerPlane;
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

public class ShowBusTerminalsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ListView<String> busTerminals;
    ArrayList<String> list = new ArrayList<>();
    public void showBusTerminals(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/BusTerminals.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bus Terminals");
        stage.show();
    }

    public void done(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/TerminalType.fxml"));
        root = loader.load();
        TerminalTypeController c1 = loader.getController();
        c1.typeSelect(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(BusTerminal a : Controller.enteredCity.busTerminals){
            StringBuilder buses = new StringBuilder("Buses: ");
            for(Bus p : a.buses){
                buses.append("[ Name: " + p.companyName + "  SeatRate: " + p.seatRate + " ]");
            }
            list.add(String.format("Name: %5s     Address: %5s     BusCount: %d\n   %s", a.name, a.address, a.buses.size(), buses));
        }
        busTerminals.getItems().addAll(list);
    }
}
