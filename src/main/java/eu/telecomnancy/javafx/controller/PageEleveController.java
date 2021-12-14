package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.Observateur.Observateur;
import eu.telecomnancy.javafx.model.MyRdv;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;

public class PageEleveController implements Observateur {

    private MyRdv myrdv;

    @FXML private Label nomEleve ;

    public PageEleveController(MyRdv myrdv) {
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
    @FXML
    protected void DemanderRdv() throws IOException {
        PageDemandeRdvController pdrc = new PageDemandeRdvController(myrdv);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageDemandeRdv.fxml"));
        fxmlLoader.setControllerFactory(ic -> {
            if (ic.equals(eu.telecomnancy.javafx.controller.PageDemandeRdvController.class)) return pdrc;
            else return null;
        });
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        myrdv.setScene(scene);
    }

    @FXML
    protected void RdvEnAttente() {}

    @FXML
    protected void RdvConfirme(){
    }

    @FXML
    protected void RdvArchive(){
    }

    public void initNom() {
        nomEleve.setText("Bienvenu " + myrdv.getAccueil_nom());
    }

    @Override
    public void update() {

    }
}
