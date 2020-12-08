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
import com.amplifyframework.datastore.DataStoreConfiguration;
import com.amplifyframework.datastore.generated.model.FollowUpSurvey;
import com.amplifyframework.datastore.generated.model.InitialSurvey;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyAmplifyApplication extends Application {
    private static Context context;
    private static Activity CurrentActivity;
    private static ArrayList<Config> configs;
    private static ArrayList<Interchange> interchangePool;
    public static String namebwe = "";


    @Override
    public void onCreate() {
        super.onCreate();
        MyAmplifyApplication.context = getApplicationContext();

        try {

            //Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSDataStorePlugin(DataStoreConfiguration.builder()
                    .syncExpression(
                            InitialSurvey.class,
                            () -> InitialSurvey.NAMEBWE.eq(namebwe)
                    )
                    .syncExpression(
                            FollowUpSurvey.class,
                            () -> FollowUpSurvey.NAMEBWE.eq(namebwe)
                    )
                    .build()));
            Amplify.addPlugin(new AWSApiPlugin());
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

        //load the config file
        MyAmplifyApplication.configs = loadInAllConfigs();
        MyAmplifyApplication.interchangePool = makeAllInterchanges();

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


    //make all interchanges possible
    private static ArrayList<Interchange> makeAllInterchanges(){
        ArrayList<Interchange> allInterchangesFromConfig = new ArrayList<>();

        if(MyAmplifyApplication.configs!=null){
            ArrayList<AnswerValue> answerValuesPool = new ArrayList<>();
            ArrayList<AnswerDef> answerDefPool = new ArrayList<>();
            ArrayList<Question> questionPool = new ArrayList<>();
            ArrayList<Answer> answerPool = new ArrayList<>();
            ArrayList<Validation> validationPool = new ArrayList<>();

            for(Config config : configs){
                //Log.i("Tutorial", "config " +config.getType() + " "+ config.getName() +" "+ config.getValue());
                if(config.getType().contentEquals("AV")){
                    answerValuesPool.add(new AnswerValue(config));
                }else if(config.getType().contentEquals("AD")){
                    answerDefPool.add(new AnswerDef(config));
                }else if(config.getType().contentEquals("Q")){
                    questionPool.add(new Question(config));
                }else if(config.getType().contentEquals("I")){
                    int interchangeNo = -1;
                    try {
                        interchangeNo = Integer.parseInt(config.getValue());
                    }catch (Exception x){
                        interchangeNo = -1;
                    }
                    allInterchangesFromConfig.add(new Interchange(config.getName(),interchangeNo));
                } else if (config.getType().contentEquals("V")) {
                    int mandatoryInt;
                    boolean mandatory;
                    try {
                        mandatoryInt = Integer.parseInt(config.getValue());
                    }catch (Exception x){
                        mandatoryInt = 0;
                    }
                    if(mandatoryInt==1){
                        mandatory=true;
                    }else{
                        mandatory = false;
                    }
                    validationPool.add(new Validation(config.getName(),mandatory,config.getDescription()));
                }
            }
            //we have made all the answers
            for(AnswerDef answerDef : answerDefPool){
                ArrayList<AnswerValue> answerValArrayList = new ArrayList<>();
                for(AnswerValue answerValue : answerValuesPool){
                    if(answerDef.getName().contentEquals(answerValue.getName())){
                        answerValArrayList.add(answerValue);
                    }
                }
                answerPool.add(new Answer(answerDef,answerValArrayList));
            }

            //update the interchanges with questions and answers and validation
            for(Interchange interchange : allInterchangesFromConfig){
                for(Question question: questionPool){
                    if(interchange.getName().contentEquals(question.getName())){
                        interchange.setQuestion(question);
                    }
                }
                for(Answer answer: answerPool){
                    if(interchange.getName().contentEquals(answer.getAnswerDef().getName())){
                        interchange.setAnswer(answer);
                    }
                }
                for(Validation validation : validationPool){
                    if(interchange.getName().contentEquals(validation.getName())){
                        interchange.setValidation(validation);
                    }
                }
            }
        }
        return allInterchangesFromConfig;
    }

    private ArrayList<Config> loadInAllConfigs(){
        ArrayList<Config> configs = null;
        //load from file
        ConfigXmlParser configXmlParser = new ConfigXmlParser();
        InputStream is = getResources().openRawResource(R.raw.config);

        try {
            configs = (ArrayList<Config>) configXmlParser.parse(is);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configs;
    }

    //get list of required interchanges with name of Survey
    public static ArrayList<Interchange> getInterchanges(String nameOfSurvey){
        ArrayList<Interchange> interchanges = null;

        //search fo the config with name nameOfSurvey
        Config configOfSurveyBeingRequested = searchConfigForSurvey(nameOfSurvey);
        //get the interchange numbers
        int[] interchangesNumbersRequested = getInterchangeNumbers(configOfSurveyBeingRequested);
        //use this list to get the actual list of interchanges
        interchanges = getInterchangesRequested(interchangesNumbersRequested);

        return interchanges;
    }

    private static Config searchConfigForSurvey(String nameOfSurvey){
        Config surveyConfig = null;
        for(Config config : MyAmplifyApplication.configs){
            if(config.getName().contentEquals(nameOfSurvey) && config.getType().contentEquals("S")){
                return config;
            }
        }
        return surveyConfig;
    }

    private static int[] getInterchangeNumbers(Config configOfSurveyBeingRequested){
        int[] interchangesRequired = null;
        if(configOfSurveyBeingRequested!=null){
            String arrayOfInterchangeNo = configOfSurveyBeingRequested.getChildValue();
            String strIntArrInterchangesNeeded = arrayOfInterchangeNo.substring(1, arrayOfInterchangeNo.length() - 1);
            String[] integerStrings = strIntArrInterchangesNeeded.split(",");
            interchangesRequired = new int[integerStrings.length];
            for (int i = 0; i < integerStrings.length; i++) {
                interchangesRequired[i] = Integer.parseInt(integerStrings[i]);
            }
        }
        return interchangesRequired;
    }

    private static ArrayList<Interchange> getInterchangesRequested(int[] interchangesRequired){
        ArrayList<Interchange> interchangesRequested = null;
        if (interchangesRequired != null) {
            interchangesRequested = new ArrayList<>();
            for(Interchange interchange : MyAmplifyApplication.interchangePool){
                for(int interchangeNo : interchangesRequired){
                    if(interchangeNo == interchange.getInterchangeNumber()){
                        interchangesRequested.add(interchange);
                    }
                }
            }
        }
        return interchangesRequested;
    }
 }

