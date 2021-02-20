package com.bwfsurvey.bwfsurveybeta.utils;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.bwfsurvey.bwfsurveybeta.types.Interchange;

import java.util.ArrayList;

public class InterchangeUtils {

    //this function will return a list of invalid interchanges
    public static ArrayList<Interchange> validateUserAns(ArrayList<Interchange> interchangesWithUserAns) {
        Log.i("Tutorial", "we are now validating " );
        ArrayList<Interchange> invalidinterchange = new ArrayList<>();
        for(Interchange interchange: interchangesWithUserAns){
            //check for its validation
            //Log.i("Tutorial", "interchange: "+interchange.getValidation().getName() +" mandatory: "+interchange.getValidation().isMandatory() + "user answer: "+interchange.getAnswer().getAns());
            if(!interchange.isValid()){
                invalidinterchange.add(interchange);
                Log.i("Tutorial", "invalid interchange: "+interchange.getValidation().getName() +" mandatory: "+interchange.getValidation().isMandatory() + " default value: "+interchange.getValidation().getDefaultValue() + "user answer: "+interchange.getAnswer().getAns());
            }
        }
        return invalidinterchange;
    }

    public static void showInvalidSurveyAlert(ArrayList<Interchange> invalideInterchanges, Context context){
        String invalidInterchangesStr = "[";
        for(Interchange interchange : invalideInterchanges){
            invalidInterchangesStr += interchange.getInterchangeNumber()+",";
        }
        invalidInterchangesStr += "]";

        new AlertDialog.Builder(context)
                .setTitle("Invalid Questions")
                .setMessage("The questions " +invalidInterchangesStr+ " have not been correctly answered \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    public static Object getInterchangeAns(String interchangeName,ArrayList<Interchange> validatedInterchangesWithAns){
        Object ans = null;
        Interchange foundInterchange = null;
        for(Interchange interchange : validatedInterchangesWithAns){
            if(interchange.getName().contentEquals(interchangeName)){
                ans = interchange.getAnswer().getAns();
                foundInterchange = interchange;
            }
        }
        if(ans==null){
            ans = foundInterchange.getValidation().getDefaultValue();
        }
        return ans;
    }
}
