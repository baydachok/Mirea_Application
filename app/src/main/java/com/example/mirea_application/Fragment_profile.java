package com.example.mirea_application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Fragment_profile extends Fragment {

    private FirebaseUser user;
    private DatabaseReference reference;

    private Button logout;
    private Button menu;

    private String userID;

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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        logout = (Button) view.findViewById(R.id.profle_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                loadFragment(new Fragment_login(), "logout");
            }
        });

        menu = (Button) view.findViewById(R.id.profile_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Fragment_menu(), "menu");
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView greetingTextView = (TextView) view.findViewById(R.id.profile_greeting);
        final TextView fullNameTextView = (TextView) view.findViewById(R.id.profile_fullname);
        final TextView emailTextView = (TextView) view.findViewById(R.id.profile_Email);
        final TextView ageTextView = (TextView) view.findViewById(R.id.profile_age);
        final TextView instituteTextView = (TextView) view.findViewById(R.id.profle_institute);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String fullname = userProfile.fullname;
                    String email = userProfile.email;
                    String age = userProfile.age;
                    String institute = userProfile.institute;

                    greetingTextView.setText("Welcome " + fullname + '!');
                    fullNameTextView.setText(fullname);
                    emailTextView.setText(email);
                    ageTextView.setText(age);
                    instituteTextView.setText(institute);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Something wrong happened!", Toast.LENGTH_LONG).show();
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