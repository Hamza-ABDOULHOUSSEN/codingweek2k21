package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.Observateur.Observateur;
import eu.telecomnancy.javafx.model.MyRdv;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class PageEleveController implements Observateur {

    private MyRdv myrdv;

    @FXML private Label nomEleve ;

    @FXML private VBox VboxAttente ;
    @FXML private VBox VboxConfirme ;
    @FXML private VBox VboxArchive ;

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
        VboxAttente.getChildren().clear() ;
        ArrayList<String> result = new ArrayList<String>() ;
        result.add("Rdv1 8h20 avec Sami Boulechfar") ;
        result.add("Rdv2 9h40 avec Maha Khatib") ;
        result.add("Rdv3 10h40 avec Hamza Abdoulhoussen") ;
        for (String s : result) {
            Label label = new Label();
            label.setFont(new Font("Arial", 24));
            label.setText(s);
            VboxAttente.getChildren().add(label);
        }
    }

    @FXML
    protected void RdvConfirme(){
    }

    @FXML
    protected void RdvArchive(){
    }

    @FXML public void goPageProfilEleve() {
        PageProfilEleveController ppc = new PageProfilEleveController(myrdv);
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageProf.fxml"));
        fxmlLoader.setControllerFactory(ic -> {
            if (ic.equals(eu.telecomnancy.javafx.controller.PageProfController.class)) return ppc;
            else return null;
        });
        Parent root = fxmlLoader.load();
        ppc.initNom();
        Scene scene = new Scene(root);
        myrdv.setScene(scene);
    }

    public void initNom() {
        nomEleve.setText("Bienvenue " + myrdv.getAccueil_nom());
    }

    @Override
    public void update() {

    }
}
