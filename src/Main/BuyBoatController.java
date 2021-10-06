package Main;

import Main.Building.Harbor;
import Main.Exceptions.InvalidInput;
import Main.Vehicle.Boat;
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

public class BuyBoatController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void showMenu(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/BuyBoat.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Boat Ordering Page");
        stage.show();
    }

    @FXML
    Label myLabel;
    @FXML
    TextField bName;
    @FXML
    TextField bCap;
    @FXML
    TextField bDepth;
    @FXML
    ChoiceBox<String> bFuel;
    @FXML
    ChoiceBox<String> bColor;
    @FXML
    ChoiceBox<String> hbr;
    ArrayList<String> list = new ArrayList<>();
    String[] f = {"Solar Energy", "Electricity", "Gas", "Coal", "Oil"};
    String[] c = {"Gold", "Red", "blue", "Grey", "Brown", "Black", "White"};
    public void order(ActionEvent e){
        Harbor harbor = null;
        double price = 450;
        boolean pv = false;
        try{
            String name = bName.getText();
            int capacity = Integer.parseInt(bCap.getText());
            double minDepth = Double.parseDouble(bDepth.getText());
            String color = bColor.getValue();
            String fuel = bFuel.getValue();
            String harborName = hbr.getValue();
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

            if(color.equals("Gold")){
                price += 100;
            }else{
                price =+ 40;
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
            a.setHeaderText("Private Boat");
            a.setContentText("Do you want to make the boat private?");
            if(a.showAndWait().get() == ButtonType.OK){
                pv = true;
            }

            harbor.boats.add(new Boat(name, price, capacity, fuel, minDepth, color, pv, Controller.enteredCity));

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Order");
            alert.setHeaderText("Thanks For Your Purchase");
            alert.setContentText("Boat Ordered Successfully: " + price + "$");
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
        hbr.getItems().addAll(list);
        bFuel.getItems().addAll(f);
        bColor.getItems().addAll(c);
    }
}
