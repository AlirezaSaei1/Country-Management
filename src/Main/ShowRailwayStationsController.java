package Main;

import Main.Building.Harbor;
import Main.Building.RailwayStation;
import Main.Vehicle.Boat;
import Main.Vehicle.Ship;
import Main.Vehicle.Train;
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

public class ShowRailwayStationsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ListView<String> railwayStations;
    ArrayList<String> list = new ArrayList<>();

    public void showTrainStations(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/RailwayStations.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Train Stations");
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
        for (RailwayStation a : Controller.enteredCity.railwayStations) {
            StringBuilder vehicles = new StringBuilder("Trains: ");
            for (Train p : a.getTrains()) {
                vehicles.append("[ Name:" + p.companyName + "  Entertainments: " + p.ent.toString() + "] ");
            }
            list.add(String.format("Name: %5s     Address: %5s\n   %s", a.name, a.address, vehicles));
        }
        railwayStations.getItems().addAll(list);
    }
}
