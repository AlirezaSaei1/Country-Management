package Main;

import Main.Finance.BankAccount;
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
import java.util.ResourceBundle;

public class FMController implements Initializable {
    public static boolean onScreen = false;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ListView<String> payments;

    public void onMenu(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/FinancialManagement.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Financial Management");
        stage.show();
    }

    public void back(ActionEvent e) throws Exception {
        BankAccount.a.clear();
        onScreen = false;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/FirstMenu.fxml"));
        root = loader.load();
        Menu1Controller c1 = loader.getController();
        c1.firstMenu(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onScreen = true;
        BankAccount.listView = payments;
    }
}
