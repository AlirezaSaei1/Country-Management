package Main;

import Main.Exceptions.BankExists;
import Main.Exceptions.InvalidInput;
import Main.Finance.Bank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class BuildBankController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void menu(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/BuildBank.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bank Construction");
        stage.show();
    }
    @FXML
    TextField bankName;
    @FXML
    Label ErrorLabel;
    public void construct(ActionEvent e){
        try {
            String bank = bankName.getText();
            if(bank.equals("")){
                throw new InvalidInput();
            }
            for(Bank b : Controller.enteredCity.banks){
                if(b.bankName.equals(bank)){
                    throw new BankExists();
                }
            }
            Controller.enteredCity.banks.add(new Bank(bank));
            Controller.enteredCity.balance -= 250;

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("Build Successful");
            alert.setContentText("Bank was built with 250$");
            alert.showAndWait();

            menu(e);
        }catch (Exception exception){
            ErrorLabel.setText(exception.getMessage());
        }

    }

    public void backToMenu(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
    }


}
