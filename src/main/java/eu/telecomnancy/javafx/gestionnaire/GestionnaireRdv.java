package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.rdv.RendezVous;

import java.util.Hashtable;

public class GestionnaireRdv {
    private Hashtable<Integer, RendezVous> table_rdv = new Hashtable<>();

    public GestionnaireRdv() {}

    public void setTable_rdv(RendezVous rdv) {
        table_rdv.put(rdv.getId_rdv(), rdv);
    }

    public Hashtable<Integer, RendezVous> getTable_rdv() {
        return table_rdv;
    }
}
