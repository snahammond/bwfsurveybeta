package com.example.bwfsurveybeta;

public class AnswerValue {
    private String name;
    private String value;
    private String desc;
    private String childname;
    private String childvalue;
    private String childdesc;
    private String parentname;
    private String parentvalue;
    private String parentdesc;

    public AnswerValue(String name, String value, String desc, String childname, String childvalue, String childdesc, String parentname, String parentvalue, String parentdesc) {
        this.name = name;
        this.value = value;
        this.desc = desc;
        this.childname = childname;
        this.childvalue = childvalue;
        this.childdesc = childdesc;
        this.parentname = parentname;
        this.parentvalue = parentvalue;
        this.parentdesc = parentdesc;
    }

    public AnswerValue(Config config){
        this.name = config.getName();
        this.value = config.getValue();
        this.desc = config.getDescription();
        this.childname = config.getChildName();
        this.childvalue = config.getChildValue();
        this.childdesc = config.getChildDescription();
        this.parentname = config.getParentName();
        this.parentvalue = config.getParentValue();
        this.parentdesc = config.getParentDescription();
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getChildname() {
        return childname;
    }

    public void setChildname(String childname) {
        this.childname = childname;
    }

    public String getChildvalue() {
        return childvalue;
    }

    public void setChildvalue(String childvalue) {
        this.childvalue = childvalue;
    }

    public String getChilddesc() {
        return childdesc;
    }

    public void setChilddesc(String childdesc) {
        this.childdesc = childdesc;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getParentvalue() {
        return parentvalue;
    }

    public void setParentvalue(String parentvalue) {
        this.parentvalue = parentvalue;
    }

    public String getParentdesc() {
        return parentdesc;
    }

    public void setParentdesc(String parentdesc) {
        this.parentdesc = parentdesc;
    }
}
