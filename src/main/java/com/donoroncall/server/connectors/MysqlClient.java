package com.donoroncall.server.connectors;

import com.google.inject.Inject;
import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vishnu on 20/1/16.
 */
public class MysqlClient {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());
    private String dbc;

    private Connection connection;

    @Inject
    public MysqlClient(Config config) throws SQLException {
        String host = config.getString("ml.mysql.host");
        String port = config.getString("ml.mysql.port");
        String user = config.getString("ml.mysql.user");
        String password = config.getString("ml.mysql.password");
        String db = config.getString("ml.mysql.db");
        this.dbc = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + password;
        this.connection = DriverManager.getConnection(dbc);
    }

    private Connection getConnection() {
        try {
            if (connection.isClosed()) {
                connection = DriverManager.getConnection(dbc);
            }
        } catch (SQLException e) {
            return null;
        }
        return connection;
    }

    public ResultSet getResultSet(String query) {
        try {
            LOG.debug("Executing Query " + query);
            return getConnection().createStatement().executeQuery(query);
        } catch (SQLException e) {
            LOG.debug("Error for Query " + query, e);
            return null;
        }
    }
}
