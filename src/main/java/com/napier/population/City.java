package com.napier.population;

/**
 * Represents a City entity from the database.
 * Includes both raw city table fields and
 * extra fields used for reporting (e.g., country, continent, region).
 */
public class City {
    /**
     * City name
     */
    private String name;

    /**
     * Country code (FK -> Country.Code)
     */
    private String country_code;

    /**
     * District where the city is located
     */
    private String district;

    /**
     * City population
     */
    private int population;

    // ----------------------------------------------------------------
    // Extra fields for reporting (not part of the raw city table)
    // ----------------------------------------------------------------

    /**
     * Country name (for report purposes, joined from Country table)
     */
    private String country_name;

    /**
     * Continent name (for extended reports)
     */
    private String continent;

    /**
     * Region within the continent (for extended reports)
     */
    private String region;

    // ----------------------------------------------------------------
    // Getters and Setters
    // ----------------------------------------------------------------

    /**
     * @return City name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name City name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Country code
     */
    public String getCountry_code() {
        return country_code;
    }

    /**
     * @param country_code Country code
     */
    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    /**
     * @return District name
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district District name
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return Population of the city
     */
    public int getPopulation() {
        return population;
    }

    /**
     * @param population Population of the city
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * @return Country name
     */
    public String getCountry_name() {
        return country_name;
    }

    /**
     * @param country_name Country name
     */
    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    /**
     * @return Continent name
     */
    public String getContinent() {
        return continent;
    }

    /**
     * @param continent Continent name
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * @return Region name
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region Region name
     */
    public void setRegion(String region) {
        this.region = region;
    }
}
