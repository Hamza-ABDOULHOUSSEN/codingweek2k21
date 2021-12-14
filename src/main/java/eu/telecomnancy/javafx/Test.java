package eu.telecomnancy.javafx;

import eu.telecomnancy.javafx.compte.Eleve;
import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.gestionnaire.RendezVousGestionnaire;
import eu.telecomnancy.javafx.rdv.*;

import java.util.ArrayList;

public class Test {

    static int k=0;

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
        Eleve e = new Eleve(0, "0101", "LAURENT", "Nathan", "ln@google.com", 03, "TN");
        Professeur p = new Professeur(0, "0101", "MARC", "Arthur", "ma@google.com", 03, "TN");
        ArrayList<Eleve> l_e = new ArrayList<>();
        l_e.add(e);

        RendezVous rdv = new RendezVous(0, "lundi", "8h", l_e, p, "TNCY");

        test(rdv.getId_rdv()==0, "creationrdv");

        test(rdv.getHoraire().equals("8h"), "creationrdv");

        RendezVousGestionnaire Grdv = new RendezVousGestionnaire();

        Grdv.addRdv(rdv);

        test(rdv.getRdvstate() instanceof Rdv_en_attente, "rdv est en attente");

        Grdv.confirmeRDV(rdv);
        test(rdv.getRdvstate() instanceof Rdv_confirme, "rdv est confirmé");

        Grdv.annuleRDV(rdv);
        test(rdv.getRdvstate() instanceof Rdv_annule, "rdv est annulé");

        Grdv.archiveRDV(rdv);
        test(rdv.getRdvstate() instanceof Rdv_archive, "rdv est archivé");

    }

    public static void main(String[] args) {

        testRendezVous();

    }


}
