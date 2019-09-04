package com.example.medicalcenterapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.medicalcenterapp.R;


public class SplashScreen extends AppCompatActivity {

    private static final int TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        LottieAnimationView medicalAnimationView = findViewById(R.id.medicalAnimation);
        setupAnimation(medicalAnimationView, 0.3f);
        new Handler().postDelayed(() -> {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();

        },TIME_OUT);
    }
    public static void setupAnimation(LottieAnimationView lottieAnimationView, float speed){

        lottieAnimationView.setSpeed(speed);
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
    }

}
