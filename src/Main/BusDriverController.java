package Main;

import Main.Building.BusTerminal;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BusDriverController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ChoiceBox<String> bList;
    @FXML
    ChoiceBox<String> tList;
    @FXML
    Label Error;
    ArrayList<String> bd = new ArrayList<>();
    ArrayList<String> ts = new ArrayList<>();
    public void showList(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/HireBusDriver.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hiring Page");
        stage.show();
    }

    public void confirmation(ActionEvent e) {
        BusTerminal busTerminal = null;
        Person person = null;
        try {
            String bDriver = bList.getValue();
            String bTerminal = tList.getValue();
            if(bDriver.equals("") || bTerminal.equals("")){
                throw new InvalidInput();
            }
            for(Person p : Controller.enteredCity.population){
                if(p.name.equals(bDriver.split(" ")[0]) && p.lastName.equals(bDriver.split(" ")[1])){
                    person = p;
                    break;
                }
            }
            for(BusTerminal t : Controller.enteredCity.busTerminals){
                if(t.name.equals(bTerminal.split(" ")[0]) && t.address.equals(bTerminal.split(" ")[1])){
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
            if(p.job.equals("Bus Driver") && !p.hired){
                bd.add(p.name + " " + p.lastName + " " + p.salary + "$");
            }
        }
        for(BusTerminal b : Controller.enteredCity.busTerminals){
            ts.add(b.name + " " + b.address);
        }
        bList.getItems().addAll(bd);
        tList.getItems().addAll(ts);
    }
}
