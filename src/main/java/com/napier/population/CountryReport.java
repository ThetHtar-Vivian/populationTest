package com.napier.population;

import java.sql.*;
import java.util.ArrayList;

public class CountryReport {

    private Connection con;

    public CountryReport(Connection con) {
        this.con = con;
    }

    public ArrayList<Country> getTop50CountriesByPopulation() {
        ArrayList<Country> countries = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();

            String sql = "SELECT co.Code, co.Name AS CountryName, " +
                    "ci.Name AS CapitalName, ci.District, co.Region, co.Continent, co.Population " +
                    "FROM country co " +
                    "LEFT JOIN city ci ON co.Capital = ci.ID " +
                    "ORDER BY co.Population DESC " +
                    "LIMIT 50;";

            ResultSet rset = stmt.executeQuery(sql);

            while (rset.next()) {
                Country country = new Country();
                country.setCode(rset.getString("Code"));
                country.setName(rset.getString("CountryName"));
                country.setCapitalName(rset.getString("CapitalName")); // city name
                country.setDistrict(rset.getString("District"));
                country.setRegion(rset.getString("Region"));
                country.setContinent(rset.getString("Continent"));
                country.setPopulation(rset.getInt("Population"));
                countries.add(country);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get top 50 countries by population: " + e.getMessage());
        }

        return countries;
    }


}
