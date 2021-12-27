package com.bakarvin.pizzatime.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakarvin.pizzatime.Model.DetailTransaksi.ModelDetailTransaksi;
import com.bakarvin.pizzatime.databinding.ItemDetailTransaksiBinding;
import com.bakarvin.pizzatime.databinding.ItemListAlamatBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterDetailTransaksi extends RecyclerView.Adapter<AdapterDetailTransaksi.DetailHolder> {
    ArrayList<ModelDetailTransaksi> detailTransaksiList;
    Context context;
    onAction action;

    public AdapterDetailTransaksi(ArrayList<ModelDetailTransaksi> detailTransaksiList, Context context) {
        this.detailTransaksiList = detailTransaksiList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterDetailTransaksi.DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemDetailTransaksiBinding detailTransaksiBinding = ItemDetailTransaksiBinding.inflate(layoutInflater,parent,false);
        return new DetailHolder(detailTransaksiBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDetailTransaksi.DetailHolder holder, int position) {
        ModelDetailTransaksi model = detailTransaksiList.get(position);

        holder.itemDetailTransaksiBinding.namaItem.setText(model.getNama_menu());
        holder.itemDetailTransaksiBinding.hargaItem.setText("Rp. "+model.getTotal_menu()+",-");
        holder.itemDetailTransaksiBinding.qtyItem.setText(model.getQty_menu()+"x");
    }

    @Override
    public int getItemCount() {
        return detailTransaksiList.size();
    }

    public class DetailHolder extends RecyclerView.ViewHolder {
        private ItemDetailTransaksiBinding itemDetailTransaksiBinding;
        public DetailHolder(@NonNull ItemDetailTransaksiBinding itemDetailTransaksiBinding) {
            super(itemDetailTransaksiBinding.getRoot());
            this.itemDetailTransaksiBinding = itemDetailTransaksiBinding;
        }
    }

    public interface onAction{
        void onActionClick(View view, int position);
    }
    public void ActionClick(onAction onAction){
        action = onAction;
    }
}
