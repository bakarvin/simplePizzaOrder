package com.bakarvin.pizzatime.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bakarvin.pizzatime.Model.Alamat.ModelAlamat;
import com.bakarvin.pizzatime.databinding.ItemListAlamatDialogBinding;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterListAlamat extends RecyclerView.Adapter<AdapterListAlamat.AlamatHolder> {
    List<ModelAlamat> alamatList;
    Context context;
    onAction action;

    public AdapterListAlamat(List<ModelAlamat> alamatList, Context context) {
        this.alamatList = alamatList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterListAlamat.AlamatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemListAlamatDialogBinding alamatDialogBinding = ItemListAlamatDialogBinding.inflate(layoutInflater,parent,false);
        return new AlamatHolder(alamatDialogBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListAlamat.AlamatHolder holder, int position) {
        ModelAlamat alamat = alamatList.get(position);
        holder.alamatDialogBinding.txtTipeAlamat.setText(alamat.getTipe_alamat_user()+"-"+alamat.getAlamat_user());
        holder.alamatDialogBinding.txtAlamatLengkap.setText(alamat.getTipe_alamat_user()+","+alamat.getKota_user()+","+alamat.getKode_pos_user());
        holder.alamatDialogBinding.linAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action.ActionClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alamatList.size();
    }

    public class AlamatHolder extends RecyclerView.ViewHolder {
        private ItemListAlamatDialogBinding alamatDialogBinding;
        public AlamatHolder(@NonNull ItemListAlamatDialogBinding alamatDialogBinding) {
            super(alamatDialogBinding.getRoot());
            this.alamatDialogBinding = alamatDialogBinding;
        }
    }

    public interface onAction{
        void ActionClick(View view, int position);
    }
    public void ActionClick(onAction onAction){
        action = onAction;
    }
}
