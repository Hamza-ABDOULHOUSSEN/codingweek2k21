package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.Observateur.Observateur;
import eu.telecomnancy.javafx.model.MyRdv;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class PageDemandeRdvController implements Observateur {

    private MyRdv myrdv;

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

    @Override public void update() {

    }
}
