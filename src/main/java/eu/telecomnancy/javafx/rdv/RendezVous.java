package eu.telecomnancy.javafx.rdv;

import java.util.ArrayList;

public class RendezVous {
    private int id_rdv;
    private String date;
    private String horaire;

    private RdvState rdvstate = new Rdv_en_attente();

    private ArrayList<Eleve> liste_eleve = new ArrayList<Eleve>();
    private Professeur prof;

    private Lieu lieu;

    public RendezVous(int id_rdv, String date, String horaire, ArrayList<Eleve> liste_eleve, Professeur prof, Lieu lieu) {
        this.id_rdv = id_rdv;
        this.date = date;
        this.horaire = horaire;
        this.liste_eleve = liste_eleve;
        this.prof = prof;
        this.lieu = lieu;
    }

    public void confirme() {
        rdvstate.confirme();
    }

    public void annule() {
        rdvstate.annule();
    }

    public void archive() {
        rdvstate.archive();
    }

}
