package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;
import service.AlertService;
import util.InMemoryDB;

import java.io.IOException;

public class RegistrationController {
    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private PasswordField pfPasswordConfirm;
    @FXML
    private TextField tfEmail;

    @FXML
    void backAction(MouseEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginPage.fxml"));
        primaryStage.setTitle("Panel logowania");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Stage stage = (Stage) tfLogin.getScene().getWindow();
        stage.close();
    }

    @FXML
    void registerAction(MouseEvent event) {
        // sprawdzenie unikatowości loginu
        if(InMemoryDB.users.stream().noneMatch(user -> user.getLogin().equals(tfLogin.getText()))) {
            // sprawdzenie tych samych wartośći w hasłach
            if (pfPassword.getText().equals(pfPasswordConfirm.getText())) {
                InMemoryDB.users.add(new User(
                        tfLogin.getText(),
                        pfPassword.getText(),
                        tfEmail.getText()));
                AlertService.getAlert(
                        Alert.AlertType.INFORMATION,
                        "Zarejestrowano", "Zarejestrowano użytkownika",
                        "Użytkownik został zarejestorwany możesz się zalogować");
                    // czyszczenie pól kontrolek
                    tfLogin.setText("");
                    pfPassword.setText("");
                    pfPasswordConfirm.setText("");
                    tfEmail.setText("");
            } else {
                AlertService.getAlert(
                        Alert.AlertType.ERROR,
                        "Błąd rejestracji", "Błąd rejestracji użytkownika",
                        "Podane hasła muszą być takie same");
            }
        } else {
            AlertService.getAlert(
                    Alert.AlertType.ERROR,
                    "Błąd rejestracji", "Błąd rejestracji użytkownika",
                    "Istnije już taki login w naszej bazie danych");
        }
    }
}
