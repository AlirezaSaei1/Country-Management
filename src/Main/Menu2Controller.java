package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static Main.Controller.enteredCity;

public class Menu2Controller implements Initializable {
    City inCity = enteredCity;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label cityLabel;
    @FXML
    Label populationLabel;
    @FXML
    Label balanceLabel;

    public void secondMenu(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/SecondMenu.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(enteredCity.cityName);
        stage.show();
    }

    public void buildHotel(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BuildHotel.fxml"));
        root = loader.load();
        BuildHotelController c1 = loader.getController();
        c1.buildHotel(e);
    }

    public void showHotels(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/ShowHotels.fxml"));
        root = loader.load();
        HotelController c1 = loader.getController();
        c1.showHotels(e);
    }

    public void showPpl(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/ShowCityPopulation.fxml"));
        root = loader.load();
        ShowPopulationController c1 = loader.getController();
        c1.show(e);
    }
    public void addPPL(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/AddPerson.fxml"));
        root = loader.load();
        AddPersonController c1 = loader.getController();
        c1.AddP(e);
    }

    public void back(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/FirstMenu.fxml"));
        root = loader.load();
        Menu1Controller c1 = loader.getController();
        c1.firstMenu(e);
    }

    public void takeLoan(ActionEvent e) throws Exception{
        if(!Main.tookLoan) {
            enteredCity.balance += 2500;
            Main.tookLoan = true;
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Warning");
            a.setHeaderText("Loan Taken Successfully");
            a.setContentText("2500$ added to " + enteredCity.cityName + "'s balance!");
            a.showAndWait();
            secondMenu(e);
        }else{
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Warning");
            a.setHeaderText("Loan Token Already Used");
            a.setContentText("You can No more take loan");
            a.showAndWait();
        }
    }

    public void terminals(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/TerminalType.fxml"));
        root = loader.load();
        TerminalTypeController c1 = loader.getController();
        c1.typeSelect(e);
    }

    public void hireForJob(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/Hire.fxml"));
        root = loader.load();
        HiringController c1 = loader.getController();
        c1.Hire(e);
    }

    public void buildTerminal(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BuildTerminal.fxml"));
        root = loader.load();
        TerminalController c1 = loader.getController();
        c1.buildMenu(e);
    }

    public void orderVehicle(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BuyVehicle.fxml"));
        root = loader.load();
        VehicleController c1 = loader.getController();
        c1.buyMenu(e);
    }

    public void buildBank(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BuildBank.fxml"));
        root = loader.load();
        BuildBankController c1 = loader.getController();
        c1.menu(e);
    }

    public void enterBank(ActionEvent e) throws Exception{
        if(enteredCity.banks.size()==0){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Warning");
            a.setHeaderText("No Bank In City");
            a.setContentText("Click OK to continue");
            a.showAndWait();
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SelectBank.fxml"));
            root = loader.load();
            SelectBank c1 = loader.getController();
            c1.selectionList(e);
        }
    }

    public void newTravel(ActionEvent e) throws Exception{
        if(Country.country.size()<=1){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Error");
            a.setHeaderText("Cannot Create New Travel");
            a.setContentText("Insufficient number of cities");
            a.showAndWait();
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/TravelsCity.fxml"));
            root = loader.load();
            TravelsCityController c1 = loader.getController();
            c1.selectCities(e);
        }
    }

    public void showTravel(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/TravelsList.fxml"));
        root = loader.load();
        TravelListController c1 = loader.getController();
        c1.showT(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cityLabel.setText("City : " + inCity.cityName);
        populationLabel.setText("Population : " + inCity.population.size());
        balanceLabel.setText("Balance : " + inCity.balance);
    }
}
