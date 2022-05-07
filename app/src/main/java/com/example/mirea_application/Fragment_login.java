package com.example.mirea_application;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment_login extends Fragment implements View.OnClickListener {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {

        }*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_login, container, false);

        TextView registerLogin  = (TextView) view.findViewById(R.id.login_register);
        registerLogin.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_register:
                Fragment_register nextFrag= new Fragment_register();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.body, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}