package Main;

import Main.Building.*;
import Main.Exceptions.InvalidInput;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static String progressName = "Anonymous";
    public static City enteredCity;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void enter(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/EarlyMenu.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Progress Menu");
        stage.show();
    }

    public void exit(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EXIT");
        alert.setHeaderText("You Are About To Exit Program");
        alert.setContentText("Exit?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    public void newProgress(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/NewP.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("New Progress");
        stage.show();
    }

    public void loadProgress(ActionEvent e) throws Exception {
        root = FXMLLoader.load(getClass().getResource("JFXs/LoadP.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Load Progress");
        stage.show();
    }

    @FXML
    TextField textField;
    @FXML
    Label myLabel;
    public String progress;

    public void submitN(ActionEvent e) {
        try {
            progress = textField.getText();
            if (progress.equals("")) {
                throw new InvalidInput();
            }
            File f = new File(progress + ".csv");
            if (f.isFile()) {
                myLabel.setText("ERROR: Progress Exists");
            } else {
                progressName = progress;
                Country.addCity(new City("Vancouver", 10000));
                new Person("Samantha", "Maxis", "1984/01/01", "Isfahan", "Pilot", "Female", 30, false, Country.country.get(0));
                new Person("Alex", "Masons", "1984/06/03", "Isfahan", "Pilot", "Male", 26, false, Country.country.get(0));
                new Person("Helen", "Park", "1983/12/28", "Isfahan", "Bus Driver", "Female", 20, false, Country.country.get(0));
                new Person("Frank", "Woods", "1980/02/11", "Isfahan", "Bus Driver", "Male", 35, false, Country.country.get(0));
                new Person("Russel", "Adler", "1982/10/10", "Isfahan", "Jobless", "Male", 0, false, Country.country.get(0));
                new Person("Arthur", "Morgan", "1980/02/03", "Isfahan", "Staff", "Male", 10, false, Country.country.get(0));
                myLabel.setText("New Progress Successfully Created");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/FirstMenu.fxml"));
                root = loader.load();
                Menu1Controller c1 = loader.getController();
                c1.firstMenu(e);
            }
        } catch (Exception ex) {
            myLabel.setText(ex.getMessage());
        }
    }

    @FXML
    TextField loadTextField;
    @FXML
    Label loadLabel;

    public void submitE(ActionEvent e) {
        try {
            progress = loadTextField.getText();
            File f = new File(progress + ".csv");
            if (!f.isFile()) {
                loadLabel.setText("ERROR: Progress Not Found");
            } else {
                progressName = progress;
                //load
                try {
                    String row1;
                    BufferedReader br1 = new BufferedReader(new FileReader(progress + ".csv"));
                    while ((row1 = br1.readLine()) != null) {
                        String[] cities = row1.split(",");
                        City c = new City(cities[0], Double.parseDouble(cities[1]));
                        Country.country.add(c);
                        BufferedReader br = new BufferedReader(new FileReader(progress + "," + c.cityName + ",population.csv"));
                        String row;
                        while ((row = br.readLine()) != null) {
                            String[] info = row.split(",");
                            new Person(info[0], info[1], info[2], info[3], info[4], info[5], Double.parseDouble(info[6]), Boolean.parseBoolean(info[7]), c);
                        }
                        br.close();
                        //---------------------------------------------------------------------------------------------
                        BufferedReader br2 = new BufferedReader(new FileReader(progress + "," + c.cityName + ",Hotels.csv"));
                        String str2;
                        while ((str2 = br2.readLine()) != null) {
                            String[] info = str2.split(",");
                            String str3;
                            BufferedReader br3 = new BufferedReader(new FileReader(progress + "," + c.cityName + ",Hotels," + info[0] + ",rooms.csv"));
                            ArrayList<Room> room = new ArrayList<>();
                            while ((str3 = br3.readLine()) != null) {
                                String[] id = str3.split(",");
                                room.add(new Room(Integer.parseInt(id[0]), Integer.parseInt(id[1])));
                            }
                            br3.close();
                            String str4;
                            BufferedReader br4 = new BufferedReader(new FileReader(progressName + "," + c.cityName + ",Hotels," + info[0] + ",ent.csv"));
                            ArrayList<Entertainment> entertainments = new ArrayList<>();
                            //Get Array
                            Entertainment[] t = Entertainment.values();
                            String[] e1 = new String[t.length];
                            for (int x2 = 0; x2 < t.length; x2++) {
                                e1[x2] = t[x2].toString();
                            }
                            while ((str4 = br4.readLine()) != null) {
                                String[] id = str4.split(",");
                                for (int i = 0; i < id.length; i++) {
                                    for (int x3 = 0; x3 < e1.length; x3++) {
                                        if (e1[x3].equals(id[i])) {
                                            Entertainment enter = t[x3];
                                            entertainments.add(enter);
                                        }
                                    }
                                }
                            }
                            br4.close();
                            Hotel newH = new Hotel(info[0], Double.parseDouble(info[1]), info[2], Integer.parseInt(info[3]), Integer.parseInt(info[4]), room, entertainments, c);
                            c.addHotel(newH, c);
                            c.balance += newH.price;
                        }
                        br2.close();
                        //---------------------------------------------------------------------------------------------
                        String str5;
                        BufferedReader br5 = new BufferedReader(new FileReader(progress + "," + c.cityName + ",Airports.csv"));
                        while ((str5 = br5.readLine()) != null) {
                            String[] strings = str5.split(",");
                            Airport a = new Airport(strings[0], strings[1], Double.parseDouble(strings[2]), Double.parseDouble(strings[3]), Integer.parseInt(strings[4]), Boolean.parseBoolean(strings[5]), c);
                            c.balance += a.price;
                            FileInputStream fis = new FileInputStream(progress + "," + c.cityName + ",Airports," + strings[0] + ",pilots.txt");
                            ObjectInputStream objectInputStream = new ObjectInputStream(fis);
                            try {
                                while (true) {
                                    Person pilot = (Person) objectInputStream.readObject();
                                    a.pilots.add(pilot);
                                }
                            } catch (EOFException exception) {

                            } finally {
                                fis.close();
                                objectInputStream.close();
                            }
                            ////////////////////////////////////
                            FileInputStream fis1 = new FileInputStream(progress + "," + c.cityName + ",Airports," + strings[0] + ",planes.txt");
                            ObjectInputStream objectInputStream1 = new ObjectInputStream(fis1);
                            try {
                                while (true) {
                                    AirVehicle air = (AirVehicle) objectInputStream1.readObject();
                                    a.AllPlane.add(air);
                                }
                            } catch (EOFException exception) {
                            } finally {
                                fis1.close();
                                objectInputStream1.close();
                            }
                            ////////////////////////////////////
                            FileInputStream fis0 = new FileInputStream(progress + "," + c.cityName + ",Airports," + strings[0] + ",Travels,in.txt");
                            ObjectInputStream objectInputStream0 = new ObjectInputStream(fis0);
                            try {
                                while (true) {
                                    Travels air = (Travels) objectInputStream0.readObject();
                                    a.inTravel.add(air);
                                }
                            } catch (EOFException exception) {
                            } finally {
                                fis0.close();
                                objectInputStream0.close();
                            }
                            ////////////////////////////////////
                            FileInputStream fis00 = new FileInputStream(progress + "," + c.cityName + ",Airports," + strings[0] + ",Travels,out.txt");
                            ObjectInputStream objectInputStream00 = new ObjectInputStream(fis00);
                            try {
                                while (true) {
                                    Travels air = (Travels) objectInputStream00.readObject();
                                    a.outTravel.add(air);
                                }
                            } catch (EOFException exception) {
                            } finally {
                                fis00.close();
                                objectInputStream00.close();
                            }
                        }
                        br5.close();
                        //-----------------------------------------------------------------------------------------
                        FileInputStream fis2 = new FileInputStream(progressName + "," + c.cityName + ",Harbors.txt");
                        ObjectInputStream objectInputStream2 = new ObjectInputStream(fis2);
                        try {
                            while (true) {
                                Harbor h = (Harbor) objectInputStream2.readObject();
                                c.harbour.add(h);
                            }
                        } catch (EOFException exception) {
                        } finally {
                            fis2.close();
                            objectInputStream2.close();
                        }
                        ;
                        //-----------------------------------------------------------------------------------------
                        FileInputStream fis3 = new FileInputStream(progressName + "," + c.cityName + ",BusTerminals.txt");
                        ObjectInputStream objectInputStream3 = new ObjectInputStream(fis3);
                        try {
                            while (true) {
                                BusTerminal h = (BusTerminal) objectInputStream3.readObject();
                                c.busTerminals.add(h);
                            }
                        } catch (EOFException exception) {

                        } finally {
                            fis3.close();
                            objectInputStream3.close();
                        }
                        //-----------------------------------------------------------------------------------------
                        FileInputStream fis4 = new FileInputStream(progressName + "," + c.cityName + ",RailwayStations.txt");
                        ObjectInputStream objectInputStream4 = new ObjectInputStream(fis4);
                        try {
                            while (true) {
                                RailwayStation h = (RailwayStation) objectInputStream4.readObject();
                                c.railwayStations.add(h);
                            }
                        } catch (EOFException exception) {
                        } finally {
                            fis4.close();
                            objectInputStream4.close();
                        }
                        //------------------------------------------------------------------------------------------
                    }
                    br1.close();
                } catch (RuntimeException | IOException | ClassNotFoundException exception) {
                    exception.printStackTrace();
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/FirstMenu.fxml"));
                root = loader.load();
                Menu1Controller c1 = loader.getController();
                c1.firstMenu(e);
            }
        } catch (Exception ex) {
            myLabel.setText(ex.getMessage());
        }
    }

    public void back(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXs/FirstMenu.fxml"));
        root = loader.load();
        Menu1Controller c1 = loader.getController();
        c1.firstMenu(e);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
