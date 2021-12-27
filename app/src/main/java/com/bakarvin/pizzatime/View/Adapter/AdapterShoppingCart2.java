package com.bakarvin.pizzatime.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakarvin.pizzatime.Model.ModelShoppingCart;
import com.bakarvin.pizzatime.R;
import com.bakarvin.pizzatime.databinding.ItemCartBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterShoppingCart2 extends RecyclerView.Adapter<AdapterShoppingCart2.CartHolder> {
    private ArrayList<ModelShoppingCart> listShoppingCart;
    private Context context;
    private onAction action;

    public AdapterShoppingCart2(ArrayList<ModelShoppingCart> listShoppingCart, Context context) {
        this.listShoppingCart = listShoppingCart;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterShoppingCart2.CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCartBinding itemCartBinding = ItemCartBinding.inflate(layoutInflater,parent,false);
        return new CartHolder(itemCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterShoppingCart2.CartHolder holder, int position) {
        ModelShoppingCart modelShoppingCart = listShoppingCart.get(position);
        holder.itemCartBinding.namaCart.setText(modelShoppingCart.getNama());
        holder.itemCartBinding.hargaCart.setText("Rp. " + modelShoppingCart.getHarga_total());
        holder.itemCartBinding.qtyCart.setText(modelShoppingCart.getQty() +"x");
        Glide.with(holder.itemCartBinding.imgCart)
                .load(R.raw.pizza_item)
                .into(holder.itemCartBinding.imgCart);
        holder.itemCartBinding.cardCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action.onActionClick(view, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listShoppingCart.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        private ItemCartBinding itemCartBinding;
        public CartHolder(@NonNull ItemCartBinding itemCartBinding) {
            super(itemCartBinding.getRoot());
            this.itemCartBinding = itemCartBinding;
        }
    }
    public interface onAction {
        void onActionClick(View view, int position);
    }
    public void ActionClick(onAction onAction) {
        action = onAction;
    }
}
