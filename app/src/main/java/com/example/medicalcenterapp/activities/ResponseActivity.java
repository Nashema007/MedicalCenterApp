package com.example.medicalcenterapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalcenterapp.R;
import com.example.medicalcenterapp.models.ResponsesModal;
import com.example.medicalcenterapp.models.TreatmentModel;

import java.util.ArrayList;

public class ResponseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        ActionBar actionBar = this.getSupportActionBar();

        if(actionBar != null) {
            actionBar.setTitle("Diagnosis");
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        }

        TextView treatment = findViewById(R.id.treatment);
        TextView riskFactor = findViewById(R.id.riskFactor);
        TextView diagnosis = findViewById(R.id.results);
        TextView medication = findViewById(R.id.medication);
        Button buttonSubmit = findViewById(R.id.respBtn);

        ResponsesModal responsesModal = new ResponsesModal();

        ArrayList<String> symptomsList = getIntent().getStringArrayListExtra("symptoms");
        String response = responsesModal.compareSymptoms(symptomsList);



        switch(response){

            case "Typhoid":
                diagnosis.setText(String.format("We have determined you might have the following: %s fever", response));
                treatment.setText(TreatmentModel.TYPHOID_TREATMENT);
                riskFactor.setText(TreatmentModel.TYPHOID_RISK_FACTORS);
                medication.setText(TreatmentModel.TYPHOID_MEDICATION);

                break;
            case "Cancer":
                diagnosis.setText(String.format("We have determined you might have the following: %s", response));
                treatment.setText(TreatmentModel.CANCER_TREATMENT);
                riskFactor.setText(TreatmentModel.CANCER_RISK_FACTORS);
                medication.setText(TreatmentModel.CANCER_MEDICATION);

                break;
            case "Diabetes":
                diagnosis.setText(String.format("We have determined you might have the following: %s", response));
                treatment.setText(TreatmentModel.DIABETES_TREATMENT);
                riskFactor.setText(TreatmentModel.DIABETES_RISK_FACTORS);
                medication.setText(TreatmentModel.DIABETES_MEDICATION);

                break;
            case "Epilepsy":
                diagnosis.setText(String.format("We have determined you might have the following: %s", response));
                treatment.setText(TreatmentModel.EPILEPSY_TREATMENT);
                riskFactor.setText(TreatmentModel.EPILEPSY_RISK_FACTORS);
                medication.setText(TreatmentModel.EPILEPSY_MEDICATION);

                break;
            case "Kidney":
                diagnosis.setText(String.format("We have determined you might have the following: %s disease", response));
                treatment.setText(TreatmentModel.KIDNEY_TREATMENT);
                riskFactor.setText(TreatmentModel.KIDNEY_RISK_FACTORS);
                medication.setText(TreatmentModel.KIDNEY_MEDICATION);

                break;
            case "Obesity":
                diagnosis.setText(String.format("We have determined you might have the following:  %s", response));
                treatment.setText(TreatmentModel.OBESITY_TREATMENT);
                riskFactor.setText(TreatmentModel.OBESITY_RISK_FACTORS);
                medication.setText(TreatmentModel.OBESITY_MEDICATION);

                break;
            case "HIV/AIDS":
                diagnosis.setText(String.format("We have determined you might have the following:  %s", response));
                treatment.setText(TreatmentModel.HIV_TREATMENT);
                riskFactor.setText(TreatmentModel.HIV_RISK_FACTORS);
                medication.setText(TreatmentModel.HIV_MEDICATION);

                break;

        }

        buttonSubmit.setOnClickListener((v)->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });


    }


}
