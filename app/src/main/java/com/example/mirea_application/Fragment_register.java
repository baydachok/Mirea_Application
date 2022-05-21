package com.example.mirea_application;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Fragment_register extends Fragment implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private EditText editTextFullName, editTextAge, editTextEmail, editTextPassword, editTextInstitute;
    private TextView banner, registerUser;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_register, container, false);

        banner = (TextView) view.findViewById(R.id.bannerRegister);
        banner.setOnClickListener(this);

        registerUser = (Button) view.findViewById(R.id.register_user);
        registerUser.setOnClickListener(this);

        editTextAge = (EditText) view.findViewById(R.id.register_age);
        editTextEmail= (EditText) view.findViewById(R.id.register_email);
        editTextFullName = (EditText) view.findViewById(R.id.register_fullname);
        editTextPassword = (EditText) view.findViewById(R.id.register_password);
        editTextInstitute = (EditText) view.findViewById(R.id.register_Institute);

        progressBar  = (ProgressBar) view.findViewById(R.id.progressBarRegister);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bannerRegister:
                Fragment_login nextFrag= new Fragment_login();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.body, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.register_user:
                registerUser();
                break;
        }
    }
    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String institute = editTextInstitute.getText().toString().trim();

        if (fullName.isEmpty()) {
            editTextAge.setError("Full name is required!");
            editTextFullName.requestFocus();
            return;
        }
        if (age.isEmpty()) {
            editTextAge.setError("Age is required");
            editTextAge.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextAge.setError("Email is required");
            editTextAge.requestFocus();
            return;
        }
        if (institute.length() <= 2) {
            editTextAge.setError("Email is required");
            editTextAge.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide valid email!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(fullName, age, email, institute);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }else{
                                        Toast.makeText(getActivity(), "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }
                    }
                });

    }
}