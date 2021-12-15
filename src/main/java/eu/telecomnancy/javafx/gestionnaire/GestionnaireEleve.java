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
}
