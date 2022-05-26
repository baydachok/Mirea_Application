package com.example.mirea_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AlarmClockAdapter extends ArrayAdapter<ListClock> {
    private LayoutInflater inflater;
    private int layout;
    private List<ListClock> listClocks;

    public AlarmClockAdapter(Context context, int resource, List<ListClock> listClocks) {
        super(context, resource, listClocks);
        this.listClocks = listClocks;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(this.layout, parent, false);

        ImageView flagView = view.findViewById(R.id.flag);
        TextView nameView = view.findViewById(R.id.name);


        ListClock listClock = listClocks.get(position);

        flagView.setImageResource(listClock.getFlagResource());
        nameView.setText(listClock.getName());

        return view;
    }

}
