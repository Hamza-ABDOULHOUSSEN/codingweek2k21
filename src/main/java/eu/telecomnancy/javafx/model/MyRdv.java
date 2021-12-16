package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.ConnectToDb.Connect ;
import eu.telecomnancy.javafx.Observateur.SujetObserve;
import eu.telecomnancy.javafx.compte.Eleve;
import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.gestionnaire.GestionnaireEleve;
import eu.telecomnancy.javafx.gestionnaire.GestionnaireProf;
import eu.telecomnancy.javafx.rdv.RendezVous;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

public class MyRdv extends SujetObserve {

    private Stage stage;
    private Connect connect = new Connect() ;

    private String Accueil_nom ;
    private String Accueil_mdp ;
    private String erreur;

    private Professeur prof ;
    private Eleve eleve ;

    public MyRdv(Stage stage) throws URISyntaxException, IOException {
        this.stage = stage ;
        this.Accueil_nom = "Nom" ;
        this.Accueil_mdp = "Mot de passe" ;
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
    }

    // Renvoi 0 si l'id saisi n'est ni dans table_id_prof, ni dans table_id_eleve, 1 s'il est dans table_id_prof, 2 sinon.
    public int check_id(String nom, String mdp) {
        Professeur p = connect.getGestionnaireProf().containsNomMdp(nom, mdp) ;
        if (p != null) {
            this.prof = p ;
            return 1 ; }
        Eleve e = connect.getGestionnaireEleve().containsNomMdp(nom, mdp) ;
        if (e != null) {
            this.eleve = e ;
            return 2 ; }
        return 0 ;
    }

    public ArrayList<RendezVous> getAllRdv(Professeur prof, String etat) {
        ArrayList<RendezVous> list = new ArrayList<RendezVous>() ;
        Hashtable<Integer, RendezVous> table_rdv = connect.getGestionnaireRdv().getTable_rdv() ;
        for (int i : connect.getGestionnaireRdv().getTable_rdv().keySet()) {
            if (table_rdv.get(i).getId_prof() == prof.getId() && table_rdv.get(i).getEtat().equals(etat)) {
                list.add(connect.getGestionnaireRdv().getTable_rdv().get(i));
            }
        }
        return list ;
    }

    // GETTERS
    public String getAccueil_nom() {
        return Accueil_nom ;
    }

    public String getAccueil_mdp() {
        return Accueil_mdp ;
    }

    public String getErreur() {
        return erreur ;
    }

    public Professeur getProf() {
        return this.prof ;
    }

    public Eleve getEleve() {
        return this.eleve ;
    }


    // SETTERS
    public void setAccueil_nom_mdp(String nom, String mdp) {
        Accueil_nom = nom ;
        Accueil_mdp = mdp ;
        notifierObservateurs();
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
        notifierObservateurs();
    }

    public Connect getConnect() { return connect ; }
}
