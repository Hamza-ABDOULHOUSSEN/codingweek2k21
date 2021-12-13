package eu.telecomnancy.javafx;

import eu.telecomnancy.javafx.controller.PageAccueilController;
import eu.telecomnancy.javafx.model.MyRdv;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // creation modèle
        MyRdv myrdv = new MyRdv(stage);

        // creation observateurs
        PageAccueilController pac = new PageAccueilController(myrdv);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageAccueil.fxml"));

        fxmlLoader.setControllerFactory(ic -> {
            if (ic.equals(eu.telecomnancy.javafx.controller.PageAccueilController.class)) return pac;
            else return null;
        });

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/stylesheet/style.css").toExternalForm());

        stage.setTitle("JavaFX and Gradle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}