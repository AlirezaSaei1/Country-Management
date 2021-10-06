package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectCityController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ChoiceBox<String> cityChoiceBox;
    ArrayList<String> list = new ArrayList<>();

    public void enterCity(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/Menu1SelectCity.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("City Selection");
        stage.show();
    }

    public void getCity(ActionEvent e) throws Exception {
        String city = cityChoiceBox.getValue();
        for(City c : Country.country){
            if (c.cityName.equals(city.split(" ")[0])){
                Controller.enteredCity = c;
                break;
            }
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
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
         list.add(c.cityName + "   (" + c.balance + "$)");
        }
        cityChoiceBox.getItems().addAll(list);

    }
}
