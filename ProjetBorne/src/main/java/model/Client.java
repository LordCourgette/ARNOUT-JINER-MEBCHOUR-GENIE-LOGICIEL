package model;

public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String numeroMobile; // This is the primary key in the SQL table
    private String adresseEmail;
    private String numeroCarteDebit;
    private String plaquesImmatriculation;

    // Constructeurs, getters et setters

    public Client(String nom, String prenom, String adresse, String numeroMobile, String adresseEmail, String numeroCarteDebit, String plaquesImmatriculation) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numeroMobile = numeroMobile;
        this.adresseEmail = adresseEmail;
        this.numeroCarteDebit = numeroCarteDebit;
        this.plaquesImmatriculation = plaquesImmatriculation;
    }

    public Client(int id, String nom, String prenom, String adresse, String numeroMobile, String adresseEmail, String numeroCarteDebit, String plaquesImmatriculation) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numeroMobile = numeroMobile;
        this.adresseEmail = adresseEmail;
        this.numeroCarteDebit = numeroCarteDebit;
        this.plaquesImmatriculation = plaquesImmatriculation;
    }

    // Getters et Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroMobile() {
        return numeroMobile;
    }

    public void setNumeroMobile(String numeroMobile) {
        this.numeroMobile = numeroMobile;
    }

    public String getAdresseEmail() {
        return adresseEmail;
    }

    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }

    public String getNumeroCarteDebit() {
        return numeroCarteDebit;
    }

    public void setNumeroCarteDebit(String numeroCarteDebit) {
        this.numeroCarteDebit = numeroCarteDebit;
    }

    public String getPlaquesImmatriculation() {
        return plaquesImmatriculation;
    }

    public void setPlaquesImmatriculation(String plaquesImmatriculation) {
        this.plaquesImmatriculation = plaquesImmatriculation;
    }
}
