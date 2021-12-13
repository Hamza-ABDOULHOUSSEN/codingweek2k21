package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.Observateur.Observateur;
import eu.telecomnancy.javafx.model.MyRdv;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PageAccueilController implements Observateur {

    private MyRdv myrdv;
    
    @FXML
    private TextField input;

    @FXML
    private Label erreur;

    public PageAccueilController(MyRdv myrdv) {
        this.myrdv = myrdv;
        myrdv.ajouterObservateur(this);
    }

    @FXML
    protected void Connection() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageProf.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        myrdv.setScene(scene);
    }

    @FXML
    protected void Professeur() {}

    @FXML
    protected void Etudiant() {}

    @Override
    public void update() {

    }
}
