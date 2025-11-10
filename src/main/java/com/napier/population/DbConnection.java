package com.napier.population;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DbConnection class manages the connection to the MySQL database.
 * - Establishes a connection with retries
 * - Provides access to the JDBC Connection object
 * - Safely disconnects from the database
 */
public class DbConnection {

    // Active database connection used to execute queries
    private Connection con = null;
    Logger log = LoggerFactory.getLogger(DbConnection.class);

    /**
     * Connects to the MySQL database.
     * Includes retry logic (up to 10 attempts) with a wait time
     * between retries, useful when the database container is starting up.
     */
    public Connection connect(String location, int delay, String className) {
        try {
            // Load Database driver
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.debug("Could not load SQL driver");
            throw new RuntimeException(e);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + location + "/world?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                        "root",
                        "abc123!@#");
                break;
            } catch (SQLException sqle) {
                log.debug("SQL Error", sqle);
            } catch (InterruptedException ie) {
                log.debug("Thread interrupted? Should not happen.");
            }
        }
        return con;
    }

    /**
     * Disconnects from the MySQL database if a connection exists.
     */
    public int disconnect() {
        if (con != null) {
            try {
                // Close active connection
                con.close();
                return 1;
            } catch (Exception e) {
                log.debug("Error closing connection to database", e);
            }
        }
        return 0;
    }
}
