package Main;

import Main.Building.Airport;
import Main.Building.BusTerminal;
import Main.Vehicle.AirVehicle;
import Main.Vehicle.Bus;
import Main.Vehicle.PassengerPlane;
import Main.Vehicle.Vehicle;
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

public class TravelsVehicleController implements Initializable {
    public static Person personT;
    public static Vehicle vehicleT;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void vShow(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/TravelsVehicle.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Vehicle Selection");
        stage.show();
    }
    @FXML
    ChoiceBox<String> vehicleChoiceBox;
    @FXML
    ChoiceBox<String> driverChoiceBox;
    ArrayList<String> v = new ArrayList<>();
    ArrayList<String> d = new ArrayList<>();

    public void confirmVehicle(ActionEvent e){
        try{
            String vehicleName = vehicleChoiceBox.getValue();
            String driverName = driverChoiceBox.getValue();

            if(TravelsTerminalController.type.equals("Airport")){
                for(Person person : ((Airport) TravelsTerminalController.departureTerminal).pilots){
                    if(person.name.equals(driverName.split(" ")[0]) && person.lastName.equals(driverName.split(" ")[1])){
                        personT = person;
                        break;
                    }
                }
                for(AirVehicle plane : ((Airport) TravelsTerminalController.departureTerminal).AllPlane){
                    if(plane.companyName.equals(vehicleName.split(" ")[0])){
                        vehicleT = plane;
                        break;
                    }
                }
            }else{
                for(Person person : ((BusTerminal) TravelsTerminalController.departureTerminal).drivers){
                    if(person.name.equals(driverName.split(" ")[0]) && person.lastName.equals(driverName.split(" ")[1])){
                        personT = person;
                        break;
                    }
                }
                for(Bus bus : ((BusTerminal) TravelsTerminalController.departureTerminal).buses){
                    if(bus.companyName.equals(vehicleName.split(" ")[0])){
                        vehicleT = bus;
                        break;
                    }
                }
            }

            if(personT==null && vehicleT==null){
                throw new NullPointerException();
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/TravelsPassenger.fxml"));
            root = loader.load();
            TravelsPassengerController c1 = loader.getController();
            c1.pShow(e);

        }catch (Exception exception){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Error");
            a.setHeaderText("Cannot Create New Travel");
            a.setContentText(exception.getMessage());
            a.showAndWait();
        }
    }

    public void backOne(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/TravelsTerminal.fxml"));
        root = loader.load();
        TravelsTerminalController c1 = loader.getController();
        c1.tShow(e);
    }

    public void cancelAll(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(TravelsTerminalController.departureTerminal instanceof Airport){
            for(Person p : ((Airport) TravelsTerminalController.departureTerminal).pilots){
                d.add(p.name + " " + p.lastName);
            }
        }else{
            for(Person p : ((BusTerminal) TravelsTerminalController.departureTerminal).drivers){
                d.add(p.name + " " + p.lastName);
            }
        }
        if(TravelsTerminalController.departureTerminal instanceof Airport){
            for(Vehicle p : ((Airport) TravelsTerminalController.departureTerminal).AllPlane){
                if(p instanceof PassengerPlane) {
                    v.add(p.companyName + " SeatRate: " + ((PassengerPlane) p).seatRating);
                }
            }
        }else{
            for(Bus p : ((BusTerminal) TravelsTerminalController.departureTerminal).buses){
                v.add(p.companyName + " SeatRate: " + p.seatRate);
            }
        }
        if(d.size()==0){
            d.add("No One...");
        }
        if(v.size()==0){
            v.add("Nothing...");
        }
        vehicleChoiceBox.getItems().addAll(v);
        driverChoiceBox.getItems().addAll(d);
    }
}
