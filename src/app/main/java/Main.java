import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.FileOperation;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginPage.fxml"));
        primaryStage.setTitle("Panel logowania");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);       // okno nie zmienia rozmiaru
        FileOperation.getDataFromFile();        // pobranie zawartości z pliku do listy users
        FileOperation.getEventDataFromFile();
    }
    @Override
    public void stop() throws IOException {
        FileOperation.setDataToFile();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
