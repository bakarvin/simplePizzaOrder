package com.bakarvin.pizzatime;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.pizzatime.Api.ConfigRetrofit;
import com.bakarvin.pizzatime.Model.CustomTransaksi.CustomTransaksiResponse;
import com.bakarvin.pizzatime.Model.CustomTransaksi.ModelCustomTransaksi;
import com.bakarvin.pizzatime.Model.DetailTransaksi.DetailTransaksiResponse;
import com.bakarvin.pizzatime.Model.DetailTransaksi.ModelDetailTransaksi;
import com.bakarvin.pizzatime.View.Adapter.AdapterDetailCustom;
import com.bakarvin.pizzatime.View.Adapter.AdapterDetailTransaksi;
import com.bakarvin.pizzatime.databinding.ActivityDetailTransaksiBinding;

import java.util.ArrayList;

public class DetailTransaksiActivity extends AppCompatActivity {

    ActivityDetailTransaksiBinding detailTransaksiBinding;
    ArrayList<ModelDetailTransaksi> detailList = new ArrayList<>();
    ArrayList<ModelCustomTransaksi> customList = new ArrayList<>();
    AdapterDetailTransaksi adapterDetailTransaksi;
    AdapterDetailCustom adapterDetailCustom;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailTransaksiBinding = ActivityDetailTransaksiBinding.inflate(getLayoutInflater());
        setContentView(detailTransaksiBinding.getRoot());
        context = DetailTransaksiActivity.this;
        getSetIntentData();
    }

    private void getSetIntentData() {
        String id_trans = getIntent().getStringExtra("id_trans");
        String tgl_trans = getIntent().getStringExtra("tgl_trans");
        String alamat_user = getIntent().getStringExtra("alamat_user");
        String total_trans = getIntent().getStringExtra("total_trans");
        detailTransaksiBinding.txtTitle.setText(tgl_trans);
        detailTransaksiBinding.txtId.setText(id_trans);
        detailTransaksiBinding.txtAlamat.setText(alamat_user);
        detailTransaksiBinding.txtSubTotal.setText(total_trans);
        detailTransaksiBinding.txtFee.setText("0");
        detailTransaksiBinding.txtDiskon.setText("0");
        detailTransaksiBinding.txtTotalPrice.setText(total_trans);
        String tipe = id_trans.substring(0,7);
        if (tipe.equals("premade")){
            getDetailPremadeOrder(id_trans);
        } else {
            getDetailCustomOrder(id_trans);
        }
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
                    detailTransaksiBinding.rvDetailTransaksi.setAdapter(adapterDetailCustom);
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
                    detailTransaksiBinding.rvDetailTransaksi.setAdapter(adapterDetailTransaksi);
                    adapterDetailTransaksi.notifyDataSetChanged();
                    adapterDetailTransaksi.ActionClick(new AdapterDetailTransaksi.onAction() {
                        @Override
                        public void onActionClick(View view, int position) {
                            Toast.makeText(context, position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    Toast.makeText(context, String.valueOf(kode), Toast.LENGTH_SHORT).show();
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
}