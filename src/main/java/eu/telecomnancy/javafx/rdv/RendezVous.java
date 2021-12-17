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
    private String descr ;
    private String intitule ;

    private RdvState rdvstate = new Rdv_en_attente();
    private ArrayList<Eleve> liste_eleve = new ArrayList<Eleve>();

    public RendezVous(int id_rdv, int id_creneau, int id_prof, String lieu, String etat, String descr, String intitule, ArrayList<Eleve> liste_eleve) {
        this.id_rdv = id_rdv;
        this.id_creneau = id_creneau;
        this.id_prof = id_prof;
        this.lieu = lieu;
        this.etat = etat;
        this.descr = descr;
        this.intitule = intitule ;
        this.liste_eleve = liste_eleve;

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
        etat = "confirme";
    }

    public void annule() {
        rdvstate.annule();
        rdvstate = new Rdv_annule();
        etat = "annule";
    }

    public void archive() {
        rdvstate.archive();
        rdvstate = new Rdv_archive();
        etat = "archive";
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

    public String getDescr() {
        return descr;
    }

    public String getIntitule() { return intitule ; }

    // SETTERS

    public void setId_rdv(int id_rdv) {
        this.id_rdv = id_rdv;
    }

    public void setId_creneau(int id_creneau) {
        this.id_creneau = id_creneau;
    }

    public void setId_prof(int id_prof) {
        this.id_prof = id_prof;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setRdvstate(RdvState rdvstate) {
        this.rdvstate = rdvstate;
    }

    public void setListe_eleve(ArrayList<Eleve> liste_eleve) {
        this.liste_eleve = liste_eleve;
    }
}
