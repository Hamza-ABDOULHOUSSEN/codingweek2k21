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
    
    private Professeur oldProf ;
    private Creneau oldCreneau ;

    private String nomProf ;
    private String prenomProf ;
    private String jour ;
    private String heure ;

    public PageEditRdvController(MyRdv myrdv) {
        myrdv = myrdv;
        myrdv.ajouterObservateur(this);
    }

    public void setRdv(RendezVous rdv) {
        rdv = rdv ;
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
        
        oldProf = myrdv.getConnect().getGestionnaireProf().getTable_prof().get(rdv.getId_prof());
        oldCreneau = myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(rdv.getId_creneau());

        nomProf = oldProf.getNom();
        prenomProf = myrdv.getConnect().getGestionnaireProf().getTable_prof().get(rdv.getId_prof()).getPrenom() ;
        jour = oldCreneau.getJour() ;
        heure = oldCreneau.getHeure() ;

        choisirProf.setText(nomProf + " " + prenomProf) ;
        choisirJour.setText(jour) ;
        choisirCreneau.setText(heure);

        if (!(rdv.getIntitule() == null)) { inputIntitule.setPromptText(rdv.getIntitule()); }
        if (!(rdv.getLieu() == null)) { inputLieu.setPromptText(rdv.getLieu()); }
        if (!(rdv.getDescr() == null)) { inputDescription.setPromptText(rdv.getDescr()); }
        
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
        choisirProf.setText(nom + " " + prenom);
        nomProf = nom ;
        prenomProf = prenom ;
    }

    public void setChoixJour(String text) {
        choisirJour.setText(text);
        jour = text ;
    }

    public void setChoixHoraire(String text) {
        choisirCreneau.setText(text);
        heure = text ;
    }

    @FXML public void modifierRdv() throws SQLException {

        if (!(choisirProf.getText().equals("Choisir un professeur")  || choisirJour.getText().equals("Choisir un jour") || choisirCreneau.getText().equals("Choisir un horaire"))) {
            String rdv_text = "Rendez-vous avec " + choisirProf.getText() + " le " + choisirJour.getText() + " a " + choisirCreneau.getText() ;
            Professeur prof = myrdv.getConnect().getGestionnaireProf().findProf(nomProf, prenomProf) ;
            ArrayList<Eleve> eleves = new ArrayList<Eleve>() ;
            Eleve eleve = myrdv.getEleve();
            eleves.add(eleve) ;

            Creneau creneau = myrdv.getConnect().getGestionnaireCreneau().findCreneau(jour, heure) ;
            
            if (oldProf != prof || oldCreneau != creneau) {
                myrdv.getConnect().getGestionnaireRdv().changeRdv(rdv, prof, eleves, creneau, inputLieu.getText(), inputDescription.getText(), inputIntitule.getText());
                myrdv.getConnect().getGestionnairePlanning().deletePlanning(oldProf, oldCreneau);
                myrdv.getConnect().getGestionnairePlanning().addPlaning(prof, creneau);

                choisirProf.setText("Choisir un professeur");
                choisirJour.setText("Choisir un jour");
                choisirCreneau.setText("Choisir un creneau");
                inputLieu.setText("");
                inputLieu.setPromptText("Lieu");
                inputDescription.setText("");
                inputDescription.setPromptText("Description");
                erreur.setText(rdv_text);
                //
                inputIntitule.setText("");
                inputIntitule.setPromptText("Intitulé");
            }
            
        }
        else {
            erreur.setText("Veuillez choisir un professeur, un jour et un horaire");
        }
    }

    @Override public void update() {}

    public void modifierDemande(ActionEvent actionEvent) {
        /*
        Professeur prof = myrdv.getConnect().getGestionnaireProf().findProf(nomProf, prenomProf) ;
        ArrayList<Eleve> eleves = new ArrayList<Eleve>() ;
        Creneau creneau = myrdv.getConnect().getGestionnaireCreneau().findCreneau(jour, heure) ;
        myrdv.getConnect().getGestionnaireProf().changeRdv()
        
         */
    }

}
