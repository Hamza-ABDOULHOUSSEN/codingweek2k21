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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class PagePlanningController implements Observateur {

    private MyRdv myrdv ;
    @FXML private GridPane grid ;
    @FXML private MenuButton choisirDebutJour ;
    @FXML private MenuButton choisirDebutHeure ;
    @FXML private MenuButton choisirFinJour ;
    @FXML private MenuButton choisirFinHeure ;
    @FXML private Label erreur ;
    @FXML private Pane pane ;

    private Professeur prof;

    private String jourDebut = "" ;
    private String heureDebut = "" ;
    private String jourFin = "" ;
    private String heureFin = "" ;

    Hashtable<String, Integer> tableJour = new Hashtable<String, Integer>() ;
    Hashtable<String, Integer> tableHeure = new Hashtable<String, Integer>() ;

    public PagePlanningController(MyRdv myrdv) {
        this.myrdv = myrdv ;
        this.myrdv.ajouterObservateur(this);
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
            this.erreur.setText("Veuillez selectionner tous les creneaux");
        }
        else {
            Creneau deb = myrdv.getConnect().getGestionnaireCreneau().findCreneau(jourDebut, heureDebut);
            Creneau fin = myrdv.getConnect().getGestionnaireCreneau().findCreneau(jourFin, heureFin);
            if (deb.getId_creneau() > fin.getId_creneau()) {
                this.erreur.setText("La fin des creneaux est avant le debut");
            }
            else {
                myrdv.UpdateIndispoPlanning(prof, deb, fin);
                this.choisirDebutJour.setText("Jour");
                this.choisirDebutHeure.setText("Heure");
                this.choisirFinJour.setText("Jour");
                this.choisirFinHeure.setText("Heure");
                this.erreur.setText("Planning mis a jour");
            }
        }
    }

    @FXML public void Deconnexion() {}

    public void initPage() {
        initChoixDebut();
        initChoixFin();
        tablePosInit();
        this.prof = myrdv.getProf();
        this.erreur.setText("");
        myrdv.updateCreneau();
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

    public void tablePosInit() {
        tableJour.put("lundi", 300) ;
        tableJour.put("mardi", 442) ;
        tableJour.put("mercredi", 584) ;
        tableJour.put("jeudi", 726) ;
        tableJour.put("vendredi", 868) ;
        tableJour.put("samedi", 1010) ;

        tableHeure.put("08:00", 200) ;
        tableHeure.put("08:20", 210) ;
        tableHeure.put("08:40", 220) ;
        tableHeure.put("09:00", 230) ;
        tableHeure.put("09:20", 240) ;
        tableHeure.put("09:40", 250) ;
        tableHeure.put("10:00", 260) ;
        tableHeure.put("10:20", 270) ;
        tableHeure.put("10:40", 280) ;
        tableHeure.put("11:00", 290) ;
        tableHeure.put("11:20", 300) ;
        tableHeure.put("11:40", 311) ;
        tableHeure.put("12:00", 322) ;
        tableHeure.put("12:20", 332) ;
        tableHeure.put("12:40", 342) ;
        tableHeure.put("13:00", 352) ;
        tableHeure.put("13:20", 362) ;
        tableHeure.put("13:40", 372) ;
        tableHeure.put("14:00", 382) ;
        tableHeure.put("14:20", 392) ;
        tableHeure.put("14:40", 402) ;
        tableHeure.put("15:00", 412) ;
        tableHeure.put("15:20", 422) ;
        tableHeure.put("15:40", 432) ;
        tableHeure.put("16:00", 442) ;
        tableHeure.put("16:20", 452) ;
        tableHeure.put("16:40", 463) ;
        tableHeure.put("17:00", 474) ;
        tableHeure.put("17:20", 484) ;
        tableHeure.put("17:40", 494) ;
        tableHeure.put("18:00", 504) ;
    }

    public void rectangleInit(String width, String height) {
        Rectangle rectangle = new Rectangle() ;
        int w = this.tableJour.get(width) ;
        int h = this.tableHeure.get(height) ;
        rectangle.setStroke(Paint.valueOf("red"));
        rectangle.setFill(Paint.valueOf("red"));
        rectangle.setWidth(135) ;
        rectangle.setHeight(8);
        rectangle.setLayoutX(w) ;
        rectangle.setLayoutY(h) ;
        this.pane.getChildren().add(rectangle) ;
    }

    public void insertRectangle(ArrayList<Creneau> list) {
        for (Creneau creneau : list) {
            rectangleInit(creneau.getJour(), creneau.getHeure()) ;
        }
    }


    @Override
    public void update() {
        this.pane.getChildren().clear();
        ArrayList<Creneau> liste_creneau = myrdv.getListe_creneau();
        insertRectangle(liste_creneau);
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
