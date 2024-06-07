import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import app.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import model.Client;
import service.ClientService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

public class InscriptionTest {

    @Mock
    private ClientService clientService;

    @Mock
    private UserInput userInput;

    @InjectMocks
    private CommandHandler commandHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        commandHandler = new CommandHandler(new Application());
        // Inject manually the mocked dependencies into the private fields
        setPrivateField(commandHandler, "clientService", clientService);
        setPrivateField(commandHandler, "userInput", userInput);
    }

    // methode pour instancier les attributs privés
    private void setPrivateField(Object target, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testInscription() {
        // Définir le comportement des mocks pour les entrées utilisateur
        when(userInput.getValidInput(eq("Veuillez saisir votre nom"), any(), any())).thenReturn("John");
        when(userInput.getValidInput(eq("Veuillez saisir votre prénom"), any(), any())).thenReturn("Doe");
        when(userInput.getValidInput(eq("Veuillez saisir votre adresse"), any(), any())).thenReturn("123 Main St");
        when(userInput.getValidInput(eq("Veuillez saisir votre adresse mail"), any(), any())).thenReturn("john.doe@example.com");
        when(userInput.getValidInput(eq("Veuillez saisir votre numéro de carte de crédit"), any(), any())).thenReturn("1234567812345678");
        when(userInput.getValidInput(eq("Veuillez saisir votre numéro mobile"), any(), any())).thenReturn("1234567890");
        when(userInput.getOptionalInput(eq("Veuillez saisir votre plaque d'immatriculation (optionnel, à laisser vide si voulu)"), any(), any())).thenReturn("ABC123");

        // Appeler la méthode inscription
        commandHandler.inscription();

        // Vérifier que les méthodes de userInput ont été appelées correctement
        verify(userInput).getValidInput(eq("Veuillez saisir votre nom"), any(), any());
        verify(userInput).getValidInput(eq("Veuillez saisir votre prénom"), any(), any());
        verify(userInput).getValidInput(eq("Veuillez saisir votre adresse"), any(), any());
        verify(userInput).getValidInput(eq("Veuillez saisir votre adresse mail"), any(), any());
        verify(userInput).getValidInput(eq("Veuillez saisir votre numéro de carte de crédit"), any(), any());
        verify(userInput).getValidInput(eq("Veuillez saisir votre numéro mobile"), any(), any());
        verify(userInput).getOptionalInput(eq("Veuillez saisir votre plaque d'immatriculation (optionnel, à laisser vide si voulu)"), any(), any());

        // Vérifier que le client est créé avec les bonnes informations
        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        try {
            verify(clientService).addClient(clientCaptor.capture());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Client capturedClient = clientCaptor.getValue();

        assertEquals("John", capturedClient.getNom());
        assertEquals("Doe", capturedClient.getPrenom());
        assertEquals("123 Main St", capturedClient.getAdresse());
        assertEquals("john.doe@example.com", capturedClient.getAdresseEmail());
        assertEquals("1234567812345678", capturedClient.getNumeroCarteDebit());
        assertEquals("1234567890", capturedClient.getNumeroMobile());
        assertEquals("ABC123", capturedClient.getPlaquesImmatriculation());
    }

    @Test
    public void testInvalidName() {
        // Définir le comportement du mock pour une entrée utilisateur invalide
        String invalidInput = "123";
        when(userInput.getValidInput(eq("Veuillez saisir votre nom"), any(), any())).thenReturn(invalidInput);

        // Rediriger la sortie standard vers un flux de sortie capturé
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Appeler la méthode inscription
        commandHandler.inscription();

        // Rétablir la sortie standard
        System.setOut(originalOut);

        // Récupérer la sortie standard capturée
        String output = outputStream.toString().trim();

        // Vérifier que le bon message d'erreur est affiché sur la sortie standard
        assertEquals("Nom invalide. Veuillez réessayer.", output);
    }






}
