package eu.telecomnancy.javafx.compte;

public class Eleve extends Compte {
    private int id_eleve;

    public Eleve(int id_eleve, String mdp, String nom, String prenom, String email, int tel, String adresse) {
        super(id_eleve, mdp, nom, prenom, email, tel, adresse);
        this.id_eleve = id_eleve ;
    }
}
