package com.bakarvin.pizzatime.View.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.bakarvin.pizzatime.Preferences;
import com.bakarvin.pizzatime.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setStatusBarColor(Color.parseColor("#20111111"));
        getWindow().setNavigationBarColor(Color.parseColor("#40000000"));
        splash();
    }

    private void checkPref() {
        if (Preferences.getLoginStatus(getBaseContext())){
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
            finish();
        }

    }

    private void splash() {
        int waktu_loading = 3000;
        new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            checkPref();
        }
    }, waktu_loading);
    }
}