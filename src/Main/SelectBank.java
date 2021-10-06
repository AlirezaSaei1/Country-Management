package Main;

import Main.Finance.Bank;
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

public class SelectBank implements Initializable {
    public static Bank bank;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void selectionList(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/SelectBank.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bank Selection Menu");
        stage.show();
    }

    @FXML
    ChoiceBox<String> banks;
    ArrayList<String> list = new ArrayList<>();
    public void selection(ActionEvent e) throws Exception {
        String bkName = banks.getValue();
        for(Bank b : Controller.enteredCity.banks){
            if(b.bankName.equals(bkName.split(" ")[1])){
                bank = b;
                break;
            }
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BankMenu.fxml"));
        root = loader.load();
        BankController c1 = loader.getController();
        c1.menu(e);
    }

    public void back(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Bank b : Controller.enteredCity.banks){
            list.add("BankName: " + b.bankName);
        }
        banks.getItems().addAll(list);
    }
}
