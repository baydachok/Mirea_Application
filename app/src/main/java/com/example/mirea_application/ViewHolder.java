package com.example.mirea_application;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView message;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        message = (TextView) itemView.findViewById(R.id.message_item);
    }
}
