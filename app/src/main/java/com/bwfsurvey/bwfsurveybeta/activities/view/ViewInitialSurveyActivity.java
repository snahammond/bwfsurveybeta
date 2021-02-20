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
import com.amplifyframework.datastore.generated.model.InitialSurvey;
import com.bwfsurvey.bwfsurveybeta.MyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.types.Answer;
import com.bwfsurvey.bwfsurveybeta.types.AnswerValue;
import com.bwfsurvey.bwfsurveybeta.types.Question;
import com.bwfsurvey.bwfsurveybeta.types.Validation;
import com.bwfsurvey.bwfsurveybeta.types.ViewOnlyInterchange;
import com.bwfsurvey.bwfsurveybeta.adapters.ViewOnlyInterchangeCardAdapter;
import com.bwfsurvey.bwfsurveybeta.types.Interchange;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;
import java.util.Collections;

public class ViewInitialSurveyActivity extends AppCompatActivity {
    InitialSurvey theInitialSurvey;
    private String uuidInitialSurvey;
    private RecyclerView recyclerView;
    private ViewOnlyInterchangeCardAdapter adapter;

    private static ArrayList<ViewOnlyInterchange> viewOnlyInterchanges;
    private LinearLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().getStringExtra("UUID")!=null)
            uuidInitialSurvey = getIntent().getStringExtra("UUID");

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createInitialSurveyViewOnlyInterchanges();
    }

    private void createInitialSurveyViewOnlyInterchanges() {
        viewOnlyInterchanges = new ArrayList<>();
        createViewOnlyInterchangesAndShowOnRecyclerView();
    }

    private void createViewOnlyInterchangesAndShowOnRecyclerView() {
        Amplify.DataStore.query(
                InitialSurvey.class,
                Where.matches(InitialSurvey.ID.eq(uuidInitialSurvey)),
                initialSurvey -> {
                    Log.i("Tutorials", "DataStore is queried.");
                    while (initialSurvey.hasNext()) {
                        theInitialSurvey = initialSurvey.next();
                        Log.i("Tutorials", "DataStore is queried. theInitialSurvey " +theInitialSurvey.getId());
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
        if(theInitialSurvey!=null){
            ArrayList<Interchange> returnedInterchanges = MyAmplifyApplication.getInterchanges("INITAILSURVEY");
            for(int i=0;i<returnedInterchanges.size();i++){
                Interchange interchange = returnedInterchanges.get(i);
                String answer = "";
                String nameOfAns = interchange.getAnswer().getAnswerDef().getName();
                String methodName = "get"+nameOfAns;
                java.lang.reflect.Method method;
                try {
                    method = theInitialSurvey.getClass().getMethod(methodName);
                    Object ansObject = method.invoke(theInitialSurvey);
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
        CountDownTimer countDownTimer = new CountDownTimer(16000,1000) {
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
        adapter = new ViewOnlyInterchangeCardAdapter(ViewInitialSurveyActivity.this, ViewInitialSurveyActivity.viewOnlyInterchanges);
        recyclerView.setAdapter(adapter);
    }

}
