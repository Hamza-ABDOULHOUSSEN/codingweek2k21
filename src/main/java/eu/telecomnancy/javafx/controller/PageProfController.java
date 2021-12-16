package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.Observateur.Observateur;
import eu.telecomnancy.javafx.compte.Eleve;
import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.model.MyRdv;
import eu.telecomnancy.javafx.rdv.Creneau;
import eu.telecomnancy.javafx.rdv.RendezVous;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PageProfController implements Observateur {

    private MyRdv myrdv;

    @FXML private VBox vbox1 ;
    @FXML private VBox vbox2 ;
    @FXML private VBox vbox3 ;

    @FXML private Label nomProf ;

    int afficheEnAttente = 0 ;
    int afficheConfirme = 0 ;
    int afficheArchive = 0 ;


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

    public Button ButtonRedX(RendezVous rdv) {
        Button buttonX = new Button() ;
        buttonX.setBackground(null);
        ImageView imageViewX = new ImageView("images/redX.png") ;
        imageViewX.setFitHeight(20);
        imageViewX.setFitWidth(20);
        buttonX.setGraphic(imageViewX);
        buttonX.setOnAction(e -> {
            try {
                myrdv.updatestatus(rdv, "annule");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            myrdv.afficheRDV("prof", "en attente");
            myrdv.afficheRDV("prof", "confirme");
            myrdv.afficheRDV("prof", "archive");

        });
        return  buttonX ;
    }

    public Button ButtonGreenV(RendezVous rdv) {
        Button buttonV = new Button() ;
        buttonV.setBackground(null);
        ImageView imageViewV = new ImageView("images/greenV.png") ;
        imageViewV.setFitHeight(20);
        imageViewV.setFitWidth(20);
        buttonV.setGraphic(imageViewV);
        buttonV.setOnAction(e -> {
            try {
                myrdv.updatestatus(rdv, "confirme");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            myrdv.afficheRDV("prof", "en attente");
            myrdv.afficheRDV("prof", "confirme");
            myrdv.afficheRDV("prof", "archive");
        });
        return buttonV ;
    }

    @FXML protected void RdvEnAttente() {
        if (afficheEnAttente==0) {
            afficheEnAttente = 1;
            myrdv.afficheRDV("prof", "en attente");
        }
        else {
            afficheEnAttente = 0;
            myrdv.clearRDV("en attente");
        }
    }

    @FXML protected void RdvConfirme() {
        if (afficheConfirme==0) {
            afficheConfirme = 1;
            myrdv.afficheRDV("prof", "confirme");
        }
        else {
            afficheConfirme = 0;
            myrdv.clearRDV("confirme");
        }

    }
    
    @FXML protected void RdvArchive() {
        if (afficheArchive==0) {
            afficheArchive = 1;
            myrdv.afficheRDV("prof", "archive");
        }
        else {
            afficheArchive = 0;
            myrdv.clearRDV("archive");
        }
    }

    public void initNom() {
        nomProf.setText("Bienvenue " + myrdv.getAccueil_nom());
    }

    @Override
    public void update() {
        ArrayList<RendezVous> enAttente = myrdv.getRdv_en_attente();
        ArrayList<RendezVous> Confirme = myrdv.getRdv_confirme();
        ArrayList<RendezVous> Archive = myrdv.getRdv_archive();

        this.vbox1.getChildren().clear();
        this.vbox2.getChildren().clear();
        this.vbox3.getChildren().clear();

        // EN ATTENTE
        if(this.afficheEnAttente == 1) {
            Label l = new Label();
            for (RendezVous rdv : enAttente) {
                Eleve e = rdv.getListe_eleve().get(0);
                Creneau c = myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(rdv.getId_creneau());
                Label label = new Label(myrdv.getConnect().getGestionnaireRdv().rdvToString(rdv, e, c));
                label.setFont(Font.font(24));
                label.setPrefSize(620, 30);
                HBox hbox = new HBox();
                hbox.getChildren().addAll(label, ButtonGreenV(rdv), ButtonRedX(rdv));
                this.vbox1.getChildren().add(hbox);
            }
        }


        // CONFIRME
        if(this.afficheConfirme == 1) {
            for (RendezVous rdv : Confirme) {
                Eleve e = rdv.getListe_eleve().get(0);
                Creneau c = myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(rdv.getId_creneau());
                Label label = new Label(myrdv.getConnect().getGestionnaireRdv().rdvToString(rdv, e, c));
                label.setFont(Font.font(24)) ;
                label.setPrefSize(650,30);
                HBox hbox = new HBox() ;
                hbox.getChildren().addAll(label, ButtonRedX(rdv)) ;
                this.vbox2.getChildren().add(hbox) ;
            }
        }


        // ARCHIVE
        if (this.afficheArchive == 1) {
            for (RendezVous rdv : Archive) {
                Eleve e = rdv.getListe_eleve().get(0);
                Creneau c = myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(rdv.getId_creneau());
                Label label = new Label(myrdv.getConnect().getGestionnaireRdv().rdvToString(rdv, e, c));
                label.setFont(Font.font(24)) ;
                label.setPrefSize(650,30);
                this.vbox3.getChildren().add(label) ;
            }
        }
    }
}
