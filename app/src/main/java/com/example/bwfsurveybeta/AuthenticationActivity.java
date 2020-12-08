package com.example.bwfsurveybeta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.AmplifyConfiguration;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.generated.model.InitialSurvey;
import com.amplifyframework.hub.HubChannel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AuthenticationActivity extends FragmentActivity implements ConfirmSignUp.ConfirmSignUpListener{
    private static String AuthenticationState = "LOGIN";
    private static String UserState = "SIGNED_OUT";

    EditText editTextUserfamilyname;
    EditText editTextUsergivenname;
    EditText editTextEmailAddress;
    EditText editTextPassword;
    String uniqueBWEName = null;
    String authenticatedName = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        doAuthentication();
    }

    public void doAuthentication(){
        //get the current authenticated user from local
        AuthUser authUser = Amplify.Auth.getCurrentUser();
        if(authUser!=null){
            //user is signed-in
            uniqueBWEName = authUser.getUsername();
            //could be offline or online
            //try to get the Attrubutes if we cannot get it we will use his email to welcome him;
            Amplify.Auth.fetchUserAttributes(
                attributes -> {
                    //user is online
                    String firstname = null;
                    String surname = null;
                    for(AuthUserAttribute attribute : attributes)
                    {
                        if(attribute.getKey().getKeyString().contentEquals(AuthUserAttributeKey.givenName().getKeyString())){
                            firstname = attribute.getValue();
                        }
                        if(attribute.getKey().getKeyString().contentEquals(AuthUserAttributeKey.familyName().getKeyString())){
                            surname = attribute.getValue();
                        }
                    }
                    authenticatedName = firstname +" "+ surname;
                    Log.i("Tutorials", "user online uniqueBWEName: " + uniqueBWEName + " authenticatedName: " +authenticatedName);

                    try {
                        MyAmplifyApplication.namebwe=uniqueBWEName;
                        //MyAmplifyApplication.namebwe="invalidUserName";
                        //getFamilysAndStartAuthenticatedScreen(true);
                        clearSyncStartDataStoreAndStartAuthenticatedScreen();
                    }catch (Exception c){
                        MyAmplifyApplication.namebwe=uniqueBWEName;
                        //MyAmplifyApplication.namebwe="invalidUserName";
                        //getFamilysAndStartAuthenticatedScreen(true);
                        clearSyncStartDataStoreAndStartAuthenticatedScreen();
                    }

                },
                error ->{
                    //the user is offline
                    authenticatedName = uniqueBWEName;
                    Log.i("Tutorials", "user offline uniqueBWEName: " + uniqueBWEName + " authenticatedName: " +authenticatedName);

                    try {
                        MyAmplifyApplication.namebwe=uniqueBWEName;
                        //MyAmplifyApplication.namebwe="invalidusername";
                        startDataStoreAndStartAuthenticatedScreen();
                    }catch (Exception c){
                        MyAmplifyApplication.namebwe=uniqueBWEName;
                        //MyAmplifyApplication.namebwe="invalidusername";
                        startDataStoreAndStartAuthenticatedScreen();
                    }
                });
        }else{
            //user is not sign-in
            Log.i("Tutorials", "user not signed in" );
            //ask the user to log in or sign up if he is a new user
            runOnUiThread(new Runnable() {
                public void run() {
                    showAuthenticationScreen();
                }
            });
        }

    }

    public void showAuthenticatedScreen(String authenticatedName){
        setContentView(R.layout.activity_authenticated);
        TextView textAuthenticated = (TextView) findViewById(R.id.textAuthenticated);
        textAuthenticated.setText(authenticatedName);

        Button button_continueAs = (Button) findViewById(R.id.button_continueAs);
        button_continueAs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //go to menu screen
                Intent i = new Intent(getApplicationContext(),MenuActivity.class);
                i.putExtra("NAME_BWE", uniqueBWEName);
                startActivity(i);
            }
        });
    }

    private void showAuthenticationScreen() {
        setContentView(R.layout.activity_authentication);
        editTextUserfamilyname = (EditText) findViewById(R.id.editTextUserfamilyname);
        editTextUsergivenname = (EditText) findViewById(R.id.editTextUsergivenname);
        editTextEmailAddress = (EditText) findViewById(R.id.editTextEmailAddress);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        Button button_login = (Button) findViewById(R.id.button_login);
        Button button_signup = (Button) findViewById(R.id.button_signup);

        button_signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //when signup is clicked
                if(AuthenticationState=="LOGIN"){
                    AuthenticationState = "SIGNUP";

                    editTextUserfamilyname.setVisibility(View.VISIBLE);
                    editTextUsergivenname.setVisibility(View.VISIBLE);
                    button_login.setText(R.string.signup);
                    button_signup.setText(R.string.Login);
                }else if(AuthenticationState=="SIGNUP"){
                    AuthenticationState = "LOGIN";

                    editTextUserfamilyname.setVisibility(View.GONE);
                    editTextUsergivenname.setVisibility(View.GONE);
                    button_login.setText(R.string.Login);
                    button_signup.setText(R.string.signup);
                }
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = editTextEmailAddress.getText().toString();
                String password = editTextPassword.getText().toString();
                if(AuthenticationState=="LOGIN"){
                    signin(email,password);
                }else{
                    String surname = editTextUserfamilyname.getText().toString();
                    String firstname = editTextUsergivenname.getText().toString();
                    signup(surname,firstname,email,password);
                }
            }
        });
    }

    public void showConfirmSignUp() {
        DialogFragment newFragment = new ConfirmSignUp();
        newFragment.show(getSupportFragmentManager(), "confirmSignUp");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog,String confirmationCode) {
        Amplify.Auth.confirmSignUp(
                editTextEmailAddress.getText().toString(),
                confirmationCode,
                result -> {
                    //go to menu screen //sign in automatically and move to next screen
                    String email = editTextEmailAddress.getText().toString();
                    String password = editTextPassword.getText().toString();
                    signin(email,password);
                },
                error -> {
                    Log.e("Tutorials", "Confirm signUp " + error.toString());
                }
        );
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.getDialog().cancel();
    }

    public void signin(String email,String password){
        Amplify.Auth.signIn(
            email,
            password,
            result -> {
                if(result.isSignInComplete() ){
                    UserState = "SIGNED_IN";
                    Log.i("Tutorial","User state after sign in " + UserState);

                    if(UserState=="SIGNED_IN"){
                        doAuthentication();
                    }
                }else{
                    AuthenticationActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(AuthenticationActivity.this, "Log in failed Please check, if you are a new user please sign up", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            },
            error -> {
                if(error.getMessage()=="User not confirmed in the system."){
                    showConfirmSignUp();
                }

                Log.e("Tutorial", error.toString());

                AuthenticationActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(AuthenticationActivity.this, "Log in failed Please check network, if you are a new user please sign up", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        );
    }

    public void signup(String surname, String firstname, String email, String password){
        List<AuthUserAttribute> attributes = new ArrayList<>();
        AuthUserAttribute surnameAttr =  new AuthUserAttribute(AuthUserAttributeKey.familyName(),surname);
        AuthUserAttribute firstnameAttr =  new AuthUserAttribute(AuthUserAttributeKey.givenName(),firstname);
        attributes.add(surnameAttr);
        attributes.add(firstnameAttr);
        Amplify.Auth.signUp(
                email,
                password,
                AuthSignUpOptions
                        .builder()
                        .userAttributes(attributes)
                        .build(),
                result ->{
                    Log.i("Tutorials", "Sign up Result: " + result.toString());
                    showConfirmSignUp();
                },
                error -> {
                    Log.e("AuthQuickStart", "Sign up failed", error);
                }
        );
    }


    private void clearSyncStartDataStoreAndStartAuthenticatedScreen(){
        Amplify.DataStore.clear(
                ()->{
                    Log.i("Tutorials", "Cleard DataStore.");
                    startDataStoreAndStartAuthenticatedScreen();
                },
                error -> {
                    Log.e("MyAmplifyApp", "Error clearing DataStore: ", error);
                    startAuthenticatedScreen();
                }
        );
    }

    private void startDataStoreAndStartAuthenticatedScreen(){
        Amplify.DataStore.start(
                () -> {
                    Log.i("Tutorials", " started DataStore.");
                    startAuthenticatedScreen();
                },
                failure -> {
                    Log.e("Tutorials", "Failed to start DataStore.");
                    startAuthenticatedScreen();
                }
        );
    }

    private void startAuthenticatedScreen(){
        AuthenticationActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                showAuthenticatedScreen(authenticatedName);
            }
        });
    }


}
