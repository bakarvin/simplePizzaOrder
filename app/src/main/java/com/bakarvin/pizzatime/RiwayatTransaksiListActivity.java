package com.bakarvin.pizzatime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.bakarvin.pizzatime.View.Ui.Fragment.HistoryOrderFragment;
import com.bakarvin.pizzatime.databinding.ActivityRiwayatTransaksiListBinding;

public class RiwayatTransaksiListActivity extends AppCompatActivity {

    ActivityRiwayatTransaksiListBinding riwayatBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        riwayatBinding = ActivityRiwayatTransaksiListBinding.inflate(getLayoutInflater());
        setContentView(riwayatBinding.getRoot());
        loadFrag(new HistoryOrderFragment());

    }
    private boolean loadFrag(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragLay, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }
}