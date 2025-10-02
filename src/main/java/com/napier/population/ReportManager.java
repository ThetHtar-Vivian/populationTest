package com.napier.population;

import java.sql.Connection;
import java.util.ArrayList;

public class ReportManager {
    private final Connection con;
    private final Display display;

    public ReportManager(Connection con) {
        this.con = con;
        this.display = new Display();
    }

    public void generateCityReport() {
        CityReport report = new CityReport(con);

        //3. All the cities in a continent organized by largest population to smallest.
        System.out.println("\nGenerate All Cities by Continent Population Report");
        ArrayList<City> cities = report.getCitiesByContinentPopulationDesc();
        display.printCityReport(cities);
    }

    public void generateCountryReport() {
        CountryReport report = new CountryReport(con);

    }

    public void generateCapitalCityReport() {
        CapitalCityReport report = new CapitalCityReport(con);

    }

}
