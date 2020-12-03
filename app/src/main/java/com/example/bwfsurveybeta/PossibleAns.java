package com.example.bwfsurveybeta;

import java.util.List;

public class PossibleAns {
    private String name;
    private String value;
    private String parent_name;
    private String parent_value;

    public PossibleAns(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getParent_value() {
        return parent_value;
    }

    public void setParent_value(String parent_value) {
        this.parent_value = parent_value;
    }
}
