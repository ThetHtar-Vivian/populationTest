package com.napier.population;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PopulationReportTest {

    DbConnection db;
    Connection con;
    PopulationReport report;

    @BeforeEach
    void setUp() {
        db = new DbConnection();// Establish connection to the database
        con = db.connect("3306:3306", 10000);
        report = new PopulationReport(con);
    }

    @AfterEach
    void tearDown() {
        db.disconnect();
    }

    @Test
    void testGetCountryPopulationReport() {
        ArrayList<PeoplePopulation> countryPopulations = report.getCountryPopulationReport();

        assertNotNull(countryPopulations, "Result list should not be null");
        assertTrue(countryPopulations.size() > 0, "Expected results from the database");

        PeoplePopulation peoplePopulation = countryPopulations.get(0);
        assertTrue(peoplePopulation.getTotalPopulation() > 0);
    }
}