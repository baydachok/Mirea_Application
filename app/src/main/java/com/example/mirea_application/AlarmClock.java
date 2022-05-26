package com.example.mirea_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AlarmClock extends AppCompatActivity {

    List<ListClock> listClocks;
    ListView listContainer;
    AlarmClockAdapter adapter;
    Intent settingsIntent;
    Bundle arguments;
    Button addAlarmClock;
    String time =  "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmclock);

        listClocks = new ArrayList();
        settingsIntent = new Intent(this, AlarmSettings.class);
        listContainer = findViewById(R.id.countriesList);
        adapter = new AlarmClockAdapter(this, R.layout.alarm_clock_list_item, listClocks);
        arguments = getIntent().getExtras();
        addAlarmClock = findViewById(R.id.newAlarmClock);

        listContainer.setAdapter(adapter);
        if(arguments != null)
        createItem();
        addAlarmClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(settingsIntent);
            }
        });



    }
    private void createItem(){
        time = String.valueOf(arguments.get("time"));
        listClocks.add(new ListClock(time, R.drawable.alarm));

    }

}
