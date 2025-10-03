package com.napier.population;

import java.sql.*;
import java.util.ArrayList;

/**
 * Provides methods for generating city reports from the database.
 * Each method queries the database and returns a list of City objects
 * for use in reports.
 */
public class CityReport {

    /**
     * Active database connection used to execute queries
     */
    private Connection con;

    /**
     * Constructor initializes the CityReport with an active DB connection.
     *
     * @param con Active MySQL database connection
     */
    public CityReport(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves the top 10 most populated cities for each continent.
     * SQL uses ROW_NUMBER() window function to rank cities within each continent.
     * Only the top 10 cities per continent are returned, ordered by continent and population descending.
     *
     * @return ArrayList of City objects containing name, country, district, region, continent, and population
     */
    public ArrayList<City> getTop10CitiesByContinentPopulation() {
        ArrayList<City> cities = new ArrayList<>();

        try {
            // Create a statement to execute SQL query
            Statement stmt = con.createStatement();

            // SQL query:
            // - Join city and country tables
            // - Partition by continent and order by city population descending
            // - Use ROW_NUMBER() to select top 10 per continent
            String sql = "SELECT CityName, CountryName, District, Region, Continent, Population " + "FROM ( " + "    SELECT c.Name AS CityName, " + "           co.Name AS CountryName, " + "           c.District, " + "           co.Region, " + "           co.Continent, " + "           c.Population, " + "           ROW_NUMBER() OVER (PARTITION BY co.Continent ORDER BY c.Population DESC) AS rn " + "    FROM city c " + "    JOIN country co ON c.CountryCode = co.Code " + ") sub " + "WHERE rn <= 10 " + "ORDER BY Continent, Population DESC;";

            // Execute query and get results
            ResultSet rset = stmt.executeQuery(sql);

            // Process each row in the result set
            while (rset.next()) {
                City city = new City();
                city.setName(rset.getString("CityName"));            // Set city name
                city.setCountry_name(rset.getString("CountryName")); // Set country name
                city.setDistrict(rset.getString("District"));        // Set district
                city.setRegion(rset.getString("Region"));            // Set region
                city.setContinent(rset.getString("Continent"));      // Set continent
                city.setPopulation(rset.getInt("Population"));       // Set population

                cities.add(city); // Add city to list
            }
        } catch (SQLException e) {
            // Print error message if query fails
            System.out.println("Failed to get top 10 cities by continent: " + e.getMessage());
        }

        return cities; // Return list of top cities
    }

    /**
     * Retrieves the top 50 most populated cities in the world.
     * <p>
     * This method queries the database, joining the city and country tables
     * to get additional information such as country name, region, and continent.
     * Results are ordered by city population in descending order and limited to 50 entries.
     *
     * @return ArrayList of City objects containing city name, country, district, region, continent, and population
     */
    public ArrayList<City> getTop50CitiesByPopulation() {
        ArrayList<City> cities = new ArrayList<>();

        try {
            // Create a statement object to execute the SQL query
            Statement stmt = con.createStatement();

            // SQL query explanation:
            // - Select city name, country name, district, region, continent, and population
            // - Join city table with country table using CountryCode
            // - Order the results by population descending
            // - Limit to top 50 cities
            String sql =
                    "SELECT city.Name AS City, " +
                            "       country.Name AS Country, " +
                            "       city.District, " +
                            "       country.Region, " +
                            "       country.Continent, " +
                            "       city.Population " +
                            "FROM city " +
                            "JOIN country ON city.CountryCode = country.Code " +
                            "ORDER BY city.Population DESC " +
                            "LIMIT 50;";

            // Execute the query
            ResultSet rset = stmt.executeQuery(sql);

            // Process each row in the result set
            while (rset.next()) {
                City city = new City();

                city.setName(rset.getString("City"));              // Set city name
                city.setCountry_name(rset.getString("Country"));   // Set country name
                city.setDistrict(rset.getString("District"));      // Set district
                city.setRegion(rset.getString("Region"));          // Set region
                city.setContinent(rset.getString("Continent"));    // Set continent
                city.setPopulation(rset.getInt("Population"));     // Set population

                // Add city to the list
                cities.add(city);
            }
        } catch (SQLException e) {
            // Print error message if query fails
            System.out.println("Failed to get city report: " + e.getMessage());
        }

        // Return the list of top 50 cities
        return cities;
    }

}
