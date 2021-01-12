package com.bwfsurvey.bwfsurveybeta.types;

import com.amplifyframework.datastore.generated.model.AnswerType;

public class AnswerDef {
    private String name;
    private AnswerType type;
    private String desc;

    public AnswerDef(String name, AnswerType type, String desc) {
        this.name = name;
        this.type = type;
        this.desc = desc;
    }

    public AnswerDef(Config config){
        this.name = config.getName();
        this.type = AnswerType.valueOf(config.getValue());
        this.desc = config.getDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnswerType getType() {
        return type;
    }

    public void setType(AnswerType type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
