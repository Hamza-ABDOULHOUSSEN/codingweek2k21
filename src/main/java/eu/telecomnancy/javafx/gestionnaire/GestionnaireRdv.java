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
        String text = "Rendez-vous avec " + e.getPrenom() + " " + e.getNom() + " le " + c.getJour() + " a " + c.getHeure() ;
        return text ;
    }

    public String rdvToString(RendezVous rdv, Professeur p, Creneau c) {
        String text = "Rendez-vous avec " + p.getPrenom() + " " + p.getNom() + " le " + c.getJour() + " a " + c.getHeure() ;
        return text ;
    }

    public void confirmeRDV(RendezVous rdv) throws SQLException {
        rdv.confirme();
        connect.changeRdvStatut(rdv);
        Professeur prof = connect.getGestionnaireProf().getTable_prof().get(rdv.getId_prof());
        Creneau creneau = connect.getGestionnaireCreneau().getTable_creneau().get(rdv.getId_creneau());
        connect.getGestionnairePlanning().addPlaning(prof, creneau);
    }

    public void annuleRDV(RendezVous rdv) throws SQLException {
        rdv.annule();
        connect.changeRdvStatut(rdv);
        Professeur prof = connect.getGestionnaireProf().getTable_prof().get(rdv.getId_prof());
        Creneau creneau = connect.getGestionnaireCreneau().getTable_creneau().get(rdv.getId_creneau());
        connect.getGestionnairePlanning().deletePlanning(prof, creneau);
    }

    public void archiveRDV(RendezVous rdv) throws SQLException {
        rdv.archive();
        connect.changeRdvStatut(rdv);
    }

    public void addRdv(Professeur p, ArrayList<Eleve> eleves, Creneau c, String lieu, String descr, String intitule) throws SQLException {
        max_id_rdv++;
        RendezVous rdv = new RendezVous(max_id_rdv, c.getId_creneau(), p.getId(), lieu, "en attente", descr, intitule, eleves);
        table_rdv.put(max_id_rdv, rdv);
        connect.insertRdv(rdv);
        connect.getGestionnaireRdvEleve().addRdvEleve(rdv);
    }

    public void setMax_id_rdv(int max_id_rdv) {
        this.max_id_rdv = max_id_rdv;
    }

    public void changeRdv(RendezVous rdv, Professeur p, ArrayList<Eleve> eleves, Creneau c, String lieu, String descr, String intitule) throws SQLException {
        rdv.setId_prof(p.getId());
        rdv.setListe_eleve(eleves);
        rdv.setId_creneau(c.getId_creneau());
        rdv.setLieu(lieu);
        rdv.setDescr(descr);
        rdv.setIntitule(intitule);
        connect.changeRdv(rdv);
    }

    public ArrayList<RendezVous> getRdvofProf(Professeur p) {
        ArrayList<RendezVous> liste_rdv = new ArrayList<RendezVous>();
        for (int i : table_rdv.keySet()) {
            RendezVous rdv = table_rdv.get(i);
            if (rdv.getId_prof() == p.getId()) {
                liste_rdv.add(rdv);
            }
        }
        return liste_rdv;
    }
}
