package eu.telecomnancy.javafx.rdv;

import eu.telecomnancy.javafx.compte.Eleve;
import eu.telecomnancy.javafx.compte.Professeur;

import java.util.ArrayList;

public class RendezVous {
    private int id_rdv;
    private String date;

    private String horaire;

    private RdvState rdvstate = new Rdv_en_attente();

    private ArrayList<Eleve> liste_eleve = new ArrayList<Eleve>();
    private Professeur prof;

    private String lieu;

    public RendezVous(int id_rdv, String date, String horaire, ArrayList<Eleve> liste_eleve, Professeur prof, String lieu) {
        this.id_rdv = id_rdv;
        this.date = date;
        this.horaire = horaire;
        this.liste_eleve = liste_eleve;
        this.prof = prof;
        this.lieu = lieu;
    }

    public void confirme() {
        rdvstate.confirme();
        rdvstate = new Rdv_confirme();
    }

    public void annule() {
        rdvstate.annule();
        rdvstate = new Rdv_annule();
    }

    public void archive() {
        rdvstate.archive();
        rdvstate = new Rdv_archive();
    }

    //GETTERS
    public int getId_rdv() {
        return id_rdv;
    }

    public String getDate() {
        return date;
    }

    public String getHoraire() {
        return horaire;
    }

    public RdvState getRdvstate() {
        return rdvstate;
    }

    public ArrayList<Eleve> getListe_eleve() {
        return liste_eleve;
    }

    public Professeur getProf() {
        return prof;
    }

    public String getLieu() {
        return lieu;
    }

}
