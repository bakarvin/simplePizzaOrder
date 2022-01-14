package com.bakarvin.pizzatime.View.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bakarvin.pizzatime.Data.DBsqlite.SqliteManager;
import com.bakarvin.pizzatime.databinding.ActivityLoadingOrderBinding;
import com.bakarvin.pizzatime.example;

public class LoadingOrderActivity extends AppCompatActivity {

    ActivityLoadingOrderBinding loadingOrderBinding;
    Context context;
    SqliteManager sqliteManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingOrderBinding = ActivityLoadingOrderBinding.inflate(getLayoutInflater());
        setContentView(loadingOrderBinding.getRoot());
        context = LoadingOrderActivity.this;
        sqliteManager = new SqliteManager(context);
        loadTime();
    }

    private void clearSqlite() {
        sqliteManager.open();
        sqliteManager.deleteAll();
        sqliteManager.close();
    }

    private void getSetIntentData(){
        String id_trans = getIntent().getStringExtra("id_trans");
        String tgl_trans = getIntent().getStringExtra("tgl_trans");
        String total_trans = getIntent().getStringExtra("total_trans");
        String alamat_user = getIntent().getStringExtra("alamat_user");
        String time_trans = getIntent().getStringExtra("time_trans");
        Intent i = new Intent(context, example.class);
        i.putExtra("id_trans", id_trans);
        i.putExtra("tgl_trans", tgl_trans);
        i.putExtra("total_trans", total_trans);
        i.putExtra("alamat_user", alamat_user);
        i.putExtra("time_trans", time_trans);
        startActivity(i);
        finish();
    }

    private void loadTime() {
        int waktu_loading = 5000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                clearSqlite();
                getSetIntentData();
            }
        }, waktu_loading);
    }
}