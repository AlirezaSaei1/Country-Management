package Main;

import Main.Building.Airport;
import Main.Building.BusTerminal;
import Main.Building.Terminal;
import Main.Exceptions.InvalidTravel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TravelsTerminalController implements Initializable {
    public static Terminal departureTerminal;
    public static Terminal arrivalTerminal;
    public static String type;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void tShow(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/TravelsTerminal.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Terminal Selection");
        stage.show();
    }
    @FXML
    ChoiceBox<String> aTerminal;
    @FXML
    ChoiceBox<String> dTerminal;
    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();

    public void confirmTerminal(ActionEvent e){
        try{
            String t1 = aTerminal.getValue();
            String t2 = dTerminal.getValue();

            if(!t1.split(" ")[3].equals(t2.split(" ")[3])){
                throw new InvalidTravel("Not Same Type Terminals");
            }
            if(t1.split(" ")[3].equals("Airport")){
                for(Airport a : TravelsCityController.departureCity.airports){
                    if(a.name.equals(t1.split(" ")[1])){
                        departureTerminal = a;
                    }
                }
                for(Airport a : TravelsCityController.arrivalCity.airports){
                    if(a.name.equals(t2.split(" ")[1])){
                        arrivalTerminal = a;
                    }
                }
                type = "Airport";
            }
            if(t1.split(" ")[3].equals("BusTerminal")){
                for(BusTerminal a : TravelsCityController.departureCity.busTerminals){
                    if(a.name.equals(t1.split(" ")[1])){
                        departureTerminal = a;
                    }
                }
                for(BusTerminal a : TravelsCityController.arrivalCity.busTerminals){
                    if(a.name.equals(t2.split(" ")[1])){
                        arrivalTerminal = a;
                    }
                }
                type = "BusTerminal";
            }
            if(departureTerminal == null || arrivalTerminal == null){
                throw new NullPointerException();
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/TravelsVehicle.fxml"));
            root = loader.load();
            TravelsVehicleController c1 = loader.getController();
            c1.vShow(e);

        }catch (Exception exception){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Error");
            a.setHeaderText("Cannot Create New Travel");
            a.setContentText(exception.getMessage());
            a.showAndWait();
        }
    }

    public void backOne(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/TravelsCity.fxml"));
        root = loader.load();
        TravelsCityController c1 = loader.getController();
        c1.selectCities(e);
    }

    public void cancelAll(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Airport a : TravelsCityController.departureCity.airports){
            list1.add("Name: " + a.name + " Type: Airport");
        }
        for(Airport a : TravelsCityController.arrivalCity.airports){
            list2.add("Name: " + a.name + " Type: Airport");
        }
        for(BusTerminal a : TravelsCityController.departureCity.busTerminals){
            list1.add("Name: " + a.name + " Type: BusTerminal");
        }
        for(BusTerminal a : TravelsCityController.arrivalCity.busTerminals){
            list2.add("Name: " + a.name + " Type: BusTerminal");
        }
        if(list1.size()==0){
            list1.add("Nothing...");
        }
        if(list2.size()==0){
            list2.add("Nothing...");
        }
        aTerminal.getItems().addAll(list1);
        dTerminal.getItems().addAll(list2);
    }
}
