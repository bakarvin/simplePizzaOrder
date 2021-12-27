package com.bakarvin.pizzatime.View.Ui.Fragment;

import android.content.Context;
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
import com.bakarvin.pizzatime.DetailTransaksiActivity;
import com.bakarvin.pizzatime.Model.Transaksi.ModelTransaksi;
import com.bakarvin.pizzatime.Model.Transaksi.TransaksiResponse;
import com.bakarvin.pizzatime.Preferences;
import com.bakarvin.pizzatime.View.Adapter.AdapterRiwayatTransaksi;
import com.bakarvin.pizzatime.databinding.FragmentHistoryOrderBinding;

import java.util.ArrayList;
import java.util.List;


public class HistoryOrderFragment extends Fragment {

    FragmentHistoryOrderBinding historyBinding;
    ArrayList<ModelTransaksi> transaksiList = new ArrayList<>();
    AdapterRiwayatTransaksi adapterTransaksi;
    Context context;

    public HistoryOrderFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        historyBinding = FragmentHistoryOrderBinding.inflate(inflater,container,false);
        View v = historyBinding.getRoot();
        context = getContext();
        getTransaksi();
        return v;
    }
    private void getTransaksi(){
        transaksiList.clear();
        String uname = Preferences.getLoginUname(context);
        ConfigRetrofit.service.filterTransaksi(uname,"").enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                int kode = response.body().getKode();
                List<ModelTransaksi> result = response.body().getResult();
                if (kode == 1){
                    transaksiList.addAll(response.body().getResult());
                    adapterTransaksi = new AdapterRiwayatTransaksi(transaksiList,context);
                    historyBinding.rvHistory.setAdapter(adapterTransaksi);
                    adapterTransaksi.notifyDataSetChanged();
                    adapterTransaksi.ActionClick(new AdapterRiwayatTransaksi.onAction() {
                        @Override
                        public void onActionClick(View view, int position) {
                            Intent i = new Intent(context, DetailTransaksiActivity.class);
                            i.putExtra("id_trans",result.get(position).getId_trans());
                            i.putExtra("tgl_trans",result.get(position).getTgl_trans());
                            i.putExtra("total_trans",result.get(position).getTotal_trans());
                            i.putExtra("alamat_user",result.get(position).getAlamat_user());
                            Toast.makeText(context, String.valueOf(result.get(position).getId_trans()), Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }
                    });
                } else {
                    Log.d("Server Error", response.body().getPesan());
                }
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Log.d("Server Error", t.getMessage());
            }
        });
//




    }
}