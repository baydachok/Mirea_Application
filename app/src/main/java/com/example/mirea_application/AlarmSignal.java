package com.example.mirea_application;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmSignal extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button btnSkull;
    TextView textWakeUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_signal);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);


        textWakeUp = findViewById(R.id.textWakeUp);
        btnSkull = findViewById(R.id.btnSkull);
        mediaPlayer = MediaPlayer.create(this, R.raw.wake_fuck_up_samurai);

        Typeface face = Typeface.createFromAsset(getAssets(), "font/cyber_punk.otf");
        textWakeUp.setTypeface(face);
        mediaPlayer.start();


        btnSkull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                finish();
            }
        });
    }

}
