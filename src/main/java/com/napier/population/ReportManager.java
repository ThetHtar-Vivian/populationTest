package com.napier.population;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * ReportManager is responsible for coordinating between
 * report classes (e.g., CityReport, CountryReport, CapitalCityReport, PopulationReport)
 * and the Display class.
 * <p>
 * It acts as a "controller" layer:
 * - Calls the correct report class to fetch data from the database.
 * - Sends the result to Display for formatted output.
 */
public class ReportManager {
    /**
     * Database connection (passed in from DbConnection)
     */
    private final Connection con;

    /**
     * Display utility for printing reports
     */
    private final Display display;

    /**
     * Constructor initializes the ReportManager with
     * a database connection and a Display instance.
     *
     * @param con Active database connection
     */
    public ReportManager(Connection con) {
        this.con = con;
        this.display = new Display();
    }

    /**
     * Generates and displays a city report.
     * Example: Top 10 most populated cities in each continent.
     * Steps:
     * 1. Create CityReport instance
     * 2. Fetch top 10 cities by continent
     * 3. Pass data to Display for formatted output
     */
    public void generateCityReport() {
        // Create CityReport instance to query database
        CityReport report = new CityReport(con);

        System.out.println("\nGenerating Top 10 Cities by Continent Report");

        // Fetch top 10 cities by continent population
        ArrayList<City> top10Cities = report.getTop10CitiesByContinentPopulation();

        // Print the result using Display class
        display.printCityReport(top10Cities);
    }

    /**
     * Generates and displays a country report.
     * Currently a placeholder (no display logic yet).
     * Steps (future implementation):
     * 1. Create CountryReport instance
     * 2. Fetch country data
     * 3. Pass to Display for formatted output
     */
    public void generateCountryReport() {
        CountryReport report = new CountryReport(con);
        // Add your top 10 Asia report here
        System.out.println("\nGenerating Top 10 Populated Countries in Asia");
        ArrayList<Country> top10Countries = report.getTop10CountriesByContinentPopulation("Asia");
        display.printCountryReport(top10Countries);
    }

    /**
     * Generates and displays a capital city report.
     * Currently a placeholder (no display logic yet).
     * Steps (future implementation):
     * 1. Create CapitalCityReport instance
     * 2. Fetch capital city data
     * 3. Pass to Display for formatted output
     */
    public void generateCapitalCityReport() {
        CapitalCityReport report = new CapitalCityReport(con);
    }

    /**
     * Generates and displays a population report.
     * Currently a placeholder (no display logic yet).
     * Steps (future implementation):
     * 1. Create PopulationReport instance
     * 2. Fetch population data
     * 3. Pass to Display for formatted output
     */
    public void generatePopulationReport() {
        PopulationReport report = new PopulationReport(con);
    }
}
