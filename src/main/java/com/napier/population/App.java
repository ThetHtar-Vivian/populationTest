package com.napier.population;

import java.sql.Connection;

/**
 * Main entry point for the Population Reporting Application.
 * This class is responsible for:
 * - Establishing a database connection
 * - Passing the connection to ReportManager
 * - Generating different types of reports
 * - Closing the database connection before exiting
 */
public class App {
    public static void main(String[] args) {
        // Create a new database connection instance
        DbConnection db = new DbConnection();

        // Establish connection to the database
        Connection con = db.connect(
                "jdbc:mysql://db:3306/world?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root",
                "abc123!@#"
        );

        // Initialize ReportManager with the database connection
        ReportManager manager = new ReportManager(con);

        // Generate different reports
        manager.generateCityReport();           // Report on cities
        manager.generateCountryReport();        // Report on countries
        manager.generateCapitalCityReport();    // Report on capital cities
        manager.generatePopulationReport();     // Report on people population
        manager.generateLanguageReport();       // Report on languages

        // Close the database connection before exiting
        db.disconnect();
    }
}
