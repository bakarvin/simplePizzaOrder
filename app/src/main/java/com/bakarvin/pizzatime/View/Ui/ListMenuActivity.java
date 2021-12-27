package com.bakarvin.pizzatime.View.Ui;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Toast;

import com.bakarvin.pizzatime.R;
import com.bakarvin.pizzatime.View.Adapter.AdapterPreMadeMenu;
import com.bakarvin.pizzatime.Api.ConfigRetrofit;
import com.bakarvin.pizzatime.Data.DBsqlite.DataBaseCart;
import com.bakarvin.pizzatime.Data.DBsqlite.SqliteManager;
import com.bakarvin.pizzatime.Model.Menu.MenuResponse;
import com.bakarvin.pizzatime.Model.Menu.ModelMenu;
import com.bakarvin.pizzatime.databinding.ActivityListMenu2Binding;

import java.util.ArrayList;

public class ListMenuActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ActivityListMenu2Binding listMenu2Binding;
    ArrayList<ModelMenu> menuList = new ArrayList<>();
    AdapterPreMadeMenu adapterPreMadeMenu;
    private SQLiteDatabase db;
    DataBaseCart dbCart;
    SqliteManager sqliteManager;
    Boolean searchOpen;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listMenu2Binding = ActivityListMenu2Binding.inflate(getLayoutInflater());
        getWindow().setBackgroundDrawableResource(R.drawable.bg);
        setContentView(listMenu2Binding.getRoot());
        context = ListMenuActivity.this;
        sqliteManager = new SqliteManager(context);
        searchOpen = Boolean.FALSE;
        listMenu2Binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerSelected();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        listMenu2Binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openSearchBar();
            }
        });
        listMenu2Binding.imgCloseSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFilterBar();
            }
        });
        listMenu2Binding.showCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCart();
            }
        });
        listMenu2Binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        checkCart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        checkCart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void spinnerSelected() {
        String spin = listMenu2Binding.spinner.getSelectedItem().toString();
        switch (spin){
            case "All":
                getAllMenuList("1", "all");
                break;
            case "Pizza":
                getAllMenuList("2", "Pizza");
                break;
            case "Drink":
                getAllMenuList("2", "Drink");
                break;
            case "Side Dish":
                getAllMenuList("2", "Side Dish");
                break;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listMenu2Binding.txtQuery.setText(newText);
        String str_query = listMenu2Binding.txtQuery.getText().toString();
        menuList.clear();
        getFilterMenuList(str_query);
        return false;
    }

    void checkCart(){
        sqliteManager.open();
        Cursor c = sqliteManager.readAll();
        c.moveToFirst();
        int icount = c.getCount();
        if (icount==0){
            listMenu2Binding.linCart.setVisibility(View.GONE);
            sqliteManager.close();
        } else {
            listMenu2Binding.linCart.setVisibility(View.VISIBLE);
            sqliteManager.close();
        }
        sqliteManager.close();
    }

    private void showCart() {
        startActivity(new Intent(context, ShoppingCartActivity.class));
    }

    private void openSearchBar() {
        menuList.clear();
        listMenu2Binding.linFilter.setVisibility(View.GONE);
        listMenu2Binding.linSearch.setVisibility(View.VISIBLE);
        searchOpen = Boolean.TRUE;
        listMenu2Binding.searchMenu.onActionViewExpanded();
        listMenu2Binding.searchMenu.setOnQueryTextListener(this);
    }

    private void openFilterBar() {
        menuList.clear();
        listMenu2Binding.linFilter.setVisibility(View.VISIBLE);
        listMenu2Binding.linSearch.setVisibility(View.GONE);
        searchOpen = Boolean.FALSE;
        hideSoftKeyboard();
    }

    public void hideSoftKeyboard(){
        View v = this.getCurrentFocus();
        if (v!=null){
            InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    private void getAllMenuList(String kode, String query) {
        menuList.clear();
        ConfigRetrofit.service.readMenu(kode, query).enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                int kode = response.body().getKode();
                menuList.addAll(response.body().getResult());
                adapterPreMadeMenu = new AdapterPreMadeMenu(context, menuList);
                listMenu2Binding.rvMenu.setAdapter(adapterPreMadeMenu);
                adapterPreMadeMenu.notifyDataSetChanged();
                adapterPreMadeMenu.ActionClick(new AdapterPreMadeMenu.onAction() {
                    @Override
                    public void onActionClick(View view, int position) {
                        String id = menuList.get(position).getId_menu();
                        String nama = menuList.get(position).getNama_menu();
                        String harga = menuList.get(position).getHarga_menu();
                        intentDetail(id,nama,harga);
                    }
                });
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                Log.d("Server Error", t.getMessage());
            }
        });
    }

    private void getFilterMenuList(String filter) {
        menuList.clear();
        ConfigRetrofit.service.SearchMenu(filter).enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                int kode = response.body().getKode();
                if (kode == 1){
                    menuList.addAll(response.body().getResult());
                    adapterPreMadeMenu = new AdapterPreMadeMenu(context, menuList);
                    listMenu2Binding.rvMenu.setAdapter(adapterPreMadeMenu);
                    adapterPreMadeMenu.ActionClick(new AdapterPreMadeMenu.onAction() {
                        @Override
                        public void onActionClick(View view, int position) {
                            String id = menuList.get(position).getId_menu();
                            String nama = menuList.get(position).getNama_menu();
                            String harga = menuList.get(position).getHarga_menu();
                            intentDetail(id,nama,harga);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                Log.d("Server Error", t.getMessage());
                Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void intentDetail(String id, String nama, String harga) {
        Intent detailItem = new Intent(context, DetailItemActivity.class);
        detailItem.putExtra("idMenu", id);
        detailItem.putExtra("namaMenu", nama);
        detailItem.putExtra("hargaMenu", harga);
        startActivity(detailItem);
    }

}