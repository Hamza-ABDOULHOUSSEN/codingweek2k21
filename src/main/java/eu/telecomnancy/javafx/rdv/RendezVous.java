package eu.telecomnancy.javafx.rdv;

import eu.telecomnancy.javafx.compte.Eleve;
import eu.telecomnancy.javafx.compte.Professeur;

import java.util.ArrayList;

public class RendezVous {
    private int id_rdv;
    private int id_creneau;
    private int id_prof ;
    private String lieu ;
    private String etat ;

    private RdvState rdvstate = new Rdv_en_attente();
    private ArrayList<Eleve> liste_eleve = new ArrayList<Eleve>();

    public RendezVous(int id_rdv, int id_creneau, int id_prof, String lieu, String etat) {
        this.id_rdv = id_rdv;
        this.id_creneau = id_creneau;
        this.id_prof = id_prof;
        this.lieu = lieu;
        this.etat = etat;
        if (etat != null)
        if (etat.equals("confirme")) {
            rdvstate = new Rdv_confirme();
        }
        else if (etat.equals("annule")) {
            rdvstate = new Rdv_annule();
        }
        else if (etat.equals("archive")) {
            rdvstate = new Rdv_archive();
        }
        else if (etat.equals("en attente")) {
            rdvstate = new Rdv_en_attente();
        }
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

    public RdvState getRdvstate() {
        return rdvstate;
    }

    public ArrayList<Eleve> getListe_eleve() {
        return liste_eleve;
    }

    public String getLieu() {
        return lieu;
    }

    public String getEtat() { return etat ; }

    public int getId_creneau() { return id_creneau ; }

    public int getId_prof() { return id_prof ; }

}
