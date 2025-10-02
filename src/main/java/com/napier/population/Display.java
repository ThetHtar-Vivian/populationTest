package com.napier.population;

import java.util.ArrayList;

/**
 * Handles displaying reports to the console.
 */
public class Display {

    /**
     * Prints a country report in a table format.
     * @param countries List of countries to display
     */
    public void printCountryReport(ArrayList<Country> countries) {
        if (countries == null || countries.isEmpty()) {
            System.out.println("No countries to display.");
            return;
        }

        // Print header
        System.out.printf("%-10s %-55s %-20s %-25s %-30s %-15s %-12s%n",
                "Code", "Country Name", "Capital", "District", "Region", "Continent", "Population");

        // Initialize counter for display ID (optional)
        int id = 1;

        // Print rows
        for (Country country : countries) {
            if (country == null) continue;

            System.out.printf("%-10s %-55s %-20s %-25s %-30s %-15s %-12d%n",
                    country.getCode(),
                    country.getName(),
                    country.getCapitalName(), // city name
                    country.getDistrict(),
                    country.getRegion(),
                    country.getContinent(),
                    country.getPopulation());
        }
    }

    /**
     * Prints a city report in a table format.
     * @param cities List of cities to display
     */
    public void printCityReport(ArrayList<City> cities) {
        if (cities == null || cities.isEmpty()) {
            System.out.println("No cities to display.");
            return;
        }

        // Print header with ID column
        System.out.printf("%-5s %-25s %-40s %-20s %-30s %-15s %-10s%n",
                "ID", "City", "Country", "District", "Region", "Continent", "Population");

        // Initialize counter
        int id = 1;

        // Print rows
        for (City city : cities) {
            if (city == null) continue;

            System.out.printf("%-5d %-25s %-40s %-20s %-30s %-15s %-10d%n",
                    id++,
                    city.getName(),
                    city.getCountry_name(),
                    city.getDistrict(),
                    city.getRegion(),
                    city.getContinent(),
                    city.getPopulation());
        }
    }

    /**
     * Prints the capital city report.
     */
    public void printCapitalCityReport(ArrayList<City> capitals) {
        if (capitals == null || capitals.isEmpty()) {
            System.out.println("No capital city data available.");
            return;
        }

        System.out.printf("%-5s %-30s %-45s %-30s %-15s %-12s%n",
                "ID", "City", "Country", "Region", "Continent", "Population");

        // Initialize counter
        int id = 1;

        for (City capital : capitals) {
            System.out.printf("%-5s %-30s %-45s %-30s %-15s %-12d%n",
                    id++,
                    capital.getName(),
                    capital.getCountry_name(),
                    capital.getRegion(),
                    capital.getContinent(),
                    capital.getPopulation());
        }
    }
}
