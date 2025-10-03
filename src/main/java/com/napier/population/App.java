package com.napier.population;

import java.sql.Connection;

/**
 * Main application entry point.
 * - Connects to the database
 * - Initializes the ReportManager
 * - Generates reports (City, Country, Capital City, Language, etc.)
 * - Disconnects safely
 */
public class App {
    public static void main(String[] args) {
        // Create a new database connection manager
        DbConnection db = new DbConnection();

        // Connect to the database
        db.connect();

        // Retrieve the active JDBC Connection object
        Connection con = db.getConnection();

        /*
         * Use ReportManager to generate different reports.
         * ReportManager takes care of calling the correct
         * report classes (CityReport, CountryReport, etc.)
         * and uses Display to print results.
         */
        ReportManager manager = new ReportManager(con);

        // Generate City-related reports
        manager.generateCityReport();

        // Generate Country-related reports
        manager.generateCountryReport();

        // Generate Capital City-related reports
        manager.generateCapitalCityReport();

        // Generate People Population-related reports
        manager.generatePopulationReport();

        // Example: Generate Language-related reports (currently commented out)
        //        manager.generateLanguageReport();

        // Disconnect from the database once all reports are generated
        db.disconnect();
    }
}
