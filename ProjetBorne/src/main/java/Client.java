import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private String nom;
    private String prenom;
    private String adresse;
    private String numeroMobile; // This is the primary key in the SQL table
    private String adresseEmail;
    private String numeroCarteDebit;
    private String plaquesImmatriculation;

    // Constructor
    public Client(String nom, String prenom, String adresse, String numeroMobile, String adresseEmail, String numeroCarteDebit, String plaquesImmatriculation) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numeroMobile = numeroMobile;
        this.adresseEmail = adresseEmail;
        this.numeroCarteDebit = numeroCarteDebit;
        this.plaquesImmatriculation = plaquesImmatriculation;
    }

    // Getters and Setters
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

    public List<Borne> verifierDisponibilite(Date date, Date debut, Date fin) {
        List<Borne> bornesDisponibles = new ArrayList<>();
        List<Borne> bornes = getAllBornes();  // Méthode fictive pour obtenir toutes les bornes
        for (Borne borne : bornes) {
            if (borne.verifierDisponibilite(date, debut, fin)) {
                bornesDisponibles.add(borne);
            }
        }
        return bornesDisponibles;
    }

    private List<Borne> getAllBornes() {
        // Cette méthode doit retourner la liste de toutes les bornes du système
        // Exemple simplifié avec des données fictives
        List<Borne> bornes = new ArrayList<>();
        bornes.add(new Borne("Borne1", new StatutBorne(1, "Disponible")));
        bornes.add(new Borne("Borne2", new StatutBorne(2, "Occupée")));
        bornes.add(new Borne("Borne3", new StatutBorne(1, "Disponible")));
        return bornes;
    }

}
