package com.example.bwfsurveybeta;

import com.amplifyframework.core.model.annotations.ModelField;

public class Config {
    private String Type;
    private String Name;
    private String Value;
    private String Description;
    private String ChildName;
    private String ChildValue;
    private String ChildDescription;
    private String ParentName;
    private String ParentValue;
    private String ParentDescription;

    public Config() {
    }

    public Config(String type, String name, String value, String description, String childName, String childValue, String childDescription, String parentName, String parentValue, String parentDescription) {
        Type = type;
        Name = name;
        Value = value;
        Description = description;
        ChildName = childName;
        ChildValue = childValue;
        ChildDescription = childDescription;
        ParentName = parentName;
        ParentValue = parentValue;
        ParentDescription = parentDescription;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getChildName() {
        return ChildName;
    }

    public void setChildName(String childName) {
        ChildName = childName;
    }

    public String getChildValue() {
        return ChildValue;
    }

    public void setChildValue(String childValue) {
        ChildValue = childValue;
    }

    public String getChildDescription() {
        return ChildDescription;
    }

    public void setChildDescription(String childDescription) {
        ChildDescription = childDescription;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String parentName) {
        ParentName = parentName;
    }

    public String getParentValue() {
        return ParentValue;
    }

    public void setParentValue(String parentValue) {
        ParentValue = parentValue;
    }

    public String getParentDescription() {
        return ParentDescription;
    }

    public void setParentDescription(String parentDescription) {
        ParentDescription = parentDescription;
    }
}
