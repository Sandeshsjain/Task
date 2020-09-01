package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText email ;
    EditText password ;
    Button login ;
    DatabaseReference firebase = FirebaseDatabase.getInstance().getReference("UserData");

    public static String email_initial = "eve.holt@reqres.in";
    public static String password_initial = "cityslicka";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email= findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_btn);
        firebase.child("email").setValue(email_initial);
        firebase.child("password").setValue(password_initial);

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("login_status").exists()){
                    Intent intent = new Intent(getApplicationContext(),Home.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    public void login_activity(View view) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String temp_email = email.getText().toString().trim();
        String temp_pass = password.getText().toString().trim();
        if(temp_email.isEmpty()){
            email.setError("Please input email first");
            return;
        }
        if(!temp_email.matches(emailPattern)){
            email.setError("please enter valid email");
            return;
        }
        if(temp_pass.isEmpty()){
            password.setError("please enter password first");
            return;
        }
        if((email_initial.matches(email_initial)) && (temp_pass.matches(password_initial))){
            firebase.child("login_status").setValue(true);
            Intent intent = new Intent(getApplicationContext(),Home.class);
            startActivity(intent);
            finish();
        }
        else if ((temp_email.matches(email_initial)) && (!temp_pass.matches(password_initial))){
            password.setError("invalid password");
            return;
        }
        else if((!temp_email.matches(email_initial)) && (temp_pass.matches(password_initial))){
            email.setError("invalid email");
        }
        else if((!temp_email.matches(email_initial)) && (!temp_pass.matches(password_initial))){
            email.setError("invalid email");
        }


    }
}