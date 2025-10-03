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

        System.out.println("\nGenerating Top 10 Cities by Continent Report");
        ArrayList<City> top10Cities = report.getTop10CitiesByContinentPopulation();
        display.printCityReport(top10Cities);
    }

    public void generateCountryReport() {
        CountryReport report = new CountryReport(con);
        // Add your top 10 Asia report here
        System.out.println("\nGenerating Top 10 Populated Countries in Asia");
        ArrayList<Country> top10Countries = report.getTop10CountriesByContinentPopulation("Asia");
        display.printCountryReport(top10Countries);
    }

    public void generateCapitalCityReport() {
        CapitalCityReport report = new CapitalCityReport(con);

    }

}
