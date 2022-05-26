package com.example.mirea_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        loadFragment(new Fragment_login());





    }




    public void loadFragment(Fragment fr){
        // Менеджер переключения фрагментов
        FragmentManager fm = getSupportFragmentManager();

        // Передача данных



        // Переключение
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.body,fr);
        ft.addToBackStack(null);
        ft.commit();
    }
}
