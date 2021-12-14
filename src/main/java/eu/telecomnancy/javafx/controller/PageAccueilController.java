package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.Observateur.Observateur;
import eu.telecomnancy.javafx.model.MyRdv;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;

public class PageAccueilController implements Observateur {

    private MyRdv myrdv;
    private int direction ;
    
    @FXML private TextField input_nom ;
    @FXML private TextField input_mdp ;

    @FXML private Label erreur;

    public PageAccueilController(MyRdv myrdv) {
        this.myrdv = myrdv;
        this.direction = 0 ;
        myrdv.ajouterObservateur(this);
    }

    // Renvoie à la PageProf ou à la PageEleve
    @FXML protected void Connexion() throws IOException {
        if (this.direction != 0 && this.myrdv.check_id(input_nom.getText()) != 0 && this.myrdv.check_mdp(input_mdp.getText()) != 0) {
            FXMLLoader fxmlLoader = null ;
            if (this.direction == 1 && this.myrdv.check_id(input_nom.getText()) == 1 && this.myrdv.check_mdp(input_mdp.getText()) == 1) {
                PageProfController ppc = new PageProfController(myrdv);
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageProf.fxml"));
                fxmlLoader.setControllerFactory(ic -> {
                    if (ic.equals(eu.telecomnancy.javafx.controller.PageProfController.class)) return ppc;
                    else return null;
                });
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                myrdv.setScene(scene);
            }
            else if (this.direction == 2 && this.myrdv.check_id(input_nom.getText()) == 2 && this.myrdv.check_mdp(input_mdp.getText()) == 2) {
                PageEleveController pec = new PageEleveController(myrdv);
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageEleve.fxml"));
                fxmlLoader.setControllerFactory(ic -> {
                    if (ic.equals(eu.telecomnancy.javafx.controller.PageEleveController.class)) return pec;
                    else return null;
                });
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                myrdv.setScene(scene);
            }
        }
    }

    @FXML
    protected void Professeur() {
        this.myrdv.setAccueil_nom_mdp("Nom du professeur", "Mot de passe du professeur");
        if (this.direction == 2) {
            input_nom.clear();
            input_mdp.clear();
        }
        this.direction = 1 ;
    }

    @FXML
    protected void Etudiant() {
        this.myrdv.setAccueil_nom_mdp("Nom de l'eleve", "Mot de passe de l'eleve");
        if (this.direction == 1) {
            input_nom.clear();
            input_mdp.clear();
        }
        this.direction = 2 ;
    }

    @Override
    public void update() {
        input_nom.setPromptText(this.myrdv.getAccueil_nom());
        input_mdp.setPromptText(this.myrdv.getAccueil_mdp());
    }
/*
    @Override
    public void getInput() {

        TextField textField = new TextField(this.album.getNom());
        textField.setMinWidth(120);
        Button boutton_ok = new Button("Ok") ;

        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.getChildren().addAll(textField, boutton_ok);
        Scene scene = new Scene(root, 200, 100);
        Stage primaryStage = new Stage() ;
        boutton_ok.setOnAction(e -> boutton(textField.getText(), primaryStage));



    }
    */
}
