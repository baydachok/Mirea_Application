package com.example.mirea_application;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


public class MainActivity1 extends TabActivity {


    TabHost tabHost;
    TabHost.TabSpec tabSpec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tabHost = getTabHost();

        tabSpec = tabHost.newTabSpec("tab1");
        tabSpec.setIndicator("Часы");
        tabSpec.setContent(new Intent(this, Clock.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab2");
        tabSpec.setIndicator("Будильник");
        tabSpec.setContent(new Intent(this, AlarmClock.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab3");
        tabSpec.setIndicator("Геобудильник");
        tabSpec.setContent(new Intent(this, GeoAlarmClock.class));
        tabHost.addTab(tabSpec);


    }


}
