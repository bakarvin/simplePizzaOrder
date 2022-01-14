package com.bakarvin.pizzatime.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakarvin.pizzatime.Model.CustomTransaksi.ModelCustomTransaksi;
import com.bakarvin.pizzatime.databinding.ItemDetailCustomBinding;
import com.bakarvin.pizzatime.databinding.ItemDetailTransaksiBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterDetailCustom extends RecyclerView.Adapter<AdapterDetailCustom.CustomHolder> {
    ArrayList<ModelCustomTransaksi> customTransaksiList;
    Context context;

    public AdapterDetailCustom(ArrayList<ModelCustomTransaksi> customTransaksiList, Context context) {
        this.customTransaksiList = customTransaksiList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemDetailCustomBinding detailCustomBinding = ItemDetailCustomBinding.inflate(layoutInflater,parent,false);
        return new CustomHolder(detailCustomBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
        ModelCustomTransaksi model = customTransaksiList.get(position);
//        List<String> freeTop = model.getFree_topping();
//        List<String> extraTop = model.getExtra_topping();
        holder.itemDetailCustomBinding.freeTop.setText(model.getFree_topping());
        holder.itemDetailCustomBinding.extraTop.setText(model.getExtra_topping());
        holder.itemDetailCustomBinding.size.setText(model.getSize_custom());

    }

    @Override
    public int getItemCount() {
        return customTransaksiList.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder {
        private ItemDetailCustomBinding itemDetailCustomBinding;
        public CustomHolder(@NonNull ItemDetailCustomBinding itemDetailCustomBinding) {
            super(itemDetailCustomBinding.getRoot());
            this.itemDetailCustomBinding = itemDetailCustomBinding;
        }
    }
}
