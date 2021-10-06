package Main;

import Main.Building.Airport;
import Main.Exceptions.InvalidInput;
import Main.Exceptions.WeightTooHigh;
import Main.Vehicle.CargoPlane;
import Main.Vehicle.PassengerPlane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BuyCPlaneController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void showMenu(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/BuyCargoPlane.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Cargo Plane Ordering Page");
        stage.show();
    }

    @FXML
    Label myLabel;
    @FXML
    TextField cpName;
    @FXML
    TextField cpCap;
    @FXML
    TextField cpHeight;
    @FXML
    TextField cpWeight;
    @FXML
    ChoiceBox<String> cpList;
    ArrayList<String> airports = new ArrayList<>();

    public void order(ActionEvent e){
        Airport a = null;
        double price = 250;
        try {
            String name = cpName.getText();
            int capacity = Integer.parseInt(cpCap.getText());
            double height = Double.parseDouble(cpHeight.getText());
            double weight = Double.parseDouble(cpWeight.getText());
            String airport = cpList.getValue();
            for (Airport air : Controller.enteredCity.airports) {
                if (air.name.equals(airport.split(" ")[1])) {
                    a = air;
                    break;
                }
            }
            if (a == null) {
                throw new NullPointerException();
            }
            if (name.equals("")) {
                throw new InvalidInput();
            }
            if (weight >= 0 && weight < 2500) {
                price += 100;
            } else if (weight >= 2500 && weight < 5000) {
                price += 150;
            } else if (weight >= 5000 && weight < 7500) {
                price += 200;
            } else if (weight >= 7500 && weight <= 10000) {
                price += 250;
            } else {
                throw new WeightTooHigh("Cargo Cannot Take off : Change Max Weight");
            }

            a.AllPlane.add(new CargoPlane(name, price, capacity, weight, height, 1000, Controller.enteredCity));
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Order");
            alert.setHeaderText("Thanks For Your Purchase");
            alert.setContentText("Cargo Plane Ordered Successfully: " + price + "$");
            alert.showAndWait();
            showMenu(e);
        } catch (Exception exception) {
            myLabel.setText(exception.getMessage());
        }
    }

    public void Return(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BuyVehicle.fxml"));
        root = loader.load();
        VehicleController c1 = loader.getController();
        c1.buyMenu(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Airport a : Controller.enteredCity.airports) {
            airports.add("Name: " + a.name + " Vehicles Stored: " + a.AllPlane.size());
        }
        cpList.getItems().addAll(airports);
    }
}
