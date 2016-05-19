package com.donoroncall.server.connectors;

import com.donoroncall.server.utils.SqlUtils;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.AnyVal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vishnu on 20/1/16.
 */
public class MysqlConnector {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());
    public String dbc;

    private Connection connection;

    @Inject
    public MysqlConnector(Config config) throws SQLException {
        String host = config.getString("server.mysql.host");
        String port = config.getString("server.mysql.port");
        String user = config.getString("server.mysql.user");
        String password = config.getString("server.mysql.password");
        String db = config.getString("server.mysql.db");
        this.dbc = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + password;
        this.connection = DriverManager.getConnection(dbc);
    }

    public Connection getConnection() {
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

    public boolean executeQuery(String query) {
        try {
            LOG.debug("Executing query " + query);
            getConnection().createStatement().execute(query);
            return true;
        } catch (Exception e) {
            LOG.debug("Error for Query " + query, e);
            return false;
        }
    }
}
