package eu.telecomnancy.javafx;

import eu.telecomnancy.javafx.ConnectToDb.Connect;
import eu.telecomnancy.javafx.compte.Eleve;
import eu.telecomnancy.javafx.rdv.RendezVous;
import eu.telecomnancy.javafx.compte.Planning;
import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.gestionnaire.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import eu.telecomnancy.javafx.rdv.*;

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

    public static void testRendezVous() throws URISyntaxException, IOException, SQLException {
        Connect connect = new Connect();

        connect.resetDb();
        connect.load_db();

        GestionnaireProf gp = connect.getGestionnaireProf();
        GestionnaireEleve ge = connect.getGestionnaireEleve();
        GestionnairePlanning gpl = connect.getGestionnairePlanning();
        GestionnaireRdv gr = connect.getGestionnaireRdv();
        GestionnaireRdvEleve gre = connect.getGestionnaireRdvEleve();
        GestionnaireCreneau gc = connect.getGestionnaireCreneau();

        Eleve e = new Eleve(412, "0101", "LAURENT", "Nathan", "ln@google.com", "03", "TN");
        Professeur p = new Professeur(412, "0101", "MARC", "Arthur", "ma@google.com", "03", "TN");
        ArrayList<Eleve> l_e = new ArrayList<Eleve>();
        l_e.add(e);

        Creneau c = gc.findCreneau(1);

        RendezVous rdv = new RendezVous(412, 1, 412, "lieu", "en attente", "descr", "intitule", l_e);

        test(rdv.getId_rdv() == 412, "creationrdv");

        test(rdv.getRdvstate() instanceof Rdv_en_attente, "rdv est en attente");

        rdv.confirme();
        test(rdv.getRdvstate() instanceof Rdv_confirme, "rdv est confirmé");

        rdv.annule();
        test(rdv.getRdvstate() instanceof Rdv_annule, "rdv est annulé");

        rdv.archive();
        test(rdv.getRdvstate() instanceof Rdv_archive, "rdv est archivé");

        connect.resetDb();

    }


    public static void main(String[] args) throws SQLException, URISyntaxException, IOException {

        testRendezVous();
    }


}
