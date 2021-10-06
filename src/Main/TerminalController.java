package Main;

import Main.Building.Airport;
import Main.Building.BusTerminal;
import Main.Building.Harbor;
import Main.Building.RailwayStation;
import Main.Exceptions.InvalidInput;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TerminalController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void buildMenu(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/BuildTerminal.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Terminal Construction");
        stage.show();
    }

    public void BuildRailwayStation(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/BuildRailwayStation.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Railway Station Construction");
        stage.show();
    }
    public void BuildAirport(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/BuildAirport.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Airport Construction");
        stage.show();
    }
    public void BuildHarbor(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/BuildHarbor.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Harbor Construction");
        stage.show();
    }
    public void BuildBusTerminal(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/BuildBusTerminal.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Terminal Construction");
        stage.show();
    }
    @FXML
    TextField aName;
    @FXML
    TextField aAddress;
    @FXML
    TextField aArea;
    @FXML
    TextField aRunway;
    @FXML
    Label errorA;
    public void AirportConfirm(ActionEvent e) throws Exception{
        if(Controller.enteredCity.balance <= 800){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Build Unsuccessful");
            alert.setContentText("Insufficient Money (800$ Needed)");
            alert.showAndWait();
            buildMenu(e);
        }
        boolean international = false;
        double totalPrice = 800;
        try{
            String name = aName.getText();
            String address =  aAddress.getText();
            String area = aArea.getText();
            int runway = Integer.parseInt(aRunway.getText());
            double Area = Double.parseDouble(area);

            if(name.equals("") || address.equals("")){
                throw new InvalidInput();
            }

            if (Area < 100) {
                Area = 1000;
                totalPrice += 100;
            }else if (Area >= 100 && Area < 2000) {
                totalPrice += 100;
            }else if (Area >= 2000 && Area < 3000) {
                totalPrice += 150;
            }else {
                totalPrice += 200;
            }
            totalPrice += (runway* 50);

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Construction");
            a.setHeaderText("International Airport");
            a.setContentText("Do you want to make the airport international?");
            if(a.showAndWait().get() == ButtonType.OK){
                international = true;
            }

            new Airport(name, address, totalPrice, Area, runway, international, Controller.enteredCity);

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("Build Successful");
            alert.setContentText("Airport was built with " + totalPrice + "$");
            alert.showAndWait();
            BuildAirport(e);

        }catch (Exception exception){
            errorA.setText(exception.getMessage());
        }

    }
    @FXML
    TextField hName;
    @FXML
    TextField hAddress;
    @FXML
    TextField hArea;
    @FXML
    TextField hCount;
    @FXML
    Label errorH;
    public void HarborConfirm(ActionEvent e) throws Exception{
        if(Controller.enteredCity.balance <= 400){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Build Unsuccessful");
            alert.setContentText("Insufficient Money (400$ Needed)");
            alert.showAndWait();
            buildMenu(e);
        }
        double totalPrice = 400;
        try{
            String name = hName.getText();
            String address = hAddress.getText();
            String area = hArea.getText();
            int count = Integer.parseInt(hCount.getText());
            if(name.equals("") || address.equals("")){
                throw new InvalidInput();
            }
            double Area = Double.parseDouble(area);
            if (Area < 100) {
                Area = 1000;
                totalPrice += 100;
            }else if (Area >= 100 && Area < 1500) {
                totalPrice += 100;
            }else if (Area >= 1500 && Area < 2500) {
                totalPrice += 150;
            }else {
                totalPrice += 200;
            }
            totalPrice += (count * 50);

            new Harbor(name, address, totalPrice, Area, count, Controller.enteredCity);

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("Build Successful");
            alert.setContentText("Harbor was built with " + totalPrice + "$");
            alert.showAndWait();

            BuildHarbor(e);

        }catch (Exception exception){
            errorH.setText(exception.getMessage());
        }
    }
    @FXML
    TextField bName;
    @FXML
    TextField bAddress;
    @FXML
    TextField bArea;
    @FXML
    TextField bCount;
    @FXML
    Label errorB;
    public void BusTerminalConfirm(ActionEvent e) throws Exception{
        if(Controller.enteredCity.balance <= 450){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Build Unsuccessful");
            alert.setContentText("Insufficient Money (450$ Needed)");
            alert.showAndWait();
            buildMenu(e);
        }
        double totalPrice = 450;
        try{
            String name = bName.getText();
            String address = bAddress.getText();
            String area = bArea.getText();
            int count = Integer.parseInt(bCount.getText());
            if(name.equals("") || address.equals("")){
                throw new InvalidInput();
            }
            double Area = Double.parseDouble(area);
            if (Area < 100) {
                Area = 1000;
                totalPrice += 100;
            }else if (Area >= 100 && Area < 1500) {
                totalPrice += 100;
            }else if (Area >= 1500 && Area < 2500) {
                totalPrice += 150;
            }else {
                totalPrice += 200;
            }
            new BusTerminal(name, address, totalPrice, Area, count, Controller.enteredCity);

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("Build Successful");
            alert.setContentText("Bus Terminal was built with " + totalPrice + "$");
            alert.showAndWait();

            BuildBusTerminal(e);

        }catch (Exception exception){
            errorB.setText(exception.getMessage());
        }
    }
    @FXML
    TextField rName;
    @FXML
    TextField rAddress;
    @FXML
    TextField rArea;
    @FXML
    TextField rArrival;
    @FXML
    TextField rDeparture;
    @FXML
    Label error;
    public void RailwayStationConfirm(ActionEvent e) throws Exception{
        if(Controller.enteredCity.balance <= 350){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Build Unsuccessful");
            alert.setContentText("Insufficient Money (350$ Needed)");
            alert.showAndWait();
            buildMenu(e);
        }
        double totalPrice = 350;
        try{
            String name = rName.getText();
            String address = rAddress.getText();
            String area = rArea.getText();
            String arrival = rArrival.getText();
            String departure = rDeparture.getText();
            if(name.equals("") || address.equals("")){
                throw new InvalidInput();
            }
            double Area = Double.parseDouble(area);
            if (Area < 100) {
                Area = 1000;
                totalPrice += 100;
            }else if (Area >= 100 && Area < 1500) {
                totalPrice += 100;
            }else if (Area >= 1500 && Area < 2500) {
                totalPrice += 150;
            }else {
                totalPrice += 200;
            }
            new RailwayStation(name, address, totalPrice, Area, Integer.parseInt(arrival), Integer.parseInt(departure), Controller.enteredCity);

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("Build Successful");
            alert.setContentText("Railway Station was built with " + totalPrice + "$");
            alert.showAndWait();

            BuildRailwayStation(e);

        }catch (Exception exception){
            error.setText(exception.getMessage());
        }
    }
    public void Return(ActionEvent e) throws Exception{
        buildMenu(e);
    }
    public void back(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
    }
}
