package com.bakarvin.pizzatime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bakarvin.pizzatime.Data.DBsqlite.SqliteManager;
import com.bakarvin.pizzatime.View.Ui.ListAlamatActivity;
import com.bakarvin.pizzatime.View.Ui.UbahDataUserActivity;
import com.bakarvin.pizzatime.View.Ui.UbahPasswordActivity;
import com.bakarvin.pizzatime.View.Ui.WelcomeActivity;
import com.bakarvin.pizzatime.databinding.ActivityProfilUserBinding;

public class ProfilUserActivity extends AppCompatActivity {

    ActivityProfilUserBinding profilUserBinding;
    SqliteManager sqliteManager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profilUserBinding = ActivityProfilUserBinding.inflate(getLayoutInflater());
        setContentView(profilUserBinding.getRoot());
        context = ProfilUserActivity.this;
        sqliteManager = new SqliteManager(context);
        Intent i = getIntent();
        String nama = i.getStringExtra("nama");
        profilUserBinding.txtNamaUser.setText(nama);
        sqliteManager.open();
    }

    public void onProfileClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnUbahData:
                startActivity(new Intent(context, UbahDataUserActivity.class));
                break;
            case R.id.btnHistory:
                startActivity(new Intent(context, RiwayatTransaksiListActivity.class));
                break;
            case R.id.btnAlamat:
                startActivity(new Intent(context, ListAlamatActivity.class));
                break;
            case R.id.btnUbahPassword:
                startActivity(new Intent(context, UbahPasswordActivity.class));
                break;
            case R.id.btnLogout:
                Preferences.clearLoginUser(getBaseContext());
                sqliteManager.deleteAll();
                sqliteManager.close();
                startActivity(new Intent(context, WelcomeActivity.class));
                finish();
                break;
        }

    }
}