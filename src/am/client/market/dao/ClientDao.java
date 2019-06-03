package am.client.market.dao;

import am.client.market.model.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface ClientDao {

    Client isnert(Connection connection, Client client) throws SQLException;

    Optional<Client> fetch(String email, String password) throws SQLException;

    boolean clientExist(String email) throws SQLException;

}
