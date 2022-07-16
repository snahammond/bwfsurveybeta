package com.bwfsurvey.bwfsurveybeta;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.AmplifyConfiguration;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.DataStoreConfiguration;
import com.amplifyframework.datastore.generated.model.CommunityWater;
import com.amplifyframework.datastore.generated.model.CommunityWaterTest;
import com.amplifyframework.datastore.generated.model.FollowUpSurvey;
import com.amplifyframework.datastore.generated.model.HealthCheckSurvey;
import com.amplifyframework.datastore.generated.model.HouseholdAttendingMeeting;
import com.amplifyframework.datastore.generated.model.HouseholdWaterTest;
import com.amplifyframework.datastore.generated.model.InitialSurvey;
import com.amplifyframework.datastore.generated.model.Meeting;
import com.amplifyframework.datastore.generated.model.SWEMonthlyClinicSummary;
import com.amplifyframework.datastore.generated.model.SWEMonthlySchoolSummary;
import com.amplifyframework.datastore.generated.model.SWEMonthlySummary;
import com.amplifyframework.datastore.generated.model.SWEMonthlyTotalSummary;
import com.amplifyframework.datastore.generated.model.Volunteer;
import com.amplifyframework.datastore.generated.model.VolunteerHousehold;
import com.amplifyframework.datastore.generated.model.VolunteerHouseholdWaterTest;
import com.amplifyframework.datastore.generated.model.VolunteerMonthlySummary;
import com.amplifyframework.datastore.generated.model.VolunteerMonthlyTotalSummary;
import com.bwfsurvey.bwfsurveybeta.types.Answer;
import com.bwfsurvey.bwfsurveybeta.types.AnswerDef;
import com.bwfsurvey.bwfsurveybeta.types.AnswerValue;
import com.bwfsurvey.bwfsurveybeta.types.Clinic;
import com.bwfsurvey.bwfsurveybeta.types.Community;
import com.bwfsurvey.bwfsurveybeta.types.Config;
import com.bwfsurvey.bwfsurveybeta.types.Interchange;
import com.bwfsurvey.bwfsurveybeta.types.Question;
import com.bwfsurvey.bwfsurveybeta.types.School;
import com.bwfsurvey.bwfsurveybeta.types.Validation;
import com.bwfsurvey.bwfsurveybeta.utils.ConfigXmlParser;
import com.example.bwfsurveybeta.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class BwfSurveyAmplifyApplication extends Application {
    private static Context context;
    private static Activity CurrentActivity;
    public static int APPVERSION = -1;
    public static ArrayList<Config> configs;
    public static ArrayList<Interchange> interchangePool;
    public static String namebwe = "";
    public static Long manualTimer = Long.valueOf(1000);


    @Override
    public void onCreate() {
        super.onCreate();
        BwfSurveyAmplifyApplication.context = getApplicationContext();

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
                    .syncExpression(
                            HouseholdWaterTest.class,
                            () -> HouseholdWaterTest.NAMEBWE.eq(namebwe)
                    )
                    .syncExpression(
                            CommunityWater.class,
                            () -> CommunityWater.NAMEBWE.eq(namebwe)
                    )
                    .syncExpression(
                            CommunityWaterTest.class,
                            () -> CommunityWaterTest.NAMEBWE.eq(namebwe)
                    )
                    .syncExpression(
                            Volunteer.class,
                            () -> Volunteer.NAMEBWE.eq(namebwe)
                    )
                    .syncExpression(
                            VolunteerHousehold.class,
                            () -> VolunteerHousehold.NAMEBWE.eq(namebwe)
                    )
                    .syncExpression(
                            VolunteerHouseholdWaterTest.class,
                            () -> VolunteerHouseholdWaterTest.NAMEBWE.eq(namebwe)
                    )
                    .syncExpression(
                            HealthCheckSurvey.class,
                            () -> HealthCheckSurvey.NAMEBWE.eq(namebwe)
                    )
                    .syncExpression(
                            SWEMonthlySummary.class,
                            () -> SWEMonthlySummary.NAMEBWE.eq(namebwe)
                    )
                    .syncExpression(
                            VolunteerMonthlySummary.class,
                            () -> VolunteerMonthlySummary.NAMEBWE.eq(namebwe)
                    )
                    .syncExpression(
                            Meeting.class,
                            () -> Meeting.NAMEBWE.eq(namebwe)
                    )
                    .syncExpression(
                            HouseholdAttendingMeeting.class,
                            () -> HouseholdAttendingMeeting.NAMEBWE.eq(namebwe)
                    )
                    .syncExpression(
                            SWEMonthlyTotalSummary.class,
                            () -> SWEMonthlyTotalSummary.NAMEBWE.eq(namebwe)
                    )
                    .syncExpression(
                            VolunteerMonthlyTotalSummary.class,
                            () -> VolunteerMonthlyTotalSummary.NAMEBWE.eq(namebwe)
                    )
                    .syncExpression(
                            SWEMonthlySchoolSummary.class,
                            () -> SWEMonthlySchoolSummary.NAMEBWE.eq(namebwe)
                    )
                    .syncExpression(
                            SWEMonthlyClinicSummary.class,
                            () -> SWEMonthlyClinicSummary.NAMEBWE.eq(namebwe)
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
        BwfSurveyAmplifyApplication.configs = loadInAllConfigs();
        BwfSurveyAmplifyApplication.APPVERSION = getAPPVERSION();
        BwfSurveyAmplifyApplication.interchangePool = makeAllInterchanges();

    }

    public static int getAPPVERSION() {
        int appVersion = -1;
        for(Config config : configs) {
            //Log.i("Tutorial", "config " +config.getType() + " "+ config.getName() +" "+ config.getValue());
            if (config.getType().contentEquals("APP")) {
                appVersion = Integer.parseInt(config.getValue());
                break;
            }
        }
        return appVersion;
    }

    public static Context getAppContext() {
        return BwfSurveyAmplifyApplication.context;
    }

    public static void setCurrentActivity(Activity activity) {
        BwfSurveyAmplifyApplication.CurrentActivity = activity;
    }

    public static Activity getCurrentActivity() {
        return BwfSurveyAmplifyApplication.CurrentActivity;
    }


    //make all interchanges possible
    public static ArrayList<Interchange> makeAllInterchanges(){
        ArrayList<Interchange> allInterchangesFromConfig = new ArrayList<>();

        if(BwfSurveyAmplifyApplication.configs!=null){
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
                    allInterchangesFromConfig.add(new Interchange(config.getName(),interchangeNo,config.getDescription()));
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
                //sort the Answerlist of the interchange (if it has)
                Collections.sort(interchange.getAnswer().getAnswerValArrayList());
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
        for(Config config : BwfSurveyAmplifyApplication.configs){
            if(config.getName().contentEquals(nameOfSurvey) && config.getType().contentEquals("S")){
                return config;
            }
        }
        return surveyConfig;
    }

    //get list of required interchanges with name of Survey
    public static ArrayList<Community> getCommunities(String nameOfCountry){
        ArrayList<Community> communities = null;

        //search fo the config with name nameOfSurvey
        Config configOfCountryBeingRequested = searchConfigForCountry(nameOfCountry);
        //make the community list
        //this config "configOfCountryBeingRequestedon" contains all the communities, so get the communities out of int
        communities = getCommunitiesFromCountryConfig(configOfCountryBeingRequested,nameOfCountry);

        return communities;
    }

    private static ArrayList<Community> getCommunitiesFromCountryConfig(Config configOfCountryBeingRequested,String nameOfCountry) {
        ArrayList<Community> communities = null;
        if(configOfCountryBeingRequested!=null){
            communities = new ArrayList<>();
            String arrayOfCommunities = configOfCountryBeingRequested.getChildValue(); //enveloped in square brackets
            //take off the square brackets
            String arrayOfCommunitiesWithoutSquareBrackets = arrayOfCommunities.substring(1, arrayOfCommunities.length() - 1);
            //they are seperated by "," so split them by ","
            String[] arrayOfIndCommunities = arrayOfCommunitiesWithoutSquareBrackets.split(",");

            for(int i=0; i<arrayOfIndCommunities.length;i++){
                //each array element has \"\" around it, so take them off
                String communityValue = arrayOfIndCommunities[i].substring(1, arrayOfIndCommunities[i].length() - 1);
                //make new community object
                Community community = new Community(nameOfCountry,communityValue);
                communities.add(community);
            }
        }
        return communities;
    }

    private static Config searchConfigForCountry(String country){
        Config surveyConfig = null;
        for(Config config : BwfSurveyAmplifyApplication.configs){
            if(config.getName().contentEquals(country.toUpperCase()) && config.getType().contentEquals("C")){
                return config;
            }
        }
        return surveyConfig;
    }

    public static ArrayList<School> getSchools(String nameOfCommunity){
        ArrayList<School> schools = null;

        //search for the config with name nameOfCommunity
        Config configOfCountryBeingRequested = searchConfigForCommSchool(nameOfCommunity);
        //make the community list
        //this config "configOfCountryBeingRequestedon" contains all the communities, so get the communities out of int
        schools = getSchoolsFromCommSchConfig(configOfCountryBeingRequested,nameOfCommunity);

        return schools;
    }

    private static Config searchConfigForCommSchool(String community){
        Config surveyConfig = null;
        for(Config config : BwfSurveyAmplifyApplication.configs){
            if(config.getName().contentEquals(community.toUpperCase()) && config.getType().contentEquals("CS")){
                return config;
            }
        }
        return surveyConfig;
    }

    private static ArrayList<School> getSchoolsFromCommSchConfig(Config configOfCountryBeingRequested,String nameOfCommunity) {
        ArrayList<School> schools = null;
        if(configOfCountryBeingRequested!=null){
            schools = new ArrayList<>();
            String arrayOfSchools = configOfCountryBeingRequested.getChildValue(); //enveloped in square brackets
            //take off the square brackets
            String arrayOfSchoolsWithoutSquareBrackets = arrayOfSchools.substring(1, arrayOfSchools.length() - 1);
            //they are seperated by "," so split them by ","
            String[] arrayOfIndSchools = arrayOfSchoolsWithoutSquareBrackets.split(",");

            for(int i=0; i<arrayOfIndSchools.length;i++){
                //each array element has \"\" around it, so take them off
                String schoolValue = arrayOfIndSchools[i].substring(1, arrayOfIndSchools[i].length() - 1);
                //make new school object
                School school = new School(nameOfCommunity,schoolValue);
                schools.add(school);
            }
        }
        return schools;
    }

    public static ArrayList<Clinic> getClinics(String nameOfCommunity){
        ArrayList<Clinic> clinics = null;

        //search for the config with name nameOfCommunity
        Config configOfCountryBeingRequested = searchConfigForCommClinic(nameOfCommunity);
        //make the community list
        //this config "configOfCountryBeingRequestedon" contains all the communities, so get the communities out of int
        clinics = getClinicsFromCommClinicConfig(configOfCountryBeingRequested,nameOfCommunity);

        return clinics;
    }

    private static Config searchConfigForCommClinic(String community){
        Config surveyConfig = null;
        for(Config config : BwfSurveyAmplifyApplication.configs){
            if(config.getName().contentEquals(community.toUpperCase()) && config.getType().contentEquals("CC")){
                return config;
            }
        }
        return surveyConfig;
    }

    private static ArrayList<Clinic> getClinicsFromCommClinicConfig(Config configOfCountryBeingRequested,String nameOfCommunity) {
        ArrayList<Clinic> clinics = null;
        if(configOfCountryBeingRequested!=null){
            clinics = new ArrayList<>();
            String arrayOfClinics = configOfCountryBeingRequested.getChildValue(); //enveloped in square brackets
            //take off the square brackets
            String arrayOfClinicsWithoutSquareBrackets = arrayOfClinics.substring(1, arrayOfClinics.length() - 1);
            //they are seperated by "," so split them by ","
            String[] arrayOfIndClinics = arrayOfClinicsWithoutSquareBrackets.split(",");

            for(int i=0; i<arrayOfIndClinics.length;i++){
                //each array element has \"\" around it, so take them off
                String clinicValue = arrayOfIndClinics[i].substring(1, arrayOfIndClinics[i].length() - 1);
                //make new school object
                Clinic clinic = new Clinic(nameOfCommunity,clinicValue);
                clinics.add(clinic);
            }
        }
        return clinics;
    }

    public static ArrayList<String> getCountries(){
        ArrayList<String> countries = new ArrayList<>();
        for(Config config : BwfSurveyAmplifyApplication.configs){
            if(config.getType().contentEquals("C")){
                countries.add(config.getDescription());
            }
        }
        return countries;
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
            for(Interchange interchange : BwfSurveyAmplifyApplication.interchangePool){
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

