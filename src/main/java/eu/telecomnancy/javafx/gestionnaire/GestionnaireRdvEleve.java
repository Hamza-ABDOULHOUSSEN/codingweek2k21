package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.ConnectToDb.Connect;
import eu.telecomnancy.javafx.rdv.RendezVous;
import eu.telecomnancy.javafx.rdv.RendezVousEleve;

import java.util.Hashtable;

public class GestionnaireRdvEleve {
    private Hashtable<Integer, RendezVousEleve> table_rdvEleve = new Hashtable<>();
    private Connect connect;

    public GestionnaireRdvEleve(Connect connect) {
        this.connect = connect;
    }

    public Hashtable<Integer, RendezVousEleve> getTable_rdvEleve() {
        return table_rdvEleve;
    }

    public void setTable_rdv(RendezVousEleve rdvEleve) {
        table_rdvEleve.put(rdvEleve.getId_rdv(), rdvEleve);
    }
}
