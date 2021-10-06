package Main;

import Main.Building.Airport;
import Main.Building.BusTerminal;
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

public class PilotController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ChoiceBox<String> pList;
    @FXML
    ChoiceBox<String> aList;
    @FXML
    Label Error;
    ArrayList<String> ps = new ArrayList<>();
    ArrayList<String> as = new ArrayList<>();
    public void showList(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/HirePilot.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hiring Page");
        stage.show();
    }

    public void confirmation(ActionEvent e){
        Airport airport = null;
        Person person = null;
        try {
            String pilot = pList.getValue();
            String air = aList.getValue();
            if(pilot.equals("") || air.equals("")){
                throw new InvalidInput();
            }
            for(Person p : Controller.enteredCity.population){
                if(p.name.equals(pilot.split(" ")[0]) && p.lastName.equals(pilot.split(" ")[1])){
                    person = p;
                    break;
                }
            }
            for(Airport t : Controller.enteredCity.airports){
                if(t.name.equals(air.split(" ")[0]) && t.address.equals(air.split(" ")[1])){
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
            if(p.job.equals("Pilot") && !p.hired){
                ps.add(p.name + " " + p.lastName + " " + p.salary + "$");
            }
        }
        for(Airport b : Controller.enteredCity.airports){
            as.add(b.name + " " + b.address);
        }
        pList.getItems().addAll(ps);
        aList.getItems().addAll(as);
    }
}
