package am.client.market.service;

import am.client.market.exceptions.DataBaseException;
import am.client.market.model.Client;

import java.io.InputStream;
import java.util.Optional;

public interface ClientService {

    Client add(Client client, InputStream imageContent) throws DataBaseException;

    Optional<Client> get(String email, String password)throws  DataBaseException;

    boolean clientExist(String email) throws DataBaseException;
}
