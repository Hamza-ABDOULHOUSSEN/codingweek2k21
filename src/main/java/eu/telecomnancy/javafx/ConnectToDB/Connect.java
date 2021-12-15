package eu.telecomnancy.javafx.ConnectToDB;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import eu.telecomnancy.javafx.compte.* ;
import eu.telecomnancy.javafx.gestionnaire.*;
import eu.telecomnancy.javafx.rdv.Creneau;
import eu.telecomnancy.javafx.rdv.RendezVous;
import eu.telecomnancy.javafx.rdv.RendezVousEleve;

public class Connect {
    //"jdbc:sqlite:C:/Users/Maha/project-grp20/BaseDeDonnees/CodingW.db"

    private GestionnaireProf gp ;
    private GestionnaireEleve ge ;
    private GestionnairePlanning gpl ;
    private GestionnaireRdv gr ;
    private GestionnaireRdvEleve gre ;
    private GestionnaireCreneau gc ;



    public Connect() {
        String jdbcUrl = "jdbc:sqlite:BaseDeDonnees/CodingW.db";
        try {
            this.gp = new GestionnaireProf() ;
            this.ge = new GestionnaireEleve() ;
            this.gpl = new GestionnairePlanning() ;
            this.gr = new GestionnaireRdv() ;
            this.gre = new GestionnaireRdvEleve() ;
            this.gc = new GestionnaireCreneau() ;
            Connection connection = DriverManager.getConnection(jdbcUrl);
            Statement statement = connection.createStatement();
            initDB(connection, statement) ;
        }
        catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }
    /*
    public static void main (String[] args) {
        String jdbcUrl = "jdbc:sqlite:BaseDeDonnees/CodingW.db";
        try {
            this.gp = new GestionnaireProf() ;
            Connection connection = DriverManager.getConnection(jdbcUrl);
            Statement statement = connection.createStatement();
            initDB(connection, statement) ;
        }
        catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }
     */

    public void initDB(Connection connection, Statement statement) throws SQLException {
        rsetToProf(connection, statement) ;
        rsetToEleve(connection, statement) ;
        rsetToCreneau(connection, statement) ;
        rsetToPlanning(connection, statement) ;
        rsetToRdv(connection, statement) ;
        rsetToRdvEleve(connection, statement);
    }

    public void rsetToProf(Connection connection, Statement statement) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM Enseignant");
        while(result.next()) {
            this.gp.setTable_prof(new Professeur(result.getInt("id_enseignant"), result.getString("mdp_enseignant"), result.getString("nom"), result.getString("prenom"), result.getString("email"), result.getString("tel"), result.getString("adresse")));
        }
    }

    public void rsetToEleve(Connection connection, Statement statement) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM Eleve");
        while(result.next()) {
            this.ge.setTable_eleve(new Eleve(result.getInt("id_eleve"), result.getString("mdp_eleve"), result.getString("nom"), result.getString("prenom"), result.getString("email"), result.getString("tel"), result.getString("adresse")));
        }
    }

    public void rsetToCreneau(Connection connection, Statement statement) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM Creneau");
        while(result.next()) {
            this.gc.setTable_creneau(new Creneau(result.getInt("id_creneau"), result.getString("jour"), result.getString("heure")));
        }
    }

    public void rsetToPlanning(Connection connection, Statement statement) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM Planning");
        while(result.next()) {
            this.gpl.setTable_planning(new Planning(result.getInt("id_creneau"), result.getInt("id_enseignant"))); ;
        }
    }

    public void rsetToRdv(Connection connection, Statement statement)throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM RendezVous");
        while (result.next()) {
            this.gr.setTable_rdv(new RendezVous(result.getInt("id_rdv"), result.getInt("id_creneau"), result.getInt("id_enseignant"), result.getString("lieu"), result.getString("etat")));
        }
    }

    public void rsetToRdvEleve(Connection connection, Statement statement)throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM RendezVous");
        while (result.next()) {
            this.gre.setTable_rdv(new RendezVousEleve(result.getInt("id_rdv"), result.getInt("id_eleve")));
        }
    }
}