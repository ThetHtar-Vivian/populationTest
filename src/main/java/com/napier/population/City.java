package com.napier.population;

public class City {
    private String name;
    private String country_code;
    private String district;
    private int population;

    // extra field for report
    private String country_name;
    private String continent;
    private String region;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry_code() { return country_code; }
    public void setCountry_code(String country_code) { this.country_code = country_code; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public int getPopulation() { return population; }
    public void setPopulation(int population) { this.population = population; }

    public String getCountry_name() { return country_name; }
    public void setCountry_name(String country_name) { this.country_name = country_name; }

    public String getContinent() { return continent; }
    public void setContinent(String continent) { this.continent = continent; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
}
