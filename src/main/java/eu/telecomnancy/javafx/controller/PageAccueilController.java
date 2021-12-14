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
import java.awt.*;
import java.io.IOException;

public class PageAccueilController implements Observateur {

    private MyRdv myrdv;
    private int direction ;
    
    @FXML private TextField input_nom ;
    @FXML private TextField input_mdp ;

    @FXML private Label erreur ;

    //@FXML private Rectangle prof_color ;
    //@FXML private Rectangle eleve_color ;


    public PageAccueilController(MyRdv myrdv) {
        this.myrdv = myrdv;
        this.direction = 0 ;
        myrdv.ajouterObservateur(this);
    }

    // Renvoie à la PageProf ou à la PageEleve
    @FXML protected void Connexion() throws IOException {
        FXMLLoader fxmlLoader = null;

        if (direction == 0) {
            myrdv.setErreur("Appuyer sur Professeur ou Etudiant");
            input_nom.clear();
            input_mdp.clear();
        }

        if (direction == 1) {
            if (this.myrdv.check_id(input_nom.getText(), input_mdp.getText()) == 1) {
                System.out.println("1");
                PageProfController ppc = new PageProfController(myrdv);
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageProf.fxml"));
                fxmlLoader.setControllerFactory(ic -> {
                    if (ic.equals(eu.telecomnancy.javafx.controller.PageProfController.class)) return ppc;
                    else return null;
                });
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                myrdv.setScene(scene);
            } else {
                myrdv.setErreur("Mauvaise Id ou Mot de Passe");
                input_nom.clear();
                input_mdp.clear();
            }
        }

        if (direction == 2) {
            if (this.myrdv.check_id(input_nom.getText(), input_mdp.getText()) == 2) {
                PageEleveController pec = new PageEleveController(myrdv);
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageEleve.fxml"));
                fxmlLoader.setControllerFactory(ic -> {
                    if (ic.equals(eu.telecomnancy.javafx.controller.PageEleveController.class)) return pec;
                    else return null;
                });
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                myrdv.setScene(scene);
            } else {
                myrdv.setErreur("Mauvaise Id ou Mot de Passe");
                input_nom.clear();
                input_mdp.clear();
            }
        }
    }


    @FXML
    protected void Professeur() {
        myrdv.setErreur("");
        this.myrdv.setAccueil_nom_mdp("Nom du professeur", "Mot de passe du professeur");
        if (this.direction == 2) {
            input_nom.clear();
            input_mdp.clear();
        }
        this.direction = 1 ;
        //this.prof_color.setSize(840, 780);
        //this.eleve_color.setSize(440,780);
    }

    @FXML
    protected void Etudiant() {
        myrdv.setErreur("");
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
        erreur.setText(myrdv.getErreur());
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
