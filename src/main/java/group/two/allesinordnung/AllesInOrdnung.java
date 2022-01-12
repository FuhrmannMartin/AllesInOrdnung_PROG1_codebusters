package group.two.allesinordnung;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// start program here
public class AllesInOrdnung extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AllesInOrdnung.class.getResource("AllesInOrdnungNewOne.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 510.0);
        stage.setTitle("Alles in Ordnung!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}