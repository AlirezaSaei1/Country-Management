package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class TerminalTypeController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void typeSelect(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/TerminalType.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Select Type");
        stage.show();
    }

    public void Harbor(ActionEvent e) throws Exception{
        if(Controller.enteredCity.harbour.size()==0){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Alert!");
            a.setHeaderText("Harbor Warning");
            a.setContentText("No Harbors Available");
            a.showAndWait();
            typeSelect(e);
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/Harbors.fxml"));
            root = loader.load();
            ShowHarborsController c1 = loader.getController();
            c1.showHarbors(e);
        }
    }

    public void RailwayStation(ActionEvent e) throws Exception{
        if(Controller.enteredCity.railwayStations.size()==0){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Alert!");
            a.setHeaderText("Railway Station Warning");
            a.setContentText("No Railway Stations Available");
            a.showAndWait();
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/RailwayStations.fxml"));
            root = loader.load();
            ShowRailwayStationsController c1 = loader.getController();
            c1.showTrainStations(e);
        }
    }

    public void Airport(ActionEvent e) throws Exception{
        if(Controller.enteredCity.airports.size()==0){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Alert!");
            a.setHeaderText("Airport Warning");
            a.setContentText("No Airports Available");
            a.showAndWait();
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/Airports.fxml"));
            root = loader.load();
            ShowAirportsController c1 = loader.getController();
            c1.showAirports(e);
        }
    }

    public void BusTerminal(ActionEvent e) throws Exception{
        if(Controller.enteredCity.busTerminals.size()==0){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Alert!");
            a.setHeaderText("Bus Terminal Warning");
            a.setContentText("No Bus Terminals Available");
            a.showAndWait();
            typeSelect(e);
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BusTerminals.fxml"));
            root = loader.load();
            ShowBusTerminalsController c1 = loader.getController();
            c1.showBusTerminals(e);
        }
    }

    public void back(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
    }
}
