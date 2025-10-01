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
     *
     */
    public ArrayList<City> getTop10CitiesByContinentPopulation() {
        ArrayList<City> cities = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT CityName, CountryName, District, Region, Continent, Population " +
                            "FROM ( " +
                            "    SELECT c.Name AS CityName, " +
                            "           co.Name AS CountryName, " +
                            "           c.District, " +
                            "           co.Region, " +
                            "           co.Continent, " +
                            "           c.Population, " +
                            "           ROW_NUMBER() OVER (PARTITION BY co.Continent ORDER BY c.Population DESC) AS rn " +
                            "    FROM city c " +
                            "    JOIN country co ON c.CountryCode = co.Code " +
                            ") sub " +
                            "WHERE rn <= 10 " +
                            "ORDER BY Continent, Population DESC;";

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
            System.out.println("Failed to get top 10 cities by continent: " + e.getMessage());
        }
        return cities;
    }
}
