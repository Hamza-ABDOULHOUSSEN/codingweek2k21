package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.compte.Eleve;
import eu.telecomnancy.javafx.compte.Professeur;

import java.util.Hashtable;

public class GestionnaireEleve {
    private Hashtable<Integer, Eleve> table_eleve = new Hashtable<>();

    public GestionnaireEleve() {}

    public void setTable_eleve(Eleve eleve) {
        table_eleve.put(eleve.getId_eleve(), eleve);
    }

    public Hashtable<Integer, Eleve> getTable_eleve() {
        return table_eleve;
    }

    public boolean containsNomMdp(String nom, String mdp) {
        for (int i : table_eleve.keySet()) {
            if (table_eleve.get(i).getNom().equals(nom) && table_eleve.get(i).getMdp().equals(mdp)) {
                return true;
            }
        }
        return false;
    }
}
