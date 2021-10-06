package Main;

import Main.Exceptions.SameCity;
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

public class TravelsCityController implements Initializable {
    public static City departureCity;
    public static City arrivalCity;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void selectCities(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/TravelsCity.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("City Selection");
        stage.show();
    }
    @FXML
    ChoiceBox<String> aCity;
    @FXML
    ChoiceBox<String> dCity;
    ArrayList<String> list = new ArrayList<>();
    public void citiesSelected(ActionEvent e){
        try{
            String city1 = aCity.getValue();
            String city2 = dCity.getValue();

            if(city1.equals(city2)){
                throw new SameCity();
            }

            for(City x : Country.country){
                if(city1.equals(x.cityName)){
                    departureCity = x;
                }
                if(city2.equals(x.cityName)){
                    arrivalCity = x;
                }
            }

            if(departureCity == null || arrivalCity == null){
                throw new NullPointerException();
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/TravelsTerminal.fxml"));
            root = loader.load();
            TravelsTerminalController c1 = loader.getController();
            c1.tShow(e);

        }catch (Exception exception){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Error");
            a.setHeaderText("Cannot Create New Travel");
            a.setContentText(exception.getMessage());
            a.showAndWait();
        }
    }

    public void cancelTravel(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(City c : Country.country){
            list.add(c.cityName);
        }
        aCity.getItems().addAll(list);
        dCity.getItems().addAll(list);
    }
}
