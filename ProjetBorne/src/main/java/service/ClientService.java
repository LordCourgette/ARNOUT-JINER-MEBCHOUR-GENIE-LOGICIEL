package service;

import data.ClientDAO;
import model.Client;

import java.sql.SQLException;

public class ClientService {
    private ClientDAO clientDAO;

    public ClientService() throws SQLException {
        this.clientDAO = new ClientDAO();
    }

    public void addClient(Client client) throws SQLException {
        clientDAO.insertClient(client);
    }

    public Client getClientById(int id) {
        return clientDAO.getClientById(id);
    }
}
