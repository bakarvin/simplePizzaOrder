package com.bakarvin.pizzatime.View.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakarvin.pizzatime.Model.Alamat.ModelAlamat;
import com.bakarvin.pizzatime.databinding.ItemListAlamatBinding;
import com.bakarvin.pizzatime.databinding.ItemMenuBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterAlamat extends RecyclerView.Adapter<AdapterAlamat.AlamatHolder> {
    List<ModelAlamat> alamatList;
    Context context;
    onAction action;

    public AdapterAlamat(List<ModelAlamat> alamatList, Context context) {
        this.alamatList = alamatList;
        this.context = context;
    }

    @NonNull
    @Override
    public AlamatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemListAlamatBinding listAlamatBinding = ItemListAlamatBinding.inflate(layoutInflater,parent,false);
        return new AlamatHolder(listAlamatBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AlamatHolder holder, int position) {
        ModelAlamat alamat = alamatList.get(position);
        holder.listAlamatBinding.txtNama.setText(alamat.getNama_user());
        holder.listAlamatBinding.txtTelp.setText(alamat.getTelp_user());
        holder.listAlamatBinding.txtAlamat.setText(alamat.getAlamat_user()+" "+alamat.getKota_user()+" "+alamat.getKode_pos_user());
        holder.listAlamatBinding.txtTipeAlamat.setText(alamat.getTipe_alamat_user());
        holder.listAlamatBinding.btnUbahAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action.onActionClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alamatList.size();
    }

    public class AlamatHolder extends RecyclerView.ViewHolder {
        private ItemListAlamatBinding listAlamatBinding;
        public AlamatHolder(@NonNull ItemListAlamatBinding listAlamatBinding) {
            super(listAlamatBinding.getRoot());
            this.listAlamatBinding = listAlamatBinding;
        }
    }

    public interface onAction {
        void onActionClick(View view, int position);
    }
    public void ActionClick(onAction onAction){
        action = onAction;
    }
}
