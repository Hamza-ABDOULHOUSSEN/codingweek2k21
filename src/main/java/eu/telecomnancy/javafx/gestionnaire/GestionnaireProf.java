package eu.telecomnancy.javafx.gestionnaire;

import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.ConnectToDb.Connect;

import java.sql.SQLException;
import java.util.Hashtable;

public class GestionnaireProf {

    private int max_id_prof;

    private Hashtable<Integer, Professeur> table_prof = new Hashtable<>();
    private Connect connect;

    public GestionnaireProf(Connect connect) {
        this.connect = connect;
    }

    public void setTable_prof(Professeur prof) {
        table_prof.put(prof.getId(), prof);
    }

    public Hashtable<Integer, Professeur> getTable_prof() {
        return this.table_prof ;
    }

    public Professeur containsNomMdp(String nom, String mdp) {
        for (int i : table_prof.keySet()) {
            if (table_prof.get(i).getNom().equals(nom) && table_prof.get(i).getMdp().equals(mdp)) {
                return table_prof.get(i) ;
            }
        }
        return null ;
    }

    public void addProfesseur(String mdp, String nom, String prenom, String email, String tel, String adresse) throws SQLException {
        max_id_prof++;
        Professeur p = new Professeur(max_id_prof, mdp, nom, prenom, email, tel, adresse);
        table_prof.put(max_id_prof, p);
        connect.insertProfesseur(p);
    }

    public Professeur findProf(String nom, String prenom) {
        for (int i : connect.getGestionnaireProf().getTable_prof().keySet()) {
            if (connect.getGestionnaireProf().getTable_prof().get(i).getNom().equals(nom) && connect.getGestionnaireProf().getTable_prof().get(i).getPrenom().equals(prenom)) {
                return connect.getGestionnaireProf().getTable_prof().get(i) ;
            }
        }
        return null ;
    }

    public void setMax_id_prof(int max_id_prof) {
        this.max_id_prof = max_id_prof;
    }
}
