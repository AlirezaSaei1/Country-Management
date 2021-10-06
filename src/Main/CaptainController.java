package Main;

import Main.Building.BusTerminal;
import Main.Building.Harbor;
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

public class CaptainController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ChoiceBox<String> cList;
    @FXML
    ChoiceBox<String> hList;
    @FXML
    Label Error;
    ArrayList<String> c = new ArrayList<>();
    ArrayList<String> h = new ArrayList<>();

    public void showList(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/HireCaptain.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hiring Page");
        stage.show();
    }

    public void confirmation(ActionEvent e) throws Exception{
        Harbor hbr = null;
        Person person = null;
        try {
            String cpt = cList.getValue();
            String harbor = hList.getValue();
            if(cpt.equals("") || harbor.equals("")){
                throw new InvalidInput();
            }
            for(Person p : Controller.enteredCity.population){
                if(p.name.equals(cpt.split(" ")[0]) && p.lastName.equals(cpt.split(" ")[1])){
                    person = p;
                    break;
                }
            }
            for(Harbor t : Controller.enteredCity.harbour){
                if(t.name.equals(harbor.split(" ")[0]) && t.address.equals(harbor.split(" ")[1])){
                    hbr = t;
                    break;
                }
            }
            if(hbr == null || person == null){
                throw new NullPointerException();
            }
            hbr.cpt.add(person);
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
            if(p.job.equals("Captain") && !p.hired){
                c.add(p.name + " " + p.lastName + " " + p.salary + "$");
            }
        }
        for(Harbor b : Controller.enteredCity.harbour){
            h.add(b.name + " " + b.address);
        }
        cList.getItems().addAll(c);
        hList.getItems().addAll(h);
    }
}
