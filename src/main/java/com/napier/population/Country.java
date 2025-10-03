package com.napier.population;

/**
 * Represents a Country entity from the world database.
 * Includes both raw country table fields and extra fields
 * used for reporting (e.g., district, capital name).
 */
public class Country {
    /**
     * ISO country code (primary key)
     */
    private String code;

    /**
     * Country name
     */
    private String name;

    /**
     * Continent (Asia, Europe, North America, Africa, Oceania, Antarctica, South America)
     */
    private String continent;

    /**
     * Geographical region
     */
    private String region;

    /**
     * Surface area in square km
     */
    private double surface_area;

    /**
     * Year of independence (nullable in DB)
     */
    private int indep_year;

    /**
     * Country population
     */
    private int population;

    /**
     * Life expectancy (nullable in DB)
     */
    private double life_expectancy;

    /**
     * Gross National Product
     */
    private double gnp;

    /**
     * Old Gross National Product
     */
    private double gnp_old;

    /**
     * Local/native name of the country
     */
    private String local_name;

    /**
     * Government form (e.g., Republic, Monarchy)
     */
    private String government_form;

    /**
     * Head of state (nullable in DB)
     */
    private String head_of_state;

    /**
     * Capital city ID (FK → City.id, nullable in DB)
     */
    private int capital;

    /**
     * ISO 2-letter country code
     */
    private String code2;

    // ----------------------------------------------------------------
    // Extra fields for reporting (not directly in the Country table)
    // ----------------------------------------------------------------

    /**
     * District (from city join, for extended reports)
     */
    private String district;

    /**
     * Capital city name (from city join, for reports)
     */
    private String capitalName;

    // ----------------------------------------------------------------
    // Getters and Setters
    // ----------------------------------------------------------------

    /**
     * @return ISO country code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code ISO country code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return Country name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Country name
     */
    public void setName(String name) {
        this.name = name;
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
     * @return Region
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

    /**
     * @return District (from reports)
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district District (from reports)
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return Surface area
     */
    public double getSurface_area() {
        return surface_area;
    }

    /**
     * @param surface_area Surface area
     */
    public void setSurface_area(double surface_area) {
        this.surface_area = surface_area;
    }

    /**
     * @return Year of independence
     */
    public int getIndep_year() {
        return indep_year;
    }

    /**
     * @param indep_year Year of independence
     */
    public void setIndep_year(int indep_year) {
        this.indep_year = indep_year;
    }

    /**
     * @return Population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * @param population Population
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * @return Life expectancy
     */
    public double getLife_expectancy() {
        return life_expectancy;
    }

    /**
     * @param life_expectancy Life expectancy
     */
    public void setLife_expectancy(double life_expectancy) {
        this.life_expectancy = life_expectancy;
    }

    /**
     * @return GNP
     */
    public double getGnp() {
        return gnp;
    }

    /**
     * @param gnp GNP
     */
    public void setGnp(double gnp) {
        this.gnp = gnp;
    }

    /**
     * @return Old GNP
     */
    public double getGnp_old() {
        return gnp_old;
    }

    /**
     * @param gnp_old Old GNP
     */
    public void setGnp_old(double gnp_old) {
        this.gnp_old = gnp_old;
    }

    /**
     * @return Local name
     */
    public String getLocal_name() {
        return local_name;
    }

    /**
     * @param local_name Local name
     */
    public void setLocal_name(String local_name) {
        this.local_name = local_name;
    }

    /**
     * @return Government form
     */
    public String getGovernment_form() {
        return government_form;
    }

    /**
     * @param government_form Government form
     */
    public void setGovernment_form(String government_form) {
        this.government_form = government_form;
    }

    /**
     * @return Head of state
     */
    public String getHead_of_state() {
        return head_of_state;
    }

    /**
     * @param head_of_state Head of state
     */
    public void setHead_of_state(String head_of_state) {
        this.head_of_state = head_of_state;
    }

    /**
     * @return Capital city ID
     */
    public int getCapital() {
        return capital;
    }

    /**
     * @param capital Capital city ID
     */
    public void setCapital(int capital) {
        this.capital = capital;
    }

    /**
     * @return Capital city name
     */
    public String getCapitalName() {
        return capitalName;
    }

    /**
     * @param capitalName Capital city name
     */
    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    /**
     * @return 2-letter ISO code
     */
    public String getCode2() {
        return code2;
    }

    /**
     * @param code2 2-letter ISO code
     */
    public void setCode2(String code2) {
        this.code2 = code2;
    }
}
