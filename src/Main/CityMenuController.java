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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CityMenuController implements Initializable {
    public City selectedCity;
    private Stage stage;
    private Scene scene;
    private Parent root;
    String currentCity;

    @FXML
    ListView<String> cityList;
    @FXML
    Label cityInfoLabel;
    ArrayList<String> myList = new ArrayList<>();

    public void InspectCity(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/Menu1AllCities.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("All Cities");
        stage.show();
    }
    public void back(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/FirstMenu.fxml"));
        root = loader.load();
        Menu1Controller c1 = loader.getController();
        c1.firstMenu(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(City c : Country.country){
            myList.add(c.cityName);
        }
        cityList.getItems().addAll(myList);
        cityList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentCity = cityList.getSelectionModel().getSelectedItem();
                for(City c : Country.country){
                    if(currentCity.equals(c.cityName)){
                        selectedCity = c;
                        break;
                    }
                }
                cityInfoLabel.setText(String.format("Name: %10s     Population: %2d     Balance: %f", selectedCity.cityName, selectedCity.population.size(), selectedCity.balance));
            }
        });
    }
}
