package com.itechart.stlab.contacts.pool;

import com.itechart.stlab.contacts.manager.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager_another_approach {
    private static final Logger LOGGER = LogManager.getLogger(DBManager_another_approach.class);
    private static final String URL = "database.url";
    private static final String USER = "database.user";
    private static final String PASSWORD = "database.password";

    public static Connection getConnection() {

        String url = ConfigurationManager.DATABASE.getProperty(URL);
        String user = ConfigurationManager.DATABASE.getProperty(USER);
        String password = ConfigurationManager.DATABASE.getProperty(PASSWORD);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.ERROR, "there is a promblem with connection to database", e);
            throw new RuntimeException("there is a promblem with connection to databse", e);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "there is a promblem with connection to database", e);
            throw new RuntimeException("there is a sql promblem with connection to databse", e);
        }


    }
}
