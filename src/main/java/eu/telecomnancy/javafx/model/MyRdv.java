package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.ConnectToDb.Connect ;
import eu.telecomnancy.javafx.Observateur.SujetObserve;
import eu.telecomnancy.javafx.compte.Eleve;
import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.gestionnaire.GestionnaireEleve;
import eu.telecomnancy.javafx.gestionnaire.GestionnaireProf;
import eu.telecomnancy.javafx.rdv.RendezVous;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

public class MyRdv extends SujetObserve {

    private Stage stage;
    private Connect connect = new Connect() ;

    private String Accueil_nom ;
    private String Accueil_mdp ;
    private String erreur;

    private Professeur prof ;
    private Eleve eleve ;
    private ArrayList<Eleve> listEleveSelect = new ArrayList<Eleve>() ;
    private ArrayList<Eleve> listEleveVbox = new ArrayList<Eleve>() ;

    // Affiche RDV
    private ArrayList<RendezVous> Rdv_en_attente = new ArrayList<RendezVous>();
    private ArrayList<RendezVous> Rdv_confirme = new ArrayList<RendezVous>();
    private ArrayList<RendezVous> Rdv_archive = new ArrayList<RendezVous>();

    public MyRdv(Stage stage) throws URISyntaxException, IOException {
        this.stage = stage ;
        this.Accueil_nom = "Nom" ;
        this.Accueil_mdp = "Mot de passe" ;
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
    }

    // Renvoi 0 si l'id saisi n'est ni dans table_id_prof, ni dans table_id_eleve, 1 s'il est dans table_id_prof, 2 sinon.
    public int check_id(String nom, String mdp) {
        Professeur p = connect.getGestionnaireProf().containsNomMdp(nom, mdp) ;
        if (p != null) {
            this.prof = p ;
            return 1 ; }
        Eleve e = connect.getGestionnaireEleve().containsNomMdp(nom, mdp) ;
        if (e != null) {
            this.eleve = e ;
            return 2 ; }
        return 0 ;
    }

    public void getAllRdv(Professeur prof, String etat) {
        Hashtable<Integer, RendezVous> table_rdv = connect.getGestionnaireRdv().getTable_rdv() ;

        if (etat.equals("en attente")) {
            Rdv_en_attente = new ArrayList<RendezVous>();
            for (int i : table_rdv.keySet()) {
                if (table_rdv.get(i).getId_prof() == prof.getId() && table_rdv.get(i).getEtat().equals(etat)) {
                    Rdv_en_attente.add(table_rdv.get(i));
                }
            }
        }
        else if (etat.equals("confirme")) {
            Rdv_confirme = new ArrayList<RendezVous>();
            for (int i : table_rdv.keySet()) {
                if (table_rdv.get(i).getId_prof() == prof.getId() && table_rdv.get(i).getEtat().equals(etat)) {
                    Rdv_confirme.add(table_rdv.get(i));
                }
            }
        }
        else if (etat.equals("archive")) {
            Rdv_archive = new ArrayList<RendezVous>();
            for (int i : table_rdv.keySet()) {
                if (table_rdv.get(i).getId_prof() == prof.getId() && table_rdv.get(i).getEtat().equals(etat)) {
                    Rdv_archive.add(table_rdv.get(i));
                }
            }
        }
    }

    public void getAllRdv(Eleve eleve, String etat) {
        Hashtable<Integer, RendezVous> table_rdv = connect.getGestionnaireRdv().getTable_rdv() ;

        if (etat.equals("en attente")) {
            Rdv_en_attente = new ArrayList<RendezVous>();
            for (int i : table_rdv.keySet()) {
                if (table_rdv.get(i).getListe_eleve().contains(eleve) && table_rdv.get(i).getEtat().equals(etat)) {
                    Rdv_en_attente.add(table_rdv.get(i));
                }
            }
        }
        else if (etat.equals("confirme")) {
            Rdv_confirme = new ArrayList<RendezVous>();
            for (int i : table_rdv.keySet()) {
                if (table_rdv.get(i).getListe_eleve().contains(eleve) && table_rdv.get(i).getEtat().equals(etat)) {
                    Rdv_confirme.add(table_rdv.get(i));
                }
            }
        }
        else if (etat.equals("archive")) {
            Rdv_archive = new ArrayList<RendezVous>();
            for (int i : table_rdv.keySet()) {
                if (table_rdv.get(i).getListe_eleve().contains(eleve) && table_rdv.get(i).getEtat().equals(etat)) {
                    Rdv_archive.add(table_rdv.get(i));
                }
            }
        }

    }

    public void clearRDV(String etat) {
        if (etat.equals("en attente")) {
            Rdv_en_attente = new ArrayList<RendezVous>();
        }
        else if (etat.equals("confirme")) {
            Rdv_confirme = new ArrayList<RendezVous>();
        }
        else if (etat.equals("archive")) {
            Rdv_archive = new ArrayList<RendezVous>();
        }
        notifierObservateurs();
    }

    public void afficheRDV(String compte, String etat) {
        if (compte.equals("prof")) {
            getAllRdv(prof, etat);
        }
        else {
            getAllRdv(eleve, etat);
        }
        notifierObservateurs();
    }

    public void selecteleve(Eleve e) {
        listEleveSelect.remove(e);
        System.out.println(listEleveSelect.size());
        listEleveVbox.add(e);
        notifierObservateurs();
    }

    public void initchoixeleve() {
        Hashtable<Integer, Eleve> table_eleve = connect.getGestionnaireEleve().getTable_eleve();
        listEleveSelect = new ArrayList<Eleve>(table_eleve.values());
        selecteleve(eleve);
    }

    // GETTERS
    public String getAccueil_nom() {
        return Accueil_nom ;
    }

    public String getAccueil_mdp() {
        return Accueil_mdp ;
    }

    public String getErreur() {
        return erreur ;
    }

    public Professeur getProf() {
        return this.prof ;
    }

    public Eleve getEleve() {
        return this.eleve ;
    }

    public ArrayList<Eleve> getListEleve_select() {
        return listEleveSelect;
    }

    public ArrayList<Eleve> getListEleve_vbox() {
        return listEleveVbox;
    }

    public ArrayList<RendezVous> getRdv_en_attente() {
        return Rdv_en_attente;
    }

    public ArrayList<RendezVous> getRdv_confirme() {
        return Rdv_confirme;
    }

    public ArrayList<RendezVous> getRdv_archive() {
        return Rdv_archive;
    }

    public void updatestatus(RendezVous rdv, String etat) throws SQLException {
        if (etat.equals("confirme")) {
            rdv.confirme();
            connect.changeRdvStatut(rdv);
        }
        else if (etat.equals("annule")) {
            rdv.annule();
            connect.changeRdvStatut(rdv);
        }
    }


    // SETTERS
    public void setAccueil_nom_mdp(String nom, String mdp) {
        Accueil_nom = nom ;
        Accueil_mdp = mdp ;
        notifierObservateurs();
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
        notifierObservateurs();
    }

    public Connect getConnect() { return connect ; }

    public void setListEleveSelect(ArrayList<Eleve> listEleveSelect) {
        this.listEleveSelect = listEleveSelect;
    }

    public void setListEleveVbox(ArrayList<Eleve> listEleveVbox) {
        this.listEleveVbox = listEleveVbox;
    }
}
