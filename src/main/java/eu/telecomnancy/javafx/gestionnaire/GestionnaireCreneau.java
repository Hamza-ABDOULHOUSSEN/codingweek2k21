package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.ConnectToDb.Connect;
import eu.telecomnancy.javafx.rdv.Creneau;
import eu.telecomnancy.javafx.rdv.RendezVous;

import java.util.Hashtable;

public class GestionnaireCreneau {
    private Hashtable<Integer, Creneau> table_creneau = new Hashtable<>();
    private Connect connect;

    public GestionnaireCreneau(Connect connect) {
        this.connect = connect;
    }

    public Hashtable<Integer, Creneau> getTable_creneau() {
        return table_creneau;
    }

    public void setTable_creneau(Creneau creneau) {
        table_creneau.put(creneau.getId_creneau(), creneau);
    }

    public Creneau findCreneau(String jour, String heure) {

        for (int i : table_creneau.keySet()) {
            if (table_creneau.get(i).getJour().equals(jour) && connect.getGestionnaireCreneau().getTable_creneau().get(i).getHeure().equals(heure)) {
                return connect.getGestionnaireCreneau().getTable_creneau().get(i) ;
            }
        }
        return null ;
    }

    public Creneau findCreneau(int id_creneau) {
        return table_creneau.get(id_creneau);
    }
}
