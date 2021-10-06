package Main;

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

public class LocomotivesController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ChoiceBox<String> lList;
    @FXML
    ChoiceBox<String> rList;
    @FXML
    Label Error;
    ArrayList<String> lc = new ArrayList<>();
    ArrayList<String> rs = new ArrayList<>();

    public void showList(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/HireLocomotives.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hiring Page");
        stage.show();
    }

    public void confirmation(ActionEvent e) {
        RailwayStation railwayStation = null;
        Person person = null;
        try {
            String loco = lList.getValue();
            String railway = rList.getValue();
            if(loco.equals("") || railway.equals("")){
                throw new InvalidInput();
            }
            for(Person p : Controller.enteredCity.population){
                if(p.name.equals(loco.split(" ")[0]) && p.lastName.equals(loco.split(" ")[1])){
                    person = p;
                    break;
                }
            }
            for(RailwayStation t : Controller.enteredCity.railwayStations){
                if(t.name.equals(railway.split(" ")[0]) && t.address.equals(railway.split(" ")[1])){
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
            if(p.job.equals("Locomotives") && !p.hired){
                lc.add(p.name + " " + p.lastName + " " + p.salary + "$");
            }
        }
        for(RailwayStation b : Controller.enteredCity.railwayStations){
            rs.add(b.name + " " + b.address);
        }
        lList.getItems().addAll(lc);
        rList.getItems().addAll(rs);
    }
}
