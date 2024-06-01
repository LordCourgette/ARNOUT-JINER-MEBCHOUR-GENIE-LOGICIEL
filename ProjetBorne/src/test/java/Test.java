import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Test {

    private Client client;
    private Date date;
    private Date debut;
    private Date fin;

    @BeforeEach
    public void setUp() throws ParseException {
        // Configurer Alice Martin comme cliente
        client = new Client("Martin", "Alice", "1 avenue des Champs-Elysees", "0987654321", "alice.martin@example.com", "", null);

        // Utiliser des formats de date et d'heure spécifiés
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        // Configurer les dates et heures pour les tests
        date = dateFormat.parse("2024-01-15");
        debut = timeFormat.parse("10:00");
        fin = timeFormat.parse("12:00");
    }

    @Test
    public void testVerifierDisponibilite_BornesDisponibles() {
        List<Borne> bornesDisponibles = Client.verifierDisponibilite(date, debut, fin);
        assertEquals(2, bornesDisponibles.size(), "Deux bornes devraient être disponibles");
    }

    @Test
    public void testVerifierDisponibilite_AucuneBorneDisponible() {
        // Simuler que toutes les bornes sont occupées
        List<Borne> bornes = Client.getAllBornes();
        for (Borne borne : bornes) {
            borne.getStatut().setNom("Occupée");
        }
        List<Borne> bornesDisponibles = Client.verifierDisponibilite(date, debut, fin);
        assertEquals(0, bornesDisponibles.size(), "Aucune borne ne devrait être disponible");
    }

    @Test
    public void testFormatDateInvalide() {
        String dateInvalide = "2024-13-01"; // Mois invalide
        assertThrows(IllegalArgumentException.class, () -> {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateInvalide);
        }, "Le format de la date est invalide, une exception devrait être lancée");
    }
}
