package com.example.bwfsurveybeta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AuthenticationActivity extends FragmentActivity implements ConfirmSignUp.ConfirmSignUpListener{
    private static String AuthenticationState = "LOGIN";
    private static String UserState = "SIGNED_OUT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AuthUser authUser = Amplify.Auth.getCurrentUser();
        if(authUser!=null){
            //we have a signed in user
            showAuthenticatedScreen(authUser);
        }else{
            //ask user to either sign in or sign up
            showAuthenticationScreen();
        }

    }

    public void showAuthenticatedScreen(AuthUser authUser){
        setContentView(R.layout.activity_authenticated);
        TextView textAuthenticated = (TextView) findViewById(R.id.textAuthenticated);
        textAuthenticated.setText(authUser.getUsername());

        Button button_continueAs = (Button) findViewById(R.id.button_continueAs);
        button_continueAs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //go to menu screen
                Intent i = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(i);
            }
        });
    }

    private void showAuthenticationScreen() {
        setContentView(R.layout.activity_authentication);
        EditText editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        EditText editTextEmailAddress = (EditText) findViewById(R.id.editTextEmailAddress);
        EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        Button button_login = (Button) findViewById(R.id.button_login);
        Button button_signup = (Button) findViewById(R.id.button_signup);

        button_signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(AuthenticationState=="LOGIN"){
                    AuthenticationState = "SIGNUP";

                    editTextEmailAddress.setVisibility(View.VISIBLE);
                    button_login.setText(R.string.signup);
                    button_signup.setText(R.string.Login);
                }else{
                    AuthenticationState = "LOGIN";

                    editTextEmailAddress.setVisibility(View.GONE);
                    button_login.setText(R.string.Login);
                    button_signup.setText(R.string.signup);
                }
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(AuthenticationState=="LOGIN"){
                    Amplify.Auth.signIn(
                            editTextUsername.getText().toString(),
                            editTextPassword.getText().toString(),
                            result -> {
                                if(result.isSignInComplete() ){
                                    UserState = "SIGNED_IN";
                                    Log.i("Tutorial","User state after sign in " + UserState);

                                    if(UserState=="SIGNED_IN"){

                                        Amplify.Auth.fetchAuthSession(
                                                onSuccess->{
                                                    Log.i("Tutorial",onSuccess.toString());
                                                    //get the user details
                                                    AuthUser authUser = Amplify.Auth.getCurrentUser();
                                                    if(authUser!=null){
                                                        Log.i("Tutorial", "Authenticated user ID " + authUser.getUserId());
                                                        Log.i("Tutorial", "AUthenticated user name " + authUser.getUsername());

                                                        AuthenticationActivity.this.runOnUiThread(new Runnable() {
                                                            public void run() {
                                                                Toast.makeText(AuthenticationActivity.this, "Log in successful " + authUser.getUserId(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                    }

                                                },
                                                onError->{
                                                    Log.e("Tutorial",onError.getMessage());
                                                }
                                        );


                                    }
                                }else{
                                    AuthenticationActivity.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(AuthenticationActivity.this, "Log in failed", Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(AuthenticationActivity.this, "Log in failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                    );


                }else{
                    Amplify.Auth.signUp(
                            editTextUsername.getText().toString(),
                            editTextPassword.getText().toString(),
                            AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), editTextEmailAddress.getText().toString()).build(),
                            result ->{
                                Log.i("Tutorials", "Sign up Result: " + result.toString());
                            },
                            error -> {
                                Log.e("AuthQuickStart", "Sign up failed", error);
                            }
                    );
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
                "snaHammond",
                confirmationCode,
                result -> {
                    Log.i("Tutorials", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
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
}
