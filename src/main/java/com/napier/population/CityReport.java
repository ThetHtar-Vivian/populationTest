package com.napier.population;

import java.sql.*;
import java.util.ArrayList;

/**
 * Provides methods for generating city reports.
 */
public class CityReport {
    private Connection con;

    /**
     * Constructor requires an active DB connection.
     * @param con Active MySQL database connection
     */
    public CityReport(Connection con) {
        this.con = con;
    }

}
