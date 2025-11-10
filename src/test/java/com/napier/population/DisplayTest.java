package com.napier.population;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisplayTest {

    private Display display;
    private String filename;

    @BeforeEach
    void setUp() {
        display = new Display();
        filename = "reports/DisplayTest.txt";
    }

    // COUNTRY REPORTS ------------------------------------------------------
    @Test
    void testWriteCountryReport_withValidData() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("AYA", "Ayarwaddy", "Yangon", "Yangon",
                "South East Asia", "Asia", 2300));

        int count = display.writeCountryReportToFile(countries, filename);
        assertEquals(1, count, "Expected 1 when writing one valid country record");
    }

    @Test
    void testWriteCountryReport_withEmptyList() {
        List<Country> countries = new ArrayList<>();
        int count = display.writeCountryReportToFile(countries, filename);
        assertEquals(0, count, "Expected 0 when writing an empty country list");
    }

    @Test
    void testWriteCountryReport_withNullCountry() {
        List<Country> countries = new ArrayList<>();
        countries.add(null);

        int count = display.writeCountryReportToFile(countries, filename);
        assertEquals(0, count, "Expected 0 when the country list contains a null record");
    }

    @Test
    void testWriteCountryReport_withNullList() {
        int count = display.writeCountryReportToFile(null, filename);
        assertEquals(0, count, "Expected 0 when the country list is null");
    }

    // CITY REPORTS ------------------------------------------------------

    @Test
    void testWriteCityReport_withValidData() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("AYA", "Myanmar", "Yangon", "Yangon", "South East Asia", 2300));

        int count = display.writeCityReportToFile(cities, filename);
        assertEquals(1, count, "Expected 1 when writing one valid city record");
    }

    @Test
    void testWriteCityReport_withEmptyList() {
        List<City> cities = new ArrayList<>();
        int count = display.writeCityReportToFile(cities, filename);
        assertEquals(0, count, "Expected 0 when writing an empty city list");
    }

    @Test
    void testWriteCityReport_withNullCity() {
        List<City> cities = new ArrayList<>();
        cities.add(null);

        int count = display.writeCityReportToFile(cities, filename);
        assertEquals(0, count, "Expected 0 when the city list contains a null record");
    }

    @Test
    void testWriteCityReport_withNullList() {
        int count = display.writeCityReportToFile(null, filename);
        assertEquals(0, count, "Expected 0 when the city list is null");
    }

    // CAPITAL CITY REPORTS ---------------------------------------------
    @Test
    void testWriteCapitalCityReport_withValidData() {
        List<City> capitals = new ArrayList<>();
        capitals.add(new City("AYA", "Myanmar", "Yangon", "Yangon", "South East Asia", 2300));

        int count = display.writeCapitalCityReportToFile(capitals, filename);
        assertEquals(1, count, "Expected 1 when writing one valid capital city record");
    }

    @Test
    void testWriteCapitalCityReport_withEmptyList() {
        List<City> capitals = new ArrayList<>();
        int count = display.writeCapitalCityReportToFile(capitals, filename);
        assertEquals(0, count, "Expected 0 when writing an empty capital city list");
    }

    @Test
    void testWriteCapitalCityReport_withNullCapital() {
        List<City> capitals = new ArrayList<>();
        capitals.add(null);

        int count = display.writeCapitalCityReportToFile(capitals, filename);
        assertEquals(0, count, "Expected 0 when the capital city list contains a null record");
    }

    @Test
    void testWriteCapitalCityReport_withNullList() {
        int count = display.writeCapitalCityReportToFile(null, filename);
        assertEquals(0, count, "Expected 0 when the capital city list is null");
    }

    // POPULATION REPORTS -----------------------------------------------
    @Test
    void testWritePopulationReport_withValidData() {
        List<PeoplePopulation> populations = new ArrayList<>();
        populations.add(new PeoplePopulation("Asia", 1000000, 600000, 400000));

        int count = display.writePopulationReportToFile(populations, "Continent", filename);
        assertEquals(1, count, "Expected 1 when writing one valid population record");
    }

    @Test
    void testWritePopulationReport_withEmptyList() {
        List<PeoplePopulation> populations = new ArrayList<>();
        int count = display.writePopulationReportToFile(populations, "Continent", filename);
        assertEquals(0, count, "Expected 0 when writing an empty population list");
    }

    @Test
    void testWritePopulationReport_withNullPopulation() {
        List<PeoplePopulation> populations = new ArrayList<>();
        populations.add(null);

        int count = display.writePopulationReportToFile(populations, "Continent", filename);
        assertEquals(0, count, "Expected 0 when the population list contains a null record");
    }

    @Test
    void testWritePopulationReport_withNullList() {
        int count = display.writePopulationReportToFile(null, "Continent", filename);
        assertEquals(0, count, "Expected 0 when the population list is null");
    }

    // OVERALL POPULATION REPORTS ---------------------------------------
    @Test
    void testWriteOverallPopulationReport_withValidData() {
        List<PeoplePopulation> populations = new ArrayList<>();
        populations.add(new PeoplePopulation("World", 8000000000L));

        int count = display.writeOverallPopulationReportToFile(populations, "World", filename);
        assertEquals(1, count, "Expected 1 when writing one valid overall population record");
    }

    @Test
    void testWriteOverallPopulationReport_withEmptyList() {
        List<PeoplePopulation> populations = new ArrayList<>();
        int count = display.writeOverallPopulationReportToFile(populations, "World", filename);
        assertEquals(0, count, "Expected 0 when writing an empty overall population list");
    }

    @Test
    void testWriteOverallPopulationReport_withNullOverallPopulation() {
        List<PeoplePopulation> populations = new ArrayList<>();
        populations.add(null);

        int count = display.writeOverallPopulationReportToFile(populations, "Continent", filename);
        assertEquals(0, count, "Expected 0 when the overall population list contains a null record");
    }

    @Test
    void testWriteOverallPopulationReport_withNullList() {
        int count = display.writeOverallPopulationReportToFile(null, "World", filename);
        assertEquals(0, count, "Expected 0 when the overall population list is null");
    }

    // LANGUAGE REPORTS -------------------------------------------------
    @Test
    void testWriteLanguageReport_withValidData() {
        List<CountryLanguage> languages = new ArrayList<>();
        languages.add(new CountryLanguage("Burmese", 30000000, 2.0));

        int count = display.writeLanguageReportToFile(languages, filename);
        assertEquals(1, count, "Expected 1 when writing one valid language record");
    }

    @Test
    void testWriteLanguageReport_withEmptyList() {
        List<CountryLanguage> languages = new ArrayList<>();
        int count = display.writeLanguageReportToFile(languages, filename);
        assertEquals(0, count, "Expected 0 when writing an empty language list");
    }

    @Test
    void testWriteLanguageReport_withNullLanguage() {
        List<CountryLanguage> languages = new ArrayList<>();
        languages.add(null);

        int count = display.writeLanguageReportToFile(languages, filename);
        assertEquals(0, count, "Expected 0 when the language list contains a null record");
    }

    @Test
    void testWriteLanguageReport_withNullList() {
        int count = display.writeLanguageReportToFile(null, filename);
        assertEquals(0, count, "Expected 0 when the language list is null");
    }
}