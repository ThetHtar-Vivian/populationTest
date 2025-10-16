package com.napier.population;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * PopulationReport is responsible for generating
 * population-related reports from the database.
 */
public class PopulationReport {

    // Active database connection used to run queries
    private Connection con;

    /**
     * Constructor initializes the PopulationReport with a database connection.
     *
     * @param con Active connection to the database
     */
    public PopulationReport(Connection con) {
        this.con = con;
    }

    /**
     * No 24 Retrieves population data for each region, including:
     * - Total population
     * - Population living in cities
     * - Population not living in cities
     *
     * @return ArrayList<PeoplePopulation> population data per region
     */
    public ArrayList<PeoplePopulation> getRegionPopulationReport() {
        // List to store population data for each region
        ArrayList<PeoplePopulation> regionPopulations = new ArrayList<>();

        try {
            // Create a SQL statement
            Statement stmt = con.createStatement();

            // SQL query:
            // - Selects region name, total country population, and total city population per region
            // - Uses LEFT JOIN to include countries even if they have no cities
            // - Groups results by region
            String sql = "SELECT co.Region AS RegionName, " +
                    "SUM(co.Population) AS TotalPopulation, " +
                    "SUM(ci.Population) AS CityPopulation " +
                    "FROM country co " +
                    "LEFT JOIN city ci ON ci.CountryCode = co.Code " +
                    "GROUP BY co.Region;";

            // Execute the query
            ResultSet rset = stmt.executeQuery(sql);

            // Loop through each row of the result set
            while (rset.next()) {
                String region = rset.getString("RegionName");             // Get region name
                long totalPop = rset.getLong("TotalPopulation");         // Get total population of the region
                long cityPop = rset.getLong("CityPopulation");           // Get total population living in cities
                long nonCityPop = totalPop - cityPop;                    // Calculate population not living in cities

                // Create a PeoplePopulation object and add it to the list
                PeoplePopulation pop = new PeoplePopulation(region, totalPop, cityPop, nonCityPop);
                regionPopulations.add(pop);
            }

        } catch (SQLException e) {
            // Print error message if query fails
            System.out.println("Failed to get region population report: " + e.getMessage());
        }

        // Return the list of population data per region
        return regionPopulations;
    }

    /**
     * No 28 Retrieves the total population of each region.
     *
     * @return ArrayList<PeoplePopulation> list of regions with their total population
     */
    public ArrayList<PeoplePopulation> getRegionTotalPopulation() {
        // List to store total population data per region
        ArrayList<PeoplePopulation> regionPopulations = new ArrayList<>();

        try {
            // Create a SQL statement
            Statement stmt = con.createStatement();

            // SQL query:
            // - Selects region name and sum of population for that region
            // - Groups by region
            // - Orders the results by total population in descending order
            String sql = "SELECT Region, SUM(Population) AS TotalPopulation " +
                    "FROM country " +
                    "GROUP BY Region " +
                    "ORDER BY TotalPopulation DESC;";

            // Execute the query
            ResultSet rset = stmt.executeQuery(sql);

            // Loop through each row of the result set
            while (rset.next()) {
                String regionName = rset.getString("Region");            // Get region name
                long totalPopulation = rset.getLong("TotalPopulation"); // Get total population

                // Create a PeoplePopulation object with only the total population
                PeoplePopulation pp = new PeoplePopulation(regionName, totalPopulation);

                // Add the object to the list
                regionPopulations.add(pp);
            }

        } catch (SQLException e) {
            // Print error message if query fails
            System.out.println("Failed to get region total population: " + e.getMessage());
        }

        // Return the list of total populations per region
        return regionPopulations;
    }

    /**
     * Gets the population of people, people living in cities, and people not living in cities in each country.
     *
     * @return List of PeoplePopulation objects representing each country's population data.
     */
    public ArrayList<PeoplePopulation> getCountryPopulationReport() {
        ArrayList<PeoplePopulation> populations = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();

            // SQL: Calculates total, city, and non-city population per country
            String sql =
                    "SELECT " +
                            "    co.Name AS CountryName, " +
                            "    co.Population AS TotalPopulation, " +
                            "    COALESCE(SUM(ci.Population), 0) AS CityPopulation, " +
                            "    (co.Population - COALESCE(SUM(ci.Population), 0)) AS NonCityPopulation " +
                            "FROM country co " +
                            "LEFT JOIN city ci ON co.Code = ci.CountryCode " +
                            "GROUP BY co.Code, co.Name, co.Population " +
                            "ORDER BY co.Name;";

            ResultSet rset = stmt.executeQuery(sql);

            // Process each record
            while (rset.next()) {
                String level = rset.getString("CountryName");
                long total = rset.getLong("TotalPopulation");
                long cityPop = rset.getLong("CityPopulation");
                long nonCityPop = rset.getLong("NonCityPopulation");

                PeoplePopulation pop = new PeoplePopulation(level, total, cityPop, nonCityPop);
                populations.add(pop);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get population by country: " + e.getMessage());
        }

        return populations;
    }

}
