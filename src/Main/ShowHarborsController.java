package Main;

import Main.Building.Harbor;
import Main.Vehicle.*;
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

public class ShowHarborsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ListView<String> harbors;
    ArrayList<String> list = new ArrayList<>();
    public void showHarbors(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/Harbors.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Harbors");
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
        for (Harbor a : Controller.enteredCity.harbour) {
            StringBuilder vehicles = new StringBuilder("Ships: ");
            for (Ship p : a.ships) {
                vehicles.append("[ Name:" + p.companyName + "  Luxury: " + p.luxury + "] ");
            }
            vehicles.append("\nBoats: ");
            for (Boat p : a.boats) {
                vehicles.append("[ Name:" + p.companyName + "  Private: " + p.privateBoat + "] ");
            }
            list.add(String.format("Name: %5s     Address: %5s\n   %s", a.name, a.address, vehicles));
        }
        harbors.getItems().addAll(list);
    }
}
