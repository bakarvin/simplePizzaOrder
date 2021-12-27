package com.bakarvin.pizzatime.View.Ui;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.bakarvin.pizzatime.Api.ConfigRetrofit;
import com.bakarvin.pizzatime.Model.Alamat.AlamatResponse;
import com.bakarvin.pizzatime.Preferences;
import com.bakarvin.pizzatime.R;
import com.bakarvin.pizzatime.databinding.ActivityUbahTambahAlamatBinding;

public class UbahTambahAlamatActivity extends AppCompatActivity {

    ActivityUbahTambahAlamatBinding alamatBinding;
    Context context;
    String tipe_alamat;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alamatBinding = ActivityUbahTambahAlamatBinding.inflate(getLayoutInflater());
        setContentView(alamatBinding.getRoot());
        context = UbahTambahAlamatActivity.this;
        alamatBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkTextKosong();
            }
        });
        alamatBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public void onRadioClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.rbApartemen:
                if (checked) tipe_alamat = alamatBinding.rbApartemen.getText().toString();
                break;
            case R.id.rbKos:
                if (checked) tipe_alamat = alamatBinding.rbKos.getText().toString();
                break;
            case R.id.rbKantor:
                if (checked) tipe_alamat = alamatBinding.rbKantor.getText().toString();
                break;
            case R.id.rbRumah:
                if (checked) tipe_alamat = alamatBinding.rbRumah.getText().toString();
                break;
        }
    }

    private void checkTextKosong() {
        final String username = Preferences.getLoginUname(context);
        final String nama = alamatBinding.txtNama.getText().toString();
        final String telp = alamatBinding.txtTelp.getText().toString();
        final String tipe = tipe_alamat;
        final String kota = alamatBinding.txtKota.getText().toString();
        final String kodepos = alamatBinding.txtKodepos.getText().toString();
        final String alamat = alamatBinding.txtAlamat.getText().toString();
        final String alamat_singkat = tipe+" - "+alamat+", "+kodepos;
        final String alamat_lengkap = "Penerima: "+nama+","+
                                      " Telp: "+telp+","+
                                      " Alamat: "+tipe+", "+kota+", "+kodepos+", "+alamat;
        if (username.isEmpty() || nama.isEmpty() || telp.isEmpty() || tipe.isEmpty() || kota.isEmpty() || kodepos.isEmpty() || alamat.isEmpty()){

        } else {
            ConfigRetrofit.service.insertAlamat(username,nama,telp, tipe,kota,kodepos,alamat).enqueue(new Callback<AlamatResponse>() {
                @Override
                public void onResponse(Call<AlamatResponse> call, Response<AlamatResponse> response) {
                    int kode = response.body().getKode();
                    if (kode==1){
                        finish();
                        Preferences.setAlamatUserLengkap(context, alamat_lengkap);
                        Preferences.setAlamatUserSingkat(context, alamat_singkat);
                    }
                }

                @Override
                public void onFailure(Call<AlamatResponse> call, Throwable t) {
                    Log.d("Server Error", t.getMessage());
                }
            });
        }
    }
}