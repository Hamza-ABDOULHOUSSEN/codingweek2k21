package eu.telecomnancy.javafx.rdv;

public class RendezVousEleve {
    private int id_rdv ;
    private int id_eleve ;

    public RendezVousEleve(int id_rdv, int id_eleve) {
        this.id_rdv = id_rdv;
        this.id_eleve = id_eleve;
    }
    public int getId_rdv() {
        return id_rdv;
    }

    public int getId_eleve() {
        return id_eleve;
    }
}
