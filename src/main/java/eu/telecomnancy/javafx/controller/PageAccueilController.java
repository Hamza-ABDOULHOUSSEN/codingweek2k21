package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.Observateur.Observateur;
import eu.telecomnancy.javafx.model.MyRdv;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.IOException;

public class PageAccueilController implements Observateur {

    private MyRdv myrdv;
    private int direction ;
    private int cacher ;
    
    @FXML private TextField input_nom ;
    @FXML private PasswordField input_mdp ;

    @FXML private Label erreur ;
    @FXML private TextField input_mdpText ;

    @FXML private Rectangle prof_color ;
    @FXML private Rectangle eleve_color ;

    public PageAccueilController(MyRdv myrdv) {
        this.myrdv = myrdv;
        this.direction = 0 ;
        this.cacher = 0 ;
        //this.input_mdp.setOnAction(Entrer()) ;
        myrdv.ajouterObservateur(this);
        //translate();
    }

    // Renvoie à la PageProf ou à la PageEleve
    @FXML protected void Connexion() throws IOException {
        FXMLLoader fxmlLoader = null;
        String password = new String("") ;
        if (this.cacher == 0) { password = this.input_mdp.getText() ; }
        if (this.cacher == 1) { password = this.input_mdpText.getText() ; }
        if (direction == 0) {
            myrdv.setErreur("Appuyer sur Professeur ou Etudiant");
            input_nom.clear();
            input_mdp.clear();
        }
        if (direction == 1) {
            if (this.myrdv.check_id(input_nom.getText(), password) == 1) {
                myrdv.setAccueil_nom_mdp(input_nom.getText(), password);
                PageProfController ppc = new PageProfController(myrdv);
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageProf.fxml"));
                fxmlLoader.setControllerFactory(ic -> {
                    if (ic.equals(eu.telecomnancy.javafx.controller.PageProfController.class)) return ppc;
                    else return null;
                });
                Parent root = fxmlLoader.load();
                ppc.initNom();
                Scene scene = new Scene(root);
                myrdv.setScene(scene);
            } else {
                myrdv.setErreur("Mauvais Id ou Mot de Passe");
                input_nom.clear();
                input_mdp.clear();
            }
        }
        if (direction == 2) {
            if (this.myrdv.check_id(input_nom.getText(), password) == 2) {
                myrdv.setAccueil_nom_mdp(input_nom.getText(), password);
                PageEleveController pec = new PageEleveController(myrdv);
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageEleve.fxml"));
                fxmlLoader.setControllerFactory(ic -> {
                    if (ic.equals(eu.telecomnancy.javafx.controller.PageEleveController.class)) return pec;
                    else return null;
                });
                Parent root = fxmlLoader.load();
                pec.initNom();
                Scene scene = new Scene(root);
                myrdv.setScene(scene);
            } else {
                myrdv.setErreur("Mauvais Id ou Mot de Passe");
                input_nom.clear();
                input_mdp.clear();
                input_mdpText.clear();
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
            input_mdpText.clear();
        }
        this.direction = 1 ;
        this.prof_color.setWidth(840);
        this.eleve_color.setWidth(440);
        this.eleve_color.setX(200);
    }

    @FXML
    protected void Etudiant() {
        myrdv.setErreur("");
        this.myrdv.setAccueil_nom_mdp("Nom de l'eleve", "Mot de passe de l'eleve");
        if (this.direction == 1) {
            input_nom.clear();
            input_mdp.clear();
            input_mdpText.clear();
        }
        this.direction = 2 ;
        this.prof_color.setWidth(440);
        this.eleve_color.setWidth(840);
        this.eleve_color.setX(-200);
    }

    @Override
    public void update() {
        input_nom.setPromptText(this.myrdv.getAccueil_nom());
        input_mdp.setPromptText(this.myrdv.getAccueil_mdp());
        erreur.setText(myrdv.getErreur());
    }

    @FXML
    public void AfficherMdp() {
        if (this.cacher == 0) {
            this.input_mdpText.setLayoutX(300);
            this.input_mdpText.setLayoutY(450);
            this.input_mdpText.setPrefWidth(700);
            this.input_mdpText.setPrefHeight(50);
            this.input_mdpText.setText(this.input_mdp.getText());
            this.input_mdpText.setOpacity(1);
            this.cacher = 1 ;
        }
        else {
            this.input_mdp.setText(this.input_mdpText.getText());
            this.input_mdpText.setPrefWidth(1);
            this.input_mdpText.setPrefHeight(1);
            this.input_mdpText.setOpacity(0);
            this.cacher = 0 ;
        }
    }

    public void keyEntrer() {
        input_mdp.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode().equals(KeyCode.ENTER)) {
                    try {
                        Connexion() ;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
