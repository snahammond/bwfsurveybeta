package com.bwfsurvey.bwfsurveybeta.types;

public class Validation {
    private String name;
    private boolean mandatory;
    private Object defaultValue;

    public Validation(String name,boolean mandatory, Object defaultValue) {
        this.name = name;
        this.mandatory = mandatory;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }
}
