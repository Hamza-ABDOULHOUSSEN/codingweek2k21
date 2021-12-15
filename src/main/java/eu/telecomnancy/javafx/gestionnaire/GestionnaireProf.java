package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.compte.Professeur;

import java.util.Hashtable;

public class GestionnaireProf {
    private Hashtable<Integer, Professeur> table_prof = new Hashtable<>();

    public GestionnaireProf() {}

    public void setTable_prof(Professeur prof) {
        table_prof.put(prof.getId(), prof);
    }

    public Hashtable<Integer, Professeur> getTable_prof() {
        return this.table_prof ;
    }

    public Professeur containsNomMdp(String nom, String mdp) {
        for (int i : table_prof.keySet()) {
            if (table_prof.get(i).getNom().equals(nom) && table_prof.get(i).getMdp().equals(mdp)) {
                return table_prof.get(i) ;
            }
        }
        return null ;
    }
}
