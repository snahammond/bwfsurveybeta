package com.example.bwfsurveybeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Family;
import com.amplifyframework.datastore.generated.model.FamilyDef;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recycler View with Card View");
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        questions = new ArrayList<>();
        adapter = new CardAdapter(this, questions);
        recyclerView.setAdapter(adapter);
        createListData();
    }

    private void createListData() {
        //get the list of questions
        Amplify.DataStore.query(
                FamilyDef.class,
                familydefs -> {
                    while (familydefs.hasNext()) {
                        FamilyDef familyDef = familydefs.next();

                        Log.i("Tutorial", "==== DataStore Family Def that contains question ====");
                        Log.i("Tutorial", "FieldName: " + familyDef.getFieldName());
                        Log.i("Tutorial", "FieldQuestionText: " + familyDef.getFieldQuestionText());
                        Log.i("Tutorial", "FieldAnswerType: " + familyDef.getFieldAnswerType().name());

                        Question question = new Question(familyDef.getFieldQuestionText());
                        questions.add(question);
                    }
                    adapter.notifyDataSetChanged();
                },
                failure -> Log.e("Tutorial", "Could not query DataStore", failure)
        );

        /*
        Amplify.API.query(
                ModelQuery.list(FamilyDef.class),
                response -> {
                    for (FamilyDef familyDef : response.getData()) {
                        Log.i("Tutorial", "==== Family Def that contains question ====");
                        Log.i("Tutorial", "FieldName: " + familyDef.getFieldName());
                        Log.i("Tutorial", "FieldQuestionText: " + familyDef.getFieldQuestionText());
                        Log.i("Tutorial", "FieldAnswerType: " + familyDef.getFieldAnswerType().name());
                    }
                },
                error -> Log.e("Tutorial", error.toString(), error)
        );*/

    }


}