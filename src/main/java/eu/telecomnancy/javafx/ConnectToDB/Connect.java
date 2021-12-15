package eu.telecomnancy.javafx.ConnectToDB;


import java.io.*;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;

import eu.telecomnancy.javafx.compte.* ;
import eu.telecomnancy.javafx.gestionnaire.*;
import eu.telecomnancy.javafx.rdv.Creneau;
import eu.telecomnancy.javafx.rdv.RendezVous;
import eu.telecomnancy.javafx.rdv.RendezVousEleve;


public class Connect {

    String jdbcUrl = null;

    private GestionnaireProf gp ;
    private GestionnaireEleve ge ;
    private GestionnairePlanning gpl ;
    private GestionnaireRdv gr ;
    private GestionnaireRdvEleve gre ;
    private GestionnaireCreneau gc ;

    private Connection connection ;
    private Statement statement ;

    public Connect() throws URISyntaxException, IOException {
        load_db();
        try {
            this.gp = new GestionnaireProf() ;
            this.ge = new GestionnaireEleve() ;
            this.gpl = new GestionnairePlanning() ;
            this.gr = new GestionnaireRdv() ;
            this.gre = new GestionnaireRdvEleve() ;
            this.gc = new GestionnaireCreneau() ;

            Connection connection = DriverManager.getConnection(jdbcUrl);
            Statement statement = connection.createStatement();
            initDB(connection, statement);
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    private static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!sourceFile.exists()) {
        }
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }

    }

    public void load_db() throws URISyntaxException, IOException {

        // Creation de la base de donnee dans .MyRdv
        String userHomeDir = System.getProperty("user.home");
        String AppDir = userHomeDir + "/.MyRdv";
        File f = new File(AppDir);

        if(f.mkdir()) {
            File source = new File("BaseDeDonnees/CodingW.db");
            File dest = new File(AppDir + "/CodingW.db");
            copyFile(source, dest);
        }

        String DbDir = AppDir + "/CodingW.db";
        jdbcUrl = "jdbc:sqlite:" + DbDir;

    }

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


    public void printTable(Hashtable h) {
        for (Object o : h.keySet()) {
            System.out.println(o + " et " + h.get(o));
        }
    }

    public GestionnaireProf getGestionnaireProf() { return gp; }
    public GestionnaireEleve getGestionnaireEleve() { return ge; }
    public GestionnairePlanning getGestionnairePlanning() { return gpl; }
    public GestionnaireRdv getGestionnaireRdv() { return gr; }
    public GestionnaireRdvEleve getGestionnaireRdvEleve() { return gre; }
    public GestionnaireCreneau getGestionnaireCreneau() { return gc; }
    public Connection getConnection() { return connection; }
    public Statement getStatement() { return statement; }
}

