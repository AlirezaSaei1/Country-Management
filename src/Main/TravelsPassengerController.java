package Main;

import Main.Exceptions.CancelTravel;
import Main.Exceptions.InvalidDate;
import Main.Travel.Travels;
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

public class TravelsPassengerController implements Initializable {
    public static ArrayList<Person> passengers = new ArrayList<>();
    public String dateT;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void pShow(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/TravelsPassenger.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Passengers And Date");
        stage.show();
    }
    @FXML
    Label pLabel;
    @FXML
    ChoiceBox<String> pChoiceBox;
    @FXML
    TextField yy;
    @FXML
    TextField mm;
    @FXML
    TextField dd;

    ArrayList<String> ps = new ArrayList<>();

    public void confirmPassengers(ActionEvent e){
        try{
            String y = yy.getText();
            String m = mm.getText();
            String d = dd.getText();
            if(Integer.parseInt(m)>12 || Integer.parseInt(d)>31){
                throw new InvalidDate();
            }
            dateT = y  + "/" + m + "/" + d;

            if(passengers.size()==0){
                throw new CancelTravel("No Passengers Added");
            }

            new Travels(TravelsCityController.departureCity, TravelsCityController.arrivalCity, TravelsTerminalController.departureTerminal, TravelsTerminalController.arrivalTerminal, dateT, passengers, TravelsVehicleController.personT, TravelsVehicleController.vehicleT, TravelsTerminalController.departureTerminal.calculateCost(passengers, TravelsVehicleController.vehicleT));

            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Congratulations!");
            a.setHeaderText("Created New Travel");
            a.setContentText("New Travel Created And Cost was : " + TravelsTerminalController.departureTerminal.calculateCost(passengers, TravelsVehicleController.vehicleT) + '$');
            a.showAndWait();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
            root = loader.load();
            Menu2Controller c1 = loader.getController();
            c1.secondMenu(e);

        }catch (Exception exception){
            exception.printStackTrace();
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Error");
            a.setHeaderText("Cannot Create New Travel");
            a.setContentText(exception.getMessage());
            a.showAndWait();
        }

    }

    public void backOne(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/TravelsVehicle.fxml"));
        root = loader.load();
        TravelsVehicleController c1 = loader.getController();
        c1.vShow(e);
    }

    public void cancelAll(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Person p : TravelsCityController.departureCity.population){
            if(!p.hired){
                ps.add(p.name + " " + p.lastName);
            }
        }
        pChoiceBox.getItems().addAll(ps);

        pChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String e = pChoiceBox.getSelectionModel().getSelectedItem();
                for(Person p : TravelsCityController.departureCity.population){
                    if(p.name.equals(e.split(" ")[0]) && p.lastName.equals(e.split(" ")[1])){
                        if(passengers.contains(p)){
                            pLabel.setText(e + " Already added to queue");
                        }else{
                            passengers.add(p);
                            pLabel.setText(e + " Added to queue");

                        }
                    }
                }
            }
        });
    }
}
