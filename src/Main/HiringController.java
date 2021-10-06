package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import static Main.Controller.enteredCity;

public class HiringController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void Hire(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/Hire.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hiring Page");
        stage.show();
    }

    public void HirePilot(ActionEvent e) throws Exception{
        int count = 0;
        for(Person p : enteredCity.population){
            if(p.job.equals("Pilot") && !p.hired){
                count++;
            }
        }
        if(count==0){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Alert!");
            a.setHeaderText("Hire Unsuccessful");
            a.setContentText("No Pilot Case For Hiring");
            a.showAndWait();
            Hire(e);
        }else{
            if(enteredCity.airports.size()==0) {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Alert!");
                a.setHeaderText("Hire Unsuccessful");
                a.setContentText("No Airports For Hiring Pilot");
                a.showAndWait();
                Hire(e);
            }else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/HirePilot.fxml"));
                root = loader.load();
                PilotController c1 = loader.getController();
                c1.showList(e);
            }
        }
    }
    public void HireStaff(ActionEvent e) throws Exception{
        int count = 0;
        for(Person p : enteredCity.population){
            if(p.job.equals("Staff") && !p.hired){
                count++;
            }
        }
        if(count==0){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Alert!");
            a.setHeaderText("Hire Unsuccessful");
            a.setContentText("No Staff Case For Hiring");
            a.showAndWait();
            Hire(e);
        }else{
            int x = (enteredCity.airports.size() + enteredCity.harbour.size() + enteredCity.railwayStations.size() + enteredCity.busTerminals.size());
            if(x==0) {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Alert!");
                a.setHeaderText("Hire Unsuccessful");
                a.setContentText("No Terminals For Hiring Staff");
                a.showAndWait();
                Hire(e);
            }else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/HireStaff.fxml"));
                root = loader.load();
                StaffController c1 = loader.getController();
                c1.showList(e);
            }
        }
    }
    public void HireCaptain(ActionEvent e) throws Exception{
        int count = 0;
        for(Person p : enteredCity.population){
            if(p.job.equals("Captain") && !p.hired){
                count++;
            }
        }
        if(count==0){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Alert!");
            a.setHeaderText("Hire Unsuccessful");
            a.setContentText("No Captain Case For Hiring");
            a.showAndWait();
            Hire(e);
        }else{
            if(enteredCity.harbour.size()==0) {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Alert!");
                a.setHeaderText("Hire Unsuccessful");
                a.setContentText("No Harbors For Hiring Captain");
                a.showAndWait();
                Hire(e);
            }else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/HireCaptain.fxml"));
                root = loader.load();
                CaptainController c1 = loader.getController();
                c1.showList(e);
            }
        }
    }
    public void HireLocomotives(ActionEvent e) throws Exception{
        int count = 0;
        for(Person p : enteredCity.population){
            if(p.job.equals("Locomotives") && !p.hired){
                count++;
            }
        }
        if(count==0){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Alert!");
            a.setHeaderText("Hire Unsuccessful");
            a.setContentText("No Locomotives Case For Hiring");
            a.showAndWait();
            Hire(e);
        }else{
            if(enteredCity.railwayStations.size()==0) {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Alert!");
                a.setHeaderText("Hire Unsuccessful");
                a.setContentText("No Railways Stations For Hiring Locomotives");
                a.showAndWait();
                Hire(e);
            }else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/HireLocomotives.fxml"));
                root = loader.load();
                LocomotivesController c1 = loader.getController();
                c1.showList(e);
            }
        }
    }
    public void HireBusDriver(ActionEvent e) throws Exception{
        int count = 0;
        for(Person p : enteredCity.population){
            if(p.job.equals("Bus Driver") && !p.hired){
                count++;
            }
        }
        if(count==0){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Alert!");
            a.setHeaderText("Hire Unsuccessful");
            a.setContentText("No Bus Driver Case For Hiring");
            a.showAndWait();
            Hire(e);
        }else{
            if(enteredCity.busTerminals.size()==0) {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Alert!");
                a.setHeaderText("Hire Unsuccessful");
                a.setContentText("No Bus Terminals For Hiring Bus Driver");
                a.showAndWait();
                Hire(e);
            }else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/HireBusDriver.fxml"));
                root = loader.load();
                BusDriverController c1 = loader.getController();
                c1.showList(e);
            }
        }
    }

    public void back(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
    }
}
