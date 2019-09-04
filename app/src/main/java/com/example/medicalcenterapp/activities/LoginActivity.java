package com.example.medicalcenterapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;
import com.example.medicalcenterapp.R;
import com.example.medicalcenterapp.models.LoginAuth;
import com.example.medicalcenterapp.utilities.ApiUrl;
import com.example.medicalcenterapp.utilities.AppSingleton;
import com.example.medicalcenterapp.utilities.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText username;
    private EditText password;
    private SharedPref sharedPref = new SharedPref(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        TextView newUser = findViewById(R.id.newUser);
        LottieAnimationView medicalAnimationView = findViewById(R.id.medical);
        SplashScreen.setupAnimation(medicalAnimationView, 0.3f);

        newUser.setOnClickListener((v)->{

            Intent newUserIntent = new Intent(this, RegisterUserActivity.class);
            startActivity(newUserIntent);

        });





        loginBtn.setOnClickListener(v -> {

            final LoginAuth myloginAuth = new LoginAuth();
            String enteredregnum = username.getText().toString().trim();
            String enteredpassword = password.getText().toString().trim();

            if (TextUtils.isEmpty(enteredregnum) & TextUtils.isEmpty(enteredpassword)) {

                username.setError("Enter your Username");
                password.setError("Enter your password");
            }
            else if (!(enteredregnum.contains("@"))){
                username.setError("Enter a valid email address");
            }

            if (!(TextUtils.isEmpty(enteredregnum) & TextUtils.isEmpty(enteredpassword))) {

                //check details in DB
                myloginAuth.setRegistrationNumbner(enteredregnum);
                myloginAuth.setStudentpass(enteredpassword);

                StringRequest myStringRequest = new StringRequest(Request.Method.POST, ApiUrl.URL_LOGIN, response -> {

                    try {

                        // gets response from php file for success or failure
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");

                        switch (code) {
                            case "successful":

                                // if login is successful
                                // username and password are stored in shared preferences
                                sharedPref.saveString("username", myloginAuth.getRegistrationNumbner());
                                sharedPref.saveString("password", myloginAuth.getStudentpass());


                                Intent intent = new Intent(this, MainActivity.class)
                                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                                break;
                            case "failed":

                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();

                                break;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }, error -> {

                    if (error instanceof TimeoutError) {
                        Toast.makeText(LoginActivity.this, "Login attempt has timed out. Please try again.",
                                Toast.LENGTH_LONG).show();

                    } else if (error instanceof NetworkError) {
                        Toast.makeText(LoginActivity.this, "Network Error", Toast.LENGTH_LONG).show();

                    } else if (error instanceof ServerError) {
                        Toast.makeText(LoginActivity.this, "Server is down", Toast.LENGTH_LONG).show();

                    }
                    error.printStackTrace();

                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        // stores the login details using key pair system
                        Map<String, String> params = new HashMap<>();
                        params.put("Username", myloginAuth.getRegistrationNumbner());
                        params.put("Password", myloginAuth.getStudentpass());

                        return params;

                    }
                };

                AppSingleton.getInstance(LoginActivity.this).addToRequestQueue(myStringRequest);

            }

        });


    }




}
