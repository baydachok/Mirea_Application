package com.example.mirea_application;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment_menu extends Fragment {

    private Button menu;
    private Button time;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        menu = (Button) view.findViewById(R.id.menu_anonimChat);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Fragment_anonymousChat(), "anonymousChat");
            }
        });
        time = (Button) view.findViewById(R.id.menu_cleverWatch);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });




        return view;
    }


    private void loadFragment(Fragment fragment, String Tag) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.body, fragment,Tag)
                .addToBackStack(null)
                .commit();
    }
}
