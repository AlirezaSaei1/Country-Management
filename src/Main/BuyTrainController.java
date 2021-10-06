package Main;

import Main.Building.RailwayStation;
import Main.Exceptions.InvalidInput;
import Main.Vehicle.Entertainment;
import Main.Vehicle.Train;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class BuyTrainController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void showMenu(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/BuyTrain.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Train Ordering Page");
        stage.show();
    }
    @FXML
    ChoiceBox<String> entertain;
    @FXML
    ChoiceBox<String> tRailway;
    @FXML
    ChoiceBox<String> tStars;
    @FXML
    ChoiceBox<String> tColors;
    @FXML
    Label EntertainmentLabel;
    @FXML
    Label myLabel;
    @FXML
    TextField tName;
    @FXML
    TextField tCount;

    Entertainment[] entertainments = Entertainment.values();
    ArrayList<String> ent = new ArrayList<>();
    ArrayList<Entertainment> TrainEnt = new ArrayList<>();
    ArrayList<String> list = new ArrayList<>();
    String[] c = {"Red", "blue", "Grey", "Brown", "Black", "White"};
    String[] s = {"1", "2", "3", "4", "5"};
    public void order(ActionEvent e){
        RailwayStation railwayStation = null;
        double price = 150;
        double multiplier = 1;
        int capacity = 5;
        double speed = 40;
        try{
            String name = tName.getText();
            int wagonCount = Integer.parseInt(tCount.getText());
            int stars = Integer.parseInt(tStars.getValue());
            String color = tColors.getValue();
            String railwayName = tRailway.getValue();

            for(RailwayStation rs : Controller.enteredCity.railwayStations){
                if(rs.name.equals(railwayName.split(" ")[1])){
                    railwayStation = rs;
                    break;
                }
            }

            if (railwayStation == null) {
                throw new NullPointerException();
            }
            if (name.equals("")) {
                throw new InvalidInput();
            }
            //---------------------------------
            if (stars == 1) {
                multiplier = 0.25;
                capacity = 5;
            }
            if (stars == 2) {
                multiplier = 0.5;
                capacity = 10;
            }
            if (stars == 3) {
                multiplier = 1;
                capacity = 15;
            }
            if (stars == 4) {
                multiplier = 1.25;
                capacity = 20;
            }
            if (stars == 5) {
                multiplier = 1.5;
                capacity = 25;
            }
            //------------------------------------
            if (wagonCount >= 0 && wagonCount < 3) {
                speed = 100;
            }
            if (wagonCount >= 3 && wagonCount < 6) {
                speed = 80;
            }
            if (wagonCount >= 6 && wagonCount < 10) {
                speed = 60;
            }
            if (wagonCount >= 10) {
                speed = 40;
            }
            //--------------------------------------
            price += wagonCount * (100*multiplier);

            railwayStation.getTrains().add(new Train(name, price, TrainEnt, capacity, speed, color, wagonCount, stars, Controller.enteredCity));

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Order");
            alert.setHeaderText("Thanks For Your Purchase");
            alert.setContentText("Train Ordered Successfully: " + price + "$");
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
        for(RailwayStation rs : Controller.enteredCity.railwayStations){
            list.add("Name: "  + rs.name + " Trains: " + rs.getTrains().size());
        }
        for (int i = 0; i < entertainments.length; i++) {
            ent.add(entertainments[i].toString());
        }
        entertain.getItems().addAll(ent);
        tRailway.getItems().addAll(list);
        tStars.getItems().addAll(s);
        tColors.getItems().addAll(c);
        entertain.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String e = entertain.getSelectionModel().getSelectedItem();
                if(!TrainEnt.contains(Entertainment.valueOf(e))) {
                    TrainEnt.add(Entertainment.valueOf(e));
                    EntertainmentLabel.setText(e + "  Added");
                }else{
                    EntertainmentLabel.setText(e + "  Already Added");
                }

            }
        });
    }
}
