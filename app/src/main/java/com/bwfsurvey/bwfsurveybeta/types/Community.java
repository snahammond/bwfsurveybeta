package com.bwfsurvey.bwfsurveybeta.types;

public class Community {
    private String countryName;
    private String name;

    public Community(String countryName, String name) {
        this.countryName = countryName;
        this.name = name;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
