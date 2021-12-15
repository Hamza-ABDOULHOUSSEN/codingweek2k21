package eu.telecomnancy.javafx.compte;

public abstract class Compte {

    private int id ;
    private String mdp ;

    public int getId() {
        return id;
    }

    public String getMdp() {
        return mdp;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getAdresse() {
        return adresse;
    }

    private String nom ;
    private String prenom ;
    private String email ;
    private String tel ;
    private String adresse ;

    public Compte(int id, String mdp, String nom, String prenom, String email, String tel, String adresse) {
        this.id = id ;
        this.mdp = mdp ;
        this.nom = nom ;
        this.prenom = prenom ;
        this.email = email ;
        this.tel = tel ;
        this.adresse = adresse ;
    }
}
