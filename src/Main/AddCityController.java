package Main;

import Main.Exceptions.CityExists;
import Main.Exceptions.InvalidInput;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCityController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void AddCity(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/Menu1AddCity.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add City");
        stage.show();
    }

    public void back(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/FirstMenu.fxml"));
        root = loader.load();
        Menu1Controller c1 = loader.getController();
        c1.firstMenu(e);
    }

    @FXML
    TextField addCityTextField;
    @FXML
    Label addCityLabel;

    public void createCity(ActionEvent e){
        try {
            String cityName = addCityTextField.getText();
            if(cityName.equals("")){
                throw new InvalidInput();
            }
            for (City c : Country.country) {
                if (c.cityName.equals(cityName)) {
                    throw new CityExists();
                }
            }
            Country.addCity(new City(cityName, 10000));
            addCityLabel.setText("City Added Successfully!");
        }catch (Exception exception){
            addCityLabel.setText(exception.getMessage());
        }
    }

}
