package Main;

import Main.Building.Airport;
import Main.Vehicle.AirVehicle;
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

public class ShowAirportsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ListView<String> airports;
    ArrayList<String> list = new ArrayList<>();
    public void showAirports(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/Airports.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Airports");
        stage.show();
    }

    public void done(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/TerminalType.fxml"));
        root = loader.load();
        TerminalTypeController c1 = loader.getController();
        c1.typeSelect(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Airport a : Controller.enteredCity.airports){
            StringBuilder planes = new StringBuilder("Planes: ");
            for(AirVehicle p : a.AllPlane){
                if(p instanceof PassengerPlane) {
                    planes.append("[ Name:" + p.companyName + "  Type: Passenger] ");
                }else{
                    planes.append("[ Name:" + p.companyName + "  Type: Cargo] ");
                }
            }
            list.add(String.format("Name: %5s     Address: %5s     International: %b     Runways: %d\n   %s", a.name, a.address, a.international, a.runwayCount, planes));
        }
        airports.getItems().addAll(list);
    }
}
