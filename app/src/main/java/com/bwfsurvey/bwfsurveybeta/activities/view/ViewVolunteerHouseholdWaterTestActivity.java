package com.bwfsurvey.bwfsurveybeta.activities.view;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.AnswerType;
import com.amplifyframework.datastore.generated.model.VolunteerHouseholdWaterTest;
import com.bwfsurvey.bwfsurveybeta.MyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.adapters.ViewOnlyInterchangeCardAdapter;
import com.bwfsurvey.bwfsurveybeta.types.AnswerValue;
import com.bwfsurvey.bwfsurveybeta.types.Interchange;
import com.bwfsurvey.bwfsurveybeta.types.ViewOnlyInterchange;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ViewVolunteerHouseholdWaterTestActivity extends AppCompatActivity {
    VolunteerHouseholdWaterTest theVolunteerHouseholdWaterTest;
    private String uuidVolunteerHouseholdWaterTest;
    private RecyclerView recyclerView;
    private ViewOnlyInterchangeCardAdapter adapter;

    private static ArrayList<ViewOnlyInterchange> viewOnlyInterchanges;
    private LinearLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().getStringExtra("UUID")!=null)
            uuidVolunteerHouseholdWaterTest = getIntent().getStringExtra("UUID");

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createVolunteerHouseholdWaterTestViewOnlyInterchanges();
    }

    private void createVolunteerHouseholdWaterTestViewOnlyInterchanges() {
        viewOnlyInterchanges = new ArrayList<>();
        createViewOnlyInterchangesAndShowOnRecyclerView();
    }

    private void createViewOnlyInterchangesAndShowOnRecyclerView() {
        Amplify.DataStore.query(
                VolunteerHouseholdWaterTest.class,
                Where.matches(VolunteerHouseholdWaterTest.ID.eq(uuidVolunteerHouseholdWaterTest)),
                volunteerHouseholdWaterTest -> {
                    Log.i("Tutorials", "DataStore is queried.");
                    while (volunteerHouseholdWaterTest.hasNext()) {
                        theVolunteerHouseholdWaterTest = volunteerHouseholdWaterTest.next();
                        Log.i("Tutorials", "DataStore is queried. HouseholdWaterTest " +theVolunteerHouseholdWaterTest.getId());
                    }

                    runOnUiThread(new Runnable() {
                        public void run() {
                            createViewOnlyInterchangesAndShow();
                        }
                    });
                },
                failure ->{
                    Log.e("Tutorials", "Query failed.", failure);
                }
        );
    }

    private void createViewOnlyInterchangesAndShow() {
        if(theVolunteerHouseholdWaterTest!=null){
            ArrayList<Interchange> returnedInterchanges = MyAmplifyApplication.getInterchanges("WATERSURVEYHOUSEHOLD");
            for(int i=0;i<returnedInterchanges.size();i++){
                Interchange interchange = returnedInterchanges.get(i);
                String answer = "";
                String nameOfAns = interchange.getAnswer().getAnswerDef().getName();
                String methodName = "get"+nameOfAns;
                java.lang.reflect.Method method;
                try {
                    method = theVolunteerHouseholdWaterTest.getClass().getMethod(methodName);
                    Object ansObject = method.invoke(theVolunteerHouseholdWaterTest);
                    answer = ansObject.toString();
                    //answer is a programmer 1, convert it to a user friendly one
                    if(interchange.getAnswer().getAnswerDef().getType()== AnswerType.ENUMVALUE){
                        ArrayList<AnswerValue> allAnsValuesForThisInterchange = interchange.getAnswer().getAnswerValArrayList();
                        for(AnswerValue answerValue: allAnsValuesForThisInterchange){
                            if(answerValue.getValue().contentEquals(answer)){
                                answer = answerValue.getDesc();
                            }
                        }
                    }

                    if(interchange.getAnswer().getAnswerDef().getType()== AnswerType.ENUMMULTIPLEVALUE){
                        //answer will have a least 1 comma
                        String[]  answerList = answer.split(",");
                        String ansToWrite = "";
                        for(String anAnswer : answerList){
                            ArrayList<AnswerValue> allAnsValuesForThisInterchange = interchange.getAnswer().getAnswerValArrayList();
                            for(AnswerValue answerValue: allAnsValuesForThisInterchange){
                                if(answerValue.getValue().contentEquals(anAnswer)){
                                    ansToWrite = ansToWrite + answerValue.getDesc() + "\n";
                                }
                            }
                        }
                        answer = ansToWrite;
                    }

                    if(interchange.getAnswer().getAnswerDef().getType()== AnswerType.DATEVALUE){
                        //answer will have a least 1 comma
                        String ansToWrite = "";
                        String dateStr = answer;
                        if(dateStr.indexOf("{")>0&&dateStr.indexOf("}")>0){
                            dateStr = dateStr.substring(dateStr.indexOf("{") + 1);
                            dateStr = dateStr.substring(0, dateStr.indexOf("}"));
                            dateStr = dateStr.split(",")[0].split("=")[1];
                            dateStr = dateStr.substring( 1, dateStr.length() - 1 );
                            Log.i("Tutorial", "index dateStr: "+dateStr);

                            Date date_ = null;
                            try {
                                date_ = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                                String dateToShow = new SimpleDateFormat("dd/MM/yyyy").format(date_);

                                if(!dateToShow.contentEquals("01/01/1900"))
                                    ansToWrite = dateToShow;
                                else
                                    ansToWrite = "";

                            } catch (Exception e) {
                                ansToWrite = "";
                            }

                        }else{
                            Log.i("Tutorial", "out index dateStr: "+dateStr);
                            ansToWrite = "";
                        }
                        answer = ansToWrite;
                    }


                } catch (Exception e) {
                    Log.e("Tutorials", "Could not get answer");
                }

                viewOnlyInterchanges.add(new ViewOnlyInterchange(interchange.getName(),interchange.getInterchangeNumber(),interchange.getQuestion().getQuestionText(),answer));
                Collections.sort(viewOnlyInterchanges);

                showViewOnlyInterchanges();
            }
        }
    }

    private void showViewOnlyInterchanges() {
        //wait a lil bit so that if we are offline things will settle
        //this is for the progress bar
        progressBar = (LinearLayout) findViewById(R.id.llProgressBar);
        TextView progressBarText = (TextView) findViewById(R.id.pbText);
        progressBarText.setText("Please wait... Getting records!");
        progressBar.setVisibility(View.VISIBLE);
        CountDownTimer countDownTimer = new CountDownTimer(MyAmplifyApplication.manualTimer,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                progressBar.setVisibility(View.GONE);
                initViewElements();
            }
        };
        countDownTimer.start();
    }

    private void initViewElements() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ViewOnlyInterchangeCardAdapter(ViewVolunteerHouseholdWaterTestActivity.this, ViewVolunteerHouseholdWaterTestActivity.viewOnlyInterchanges);
        recyclerView.setAdapter(adapter);
    }
}
