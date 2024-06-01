public class Capteur {
    private String identifiant;

    public Capteur(String identifiant) {
        this.identifiant = identifiant;
    }

    public boolean detecterDepart() {
        return true;
    }

    // Getters and setters
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }
}