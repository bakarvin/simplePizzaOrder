package com.bakarvin.pizzatime.View.Ui;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.bakarvin.pizzatime.Api.ConfigRetrofit;
import com.bakarvin.pizzatime.Model.Alamat.AlamatResponse;
import com.bakarvin.pizzatime.Model.Alamat.ModelAlamat;
import com.bakarvin.pizzatime.Preferences;
import com.bakarvin.pizzatime.View.Adapter.AdapterAlamat;
import com.bakarvin.pizzatime.databinding.ActivityListAlamatBinding;

import java.util.ArrayList;

public class ListAlamatActivity extends AppCompatActivity {

    ActivityListAlamatBinding listAlamatBinding;
    Context context;
    ArrayList<ModelAlamat> alamatList = new ArrayList<>();
    AdapterAlamat adapterAlamat;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getAlamatList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listAlamatBinding = ActivityListAlamatBinding.inflate(getLayoutInflater());
        setContentView(listAlamatBinding.getRoot());
        context = ListAlamatActivity.this;
        listAlamatBinding.imgAddLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, UbahTambahAlamatActivity.class));
            }
        });
        listAlamatBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        listAlamatBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerSelected();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void spinnerSelected() {
        String spin = listAlamatBinding.spinner.getSelectedItem().toString();
        switch (spin){
            case "All":
                getAlamatList();
                break;
            case "Apartemen":
                getFilterAlamatList("Apartemen");
                break;
            case "Kantor":
                getFilterAlamatList("Kantor");
                break;
            case "Kos":
                getFilterAlamatList("Kos");
                break;
            case "Rumah":
                getFilterAlamatList("Rumah");
                break;
        }
    }

    private void getFilterAlamatList(String tipe_alamat_user) {
        alamatList.clear();
        String username = Preferences.getLoginUname(context);
        ConfigRetrofit.service.filterAlamatUser(username,tipe_alamat_user).enqueue(new Callback<AlamatResponse>() {
            @Override
            public void onResponse(Call<AlamatResponse> call, Response<AlamatResponse> response) {
                alamatList.addAll(response.body().getResult());
                adapterAlamat = new AdapterAlamat(alamatList, context);
                listAlamatBinding.rvAlamat.setAdapter(adapterAlamat);
                adapterAlamat.notifyDataSetChanged();
                adapterAlamat.ActionClick(new AdapterAlamat.onAction() {
                    @Override
                    public void onActionClick(View view, int position) {

                    }
                });
            }

            @Override
            public void onFailure(Call<AlamatResponse> call, Throwable t) {
                Log.d("Server Error", t.getMessage());
            }
        });
    }

    private void getAlamatList() {
        alamatList.clear();
        String username = Preferences.getLoginUname(context);
        ConfigRetrofit.service.readAlamatUser(username).enqueue(new Callback<AlamatResponse>() {
            @Override
            public void onResponse(Call<AlamatResponse> call, Response<AlamatResponse> response) {
                alamatList.addAll(response.body().getResult());
                adapterAlamat = new AdapterAlamat(alamatList, context);
                listAlamatBinding.rvAlamat.setAdapter(adapterAlamat);
                adapterAlamat.notifyDataSetChanged();
                adapterAlamat.ActionClick(new AdapterAlamat.onAction() {
                    @Override
                    public void onActionClick(View view, int position) {

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