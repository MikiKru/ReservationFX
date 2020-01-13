package service;

import javafx.scene.control.Alert;

public class AlertService {
    public static void getAlert(
            Alert.AlertType alertType,
            String title,
            String header,
            String content){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
