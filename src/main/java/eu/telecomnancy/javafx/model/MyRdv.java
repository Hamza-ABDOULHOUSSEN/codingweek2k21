package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.Observateur.SujetObserve;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Hashtable;

public class MyRdv extends SujetObserve {

    private Stage stage;
    private Hashtable<String, String> table_prof = new Hashtable<>();
    private Hashtable<String, String> table_eleve = new Hashtable<>();

    private String Accueil_nom ;
    private String Accueil_mdp ;
    private String erreur;

    public MyRdv(Stage stage) {
        this.stage = stage ;
        this.Accueil_nom = "Nom" ;
        this.Accueil_mdp = "Mot de passe" ;

        setTable_prof() ;
        setTable_eleve() ;
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
    }

    //Remplissage des tables :
    public void setTable_prof() {
        table_prof.put("Sami", "2703");
        table_prof.put("Maha", "0112");
        table_prof.put("Hamza", "0608");
    }

    public void setTable_eleve() {
        table_eleve.put("Quentin", "0905");
        table_eleve.put("Isabelle", "1810");
        table_eleve.put("Alois", "2711");
        table_eleve.put("Flavien", "0101");
    }

    // Renvoi 0 si l'id saisi n'est ni dans table_id_prof, ni dans table_id_eleve, 1 s'il est dans table_id_prof, 2 sinon.
    public int check_id(String id, String mdp) {
        if (table_prof.containsKey(id) && table_prof.get(id).equals(mdp)) {
            return 1 ;
        }
        if (table_eleve.containsKey(id) && table_eleve.get(id).equals(mdp)) {
            return 2 ;
        }
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
}
