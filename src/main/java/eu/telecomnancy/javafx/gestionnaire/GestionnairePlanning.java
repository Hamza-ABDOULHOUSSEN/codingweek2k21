package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.ConnectToDb.Connect;
import eu.telecomnancy.javafx.compte.Planning;
import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.rdv.Creneau;
import eu.telecomnancy.javafx.rdv.RendezVous;

import java.sql.SQLException;
import java.util.ArrayList;

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

    public void deletePlanning(Professeur p, Creneau c) throws SQLException {
        connect.deletePlanning(c, p);
    }

    public Boolean estDispo(Professeur p, Creneau c) {
        Boolean b = true;
        for (Planning plan : table_planning) {
            if (plan.getId_enseignant() == p.getId() && plan.getId_creneau() == c.getId_creneau()) {
                b = false;
            }
        }
        return b;
     }

    public Boolean contientRdv(Professeur p, Creneau deb, Creneau fin) {
        int id_deb = deb.getId_creneau();
        int id_fin = fin.getId_creneau();

        ArrayList<RendezVous> liste_rdv = connect.getGestionnaireRdv().getRdvofProf(p);
        for (RendezVous rdv : liste_rdv) {
            int id_creneau = rdv.getId_creneau();
            if (id_deb <= id_creneau && id_creneau <= id_fin) {
                return true;
            }
        }
        return false;
    }
    
    public Planning findPlanning(Professeur p, Creneau c) {
        for (Planning plan : table_planning) {
            if (plan.getId_enseignant() == p.getId() && plan.getId_creneau() == c.getId_creneau()) {
                return plan;
            }
        }
        return null;
    }

    public void RendreDispo(Professeur p, Creneau deb, Creneau fin) throws SQLException {
        int id_deb = deb.getId_creneau();
        int id_fin = fin.getId_creneau();

        for (int i = id_deb; i < id_fin + 1; i++) {
            Creneau c = connect.getGestionnaireCreneau().findCreneau(i);
            Planning plan = findPlanning(p, c);
            if (plan != null) {
                table_planning.remove(plan);
                connect.deletePlanning(c, p);
            }
        }
    }

    public void RendreIndispo(Professeur p, Creneau deb, Creneau fin) throws SQLException {
        int id_deb = deb.getId_creneau();
        int id_fin = fin.getId_creneau();

        for (int i = id_deb; i < id_fin + 1; i++) {
            Creneau c = connect.getGestionnaireCreneau().findCreneau(i);
            Planning plan = findPlanning(p, c);
            if (plan == null) {
                Planning newP = new Planning(c.getId_creneau(), p.getId());
                table_planning.add(newP);
                connect.insertPlanning(c, p);
            }
        }
    }

    public ArrayList<Creneau> getAllCreneauProf(Professeur p) {
        ArrayList<Creneau> liste_creneau = new ArrayList<Creneau>();
        for (Planning plan : table_planning) {
            if (plan.getId_enseignant() == p.getId()) {
                int c_id = plan.getId_creneau();
                Creneau c = connect.getGestionnaireCreneau().getTable_creneau().get(c_id);
                liste_creneau.add(c);
            }
        }
        return liste_creneau;
    }
}
