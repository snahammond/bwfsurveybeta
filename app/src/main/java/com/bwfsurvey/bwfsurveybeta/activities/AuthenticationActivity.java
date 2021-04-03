package com.bwfsurvey.bwfsurveybeta.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.bwfsurvey.bwfsurveybeta.dialogs.ConfirmSignUp;
import com.bwfsurvey.bwfsurveybeta.MyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.dialogs.ResetPasswordConfirmation;
import com.bwfsurvey.bwfsurveybeta.dialogs.ResetPasswordUsername;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

public class AuthenticationActivity extends FragmentActivity implements ConfirmSignUp.ConfirmSignUpListener, ResetPasswordUsername.ResetPasswordUsernameListener, ResetPasswordConfirmation.ResetPasswordConfirmationListener {
    private static final String SHOWING_CONFIRMATION_DIALOG = "showing_confirmation_dialog";
    private static final String STRING_CONFIRMATION_CODE = "string_confirmation_code";

    private LinearLayout progressBar;
    private TextView progressBarText;

    private static String AuthenticationState = "LOGIN";
    private static String UserState = "SIGNED_OUT";

    EditText editTextUserfamilyname;
    EditText editTextUsergivenname;
    EditText editTextEmailAddress;
    EditText editTextPassword;
    Spinner spinnerCountry;
    RadioGroup radioSWEPosition;
    RadioButton radioEducator;
    RadioButton radioVolunteer;

    LinearLayout forgotPasswordLayout;

    String uniqueBWEName = null;
    String authenticatedName = null;

