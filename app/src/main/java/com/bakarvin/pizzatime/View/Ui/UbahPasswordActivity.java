package com.bakarvin.pizzatime.View.Ui;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.bakarvin.pizzatime.Api.ConfigRetrofit;
import com.bakarvin.pizzatime.Model.User.ModelUser;
import com.bakarvin.pizzatime.Model.User.UserResponse;
import com.bakarvin.pizzatime.Preferences;
import com.bakarvin.pizzatime.databinding.ActivityUbahPasswordBinding;

import java.util.List;

public class UbahPasswordActivity extends AppCompatActivity {

    ActivityUbahPasswordBinding ubahPasswordBinding;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ubahPasswordBinding = ActivityUbahPasswordBinding.inflate(getLayoutInflater());
        setContentView(ubahPasswordBinding.getRoot());
        context = UbahPasswordActivity.this;
        ubahPasswordBinding.btnUbahPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValid();
            }
        });
        ubahPasswordBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ubahPasswordBinding.cbPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    ubahPasswordBinding.txtPassLama.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ubahPasswordBinding.txtPassBaru.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ubahPasswordBinding.txtKonfirmPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    ubahPasswordBinding.txtPassLama.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ubahPasswordBinding.txtPassBaru.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ubahPasswordBinding.txtKonfirmPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    private void checkValid() {
        final String lama = ubahPasswordBinding.txtPassLama.getText().toString();
        final String baru = ubahPasswordBinding.txtPassBaru.getText().toString();
        final String konfirm = ubahPasswordBinding.txtKonfirmPass.getText().toString();
        final String username = Preferences.getLoginUname(context);
        if (!lama.isEmpty() && !baru.isEmpty() && !konfirm.isEmpty()){
            if (baru.equals(konfirm)){
                if (!baru.equals(lama)){
                    ConfigRetrofit.service.loginUser(username, lama).enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            int kode = response.body().getKode();
                            if (kode==1) {
                                List<ModelUser> result = response.body().getResult();
                                if (username.equals(result.get(0).getUsername()) && lama.equals(result.get(0).getPassword_user())) {
                                    Toast.makeText(context, "Cocok", Toast.LENGTH_SHORT).show();
                                    changePassword(baru, username);
                                } else {
                                    Toast.makeText(context, "Password lama tidak sesuai", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            Log.d("Server Error", t.getMessage());
                        }
                    });
                } else {
                    Toast.makeText(context, "Password Baru tidak boleh sama dengan Password Lama", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Konfirmasi Password Baru Gagal", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Mohon isi semua kolom", Toast.LENGTH_SHORT).show();
        }
    }

    private void changePassword(String baru, String username) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Menambahkan Pesanan");
        progressDialog.show();
        ConfigRetrofit.service.changePassword(username,baru).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                int kode = response.body().getKode();
                if (kode==1){
                    finish();
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(context, "Gagal Mengubah Password", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("Server Error", t.getMessage());
            }
        });
    }
}