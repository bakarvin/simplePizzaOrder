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
import android.widget.Toast;

import com.bakarvin.pizzatime.Api.ConfigRetrofit;
import com.bakarvin.pizzatime.Model.CustomTransaksi.CustomTransaksiResponse;
import com.bakarvin.pizzatime.Model.CustomTransaksi.ModelCustomTransaksi;
import com.bakarvin.pizzatime.Model.DetailTransaksi.DetailTransaksiResponse;
import com.bakarvin.pizzatime.Model.DetailTransaksi.ModelDetailTransaksi;
import com.bakarvin.pizzatime.Model.Transaksi.ModelTransaksi;
import com.bakarvin.pizzatime.Model.Transaksi.TransaksiResponse;
import com.bakarvin.pizzatime.View.Adapter.AdapterDetailCustom;
import com.bakarvin.pizzatime.View.Adapter.AdapterDetailTransaksi;
import com.bakarvin.pizzatime.View.Ui.MainActivity;
import com.bakarvin.pizzatime.databinding.ActivitySpecialIntentBinding;

import java.util.ArrayList;
import java.util.List;

public class SpecialIntentActivity extends AppCompatActivity {

    ActivitySpecialIntentBinding specialIntentBinding;

    ArrayList<ModelDetailTransaksi> detailList = new ArrayList<>();
    ArrayList<ModelCustomTransaksi> customList = new ArrayList<>();
    AdapterDetailTransaksi adapterDetailTransaksi;
    AdapterDetailCustom adapterDetailCustom;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        specialIntentBinding = ActivitySpecialIntentBinding.inflate(getLayoutInflater());
        setContentView(specialIntentBinding.getRoot());
        context = SpecialIntentActivity.this;
        getSetIntent();
    }

    public void getSetIntent(){
        String id_trans = getIntent().getStringExtra("id_trans");
        specialIntentBinding.txtFee.setText("0");
        specialIntentBinding.txtDiskon.setText("0");
        String tipe = id_trans.substring(0,7);
        getDetailOrder(id_trans);
        if (tipe.equals("premade")){
            getDetailPremadeOrder(id_trans);
        } else {
            getDetailCustomOrder(id_trans);
        }
        specialIntentBinding.cardFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTransaksi(id_trans);
            }
        });
    }

    private void getDetailOrder(String id_trans){
        ConfigRetrofit.service.readTransaksi(id_trans).enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                int kode = response.body().getKode();
                if (kode == 1){
                    List<ModelTransaksi> result = response.body().getResult();
                    specialIntentBinding.txtTglOrder.setText(result.get(0).getTgl_trans());
                    specialIntentBinding.txtAlamat.setText(result.get(0).getAlamat_user());
                    specialIntentBinding.txtSubTotal.setText(result.get(0).getTotal_trans());
                    specialIntentBinding.txtTotalPrice.setText(result.get(0).getTotal_trans());
                } else {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Log.d("Server Error", t.getMessage());
            }
        });
    }

    private void getDetailCustomOrder(String id_trans) {
        customList.clear();
        ConfigRetrofit.service.readCustomTransaksi(id_trans).enqueue(new Callback<CustomTransaksiResponse>() {
            @Override
            public void onResponse(Call<CustomTransaksiResponse> call, Response<CustomTransaksiResponse> response) {
                int kode = response.body().getKode();
                if (kode == 1) {
                    customList.addAll(response.body().getResult());
                    adapterDetailCustom = new AdapterDetailCustom(customList,context);
                    specialIntentBinding.rvDetailTransaksi.setAdapter(adapterDetailCustom);
                    adapterDetailCustom.notifyDataSetChanged();
                    Toast.makeText(context, String.valueOf(kode), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, String.valueOf(kode), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomTransaksiResponse> call, Throwable t) {
                Log.d("Server Error", t.getMessage());
            }
        });
    }

    private void getDetailPremadeOrder(String id_trans) {
        detailList.clear();
        ConfigRetrofit.service.readDetailTransaksi(id_trans).enqueue(new Callback<DetailTransaksiResponse>() {
            @Override
            public void onResponse(Call<DetailTransaksiResponse> call, Response<DetailTransaksiResponse> response) {
                int kode = response.body().getKode();
                if (kode == 1) {
                    detailList.addAll(response.body().getResult());
                    adapterDetailTransaksi = new AdapterDetailTransaksi(detailList, context);
                    specialIntentBinding.rvDetailTransaksi.setAdapter(adapterDetailTransaksi);
                    adapterDetailTransaksi.notifyDataSetChanged();
                    adapterDetailTransaksi.ActionClick(new AdapterDetailTransaksi.onAction() {
                        @Override
                        public void onActionClick(View view, int position) {
                            Toast.makeText(context, position, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(context, String.valueOf(kode), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetailTransaksiResponse> call, Throwable t) {
                Log.d("Server Error", t.getMessage());
            }
        });
    }

    private void updateTransaksi(String id_trans){
        ConfigRetrofit.service.updateTransaksi(id_trans,"Finish").enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                int kode = response.body().getKode();
                if (kode==1){
                    finish();
                    startActivity(new Intent(context, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Log.d("Server Error", t.getMessage());
            }
        });
    }
}