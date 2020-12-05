package com.example.bwfsurveybeta;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.AnswerType;
import com.amplifyframework.datastore.generated.model.ConfigDef;
import com.amplifyframework.datastore.generated.model.Family;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class InitialSurveyActivity extends AppCompatActivity /*implements SaveSurveyDialog.SaveQuestionaireDialogListener*/ {
    private RecyclerView recyclerView;
    private InterchangeCardAdapter adapter;
    private String namebwe;
    private static ArrayList<Interchange> interchanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        namebwe = getIntent().getStringExtra("NAME_BWE");
        setContentView(R.layout.activity_recycler);
        initView();
    }

    private void initView() {
        createInitialSurveyQuestionaire();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InterchangeCardAdapter(InitialSurveyActivity.this, InitialSurveyActivity.interchanges);
        recyclerView.setAdapter(adapter);

    }
/*
    private void createInitialSurveyQuestionaire(){
        InitialSurveyActivity.interchanges = new ArrayList<>();

        int[] interchangesRequired = null;

        //load in all the configs
        ArrayList<Config> configs = loadInAllConfigs();

        if(configs!=null){
            ArrayList<AnswerValue> answerValuesPool = new ArrayList<>();
            ArrayList<AnswerDef> answerDefPool = new ArrayList<>();
            ArrayList<Question> questionPool = new ArrayList<>();
            ArrayList<Interchange> interchangePool = new ArrayList<>();
            ArrayList<Answer> answerPool = new ArrayList<>();

            for(Config config : configs){
                Log.i("Tutorial", "config " +config.getType() + " "+ config.getName() +" "+ config.getValue());
                if(config.getType().contentEquals("AV")){
                    answerValuesPool.add(new AnswerValue(config));
                }else if(config.getType().contentEquals("AD")){
                    answerDefPool.add(new AnswerDef(config));
                }else if(config.getType().contentEquals("Q")){
                    questionPool.add(new Question(config));
                }else if(config.getType().contentEquals("I")){
                    int interchangeNo = -1;
                    try {
                        Log.i("Tutorial", "interchangeNo before" +config.getValue());
                        interchangeNo = Integer.parseInt(config.getValue());
                        Log.i("Tutorial", "interchangeNo after " +interchangeNo);
                    }catch (Exception x){
                        interchangeNo = -1;
                    }
                    interchangePool.add(new Interchange(config.getName(),interchangeNo));
                }else if(config.getType().contentEquals("S")&& config.getName().contentEquals("INITAILSURVEY")) {
                    String rawData = config.getChildValue();
                    String strIntArrInterchangesNeeded = rawData.substring(1, rawData.length() - 1);
                    String[] integerStrings = strIntArrInterchangesNeeded.split(",");
                    interchangesRequired = new int[integerStrings.length];
                    for (int i = 0; i < integerStrings.length; i++) {
                        interchangesRequired[i] = Integer.parseInt(integerStrings[i]);
                    }
                }
            }

            //we have made all the answers
            for(AnswerDef answerDef : answerDefPool){
                //get all AV for each answer def
                ArrayList<AnswerValue> answerValArrayList = new ArrayList<>();
                for(AnswerValue answerValue : answerValuesPool){
                    if(answerDef.getName().contentEquals(answerValue.getName())){
                        answerValArrayList.add(answerValue);
                    }
                }
                answerPool.add(new Answer(answerDef,answerValArrayList));
                Log.i("Tutorial", "answerDef " +answerDef.getType() + " "+ answerDef.getName() +" "+ answerDef.getDesc());
            }

            //get the required interchanges
            Log.i("Tutorial", "interchangesRequired " +interchangesRequired.length);
            for(int interchangenorequired : interchangesRequired){
                Log.i("Tutorial", "interchangenorequired " +interchangenorequired);
            }
            for(Interchange interchange : interchangePool){
                Log.i("Tutorial", "interchange " +interchange.getName() +" "+ interchange.getInterchangeNumber());
            }
            if (interchangesRequired != null) {
                int positionOnRecyler = 0;
                for(Interchange interchange : interchangePool){
                    for(int interchangeNo : interchangesRequired){
                        if(interchangeNo == interchange.getInterchangeNumber()){
                            InitialSurveyActivity.interchanges.add(interchange);
                            interchange.setPositionOnRecyler(positionOnRecyler);
                            positionOnRecyler += 1;
                            Log.i("Tutorial", "interchange " +interchange.getName() + " "+  interchange.getInterchangeNumber());
                        }
                    }
                }

            }
            Log.i("Tutorial", "interchanges selected no " +InitialSurveyActivity.interchanges.size());
            //update interchanges with their questions and answers
            for(Interchange interchange : InitialSurveyActivity.interchanges){
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
            }
        }
    }
*/
    private void createInitialSurveyQuestionaire(){
        try{
            InitialSurveyActivity.interchanges = MyAmplifyApplication.getInterchanges("INITAILSURVEY");
            Log.i("Tutorial", "InitialSurveyActivity.interchanges.size()  " +InitialSurveyActivity.interchanges.size() );
            for(Interchange interchange : InitialSurveyActivity.interchanges){
                Log.i("Tutorial", "interchange " +interchange.getName() +" "+ interchange.getInterchangeNumber());
            }
        }catch (Exception c){
            Log.i("Tutorial", "we cannot get list of interchanges " );
        }
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
/*
    private static class GetFamilyQuestionsConfigs{

        public interface GetFamilyQuestionsConfigsListerner{
            public void onSuccess(ArrayList<Question> familyQuestions);
            public void onFailure();
        }

        GetFamilyQuestionsConfigsListerner listerner;

        public GetFamilyQuestionsConfigs(GetFamilyQuestionsConfigsListerner l){
            this.listerner = l;
        }

        public void execute(){
            try{
                Amplify.DataStore.query(
                        ConfigDef.class,
                        configs -> {
                            ArrayList<PossibleAns> possibleAnssSex = new ArrayList<>();
                            ArrayList<PossibleAns> possibleAnssMaritalStatus = new ArrayList<>();
                            ArrayList<PossibleAns> possibleAnssOccupation = new ArrayList<>();
                            ArrayList<Question> familyQuestions = new ArrayList<>();
                            while (configs.hasNext()) {
                                ConfigDef configDef = configs.next();
                                if(configDef.getName().contentEquals("Sex")){
                                    PossibleAns possibleAns = new PossibleAns(configDef.getValue(),configDef.getDescription());
                                    possibleAnssSex.add(possibleAns);
                                }else if(configDef.getName().contentEquals("Occupation")){
                                    PossibleAns possibleAns = new PossibleAns(configDef.getValue(),configDef.getDescription());
                                    possibleAnssOccupation.add(possibleAns);
                                }else if(configDef.getName().contentEquals("MaritalStatus")){
                                    PossibleAns possibleAns = new PossibleAns(configDef.getValue(),configDef.getDescription());
                                    possibleAnssMaritalStatus.add(possibleAns);
                                }else if(configDef.getName().contentEquals("FAMILYQUESTION")){
                                    String questionText = configDef.getDescription();
                                    String answerType = configDef.getValue();
                                    String answerTypeName = configDef.getParentName();
                                    ArrayList<PossibleAns> possibleAnss = null;
                                    int questionNum = -1;
                                    try {
                                        questionNum = Integer.parseInt(configDef.getParentValue());
                                    }catch (Exception x){
                                        questionNum = -1;
                                    }

                                    if(answerTypeName.contentEquals("Sex")){
                                        possibleAnss=possibleAnssSex;
                                    }else if(answerTypeName.contentEquals("Occupation")){
                                        possibleAnss=possibleAnssOccupation;
                                    }else if(answerTypeName.contentEquals("MaritalStatus")){
                                        possibleAnss=possibleAnssMaritalStatus;
                                    }
                                    //manually do country and community
                                    if(questionNum==1){
                                        answerType = AnswerType.ENUMDROPDOWNVALUE.name();
                                        answerTypeName = "COUNTRY";
                                        ArrayList<PossibleAns> possibleAnssCountries = new ArrayList<>();
                                        possibleAnssCountries.add(new PossibleAns("GHANA","Ghana"));
                                        possibleAnssCountries.add(new PossibleAns("UGANDA","Uganda"));
                                        possibleAnssCountries.add(new PossibleAns("RWANDA","Rwanda"));
                                        possibleAnssCountries.add(new PossibleAns("PERU","Peru"));
                                        possibleAnss = possibleAnssCountries;
                                    }
                                    if(questionNum==2){
                                        answerType = AnswerType.ENUMDROPDOWNVALUE.name();
                                        answerTypeName = "COMMUNITY";
                                        ArrayList<PossibleAns> possibleAnssCommunities = new ArrayList<>();
                                        PossibleAns comm1Ghana = new PossibleAns("KUMASI","KUMASI");
                                        comm1Ghana.setParent_name("COUNTRY");
                                        comm1Ghana.setParent_value("GHANA");
                                        possibleAnssCommunities.add(comm1Ghana);
                                        PossibleAns comm2Ghana = new PossibleAns("OSU","OSU");
                                        comm2Ghana.setParent_name("COUNTRY");
                                        comm2Ghana.setParent_value("GHANA");
                                        possibleAnssCommunities.add(comm2Ghana);
                                        PossibleAns comm3Ghana = new PossibleAns("TESHIE","TESHIE");
                                        comm3Ghana.setParent_name("COUNTRY");
                                        comm3Ghana.setParent_value("GHANA");
                                        possibleAnssCommunities.add(comm3Ghana);
                                        PossibleAns comm4Ghana = new PossibleAns("ABOKOBI","ABOKOBI");
                                        comm4Ghana.setParent_name("COUNTRY");
                                        comm4Ghana.setParent_value("GHANA");
                                        possibleAnssCommunities.add(comm4Ghana);

                                        PossibleAns comm1Uganda = new PossibleAns("KAMULI","KAMULI");
                                        comm1Uganda.setParent_name("COUNTRY");
                                        comm1Uganda.setParent_value("UGANDA");
                                        possibleAnssCommunities.add(comm1Uganda);
                                        PossibleAns comm2Uganda = new PossibleAns("BUKUNGU","BUKUNGU");
                                        comm2Uganda.setParent_name("COUNTRY");
                                        comm2Uganda.setParent_value("UGANDA");
                                        possibleAnssCommunities.add(comm2Uganda);
                                        PossibleAns comm3Uganda = new PossibleAns("KOBOKO","KOBOKO");
                                        comm3Uganda.setParent_name("COUNTRY");
                                        comm3Uganda.setParent_value("UGANDA");
                                        possibleAnssCommunities.add(comm3Uganda);
                                        PossibleAns comm4Uganda = new PossibleAns("NJERU","NJERU");
                                        comm4Uganda.setParent_name("COUNTRY");
                                        comm4Uganda.setParent_value("UGANDA");
                                        possibleAnssCommunities.add(comm4Uganda);

                                        PossibleAns comm1Rwanda = new PossibleAns("MUHANGA","MUHANGA");
                                        comm1Rwanda.setParent_name("COUNTRY");
                                        comm1Rwanda.setParent_value("RWANDA");
                                        possibleAnssCommunities.add(comm1Rwanda);
                                        PossibleAns comm2Rwanda = new PossibleAns("CYANGUGU","CYANGUGU");
                                        comm2Rwanda.setParent_name("COUNTRY");
                                        comm2Rwanda.setParent_value("RWANDA");
                                        possibleAnssCommunities.add(comm2Rwanda);
                                        PossibleAns comm3Rwanda = new PossibleAns("UMUTARA","UMUTARA");
                                        comm3Rwanda.setParent_name("COUNTRY");
                                        comm3Rwanda.setParent_value("RWANDA");
                                        possibleAnssCommunities.add(comm3Rwanda);
                                        PossibleAns comm4Rwanda = new PossibleAns("KIBUYE","KIBUYE");
                                        comm4Rwanda.setParent_name("COUNTRY");
                                        comm4Rwanda.setParent_value("RWANDA");
                                        possibleAnssCommunities.add(comm4Rwanda);

                                        PossibleAns comm1Peru = new PossibleAns("AYACUCHO","AYACUCHO");
                                        comm1Peru.setParent_name("COUNTRY");
                                        comm1Peru.setParent_value("PERU");
                                        possibleAnssCommunities.add(comm1Peru);
                                        PossibleAns comm2Peru = new PossibleAns("CAHUANUYO","CAHUANUYO");
                                        comm2Peru.setParent_name("COUNTRY");
                                        comm2Peru.setParent_value("PERU");
                                        possibleAnssCommunities.add(comm2Peru);
                                        PossibleAns comm3Peru = new PossibleAns("CHIVAY","CHIVAY");
                                        comm3Peru.setParent_name("COUNTRY");
                                        comm3Peru.setParent_value("PERU");
                                        possibleAnssCommunities.add(comm3Peru);
                                        PossibleAns comm4Peru = new PossibleAns("HUACHO","HUACHO");
                                        comm4Peru.setParent_name("COUNTRY");
                                        comm4Peru.setParent_value("PERU");
                                        possibleAnssCommunities.add(comm4Peru);

                                        possibleAnss = possibleAnssCommunities;
                                    }

                                    familyQuestions.add(new Question(questionText,answerType,answerTypeName,possibleAnss,questionNum));
                                }
                            }
                            Collections.sort(familyQuestions);
                            listerner.onSuccess(familyQuestions);
                            Log.i("Tutorial", "Loaded family question configs from DataStore");
                        },
                        failure -> {
                            Log.i("Tutorial", "Could not query configs in DataStore", failure);
                            listerner.onFailure();
                        }
                );
            }catch (Exception ex){
                Log.i("Tutorial", "Could not query configs in DataStore");
                listerner.onFailure();
            }
        }
    }

    private ArrayList<Question> loadFamilyQuestionsConfigsManually(){
        Log.i("Tutorial", "Manually loading MaritalStatus def ");
        ArrayList<PossibleAns> possibleAnssSex = loadSexDefsManually();
        ArrayList<PossibleAns> possibleAnssMaritalStatus = loadMaritalStatusDefManually();
        ArrayList<PossibleAns> possibleAnssOccupation = loadOccupationDefManually();
        ArrayList<Question> familyQuestions = loadFamilyQuestionsManually(possibleAnssSex,possibleAnssMaritalStatus,possibleAnssOccupation);
        return familyQuestions;
    }

    private ArrayList<PossibleAns> loadSexDefsManually(){
        Log.i("Tutorial", "Manually loading sex def ");
        ArrayList<PossibleAns> possibleAnssSex = new ArrayList<>();
        possibleAnssSex.add(new PossibleAns("MALE","MALE"));
        possibleAnssSex.add(new PossibleAns("FEMALE","FEMALE"));
        return possibleAnssSex;
    }

    private ArrayList<PossibleAns> loadMaritalStatusDefManually(){
        Log.i("Tutorial", "Manually loading MaritalStatus def ");
        ArrayList<PossibleAns> possibleAnssMaritalStatus = new ArrayList<>();
        possibleAnssMaritalStatus.add(new PossibleAns("MARRIED","MARRIED"));
        possibleAnssMaritalStatus.add(new PossibleAns("SINGLE","SINGLE"));
        possibleAnssMaritalStatus.add(new PossibleAns("WIDOWED","WIDOWED"));
        possibleAnssMaritalStatus.add(new PossibleAns("DIVORCED","DIVORCED"));
        possibleAnssMaritalStatus.add(new PossibleAns("COHABITING","COHABITING"));
        return possibleAnssMaritalStatus;
    };

    private ArrayList<PossibleAns> loadOccupationDefManually(){
        Log.i("Tutorial", "Manually loading Occupation def ");
        ArrayList<PossibleAns> possibleAnssOccupation = new ArrayList<>();
        possibleAnssOccupation.add(new PossibleAns("FARMER","FARMER"));
        possibleAnssOccupation.add(new PossibleAns("TRADER","TRADER"));
        possibleAnssOccupation.add(new PossibleAns("TEACHER","TEACHER"));
        possibleAnssOccupation.add(new PossibleAns("FISHERMAN","FISHERMAN"));
        possibleAnssOccupation.add(new PossibleAns("CIVILSERVANT","CIVIL SERVANT (WORKS FOR GOVERNMENT)"));
        possibleAnssOccupation.add(new PossibleAns("NONE","NONE"));
        possibleAnssOccupation.add(new PossibleAns("OTHER","OTHER"));
        return possibleAnssOccupation;
    }

    private ArrayList<Question> loadFamilyQuestionsManually(ArrayList<PossibleAns> possibleAnsSex,ArrayList<PossibleAns> possibleAnsMaritalStatus,ArrayList<PossibleAns> possibleAnsOccupation){
        ArrayList<Question> familyQuestions = new ArrayList<>();
        Question familyCountry = new Question("Name of Country",AnswerType.TEXTVALUE.name(),"",null,1);
        familyQuestions.add(familyCountry);
        Question familyCommunity = new Question("Name of Community",AnswerType.TEXTVALUE.name(),"",null,2);
        familyQuestions.add(familyCommunity);
        Question familyHeadName = new Question("Head of Household Name",AnswerType.TEXTVALUE.name(),"",null,3);
        familyQuestions.add(familyHeadName);
        Question familyHeadNameSex = new Question("Head of Household Sex",AnswerType.ENUMVALUE.name(),"Sex",possibleAnsSex,4);
        familyQuestions.add(familyHeadNameSex);
        Question familyHeadNameMaritalStatus = new Question("Head of Household Marital Status",AnswerType.ENUMVALUE.name(),"MaritalStatus",possibleAnsMaritalStatus,5);
        familyQuestions.add(familyHeadNameMaritalStatus);
        Question familyHeadNameAge = new Question("Head of Household Age",AnswerType.NUMBERVALUE.name(),"",null,6);
        familyQuestions.add(familyHeadNameAge);
        Question familyHeadNameOccupation = new Question("Head of Household Occupation",AnswerType.ENUMVALUE.name(),"Occupation",possibleAnsOccupation,7);
        familyQuestions.add(familyHeadNameOccupation);
        return familyQuestions;
    }

    private void createQuestionaire() {
        InitialSurveyActivity.questions = new ArrayList<>();
        GetFamilyQuestionsConfigs getFamilyQuestionsConfigs = new GetFamilyQuestionsConfigs(new GetFamilyQuestionsConfigs.GetFamilyQuestionsConfigsListerner() {
            @Override
            public void onSuccess(ArrayList<Question> familyQuestions) {
                for(Question familyQuestion: familyQuestions){
                    InitialSurveyActivity.questions.add(familyQuestion);
                }
                Log.i("Tutorial", "questions "+ InitialSurveyActivity.questions.size());

                runOnUiThread(new Runnable() {
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFailure() {
                ArrayList<Question> familyQuestions = loadFamilyQuestionsConfigsManually();
                for(Question familyQuestion: familyQuestions){
                    InitialSurveyActivity.questions.add(familyQuestion);
                }
                Log.i("Tutorial", "questions "+ InitialSurveyActivity.questions.size());
                runOnUiThread(new Runnable() {
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
        getFamilyQuestionsConfigs.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_save) {
            // do something here
            ArrayList<Question> questionsWithAns = adapter.retrieveData();

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date_s = dateFormat.format(calendar.getTime());
            Temporal.Date date = new Temporal.Date(date_s);
            Log.i("Tutorial", "date to save: "+ date_s + " " + date.toString());


            String country = (String)questionsWithAns.get(0).getAns();
            Log.i("Tutorial", "country to save: "+ country );
            String community = (String)questionsWithAns.get(1).getAns();
            Log.i("Tutorial", "community to save: "+ community );
            String headHouseholdName = (String)questionsWithAns.get(2).getAns();
            Log.i("Tutorial", "headHouseholdName to save: "+ headHouseholdName );
            String headHouseholdSex = (String)questionsWithAns.get(3).getAns();
            Log.i("Tutorial", "headHouseholdSex to save: "+ headHouseholdSex );
            String headHouseholdMaritalStatus = (String)questionsWithAns.get(4).getAns();
            Log.i("Tutorial", "headHouseholdMaritalStatus to save: "+ headHouseholdMaritalStatus );
            Integer headHouseholdAge = (Integer) questionsWithAns.get(5).getAns();
            Log.i("Tutorial", "headHouseholdAge to save: "+ headHouseholdAge );
            String headHouseholdOccupation = (String)questionsWithAns.get(6).getAns();
            Log.i("Tutorial", "headHouseholdOccupation to save: "+ headHouseholdOccupation );


            //create a new
            Family family = Family.builder()
                    .namebwe(namebwe)
                    .surveyId(0)
                    .country(country)
                    .community(community)
                    .headHouseholdName(headHouseholdName)
                    .headHouseholdSex(headHouseholdSex)
                    .headHouseholdMaritalStatus(headHouseholdMaritalStatus)
                    .headHouseholdAge(headHouseholdAge)
                    .headHouseholdOccupation(headHouseholdOccupation)
                    .headHouseholdEducationLevel("COMPLETEDSECONDARY")
                    .interviewee("BROTHER")
                    .numberPeopleHousehold(6)
                    .numberMaleHousehold1(5)
                    .numberFemaleHousehold1(1)
                    .numberMaleHousehold5(5)
                    .numberFemaleHousehold5(1)
                    .numberMaleHousehold12(5)
                    .numberFemaleHousehold12(1)
                    .numberMaleHousehold17(5)
                    .numberFemaleHousehold17(1)
                    .numberMaleHousehold18(5)
                    .numberFemaleHousehold18(1)
                    .reasonChildrenNotInSchool("NOTENOUGHFUNDS")
                    .date(date)
                    .build();

            //save family record
            Amplify.DataStore.save(family,
                update -> {
                    Log.i("Tutorial", "Saved Successfully ");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            showSaveSurvey("Saved Successfully");
                        }
                    });
                },
                failure -> {
                    Log.i("Tutorial", "Save Failed ");
                }
            );


        }
        return super.onOptionsItemSelected(item);
    }

    public void showSaveSurvey(String content) {
        DialogFragment newFragment = new SaveSurveyDialog(content);
        newFragment.show(getSupportFragmentManager(), "confirmSignUp");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        InitialSurveyActivity.this.finish();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.getDialog().cancel();
    }

 */
}
