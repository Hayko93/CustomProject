package am.client.market.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectionFactory {

    private final String DB_URL;
    private final String BD_USER;
    private final String DB_PASSWORD;

    private DataBaseConnectionFactory() {
        this.BD_USER = Settings.getInstance().getString("db.user");
        this.DB_PASSWORD = Settings.getInstance().getString("db.password");
        this.DB_URL = Settings.getInstance().getString("db.url");
        try {
            Class.forName(Settings.getInstance().getString("db.driver"));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public Connection getConnection(boolean autoCommit) throws SQLException {
        Connection connection = DriverManager.getConnection(this.DB_URL, this.DB_URL, this.DB_PASSWORD);
        connection.setAutoCommit(autoCommit);
        return connection;
    }

    public Connection getConnection() throws SQLException {
        return this.getConnection(true);
    }

    public static DataBaseConnectionFactory getInstance() {
        return DatabaseConnectionFactoryInstanceCreator.connectionFactory;
    }

    private static class DatabaseConnectionFactoryInstanceCreator {
        private static DataBaseConnectionFactory connectionFactory = new DataBaseConnectionFactory();
    }

}
