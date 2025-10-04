package com.napier.population;

public class CountryLanguage {
    /**
     * Country code (FK -> country.code)
     */
    private String country_code;

    /**
     * Language name
     */
    private String language;

    /**
     * Is official? ('T' or 'F')
     */
    private String is_official;

    /**
     * Percentage of speakers (decimal(4,1))
     */
    private double percentage;
}
