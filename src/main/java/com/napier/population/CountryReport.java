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

    public ArrayList<Country> getAllCountriesByPopulationDesc() {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT co.Code, co.Name AS CountryName, " +
                            "       c.Name AS CapitalName, " +
                            "       c.District, " +
                            "       co.Region, co.Continent, co.Population " +
                            "FROM country co " +
                            "LEFT JOIN city c ON co.Capital = c.ID " +
                            "ORDER BY co.Population DESC;";

            ResultSet rset = stmt.executeQuery(sql);

            while (rset.next()) {
                Country country = new Country();
                country.setCode(rset.getString("Code"));
                country.setName(rset.getString("CountryName"));
                country.setCapitalName(rset.getString("CapitalName"));
                country.setDistrict(rset.getString("District"));  // district from capital city
                country.setRegion(rset.getString("Region"));
                country.setContinent(rset.getString("Continent"));
                country.setPopulation(rset.getInt("Population"));
                countries.add(country);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get countries by population: " + e.getMessage());
        }
        return countries;
    }




}
