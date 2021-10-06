package Main;

import Main.Exceptions.InvalidInput;
import Main.Exceptions.UserNotFound;
import Main.Finance.BankAccount;
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

public class BankController {
    public static BankAccount bankAccount;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void menu(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/BankMenu.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bank Menu");
        stage.show();
    }

    public void bankInfo(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Bank Information");
        alert.setHeaderText("Bank Name : " + SelectBank.bank.bankName + "\nNumber Of Active Accounts : " + SelectBank.bank.getActiveAccounts() + "\nBank Cash : " + SelectBank.bank.getAllMoney());
        alert.setContentText("Click OK to Continue");
        alert.showAndWait();
    }
    @FXML
    Label loginLabel;
    @FXML
    TextField userLogin;
    @FXML
    TextField passLogin;
    public void login(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/BankLogin.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
    public void enterAccount(ActionEvent e) {
        try{
            String User = userLogin.getText();
            String Pass = passLogin.getText();
            for(BankAccount ba : SelectBank.bank.getAccounts()){
                if(ba.getUsername().equals(User) && ba.getPassword().equals(Pass)){
                    bankAccount = ba;
                    break;
                }
            }
            if(bankAccount == null){
                throw new UserNotFound();
            }else {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/InsideAccount.fxml"));
                root = loader.load();
                AccountController c1 = loader.getController();
                c1.list(e);
            }

        }catch (Exception exception){
            loginLabel.setText(exception.getMessage());
        }
    }
    @FXML
    Label signupLabel;
    @FXML
    TextField user;
    @FXML
    TextField pass;
    @FXML
    TextField name;
    public void signUp(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/BankSignup.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sign Up");
        stage.show();
    }

    public void create(ActionEvent e) {
        boolean personFound = false;
        Person person = null;
        try {
            String Name = name.getText();
            String username = user.getText();
            String password = pass.getText();

            if(Name.equals("") || username.equals("") || password.equals("")){
                throw new InvalidInput();
            }

            for(Person p : Controller.enteredCity.population){
                if(p.name.equals(Name)){
                    person = p;
                    personFound = true;
                    break;
                }
            }
            if(!personFound){
                throw new InvalidInput();
            }

            BankAccount ba = new BankAccount(person, username, password, 500, SelectBank.bank);
            SelectBank.bank.getAccounts().add(ba);
            /*Thread t = new Thread(ba);
            t.start();*/


            SelectBank.bank.setActiveAccounts(SelectBank.bank.getActiveAccounts() + 1);
            SelectBank.bank.setAllMoney(SelectBank.bank.getAllMoney() + 500);

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sign Up");
            alert.setHeaderText("Account Created Successfully with 500$ initial balance");
            alert.setContentText("Click OK to Continue");
            alert.showAndWait();
            menu(e);
        }catch (Exception exception){
            signupLabel.setText(exception.getMessage());
        }
    }
    public void cancel(ActionEvent e) throws Exception{
        menu(e);
    }

    public void backToMenu(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SelectBank.fxml"));
        root = loader.load();
        SelectBank c1 = loader.getController();
        c1.selectionList(e);
    }
}
