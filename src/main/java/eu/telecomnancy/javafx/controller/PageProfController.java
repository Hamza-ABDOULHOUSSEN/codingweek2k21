package eu.telecomnancy.javafx.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class PageProfController {

    @FXML private VBox vbox1 ;
    @FXML private VBox vbox2 ;
    @FXML private VBox vbox3 ;

    @FXML protected void exit() {
        Platform.exit();
    }

    @FXML protected void RdvEnAttente() {}
    @FXML protected void RdvConfirme() {}
    @FXML protected void RdvArchive() {}
}
