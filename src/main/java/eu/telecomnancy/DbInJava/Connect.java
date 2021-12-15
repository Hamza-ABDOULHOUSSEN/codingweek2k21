package eu.telecomnancy.DbInJava;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    //"jdbc:sqlite:C:/Users/Maha/project-grp20/BaseDeDonnees/CodingW.db"

    public static void main (String[] args) {
        String jdbcUrl = "jdbc:sqlite:BaseDeDonnees/CodingW.db";
        //String jdbcUrl = "jdbc:sqlite:CodingW.db";

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