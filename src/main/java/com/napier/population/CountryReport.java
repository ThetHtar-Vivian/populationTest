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
         * Get the top 10 populated countries in a given continent.
         * @param continent The continent name
         * @return ArrayList of Country objects
         */
        public ArrayList<Country> getTop10CountriesByContinentPopulation(String continent) {
            ArrayList<Country> countries = new ArrayList<>();
            try {
                PreparedStatement stmt = con.prepareStatement(
                        "SELECT co.Code, co.Name AS CountryName, co.Capital, co.Region, co.Continent, co.Population, " +
                                "c.District FROM country co " +
                                "LEFT JOIN city c ON co.Capital = c.ID " +
                                "WHERE co.Continent = ? " +
                                "ORDER BY co.Population DESC " +
                                "LIMIT 10;"
                );
                stmt.setString(1, continent);

                ResultSet rset = stmt.executeQuery();

                while (rset.next()) {
                    Country country = new Country();
                    country.setCode(rset.getString("Code"));
                    country.setName(rset.getString("CountryName"));
                    country.setCapital(rset.getInt("Capital"));
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
