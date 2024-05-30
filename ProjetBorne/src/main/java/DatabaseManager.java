import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/borne";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void insertClient(Client client) {
        String query = "INSERT INTO Client (nom, prenom, adresse, numeroMobile, adresseEmail, numeroCarteDebit, plaquesImmatriculation) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getAdresse());
            stmt.setString(4, client.getNumeroMobile());
            stmt.setString(5, client.getAdresseEmail());
            stmt.setString(6, client.getNumeroCarteDebit());
            stmt.setString(7, client.getPlaquesImmatriculation());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
