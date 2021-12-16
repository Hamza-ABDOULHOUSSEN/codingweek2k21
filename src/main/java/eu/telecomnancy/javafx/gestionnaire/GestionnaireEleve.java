package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.ConnectToDb.Connect;
import eu.telecomnancy.javafx.compte.Eleve;
import eu.telecomnancy.javafx.compte.Professeur;

import java.sql.SQLException;
import java.util.Hashtable;

public class GestionnaireEleve {

    private int max_id_eleve;

    private Hashtable<Integer, Eleve> table_eleve = new Hashtable<>();
    private Connect connect;

    public GestionnaireEleve(Connect connect) {
        this.connect = connect;
    }

    public void setTable_eleve(Eleve eleve) {
        table_eleve.put(eleve.getId(), eleve);
    }

    public Hashtable<Integer, Eleve> getTable_eleve() {
        return table_eleve;
    }

    public Eleve containsNomMdp(String nom, String mdp) {
        for (int i : table_eleve.keySet()) {
            if (table_eleve.get(i).getNom().equals(nom) && table_eleve.get(i).getMdp().equals(mdp)) {
                return table_eleve.get(i) ;
            }
        }
        return null ;
    }

    public void setMax_id_eleve(int max_id_eleve) {
        this.max_id_eleve = max_id_eleve;
    }
}
