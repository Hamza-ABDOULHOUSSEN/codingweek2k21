package eu.telecomnancy.javafx.compte;

public class Professeur extends Compte {
    private int id_professeur;

    public Professeur(int id_professeur, String mdp, String nom, String prenom, String email, String tel, String adresse) {
        super(id_professeur, mdp, nom, prenom, email, tel, adresse) ;
        this.id_professeur = id_professeur ;
    }


}