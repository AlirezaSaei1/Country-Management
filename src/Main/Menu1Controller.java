package Main;

import Main.Building.*;
import Main.Travel.Travels;
import Main.Vehicle.AirVehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static Main.Controller.progressName;

public class Menu1Controller implements Initializable {
    ArrayList<String> l = new ArrayList<>();
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label allMoney;
    @FXML
    Label allPopulation;
    @FXML
    Label ProgressNameShow;
    public void firstMenu(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/FirstMenu.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allMoney.setText("Total Money : " + Country.calculateTotalMoney());
        allPopulation.setText("Total Population : " + Country.calculateTotalPopulation());
        ProgressNameShow.setText("Progress : " + progressName);
    }
    public void FManager(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/FinancialManagement.fxml"));
        root = loader.load();
        FMController c1 = loader.getController();
        c1.onMenu(e);
    }

    public void AllTravels(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/Menu1Travels.fxml"));
        root = loader.load();
        AllTravelsController c1 = loader.getController();
        c1.showTravels(e);
    }

    public void selectCity(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/Menu1SelectCity.fxml"));
        root = loader.load();
        SelectCityController c1 = loader.getController();
        c1.enterCity(e);
    }

    public void askSave(ActionEvent e) throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save And Exit");
        alert.setHeaderText("You Are About To Exit Program");
        alert.setContentText("Do You Wish To Save Your Progress?");
        if(alert.showAndWait().get() == ButtonType.OK){
            FileOutputStream file = new FileOutputStream(progressName + ".csv");
            PrintWriter csvWriter = new PrintWriter(file);
            for (City c : Country.country) {
                csvWriter.printf("%s,%f\n", c.cityName, c.balance);
                //-------------------------------------------------------------------------
                FileOutputStream file1 = new FileOutputStream(progressName + "," + c.cityName + ",population.csv");
                PrintWriter csvWriter1 = new PrintWriter(file1);
                for (Person p : c.population) {
                    csvWriter1.printf("%s,%s,%s,%s,%s,%s,%f,%B\n", p.name, p.lastName, p.dateOfBirth, p.birthLocation, p.job, p.gender, p.salary, p.hired);
                }
                csvWriter1.flush();
                file1.close();
                csvWriter1.close();
                //---------------------------------------------------------------------------
                FileOutputStream file2 = new FileOutputStream(progressName + "," + c.cityName + ",Hotels.csv");
                PrintWriter csvWriter2 = new PrintWriter(file2);
                for (Hotel p : c.hotels) {
                    csvWriter2.printf("%s,%f,%s,%d,%d\n", p.hotelName, p.price, p.address, p.stars, p.roomCount);
                    FileOutputStream file3 = new FileOutputStream(progressName + "," + c.cityName + ",Hotels," + p.hotelName + ",rooms.csv");
                    PrintWriter csvWriter3 = new PrintWriter(file3);
                    for (Room r : p.rooms) {
                        csvWriter3.printf("%d,%d\n", r.bedCount, r.roomCode);
                    }
                    csvWriter3.flush();
                    file3.close();
                    csvWriter3.close();
                    FileOutputStream file4 = new FileOutputStream(progressName + "," + c.cityName + ",Hotels," + p.hotelName + ",ent.csv");
                    PrintWriter csvWriter4 = new PrintWriter(file4);
                    for (Entertainment r : p.ent) {
                        csvWriter4.println(r.toString() + ",");
                    }
                    csvWriter4.flush();
                    file4.close();
                    csvWriter4.close();
                }
                csvWriter2.flush();
                file2.close();
                csvWriter2.close();
                //----------------------------------------------------------------------------
                FileOutputStream file5 = new FileOutputStream(progressName + "," + c.cityName + ",Airports.csv");
                PrintWriter csvWriter5 = new PrintWriter(file5);
                for (Airport p : c.airports) {
                    csvWriter5.printf("%s,%s,%f,%f,%d,%B\n", p.name, p.address, p.price, p.area, p.runwayCount, p.international);
                    FileOutputStream file6 = new FileOutputStream(progressName + "," + c.cityName + ",Airports," + p.name + ",planes.txt");
                    ObjectOutputStream csvWriter6 = new ObjectOutputStream(file6);
                    for (AirVehicle a : p.AllPlane) {
                        csvWriter6.writeObject(a);
                    }
                    file6.close();
                    csvWriter6.close();
                    FileOutputStream file7 = new FileOutputStream(progressName + "," + c.cityName + ",Airports," + p.name + ",pilots.txt");
                    ObjectOutputStream csvWriter7 = new ObjectOutputStream(file7);
                    for (Person p1 : p.pilots) {
                        csvWriter7.writeObject(p1);
                    }
                    file7.close();
                    csvWriter7.close();
                    FileOutputStream file0 = new FileOutputStream(progressName + "," + c.cityName + ",Airports," + p.name + ",Travels,in.txt");
                    ObjectOutputStream csvWriter0 = new ObjectOutputStream(file0);
                    for (Travels p1 : p.inTravel) {
                        csvWriter0.writeObject(p1);
                    }
                    file0.close();
                    csvWriter0.close();
                    FileOutputStream file00 = new FileOutputStream(progressName + "," + c.cityName + ",Airports," + p.name + ",Travels,out.txt");
                    ObjectOutputStream csvWriter00 = new ObjectOutputStream(file00);
                    for (Travels p1 : p.outTravel) {
                        csvWriter00.writeObject(p1);
                    }
                    file00.close();
                    csvWriter00.close();
                }
                csvWriter5.flush();
                file5.close();
                csvWriter5.close();
                //---------------------------------------------------------------------------------
                FileOutputStream file8 = new FileOutputStream(progressName + "," + c.cityName + ",Harbors.txt");
                ObjectOutputStream csvWriter8 = new ObjectOutputStream(file8);
                for (Harbor p : c.harbour) {
                    csvWriter8.writeObject(p);
                }
                file8.close();
                csvWriter8.close();
                //----------------------------------------------------------------------------------
                FileOutputStream file9 = new FileOutputStream(progressName + "," + c.cityName + ",RailwayStations.txt");
                ObjectOutputStream csvWriter9 = new ObjectOutputStream(file9);
                for (Object p : c.railwayStations) {
                    csvWriter9.writeObject(p);
                }
                file9.close();
                csvWriter9.close();
                //----------------------------------------------------------------------------------
                FileOutputStream file10 = new FileOutputStream(progressName + "," + c.cityName + ",BusTerminals.txt");
                ObjectOutputStream csvWriter10 = new ObjectOutputStream(file10);
                for (BusTerminal p : c.busTerminals) {
                    csvWriter10.writeObject(p);
                }
                file10.close();
                csvWriter10.close();
                //----------------------------------------------------------------------------------
            }
            csvWriter.flush();
            file.close();
            csvWriter.close();
        }
        System.exit(0);
    }

    public void InspectCity(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/Menu1AllCities.fxml"));
        root = loader.load();
        CityMenuController c1 = loader.getController();
        c1.InspectCity(e);
    }
    public void AddCity(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/Menu1AddCity.fxml"));
        root = loader.load();
        AddCityController c1 = loader.getController();
        c1.AddCity(e);
    }
}
