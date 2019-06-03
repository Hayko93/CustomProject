package am.client.market.dao.sql;

import am.client.market.dao.ClientDao;
import am.client.market.model.Client;

import java.sql.*;
import java.util.Optional;

public class ClientDaoSql extends BaseSql implements ClientDao {


    @Override
    public Client isnert(Connection connection, Client client) throws SQLException {
        String query = "INSERT INTO user_client(name,surname,email,password,country,image_url) values(?,?,?,?,?,?);";
        try (PreparedStatement prSt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            prSt.setString(1, client.getName());
            prSt.setString(2, client.getSurname());
            prSt.setString(3, client.getEmail());
            prSt.setString(4, client.getPassword());
            prSt.setString(5, client.getCountry());
            prSt.setString(6, client.getImageUrl());
            prSt.execute();
            try (ResultSet res = prSt.executeQuery()) {
                if (res.next()) {
                    client.setId(res.getInt(1));
                }
            }
        }
        return client;
    }

    @Override
    public Optional<Client> fetch(String email, String password) throws SQLException {
        String query = "select * from user_client where email = ? and password = ?;";
        Client client = null;
        try (Connection connect = this.dataBaseConnectionFactory.getConnection();
             PreparedStatement prSt = connect.prepareStatement(query)) {
            prSt.setString(1, email);
            prSt.setString(2, password);
            try (ResultSet res = prSt.executeQuery()) {
                if (res.next()) {
                    client = createFromResultSet(res);
                }
            }
        }
        return client == null ? Optional.empty() : Optional.of(client);
    }

    private Client createFromResultSet(ResultSet res) throws SQLException {
        Client client = new Client();
        client.setId(res.getInt("id"));
        client.setName(res.getString("name"));
        client.setSurname(res.getString("surname"));
        client.setEmail(res.getString("email"));
        client.setPassword(res.getString("password"));
        client.setCountry(res.getString("country"));
        client.setImageUrl(res.getString("image_url"));
        return  client;
    }

    @Override
    public boolean clientExist(String email) throws SQLException {
        String query = "select email from user_client where email = ?;";

        try (Connection connection = this.dataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        }
        return false;
    }
}
