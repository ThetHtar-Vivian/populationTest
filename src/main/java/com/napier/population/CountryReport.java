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
     * 2. All the countries in a continent organized by largest population to smallest.
     * Retrieves all countries grouped by continent, ordered by population descending.
     * Uses capital city's district for the report.
     * @return List of Country objects containing code, name, capital, district, region, continent, and population.
     */
    public ArrayList<Country> getCountriesByContinentPopulationDesc() {
        ArrayList<Country> countries = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();

            // Join country with city to get capital name and capital district
            String sql = "SELECT c.Code, c.Name AS CountryName, ci.Name AS CapitalName, ci.District AS CapitalDistrict, " +
                    "c.Region, c.Continent, c.Population " +
                    "FROM country c " +
                    "LEFT JOIN city ci ON c.Capital = ci.ID " +
                    "ORDER BY c.Continent, c.Population DESC;";

            ResultSet rset = stmt.executeQuery(sql);

            while (rset.next()) {
                Country country = new Country();

                country.setCode(rset.getString("Code"));
                country.setName(rset.getString("CountryName"));
                country.setCapitalName(rset.getString("CapitalName"));      // Capital city name
                country.setDistrict(rset.getString("CapitalDistrict"));     // Capital's district
                country.setRegion(rset.getString("Region"));
                country.setContinent(rset.getString("Continent"));
                country.setPopulation(rset.getInt("Population"));

                countries.add(country);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get countries by continent population: " + e.getMessage());
        }

        return countries;
    }

    /**
     * Retrieves the top 5 most populated countries per region.
     * Skips countries with null or zero population.
     *
     * @return List of Country objects containing code, name, capital, district, region, continent, and population
     */
    public ArrayList<Country> getTop5CountriesPerRegion() {
        ArrayList<Country> countries = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();

            // SQL: Top 5 populated countries per region, skip null/0 population
            String sql = "SELECT Code, CountryName, CapitalName, CapitalDistrict, Region, Continent, Population\n" +
                    "            FROM (\n" +
                    "                SELECT \n" +
                    "                    c.Code,\n" +
                    "                    c.Name AS CountryName,\n" +
                    "                    ci.Name AS CapitalName,\n" +
                    "                    ci.District AS CapitalDistrict,\n" +
                    "                    c.Region,\n" +
                    "                    c.Continent,\n" +
                    "                    c.Population,\n" +
                    "                    ROW_NUMBER() OVER (PARTITION BY c.Region ORDER BY c.Population DESC) AS rn\n" +
                    "                FROM country c\n" +
                    "                LEFT JOIN city ci ON c.Capital = ci.ID\n" +
                    "                WHERE c.Population IS NOT NULL AND c.Population > 0\n" +
                    "            ) AS sub\n" +
                    "            WHERE rn <= 5\n" +
                    "            ORDER BY Region, Population DESC;";

            ResultSet rset = stmt.executeQuery(sql);

            while (rset.next()) {
                Country country = new Country();
                country.setCode(rset.getString("Code"));
                country.setName(rset.getString("CountryName"));
                country.setCapitalName(rset.getString("CapitalName"));      // Capital city name
                country.setDistrict(rset.getString("CapitalDistrict"));     // Capital's district
                country.setRegion(rset.getString("Region"));
                country.setContinent(rset.getString("Continent"));
                country.setPopulation(rset.getInt("Population"));
                countries.add(country);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get top 5 countries per region: " + e.getMessage());
        }

        return countries;
    }

}
