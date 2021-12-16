package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.ConnectToDb.Connect;
import eu.telecomnancy.javafx.compte.Eleve;
import eu.telecomnancy.javafx.rdv.RendezVous;
import eu.telecomnancy.javafx.rdv.RendezVousEleve;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

public class GestionnaireRdvEleve {
    private ArrayList<RendezVousEleve> table_rdvEleve = new ArrayList<RendezVousEleve>();
    private Connect connect;

    public GestionnaireRdvEleve(Connect connect) {
        this.connect = connect;
    }

    public ArrayList<RendezVousEleve> getTable_rdvEleve() {
        return table_rdvEleve;
    }

    public void setTable_rdv(RendezVousEleve rdvEleve) {
        table_rdvEleve.add(rdvEleve);
    }

    public void addRdvEleve(RendezVous rdv) throws SQLException {
        ArrayList<Eleve> liste_eleve = rdv.getListe_eleve();

        for (Eleve e : liste_eleve) {
            RendezVousEleve rdveleve = new RendezVousEleve(rdv.getId_rdv(), e.getId());
            table_rdvEleve.add(rdveleve);
            connect.insertRdvEleve(rdveleve);
        }
    }
}
