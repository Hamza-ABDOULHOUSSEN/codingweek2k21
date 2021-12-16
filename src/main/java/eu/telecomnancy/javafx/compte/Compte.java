package eu.telecomnancy.javafx.compte;

public abstract class Compte {

    private int id ;
    private String mdp ;
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

    public void setId(int id) { this.id = id; }
    public void setMdp(String mdp) { this.mdp = mdp; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setTel(String tel) { this.tel = tel; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
}
