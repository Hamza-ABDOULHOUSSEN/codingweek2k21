package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.Observateur.Observateur;
import eu.telecomnancy.javafx.model.MyRdv;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.io.IOException;

public class PageDemandeRdvController implements Observateur {

    private MyRdv myrdv;

    @FXML private MenuButton choisirProf ;
    @FXML private MenuButton choisirCreneau ;

    public PageDemandeRdvController(MyRdv myrdv) {
        this.myrdv = myrdv;
        myrdv.ajouterObservateur(this);
    }

    // Renvoie à la PageEleve
    @FXML protected void RetourEleve() throws IOException {
        PageEleveController pec = new PageEleveController(myrdv);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageEleve.fxml"));
        fxmlLoader.setControllerFactory(ic -> {
            if (ic.equals(eu.telecomnancy.javafx.controller.PageEleveController.class)) return pec;
            else return null;
        });
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        myrdv.setScene(scene);
    }

    public void initChoixProf() {
        for (int i : myrdv.getConnect().getGestionnaireProf().getTable_prof().keySet()) {
            MenuItem mi = new MenuItem() ;
            mi.setText(myrdv.getConnect().getGestionnaireProf().getTable_prof().get(i).getNom() + " " + myrdv.getConnect().getGestionnaireProf().getTable_prof().get(i).getPrenom());
            mi.setOnAction(e -> { setChoixProf(mi.getText()) ; });
            choisirProf.getItems().add(mi) ;
        }
    }

    public void initChoixHoraire() {
        for (int i : myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().keySet()) {
            MenuItem mi = new MenuItem() ;
            mi.setText(myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(i).getJour() + " " + myrdv.getConnect().getGestionnaireCreneau().getTable_creneau().get(i).getHeure());
            mi.setOnAction(e -> { setChoixHoraire(mi.getText()) ; });
            choisirCreneau.getItems().add(mi) ;
        }
    }

    public void setChoixProf(String text) {
        this.choisirProf.setText(text);
    }

    public void setChoixHoraire(String text) {
        this.choisirCreneau.setText(text);
    }

    @Override public void update() {

    }
}
