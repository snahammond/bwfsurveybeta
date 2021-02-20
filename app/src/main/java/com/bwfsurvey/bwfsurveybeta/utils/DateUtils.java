package com.bwfsurvey.bwfsurveybeta.utils;

import com.amplifyframework.core.model.temporal.Temporal;

import java.text.SimpleDateFormat;

public class DateUtils {
    public static Temporal.Date parseDateWithDefault(Object s){
        Temporal.Date dateValue = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try{
            dateValue = new Temporal.Date(dateFormat.format(s));
        }catch (Exception x){
            dateValue = new Temporal.Date("1900-01-01");
        }
        return dateValue;
    }
}
