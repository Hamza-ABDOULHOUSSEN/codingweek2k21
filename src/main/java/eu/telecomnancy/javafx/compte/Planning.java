package eu.telecomnancy.javafx.compte;

import eu.telecomnancy.javafx.rdv.RendezVous;

public class Planning {
    private int id_creneau ;
    private int id_enseignant ;

    public Planning(int id_creneau, int id_enseignant) {
        this.id_creneau = id_creneau ;
        this.id_enseignant = id_enseignant ;
    }

    public void addRdvToPlanning(RendezVous rdv) {
    }

    public int getId_planning() {
        return this.id_creneau ;
    }
}
