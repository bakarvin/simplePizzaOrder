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
import com.bakarvin.pizzatime.View.Adapter.AdapterPreMadeMenu;
import com.bakarvin.pizzatime.View.Adapter.AdapterRiwayatTransaksi;
import com.bakarvin.pizzatime.databinding.FragmentOnGoingBinding;

import java.util.ArrayList;

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
        return v;
    }

    private void getTransaksi(){
        transaksiList.clear();
//        transaksiList.addAll();
        adapterTransaksi = new AdapterRiwayatTransaksi(transaksiList,context);
        onGoingBinding.rvOnGoing.setAdapter(adapterTransaksi);
        adapterTransaksi.notifyDataSetChanged();
        adapterTransaksi.ActionClick(new AdapterRiwayatTransaksi.onAction() {
            @Override
            public void onActionClick(View view, int position) {
                startActivity(new Intent(context, DetailTransaksiActivity.class));
            }
        });
    }
}