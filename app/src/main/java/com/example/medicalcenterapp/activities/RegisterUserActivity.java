package com.example.medicalcenterapp.activities;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;
import com.example.medicalcenterapp.R;
import com.example.medicalcenterapp.models.RegModal;
import com.example.medicalcenterapp.utilities.ApiUrl;
import com.example.medicalcenterapp.utilities.AppSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterUserActivity extends AppCompatActivity {
    private EditText email;
    private EditText fname;
    private EditText nationalID;
    private EditText confirmPass;
    private EditText pass;
    private EditText phone;
    private EditText address;
    RegModal regModal = new RegModal();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        email = findViewById(R.id.email);
        fname = findViewById(R.id.fullname);
        nationalID =findViewById(R.id.nationalD);
        pass = findViewById(R.id.pass);
        confirmPass = findViewById(R.id.confirmPass);
        phone = findViewById(R.id.phone);
        address =findViewById(R.id.address);
        Button sub = findViewById(R.id.submitRegDetails);

        sub.setOnClickListener((v)->{

            validate();
            if(pass.getText().toString().equals(confirmPass.getText().toString())){
                if (pass.getText().toString().length() < 6){
                    pass.setError("Password Length should be greater than 6 characters");
                    confirmPass.setError("Password Length should be greater than 6 characters");

                }
                else{

                    regModal.setName(fname.getText().toString());
                    regModal.setAddress(address.getText().toString());
                    regModal.setEmail(email.getText().toString());
                    regModal.setNatID(nationalID.getText().toString());
                    regModal.setPass(pass.getText().toString());
                    regModal.setPhone(phone.getText().toString());

                    setDetails();
                }

            }
            else {
                pass.setError("Passwords do not match");
                confirmPass.setError("Passwords do not match");
            }

        });

    }

    private void setDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrl.URL_REG, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String code = jsonObject.getString("code");
                String message = jsonObject.getString("message");

                if (code.equals("successful")){
                    Intent intent = new Intent(this, LoginActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                }
                else if(code.equals("failed")){
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {

            if (error instanceof TimeoutError) {
                Toast.makeText(this, "Attempt has timed out. Please try again.",
                        Toast.LENGTH_LONG).show();

            } else if (error instanceof NetworkError) {
                Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show();

            } else if (error instanceof ServerError) {
                Toast.makeText(this, "Server is down", Toast.LENGTH_LONG).show();

            }
            error.printStackTrace();

        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("fname", regModal.getName());
                params.put("natID", regModal.getNatID());
                params.put("address", regModal.getAddress());
                params.put("email", regModal.getEmail());
                params.put("phone", regModal.getPhone());
                params.put("password", regModal.getPass());
                return params;
            }
        };

        AppSingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    public void validate(){
        String strEmail = email.getText().toString().trim();
        String strFname = fname.getText().toString().trim();
        String strNationalID = nationalID.getText().toString().trim();
        String strConfirmPass = confirmPass.getText().toString().trim();
        String strPass = pass.getText().toString().trim();
        String strPhone = phone.getText().toString().trim();
        String strAddress = address.getText().toString().trim();

        if(TextUtils.isEmpty(strFname)) {
            fname.setError("Enter your Name");
        }
        else if(TextUtils.isEmpty(strNationalID)){
            nationalID.setError("Enter a valid National ID");

        }else if(strNationalID.length() > 11){
           nationalID.setError("Enter a valid National ID");
        }else if(TextUtils.isEmpty(strAddress)){
            address.setError("Enter your Address");
        }else if (TextUtils.isEmpty(strEmail)){
            email.setError("Enter your email address");

        }else if(!strEmail.contains("@")){
            email.setError("Enter a valid email address");
        }else if(TextUtils.isEmpty(strPhone)){
            phone.setError("Enter your Phone Number");
        }else if (TextUtils.isEmpty(strPass)){
            pass.setError("Enter your Password");
        } else if (TextUtils.isEmpty(strConfirmPass)){
            confirmPass.setError("Enter Password");
        }


    }
}
