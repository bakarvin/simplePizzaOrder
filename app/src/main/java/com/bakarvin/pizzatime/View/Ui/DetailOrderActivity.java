package com.bakarvin.pizzatime.View.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bakarvin.pizzatime.R;
import com.bakarvin.pizzatime.databinding.ActivityDetailOrderBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailOrderActivity extends AppCompatActivity {

    ActivityDetailOrderBinding detailOrderBinding;
    ArrayList<String> myExtraPrice = new ArrayList<String>();
    ArrayList<String> myExtraName = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailOrderBinding = ActivityDetailOrderBinding.inflate(getLayoutInflater());
        setContentView(detailOrderBinding.getRoot());
        getIntentData();
//        showMap(extraMap);
    }

    private void getIntentData() {
        detailOrderBinding.txtCustomPizza.setText(getIntent().getStringExtra("cstPizza"));
        detailOrderBinding.txtBasePrice.setText(getIntent().getStringExtra("cstPrice"));
        detailOrderBinding.txtFreeTop.setText(getIntent().getStringExtra("cstFreeTop"));
        detailOrderBinding.txtTotalPrice.setText(getIntent().getStringExtra("cstTotal"));
        myExtraName = getIntent().getStringArrayListExtra("cstExtraName");
        myExtraPrice = getIntent().getStringArrayListExtra("cstExtraPrice");
        showMap(myExtraName,myExtraPrice);
    }

    private void showMap(ArrayList<String> myExtraName, ArrayList<String> myExtraPrice) {
        List<HashMap<String, String>> list = new ArrayList<>();

        for (int i = 0; i < myExtraName.size(); i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("Name", myExtraName.get(i));
            hashMap.put("Price", myExtraPrice.get(i));
            list.add(hashMap);
        }

        String[] from = {"Name", "Price"};
        int[] to = {R.id.itemTopNama, R.id.itemTopPrice};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), list, R.layout.item_order_detail, from, to);
        ListView listView = findViewById(R.id.listExtraTop);
        listView.setAdapter(simpleAdapter);
    }

}