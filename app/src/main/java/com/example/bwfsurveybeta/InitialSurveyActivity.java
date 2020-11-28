package com.example.bwfsurveybeta;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.AnswerType;
import com.amplifyframework.datastore.generated.model.EducationLevel;
import com.amplifyframework.datastore.generated.model.Family;
import com.amplifyframework.datastore.generated.model.FamilyDef;
import com.amplifyframework.datastore.generated.model.Interviewee;
import com.amplifyframework.datastore.generated.model.MaritalStatus;
import com.amplifyframework.datastore.generated.model.MaritalStatusDef;
import com.amplifyframework.datastore.generated.model.NoSchoolReason;
import com.amplifyframework.datastore.generated.model.Occupation;
import com.amplifyframework.datastore.generated.model.Sex;
import com.amplifyframework.datastore.generated.model.SexDef;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InitialSurveyActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private QuestionCardAdapter adapter;
    private ArrayList<Question> questions;

    private ArrayList<PossibleAns> possibleAnssSex = null;
    private ArrayList<PossibleAns> possibleAnssMaritalStatus = null;
    private ArrayList<PossibleAns> possibleAnssOccupation = null;
    private ArrayList<Question> familyQuestions = null;
    private String namebwe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        namebwe = getIntent().getStringExtra("NAME_BWE");
        setContentView(R.layout.activity_recycler);
        initView();
    }

    private void initView() {
        createQuestionaire();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuestionCardAdapter(InitialSurveyActivity.this, this.questions);
        recyclerView.setAdapter(adapter);
    }

    private void getQuestionaireConfigs(){
        //get all congfiguration data for question
        getSexDefs();; //<-- put your code in here.
        getMaritalStatus();//<-- put your code in here.
        getFamilyQuestions();;//<-- put your code in here.
    }

    private void loadQuestionaireConfigsManually(){
        loadSexDefsManually();
        loadMaritalStatusDefManually();
        loadOccupationDefManually();
        loadFamilyQuestionsManually();
    }

    private void getSexDefs(){
        try{
            Amplify.DataStore.query(
                    SexDef.class,
                    sexdefs -> {
                        Log.i("Tutorial", "==== DataStore sexDef query success ====");
                        this.possibleAnssSex = new ArrayList<>();
                        while (sexdefs.hasNext()) {
                            SexDef sexDef = sexdefs.next();
                            PossibleAns possibleAns = new PossibleAns(sexDef.getFieldName(),sexDef.getFieldDescription());
                            this.possibleAnssSex.add(possibleAns);
                        }
                        for(PossibleAns possibleAns : this.possibleAnssSex){
                            Log.i("Tutorial", "possibleAnssSex name "+ possibleAns.getName() + " possibleAns value "+possibleAns.getValue());
                        }

                    },
                    failure -> {
                        Log.i("Tutorial", "Could not query SexDef in DataStore", failure);
                        if(this.possibleAnssSex==null)
                            this.possibleAnssSex = new ArrayList<>();
                        this.possibleAnssSex.add(new PossibleAns(Sex.MALE.name(),Sex.MALE.name()));
                        this.possibleAnssSex.add(new PossibleAns(Sex.FEMALE.name(),Sex.FEMALE.name()));
                        for(PossibleAns possibleAns : this.possibleAnssSex){
                            Log.i("Tutorial", "possibleAnssSex name "+ possibleAns.getName() + " possibleAns value "+possibleAns.getValue());
                        }
                    }
            );
        }catch (Exception ex){
            if(this.possibleAnssSex==null)
                this.possibleAnssSex = new ArrayList<>();
            else
                this.possibleAnssSex.clear();
            this.possibleAnssSex.add(new PossibleAns(Sex.MALE.name(),Sex.MALE.name()));
            this.possibleAnssSex.add(new PossibleAns(Sex.FEMALE.name(),Sex.FEMALE.name()));
            for(PossibleAns possibleAns : this.possibleAnssSex){
                Log.i("Tutorial", "possibleAnssSex name "+ possibleAns.getName() + " possibleAns value "+possibleAns.getValue());
            }
        }
    }

    private void loadSexDefsManually(){
        if(this.possibleAnssSex==null)
            this.possibleAnssSex = new ArrayList<>();
        else
            this.possibleAnssSex.clear();
        this.possibleAnssSex.add(new PossibleAns(Sex.MALE.name(),Sex.MALE.name()));
        this.possibleAnssSex.add(new PossibleAns(Sex.FEMALE.name(),Sex.FEMALE.name()));
        for(PossibleAns possibleAns : this.possibleAnssSex){
            Log.i("Tutorial", "possibleAnssSex name "+ possibleAns.getName() + " possibleAns value "+possibleAns.getValue());
        }
    }

    private void getMaritalStatus(){
        try{
            Amplify.DataStore.query(
                    MaritalStatusDef.class,
                    maritalStatusDefs -> {
                        Log.i("Tutorial", "==== DataStore maritalStatusDefs query success ====");
                        this.possibleAnssMaritalStatus = new ArrayList<>();
                        while (maritalStatusDefs.hasNext()) {
                            MaritalStatusDef maritalStatusDef = maritalStatusDefs.next();
                            PossibleAns possibleAns = new PossibleAns(maritalStatusDef.getFieldName(),maritalStatusDef.getFieldDescription());
                            this.possibleAnssMaritalStatus.add(possibleAns);
                        }
                        for(PossibleAns possibleAns : this.possibleAnssMaritalStatus){
                            Log.i("Tutorial", "possibleAnssMaritalStatus name "+ possibleAns.getName() + " possibleAns value "+possibleAns.getValue());
                        }
                    },
                    failure -> {
                        Log.i("Tutorial", "Could not query maritalStatusDefs in DataStore", failure);
                        if(this.possibleAnssMaritalStatus==null)
                            this.possibleAnssMaritalStatus = new ArrayList<>();
                        this.possibleAnssMaritalStatus.add(new PossibleAns(MaritalStatus.MARRIED.name(),MaritalStatus.MARRIED.name()));
                        this.possibleAnssMaritalStatus.add(new PossibleAns(MaritalStatus.SINGLE.name(),MaritalStatus.SINGLE.name()));
                        this.possibleAnssMaritalStatus.add(new PossibleAns(MaritalStatus.WIDOWED.name(),MaritalStatus.WIDOWED.name()));
                        this.possibleAnssMaritalStatus.add(new PossibleAns(MaritalStatus.DIVORCED.name(),MaritalStatus.DIVORCED.name()));
                        this.possibleAnssMaritalStatus.add(new PossibleAns(MaritalStatus.COHABITING.name(),MaritalStatus.COHABITING.name()));
                        for(PossibleAns possibleAns : this.possibleAnssMaritalStatus){
                            Log.i("Tutorial", "possibleAnssMaritalStatus name "+ possibleAns.getName() + " possibleAns value "+possibleAns.getValue());
                        }
                    }
            );
        }catch (Exception e){
            Log.i("Tutorial", "Could not query maritalStatusDefs in DataStore");
            if(this.possibleAnssMaritalStatus==null){
                this.possibleAnssMaritalStatus = new ArrayList<>();
            }else{
                this.possibleAnssMaritalStatus.clear();
            }
            this.possibleAnssMaritalStatus.add(new PossibleAns(MaritalStatus.MARRIED.name(),MaritalStatus.MARRIED.name()));
            this.possibleAnssMaritalStatus.add(new PossibleAns(MaritalStatus.SINGLE.name(),MaritalStatus.SINGLE.name()));
            this.possibleAnssMaritalStatus.add(new PossibleAns(MaritalStatus.WIDOWED.name(),MaritalStatus.WIDOWED.name()));
            this.possibleAnssMaritalStatus.add(new PossibleAns(MaritalStatus.DIVORCED.name(),MaritalStatus.DIVORCED.name()));
            this.possibleAnssMaritalStatus.add(new PossibleAns(MaritalStatus.COHABITING.name(),MaritalStatus.COHABITING.name()));

            for(PossibleAns possibleAns : this.possibleAnssMaritalStatus){
                Log.i("Tutorial", "possibleAnssMaritalStatus name "+ possibleAns.getName() + " possibleAns value "+possibleAns.getValue());
            }
        }
    }

    private void loadMaritalStatusDefManually(){
        if(this.possibleAnssMaritalStatus==null){
            this.possibleAnssMaritalStatus = new ArrayList<>();
        }else{
            this.possibleAnssMaritalStatus.clear();
        }
        this.possibleAnssMaritalStatus.add(new PossibleAns(MaritalStatus.MARRIED.name(),MaritalStatus.MARRIED.name()));
        this.possibleAnssMaritalStatus.add(new PossibleAns(MaritalStatus.SINGLE.name(),MaritalStatus.SINGLE.name()));
        this.possibleAnssMaritalStatus.add(new PossibleAns(MaritalStatus.WIDOWED.name(),MaritalStatus.WIDOWED.name()));
        this.possibleAnssMaritalStatus.add(new PossibleAns(MaritalStatus.DIVORCED.name(),MaritalStatus.DIVORCED.name()));
        this.possibleAnssMaritalStatus.add(new PossibleAns(MaritalStatus.COHABITING.name(),MaritalStatus.COHABITING.name()));

        for(PossibleAns possibleAns : this.possibleAnssMaritalStatus){
            Log.i("Tutorial", "possibleAnssMaritalStatus name "+ possibleAns.getName() + " possibleAns value "+possibleAns.getValue());
        }
    };

    private void loadOccupationDefManually(){
        if(this.possibleAnssOccupation==null){
            this.possibleAnssOccupation = new ArrayList<>();
        }else{
            this.possibleAnssOccupation.clear();
        }
        this.possibleAnssOccupation.add(new PossibleAns(Occupation.FARMER.name(),Occupation.FARMER.name()));
        this.possibleAnssOccupation.add(new PossibleAns(Occupation.TRADER.name(),Occupation.TRADER.name()));
        this.possibleAnssOccupation.add(new PossibleAns(Occupation.TEACHER.name(),Occupation.TEACHER.name()));
        this.possibleAnssOccupation.add(new PossibleAns(Occupation.FISHERMAN.name(),Occupation.FISHERMAN.name()));
        this.possibleAnssOccupation.add(new PossibleAns(Occupation.CIVILSERVANT.name(),"CIVIL SERVANT (WORKS FOR GOVERNMENT)"));
        this.possibleAnssOccupation.add(new PossibleAns(Occupation.NONE.name(),Occupation.NONE.name()));
        this.possibleAnssOccupation.add(new PossibleAns(Occupation.OTHER.name(),Occupation.OTHER.name()));

        for(PossibleAns possibleAns : this.possibleAnssOccupation){
            Log.i("Tutorial", "possibleAnssOccupation name "+ possibleAns.getName() + " possibleAns value "+possibleAns.getValue());
        }
    }

    private void getFamilyQuestions(){
        try{
            Amplify.DataStore.query(
                    FamilyDef.class,
                    familydefs -> {
                        Log.i("Tutorial", "==== DataStore familydefs query success ====");
                        this.familyQuestions = new ArrayList<>();
                        while (familydefs.hasNext()) {
                            FamilyDef familyQuestionDef = familydefs.next();
                            Question familyQuestion ;
                            if(familyQuestionDef.getFieldAnswerTypeName().contentEquals("Sex")){
                                familyQuestion = new Question(familyQuestionDef.getFieldQuestionText(),familyQuestionDef.getFieldAnswerType().name(),familyQuestionDef.getFieldAnswerTypeName(),this.possibleAnssSex);
                            }else if(familyQuestionDef.getFieldAnswerTypeName().contentEquals("MaritalStatus")){
                                familyQuestion = new Question(familyQuestionDef.getFieldQuestionText(),familyQuestionDef.getFieldAnswerType().name(),familyQuestionDef.getFieldAnswerTypeName(),this.possibleAnssMaritalStatus);
                            }else{
                                familyQuestion = new Question(familyQuestionDef.getFieldQuestionText(),familyQuestionDef.getFieldAnswerType().name(),familyQuestionDef.getFieldAnswerTypeName(),null);
                            }
                            this.familyQuestions.add(familyQuestion);
                        }
                        for(Question question : this.familyQuestions){
                            Log.i("Tutorial", "question  getQuestionText "+ question.getQuestionText() + " possibleAns value "+question.getAnsType());
                        }



                    },
                    failure -> {
                        Log.i("Tutorial", "Could not query familydefs in DataStore", failure);
                        if(this.familyQuestions==null)
                            this.familyQuestions = new ArrayList<>();
                        Question familyCountry = new Question("Name of Country",AnswerType.TEXTVALUE.name(),"",null);
                        this.familyQuestions.add(familyCountry);
                        Question familyCommunity = new Question("Name of Community",AnswerType.TEXTVALUE.name(),"",null);
                        this.familyQuestions.add(familyCommunity);
                        Question familyHeadName = new Question("Head of Household Name",AnswerType.TEXTVALUE.name(),"",null);
                        this.familyQuestions.add(familyHeadName);
                        Question familyHeadNameSex = new Question("Head of Household Sex",AnswerType.ENUMVALUE.name(),"Sex",this.possibleAnssSex);
                        this.familyQuestions.add(familyHeadNameSex);
                        Question familyHeadNameMaritalStatus = new Question("Head of Household Marital Status",AnswerType.ENUMVALUE.name(),"MaritalStatus",this.possibleAnssMaritalStatus);
                        this.familyQuestions.add(familyHeadNameMaritalStatus);
                        Question familyHeadNameAge = new Question("Head of Household Age",AnswerType.NUMBERVALUE.name(),"",null);
                        this.familyQuestions.add(familyHeadNameAge);
                        for(Question question : this.familyQuestions){
                            Log.i("Tutorial", "question  getQuestionText "+ question.getQuestionText() + " possibleAns value "+question.getAnsType());
                        }
                    }
            );
        }catch (Exception e){
            Log.i("Tutorial", "Could not query familydefs in DataStore");
            if(this.familyQuestions==null){
                this.familyQuestions = new ArrayList<>();
            }else{
                this.familyQuestions.clear();
            }
            Question familyCountry = new Question("Name of Country",AnswerType.TEXTVALUE.name(),"",null);
            this.familyQuestions.add(familyCountry);
            Question familyCommunity = new Question("Name of Community",AnswerType.TEXTVALUE.name(),"",null);
            this.familyQuestions.add(familyCommunity);
            Question familyHeadName = new Question("Head of Household Name",AnswerType.TEXTVALUE.name(),"",null);
            this.familyQuestions.add(familyHeadName);
            Question familyHeadNameSex = new Question("Head of Household Sex",AnswerType.ENUMVALUE.name(),"Sex",this.possibleAnssSex);
            this.familyQuestions.add(familyHeadNameSex);
            Question familyHeadNameMaritalStatus = new Question("Head of Household Marital Status",AnswerType.ENUMVALUE.name(),"MaritalStatus",this.possibleAnssMaritalStatus);
            this.familyQuestions.add(familyHeadNameMaritalStatus);
            Question familyHeadNameAge = new Question("Head of Household Age",AnswerType.NUMBERVALUE.name(),"",null);
            this.familyQuestions.add(familyHeadNameAge);
            for(Question question : this.familyQuestions){
                Log.i("Tutorial", "question  getQuestionText "+ question.getQuestionText() + " possibleAns value "+question.getAnsType());
            }
        }
    }

    private void loadFamilyQuestionsManually(){
        if(this.familyQuestions==null){
            this.familyQuestions = new ArrayList<>();
        }else{
            this.familyQuestions.clear();
        }
        Question familyCountry = new Question("Name of Country",AnswerType.TEXTVALUE.name(),"",null);
        this.familyQuestions.add(familyCountry);
        Question familyCommunity = new Question("Name of Community",AnswerType.TEXTVALUE.name(),"",null);
        this.familyQuestions.add(familyCommunity);
        Question familyHeadName = new Question("Head of Household Name",AnswerType.TEXTVALUE.name(),"",null);
        this.familyQuestions.add(familyHeadName);
        Question familyHeadNameSex = new Question("Head of Household Sex",AnswerType.ENUMVALUE.name(),"Sex",this.possibleAnssSex);
        this.familyQuestions.add(familyHeadNameSex);
        Question familyHeadNameMaritalStatus = new Question("Head of Household Marital Status",AnswerType.ENUMVALUE.name(),"MaritalStatus",this.possibleAnssMaritalStatus);
        this.familyQuestions.add(familyHeadNameMaritalStatus);
        Question familyHeadNameAge = new Question("Head of Household Age",AnswerType.NUMBERVALUE.name(),"",null);
        this.familyQuestions.add(familyHeadNameAge);
        Question familyHeadNameOccupation = new Question("Head of Household Occupation",AnswerType.ENUMVALUE.name(),"Occupation",this.possibleAnssOccupation);
        this.familyQuestions.add(familyHeadNameOccupation);
        for(Question question : this.familyQuestions){
            Log.i("Tutorial", "question  getQuestionText "+ question.getQuestionText() + " possibleAns value "+question.getAnsType());
        }
    }


    private void createQuestionaire() {
        this.questions = new ArrayList<>();
        //getQuestionaireConfigs();
        loadQuestionaireConfigsManually();

        for(Question familyQuestion: this.familyQuestions){
            this.questions.add(familyQuestion);
        }

        Log.i("Tutorial", "questions "+ questions.size());
        /*
        runOnUiThread(new Runnable() {
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
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
                    .headHouseholdSex(Sex.valueOf(headHouseholdSex))
                    .headHouseholdMaritalStatus(MaritalStatus.valueOf(headHouseholdMaritalStatus))
                    .headHouseholdAge(headHouseholdAge)
                    .headHouseholdOccupation(Occupation.valueOf(headHouseholdOccupation))
                    .headHouseholdEducationLevel(EducationLevel.COMPLETEDSECONDARY)
                    .interviewee(Interviewee.BROTHER)
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
                    .reasonChildrenNotInSchool(NoSchoolReason.NOTENOUGHFUNDS)
                    .date(date)
                    .build();

            //save family record
            Amplify.DataStore.save(family,
                    update -> {
                        Log.i("Tutorial", "Saved Successfully ");
                    },
                    failure -> {
                        Log.i("Tutorial", "Save Failed ");
                    }
            );

             
        }
        return super.onOptionsItemSelected(item);
    }
}
