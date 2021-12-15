package eu.telecomnancy.javafx.rdv;

public class Creneau {

    private int id_creneau ;
    private String jour ;
    private String heure ;

    public Creneau (int id_creneau, String jour, String heure) {
        this.id_creneau = id_creneau ;
        this.jour = jour ;
        this.heure = heure ;
    }

    public Integer getId_creneau() {
        return this.id_creneau ;
    }

    public String getJour() { return this.jour ; }

    public String getHeure() { return this.heure ; }
}
