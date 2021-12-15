package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.ConnectToDB.Connect;
import eu.telecomnancy.javafx.Observateur.SujetObserve;
import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.gestionnaire.GestionnaireEleve;
import eu.telecomnancy.javafx.gestionnaire.GestionnaireProf;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

    public MyRdv(Stage stage) {
        this.stage = stage ;
        this.Accueil_nom = "Nom" ;
        this.Accueil_mdp = "Mot de passe" ;

        /*
        connect.printTable(connect.getGestionnaireEleve().getTable_eleve());
        int id = 1 ;
        String mdp = "01122000" ;
        String nom = "khatib" ;
        System.out.println(id);
        System.out.println(mdp);
        System.out.println(connect.getGestionnaireEleve().getTable_eleve());
        System.out.println(connect.getGestionnaireEleve().getTable_eleve().get(id)) ;
        System.out.println(connect.getGestionnaireEleve().getTable_eleve().get(id).getMdp()) ;
        System.out.println(connect.getGestionnaireEleve().getTable_eleve().get(id).getMdp().equals(mdp)) ;
        System.out.println(connect.getGestionnaireEleve().containsNomMdp(nom, mdp)) ;
        */
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
    }
/*
    //Remplissage des tables :
    public void setTable_prof() {
        gp.getTable_prof().put("Sami", "2703");
        gp.getTable_prof().put("Maha", "0112");
        gp.getTable_prof().put("Hamza", "0608");
    }

    public void setTable_eleve() {
        table_eleve.put("Quentin", "0905");
        table_eleve.put("Isabelle", "1810");
        table_eleve.put("Alois", "2711");
        table_eleve.put("Flavien", "0101");
    }
*/
    // Renvoi 0 si l'id saisi n'est ni dans table_id_prof, ni dans table_id_eleve, 1 s'il est dans table_id_prof, 2 sinon.
    public int check_id(String nom, String mdp) {
        if (connect.getGestionnaireProf().containsNomMdp(nom, mdp)) { return 1 ; }
        if (connect.getGestionnaireEleve().containsNomMdp(nom, mdp)) { return 2 ; }
        return 0 ;
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
