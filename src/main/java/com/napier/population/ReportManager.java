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
        display.clearReportFile();
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

        //
        ArrayList<City> allCities = report.getAllCitiesByPopulation();
        display.writeCityReportToFile(allCities, "All Cities by Population Report");

        //
        ArrayList<City> citiesByContinent = report.getCitiesByContinentPopulationDesc();
        display.writeCityReportToFile(citiesByContinent, "Cities by Continent Population Report");

        //
        ArrayList<City> top50Cities = report.getTop50CitiesByPopulation();
        display.writeCityReportToFile(top50Cities, "Top 50 Cities by Population Report");

        //
        ArrayList<City> top10Cities = report.getTop10CitiesByContinentPopulation();
        display.writeCityReportToFile(top10Cities, "Top 10 Cities By Continent Population Report");

        //
        ArrayList<City> citiesByDistrict = report.getCitiesByDistrictPopulationDesc();
        display.writeCityReportToFile(citiesByDistrict, "Cities by District Population Report");

        //
        ArrayList<City> top5Cities = report.getTop5CitiesByRegionPopulation();
        display.writeCityReportToFile(top5Cities, "Top 5 Cities by Region Population Report");

        //
        ArrayList<City> top5CitiesByCountry = report.getTop5CitiesByRegionPopulation();
        display.writeCityReportToFile(top5CitiesByCountry, "No 15 Top 5 Cities by Country Population Report");

        ArrayList<City> cityByRegion = report.getAllCitiesByRegionPopulationDesc();
        display.writeCityReportToFile(cityByRegion, "No 9 Cities by Region Population Report");

        ArrayList<City> cityByCountry = report.getAllCitiesByCountryPopulationDesc();
        display.writeCityReportToFile(cityByCountry, "No 10  Cities by Country Population Report");

        //16. The topmost populated cities in each district.
        ArrayList<City> topMostCity = report.getTopCityByDistrictPopulation();
        display.writeCityReportToFile(topMostCity, "No 16 Top Most City by District Population Report");

    }

    /**
     * Generates and displays a country report.
     * Currently, a placeholder (no display logic yet).
     * Steps (future implementation):
     * 1. Create CountryReport instance
     * 2. Fetch country data
     * 3. Pass to Display for formatted output
     */
    public void generateCountryReport() {
        CountryReport report = new CountryReport(con);

        //
        ArrayList<Country> countriesByContinent = report.getCountriesByContinentPopulationDesc();
        display.writeCountryReportToFile(countriesByContinent, "Countries by Continent Population Report");

        //
        ArrayList<Country> countriesByPopulation = report.getAllCountriesByPopulationDesc();
        display.writeCountryReportToFile(countriesByPopulation, "All Countries by Population Report");

        //
        ArrayList<Country> top10Countries = report.getTop10CountriesByContinentPopulation();
        display.writeCountryReportToFile(top10Countries, "Top 10 Countries by Continent Population Report");

        //
        ArrayList<Country> top50Countries = report.getTop50CountriesByPopulation();
        display.writeCountryReportToFile(top50Countries, "Top 50 Countries by Population Report");

        //
        ArrayList<Country> top5Countries = report.getTop5CountriesPerRegion();
        display.writeCountryReportToFile(top5Countries, "Top 5 Countries by Population Report");
    }

    /**
     * Generates and displays a capital city report.
     * Currently, a placeholder (no display logic yet).
     * Steps (future implementation):
     * 1. Create CapitalCityReport instance
     * 2. Fetch capital city data
     * 3. Pass to Display for formatted output
     */
    public void generateCapitalCityReport() {
        CapitalCityReport report = new CapitalCityReport(con);

        // No 20 Retrieve the list of the top 50 most populated capital cities from the report
        ArrayList<City> top50capitals = report.getTop50CapitalCitiesByPopulation();
        // Write the retrieved data to a file with the given report title
        display.writeCapitalCityReport(top50capitals, "No 20 Top 50 Capital Cities by Population Report");

        ArrayList<City> capitals = report.getAllCapitalCitiesByContinentPopulationDesc();
        display.writeCityReportToFile(capitals, "No 18 Capital City by Continent Population Report");
    }

    /**
     * Generates and displays a population report.
     * Currently, a placeholder (no display logic yet).
     * Steps (future implementation):
     * 1. Create PopulationReport instance
     * 2. Fetch population data
     * 3. Pass to Display for formatted output
     */
    public void generatePopulationReport() {
        PopulationReport report = new PopulationReport(con);

        // Call the method in PopulationReport to get population data per region
        ArrayList<PeoplePopulation> regionPopulations = report.getRegionPopulationReport();
        // Write the population report to file using Display
        display.writePopulationReportToFile(regionPopulations, "Region");
    }
}
