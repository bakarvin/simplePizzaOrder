package com.bakarvin.pizzatime.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bakarvin.pizzatime.Model.ModelTopping;
import com.bakarvin.pizzatime.R;
import com.bakarvin.pizzatime.databinding.ItemToppingsBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterFreeTopping extends RecyclerView.Adapter<AdapterFreeTopping.ToppingHolder> {
    private ArrayList<ModelTopping> listTopping;
    private Context context;
    private onAction action;
    private int freeCount;
    public AdapterFreeTopping(ArrayList<ModelTopping> listTopping, Context context) {
        this.listTopping = listTopping;
        this.context = context;
        freeCount=0;
    }

    @NonNull
    @Override
    public AdapterFreeTopping.ToppingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemToppingsBinding toppingsBinding = ItemToppingsBinding.inflate(layoutInflater,parent,false);
        return new ToppingHolder(toppingsBinding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull AdapterFreeTopping.ToppingHolder holder, int position) {
        ModelTopping modelTopping = listTopping.get(position);
        Glide.with(holder.itemToppingsBinding.itemImg)
                .load(modelTopping.getImgTopping())
                .into(holder.itemToppingsBinding.itemImg);
        holder.itemToppingsBinding.itemNama.setText(modelTopping.getNamaTopping());
        holder.itemToppingsBinding.itemHarga.setText("Free");
        holder.itemToppingsBinding.itemHarga.setTextColor(Color.WHITE);
        holder.itemToppingsBinding.itemHarga.setBackgroundColor(R.color.purple_200);
        holder.itemToppingsBinding.itemCardInner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action.onActionClick(view, position);
                modelTopping.setClickedTopping(!modelTopping.clickedTopping());
                if (modelTopping.clickedTopping() &&  freeCount >= 4){
                    modelTopping.setClickedTopping(!modelTopping.clickedTopping());
                } else {
                    if (modelTopping.clickedTopping()) {
                        holder.itemToppingsBinding.itemCardOutter.setBackgroundColor(Color.BLACK);
                        freeCount ++;
                    } else {
                        holder.itemToppingsBinding.itemCardOutter.setBackgroundColor(Color.WHITE);
                        freeCount --;
                    }
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



