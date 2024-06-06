package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Client;

public class ClientDAO {
    private Connection connection;

    public ClientDAO() throws SQLException {
        this.connection = DatabaseManager.getInstance().getConnection();
    }

    public void insertClient(Client client) {
        String query = "INSERT INTO Client (nom, prenom, adresse, numeroMobile, adresseEmail, numeroCarteDebit, plaquesImmatriculation) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getAdresse());
            stmt.setString(4, client.getNumeroMobile());
            stmt.setString(5, client.getAdresseEmail());
            stmt.setString(6, client.getNumeroCarteDebit());
            stmt.setString(7, client.getPlaquesImmatriculation());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                client.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client getClientById(int id) {
        try {
            String sql = "SELECT * FROM clients WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("adresse"),
                        resultSet.getString("numeroMobile"),
                        resultSet.getString("adresseEmail"),
                        resultSet.getString("numeroCarteDebit"),
                        resultSet.getString("referenceClient")
                );
            } else {
                return null; // ou lancez une exception si le client n'est pas trouvé
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
