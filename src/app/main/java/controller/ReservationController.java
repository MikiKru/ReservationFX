package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Event;
import util.InMemoryDB;

import java.io.IOException;

public class ReservationController {
    @FXML
    private TextField tfSearch;
    @FXML
    private ComboBox<Event> cEvent;         // cEvent będzie zawierać obiekty klasy Event
    @FXML
    private Spinner<?> sNumber;
    @FXML
    private CheckBox cbFv;
    @FXML
    private TextArea taDescription;
    @FXML
    private ComboBox<?> cType;
    @FXML
    private Label lblAviable;
    @FXML
    private TextArea taFV;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnConfirm;

    @FXML
    void selectEventAction(ActionEvent event) {

    }

    @FXML
    void confirmAction(ActionEvent event) {

    }

    @FXML
    void fvAction(ActionEvent event) {

    }

    @FXML
    void submitAction(ActionEvent event) {

    }
    @FXML
    void logoutAction(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginPage.fxml"));
        primaryStage.setTitle("Panel logowanie");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        // zakmnięcie aktualnie otwartego okna
        Stage stage = (Stage) tfSearch.getScene().getWindow();
        stage.close();
    }
    @FXML
    void exitAction(ActionEvent event) {
        Platform.exit();
    }
    ObservableList<Event> fxEvents = FXCollections.observableArrayList(InMemoryDB.events);
    // metoda która się wykona jko pierwsza po odpaleniu widoku
    public void initialize(){
        // załadować dane z listy event do combo cEvent
        cEvent.setItems(fxEvents);
    }
}
