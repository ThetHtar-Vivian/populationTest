package com.napier.population;

public class Country {
    /** Country code (primary key) */
    private String code;

    /** Country name */
    private String name;

    /** Continent (one of: Asia, Europe, North America, Africa, Oceania, Antarctica, South America) */
    private String continent;

    /** Region */
    private String region;

    /** Surface area (decimal(10,2)) */
    private double surface_area;

    /** Year of independence (nullable) */
    private int indep_year;

    /** Population */
    private int population;

    /** Life expectancy (nullable, decimal(3,1)) */
    private double life_expectancy;

    /** GNP (nullable, decimal(10,2)) */
    private double gnp;

    /** GNPOld (nullable, decimal(10,2)) */
    private double gnp_old;

    /** Local name */
    private String local_name;

    /** Government form */
    private String government_form;

    /** Head of state (nullable) */
    private String head_of_state;

    /** Capital (nullable) — references city.id */
    private int capital;

    /** 2-letter country code */
    private String code2;
}
