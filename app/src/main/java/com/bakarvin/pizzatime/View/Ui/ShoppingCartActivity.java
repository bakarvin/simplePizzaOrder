package com.bakarvin.pizzatime.View.Ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.pizzatime.Api.ConfigRetrofit;
import com.bakarvin.pizzatime.ListAlamatDialogActivity;
import com.bakarvin.pizzatime.Model.DetailTransaksi.DetailTransaksiResponse;
import com.bakarvin.pizzatime.Model.Transaksi.TransaksiResponse;
import com.bakarvin.pizzatime.Preferences;
import com.bakarvin.pizzatime.View.Adapter.AdapterShoppingCart;
import com.bakarvin.pizzatime.View.Adapter.AdapterShoppingCart2;
import com.bakarvin.pizzatime.Data.DBsqlite.DBContract;
import com.bakarvin.pizzatime.Data.DBsqlite.SqliteManager;
import com.bakarvin.pizzatime.Model.DetailTransaksi.ModelDetailTransaksi;
import com.bakarvin.pizzatime.Model.ModelShoppingCart;
import com.bakarvin.pizzatime.databinding.ActivityShoppingCartBinding;
import com.bakarvin.pizzatime.databinding.DialogAddAlamatBinding;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ShoppingCartActivity extends AppCompatActivity {

    ActivityShoppingCartBinding cartBinding;
    AdapterShoppingCart adapterShoppingCart;
    AdapterShoppingCart2 adapterShoppingCart2;
    SqliteManager sqliteManager;
    Cursor c;
    Context context;
    JSONArray jsonArray;
    ProgressDialog progressDialog;
    Gson gson = new Gson();
    ArrayList<ModelShoppingCart> resultList = new ArrayList<>();
    ArrayList<ModelDetailTransaksi> detailTransList = new ArrayList<>();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sqliteManager = new SqliteManager(context);
        sqliteManager.open();
        getAlamat();
        getPriceCart();
        getCartList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartBinding = ActivityShoppingCartBinding.inflate(getLayoutInflater());
        setContentView(cartBinding.getRoot());
        context = ShoppingCartActivity.this;
        sqliteManager = new SqliteManager(context);
        jsonArray = new JSONArray();
        sqliteManager.open();
        cartBinding.imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        cartBinding.checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Preferences.getAlamatUserSingkat(context).isEmpty()){
                    dialogAddAlamat();
                } else {
                    checkOut();
                }
            }
        });
        cartBinding.txtAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ListAlamatDialogActivity.class));
            }
        });
        getAlamat();
        getCartList();
        getPriceCart();
    }

    private void dialogAddAlamat() {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        DialogAddAlamatBinding dialogAlamatBinding = DialogAddAlamatBinding.inflate(layoutInflater);
        dialog.setView(dialogAlamatBinding.getRoot());
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialogAlamatBinding.cardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ListAlamatDialogActivity.class));
                dialog.dismiss();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void getAlamat(){
        if (!Preferences.getAlamatUserSingkat(context).isEmpty()){
            cartBinding.txtAlamat.setText(Preferences.getAlamatUserSingkat(context));
        } else {
            cartBinding.txtAlamat.setText("Kos - alamat lengkap, kode pos");
        }
    }

    public void getCartList(){
        resultList.clear();
        getShopItem();
        adapterShoppingCart2 = new AdapterShoppingCart2(resultList, context);
        cartBinding.rvCart.setAdapter(adapterShoppingCart2);
        adapterShoppingCart2.ActionClick(new AdapterShoppingCart2.onAction() {
            @Override
            public void onActionClick(View view, int position) {
                String id = String.valueOf(resultList.get(position).getId());
                int harga = Integer.parseInt(resultList.get(position).getHarga_satuan());
                int qty = resultList.get(position).getQty();
                Intent detailItem = new Intent(context, DetailShopCartActivity.class);
                detailItem.putExtra("id_item", id);
                detailItem.putExtra("harga_item", harga);
                detailItem.putExtra("qty_item", qty);
                detailItem.putExtra("kode",2);
                startActivity(detailItem);
            }
        });
    }

    public void getPriceCart() {
        Cursor mcursor = sqliteManager.readTotalHarga();
        if (mcursor != null) {
            mcursor.moveToFirst();
            cartBinding.txtTotalPrice.setText(mcursor.getString(0));
        }
    }

    private void convertJSON() {
        for (int i=0; i < detailTransList.size(); i++) {
            jsonArray.put(detailTransList.get(i).getJSONOb());
        }
        Log.d("JSON", "convertJSON: " + jsonArray);
    }

    private void insertDetailTrans(String id_trans) {
        Cursor mc = sqliteManager.readAll();
        int count = mc.getCount();
        if (count != 0){
            while (mc.moveToNext()) {
                String nama = mc.getString(mc.getColumnIndex(DBContract.CartList.COL_NAMA_ITEM));
                String id_menu = mc.getString(mc.getColumnIndex(DBContract.CartList.COL_ID_ITEM));
                String harga_total = mc.getString(mc.getColumnIndex(DBContract.CartList.COL_TOTAL_HARGA_ITEM));
                String qty = mc.getString(mc.getColumnIndex(DBContract.CartList.COL_QTY_ITEM));
//              int harga_satuan = c.getInt(c.getColumnIndex(DBContract.CartList.COL_HARGA_ITEM));
//                int id = c.getInt(c.getColumnIndex(DBContract.CartList._ID));
                try {
                    ModelDetailTransaksi detailTransaksi = new ModelDetailTransaksi();
                    detailTransaksi.setId_trans(id_trans);
                    detailTransaksi.setId_menu(id_menu);
                    detailTransaksi.setNama_menu(nama);
                    detailTransaksi.setQty_menu(qty);
                    detailTransaksi.setTotal_menu(harga_total);
                    detailTransList.add(detailTransaksi);
                } catch (Exception e) {
                    Log.d("Server Error", e.getMessage());

                }
            }
        }
        convertJSON();
    }

    public ArrayList<ModelShoppingCart> getShopItem() {
        Cursor c = sqliteManager.readAll();
        int count = c.getCount();
        if (count != 0) {
            while (c.moveToNext()) {
                String nama = c.getString(c.getColumnIndex(DBContract.CartList.COL_NAMA_ITEM));
                int harga_total = c.getInt(c.getColumnIndex(DBContract.CartList.COL_TOTAL_HARGA_ITEM));
                int harga_satuan = c.getInt(c.getColumnIndex(DBContract.CartList.COL_HARGA_ITEM));
                int qty = c.getInt(c.getColumnIndex(DBContract.CartList.COL_QTY_ITEM));
                int id = c.getInt(c.getColumnIndex(DBContract.CartList._ID));
                try {
                    ModelShoppingCart shoppingCart = new ModelShoppingCart();
                    shoppingCart.setId(id);
                    shoppingCart.setNama(nama);
                    shoppingCart.setQty(qty);
                    shoppingCart.setHarga_satuan(String.valueOf(harga_satuan));
                    shoppingCart.setHarga_total(String.valueOf(harga_total));
                    resultList.add(shoppingCart);
                } catch (Exception e) {
                    Log.d("Server Error", e.getMessage());
                }
            }
            cartBinding.linCheckOut.setVisibility(View.VISIBLE);
        } else {
            cartBinding.linCheckOut.setVisibility(View.GONE);
        }
        return resultList;
    }

    private void checkOut() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Menambahkan Pesanan");
        progressDialog.setCancelable(false);
        progressDialog.show();
        final String tgl_trans_db = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        final String tgl_trans = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        final String total_trans = cartBinding.txtTotalPrice.getText().toString();
        final String uname = Preferences.getLoginUname(context);
        final String id_trans = "premade"+uname+tgl_trans;
        final String alamat_user = Preferences.getAlamatUserLengkap(context);
        ConfigRetrofit.service.insertTransaksi(id_trans,uname, tgl_trans_db, total_trans, alamat_user).enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                int kode = response.body().getKode();
                Toast.makeText(context, String.valueOf(kode), Toast.LENGTH_SHORT).show();
                if (kode == 1){
                    checkoutDetailTransaksi(id_trans,total_trans);
                }
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Log.d("Server Error", t.getMessage());
                progressDialog.dismiss();
            }
        });
    }

    private void checkoutDetailTransaksi(String id_trans, String total_trans){
        insertDetailTrans(id_trans);
        String jsonArrays = gson.toJson(detailTransList);
        Log.d("Server: ", jsonArrays);
        Toast.makeText(context, jsonArrays, Toast.LENGTH_SHORT).show();
        ConfigRetrofit.service.insertDetailTransaksi(jsonArrays).enqueue(new Callback<DetailTransaksiResponse>() {
            @Override
            public void onResponse(Call<DetailTransaksiResponse> call, Response<DetailTransaksiResponse> response) {
                int kode = response.body().getKode();
                if (kode ==1) {
//                    Intent i = new Intent(context, example.class);
//                    i.putExtra("cstTotal", total_trans);
//                    i.putExtra("kode",2);
//                    startActivity(i);
//                    finish();
                    progressDialog.dismiss();
                    Log.d("kode", String.valueOf(kode));
                } else {
                    progressDialog.dismiss();
                    Log.d("kode", String.valueOf(kode));
                }
            }

            @Override
            public void onFailure(Call<DetailTransaksiResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("Server Error", t.getMessage());
            }
        });
    }
}