package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.Observateur.Observateur;
import eu.telecomnancy.javafx.compte.Eleve;
import eu.telecomnancy.javafx.compte.Professeur;
import eu.telecomnancy.javafx.gestionnaire.GestionnaireCreneau;
import eu.telecomnancy.javafx.gestionnaire.GestionnairePlanning;
import eu.telecomnancy.javafx.model.MyRdv;
import eu.telecomnancy.javafx.rdv.Creneau;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PagePlanningController implements Observateur {

    private Professeur prof;

    private MyRdv myrdv ;
    @FXML private GridPane grid ;
    @FXML private MenuButton choisirDebutJour ;
    @FXML private MenuButton choisirDebutHeure ;
    @FXML private MenuButton choisirFinJour ;
    @FXML private MenuButton choisirFinHeure ;

    private String jourDebut = "" ;
    private String heureDebut = "" ;
    private String jourFin = "" ;
    private String heureFin = "" ;

    public PagePlanningController(MyRdv myrdv) {
        this.myrdv = myrdv ;
    }

    @FXML public void saisirDispo() throws SQLException {
        if (jourDebut.equals("") || heureDebut.equals("") || jourFin.equals("") || heureFin.equals("")) {
            // afficher erreur
        }
        else {
            GestionnaireCreneau gc = myrdv.getConnect().getGestionnaireCreneau();
            GestionnairePlanning gp = myrdv.getConnect().getGestionnairePlanning();
            Creneau deb = gc.findCreneau(jourDebut, heureDebut);
            Creneau fin = gc.findCreneau(jourFin, heureFin);
            if (deb.getId_creneau() > fin.getId_creneau()) {
                // affiche erreur , deb apres fin
            }
            else {
                if (gp.contientRdv(prof, deb, fin)) {
                    // afficher erreur contient rdv
                }
                else {
                    myrdv.UpdateDispoPlanning(prof, deb, fin);
                }

            }
        }
    }
    @FXML public void saisirIndispo() throws SQLException {
        if (jourDebut.equals("") || heureDebut.equals("") || jourFin.equals("") || heureFin.equals("")) {
            // afficher erreur
        }
        else {
            Creneau deb = myrdv.getConnect().getGestionnaireCreneau().findCreneau(jourDebut, heureDebut);
            Creneau fin = myrdv.getConnect().getGestionnaireCreneau().findCreneau(jourFin, heureFin);
            if (deb.getId_creneau() > fin.getId_creneau()) {
                // affiche erreur , deb apres fin
            }
            else {
                myrdv.UpdateIndispoPlanning(prof, deb, fin);
            }
        }
    }

    @FXML public void Deconnexion() {}

    public void initPage() {
        initChoixDebut();
        initChoixFin();
        this.prof = myrdv.getProf();
    }
    public void initChoixDebut() {
        ArrayList<Creneau> list = new ArrayList<Creneau>() ;
        ArrayList<String> listJour = new ArrayList<String>() ;
        ArrayList<String> listHeure = new ArrayList<String>() ;
        for (int i : myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().keySet()) {
            Creneau creneau = myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(i) ;
            if (!listJour.contains(creneau.getJour())) {
                listJour.add(0, creneau.getJour()) ;
                MenuItem mi = new MenuItem();
                mi.setText(creneau.getJour()) ;
                mi.setOnAction(e -> {setChoisirDebutJour(creneau.getJour()); ; });
                choisirDebutJour.getItems().add(mi);
            }
        }
        for (int i : myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().keySet()) {
            Creneau creneau = myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(i) ;
            if (!listHeure.contains(creneau.getHeure())) {
                listHeure.add(0, creneau.getHeure()) ;
                MenuItem mi = new MenuItem();
                mi.setText(creneau.getHeure()) ;
                mi.setOnAction(e -> {setChoisirDebutHeure(creneau.getHeure()); ; });
                choisirDebutHeure.getItems().add(mi);
            }
        }
    }
    public void initChoixFin() {
        ArrayList<Creneau> list = new ArrayList<Creneau>() ;
        ArrayList<String> listJour = new ArrayList<String>() ;
        ArrayList<String> listHeure = new ArrayList<String>() ;
        for (int i : myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().keySet()) {
            Creneau creneau = myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(i) ;
            if (!listJour.contains(creneau.getJour())) {
                listJour.add(0, creneau.getJour()) ;
                MenuItem mi = new MenuItem();
                mi.setText(creneau.getJour()) ;
                mi.setOnAction(e -> {setChoisirFinJour(creneau.getJour()); ; });
                choisirFinJour.getItems().add(mi);
            }
        }
        for (int i : myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().keySet()) {
            Creneau creneau = myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(i) ;
            if (!listHeure.contains(creneau.getHeure())) {
                listHeure.add(0, creneau.getHeure()) ;
                MenuItem mi = new MenuItem();
                mi.setText(creneau.getHeure()) ;
                mi.setOnAction(e -> {setChoisirFinHeure(creneau.getHeure()); ; });
                choisirFinHeure.getItems().add(mi);
            }
        }
    }

    public void setChoisirDebutJour(String text) {
        this.choisirDebutJour.setText(text);
        this.jourDebut = text ;
    }
    public void setChoisirDebutHeure(String text) {
        this.choisirDebutHeure.setText(text);
        this.heureDebut = text ;
    }
    public void setChoisirFinJour(String text) {
        this.choisirFinJour.setText(text);
        this.jourFin = text ;
    }
    public void setChoisirFinHeure(String text) {
        this.choisirFinHeure.setText(text);
        this.heureFin = text ;
    }


    @Override
    public void update() {

    }

    public MyRdv getMyrdv() { return myrdv; }

    public GridPane getGrid() { return grid; }
    public void setGrid(GridPane grid) { this.grid = grid; }


    // Renvoie à la PageProf
    @FXML protected void goProf() throws IOException {
        PageProfController ppc = new PageProfController(myrdv);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageProf.fxml"));
        fxmlLoader.setControllerFactory(ic -> {
            if (ic.equals(eu.telecomnancy.javafx.controller.PageProfController.class)) return ppc;
            else return null;
        });
        Parent root = fxmlLoader.load();
        ppc.initPage();
        Scene scene = new Scene(root);
        myrdv.setScene(scene);
    }
}
