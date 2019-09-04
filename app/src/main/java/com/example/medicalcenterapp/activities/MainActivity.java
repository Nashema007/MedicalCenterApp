package com.example.medicalcenterapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.medicalcenterapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Medical Center");
        }

        CardView chat = findViewById(R.id.chatCardView);
        CardView symptoms = findViewById(R.id.symptomsCardView);
        CardView pharmacy = findViewById(R.id.pharmacyCardView);


        chat.setOnClickListener((v)->{
            Intent chatIntent = new Intent(this, ChatLoginActivity.class);
            startActivity(chatIntent);
        });
        symptoms.setOnClickListener((v)->{
            Intent symptomIntent = new Intent(this, SymptomsCheckActivity.class);
            startActivity(symptomIntent);
        });
        pharmacy.setOnClickListener((v)->{
            Intent pharmmacyIntent = new Intent(this, PharmacyActivity.class);
            startActivity(pharmmacyIntent);
        });


    }
}
