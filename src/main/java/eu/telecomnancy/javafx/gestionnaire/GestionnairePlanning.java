package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.ConnectToDb.Connect;
import eu.telecomnancy.javafx.compte.Planning;
import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.rdv.Creneau;
import eu.telecomnancy.javafx.rdv.RendezVous;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

public class GestionnairePlanning {

    private ArrayList<Planning> table_planning = new ArrayList<Planning>();
    private Connect connect;

    public GestionnairePlanning(Connect connect) {
        this.connect = connect;
    }

    public ArrayList<Planning> getTable_planning() {
        return table_planning;
    }

    public void setTable_planning(Planning planning) {
        table_planning.add(planning);
    }

    public void addPlaning(Professeur p, Creneau c) throws SQLException {
        Planning planning = new Planning(c.getId_creneau(), p.getId());
        table_planning.add(planning);
        connect.insertPlanning(c, p);
    }
}
