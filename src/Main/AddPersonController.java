package Main;

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
import java.util.ResourceBundle;

import static Main.Controller.enteredCity;

public class AddPersonController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    String[] jobs = {"Pilot", "Captain", "Bus Driver", "Staff", "Locomotives", "Jobless"};
    String[] gender = {"Male", "Female", "Rather Not Say"};

    String name;
    String lastName;
    String salary;
    String Gender;
    String birthDate;
    String Job;
    String birthLocation = enteredCity.cityName;

    @FXML
    Label ELabel;
    @FXML
    TextField pName;
    @FXML
    TextField pLastName;
    @FXML
    TextField yyyy;
    @FXML
    TextField mm;
    @FXML
    TextField dd;
    @FXML
    TextField pSalary;
    @FXML
    ChoiceBox<String> pGender;
    @FXML
    ChoiceBox<String> pJob;
    @FXML
    Label g;
    @FXML
    Label j;

    public void AddP(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/AddPerson.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add New Person");
        stage.show();
    }

    public void confirm(ActionEvent e) throws Exception{
        try{
            name = pName.getText();
            lastName = pLastName.getText();
            salary = pSalary.getText();
            birthDate = (yyyy.getText() + "/" + mm.getText() + "/" + dd.getText());
            new Person(name, lastName, birthDate, birthLocation, Job, Gender, Double.parseDouble(salary), false, enteredCity);
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("New Person");
            a.setHeaderText("Person Added!");
            a.setContentText("Person successfully added to city");
            a.showAndWait();
            AddP(e);
        }catch (Exception exception){
            ELabel.setText(exception.getMessage());
        }
    }

    public void back(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pGender.getItems().addAll(gender);
        pJob.getItems().addAll(jobs);

        pGender.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                Gender = pGender.getSelectionModel().getSelectedItem();
                g.setText("Gender : " + Gender);
            }
        });
        pJob.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                Job = pJob.getSelectionModel().getSelectedItem();
                j.setText("Job Selected : " + Job);
            }
        });
    }
}
