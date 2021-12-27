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

import com.bakarvin.pizzatime.Api.ConfigRetrofit;
import com.bakarvin.pizzatime.Model.Alamat.AlamatResponse;
import com.bakarvin.pizzatime.Model.Alamat.ModelAlamat;
import com.bakarvin.pizzatime.View.Adapter.AdapterAlamat;
import com.bakarvin.pizzatime.View.Adapter.AdapterListAlamat;
import com.bakarvin.pizzatime.View.Ui.UbahTambahAlamatActivity;
import com.bakarvin.pizzatime.databinding.ActivityListAlamatDialogBinding;

import java.util.ArrayList;
import java.util.List;

public class ListAlamatDialogActivity extends AppCompatActivity {

    ActivityListAlamatDialogBinding alamatDialogBinding;
    ArrayList<ModelAlamat> alamatList = new ArrayList<>();
    AdapterListAlamat adapterAlamat;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alamatDialogBinding = ActivityListAlamatDialogBinding.inflate(getLayoutInflater());
        setContentView(alamatDialogBinding.getRoot());
        context = ListAlamatDialogActivity.this;
        alamatDialogBinding.linAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, UbahTambahAlamatActivity.class));
            }
        });
        getAlamatUser();
    }

    private void getAlamatUser() {
        alamatList.clear();
        String username = Preferences.getLoginUname(context);
        ConfigRetrofit.service.readAlamatUser(username).enqueue(new Callback<AlamatResponse>() {
            @Override
            public void onResponse(Call<AlamatResponse> call, Response<AlamatResponse> response) {
                alamatList.addAll(response.body().getResult());
                adapterAlamat = new AdapterListAlamat(alamatList,context);
                alamatDialogBinding.rvAlamat.setAdapter(adapterAlamat);
                adapterAlamat.ActionClick(new AdapterListAlamat.onAction() {
                    @Override
                    public void ActionClick(View view, int position) {
                        String alamat_lengkap = "Penerima: "+alamatList.get(position).getNama_user()+","
                                                +" Telp: "+ alamatList.get(position).getTelp_user()+","
                                                +" Alamat: "+ alamatList.get(position).getTipe_alamat_user()+", "
                                                            + alamatList.get(position).getKota_user()+", "
                                                            + alamatList.get(position).getKode_pos_user()+", "
                                                            + alamatList.get(position).getAlamat_user();
                        String alamat_singkat = alamatList.get(position).getTipe_alamat_user()+" - "
                                            + alamatList.get(position).getAlamat_user()+", "
                                            + alamatList.get(position).getKode_pos_user();
                        Preferences.setAlamatUserLengkap(context, alamat_lengkap);
                        Preferences.setAlamatUserSingkat(context, alamat_singkat);
                        finish();
                    }
                });
            }

            @Override
            public void onFailure(Call<AlamatResponse> call, Throwable t) {
                Log.d("Server Error", t.getMessage());
            }
        });
    }
}