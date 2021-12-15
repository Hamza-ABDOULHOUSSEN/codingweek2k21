package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.compte.Planning;
import eu.telecomnancy.javafx.rdv.RendezVous;

import java.util.Hashtable;

public class GestionnairePlanning {
    private Hashtable<Integer, Planning> table_planning = new Hashtable<>();

    public GestionnairePlanning() {}

    public Hashtable<Integer, Planning> getTable_planning() {
        return table_planning;
    }

    public void setTable_planning(Planning planning) {
        table_planning.put(planning.getId_planning(), planning);
    }
}
