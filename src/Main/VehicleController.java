package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class VehicleController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void buyMenu(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/BuyVehicle.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Vehicle Ordering Page");
        stage.show();
    }

    public void buyPassengerPlane(ActionEvent e) throws Exception{
        if(Controller.enteredCity.balance <= 350){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot Place Order");
            alert.setContentText("Insufficient Money (350$ Needed)");
            alert.showAndWait();
            buyMenu(e);
        }
        if(Controller.enteredCity.airports.size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Cannot Order");
            alert.setContentText("No Airports Available to store vehicle");
            alert.showAndWait();
            buyMenu(e);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BuyPassengerPlane.fxml"));
            root = loader.load();
            BuyPPlaneController c1 = loader.getController();
            c1.showMenu(e);
        }
    }

    public void buyCargoPlane(ActionEvent e) throws Exception{
        if(Controller.enteredCity.balance <= 250){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot Place Order");
            alert.setContentText("Insufficient Money (250$ Needed)");
            alert.showAndWait();
            buyMenu(e);
        }
        if(Controller.enteredCity.airports.size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Cannot Order");
            alert.setContentText("No Airports Available to store vehicle");
            alert.showAndWait();
            buyMenu(e);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BuyCargoPlane.fxml"));
            root = loader.load();
            BuyCPlaneController c1 = loader.getController();
            c1.showMenu(e);
        }
    }

    public void buyTrain(ActionEvent e) throws Exception{
        if(Controller.enteredCity.balance <= 150){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot Place Order");
            alert.setContentText("Insufficient Money (150$ Needed)");
            alert.showAndWait();
            buyMenu(e);
        }
        if(Controller.enteredCity.railwayStations.size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Cannot Order");
            alert.setContentText("No Railway Stations Available to store vehicle");
            alert.showAndWait();
            buyMenu(e);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BuyTrain.fxml"));
            root = loader.load();
            BuyTrainController c1 = loader.getController();
            c1.showMenu(e);
        }
    }

    public void buyBus(ActionEvent e) throws Exception{
        if(Controller.enteredCity.balance <= 200){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot Place Order");
            alert.setContentText("Insufficient Money (200$ Needed)");
            alert.showAndWait();
            buyMenu(e);
        }
        if(Controller.enteredCity.busTerminals.size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Cannot Order");
            alert.setContentText("No Bus Terminals Available to store vehicle");
            alert.showAndWait();
            buyMenu(e);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BuyBus.fxml"));
            root = loader.load();
            BuyBusController c1 = loader.getController();
            c1.showMenu(e);
        }
    }

    public void buyShip(ActionEvent e) throws Exception{
        if(Controller.enteredCity.balance <= 400){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot Place Order");
            alert.setContentText("Insufficient Money (400$ Needed)");
            alert.showAndWait();
            buyMenu(e);
        }
        if(Controller.enteredCity.harbour.size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Cannot Order");
            alert.setContentText("No Harbors Available to store vehicle");
            alert.showAndWait();
            buyMenu(e);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BuyShip.fxml"));
            root = loader.load();
            BuyShipController c1 = loader.getController();
            c1.showMenu(e);
        }
    }

    public void buyBoat(ActionEvent e) throws Exception{
        if(Controller.enteredCity.balance <= 450){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot Place Order");
            alert.setContentText("Insufficient Money (450$ Needed)");
            alert.showAndWait();
            buyMenu(e);
        }
        if(Controller.enteredCity.harbour.size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Cannot Order");
            alert.setContentText("No Harbors Available to store vehicle");
            alert.showAndWait();
            buyMenu(e);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BuyBoat.fxml"));
            root = loader.load();
            BuyBoatController c1 = loader.getController();
            c1.showMenu(e);
        }
    }

    public void back(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
    }
}
