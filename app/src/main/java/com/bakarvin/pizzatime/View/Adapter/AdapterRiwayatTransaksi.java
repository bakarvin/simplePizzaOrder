package com.bakarvin.pizzatime.View.Adapter;

import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakarvin.pizzatime.Model.Transaksi.ModelTransaksi;
import com.bakarvin.pizzatime.databinding.ItemMenuBinding;
import com.bakarvin.pizzatime.databinding.ItemTransaksiBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterRiwayatTransaksi extends RecyclerView.Adapter<AdapterRiwayatTransaksi.RiwayatHolder> {
    private ArrayList<ModelTransaksi> tranksiList;
    private Context context;
    private onAction action;

    public AdapterRiwayatTransaksi(ArrayList<ModelTransaksi> tranksiList, Context context) {
        this.tranksiList = tranksiList;
        this.context = context;
    }

    @NonNull
    @Override
    public RiwayatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemTransaksiBinding transaksiBinding = ItemTransaksiBinding.inflate(layoutInflater,parent,false);
        return new RiwayatHolder(transaksiBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatHolder holder, int position) {
        ModelTransaksi transaksi = tranksiList.get(position);
        holder.transaksiBinding.txtTglOrder.setText(transaksi.getTgl_trans());
        holder.transaksiBinding.txtTotalPrice.setText("Rp. "+transaksi.getTotal_trans()+",-");
        holder.transaksiBinding.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action.onActionClick(view,position);
            }
        });
        String id = transaksi.getId_trans();
        String tipe = id.substring(0,7);
        if (tipe.equals("premade")){
            holder.transaksiBinding.txtOrder.setText("Order Pizza");
        } else {
            holder.transaksiBinding.txtOrder.setText("Custom Pizza");
        }
    }

    @Override
    public int getItemCount() {
        return tranksiList.size();
    }

    public class RiwayatHolder extends RecyclerView.ViewHolder {
        private ItemTransaksiBinding transaksiBinding;
        public RiwayatHolder(@NonNull ItemTransaksiBinding transaksiBinding) {
            super(transaksiBinding.getRoot());
            this.transaksiBinding = transaksiBinding;
        }
    }
    public interface onAction{
        void onActionClick(View view, int position);
    }
    public void ActionClick(onAction onAction){
        action = onAction;
    }

}
