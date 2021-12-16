package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.Observateur.Observateur;
import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.model.MyRdv;
import eu.telecomnancy.javafx.rdv.RendezVous;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class PageProfController implements Observateur {

    private MyRdv myrdv;

    @FXML private VBox vbox1 ;
    @FXML private VBox vbox2 ;
    @FXML private VBox vbox3 ;

    @FXML private Label nomProf ;

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

    @FXML protected void RdvEnAttente() {
        ArrayList<RendezVous> list = myrdv.getAllRdv(myrdv.getProf(), "en attente") ;
        Label l = new Label() ;
        for (RendezVous rdv : list) {
            Label label = new Label(myrdv.getConnect().getGestionnaireRdv().rdvToString(rdv)) ;
            label.setFont(Font.font(24)) ;
            this.vbox1.getChildren().add(label) ;
        }
    }
    @FXML protected void RdvConfirme() {
        ArrayList<RendezVous> list = myrdv.getAllRdv(myrdv.getProf(), "confirme");
        for (RendezVous rdv : list) {
            Label label = new Label(myrdv.getConnect().getGestionnaireRdv().rdvToString(rdv)) ;
            label.setFont(Font.font(24)) ;
            this.vbox2.getChildren().add(label) ;
        }
    }
    @FXML protected void RdvArchive() {
        ArrayList<RendezVous> list = myrdv.getAllRdv(myrdv.getProf(), "archive");
        for (RendezVous rdv : list) {
            Label label = new Label(myrdv.getConnect().getGestionnaireRdv().rdvToString(rdv)) ;
            label.setFont(Font.font(24)) ;
            this.vbox3.getChildren().add(label) ;
        }
    }
    public void initNom() {
        nomProf.setText("Bienvenue " + myrdv.getAccueil_nom());
    }


    @Override
    public void update() {

    }
}
