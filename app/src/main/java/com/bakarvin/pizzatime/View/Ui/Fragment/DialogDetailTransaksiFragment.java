package com.bakarvin.pizzatime.View.Ui.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bakarvin.pizzatime.Api.ConfigRetrofit;
import com.bakarvin.pizzatime.Model.CustomTransaksi.CustomTransaksiResponse;
import com.bakarvin.pizzatime.Model.CustomTransaksi.ModelCustomTransaksi;
import com.bakarvin.pizzatime.Model.DetailTransaksi.DetailTransaksiResponse;
import com.bakarvin.pizzatime.Model.DetailTransaksi.ModelDetailTransaksi;
import com.bakarvin.pizzatime.Model.Transaksi.TransaksiResponse;
import com.bakarvin.pizzatime.R;
import com.bakarvin.pizzatime.View.Adapter.AdapterDetailCustom;
import com.bakarvin.pizzatime.View.Adapter.AdapterDetailTransaksi;
import com.bakarvin.pizzatime.databinding.FragmentDialogDetailTransaksiBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogDetailTransaksiFragment extends BottomSheetDialogFragment {
    FragmentDialogDetailTransaksiBinding dialogDetailTransaksiBinding;

    ArrayList<ModelDetailTransaksi> detailList = new ArrayList<>();
    ArrayList<ModelCustomTransaksi> customList = new ArrayList<>();
    AdapterDetailTransaksi adapterDetailTransaksi;
    AdapterDetailCustom adapterDetailCustom;
    Context context;

    public DialogDetailTransaksiFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogDetailTransaksiBinding = FragmentDialogDetailTransaksiBinding.inflate(inflater,container,false);
        View v = dialogDetailTransaksiBinding.getRoot();
        context = getContext();
        dialogDetailTransaksiBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        getSetBundle();
        return v;
    }

    public void getSetBundle(){
        String id_trans = getArguments().getString("id_trans");
        String tgl_trans = getArguments().getString("tgl_trans");
        String alamat_user = getArguments().getString("alamat_user");
        String total_trans = getArguments().getString("total_trans");
        String status_trans = getArguments().getString("status_trans");
        dialogDetailTransaksiBinding.txtTglOrder.setText(tgl_trans);
        dialogDetailTransaksiBinding.txtId.setText(id_trans);
        dialogDetailTransaksiBinding.txtAlamat.setText(alamat_user);
        dialogDetailTransaksiBinding.txtSubTotal.setText(total_trans);
        dialogDetailTransaksiBinding.txtFee.setText("0");
        dialogDetailTransaksiBinding.txtDiskon.setText("0");
        dialogDetailTransaksiBinding.txtTotalPrice.setText(total_trans);
        String tipe = id_trans.substring(0,7);
        if (tipe.equals("premade")){
            getDetailPremadeOrder(id_trans);
        } else {
            getDetailCustomOrder(id_trans);
        }
        if (status_trans.equals("Ongoing")){
            dialogDetailTransaksiBinding.linFinish.setVisibility(View.VISIBLE);
        } else {
            dialogDetailTransaksiBinding.linFinish.setVisibility(View.GONE);
        }
        dialogDetailTransaksiBinding.cardFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTransaksi(id_trans);
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
                    dialogDetailTransaksiBinding.rvDetailTransaksi.setAdapter(adapterDetailCustom);
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
                    dialogDetailTransaksiBinding.rvDetailTransaksi.setAdapter(adapterDetailTransaksi);
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
                    dismiss();
                } else {
                    Toast.makeText(context, "Gagal Update", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Log.d("Server Error", t.getMessage());
            }
        });
    }
}
