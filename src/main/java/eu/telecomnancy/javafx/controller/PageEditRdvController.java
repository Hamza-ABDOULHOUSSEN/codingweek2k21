package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.Observateur.Observateur;
import eu.telecomnancy.javafx.compte.Eleve;
import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.gestionnaire.GestionnaireRdv;
import eu.telecomnancy.javafx.model.MyRdv;
import eu.telecomnancy.javafx.rdv.Creneau;
import eu.telecomnancy.javafx.rdv.RendezVous;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PageEditRdvController implements Observateur {

    private MyRdv myrdv;
    private RendezVous rdv ;

    @FXML private MenuButton choisirProf ;
    @FXML private MenuButton choisirJour ;
    @FXML private MenuButton choisirCreneau ;
    @FXML private TextField inputDescription ;
    @FXML private TextField inputLieu ;
    @FXML private Label erreur ;
    @FXML private TextField inputIntitule ;

    private String nomProf = "" ;
    private String prenomProf = "" ;
    private String jour = "" ;
    private String heure = "" ;

    public PageEditRdvController(MyRdv myrdv) {
        this.myrdv = myrdv;
        myrdv.ajouterObservateur(this);
    }

    public void setRdv(RendezVous rdv) {
        this.rdv = rdv ;
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
        pec.initPage();
        Scene scene = new Scene(root);
        myrdv.setScene(scene);
    }

    public void initPage(RendezVous rdv){
        setRdv(rdv);
        this.choisirProf.setText(myrdv.getConnect().getGestionnaireProf().getTable_prof().get(this.rdv.getId_prof()).getNom()) ;
        this.choisirJour.setText(myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(this.rdv.getId_creneau()).getJour()) ;
        this.choisirCreneau.setText(myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(this.rdv.getId_creneau()).getHeure());
        if (!(this.rdv.getIntitule() == null)) { this.inputIntitule.setPromptText(rdv.getIntitule()); }
        if (!(this.rdv.getLieu() == null)) { this.inputLieu.setPromptText(rdv.getLieu()); }
        if (!(this.rdv.getDescr() == null)) { this.inputDescription.setPromptText(rdv.getDescr()); }
        initChoixProf();
        initChoixJour();
        initChoixHoraire();
    }

    public void initChoixProf() {
        for (int i : myrdv.getConnect().getGestionnaireProf().getTable_prof().keySet()) {
            MenuItem mi = new MenuItem() ;
            mi.setText(myrdv.getConnect().getGestionnaireProf().getTable_prof().get(i).getNom() + " " + myrdv.getConnect().getGestionnaireProf().getTable_prof().get(i).getPrenom());
            mi.setOnAction(e -> { setChoixProf(myrdv.getConnect().getGestionnaireProf().getTable_prof().get(i).getNom(), myrdv.getConnect().getGestionnaireProf().getTable_prof().get(i).getPrenom()) ; });
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

    public void setChoixProf(String nom, String prenom) {
        this.choisirProf.setText(nom + " " + prenom);
        this.nomProf = nom ;
        this.prenomProf = prenom ;
    }

    public void setChoixJour(String text) {
        this.choisirJour.setText(text);
        this.jour = text ;
    }

    public void setChoixHoraire(String text) {
        this.choisirCreneau.setText(text);
        this.heure = text ;
    }

    @FXML public void envoyerDemande() throws SQLException {
        if (!(this.choisirProf.getText().equals("Choisir un professeur")  || this.choisirJour.getText().equals("Choisir un jour") || this.choisirCreneau.getText().equals("Choisir un horaire"))) {
            String rdv = "Rendez-vous avec " + this.choisirProf.getText() + " le " + this.choisirJour.getText() + " a " + this.choisirCreneau.getText() ;
            Professeur prof = myrdv.getConnect().getGestionnaireProf().findProf(this.nomProf, this.prenomProf) ;
            ArrayList<Eleve> eleves = new ArrayList<Eleve>() ;
            Creneau creneau = myrdv.getConnect().getGestionnaireCreneau().findCreneau(this.jour, this.heure) ;

            eleves.add(myrdv.getEleve()) ;
            myrdv.getConnect().getGestionnaireRdv().addRdv(prof, eleves, creneau, this.inputLieu.getText(), this.inputDescription.getText(), this.inputIntitule.getText()) ;

            this.choisirProf.setText("Choisir un professeur");
            this.choisirJour.setText("Choisir un jour");
            this.choisirCreneau.setText("Choisir un creneau");
            this.inputLieu.setText("");
            this.inputLieu.setPromptText("Lieu");
            this.inputDescription.setText("");
            this.inputDescription.setPromptText("Description");
            this.erreur.setText(rdv);
            //
            this.inputIntitule.setText("");
            this.inputIntitule.setPromptText("Intitulé");

        }
        else {
            this.erreur.setText("Veuillez choisir un professeur, un jour et un horaire");
        }
    }

    @Override public void update() {}

    public void modifierDemande(ActionEvent actionEvent) {
        /*
        Professeur prof = myrdv.getConnect().getGestionnaireProf().findProf(this.nomProf, this.prenomProf) ;
        ArrayList<Eleve> eleves = new ArrayList<Eleve>() ;
        Creneau creneau = myrdv.getConnect().getGestionnaireCreneau().findCreneau(this.jour, this.heure) ;
        this.myrdv.getConnect().getGestionnaireProf().changeRdv()
        
         */
    }

}
