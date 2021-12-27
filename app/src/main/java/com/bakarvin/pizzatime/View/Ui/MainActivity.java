package com.bakarvin.pizzatime.View.Ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bakarvin.pizzatime.Api.ConfigRetrofit;
import com.bakarvin.pizzatime.Data.DBsqlite.SqliteManager;
import com.bakarvin.pizzatime.Model.User.ModelUser;
import com.bakarvin.pizzatime.Model.User.UserResponse;
import com.bakarvin.pizzatime.Preferences;
import com.bakarvin.pizzatime.ProfilUserActivity;
import com.bakarvin.pizzatime.R;
import com.bakarvin.pizzatime.databinding.ActivityMainBinding;
import com.bakarvin.pizzatime.databinding.DialogContinueShopBinding;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    Context context;
    int[] sampleImg =
            {
                    R.drawable.bg1,
                    R.drawable.bg2,
                    R.drawable.bg3
            };
    SqliteManager sqliteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        //getWindow().setStatusBarColor(getResources().getColor(R.color.orange_700));
        context = MainActivity.this;
        sqliteManager = new SqliteManager(context);
        sqliteManager.open();
        mainBinding.carouselView.setPageCount(sampleImg.length);
        mainBinding.carouselView.setImageListener(imageListener);
        mainBinding.carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
        checkCart();
        checkUser();
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImg[position]);
        }
    };

    private void checkUser(){
        String uname = Preferences.getLoginUname(getBaseContext());
        ConfigRetrofit.service.checkUser(uname).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                int kode = response.body().getKode();
                if (kode == 1){
                    List<ModelUser> result = response.body().getResult();
                    mainBinding.txtNamaUser.setText(result.get(0).getNama_user());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

    }

    private void checkCart() {
        Cursor c = sqliteManager.readAll();
        c.moveToFirst();
        int icount = c.getCount();
        if (icount>0){
            showDialogWarning();
        }
    }

    private void hapusCart(){
        sqliteManager.deleteAll();
        sqliteManager.close();
    }

    private void showDialogWarning() {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        DialogContinueShopBinding dialogBinding = DialogContinueShopBinding.inflate(layoutInflater);
        dialog.setView(dialogBinding.getRoot());
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialogBinding.cardLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ShoppingCartActivity.class));
                dialog.dismiss();
            }
        });
        dialogBinding.cardHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hapusCart();
                startActivity(new Intent(context, ShoppingCartActivity.class));
                Toast.makeText(context, "Keranjang telah dibersihkan", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    public void onMainClicked(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnAccount:
                Intent i = new Intent(context, ProfilUserActivity.class);
                i.putExtra("nama", mainBinding.txtNamaUser.getText().toString());
                startActivity(i);
                break;
            case R.id.btnCustomMenu:
                startActivity(new Intent(MainActivity.this, CustomPizzaActivity.class));
                break;
            case R.id.btnListMenu:
                startActivity(new Intent(MainActivity.this, ListMenuActivity.class));
                break;
            case R.id.btnSemuaPromo:
                Toast.makeText(context, "Detail Promo", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}