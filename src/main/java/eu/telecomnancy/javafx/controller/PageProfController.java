package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.Observateur.Observateur;
import eu.telecomnancy.javafx.model.MyRdv;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PageProfController implements Observateur {

    private MyRdv myrdv;

    @FXML private VBox vbox1 ;
    @FXML private VBox vbox2 ;
    @FXML private VBox vbox3 ;

    public PageProfController(MyRdv myrdv) {
        this.myrdv = myrdv;
        myrdv.ajouterObservateur(this);
    }

    // Renvoie à la PageAccueil
    @FXML protected void Deconnexion() throws IOException {
        PageAccueilController pac = new PageAccueilController(myrdv);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageAccueil.fxml"));
        fxmlLoader.setControllerFactory(ic -> {
            if (ic.equals(eu.telecomnancy.javafx.controller.PageAccueilController.class)) return pac;
            else return null;
        });
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        myrdv.setScene(scene);
    }

    @FXML protected void RdvEnAttente() {}
    @FXML protected void RdvConfirme() {}
    @FXML protected void RdvArchive() {}

    @Override
    public void update() {

    }
}
