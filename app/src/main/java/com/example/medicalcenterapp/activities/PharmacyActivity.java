package com.example.medicalcenterapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalcenterapp.R;
import com.example.medicalcenterapp.adapters.PharmacyAdapter;
import com.example.medicalcenterapp.models.PharmacyModal;

import java.util.ArrayList;

public class PharmacyActivity extends AppCompatActivity {
    private ArrayList<PharmacyModal> pharmacyModals = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmarcy);
        ActionBar actionBar = this.getSupportActionBar();

       if(actionBar != null) {
           actionBar.setTitle("List of Pharmacies");
           actionBar.setDefaultDisplayHomeAsUpEnabled(true);
       }

        RecyclerView pharmacyRecyclerView = findViewById(R.id.pharmacyRecyclerView);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        pharmacyRecyclerView.setLayoutManager(linearLayoutManager);
        pharmacyRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        pharmacyRecyclerView.setHasFixedSize(true);
        int count =0;
        for(String name: PharmacyModal.PHARMACY_NAMES){
            pharmacyModals.add(new PharmacyModal(name, PharmacyModal.PHARMACY_ADDRESSES[count], PharmacyModal.PHARMACY_CONTACT[count]));
            count++;
        }
        PharmacyAdapter pharmacyAdapter = new PharmacyAdapter(this,pharmacyModals);
        pharmacyRecyclerView.setAdapter(pharmacyAdapter);

    }
}

