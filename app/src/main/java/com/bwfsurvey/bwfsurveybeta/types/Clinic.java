package com.bwfsurvey.bwfsurveybeta.types;

public class Clinic {

    private String communityName;
    private String name;

    public Clinic(String communityName, String name) {
        this.communityName = communityName;
        this.name = name;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
