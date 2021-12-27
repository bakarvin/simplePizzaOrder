package com.bakarvin.pizzatime.View.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakarvin.pizzatime.Model.ModelTopping;
import com.bakarvin.pizzatime.databinding.ItemToppingsBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterTopping extends RecyclerView.Adapter<AdapterTopping.ToppingHolder> {
    private ArrayList<ModelTopping> listTopping;
    private Context context;
    private onAction action;
    private int selectedItem;
    private int freeCount;
    public AdapterTopping(ArrayList<ModelTopping> listTopping, Context context) {
        this.listTopping = listTopping;
        this.context = context;
        selectedItem=-1;
    }

    @NonNull
    @Override
    public AdapterTopping.ToppingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemToppingsBinding toppingsBinding = ItemToppingsBinding.inflate(layoutInflater,parent,false);
        return new ToppingHolder(toppingsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTopping.ToppingHolder holder, int position) {
        ModelTopping modelTopping = listTopping.get(position);
        Glide.with(holder.itemToppingsBinding.itemImg)
                .load(modelTopping.getImgTopping())
                .into(holder.itemToppingsBinding.itemImg);
        holder.itemToppingsBinding.itemNama.setText(modelTopping.getNamaTopping());
        holder.itemToppingsBinding.itemHarga.setText(modelTopping.getHargaTopping());
//        holder.itemToppingsBinding.itemCardOutter.setBackgroundColor(modelTopping.clickedTopping() ? Color.BLACK : Color.WHITE);
        holder.itemToppingsBinding.itemCardInner.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                action.onActionClick(view, position);
                modelTopping.setClickedTopping(!modelTopping.clickedTopping());
                    if (modelTopping.clickedTopping()) {
                        holder.itemToppingsBinding.itemCardOutter.setBackgroundColor(Color.parseColor("#FFC700"));
                    } else {
                        holder.itemToppingsBinding.itemCardOutter.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
//                holder.itemToppingsBinding.itemCardOutter.setBackgroundColor(modelTopping.clickedTopping() ? Color.BLACK : Color.WHITE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTopping.size();
    }

    public class ToppingHolder extends RecyclerView.ViewHolder {
        private ItemToppingsBinding itemToppingsBinding;
        public ToppingHolder(@NonNull ItemToppingsBinding itemToppingsBinding) {
            super(itemToppingsBinding.getRoot());
            this.itemToppingsBinding= itemToppingsBinding;
        }
    }
    public interface onAction{
        void onActionClick(View view, int position);
    }

    public void ActionClick(onAction onAction){
        action = onAction;
    }
}