    String email = null;
    String password = null;
    String surname = null;
    String firstname = null;
    String SWEPosition = "Educator";
    String SWECountry = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Tutorials", "Authentication activity creating" );
        setContentView(R.layout.activity_splashscreen);
        doAuthentication();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Tutorials", "Authentication activity starting" );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Tutorials", "Authentication activity resuming" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Tutorials", "Authentication activity pausing" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Tutorials", "Authentication activity stoping" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Tutorials", "Authentication activity destroying" );
    }


    public void doAuthentication(){
        //do the progress
        runOnUiThread(new Runnable() {
            public void run() {
                startProgress("Please wait... Starting up!");
            }
        });

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
                        if(attribute.getKey().getKeyString().contentEquals("custom:Country")){
                            SWECountry = attribute.getValue();
                        }
                        if(attribute.getKey().getKeyString().contentEquals("custom:Position")){
                            SWEPosition = attribute.getValue();
                        }

                    }
                    authenticatedName = firstname +" "+ surname;
                    Log.i("Tutorials", "user online uniqueBWEName: " + uniqueBWEName + " authenticatedName: " +authenticatedName);
                    Log.i("Tutorials", "user online SWECountry: " + SWECountry + " SWEPosition: " +SWEPosition);

                    try {
                        MyAmplifyApplication.namebwe=uniqueBWEName;
                        reevluateStartDataStoreSyncAndStartAuthenticatedScreen();
                    }catch (Exception c){
                        MyAmplifyApplication.namebwe=uniqueBWEName;
                        reevluateStartDataStoreSyncAndStartAuthenticatedScreen();
                    }
                },
                error ->{
                    Log.i("Tutorials", "user offline uniqueBWEName: " + uniqueBWEName + " authenticatedName: " +authenticatedName);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            endProgress();
                            //the user is offline
                            authenticatedName = uniqueBWEName;
                            startAuthenticatedScreen(false);
                        }
                    });
                });
        }else{
            //user is not sign-in
            Log.i("Tutorials", "user not signed in" );
            //ask the user to log in or sign up if he is a new user
            runOnUiThread(new Runnable() {
                public void run() {
                    endProgress();
                    showAuthenticationScreen();
                }
            });
        }

    }

    public void showAuthenticatedScreen(String authenticatedName,Boolean calledAMPStart){
        setContentView(R.layout.activity_authenticated);
        TextView textAuthenticated = (TextView) findViewById(R.id.textAuthenticated);
        textAuthenticated.setText(authenticatedName);

        Button button_continueAs = (Button) findViewById(R.id.button_continueAs);
        button_continueAs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //go to menu screen
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                i.putExtra("NAME_BWE", uniqueBWEName);
                i.putExtra("COUNTRY_BWE", SWECountry);
                i.putExtra("POSITION_BWE", SWEPosition);
                i.putExtra("CALLED_AMPSTART", calledAMPStart);
                startActivity(i);
            }
        });

        Button button_signOut = (Button) findViewById(R.id.button_signOut);
        button_signOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //sign out of cognito, move back to authentication screen
                Log.i("Tutorials", "sign out of cognito, move back to authentication screen" );
                signout();
            }
        });

        runOnUiThread(new Runnable() {
            public void run() {
                endProgress();
            }
        });
    }

    private void showAuthenticationScreen() {
        setContentView(R.layout.activity_authentication);
        editTextUserfamilyname = (EditText) findViewById(R.id.editTextUserfamilyname);
        editTextUsergivenname = (EditText) findViewById(R.id.editTextUsergivenname);
        editTextEmailAddress = (EditText) findViewById(R.id.editTextEmailAddress);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
        radioSWEPosition = (RadioGroup) findViewById(R.id.radioSWEPosition);
        radioEducator = (RadioButton) findViewById(R.id.Educator);
        radioVolunteer = (RadioButton) findViewById(R.id.Volunteer);

        progressBar = (LinearLayout) findViewById(R.id.llProgressBar);
        progressBarText = (TextView) findViewById(R.id.pbText);

        forgotPasswordLayout = (LinearLayout) findViewById(R.id.forgotPasswordLayout);

        TextView newUserOrAlreadySignUpPrompt = (TextView) findViewById(R.id.newUserOrAlreadySignUpPrompt);
        Button button_login = (Button) findViewById(R.id.button_login);
        Button button_signup = (Button) findViewById(R.id.button_signup);
        Button button_resetPassword = (Button) findViewById(R.id.button_resetPassword);

        button_signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //when signup is clicked
                if(AuthenticationState=="LOGIN"){
                    AuthenticationState = "SIGNUP";

                    editTextUserfamilyname.setVisibility(View.VISIBLE);
                    editTextUsergivenname.setVisibility(View.VISIBLE);
                    spinnerCountry.setVisibility(View.VISIBLE);
                    forgotPasswordLayout.setVisibility(View.GONE);

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AuthenticationActivity.this,android.R.layout.simple_spinner_item, getCountryListByLocale().toArray(new String[0]));
                    spinnerCountry.setAdapter(adapter);
                    spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            SWECountry = (String)parent.getItemAtPosition(position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    radioSWEPosition.setVisibility(View.VISIBLE);
                    radioEducator.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SWEPosition = ((RadioButton)v).getText().toString();
                        }
                    });
                    radioVolunteer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SWEPosition = ((RadioButton)v).getText().toString();
                        }
                    });

                    button_login.setText(R.string.signup);
                    button_signup.setText(R.string.Login);
                    newUserOrAlreadySignUpPrompt.setText("Already Signed Up?");
                }else if(AuthenticationState=="SIGNUP"){
                    AuthenticationState = "LOGIN";

                    editTextUserfamilyname.setVisibility(View.GONE);
                    editTextUsergivenname.setVisibility(View.GONE);
                    spinnerCountry.setVisibility(View.GONE);
                    radioSWEPosition.setVisibility(View.GONE);
                    forgotPasswordLayout.setVisibility(View.VISIBLE);

                    button_login.setText(R.string.Login);
                    button_signup.setText(R.string.signup);
                    newUserOrAlreadySignUpPrompt.setText("New User?");
                }
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                email = editTextEmailAddress.getText().toString();
                password = editTextPassword.getText().toString();
                if(AuthenticationState=="LOGIN"){
                    signin(email,password);
                }else{
                    surname = editTextUserfamilyname.getText().toString();
                    firstname = editTextUsergivenname.getText().toString();
                    if(authenticationDataValid()){
                        signup();
                        Log.i("Tutorials", "going to sign up all values are correct" );
                        Log.i("Tutorials", "surname " +surname +" firstname " +firstname+ " email " +email+" password " +password+" SWECountry " +SWECountry+" SWEPosition " +SWEPosition);
                    }

                    else
                        //showInvalidDataAlert();
                        showTitleMessageAlert("Validation Error", "Please fill in all details");
                }

            }
        });

        button_resetPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showResetPasswordUsernameDialog();
            }
        });
    }

    private void showTitleMessageAlert(String title, String message) {
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(AuthenticationActivity.this)
                        .setTitle(title)
                        .setMessage(message + "\n" )
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    private boolean authenticationDataValid() {
        boolean validity = false;
        if(firstname!=null)
            if(!firstname.isEmpty())
                if(surname!=null)
                    if(!surname.isEmpty())
                        if(email!=null)
                            if(!email.isEmpty())
                                if(password!=null)
                                    if(!password.isEmpty())
                                        if(SWECountry!=null)
                                            if(!SWECountry.isEmpty())
                                                if(SWEPosition!=null)
                                                    if(!SWEPosition.isEmpty())
                                                        return true;
        return validity;
    }

    public void ShowHidePass(View view){

        if(view.getId()==R.id.show_pass_btn){

            if(editTextPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_hide_password);

                //Show Password
                editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_show_password);

                //Hide Password
                editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }

    private SortedSet<String> getCountryListByLocale() {
        SortedSet<String> countries = new TreeSet<>();
        for (Locale locale : Locale.getAvailableLocales()) {
            if (!TextUtils.isEmpty(locale.getDisplayCountry())) {
                countries.add(locale.getDisplayCountry());
            }
        }
        return countries;
    }

    private DialogFragment confirmSignUp;
    public void showConfirmSignUp() {
        confirmSignUp = new ConfirmSignUp();
        confirmSignUp.show(getSupportFragmentManager(), "confirmSignUp");
        confirmSignUp.setCancelable(false);
    }

    @Override
    public void onConfirmSignUpDialogPositiveClick(DialogFragment dialog,String confirmationCode) {
        //do progress thing here
        runOnUiThread(new Runnable() {
            public void run() {
                startProgress("Please wait... Comfirming!");
            }
        });


        Amplify.Auth.confirmSignUp(
                editTextEmailAddress.getText().toString(),
                confirmationCode,
                result -> {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            endProgress();
                        }
                    });
                    //go to menu screen //sign in automatically and move to next screen
                    signin(email,password);
                },
                error -> {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            endProgress();
                        }
                    });
                    showTitleMessageAlert("Confirmation Error", "Please check the code and try again.");
                    Log.e("Tutorials", "Confirm signUp " + error.toString());
                }
        );
    }

    @Override
    public void onConfirmSignUpDialogNegativeClick(DialogFragment dialog) {
        dialog.getDialog().cancel();
    }


    public void signin(String email,String password){
        runOnUiThread(new Runnable() {
            public void run() {
                startProgress("Please wait... signing in!");
            }
        });
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

                    runOnUiThread(new Runnable() {
                        public void run() {
                            endProgress();
                        }
                    });

                    AuthenticationActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(AuthenticationActivity.this, "Log in failed Please check, if you are a new user please sign up", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            },
            error -> {
                runOnUiThread(new Runnable() {
                    public void run() {
                        endProgress();
                    }
                });
                if(error.getMessage()=="User not confirmed in the system."){
                    showConfirmSignUp();
                }else{
                    Log.e("Tutorial", error.toString());
                    showTitleMessageAlert("Sign In unsuccessful","Log in failed, if you are a new user please sign up");
                }
            }
        );
    }

    public void signup(){
        List<AuthUserAttribute> attributes = new ArrayList<>();
        AuthUserAttribute surnameAttr =  new AuthUserAttribute(AuthUserAttributeKey.familyName(),surname);
        attributes.add(surnameAttr);
        AuthUserAttribute firstnameAttr =  new AuthUserAttribute(AuthUserAttributeKey.givenName(),firstname);
        attributes.add(firstnameAttr);
        AuthUserAttribute countryAttr =  new AuthUserAttribute(AuthUserAttributeKey.custom("custom:Country"),SWECountry);
        attributes.add(countryAttr);
        AuthUserAttribute swePositionAttr =  new AuthUserAttribute(AuthUserAttributeKey.custom("custom:Position"),SWEPosition);
        attributes.add(swePositionAttr);
        //start a progress thing here
        runOnUiThread(new Runnable() {
            public void run() {
                startProgress("Please wait... Signing Up!");
            }
        });
        try{
            Amplify.Auth.signUp(
                    email,
                    password,
                    AuthSignUpOptions
                            .builder()
                            .userAttributes(attributes)
                            .build(),
                    result ->{
                        Log.i("Tutorials", "Sign up Result: " + result.toString());
                        runOnUiThread(new Runnable() {
                            public void run() {
                                endProgress();
                            }
                        });
                        showConfirmSignUp();
                    },
                    error -> {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                endProgress();
                            }
                        });
                        showTitleMessageAlert("Sign Up Error", error.getMessage());
                        Log.e("Tutorials", "Sign up failed", error);
                    }
            );
        }catch (Exception x){
            runOnUiThread(new Runnable() {
                public void run() {
                    endProgress();
                }
            });
            showTitleMessageAlert("Sign Up Error", x.getMessage());
        }

    }

    public void signout(){
        runOnUiThread(new Runnable() {
            public void run() {
                startProgress("Signing out!");
            }
        });

        Amplify.Auth.signOut(
            () -> {
                runOnUiThread(new Runnable() {
                    public void run() {
                        endProgress();
                    }
                });
                doAuthentication();
                Log.i("Tutorials", "Signed out successfully");
            },
            error -> {
                Log.e("Tutorials", error.toString());
            }
        );
    }

    private void startProgress(String s) {
        Log.i("Tutorials", "Going to show progress " + s );
        if(progressBar==null){
            progressBar = (LinearLayout) findViewById(R.id.llProgressBar);
            Log.i("Tutorials", "Progress bar was null " + s );
        }
        if(progressBarText==null){
            progressBarText = (TextView) findViewById(R.id.pbText);
        }

        progressBarText.setText(s);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void endProgress() {
        Log.i("Tutorials", "Done showing progress" );
        if(progressBar==null)
            progressBar = (LinearLayout) findViewById(R.id.llProgressBar);

        progressBar.setVisibility(View.GONE);
    }

    private void reevluateStartDataStoreSyncAndStartAuthenticatedScreen(){
        Amplify.DataStore.stop(
                () -> {
                    Log.i("Tutorials", " started DataStore.");
                    startDataStoreAndStartAuthenticatedScreen();
                },
                failure -> {
                    Log.e("Tutorials", "Failed to start DataStore.");
                    startAuthenticatedScreen(false);
                }
        );
    }

    private void startDataStoreAndStartAuthenticatedScreen(){
        Amplify.DataStore.start(
                () -> {
                    Log.i("Tutorials", " started DataStore.");
                    startAuthenticatedScreen(true);
                },
                failure -> {
                    Log.e("Tutorials", "Failed to start DataStore.");
                    startAuthenticatedScreen(true);
                }
        );
    }

    private void startAuthenticatedScreen(boolean calledAMPStart){
        AuthenticationActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                showAuthenticatedScreen(authenticatedName,calledAMPStart);
            }
        });
    }

    private DialogFragment resetPasswordUsernameDialog;
    public void showResetPasswordUsernameDialog() {
        resetPasswordUsernameDialog = new ResetPasswordUsername();
        resetPasswordUsernameDialog.show(getSupportFragmentManager(), "PasswordUsername");
        resetPasswordUsernameDialog.setCancelable(false);
    }

    String resetPasswordEmail = null;
    @Override
    public void onResetPasswordUsernameDialogPositiveClick(DialogFragment dialog, String email) {
        resetPasswordEmail = email;
        //do progress thing here
        runOnUiThread(new Runnable() {
            public void run() {
                startProgress("Please wait... Sending reset code to email!");
            }
        });

        Amplify.Auth.resetPassword(
                email,
                result -> {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            endProgress();
                            showResetPasswordConfirmationDialog();
                        }
                    });
                },
                error -> {

                    runOnUiThread(new Runnable() {
                        public void run() {
                            endProgress();
                            showTitleMessageAlert("Password Reset Error", "Password reset unsucessful, Please try again later");
                        }
                    });
                    Log.e("Tutorials", "Reset Password username Error " + error.toString());
                }
        );

    }

    @Override
    public void onResetPasswordUsernameDialogNegativeClick(DialogFragment dialog) {
        dialog.getDialog().cancel();
    }

    private DialogFragment resetPasswordConfirmationDialog;
    public void showResetPasswordConfirmationDialog() {
        resetPasswordConfirmationDialog = new ResetPasswordConfirmation();
        resetPasswordConfirmationDialog.show(getSupportFragmentManager(), "PasswordConfirmation");
        resetPasswordConfirmationDialog.setCancelable(false);
    }

    @Override
    public void onResetPasswordConfirmationDialogPositiveClick(DialogFragment dialog, String newPassword, String confirmationCode) {
        Amplify.Auth.confirmResetPassword(
                newPassword,
                confirmationCode,
                () -> {
                    signin(resetPasswordEmail,newPassword);
                },
                error -> {
                    Log.e("Tutorials", "Reset Password username Error " + error.toString());
                }
        );
    }

    @Override
    public void onResetPasswordConfirmationDialogNegativeClick(DialogFragment dialog) {
        dialog.getDialog().cancel();
    }

    /*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i("Tutorials", "Confirmation pop is going off" );
        /*if (confirmSignUp != null) {
            Log.i("Tutorials", "Confirmation pop is going off inside" );
            outState.putBoolean(SHOWING_CONFIRMATION_DIALOG, true);
            outState.putString(STRING_CONFIRMATION_CODE, ((EditText)confirmSignUp.getDialog().findViewById(R.id.confirmation_code)).getText().toString());
        //}
        super.onSaveInstanceState(outState);
    }
    */
    /*
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i("Tutorials", "Confirmation pop is going to come on" );
        boolean showingDialog = savedInstanceState.getBoolean(SHOWING_CONFIRMATION_DIALOG);
        if (showingDialog) {
            Log.i("Tutorials", "Confirmation pop is going to come on inside" );
            showConfirmSignUp();
        }
    }
    */


}
