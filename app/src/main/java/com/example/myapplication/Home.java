package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UserData");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void open_Profile(View view) {
        Intent intent = new Intent(getApplicationContext(),Profile.class);
        startActivity(intent);
    }

    public void logout_function(View view) {
        databaseReference.child("login_status").removeValue();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class );
        startActivity(intent);
        finish();
    }
}