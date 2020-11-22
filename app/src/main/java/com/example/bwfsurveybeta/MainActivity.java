package com.example.bwfsurveybeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_add = (Button) findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Date c = Calendar.getInstance().getTime();
                String desSaved = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.getDefault()).format(c);
                Todo item = Todo.builder()
                        .name("Use AppSync from android")
                        .description( desSaved + " from android")
                        .build();

                Amplify.DataStore.save(
                        item,
                        success -> Log.i("Tutorial", "Saved item: " + success.item().getName()),
                        error -> {
                            Log.e("Tutorial", "Could not save item to DataStore", error);
                        }
                );
            }
        });

        Button button_query = (Button) findViewById(R.id.button_query);
        button_query.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Amplify.DataStore.query(
                        Todo.class,
                        todos -> {
                            while (todos.hasNext()) {
                                Todo todo = todos.next();

                                Log.i("Tutorial", "==== Todo ====");
                                Log.i("Tutorial", "Name: " + todo.getName());

                                if (todo.getDescription() != null) {
                                    Log.i("Tutorial", "Description: " + todo.getDescription());
                                }
                            }
                        },
                        failure -> Log.e("Tutorial", "Could not query DataStore", failure)
                );
            }
        });

        Button button_clear = (Button) findViewById(R.id.button_clear);
        button_clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Amplify.DataStore.clear(
                        () -> Log.i("Tutorial", "DataStore cleared"),
                        error -> Log.e("Tutorial", "Error clearing DataStore: ", error)
                );
            }
        });
    }
}