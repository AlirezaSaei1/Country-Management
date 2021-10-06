package Main;

import Main.Building.Airport;
import Main.Exceptions.InvalidInput;
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

public class BuyPPlaneController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void showMenu(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/BuyPassengerPlane.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Passenger Plane Ordering Page");
        stage.show();
    }
    @FXML
    Label myLabel;
    @FXML
    TextField ppName;
    @FXML
    TextField ppCap;
    @FXML
    TextField ppHeight;
    @FXML
    TextField ppCount;
    @FXML
    TextField ppRate;
    @FXML
    ChoiceBox<String> ppList;
    ArrayList<String> airports = new ArrayList<>();

    public void order(ActionEvent e) {
        Airport a = null;
        double price = 350;
        int multiplier;
        try{
            String name = ppName.getText();
            int capacity = Integer.parseInt(ppCap.getText());
            double height = Double.parseDouble(ppHeight.getText());
            int seatRate = Integer.parseInt(ppRate.getText());
            int seatCount = Integer.parseInt(ppCount.getText());
            String airport = ppList.getValue();
            for(Airport air : Controller.enteredCity.airports){
                if(air.name.equals(airport.split(" ")[1])){
                    a = air;
                    break;
                }
            }
            if(a == null){
                throw new NullPointerException();
            }
            if(name.equals("")){
                throw new InvalidInput();
            }
            switch (seatRate) {
                case 1 -> {
                    multiplier = 10;
                }
                case 2 -> {
                    multiplier = 5;
                }
                case 3 -> {
                    multiplier = 2;
                }
                default -> {
                    seatRate = 2;
                    multiplier = 2;
                }
            }
            price += seatCount * multiplier;
            a.AllPlane.add(new PassengerPlane(name, price, capacity, height, 1000, seatRate, 10, Controller.enteredCity));
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Order");
            alert.setHeaderText("Thanks For Your Purchase");
            alert.setContentText("Passenger Plane Ordered Successfully: " + price + "$");
            alert.showAndWait();
            showMenu(e);
        }catch (Exception exception){
            myLabel.setText(exception.getMessage());
        }
    }

    public void Return(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BuyVehicle.fxml"));
        root = loader.load();
        VehicleController c1 = loader.getController();
        c1.buyMenu(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Airport a : Controller.enteredCity.airports){
            airports.add("Name: " + a.name + " Vehicles Stored: " + a.AllPlane.size());
        }
        ppList.getItems().addAll(airports);
    }
}
