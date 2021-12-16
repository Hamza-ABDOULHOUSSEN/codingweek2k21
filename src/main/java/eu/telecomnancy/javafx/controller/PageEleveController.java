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
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PageEleveController implements Observateur {

    private MyRdv myrdv;

    @FXML private Label nomEleve ;

    @FXML private VBox VboxAttente ;
    @FXML private VBox VboxConfirme ;
    @FXML private VBox VboxArchive ;

    int afficheEnAttente = 0 ;
    int afficheConfirme = 0 ;
    int afficheArchive = 0 ;

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
        pdrc.initChoixProf() ;
        pdrc.initChoixJour() ;
        pdrc.initChoixHoraire();
        Scene scene = new Scene(root);
        myrdv.setScene(scene);
    }

    @FXML
    protected void RdvEnAttente() {
        if (afficheEnAttente==0) {
            afficheEnAttente = 1;
            myrdv.afficheRDV("eleve", "en attente");
        }
        else {
            afficheEnAttente = 0;
            myrdv.clearRDV("en attente");
        }
    }

    @FXML
    protected void RdvConfirme(){
        if (afficheConfirme==0) {
            afficheConfirme = 1;
            myrdv.afficheRDV("eleve", "confirme");
        }
        else {
            afficheConfirme = 0;
            myrdv.clearRDV("confirme");
        }
    }

    @FXML
    protected void RdvArchive(){
        if (afficheArchive==0) {
            afficheArchive = 1;
            myrdv.afficheRDV("eleve", "archive");
        }
        else {
            afficheArchive = 0;
            myrdv.clearRDV("archive");
        }
    }

    @FXML public void goPageProfilEleve() throws IOException {
        FXMLLoader fxmlLoader = null;
        PageProfilEleveController ppec = new PageProfilEleveController(myrdv);
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageProfilEleve.fxml"));
        fxmlLoader.setControllerFactory(ic -> {
            if (ic.equals(eu.telecomnancy.javafx.controller.PageProfilEleveController.class)) return ppec;
            else return null;
        });
        Parent root = fxmlLoader.load();
        ppec.initPage();
        Scene scene = new Scene(root);
        myrdv.setScene(scene);
    }

    public void initNom() {
        nomEleve.setText("Bienvenue " + myrdv.getAccueil_nom());
    }

    public javafx.scene.control.Button ButtonRedX(RendezVous rdv) {
        javafx.scene.control.Button buttonX = new javafx.scene.control.Button() ;
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
            myrdv.afficheRDV("eleve", "en attente");
            myrdv.afficheRDV("eleve", "confirme");
            myrdv.afficheRDV("eleve", "archive");
        });
        return  buttonX ;
    }

    public javafx.scene.control.Button ButtonGreenV(RendezVous rdv) {
        javafx.scene.control.Button buttonV = new Button() ;
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
            myrdv.afficheRDV("eleve", "en attente");
            myrdv.afficheRDV("eleve", "confirme");
            myrdv.afficheRDV("eleve", "archive");
        });
        return buttonV ;
    }

    @Override
    public void update() {
        ArrayList<RendezVous> enAttente = myrdv.getRdv_en_attente();
        ArrayList<RendezVous> Confirme = myrdv.getRdv_confirme();
        ArrayList<RendezVous> Archive = myrdv.getRdv_archive();

        this.VboxAttente.getChildren().clear();
        this.VboxConfirme.getChildren().clear();
        this.VboxArchive.getChildren().clear();

        // EN ATTENTE
        if(this.afficheEnAttente == 1) {
            Label l = new Label();
            for (RendezVous rdv : enAttente) {
                Professeur p = myrdv.getConnect().getGestionnaireProf().getTable_prof().get(rdv.getId_prof());
                Creneau c = myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(rdv.getId_creneau());
                Label label = new Label(myrdv.getConnect().getGestionnaireRdv().rdvToString(rdv, p, c));
                label.setFont(Font.font(24));
                label.setPrefSize(620, 30);
                HBox hbox = new HBox();
                hbox.getChildren().addAll(label, ButtonGreenV(rdv), ButtonRedX(rdv));
                this.VboxAttente.getChildren().add(hbox);
            }
        }


        // CONFIRME
        if(this.afficheConfirme == 1) {
            for (RendezVous rdv : Confirme) {
                Professeur p = myrdv.getConnect().getGestionnaireProf().getTable_prof().get(rdv.getId_prof());
                Creneau c = myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(rdv.getId_creneau());
                Label label = new Label(myrdv.getConnect().getGestionnaireRdv().rdvToString(rdv, p, c));
                label.setFont(Font.font(24)) ;
                label.setPrefSize(650,30);
                HBox hbox = new HBox() ;
                hbox.getChildren().addAll(label, ButtonRedX(rdv)) ;
                this.VboxConfirme.getChildren().add(hbox) ;
            }
        }


        // ARCHIVE
        if (this.afficheArchive == 1) {
            for (RendezVous rdv : Archive) {
                Professeur p = myrdv.getConnect().getGestionnaireProf().getTable_prof().get(rdv.getId_prof());
                Creneau c = myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(rdv.getId_creneau());
                Label label = new Label(myrdv.getConnect().getGestionnaireRdv().rdvToString(rdv, p, c));
                label.setFont(Font.font(24)) ;
                label.setPrefSize(650,30);
                this.VboxArchive.getChildren().add(label) ;
            }
        }

    }
}
