package com.bakarvin.pizzatime.View.Ui.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bakarvin.pizzatime.Api.ConfigRetrofit;
import com.bakarvin.pizzatime.Model.User.UserResponse;
import com.bakarvin.pizzatime.Preferences;
import com.bakarvin.pizzatime.View.Ui.MainActivity;
import com.bakarvin.pizzatime.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    FragmentLoginBinding loginBinding;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        View v = loginBinding.getRoot();
        loginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckUser();
            }
        });
        return v;
    }

    private void CheckUser() {
        final String username = loginBinding.edtUsername.getText().toString();
        final String pass = loginBinding.edtPass.getText().toString();
        if (username.equals("") || pass.equals("")){
            loginBinding.edtUsername.setError("Mohon Isi Semua Kolom");
            loginBinding.edtPass.setError("Mohon Isi Semua Kolom");
        } else {
            ConfigRetrofit.service.loginUser(username, pass).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    int kode = response.body().getKode();
                    if (kode == 1){
                        setSharedPref(username);
                        startActivity(new Intent(getContext(), MainActivity.class));
                    } else {
                        Toast.makeText(getContext(), "Username dan Password tidak cocok", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.d("Server Error", t.getMessage());
                }
            });
        }
    }

    private void setSharedPref(String username) {
        Preferences.setLoginStatus(getContext(), true);
        Preferences.setLoginUname(getContext(), username);
    }
}