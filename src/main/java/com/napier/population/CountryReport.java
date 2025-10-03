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
     * Get all countries in the world organized by population (largest to smallest).
     * @return ArrayList of countries
     */

    public ArrayList<Country> getTop10CountriesByContinentPopulation() {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT Code, CountryName, CapitalName, District, Region, Continent, Population " +
                            "FROM ( " +
                            "   SELECT co.Code, co.Name AS CountryName, c.Name AS CapitalName, c.District, " +
                            "          co.Region, co.Continent, co.Population, " +
                            "          ROW_NUMBER() OVER (PARTITION BY co.Continent ORDER BY co.Population DESC) AS rn " +
                            "   FROM country co " +
                            "   LEFT JOIN city c ON co.Capital = c.ID " +
                            ") sub " +
                            "WHERE rn <= 10 " +  // top 10 for each continent
                            "ORDER BY Continent, Population DESC;";

            ResultSet rset = stmt.executeQuery(sql);

            while (rset.next()) {
                Country country = new Country();
                country.setCode(rset.getString("Code"));
                country.setName(rset.getString("CountryName"));
                country.setCapitalName(rset.getString("CapitalName"));
                country.setDistrict(rset.getString("District"));
                country.setRegion(rset.getString("Region"));
                country.setContinent(rset.getString("Continent"));
                country.setPopulation(rset.getInt("Population"));
                countries.add(country);
            }

            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Failed to get top 10 countries by continent: " + e.getMessage());
        }
        return countries;
    }

}
