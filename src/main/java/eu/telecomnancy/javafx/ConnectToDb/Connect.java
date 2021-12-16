package eu.telecomnancy.javafx.ConnectToDb;


import java.io.*;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import eu.telecomnancy.javafx.compte.* ;
import eu.telecomnancy.javafx.gestionnaire.*;
import eu.telecomnancy.javafx.rdv.Creneau;
import eu.telecomnancy.javafx.rdv.RendezVous;
import eu.telecomnancy.javafx.rdv.RendezVousEleve;
import javafx.collections.ArrayChangeListener;


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
            this.gp = new GestionnaireProf(this) ;
            this.ge = new GestionnaireEleve(this) ;
            this.gpl = new GestionnairePlanning(this) ;
            this.gr = new GestionnaireRdv(this) ;
            this.gre = new GestionnaireRdvEleve(this) ;
            this.gc = new GestionnaireCreneau(this) ;

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
        System.out.println(userHomeDir);
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
        rsetToRdvEleve(connection, statement);
        rsetToRdv(connection, statement) ;

    }

    public void rsetToProf(Connection connection, Statement statement) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM Enseignant");
        int id = 0;
        while(result.next()) {
            int LineId = result.getInt("id_enseignant");
            this.gp.setTable_prof(new Professeur(LineId, result.getString("mdp_enseignant"), result.getString("nom"), result.getString("prenom"), result.getString("email"), result.getString("tel"), result.getString("adresse")));
            if (LineId > id) {
                id = LineId;
            }
        }
        gp.setMax_id_prof(id);
    }

    public void rsetToEleve(Connection connection, Statement statement) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM Eleve");
        int id = 0;
        while(result.next()) {
            int LineId = result.getInt("id_eleve");
            this.ge.setTable_eleve(new Eleve(LineId, result.getString("mdp_eleve"), result.getString("nom"), result.getString("prenom"), result.getString("email"), result.getString("tel"), result.getString("adresse")));
            if (LineId > id) {
                id = LineId;
            }
        }
        ge.setMax_id_eleve(id);

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
        int id = 0;
        while (result.next()) {
            int LineId = result.getInt("id_rdv");

            ArrayList<RendezVousEleve> TableRdvEleve = gre.getTable_rdvEleve();

            ArrayList<Eleve> list_eleve = new ArrayList<Eleve>();
            for (RendezVousEleve rd : TableRdvEleve) {

                if (rd.getId_rdv() == LineId) {
                    list_eleve.add(ge.getTable_eleve().get(rd.getId_eleve()));
                }
            }

            RendezVous rdv = new RendezVous(LineId, result.getInt("id_creneau"), result.getInt("id_enseignant"), result.getString("lieu"), result.getString("etat"), result.getString("description"), result.getString("intitule"), list_eleve);

            this.gr.setTable_rdv(rdv);
            if (LineId > id) {
                id = LineId;
            }
        }
        gr.setMax_id_rdv(id);

    }

    public void rsetToRdvEleve(Connection connection, Statement statement)throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM RendezVousEleve");
        while (result.next()) {
            this.gre.setTable_rdv(new RendezVousEleve(result.getInt("id_rdv"), result.getInt("id_eleve")));
        }
    }

    public void insertRdv(RendezVous rdv) throws SQLException {
        connection = DriverManager.getConnection(jdbcUrl);
        statement = connection.createStatement();

        String request = "INSERT INTO RendezVous VALUES ("+String.valueOf(rdv.getId_rdv())+","+String.valueOf(rdv.getId_creneau())+","+String.valueOf(rdv.getId_prof())+",'"+rdv.getEtat()+"','" + rdv.getIntitule() + "','"+rdv.getDescr()+"','"+rdv.getLieu()+"');";
        System.out.println(request);
        statement.executeUpdate(request);
        connection.close();

    }

    public void insertPlanning(Creneau c, Professeur p) throws SQLException {

        connection = DriverManager.getConnection(jdbcUrl);
        statement = connection.createStatement();

        String request = "INSERT INTO Planning VALUES ('"+String.valueOf(c.getId_creneau())+"', '"+ String.valueOf(p.getId())+ "');";
        statement.executeUpdate(request);

        connection.close();

    }

    public void insertProfesseur(Professeur p) throws SQLException {

        connection = DriverManager.getConnection(jdbcUrl);
        statement = connection.createStatement();

        String id = String.valueOf(p.getId());
        String mdp = p.getMdp();
        String nom = p.getNom();
        String prenom = p.getPrenom();
        String email = p.getEmail();
        String tel = p.getTel();
        String adresse = p.getAdresse();

        String request = "INSERT INTO Enseignant VALUES ("+id+",'"+mdp+"','"+nom+"','"+prenom+"','"+email+"','"+tel+"','"+adresse+"');";
        statement.executeUpdate(request);

        connection.close();

    }

    public void insertRdvEleve(RendezVousEleve rdveleve) throws SQLException {
        connection = DriverManager.getConnection(jdbcUrl);
        statement = connection.createStatement();

        int id_rdv = rdveleve.getId_rdv();
        int id_eleve = rdveleve.getId_eleve();

        String request = "INSERT INTO RendezVousEleve VALUES ("+id_rdv+", "+id_eleve+");";
        System.out.println(request);
        statement.executeUpdate(request);

        connection.close();
    }

    public String quote(String string) {
        string = '"' + string + '"' ;
        return string ;
    }

    public void changeRdvStatut(RendezVous rdv) throws SQLException {

        connection = DriverManager.getConnection(jdbcUrl);
        statement = connection.createStatement();

        String id = String.valueOf(rdv.getId_rdv());
        String etat = rdv.getEtat();

        String request = "UPDATE 'RendezVous' SET etat='"+etat+"' WHERE id_rdv="+id;
        System.out.println(request);
        statement.executeUpdate(request);

        connection.close();
    }

    public void changeEleve(Eleve eleve) throws SQLException {

        connection = DriverManager.getConnection(jdbcUrl);
        statement = connection.createStatement();

        String id = String.valueOf(eleve.getId());
        String mdp = quote(eleve.getMdp()) ;
        String nom = quote(eleve.getNom()) ;
        String prenom = quote(eleve.getPrenom()) ;
        String email = quote(eleve.getEmail()) ;
        String tel = quote(eleve.getTel()) ;
        String adresse = quote(eleve.getAdresse()) ;

        String request = "UPDATE Eleve SET mdp_eleve = " + mdp + ", nom = " + nom + ", prenom = " + prenom + ", email = " + email + ", tel = " + tel + ", adresse = " + adresse + " WHERE id_eleve = " + id  ;
        System.out.println(request);

        statement.executeUpdate(request);
        connection.close();
    }

    public void changeProf(Professeur prof) throws SQLException {
        connection = DriverManager.getConnection(jdbcUrl);
        statement = connection.createStatement();

        String id = String.valueOf(prof.getId());
        String mdp = quote(prof.getMdp()) ;
        String nom = quote(prof.getNom()) ;
        String prenom = quote(prof.getPrenom()) ;
        String email = quote(prof.getEmail()) ;
        String tel = quote(prof.getTel()) ;
        String adresse = quote(prof.getAdresse()) ;

        String request = "UPDATE Enseignant SET mdp_enseignant = " + mdp + ", nom = " + nom + ", prenom = " + prenom + ", email = " + email + ", tel = " + tel + ", adresse = " + adresse + " WHERE id_enseignant = " + id  ;
        System.out.println(request);

        statement.executeUpdate(request);
        connection.close();
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

