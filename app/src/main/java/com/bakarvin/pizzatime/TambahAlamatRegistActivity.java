package com.bakarvin.pizzatime;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.bakarvin.pizzatime.Api.ConfigRetrofit;
import com.bakarvin.pizzatime.Model.Alamat.AlamatResponse;
import com.bakarvin.pizzatime.View.Ui.MainActivity;
import com.bakarvin.pizzatime.databinding.ActivityTambahAlamatRegistBinding;

public class TambahAlamatRegistActivity extends AppCompatActivity {

    ActivityTambahAlamatRegistBinding tambahAlamatRegistBinding;
    Context context;
    String tipe_alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tambahAlamatRegistBinding = ActivityTambahAlamatRegistBinding.inflate(getLayoutInflater());
        setContentView(tambahAlamatRegistBinding.getRoot());
        context = TambahAlamatRegistActivity.this;
        tambahAlamatRegistBinding.btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, MainActivity.class));
            }
        });
        tambahAlamatRegistBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkKolom();
            }
        });
    }

    public void onRadioClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.rbApartemen:
                if (checked) tipe_alamat = tambahAlamatRegistBinding.rbApartemen.getText().toString();
                break;
            case R.id.rbKos:
                if (checked) tipe_alamat = tambahAlamatRegistBinding.rbKos.getText().toString();
                break;
            case R.id.rbKantor:
                if (checked) tipe_alamat = tambahAlamatRegistBinding.rbKantor.getText().toString();
                break;
            case R.id.rbRumah:
                if (checked) tipe_alamat = tambahAlamatRegistBinding.rbRumah.getText().toString();
                break;
        }
    }

    private void checkKolom() {
        final String username = Preferences.getLoginUname(context);
        final String nama = tambahAlamatRegistBinding.txtNama.getText().toString();
        final String telp = tambahAlamatRegistBinding.txtTelp.getText().toString();
        final String tipe = tipe_alamat;
        final String kota = tambahAlamatRegistBinding.txtKota.getText().toString();
        final String kodepos = tambahAlamatRegistBinding.txtKodepos.getText().toString();
        final String alamat = tambahAlamatRegistBinding.txtAlamat.getText().toString();
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
                        startActivity(new Intent(context, MainActivity.class));
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