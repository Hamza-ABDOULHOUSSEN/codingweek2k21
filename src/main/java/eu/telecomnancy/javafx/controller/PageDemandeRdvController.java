package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.Observateur.Observateur;
import eu.telecomnancy.javafx.model.MyRdv;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class PageDemandeRdvController implements Observateur {

    private MyRdv myrdv;

    @FXML private MenuButton choisirProf ;
    @FXML private MenuButton choisirJour ;
    @FXML private MenuButton choisirCreneau ;
    @FXML private TextField inputDescription ;

    public PageDemandeRdvController(MyRdv myrdv) {
        this.myrdv = myrdv;
        myrdv.ajouterObservateur(this);
    }

    // Renvoie à la PageEleve
    @FXML protected void RetourEleve() throws IOException {
        PageEleveController pec = new PageEleveController(myrdv);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageEleve.fxml"));
        fxmlLoader.setControllerFactory(ic -> {
            if (ic.equals(eu.telecomnancy.javafx.controller.PageEleveController.class)) return pec;
            else return null;
        });
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        myrdv.setScene(scene);
    }

    public void initChoixProf() {
        for (int i : myrdv.getConnect().getGestionnaireProf().getTable_prof().keySet()) {
            MenuItem mi = new MenuItem() ;
            mi.setText(myrdv.getConnect().getGestionnaireProf().getTable_prof().get(i).getNom() + " " + myrdv.getConnect().getGestionnaireProf().getTable_prof().get(i).getPrenom());
            mi.setOnAction(e -> { setChoixProf(mi.getText()) ; });
            choisirProf.getItems().add(mi) ;
        }
    }

    public void initChoixJour() {
        ArrayList<String> list = new ArrayList<String>() ;
        for (int i : myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().keySet()) {
            String jour = myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(i).getJour() ;
            if (!list.contains(jour)) {
                list.add(0, jour) ;
                MenuItem mi = new MenuItem() ;
                mi.setText(jour);
                mi.setOnAction(e -> { setChoixJour(mi.getText()) ; });
                choisirJour.getItems().add(mi) ;
            }
        }
    }

    public void initChoixHoraire() {
        ArrayList<String> list = new ArrayList<String>() ;
        for (int i : myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().keySet()) {
            String horaire = myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(i).getHeure() ;
            if (!list.contains(horaire)) {
                list.add(0, horaire);
                MenuItem mi = new MenuItem();
                mi.setText(myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(i).getHeure());
                mi.setOnAction(e -> {setChoixHoraire(mi.getText());});
                choisirCreneau.getItems().add(mi);
            }
        }
    }

    public void setChoixProf(String text) {
        this.choisirProf.setText(text);
    }

    public void setChoixJour(String text) {
        this.choisirJour.setText(text);
    }

    public void setChoixHoraire(String text) {
        this.choisirCreneau.setText(text);
    }

    @FXML public void envoyerDemande() {
        String rdv = "Rendez-vous avec " + this.choisirProf.getText() + " à " + this.choisirCreneau.getText() + " au " + this.inputDescription.getText() ;
        System.out.println(rdv);
    }

    @Override public void update() {

    }
}
