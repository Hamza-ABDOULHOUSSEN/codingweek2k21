package eu.telecomnancy.javafx;

import eu.telecomnancy.javafx.ConnectToDB.Connect;
import eu.telecomnancy.javafx.compte.Eleve;
import eu.telecomnancy.javafx.compte.Planning;
import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.gestionnaire.*;
import eu.telecomnancy.javafx.rdv.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import eu.telecomnancy.javafx.compte.* ;
import eu.telecomnancy.javafx.gestionnaire.*;
import eu.telecomnancy.javafx.rdv.Creneau;
import eu.telecomnancy.javafx.rdv.RendezVous;
import eu.telecomnancy.javafx.rdv.RendezVousEleve;

import java.sql.*;
import java.util.ArrayList;

public class Test {

    static int k=0;
    private static GestionnaireProf gp ;
    private static GestionnaireEleve ge ;
    private static GestionnairePlanning gpl ;
    private static GestionnaireRdv gr ;
    private static GestionnaireRdvEleve gre ;
    private static GestionnaireCreneau gc ;

    public static void test(Boolean b, String code) {
        k++;
        System.out.println("Test" + String.valueOf(k) + " : " + code);
        if (b) {
            System.out.println("✅ okay");
        }
        else {
            System.out.println("❌ error");
        }
        System.out.println();
    }

    public static void testRendezVous() {
        /*Eleve e = new Eleve(0, "0101", "LAURENT", "Nathan", "ln@google.com", "03", "TN");
        Professeur p = new Professeur(0, "0101", "MARC", "Arthur", "ma@google.com", "03", "TN");
        ArrayList<Eleve> l_e = new ArrayList<>();
        l_e.add(e);

        RendezVous rdv = new RendezVous(0, "lundi", "8h", l_e, p, "TNCY");

        test(rdv.getId_rdv()==0, "creationrdv");

        RendezVousGestionnaire Grdv = new RendezVousGestionnaire();

        Grdv.addRdv(rdv);

        test(rdv.getRdvstate() instanceof Rdv_en_attente, "rdv est en attente");

        Grdv.confirmeRDV(rdv);
        test(rdv.getRdvstate() instanceof Rdv_confirme, "rdv est confirmé");

        Grdv.annuleRDV(rdv);
        test(rdv.getRdvstate() instanceof Rdv_annule, "rdv est annulé");

        Grdv.archiveRDV(rdv);
        test(rdv.getRdvstate() instanceof Rdv_archive, "rdv est archivé");*/

    }

//"jdbc:sqlite:C:/Users/Maha/project-grp20/BaseDeDonnees/CodingW.db"


    public static void createConnect() {
        String jdbcUrl = "jdbc:sqlite:BaseDeDonnees/CodingW.db";
        try {
            Connect connect = new Connect() ;
            gp = new GestionnaireProf() ;
            ge = new GestionnaireEleve() ;
            gpl = new GestionnairePlanning() ;
            gr = new GestionnaireRdv(connect) ;
            gre = new GestionnaireRdvEleve() ;
            gc = new GestionnaireCreneau() ;
            Connection connection = DriverManager.getConnection(jdbcUrl);
            Statement statement = connection.createStatement();
            initDB(connection, statement) ;
            testConnect();
        }
        catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

    public static void initDB(Connection connection, Statement statement) throws SQLException {
        rsetToProf(connection, statement) ;
        rsetToEleve(connection, statement) ;
        rsetToCreneau(connection, statement) ;
        rsetToPlanning(connection, statement) ;
        rsetToRdv(connection, statement) ;
        rsetToRdvEleve(connection, statement);
    }

    public static void rsetToProf(Connection connection, Statement statement) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM Enseignant");
        while(result.next()) {
            gp.setTable_prof(new Professeur(result.getInt("id_enseignant"), result.getString("mdp_enseignant"), result.getString("nom"), result.getString("prenom"), result.getString("email"), result.getString("tel"), result.getString("adresse")));
        }
    }

    public static void rsetToEleve(Connection connection, Statement statement) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM Eleve");
        while(result.next()) {
            ge.setTable_eleve(new Eleve(result.getInt("id_eleve"), result.getString("mdp_eleve"), result.getString("nom"), result.getString("prenom"), result.getString("email"), result.getString("tel"), result.getString("adresse")));
        }
    }

    public static void rsetToCreneau(Connection connection, Statement statement) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM Creneau");
        while(result.next()) {
            gc.setTable_creneau(new Creneau(result.getInt("id_creneau"), result.getString("jour"), result.getString("heure")));
        }
    }

    public static void rsetToPlanning(Connection connection, Statement statement) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM Planning");
        while(result.next()) {
            gpl.setTable_planning(new Planning(result.getInt("id_creneau"), result.getInt("id_enseignant"))); ;
        }
    }

    public static void rsetToRdv(Connection connection, Statement statement)throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM RendezVous");
        while (result.next()) {
            gr.setTable_rdv(new RendezVous(result.getInt("id_rdv"), result.getInt("id_creneau"), result.getInt("id_enseignant"), result.getString("lieu"), result.getString("etat"), result.getString("description")));
        }
    }

    public static void rsetToRdvEleve(Connection connection, Statement statement)throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM RendezVous");
        while (result.next()) {
            gre.setTable_rdv(new RendezVousEleve(result.getInt("id_rdv"), result.getInt("id_eleve")));
        }
    }

    public static void testConnect() {
        for (int key : gp.getTable_prof().keySet()) {
            System.out.println(key  + ":" + gp.getTable_prof().get(key).getNom());
        }
    }

    public static void main(String[] args) {

        //testRendezVous();
        createConnect();
    }


}
