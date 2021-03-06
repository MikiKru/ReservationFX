package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Event;
import service.AlertService;
import util.FileOperation;
import util.InMemoryDB;

import java.io.*;
import java.time.format.DateTimeFormatter;

public class ReservationController {
    @FXML
    private TextField tfSearch;
    @FXML
    private ComboBox<Event> cEvent;         // cEvent będzie zawierać obiekty klasy Event
    @FXML
    private Spinner<Integer> sNumber;
    @FXML
    private CheckBox cbFv;
    @FXML
    private TextArea taDescription;
    @FXML
    private ComboBox<String> cType;
    @FXML
    private Label lblAviable;
    @FXML
    private TextArea taFV;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnConfirm;

    // przechwuje dane o eventach
    private ObservableList<Event> fxEvents = FXCollections.observableArrayList(InMemoryDB.events);
    // przechowyje dane o typach uczestnictwa w ramach wybranego eventu
    private ObservableList<String> fxTypes = FXCollections.observableArrayList();
    // wybrany event
    private Event selectedEvent;
    private Integer places;

    @FXML
    void confirmAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File("C:\\Users\\PROXIMO\\Desktop\\TARR1\\Reservation\\src\\app\\main\\resources\\files"));
        Stage stage = new Stage();
        File selectedFile = fileChooser.showSaveDialog(stage);
        FileWriter writer = new FileWriter(selectedFile);
        writer.write("Potwierdzenie rejestracji na wydarzenie " + selectedEvent.getName() + "\n" );
        writer.write("Termin: " + selectedEvent.getStartTime() + "\n");
        writer.write("Ilość miejsc: " + places + "\n");
        writer.close();
    }

    @FXML
    void submitAction(ActionEvent event) throws IOException {
        if (cType.getValue() != null) {
            // Alert typu Confirmation
            boolean decision = AlertService.getConfirmationAlert(
                    "Potwierdź rejestrację", "Potwierdź rejestrację",
                    "Czy na pewno chcesz się zarejestrować na wydarzenie " + cEvent.getValue().getName());
            if(decision){
                // update w pliku events
                Event selectedEvent = cEvent.getValue();
                places = sNumber.getValue();
                InMemoryDB.events.stream()
                        .filter(e -> e.equals(selectedEvent))
                        .findFirst().get().decrementPlaces(places);
                FileOperation.setEventDataToFile();
                // czyszczenie pol -> przywrócenie stany początkowego
                cEvent.setValue(null);
                cType.setValue(null);
                lblAviable.setText("dostępne");
                cbFv.setSelected(false);
                taFV.clear();
                taDescription.clear();
                cType.setDisable(true);
                cbFv.setDisable(true);
                taFV.setDisable(true);
                taDescription.setDisable(true);
                sNumber.setDisable(true);
                SpinnerValueFactory<Integer> spinerValues = new SpinnerValueFactory
                        .IntegerSpinnerValueFactory(1, 1, 1, 1);
                sNumber.setValueFactory(spinerValues);
                btnSubmit.setDisable(true);
                btnConfirm.setDisable(false);

            }
        } else {
            AlertService.getAlert(Alert.AlertType.ERROR, "Błąd", "Błąd zapisu na event", "Musisz wybrać typ uczestnictwa");
        }
    }

    @FXML
    void selectEventAction(ActionEvent event) {
        // sprawdzam czy wybrano jakiś kurs
        if (cEvent.getValue() != null) {
            taDescription.setDisable(false);
            cbFv.setDisable(false);
            btnSubmit.setDisable(false);
            sNumber.setDisable(false);
            cType.setDisable(false);
            // pobierz obiekt z cEvent
            selectedEvent = cEvent.getValue();
            taDescription.setText(
                    selectedEvent.getName() + "\n" +
                            selectedEvent.getStartTime().format(
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n" +
                            selectedEvent.getDescription());
            taDescription.setEditable(false);
            lblAviable.setText("dostępne: " + selectedEvent.getAvailablePlaces());
            // dodanie tablicy String [] do FXCollection
            fxTypes = FXCollections.observableArrayList(selectedEvent.getType());
            // dodać do cType - typy uczestnictwa
            cType.setItems(fxTypes);
            // dodanie miejsc do zarezerwowania
            SpinnerValueFactory<Integer> spinerValues = new SpinnerValueFactory
                    .IntegerSpinnerValueFactory(1, selectedEvent.getAvailablePlaces(), 1, 1);
            sNumber.setValueFactory(spinerValues);
            btnConfirm.setDisable(true);
        }
    }





    @FXML
    void fvAction(ActionEvent event) {
        if (cbFv.isSelected()) {          // gdy cb jest zaznaczony
            taFV.setDisable(false);
        } else {                        // gdy cb nie jest zaznaczony
            taFV.setDisable(true);
            taFV.clear();
        }
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


    // metoda która się wykona jko pierwsza po odpaleniu widoku
    public void initialize() {
        // załadować dane z listy event do combo cEvent
        cEvent.setItems(fxEvents);
    }
}
