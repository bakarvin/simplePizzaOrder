package com.bakarvin.pizzatime.View.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bakarvin.pizzatime.R;
import com.bakarvin.pizzatime.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {

    ActivityWelcomeBinding welcomeBinding;
    Context context;

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        welcomeBinding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(welcomeBinding.getRoot());
        context = WelcomeActivity.this;
    }

    public void onButtonClicked(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                intent(1);
                break;
            case R.id.btnRegist:
                intent(2);
                break;
        }
    }

    public void intent(int kode){
        Intent i = new Intent(context, LoginRegistActivity.class);
        i.putExtra("kode", kode);
        startActivity(i);
    }
}