package com.example.mirea_application;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlarmSettings extends AppCompatActivity {


    TextView periodCheck;
    Button set;
    Button cancel;
    TimePicker timePicker;
    PendingIntent pendingIntent;
    Intent alarmIntent;
    Intent valueIntent;
    AlarmManager alarmManager;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_settings);





        periodCheck = findViewById(R.id.textView);
        set = findViewById(R.id.btnSet);
        cancel = findViewById(R.id.btnCancel);
        timePicker = this.findViewById(R.id.timePicker);
        alarmIntent = new Intent(AlarmSettings.this, AlarmReceiver.class);
        valueIntent = new Intent(AlarmSettings.this, AlarmClock.class);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        pendingIntent = PendingIntent.getBroadcast(AlarmSettings.this, 0, alarmIntent, 0);

        final int HourNow = timePicker.getHour();
        final int MinNow = timePicker.getMinute();

        timePicker.setIs24HourView(true);


        set.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                int Hour = timePicker.getHour();
                int Min = timePicker.getMinute();
                int Interval = timeConverter(HourNow, MinNow, Hour, Min);

                Calendar time = Calendar.getInstance();
                time.setTimeInMillis(System.currentTimeMillis());
                messageOutput(Interval);
                time.add(Calendar.SECOND, Interval);// Mast be changed later or not \_(ツ)_/
                periodCheck.setText(String.valueOf(Interval));// Check
                valueIntent.putExtra("time", String.valueOf(Hour + ":" + Min));
                //alarmManager.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(),pendingIntent);
                startActivity(valueIntent);


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (alarmManager != null) {
                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(AlarmSettings.this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private int timeConverter(int HourNow, int MinNow, int Hour, int Min) {
        int Period = 0;
        if (HourNow <= Hour) {
            if (MinNow <= Min) Min = (Min - MinNow) * 60000;
            else Min = (60 - MinNow + Min) * 60000;
            Hour = Math.abs((Hour - HourNow) * 3600000);
            Period = Hour + Min;

        } else {
            if (MinNow <= Min) Min = (Min - MinNow) * 60000;
            else Min = (60 - MinNow + Min) * 60000;
            Hour = (24 + (Hour - HourNow)) * 3600000;
            Period = Hour + Min;
        }
        return Period;
    }

    private void messageOutput(int Interval) {
        Interval = Interval / 60000;
        int Hour = Interval / 60;
        int Min = Interval - Hour;
        if (Hour > 0 && Min > 0) {
            Toast.makeText(this, "будильник сработает через " + Hour + " ч. " + Min + " мин.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Hour == 0) {
            Toast.makeText(this, "будильник сработает через " + Min + " мин.", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "будильник сработает через " + Hour + " ч. ", Toast.LENGTH_SHORT).show();

    }


}