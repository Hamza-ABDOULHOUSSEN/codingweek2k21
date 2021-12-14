package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.rdv.RendezVous;

import java.util.ArrayList;

public class RendezVousGestionnaire {

    ArrayList<RendezVous> rdv_list = new ArrayList<>();

    void RendezVousGestionnaire() {
    }

    public void addRdv(RendezVous rdv) {
        rdv_list.add(rdv);
    }

    public void confirmeRDV(RendezVous rdv) {
        rdv.confirme();
    }

    public void annuleRDV(RendezVous rdv) {
        rdv.annule();
    }

    public void archiveRDV(RendezVous rdv) {
        rdv.archive();
    }

    //GETTERS
    public ArrayList<RendezVous> getRdv_list() {
        return rdv_list;
    }
}
