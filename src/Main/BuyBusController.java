package Main;

import Main.Building.BusTerminal;
import Main.Exceptions.InvalidInput;
import Main.Vehicle.Bus;
import com.sun.javafx.image.IntPixelGetter;
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

public class BuyBusController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void showMenu(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/BuyBus.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bus Ordering Page");
        stage.show();
    }

    @FXML
    Label myLabel;
    @FXML
    TextField bName;
    @FXML
    TextField bSpeed;
    @FXML
    TextField bCap;
    @FXML
    TextField bRows;
    @FXML
    TextField bRate;
    @FXML
    ChoiceBox<String> bColor;
    @FXML
    ChoiceBox<String> bTerminal;
    String[] c = {"Red", "blue", "Grey", "Brown", "Black", "White"};
    ArrayList<String> list = new ArrayList<>();
    public void order(ActionEvent e) {
        BusTerminal busTerminal = null;
        int multiplier;
        double price = 200;
        try {
            String name = bName.getText();
            String color = bColor.getValue();
            double speed = Double.parseDouble(bSpeed.getText());
            int capacity = Integer.parseInt(bCap.getText());
            int rows = Integer.parseInt(bRows.getText());
            int rating = Integer.parseInt(bRate.getText());
            String busT = bTerminal.getValue();
            for(BusTerminal bt : Controller.enteredCity.busTerminals){
                if(bt.name.equals(busT.split(" ")[1])){
                    busTerminal = bt;
                    break;
                }
            }
            if (busTerminal == null) {
                throw new NullPointerException();
            }
            if (name.equals("")) {
                throw new InvalidInput();
            }
            switch (rating) {
                case 1: {
                    multiplier = 10;
                    break;
                }
                case 2: {
                    multiplier = 5;
                    break;
                }
                case 3: {
                    multiplier = 2;
                    break;
                }
                default: {
                    rating = 2;
                    multiplier = 2;
                }
            }
            capacity += rows * 2;
            price += capacity * multiplier;

            busTerminal.buses.add(new Bus(name, price, capacity, rows, rating, speed, color, Controller.enteredCity));

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Order");
            alert.setHeaderText("Thanks For Your Purchase");
            alert.setContentText("Bus Ordered Successfully: " + price + "$");
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
        for(BusTerminal bt : Controller.enteredCity.busTerminals){
            list.add("Name: " + bt.name + " Buses: " + bt.buses.size());
        }
        bTerminal.getItems().addAll(list);
        bColor.getItems().addAll(c);
    }
}
