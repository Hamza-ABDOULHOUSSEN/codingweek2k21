package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.ConnectToDB.Connect;
import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.model.MyRdv;
import eu.telecomnancy.javafx.rdv.RendezVous;

import java.util.Hashtable;

public class GestionnaireRdv {
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

    public String rdvToString(RendezVous rdv) {
        String text = "Rendez-vous avec " + connect.getGestionnaireProf().getTable_prof().get(rdv.getId_prof()).getPrenom() + " " + connect.getGestionnaireProf().getTable_prof().get(rdv.getId_prof()).getNom() + " le " + connect.getGestionnaireCreneau().getTable_creneau().get(rdv.getId_creneau()).getJour() + " à " + connect.getGestionnaireCreneau().getTable_creneau().get(rdv.getId_creneau()).getHeure() + " au " + rdv.getLieu() ;
        return text ;
    }

    public void confirmeRDV(RendezVous rdv) {
        rdv.confirme();
    }

    public void annuleRDV(RendezVous rdv) {
        rdv.annule();
    }

    public void archiveRDV(RendezVous rdv) {
        rdv.archive();
    }

}
