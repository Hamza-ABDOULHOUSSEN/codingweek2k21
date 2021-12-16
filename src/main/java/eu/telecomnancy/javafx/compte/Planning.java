package eu.telecomnancy.javafx.compte;

import eu.telecomnancy.javafx.rdv.RendezVous;

public class Planning {
    private int id_creneau ;
    private int id_enseignant ;

    public Planning(int id_creneau, int id_enseignant) {
        this.id_creneau = id_creneau ;
        this.id_enseignant = id_enseignant ;
    }

    //GETTERS
    public int getId_creneau() {
        return id_creneau;
    }

    public int getId_enseignant() {
        return id_enseignant;
    }
}
