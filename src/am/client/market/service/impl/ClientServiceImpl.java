package am.client.market.service.impl;

import am.client.market.dao.ClientDao;
import am.client.market.exceptions.DataBaseException;
import am.client.market.exceptions.FileUploadException;
import am.client.market.model.Client;
import am.client.market.service.ClientService;
import am.client.market.util.DataBaseConnectionFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao;

    @Override
    public Client add(Client client, InputStream imageContent) throws DataBaseException {
        String imageName = UUID.nameUUIDFromBytes(client.getEmail().getBytes()).toString();
        String path = ClientServiceImpl.class.getClassLoader().getResource("../../images").getFile() + imageName;
        try (Connection connection = DataBaseConnectionFactory.getInstance().getConnection(false)) {
            if (imageContent != null) {
                try (OutputStream out = new FileOutputStream(path)) {
                    byte[] buffer = new byte[2048];
                    int readCount;
                    while ((readCount = imageContent.read(buffer)) > -1) {
                        out.write(buffer, 0, readCount);
                    }
                    client.setImageUrl("/images/" + imageName);
                } catch (IOException e) {
                    throw new FileUploadException(e);
                }
            } else {
                client.setImageUrl("/images/car.png");
            }
            try {
                client = this.clientDao.isnert(connection, client);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
            return client;
        } catch (SQLException | FileUploadException e) {
            if (client.getId() > 0) {
                new File(path).delete();
            }
            throw new DataBaseException(e);
        }
    }


    @Override
    public Optional<Client> get(String email, String password) throws DataBaseException {
        try {
            return this.clientDao.fetch(email, password);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }


    @Override
    public boolean clientExist(String email) throws DataBaseException {
        try {
            return this.clientDao.clientExist(email);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }
}