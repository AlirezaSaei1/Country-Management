package Main;

import Main.Building.Entertainment;
import Main.Building.Hotel;
import Main.Building.Room;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class BuildHotelController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ChoiceBox<String> entertain;
    @FXML
    TextField hName;
    @FXML
    TextField hAddress;
    @FXML
    TextField hStars;
    @FXML
    TextField hRoomCount;
    @FXML
    Label ErrorLabel;
    @FXML
    Label EntertainmentLabel;
    Entertainment[] entertainments = Entertainment.values();
    ArrayList<String> ent = new ArrayList<>();
    ArrayList<Entertainment> hotelEnt = new ArrayList<>();

    public void buildHotel(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/BuildHotel.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hotel Construction");
        stage.show();
    }

    public void build(ActionEvent e) throws Exception {
        try {
            ArrayList<Room> rooms = new ArrayList<>();
            String name = hName.getText();
            String address = hAddress.getText();
            int stars = Integer.parseInt(hStars.getText());
            int roomCount = Integer.parseInt(hRoomCount.getText());
            double p = 700.0 + (roomCount * 100);
            p += (stars * 100);
            for (int i=0; i<roomCount; i++){
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Bed Count");
                dialog.setHeaderText("Bed Count for room :" + (i+1) + "\nWill Be Set 1 If Skipped");
                dialog.setContentText("Enter Number Of Beds: ");
                Optional<String> input = dialog.showAndWait();
                if (input.isPresent()){
                    String rCount = dialog.getEditor().getText();
                    if(rCount.equals("")){
                        rooms.add(new Room(1));
                    }else {
                        rooms.add(new Room(Integer.parseInt(rCount)));
                    }
                }else{
                    rooms.add(new Room(1));
                }
            }
            Controller.enteredCity.addHotel(new Hotel(name, p, address, stars, roomCount, rooms, hotelEnt, Controller.enteredCity), Controller.enteredCity);
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Result");
            a.setHeaderText("Congratulations");
            a.setContentText("Hotel Creation was Successful");
            a.showAndWait();
            back(e);
        } catch (Exception exception) {
            ErrorLabel.setText(exception.getMessage());
        }
    }

    public void back(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < entertainments.length; i++) {
            ent.add(entertainments[i].toString());
        }
        entertain.getItems().addAll(ent);
        entertain.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String e = entertain.getSelectionModel().getSelectedItem();
                if(!hotelEnt.contains(Entertainment.valueOf(e))) {
                    hotelEnt.add(Entertainment.valueOf(e));
                    EntertainmentLabel.setText(e + "  Added");
                }else{
                    EntertainmentLabel.setText(e + "  Already Added");
                }

            }
        });

    }
}
