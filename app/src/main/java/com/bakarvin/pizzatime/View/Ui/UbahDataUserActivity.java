package com.bakarvin.pizzatime.View.Ui;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.net.wifi.hotspot2.ConfigParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.pizzatime.Api.ConfigRetrofit;
import com.bakarvin.pizzatime.Model.User.ModelUser;
import com.bakarvin.pizzatime.Model.User.UserResponse;
import com.bakarvin.pizzatime.Preferences;
import com.bakarvin.pizzatime.R;
import com.bakarvin.pizzatime.databinding.ActivityUbahDataUserBinding;

import java.util.List;

public class UbahDataUserActivity extends AppCompatActivity {

    ActivityUbahDataUserBinding ubahDataUserBinding;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ubahDataUserBinding = ActivityUbahDataUserBinding.inflate(getLayoutInflater());
        setContentView(ubahDataUserBinding.getRoot());
        context = UbahDataUserActivity.this;
        getDataUser();
        ubahDataUserBinding.btnUbahProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValid();
            }
        });
        ubahDataUserBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getDataUser() {
        final String username = Preferences.getLoginUname(context);
        ConfigRetrofit.service.checkUser(username).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                int kode = response.body().getKode();
                if (kode == 1) {
                    List<ModelUser> result = response.body().getResult();
                    ubahDataUserBinding.txtNamaUser.setText(result.get(0).getNama_user());
                    ubahDataUserBinding.txtTeleponUser.setText(result.get(0).getTelp_user());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("Server Error", t.getMessage());
            }
        });
    }

    private void checkValid() {
        final String nama = ubahDataUserBinding.txtNamaUser.getText().toString();
        final String telp = ubahDataUserBinding.txtTeleponUser.getText().toString();
        final String username = Preferences.getLoginUname(context);
        if (nama.isEmpty() && telp.isEmpty()){
            Toast.makeText(context, "Mohon isi semua kolom", Toast.LENGTH_SHORT).show();
        } else {
            ConfigRetrofit.service.updateUser(username, nama, telp).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    int kode = response.body().getKode();
                    if (kode==1){
                        finish();
                    }
                }
                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.d("Server Error", t.getMessage());
                }
            });
        }
    }
}