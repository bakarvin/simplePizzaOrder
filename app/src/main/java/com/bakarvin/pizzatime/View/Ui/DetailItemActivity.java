package com.bakarvin.pizzatime.View.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.pizzatime.Data.DBsqlite.SqliteManager;
import com.bakarvin.pizzatime.R;
import com.bakarvin.pizzatime.databinding.ActivityDetailItemBinding;
import com.bumptech.glide.Glide;

public class DetailItemActivity extends AppCompatActivity {

    ActivityDetailItemBinding detailItemBinding;
    Context context;
    int qty;
    String idMenu;
    SqliteManager sqliteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailItemBinding = ActivityDetailItemBinding.inflate(getLayoutInflater());
        setContentView(detailItemBinding.getRoot());
        context = this.getApplicationContext();
        sqliteManager = new SqliteManager(context);
        qty = 1;
        detailItemBinding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        detailItemBinding.cardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (detailItemBinding.txtKeranjang.getText().equals("Kembali ke Halaman Menu")){
                    finish();
                } else{
                    addDataDB();
                }

            }
        });

        detailItemBinding.imgMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty--;
                detailItemBinding.qtyDetail.setText(String.valueOf(qty));
                qtyCounter();
            }
        });
        detailItemBinding.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty++;
                detailItemBinding.qtyDetail.setText(String.valueOf(qty));
                qtyCounter();
            }
        });
        getIntentData();
    }

    private void getIntentData() {
        final Bundle data = getIntent().getExtras();
        idMenu = data.getString("idMenu");
        detailItemBinding.namaDetail.setText(data.getString("namaMenu"));
        detailItemBinding.hargaDetail.setText(data.getString("hargaMenu"));
        detailItemBinding.descDetail.setText(data.getString("descPizza"));
        Glide.with(detailItemBinding.imgDetail)
                .load(R.raw.example_pizza_image)
                .into(detailItemBinding.imgDetail);
    }

    private void addDataDB() {
        sqliteManager.open();
        String nama = detailItemBinding.namaDetail.getText().toString();
        String desc = detailItemBinding.descDetail.getText().toString();
        String id = idMenu;
        int harga = Integer.parseInt(String.valueOf(detailItemBinding.hargaDetail.getText()));
        int subTotal = harga * qty;
        Cursor cursor = sqliteManager.checkDup(nama);
        if (cursor.getCount() > 0){
            Toast.makeText(context, "Item Already in ShoppingCart", Toast.LENGTH_SHORT).show();
            sqliteManager.close();
        } else {
            sqliteManager.insert(id, nama, desc, harga, subTotal, qty);
            Toast.makeText(context, "Marked as Favorite ", Toast.LENGTH_SHORT).show();
            finish();
            sqliteManager.close();
        }
    }

    void qtyCounter(){
        if (qty == 0){
            detailItemBinding.imgMin.setClickable(false);
            detailItemBinding.imgMin.setEnabled(false);
            detailItemBinding.txtKeranjang.setText("Kembali ke Halaman Menu");
            detailItemBinding.cardAdd.setCardBackgroundColor(Color.RED);
        } else {
            detailItemBinding.imgMin.setClickable(true);
            detailItemBinding.imgMin.setEnabled(true);
            detailItemBinding.txtKeranjang.setText("Tambahkan kedalam Keranjang");
            detailItemBinding.cardAdd.setCardBackgroundColor(Color.parseColor("#FF8A00"));
        }
    }
}