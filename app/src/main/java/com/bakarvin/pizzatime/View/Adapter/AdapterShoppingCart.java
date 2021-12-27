package com.bakarvin.pizzatime.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakarvin.pizzatime.Data.DBsqlite.DBContract;
import com.bakarvin.pizzatime.View.Ui.DetailShopCartActivity;
import com.bakarvin.pizzatime.R;
import com.bakarvin.pizzatime.databinding.ItemCartBinding;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterShoppingCart extends RecyclerView.Adapter<AdapterShoppingCart.CartHolder> {
    private Context mContext;
    private Cursor mCursor;
    private onAction action;

    public AdapterShoppingCart(Context mContext, Cursor mCursor) {
        this.mContext = mContext;
        this.mCursor = mCursor;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCartBinding itemCartBinding = ItemCartBinding.inflate(layoutInflater,parent,false);
        return new CartHolder(itemCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;
        }
        final String nama = mCursor.getString(mCursor.getColumnIndex(DBContract.CartList.COL_NAMA_ITEM));
        final int harga_total = mCursor.getInt(mCursor.getColumnIndex(DBContract.CartList.COL_TOTAL_HARGA_ITEM));
        final int harga_satuan = mCursor.getInt(mCursor.getColumnIndex(DBContract.CartList.COL_HARGA_ITEM));
        final int qty = mCursor.getInt(mCursor.getColumnIndex(DBContract.CartList.COL_QTY_ITEM));
        final int id = mCursor.getInt(mCursor.getColumnIndex(DBContract.CartList._ID));
        holder.itemCartBinding.namaCart.setText(nama);
        holder.itemCartBinding.hargaCart.setText("Rp. " + harga_total);
        holder.itemCartBinding.qtyCart.setText(qty +"x");
        Glide.with(holder.itemCartBinding.imgCart)
                .load(R.raw.pizza_item)
                .into(holder.itemCartBinding.imgCart);
        holder.itemCartBinding.cardCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailShopCartActivity.class);
                i.putExtra("id_item", String.valueOf(id));
                i.putExtra("harga_item", harga_satuan);
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }



    public class CartHolder extends RecyclerView.ViewHolder {
        private ItemCartBinding itemCartBinding;
        public CartHolder(@NonNull ItemCartBinding itemCartBinding) {
            super(itemCartBinding.getRoot());
            this.itemCartBinding = itemCartBinding;
        }
    }

    public interface onAction {
        void onActionClick(View view, int postition);
    }

    public void ActionClick(onAction onAction){
        action = onAction;
    }

}
