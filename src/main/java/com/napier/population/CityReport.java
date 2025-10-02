package com.napier.population;

import java.sql.*;
import java.util.ArrayList;

/**
 * Provides methods for generating city reports.
 */
public class CityReport {
    private Connection con;

    /**
     * Constructor requires an active DB connection.
     * @param con Active MySQL database connection
     */
    public CityReport(Connection con) {
        this.con = con;
    }

    /**
     * 3. All the cities in a continent organized by largest population to smallest.
     * Retrieves all cities in each continent organized by largest population to smallest.
     * @return List of City objects containing city name, country, district, region, continent, and population.
     */
    public ArrayList<City> getCitiesByContinentPopulationDesc() {
        ArrayList<City> cities = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();

            String sql = "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, " +
                    "co.Region, co.Continent, ci.Population " +
                    "FROM city ci " +
                    "JOIN country co ON ci.CountryCode = co.Code " +
                    "ORDER BY co.Continent, ci.Population DESC;"; // order by continent, then population descending

            ResultSet rset = stmt.executeQuery(sql);

            while (rset.next()) {
                City city = new City();

                city.setName(rset.getString("CityName"));
                city.setCountry_name(rset.getString("CountryName"));
                city.setDistrict(rset.getString("District"));
                city.setRegion(rset.getString("Region"));
                city.setContinent(rset.getString("Continent"));
                city.setPopulation(rset.getInt("Population"));

                cities.add(city);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get cities by continent population: " + e.getMessage());
        }

        return cities;
    }

}
