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

    }

    public void generateCountryReport() {
        CountryReport report = new CountryReport(con);

    }

    public void generateCapitalCityReport() {
        CapitalCityReport report = new CapitalCityReport(con);

    }

}
