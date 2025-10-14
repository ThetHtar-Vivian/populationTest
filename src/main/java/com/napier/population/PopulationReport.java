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

    /**
     * Active database connection used to run queries
     */
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

}
