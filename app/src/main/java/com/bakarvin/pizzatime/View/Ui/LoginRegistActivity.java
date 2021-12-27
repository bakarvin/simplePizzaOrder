package com.bakarvin.pizzatime.View.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.bakarvin.pizzatime.R;
import com.bakarvin.pizzatime.View.Ui.Fragment.LoginFragment;
import com.bakarvin.pizzatime.View.Ui.Fragment.RegisterFragment;
import com.bakarvin.pizzatime.databinding.ActivityLoginRegistBinding;

public class LoginRegistActivity extends AppCompatActivity {

    ActivityLoginRegistBinding loginRegistBinding;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginRegistBinding = ActivityLoginRegistBinding.inflate(getLayoutInflater());
        setContentView(loginRegistBinding.getRoot());
        Intent i = getIntent();
        int kode = i.getIntExtra("kode",1);
        if (kode == 1){
            fragLogin();
        } else {
            fragSignUp();
        }
        loginRegistBinding.txtFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginRegistBinding.txtFragment.getText().equals("Sign Up")){
                    fragSignUp();
                } else {
                    fragLogin();
                }
            }
        });
        loginRegistBinding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void fragLogin(){
        loadFrag(new LoginFragment());
        loginRegistBinding.txtFragment.setText("Sign Up");
        loginRegistBinding.txtTitle.setText("Sign In");
        loginRegistBinding.txtSubTitle.setText(R.string.login_text);
    }
    private void fragSignUp(){
        loadFrag(new RegisterFragment());
        loginRegistBinding.txtFragment.setText("Sign In");
        loginRegistBinding.txtTitle.setText("Sign Up");
        loginRegistBinding.txtSubTitle.setText(R.string.signup_text);
    }
    private boolean loadFrag(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragLay, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }
}