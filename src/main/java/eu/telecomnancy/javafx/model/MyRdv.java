package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.Observateur.SujetObserve;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Hashtable;

public class MyRdv extends SujetObserve {

    private Stage stage;
    private ArrayList<String> table_id_prof ;
    private ArrayList<String> table_id_eleve ;
    private ArrayList<String> table_mdp_prof ;
    private ArrayList<String> table_mdp_eleve ;
    private String Accueil_nom ;
    private String Accueil_mdp ;

    public MyRdv(Stage stage) {
        this.stage = stage ;
        this.table_id_prof = new ArrayList<String>() ;
        this.table_id_eleve = new ArrayList<String>() ;
        this.table_mdp_prof = new ArrayList<String>() ;
        this.table_mdp_eleve = new ArrayList<String>() ;
        this.Accueil_nom = "Nom" ;
        this.Accueil_mdp = "Mot de passe" ;

        setTable_id_prof() ;
        setTable_id_eleve() ;
        setTable_mdp_prof();
        setTable_mdp_eleve();
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
    }

    //Remplissage des tables :
    public void setTable_id_prof() {
        this.table_id_prof.add("Sami") ;
        this.table_id_prof.add("Maha") ;
        this.table_id_prof.add("Hamza") ;
    }

    public void setTable_id_eleve() {
        this.table_id_eleve.add("Quentin") ;
        this.table_id_eleve.add("Isabelle") ;
        this.table_id_eleve.add("Alois") ;
        this.table_id_eleve.add("Flavien") ;
    }

    public void setTable_mdp_prof() {
        this.table_mdp_prof.add("2703") ;
        this.table_mdp_prof.add("0112") ;
        this.table_mdp_prof.add("0608") ;
    }

    public void setTable_mdp_eleve() {
        this.table_mdp_eleve.add("0905") ;
        this.table_mdp_eleve.add("1810") ;
        this.table_mdp_eleve.add("2711") ;
        this.table_mdp_eleve.add("0101") ;
    }

    // Renvoi 0 si l'id saisi n'est ni dans table_id_prof, ni dans table_id_eleve, 1 s'il est dans table_id_prof, 2 sinon.
    public int check_id(String id) {
        if (this.table_id_prof.contains(id)) {
            return 1 ;
        }
        if (this.table_id_eleve.contains(id)) {
            return 2 ;
        }
        return 0 ;
    }
    // Renvoi 0 si le mdp saisi n'est ni dans table_mdp_prof, ni dans table_mdp_eleve, 1 s'il est dans table_mdp_prof, 2 sinon.
    public int check_mdp(String mdp) {
        if (this.table_mdp_prof.contains(mdp)) {
            return 1 ;
        }
        if (this.table_mdp_eleve.contains(mdp)) {
            return 2 ;
        }
        return 0 ;
    }

    public String getAccueil_nom() {
        return Accueil_nom ;
    }

    public String getAccueil_mdp() {
        return Accueil_mdp ;
    }

    public void setAccueil_nom_mdp(String nom, String mdp) {
        Accueil_nom = nom ;
        Accueil_mdp = mdp ;
        notifierObservateurs();
    }
}
