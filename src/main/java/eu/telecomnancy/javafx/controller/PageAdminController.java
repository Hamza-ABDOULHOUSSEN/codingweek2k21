package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.Observateur.Observateur;
import eu.telecomnancy.javafx.model.MyRdv;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class PageAdminController implements Observateur {

    private MyRdv myRdv ;

    @FXML private TextField inputNom ;
    @FXML private TextField inputPrenom ;
    @FXML private TextField inputEmail ;
    @FXML private TextField inputTel ;
    @FXML private TextField inputAdresse ;
    @FXML private TextField inputMotdepasse ;

    public PageAdminController(MyRdv myRdv) {
        this.myRdv = myRdv ;
    }

    @FXML public void creerCompte() throws SQLException {
        myRdv.getConnect().getGestionnaireProf().addProfesseur(inputMotdepasse.getText(), inputNom.getText(), inputPrenom.getText(), inputEmail.getText(), inputTel.getText(), inputAdresse.getText()) ;
        this.inputNom.clear();
        this.inputPrenom.clear();
        this.inputEmail.clear();
        this.inputTel.clear();
        this.inputAdresse.clear();
        this.inputMotdepasse.clear();
    }

    @Override
    public void update() {

    }
}

