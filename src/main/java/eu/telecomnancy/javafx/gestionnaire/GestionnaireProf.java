package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.compte.Professeur;

import java.util.Hashtable;

public class GestionnaireProf {
    private Hashtable<Integer, Professeur> table_prof = new Hashtable<>();

    public GestionnaireProf() {}

    public void setTable_prof(Professeur prof) {
        table_prof.put(prof.getId_professeur(), prof);
    }

    public Hashtable<Integer, Professeur> getTable_prof() {
        return this.table_prof ;
    }
}
