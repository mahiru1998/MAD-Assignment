package com.example.e_study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class splash extends AppCompatActivity {
    ImageView wel,splash;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        wel = findViewById(R.id.app_name);
        splash = findViewById(R.id.img);
        lottieAnimationView = findViewById(R.id.lottie);

        splash.animate().translationY(-2500).setDuration(1000).setStartDelay(5000);
        wel.animate().translationY(2000).setDuration(1000).setStartDelay(5000);
        lottieAnimationView.animate().translationY(1500).setDuration(1000).setStartDelay(5000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(splash.this,MainActivity.class));

            }
        },6000);

    }
}