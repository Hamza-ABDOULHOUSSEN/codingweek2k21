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

public class PageProfilEleveController implements Observateur {

    @FXML private TextField inputNom ;
    @FXML private TextField inputPrenom ;
    @FXML private TextField inputEmail ;
    @FXML private TextField inputTel ;
    @FXML private TextField inputAdresse ;
    @FXML private TextField inputMotdepasse ;


    @FXML public void modifierNom() {}
    @FXML public void modifierPrenom() {}
    @FXML public void modifierEmail() {}
    @FXML public void modifierTel() {}
    @FXML public void modifierAdresse() {}
    @FXML public void modifierMotdepasse() {}
    @FXML public void AfficherMdp() {}

















    @Override
    public void update() {

    }
}
