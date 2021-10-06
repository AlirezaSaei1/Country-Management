package Main;

import Main.Finance.Transaction;
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

public class AllTransaction implements Initializable {
    public static boolean tr = false;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    ListView<String> trs;
    ArrayList<String> list = new ArrayList<>();
    public void showList(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/Transactions.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Transactions");
        stage.show();
    }

    public void done(ActionEvent e) throws Exception{
        tr = false;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/InsideAccount.fxml"));
        root = loader.load();
        AccountController c1 = loader.getController();
        c1.list(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tr = true;
        for(Transaction tr : BankController.bankAccount.getAllTransactions()){
            list.add("Amount: " + tr.getAmount() + "   Type: " + tr.getType().toString() + "   Duration: " + tr.getDuration());
        }
        trs.getItems().addAll(list);
        BankController.bankAccount.transaction = trs;
    }
}
