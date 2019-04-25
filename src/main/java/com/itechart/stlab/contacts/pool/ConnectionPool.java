package com.itechart.stlab.contacts.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final String DATASOURCE_NAME = "jdbc/contacts";
    private static DataSource dataSource;
    static {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup(DATASOURCE_NAME);
        } catch (NamingException e) {
            LOGGER.log(Level.ERROR, "promblem with connection to database", e);
            throw new RuntimeException("promblem with connection to databse", e);
        }
    }
    private ConnectionPool() { }
    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }




}
