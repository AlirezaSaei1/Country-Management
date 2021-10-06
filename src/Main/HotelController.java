package Main;

import Main.Building.Hotel;
import Main.Building.Room;
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

public class HotelController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;



    public void buildHotel(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/BuildHotel.fxml"));
        root = loader.load();
        BuildHotelController c1 = loader.getController();
        c1.buildHotel(e);
    }

    @FXML
    ListView<String> hotelListView;
    ArrayList<String> hotels = new ArrayList<>();
    public void showHotels(ActionEvent e) throws Exception{
        root = FXMLLoader.load(getClass().getResource("JFXs/ShowHotels.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hotels");
        stage.show();
    }

    public void back(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/SecondMenu.fxml"));
        root = loader.load();
        Menu2Controller c1 = loader.getController();
        c1.secondMenu(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Hotel h : Controller.enteredCity.hotels){
            StringBuilder rooms = new StringBuilder();
            for(Room r : h.rooms){
                rooms.append("[Room : " + r.roomCode + "     Beds : " + r.bedCount + "]");
            }
            hotels.add(String.format("Name: %5s     Address: %5s     Stars: %d     RoomCount: %d\n      Entertainments : %s\n      Rooms: %s", h.hotelName, h.address, h.stars, h.roomCount, h.ent.toString(), rooms));
        }
        hotelListView.getItems().addAll(hotels);
    }
}
