import java.util.Date;

public class Borne {
    private String identifiant;
    private StatutBorne statut;
    private Capteur capteur;

    public Borne(String identifiant, StatutBorne statut) {
        this.identifiant = identifiant;
        this.statut = statut;
        this.capteur = new Capteur("capteur_" + identifiant);
    }

    public boolean verifierDisponibilite(Date date, Date debut, Date fin) {
        return this.statut.getNom().equals("Disponible");
    }

    // Getters and setters
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public StatutBorne getStatut() {
        return statut;
    }

    public void setStatut(StatutBorne statut) {
        this.statut = statut;
    }

    public Capteur getCapteur() {
        return capteur;
    }

    public void setCapteur(Capteur capteur) {
        this.capteur = capteur;
    }
}