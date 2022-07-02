package com.bwfsurvey.bwfsurveybeta.types;

public class School {

    private String communityName;
    private String name;

    public School(String communityName, String name) {
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
