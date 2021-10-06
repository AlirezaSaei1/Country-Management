package Main;

import Main.Building.Harbor;
import Main.Exceptions.InvalidInput;
import Main.Vehicle.Ship;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BuyShipController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void showMenu(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/BuyShip.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Ship Ordering Page");
        stage.show();
    }
    @FXML
    Label myLabel;
    @FXML
    TextField sName;
    @FXML
    TextField sCap;
    @FXML
    ChoiceBox<String> sFuel;
    @FXML
    ChoiceBox<String> sHarbor;
    ArrayList<String> list = new ArrayList<>();
    String[] f = {"Solar Energy", "Electricity", "Gas", "Coal", "Oil"};
    public void order(ActionEvent e){
        Harbor harbor = null;
        double price = 400;
        boolean luxury = false;
        try{
            String name = sName.getText();
            int capacity = Integer.parseInt(sCap.getText());
            String fuel = sFuel.getValue();
            String harborName = sHarbor.getValue();
            for(Harbor h : Controller.enteredCity.harbour){
                if(h.name.equals(harborName.split(" ")[1])){
                    harbor = h;
                    break;
                }
            }
            if (harbor == null) {
                throw new NullPointerException();
            }
            if (name.equals("")) {
                throw new InvalidInput();
            }

            if(fuel.equals("Solar Energy")){
                price += 150;
            }else if ( fuel.equals("Electricity")){
                price += 100;
            }else{
                price += 40;
            }

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Order");
            a.setHeaderText("Luxury Ship");
            a.setContentText("Do you want to make the ship luxury?");
            if(a.showAndWait().get() == ButtonType.OK){
                luxury = true;
            }

            harbor.ships.add(new Ship(name, price, capacity, fuel, luxury, Controller.enteredCity));

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Order");
            alert.setHeaderText("Thanks For Your Purchase");
            alert.setContentText("Ship Ordered Successfully: " + price + "$");
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
        for(Harbor h : Controller.enteredCity.harbour){
            list.add("Name: " + h.name + " HarborCount: " + h.harborCount);
        }
        sHarbor.getItems().addAll(list);
        sFuel.getItems().addAll(f);
    }
}
