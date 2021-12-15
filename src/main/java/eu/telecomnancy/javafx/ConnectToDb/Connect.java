package eu.telecomnancy.javafx.ConnectToDb;

import eu.telecomnancy.javafx.MainApp;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.sql.*;

public class Connect {

    public Connect() {
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
        String jdbcUrl = "jdbc:sqlite:" + DbDir;

        // ########### TESTTTTTTT ###########

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT * FROM Creneau";

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                String id_creneau = result.getString("id_creneau");
                String jour = result.getString("jour");
                String heure = result.getString("heure");

                System.out.println(id_creneau + " | " + jour + " | " + heure );
            }

        }
        catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }


    }





}
