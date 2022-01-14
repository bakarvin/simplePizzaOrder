package com.bakarvin.pizzatime.View.Ui.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import com.bakarvin.pizzatime.Api.ConfigRetrofit;
import com.bakarvin.pizzatime.Model.User.UserResponse;
import com.bakarvin.pizzatime.Preferences;
import com.bakarvin.pizzatime.TambahAlamatRegistActivity;
import com.bakarvin.pizzatime.databinding.FragmentRegisterBinding;

import org.jetbrains.annotations.NotNull;

public class RegisterFragment extends Fragment {

    FragmentRegisterBinding registerBinding;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        registerBinding = FragmentRegisterBinding.inflate(inflater, container, false);
        View v = registerBinding.getRoot();
        registerBinding.btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkKolom();
            }
        });
        registerBinding.cbPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    registerBinding.edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    registerBinding.edtKonfirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    registerBinding.edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    registerBinding.edtKonfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        return v;
    }

    private void checkKolom() {
        String username = registerBinding.edtUsername.getText().toString();
        String nama_user = registerBinding.edtNama.getText().toString();
        String telp_user = registerBinding.edtTelp.getText().toString();
        String pass_user = registerBinding.edtPass.getText().toString();
        String konfirm = registerBinding.edtKonfirm.getText().toString();
        if (username.equals("") || nama_user.equals("") || telp_user.equals("") || pass_user.equals("") || konfirm.equals("")){
            registerBinding.edtUsername.setError("Mohon Isi Semua Kolom");
            registerBinding.edtNama.setError("Mohon Isi Semua Kolom");
            registerBinding.edtTelp.setError("Mohon Isi Semua Kolom");
            registerBinding.edtPass.setError("Mohon Isi Semua Kolom");
            registerBinding.edtKonfirm.setError("Mohon Isi Semua Kolom");
        } else {
            checkPass(pass_user, konfirm);
        }
    }

    private void checkPass(String pass_user, String konfirm) {
        if (!pass_user.equals(konfirm)){
            registerBinding.edtPass.setError("Password dan Konfirmasi tidak cocok");
            registerBinding.edtKonfirm.setError("Password dan Konfirmasi tidak cocok");
        } else {
            checkUser();
        }
    }

    private void checkUser() {
        String username = registerBinding.edtUsername.getText().toString();
        ConfigRetrofit.service.checkUser(username).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NotNull Call<UserResponse> call, @NotNull Response<UserResponse> response) {
                assert response.body() != null;
                int kode = response.body().getKode();
                if (kode == 2){
                    registUser();
                } else {
                    registerBinding.edtUsername.setError("Username telah terdaftar");
                }
            }
            @Override
            public void onFailure(@NotNull Call<UserResponse> call, @NotNull Throwable t) {
                Log.d("Server Error", t.getMessage());
            }
        });
    }

    private void registUser() {
        final String username = registerBinding.edtUsername.getText().toString();
        final String nama_user = registerBinding.edtNama.getText().toString();
        final String telp_user = registerBinding.edtTelp.getText().toString();
        final String pass_user = registerBinding.edtPass.getText().toString();
        ConfigRetrofit.service.insertUser(username, nama_user, telp_user, pass_user).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NotNull Call<UserResponse> call, @NotNull Response<UserResponse> response) {
                assert response.body() != null;
                int kode = response.body().getKode();
                if (kode == 1){
                    setSharedPref(username);
                    startActivity(new Intent(getContext(), TambahAlamatRegistActivity.class));
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserResponse> call, @NotNull Throwable t) {
                Log.d("Server Error", t.getMessage());
            }
        });
    }

    private void setSharedPref(String username) {
        Preferences.setLoginStatus(getContext(), true);
        Preferences.setLoginUname(getContext(), username);
    }
}