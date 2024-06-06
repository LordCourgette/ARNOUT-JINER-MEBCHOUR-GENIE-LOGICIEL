import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import model.Client;
import service.ClientService;

public class ClientServiceTest {

    private ClientService clientService;

    @BeforeEach
    public void setUp() throws SQLException {
        // Initialisation du service avant chaque test
        clientService = new ClientService();
    }

    @Test
    public void testAddClient() throws SQLException {
        // Création d'un nouveau client
        Client client = new Client("John",
                "Doe",
                "123 Main St",
                "555-1234",
                "john@example.com",
                "123456789",
                "AB123BC");

        // Ajout du client
        clientService.addClient(client);

        // Vérification que le client a été ajouté avec succès
        assertNotNull(client.getId());
    }

    @Test
    public void testGetClientById() throws SQLException {
        // Ajouter un client pour le tester
        Client client = new Client("Jane", "Doe", "456 Elm St", "555-5678", "jane@example.com", "987654321", "AB123BC");
        clientService.addClient(client);

        // Récupérer le client par son ID
        Client retrievedClient = clientService.getClientById(client.getId());

        // Vérification que le client récupéré correspond au client ajouté
        assertEquals(client.getNom(), retrievedClient.getNom());
        assertEquals(client.getPrenom(), retrievedClient.getPrenom());
        assertEquals(client.getAdresse(), retrievedClient.getAdresse());
        assertEquals(client.getNumeroMobile(), retrievedClient.getNumeroMobile());
        assertEquals(client.getAdresseEmail(), retrievedClient.getAdresseEmail());
        assertEquals(client.getNumeroCarteDebit(), retrievedClient.getNumeroCarteDebit());
    }

    // Ajouter d'autres tests similaires pour tester les autres fonctionnalités du service ClientService
}
