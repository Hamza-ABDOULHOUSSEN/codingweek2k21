package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.Observateur.Observateur;
import eu.telecomnancy.javafx.model.MyRdv;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;
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

    @FXML public void creerCompteProf() throws SQLException {
        myRdv.getConnect().getGestionnaireProf().addProfesseur(inputMotdepasse.getText(), inputNom.getText(), inputPrenom.getText(), inputEmail.getText(), inputTel.getText(), inputAdresse.getText()) ;
        this.inputNom.clear();
        this.inputPrenom.clear();
        this.inputEmail.clear();
        this.inputTel.clear();
        this.inputAdresse.clear();
        this.inputMotdepasse.clear();
    }

    @FXML public void creerCompteEleve() throws SQLException {
        myRdv.getConnect().getGestionnaireEleve().addEleve(inputMotdepasse.getText(), inputNom.getText(), inputPrenom.getText(), inputEmail.getText(), inputTel.getText(), inputAdresse.getText()) ;
        this.inputNom.clear();
        this.inputPrenom.clear();
        this.inputEmail.clear();
        this.inputTel.clear();
        this.inputAdresse.clear();
        this.inputMotdepasse.clear();
    }

    @FXML
    public void logoButton() throws IOException {
        PageAccueilController pac = new PageAccueilController(myRdv);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PageAccueil.fxml"));
        fxmlLoader.setControllerFactory(ic -> {
            if (ic.equals(eu.telecomnancy.javafx.controller.PageAccueilController.class)) return pac;
            else return null;
        });
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        myRdv.setScene(scene);
    }

    @Override
    public void update() {

    }
}

