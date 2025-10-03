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
     * 3. Retrieves a list of all cities in the world, organized by continent and
     * sorted by city population in descending order within each continent.
     * The method joins the 'city' and 'country' tables to include relevant
     * information such as country name, district, region, and continent.
     *
     * @return An ArrayList of City objects containing city details ordered by continent and population.
     */
    public ArrayList<City> getCitiesByContinentPopulationDesc() {
        // Create a list to store the retrieved City objects
        ArrayList<City> cities = new ArrayList<>();

        try {
            // Create a SQL statement object to execute the query
            Statement stmt = con.createStatement();

            // SQL query:
            // - Joins 'city' and 'country' tables using the country code
            // - Selects city name, country name, district, region, continent, and population
            // - Orders the result first by continent (alphabetically),
            //   then by city population in descending order within each continent
            String sql = "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, " +
                    "co.Region, co.Continent, ci.Population " +
                    "FROM city ci " +
                    "JOIN country co ON ci.CountryCode = co.Code " +
                    "ORDER BY co.Continent, ci.Population DESC;";

            // Execute the SQL query and store the result set
            ResultSet rset = stmt.executeQuery(sql);

            // Loop through the result set and map each row to a City object
            while (rset.next()) {
                City city = new City();
                city.setName(rset.getString("CityName"));             // Set city name
                city.setCountry_name(rset.getString("CountryName"));  // Set the name of the country the city belongs to
                city.setDistrict(rset.getString("District"));         // Set the city's district
                city.setRegion(rset.getString("Region"));             // Set the region name
                city.setContinent(rset.getString("Continent"));       // Set the continent name
                city.setPopulation(rset.getInt("Population"));        // Set the city's population

                // Add the populated City object to the list
                cities.add(city);
            }

        } catch (SQLException e) {
            // Handle any SQL errors that occur during the query execution
            System.out.println("Failed to get cities by continent population: " + e.getMessage());
        }

        // Return the list of cities organized by continent and sorted by population
        return cities;
    }

}
