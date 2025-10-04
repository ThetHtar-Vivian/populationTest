package com.napier.population;

import java.sql.*;
import java.util.ArrayList;

/**
 * Handles generating country-related reports from the database.
 * This class interacts with the database using the provided Connection
 * and retrieves country data for use in reports.
 */
public class CountryReport {

    /**
     * Database connection object used to query the database
     */
    private Connection con;

    /**
     * Constructor initializes the CountryReport with an active database connection.
     *
     * @param con Active database connection (from DbConnection)
     */
    public CountryReport(Connection con) {
        this.con = con;
    }

    /**
     * 4. Retrieves the top 50 most populated countries from the database.
     * The method queries the 'country' and 'city' tables, joining them to get each country's capital name,
     * then orders the results by population in descending order and limits the output to 50 countries.
     *
     * @return An ArrayList of Country objects representing the top 50 most populated countries.
     */
    public ArrayList<Country> getTop50CountriesByPopulation() {
        // Create a list to store the retrieved Country objects
        ArrayList<Country> countries = new ArrayList<>();

        try {
            // Create a SQL statement object to execute the query
            Statement stmt = con.createStatement();

            // SQL query to select the top 50 most populated countries
            // Includes country code, name, capital city name (via LEFT JOIN), district, region, continent, and population
            String sql = "SELECT co.Code, co.Name AS CountryName, " +
                    "ci.Name AS CapitalName, ci.District, co.Region, co.Continent, co.Population " +
                    "FROM country co " +
                    "LEFT JOIN city ci ON co.Capital = ci.ID " +
                    "ORDER BY co.Population DESC " +
                    "LIMIT 50;";

            // Execute the SQL query and store the result set
            ResultSet rset = stmt.executeQuery(sql);

            // Loop through the result set and map each row to a Country object
            while (rset.next()) {
                Country country = new Country();
                country.setCode(rset.getString("Code"));                // Set country code
                country.setName(rset.getString("CountryName"));         // Set country name
                country.setCapitalName(rset.getString("CapitalName"));  // Set capital city name
                country.setDistrict(rset.getString("District"));        // Set district of the capital
                country.setRegion(rset.getString("Region"));            // Set region name
                country.setContinent(rset.getString("Continent"));      // Set continent name
                country.setPopulation(rset.getInt("Population"));       // Set population value

                // Add the populated Country object to the list
                countries.add(country);
            }

        } catch (SQLException e) {
            // Handle any SQL errors that occur during the query execution
            System.out.println("Failed to get top 50 countries by population: " + e.getMessage());
        }

        // Return the list of top 50 countries
        return countries;
    }
}
