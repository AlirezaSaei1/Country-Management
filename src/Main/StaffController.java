package Main;

import Main.Building.Airport;
import Main.Building.BusTerminal;
import Main.Building.Harbor;
import Main.Building.RailwayStation;
import Main.Exceptions.InvalidInput;
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
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StaffController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ChoiceBox<String> sList;
    @FXML
    ChoiceBox<String> tList;
    @FXML
    Label Error;
    ArrayList<String> allStaff = new ArrayList<>();
    ArrayList<String> allTerminals = new ArrayList<>();

    public void showList(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/HireStaff.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hiring Page");
        stage.show();
    }

    public void confirmation(ActionEvent e){
        RailwayStation railwayStation = null;
        Airport airport = null;
        BusTerminal busTerminal = null;
        Harbor harbor = null;
        Person person = null;
        try {
            String staff = sList.getValue();
            String terminals = tList.getValue();
            if(staff.equals("") || terminals.equals("")){
                throw new InvalidInput();
            }
            //------------------------------------------------------------------------------------------------
            for(Person p : Controller.enteredCity.population){
                if(p.name.equals(staff.split(" ")[0]) && p.lastName.equals(staff.split(" ")[1])){
                    person = p;
                    break;
                }
            }
            //------------------------------------------------------------------------------------------------
            if(terminals.split("---")[0].equals("RailwayStation")){
                for(RailwayStation t : Controller.enteredCity.railwayStations){
                    if(t.name.equals(terminals.split(" ")[1]) && t.address.equals(terminals.split(" ")[2])){
                        railwayStation = t;
                        break;
                    }
                }
                if(railwayStation == null || person == null){
                    throw new NullPointerException();
                }
                railwayStation.locomotives.add(person);
                person.hired = true;
                Controller.enteredCity.balance -= person.getSalary();
            }
            //------------------------------------------------------------------------------------------------
            if(terminals.split("---")[0].equals("BusTerminal")){
                for(BusTerminal t : Controller.enteredCity.busTerminals){
                    if(t.name.equals(terminals.split(" ")[1]) && t.address.equals(terminals.split(" ")[2])){
                        busTerminal = t;
                        break;
                    }
                }
                if(busTerminal == null || person == null){
                    throw new NullPointerException();
                }
                busTerminal.drivers.add(person);
                person.hired = true;
                Controller.enteredCity.balance -= person.getSalary();
            }
            //------------------------------------------------------------------------------------------------
            if(terminals.split("---")[0].equals("Airport")){
                for(Airport t : Controller.enteredCity.airports){
                    if(t.name.equals(terminals.split(" ")[1]) && t.address.equals(terminals.split(" ")[2])){
                        airport = t;
                        break;
                    }
                }
                if(airport == null || person == null){
                    throw new NullPointerException();
                }
                airport.pilots.add(person);
                person.hired = true;
                Controller.enteredCity.balance -= person.getSalary();
            }
            //------------------------------------------------------------------------------------------------
            if(terminals.split("---")[0].equals("Harbor")){
                for(Harbor t : Controller.enteredCity.harbour){
                    if(t.name.equals(terminals.split(" ")[1]) && t.address.equals(terminals.split(" ")[2])){
                        harbor = t;
                        break;
                    }
                }
                if(harbor == null || person == null){
                    throw new NullPointerException();
                }
                harbor.cpt.add(person);
                person.hired = true;
                Controller.enteredCity.balance -= person.getSalary();
            }
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Note");
            a.setHeaderText("Hire");
            a.setContentText("Hiring was successful");
            a.showAndWait();
            back(e);
        }catch (Exception exception){
            Error.setText(exception.getMessage());
        }
    }

    public void back(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/Hire.fxml"));
        root = loader.load();
        HiringController c1 = loader.getController();
        c1.Hire(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Person p : Controller.enteredCity.population){
            if(p.job.equals("Staff") && !p.hired){
                allStaff.add(p.name + " " + p.lastName + " " + p.salary + "$");
            }
        }
        for(RailwayStation b : Controller.enteredCity.railwayStations){
            allTerminals.add("RailwayStation--- " + b.name + " " + b.address);
        }
        for(BusTerminal b : Controller.enteredCity.busTerminals){
            allTerminals.add("BusTerminal--- " + b.name + " " + b.address);
        }
        for(Airport b : Controller.enteredCity.airports){
            allTerminals.add("Airport--- " + b.name + " " + b.address);
        }
        for(Harbor b : Controller.enteredCity.harbour){
            allTerminals.add("Harbor--- " + b.name + " " + b.address);
        }
        sList.getItems().addAll(allStaff);
        tList.getItems().addAll(allTerminals);
    }
}
