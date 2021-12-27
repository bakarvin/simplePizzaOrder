package com.bakarvin.pizzatime.View.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.pizzatime.Data.DBsqlite.DataBaseCart;
import com.bakarvin.pizzatime.Data.DBsqlite.SqliteManager;
import com.bakarvin.pizzatime.databinding.ActivityDetailShopCartBinding;

public class DetailShopCartActivity extends AppCompatActivity {

    ActivityDetailShopCartBinding shopCartBinding;
    private SQLiteDatabase db;
    DataBaseCart dataBaseCart;
    SqliteManager sqliteManager;
    int kode;
    int qty;
    int harga;
    String id;
    String harga_total;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shopCartBinding = ActivityDetailShopCartBinding.inflate(getLayoutInflater());
        setContentView(shopCartBinding.getRoot());
        context = this.getApplicationContext();
        sqliteManager = new SqliteManager(context);
        sqliteManager.open();
        Intent i = getIntent();
        kode = i .getIntExtra("kode", 1);
        id = i.getStringExtra("id_item");
        harga = i.getIntExtra("harga_item",1);
        Toast.makeText(context, String.valueOf(kode), Toast.LENGTH_SHORT).show();
        if (kode == 1){
            qty = 1;
        } else {
            qty = i.getIntExtra("qty_item",1);
            shopCartBinding.qtyDetail.setText(String.valueOf(qty));
        }


        shopCartBinding.imgMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty--;
                shopCartBinding.qtyDetail.setText(String.valueOf(qty));
                qtyCounter();
            }
        });
        shopCartBinding.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty++;
                shopCartBinding.qtyDetail.setText(String.valueOf(qty));
                qtyCounter();
            }
        });
        shopCartBinding.cardUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qty == 0){
                    hapusItem(id);
                } else {
                    updateItem(id);
                }
            }
        });
    }

    void qtyCounter(){
        if (qty == 0){
            shopCartBinding.imgMin.setClickable(false);
            shopCartBinding.imgMin.setEnabled(false);
            shopCartBinding.txtKeranjang.setText("Hapus Menu dari Keranjang");
            shopCartBinding.cardUpdate.setCardBackgroundColor(Color.RED);
        } else {
            shopCartBinding.imgMin.setClickable(true);
            shopCartBinding.imgMin.setEnabled(true);
            shopCartBinding.txtKeranjang.setText("Tambahkan kedalam Keranjang");
            shopCartBinding.cardUpdate.setCardBackgroundColor(Color.parseColor("#FF8A00"));
        }
    }

    private void updateItem(String id){
        harga_total = String.valueOf(qty * harga);
        sqliteManager.updateQTY(id,qty, Integer.parseInt(harga_total));
        finish();
    }

    private void hapusItem(String id) {
        harga_total = String.valueOf(qty * harga);
        sqliteManager.delete(id);
        finish();
    }

}