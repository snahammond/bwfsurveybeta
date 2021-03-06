package com.bwfsurvey.bwfsurveybeta.utils;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import static com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread;

public class ListUtils {
    public static void showZeroListAlert(String zeroListStringAlert, Context context){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(context)
                        .setTitle("No Data")
                        .setMessage(zeroListStringAlert)
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                        .setCanceledOnTouchOutside(false);
            }
        });
    }
}
