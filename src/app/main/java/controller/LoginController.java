package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.InMemoryDB;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label lblInfo;
    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField pfPassword;

    // funkcja do logowania zwraca boolean
    private boolean isLogged(String login, String password){
        return InMemoryDB.users.stream()
                .anyMatch(user -> user.getLogin().equals(login) && user.getPassword().equals(password));
    }

    @FXML
    void loginAction(MouseEvent event) {
        if(isLogged(tfLogin.getText(), pfPassword.getText())){
            // okienko informacyjne
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Zalogowano");
            alert.setHeaderText("Zalogowano poprawnie");
            alert.setContentText("Zalogowno poprawnie, ale aplikacja jest w trakcie budowy ");
            alert.showAndWait();
            lblInfo.setText("");
        } else {
            lblInfo.setText("BŁAD LOGOWANIA!");
        }
    }
    @FXML
    void registerAction(MouseEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/registrationPage.fxml"));
        primaryStage.setTitle("Panel rejestracji");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        // zakmnięcie aktualnie otwartego okna
        Stage stage = (Stage) tfLogin.getScene()            // pobierz Scene na którj znajduje się tfLogin
                                            .getWindow();   // pobierz Window - obiekt okna aplikacji
        // na obiekcie stage wywołaj metodę close
        stage.close();
    }

}

