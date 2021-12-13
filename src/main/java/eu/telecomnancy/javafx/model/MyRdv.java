package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.Observateur.SujetObserve;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyRdv extends SujetObserve {

    private Stage stage;

    public MyRdv(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
    }


}
