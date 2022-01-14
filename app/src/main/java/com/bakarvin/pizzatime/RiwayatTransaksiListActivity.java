package com.bakarvin.pizzatime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.bakarvin.pizzatime.View.Ui.Fragment.HistoryOrderFragment;
import com.bakarvin.pizzatime.View.Ui.Fragment.OnGoingFragment;
import com.bakarvin.pizzatime.databinding.ActivityRiwayatTransaksiListBinding;

public class RiwayatTransaksiListActivity extends AppCompatActivity {

    ActivityRiwayatTransaksiListBinding riwayatBinding;
    Context context;

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        riwayatBinding = ActivityRiwayatTransaksiListBinding.inflate(getLayoutInflater());
        setContentView(riwayatBinding.getRoot());
        context = RiwayatTransaksiListActivity.this;
        riwayatBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        riwayatBinding.radioGroup.check(riwayatBinding.rbOngoing.getId());
        loadFrag(new OnGoingFragment());
    }
    private void loadFrag(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragLay, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void onSelected(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.rbOngoing:
                if (checked)
                    loadFrag(new OnGoingFragment());
                break;

            case R.id.rbHistory:
                if (checked)
                    loadFrag(new HistoryOrderFragment());
                break;
        }
    }
}