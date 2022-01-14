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
import com.bakarvin.pizzatime.Model.Menu.MenuResponse;
import com.bakarvin.pizzatime.Model.Transaksi.ModelTransaksi;
import com.bakarvin.pizzatime.Model.Transaksi.TransaksiResponse;
import com.bakarvin.pizzatime.Preferences;
import com.bakarvin.pizzatime.View.Adapter.AdapterPreMadeMenu;
import com.bakarvin.pizzatime.View.Adapter.AdapterRiwayatTransaksi;
import com.bakarvin.pizzatime.databinding.FragmentOnGoingBinding;

import java.util.ArrayList;
import java.util.List;

public class OnGoingFragment extends Fragment {

    FragmentOnGoingBinding onGoingBinding;
    ArrayList<ModelTransaksi> transaksiList = new ArrayList<>();
    AdapterRiwayatTransaksi adapterTransaksi;
    Context context;

    public OnGoingFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        onGoingBinding = FragmentOnGoingBinding.inflate(inflater,container,false);
        View v = onGoingBinding.getRoot();
        context = getContext();
        getTransaksi();
        return v;
    }

    private void getTransaksi(){
        transaksiList.clear();
        String uname = Preferences.getLoginUname(context);
        ConfigRetrofit.service.filterTransaksi(uname,"1").enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                int kode = response.body().getKode();
                List<ModelTransaksi> result = response.body().getResult();
                if (kode == 1){
                    transaksiList.addAll(response.body().getResult());
                    adapterTransaksi = new AdapterRiwayatTransaksi(transaksiList,context);
                    onGoingBinding.rvOnGoing.setAdapter(adapterTransaksi);
                    adapterTransaksi.notifyDataSetChanged();
                    adapterTransaksi.ActionClick(new AdapterRiwayatTransaksi.onAction() {
                        @Override
                        public void onActionClick(View view, int position) {
                            DialogDetailTransaksiFragment dialogFragment = new DialogDetailTransaksiFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("id_trans",result.get(position).getId_trans());
                            bundle.putString("tgl_trans",result.get(position).getTgl_trans());
                            bundle.putString("total_trans",result.get(position).getTotal_trans());
                            bundle.putString("alamat_user",result.get(position).getAlamat_user());
                            bundle.putString("status_trans", result.get(position).getStatus_trans());
                            dialogFragment.setArguments(bundle);
                            dialogFragment.show(getFragmentManager(),dialogFragment.getTag());
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
    }
}