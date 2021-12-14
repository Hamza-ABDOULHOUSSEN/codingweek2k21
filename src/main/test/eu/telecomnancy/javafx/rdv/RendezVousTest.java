package eu.telecomnancy.javafx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static eu.telecomnancy.junit.ReflectionAssertions.assertInstanceOf;

public class RendezVousTest() {
    @Test
    public void testRendezVous() {
        Eleve e = new Eleve();
        Professeur p = new Professeur();
        ArrayList<Eleve> l_e = {e};

        RendezVous rdv = new RendezVous(0, "lundi", "8h", l_e, p, "TNCY");

        assertInstanceOf("eu.telecomnancy.javafx.rdv.RendezVouos", rdv);

        assertEquals(rdv.getId_rdv(), 0);
        assertEquals(rdv.getHoraire(), "FAUUXXXXXX");

    }



}
