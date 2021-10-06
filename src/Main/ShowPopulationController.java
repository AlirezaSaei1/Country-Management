package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowPopulationController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ListView<String> showPopulationListView;
    ArrayList<String> ppl = new ArrayList<>();

    public void show(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/ShowCityPopulation.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(Controller.enteredCity.cityName + "'s Population");
        stage.show();
    }

    public void back(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Person p : Controller.enteredCity.population){
            ppl.add(String.format("Name: %10s    LastName: %10s    Gender: %5s    Job: %5s    Salary: %f    Working: %b", p.name, p.lastName, p.gender, p.job, p.salary, p.hired));
        }
        showPopulationListView.getItems().addAll(ppl);
    }
}
