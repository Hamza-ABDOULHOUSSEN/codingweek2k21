package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.rdv.Creneau;
import eu.telecomnancy.javafx.rdv.RendezVous;

import java.util.Hashtable;

public class GestionnaireCreneau {
    private Hashtable<Integer, Creneau> table_creneau = new Hashtable<>();

    public GestionnaireCreneau() {}

    public Hashtable<Integer, Creneau> getTable_creneau() {
        return table_creneau;
    }

    public void setTable_creneau(Creneau creneau) {
        table_creneau.put(creneau.getId_creneau(), creneau);
    }
}
