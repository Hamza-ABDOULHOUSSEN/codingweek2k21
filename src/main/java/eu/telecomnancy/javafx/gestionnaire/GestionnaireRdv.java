package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.ConnectToDb.Connect;
import eu.telecomnancy.javafx.compte.Eleve;
import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.model.MyRdv;
import eu.telecomnancy.javafx.rdv.Creneau;
import eu.telecomnancy.javafx.rdv.RendezVous;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

public class GestionnaireRdv {
    private int max_id_rdv;

    private Hashtable<Integer, RendezVous> table_rdv = new Hashtable<>();
    private Connect connect ;

    public GestionnaireRdv(Connect connect) {
        this.connect = connect ;
    }

    public void setTable_rdv(RendezVous rdv) {
        table_rdv.put(rdv.getId_rdv(), rdv);
    }

    public Hashtable<Integer, RendezVous> getTable_rdv() {
        return table_rdv;
    }

    public String rdvToString(RendezVous rdv, Eleve e, Creneau c) {
        String text = "Rendez-vous avec " + e.getPrenom() + " " + e.getNom() + " le " + c.getJour() + " a " + c.getHeure() + " au " + rdv.getLieu() ;
        return text ;
    }

    public String rdvToString(RendezVous rdv, Professeur p, Creneau c) {
        String text = "Rendez-vous avec " + p.getPrenom() + " " + p.getNom() + " le " + c.getJour() + " a " + c.getHeure() + " au " + rdv.getLieu() ;
        return text ;
    }

    public void confirmeRDV(RendezVous rdv) throws SQLException {
        rdv.confirme();
        connect.changeRdvStatut(rdv);
    }

    public void annuleRDV(RendezVous rdv) throws SQLException {
        rdv.annule();
        connect.changeRdvStatut(rdv);
    }

    public void archiveRDV(RendezVous rdv) throws SQLException {
        rdv.archive();
        connect.changeRdvStatut(rdv);
    }

    public void addRdv(Professeur p, ArrayList<Eleve> eleves, Creneau c, String lieu, String descr, String Intitule) throws SQLException {
        max_id_rdv++;
        RendezVous rdv = new RendezVous(max_id_rdv, c.getId_creneau(), p.getId(), lieu, "en attente", descr, eleves);
        table_rdv.put(max_id_rdv, rdv);
        connect.insertRdv(rdv);
        connect.getGestionnaireRdvEleve().addRdvEleve(rdv);
    }

    public void setMax_id_rdv(int max_id_rdv) {
        this.max_id_rdv = max_id_rdv;
    }
}
