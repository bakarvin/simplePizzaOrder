package com.bakarvin.pizzatime.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakarvin.pizzatime.Model.Menu.ModelMenu;
import com.bakarvin.pizzatime.R;
import com.bakarvin.pizzatime.databinding.ItemMenuBinding;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterPreMadeMenu extends RecyclerView.Adapter<AdapterPreMadeMenu.MenuHolder> {
    private Context context;
    private List<ModelMenu> menuList;
    private onAction action;

    public AdapterPreMadeMenu(Context context, List<ModelMenu> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public AdapterPreMadeMenu.MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMenuBinding menuBinding = ItemMenuBinding.inflate(layoutInflater,parent,false);
        return new MenuHolder(menuBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPreMadeMenu.MenuHolder holder, int position) {
        ModelMenu modelMenu = menuList.get(position);
        Glide.with(holder.menuBinding.imgItem)
                .load(R.raw.pizza_item)
                .into(holder.menuBinding.imgItem);
        holder.menuBinding.namaItem.setText(modelMenu.getNama_menu());
        holder.menuBinding.hrgItem.setText(modelMenu.getHarga_menu());
        holder.menuBinding.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action.onActionClick(view, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MenuHolder extends RecyclerView.ViewHolder {
        private ItemMenuBinding menuBinding;
        public MenuHolder(@NonNull ItemMenuBinding menuBinding) {
            super(menuBinding.getRoot());
            this.menuBinding = menuBinding;
        }
    }
    public interface onAction{
        void onActionClick(View view, int position);
    }
    public void ActionClick(onAction onAction){
        action = onAction;
    }
}