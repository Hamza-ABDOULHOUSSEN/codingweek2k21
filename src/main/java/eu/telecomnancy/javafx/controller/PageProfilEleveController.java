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
import java.sql.SQLException;

public class PageProfilEleveController implements Observateur {
    private MyRdv myRdv;

    @FXML private TextField inputNom ;
    @FXML private TextField inputPrenom ;
    @FXML private TextField inputEmail ;
    @FXML private TextField inputTel ;
    @FXML private TextField inputAdresse ;
    @FXML private TextField inputMotdepasse ;

    @FXML private Label labelNom ;
    @FXML private Label labelPrenom ;
    @FXML private Label labelEmail ;
    @FXML private Label labelTel ;
    @FXML private Label labelAdresse ;
    @FXML private Label labelMotDePasse ;

    @FXML private Label nomEleve ;

    int afficherNom = 0 ;
    int afficherPrenom = 0 ;
    int afficherEmail = 0 ;
    int afficherTel = 0 ;
    int afficherAdresse = 0 ;
    int afficherMotDePasse = 0 ;

    public PageProfilEleveController(MyRdv myRdv) {
        this.myRdv = myRdv ;
    }

    public void initPage() {
        this.nomEleve.setText("Page profil de " + myRdv.getEleve().getPrenom() + " " + myRdv.getEleve().getNom());
        this.labelNom.setText("Nom : " + myRdv.getEleve().getNom());
        this.labelPrenom.setText("Prenom : " + myRdv.getEleve().getPrenom());
        this.labelEmail.setText("Email : " + myRdv.getEleve().getEmail());
        this.labelTel.setText("Telephone : " + myRdv.getEleve().getTel());
        this.labelAdresse.setText("Adresse : " + myRdv.getEleve().getAdresse());
        this.labelMotDePasse.setText("Mot de passe : " + myRdv.getEleve().getMdp());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    @FXML public void goPageEleve() throws IOException {
        PageEleveController pec = new PageEleveController(myRdv);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageEleve.fxml"));
        fxmlLoader.setControllerFactory(ic -> {
            if (ic.equals(eu.telecomnancy.javafx.controller.PageEleveController.class)) return pec;
            else return null;
        });
        Parent root = fxmlLoader.load();
        pec.initPage();
        Scene scene = new Scene(root);
        myRdv.setScene(scene);
    }

    @FXML public void modifierNom() {
        if (this.afficherNom == 0) {
            this.labelNom.setText("");
            this.labelNom.setPrefWidth(1);
            this.labelNom.setPrefHeight(1);
            this.afficherNom = 1 ;
        }
        else {
            this.labelNom.setText("Nom : " + myRdv.getEleve().getNom());
            this.labelNom.setPrefWidth(480);
            this.labelNom.setPrefHeight(27);
            this.afficherNom = 0 ;
        }
    }
    @FXML public void modifierPrenom() {
        if (this.afficherPrenom == 0) {
            this.labelPrenom.setText("");
            this.labelPrenom.setPrefWidth(1);
            this.labelPrenom.setPrefHeight(1);
            this.afficherPrenom = 1 ;
        }
        else {
            this.labelPrenom.setText("Prenom : " + myRdv.getEleve().getPrenom());
            this.labelPrenom.setPrefWidth(480);
            this.labelPrenom.setPrefHeight(27);
            this.afficherPrenom = 0 ;
        }
    }
    @FXML public void modifierEmail() {
        if (this.afficherEmail == 0) {
            this.labelEmail.setText("");
            this.labelEmail.setPrefWidth(1);
            this.labelEmail.setPrefHeight(1);
            this.afficherEmail = 1 ;
        }
        else {
            this.labelEmail.setText("Email : " + myRdv.getEleve().getEmail());
            this.labelEmail.setPrefWidth(480);
            this.labelEmail.setPrefHeight(27);
            this.afficherEmail = 0 ;
        }
    }
    @FXML public void modifierTel() {
        if (this.afficherTel == 0) {
            this.labelTel.setText("");
            this.labelTel.setPrefWidth(1);
            this.labelTel.setPrefHeight(1);
            this.afficherTel = 1 ;
        }
        else {
            this.labelTel.setText("Telephone : " + myRdv.getEleve().getTel());
            this.labelTel.setPrefWidth(480);
            this.labelTel.setPrefHeight(27);
            this.afficherTel = 0 ;
        }
    }
    @FXML public void modifierAdresse() {
        if (this.afficherAdresse == 0) {
            this.labelAdresse.setText("");
            this.labelAdresse.setPrefWidth(1);
            this.labelAdresse.setPrefHeight(1);
            this.afficherAdresse = 1 ;
        }
        else {
            this.labelAdresse.setText("Adresse : " + myRdv.getEleve().getAdresse());
            this.labelAdresse.setPrefWidth(480);
            this.labelAdresse.setPrefHeight(27);
            this.afficherAdresse = 0 ;
        }
    }
    @FXML public void modifierMotdepasse() {
        if (this.afficherMotDePasse == 0) {
            this.labelMotDePasse.setText("");
            this.labelMotDePasse.setPrefWidth(1);
            this.labelMotDePasse.setPrefHeight(1);
            this.afficherMotDePasse = 1 ;
        }
        else {
            this.labelMotDePasse.setText("Mot de passe : " + myRdv.getEleve().getMdp());
            this.labelMotDePasse.setPrefWidth(450);
            this.labelMotDePasse.setPrefHeight(27);
            this.afficherMotDePasse = 0 ;
        }
    }
    @FXML public void AfficherMdp() {}

    public void enregistrerModification() throws SQLException {
        if (!this.inputNom.getText().equals("")) {this.myRdv.getEleve().setNom(this.inputNom.getText()) ; }
        if (!this.inputPrenom.getText().equals("")) {this.myRdv.getEleve().setPrenom(this.inputPrenom.getText()) ; }
        if (!this.inputEmail.getText().equals("")) {this.myRdv.getEleve().setEmail(this.inputEmail.getText()); }
        if (!this.inputTel.getText().equals("")) {this.myRdv.getEleve().setTel(this.inputTel.getText()); }
        if (!this.inputAdresse.getText().equals("")) {this.myRdv.getEleve().setAdresse(this.inputAdresse.getText()); }
        if (!this.inputMotdepasse.getText().equals("")) {this.myRdv.getEleve().setMdp(this.inputMotdepasse.getText()); }
        this.myRdv.getConnect().changeEleve(this.myRdv.getEleve());
    }

    @Override
    public void update() {

    }
}
