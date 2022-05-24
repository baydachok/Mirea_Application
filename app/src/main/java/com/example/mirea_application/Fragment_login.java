package com.example.mirea_application;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;


public class Fragment_login extends Fragment implements View.OnClickListener {
    private TextView registerLogin, forgotPassword;

    private EditText editTextEmail, editTextPassword;

    private Button signIn;

    private FirebaseAuth mAuth;

    private ProgressBar progressBar;


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

        registerLogin  = (TextView) view.findViewById(R.id.login_register);
        forgotPassword = (TextView) view.findViewById(R.id.login_forget_password);
        forgotPassword.setOnClickListener(this);

        editTextEmail = (EditText) view.findViewById(R.id.login_email);
        editTextPassword = (EditText) view.findViewById(R.id.login_password);

        signIn = (Button) view.findViewById(R.id.login_signIn);
        signIn.setOnClickListener(this);

        progressBar = (ProgressBar) view.findViewById(R.id.login_progressBar);

        registerLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_register:
                // Сделать ввод строки для багов
                loadFragment(new Fragment_register(), "Finish Fragment_login, start Fragment_register");
                break;
            case R.id.login_signIn:
                userLogin();
                break;
            case R.id.login_forget_password:
                loadFragment(new Fragment_forgotPassword(), "Finish Fragment_login, start Fragment_forgotPassword");
                break;
        }
    }


    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6){
            editTextPassword.setError("Min password length is 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()){
                        loadFragment(new Fragment_profile(), "Finish Fragment_login, start Fragment_main");
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(getActivity(), "Check your email to verify you account",
                                Toast.LENGTH_LONG).show();
                    }



                }else{
                    Toast.makeText(getActivity(), "Failed to login! Please check your credentials",
                            Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void loadFragment(Fragment fragment, String Tag) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.body, fragment,Tag)
                .addToBackStack(null)
                .commit();
    }
}

