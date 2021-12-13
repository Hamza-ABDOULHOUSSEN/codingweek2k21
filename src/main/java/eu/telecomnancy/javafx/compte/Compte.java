package eu.telecomnancy.javafx.compte;

public abstract class Compte {

    private int id ;
    private String mdp ;
    private String nom ;
    private String prenom ;
    private String email ;
    private int tel ;
    private String adresse ;

    public Compte(int id, String mdp, String nom, String prenom, String email, int tel, String adresse) {
        this.id = id ;
        this.mdp = mdp ;
        this.nom = nom ;
        this.prenom = prenom ;
        this.email = email ;
        this.tel = tel ;
        this.adresse = adresse ;
    }
}
