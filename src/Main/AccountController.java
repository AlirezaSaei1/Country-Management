package Main;


import Main.Finance.TRs;
import Main.Finance.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label welcomeLabel;

    public void list(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/InsideAccount.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(BankController.bankAccount.getUsername());
        stage.show();
    }

    public void deposit(ActionEvent e) {
        try {
            String amount = "0";
            long t1 = System.currentTimeMillis();
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Deposit");
            dialog.setHeaderText("Deposit");
            dialog.setContentText("Enter amount of money you want to deposit:");
            Optional<String> input = dialog.showAndWait();
            if (input.isPresent()) {
                amount = dialog.getEditor().getText();
                long t2 = System.currentTimeMillis();
                BankController.bankAccount.getAllTransactions().add(new Transaction(Double.parseDouble(amount), (t2 - t1), TRs.Deposit));
                BankController.bankAccount.setBalance(BankController.bankAccount.getBalance() + Double.parseDouble(amount));
                BankController.bankAccount.branch.setAllMoney(BankController.bankAccount.branch.getAllMoney() + Double.parseDouble(amount));
            }
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Deposit");
            a.setHeaderText("Deposit Successful");
            a.setContentText(Double.parseDouble(amount) + "$ Deposited successfully");
            a.showAndWait();
        }catch (Exception exception){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Deposit");
            a.setHeaderText("Deposit");
            a.setContentText("Enter numbers you dumb!");
            a.showAndWait();
        }
    }

    public void withdraw(ActionEvent e) {
        try {
            String amount = "0";
            long t1 = System.currentTimeMillis();
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Withdraw");
            dialog.setHeaderText("Withdraw");
            dialog.setContentText("Enter amount of money you want to withdraw:");
            Optional<String> input = dialog.showAndWait();
            if (input.isPresent()) {
                amount = dialog.getEditor().getText();
                if (Double.parseDouble(amount) >= BankController.bankAccount.getBalance()) {
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setTitle("Error");
                    a.setHeaderText("Withdrawal Unsuccessful");
                    a.setContentText("Withdrawal higher than account balance");
                    a.showAndWait();
                    list(e);
                } else {
                    long t2 = System.currentTimeMillis();
                    BankController.bankAccount.getAllTransactions().add(new Transaction(Double.parseDouble(amount), (t2 - t1), TRs.Withdraw));
                    BankController.bankAccount.setBalance(BankController.bankAccount.getBalance() - Double.parseDouble(amount));
                    BankController.bankAccount.branch.setAllMoney(BankController.bankAccount.branch.getAllMoney() - Double.parseDouble(amount));

                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setTitle("Withdraw");
                    a.setHeaderText("Withdrawal Successful");
                    a.setContentText(Double.parseDouble(amount) + "$ subtracted successfully");
                    a.showAndWait();
                }
            }
        }catch (Exception exception){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Withdraw");
            a.setHeaderText("Withdrawal");
            a.setContentText("Enter numbers you dumb!");
            a.showAndWait();
        }
    }

    public void allTransaction(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/Transactions.fxml"));
        root = loader.load();
        AllTransaction c1 = loader.getController();
        c1.showList(e);
    }

    public void logout(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BankMenu.fxml"));
        root = loader.load();
        BankController c1 = loader.getController();
        c1.menu(e);
        BankController.bankAccount = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeLabel.setText("Welcome : " + BankController.bankAccount.getClient().name + "!\nBalance: " + BankController.bankAccount.getBalance() + "$");
    }
}
