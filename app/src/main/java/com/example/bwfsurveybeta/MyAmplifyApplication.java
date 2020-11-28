package com.example.bwfsurveybeta;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.MenuItem;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.AmplifyConfiguration;
import com.amplifyframework.datastore.AWSDataStorePlugin;

public class MyAmplifyApplication extends Application {
    private static Context context;
    private static Activity CurrentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        MyAmplifyApplication.context = getApplicationContext();

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());

            AmplifyConfiguration config = AmplifyConfiguration.builder(getApplicationContext())
                    .devMenuEnabled(false)
                    .build();
            Amplify.configure(config,getApplicationContext());

            //Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }
    }

    public static Context getAppContext() {
        return MyAmplifyApplication.context;
    }

    public static void setCurrentActivity(Activity activity) {
        MyAmplifyApplication.CurrentActivity = activity;
    }

    public static Activity getCurrentActivity() {
        return MyAmplifyApplication.CurrentActivity;
    }


}

