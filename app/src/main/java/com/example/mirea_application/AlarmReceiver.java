package com.example.mirea_application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        Intent signal = new Intent(context, AlarmSignal.class);
        context.startActivity(signal);
    }
}
