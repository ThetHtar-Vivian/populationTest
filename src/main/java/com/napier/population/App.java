package com.napier.population;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        DbConnection db = new DbConnection();
        db.connect();
        Connection con = db.getConnection();

        // Use Manager to generate all reports
        ReportManager manager = new ReportManager(con);
        manager.generateCityReport();
        manager.generateCountryReport();
        manager.generateCapitalCityReport();
//        manager.generateLanguageReport();

        db.disconnect();
    }
}
