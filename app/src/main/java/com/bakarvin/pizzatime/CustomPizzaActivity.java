package com.bakarvin.pizzatime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bakarvin.pizzatime.Adapter.AdapterFreeTopping;
import com.bakarvin.pizzatime.Adapter.AdapterTopping;
import com.bakarvin.pizzatime.Model.ModelTopping;
import com.bakarvin.pizzatime.databinding.ActivityCustomPizzaBinding;

import java.util.ArrayList;

public class CustomPizzaActivity extends AppCompatActivity {

    ActivityCustomPizzaBinding customPizza;
    int freeToppings = 0;
    String sizePizza;
    double priceSize;
    int priceToppings;
    double extraPriceToppings;
    ArrayList<String> myFreeToppings = new ArrayList<String>();
    ArrayList<ModelTopping> freeToppingsList = new ArrayList<>();
    ArrayList<String> myExtraPriceToppings = new ArrayList<String>();
    ArrayList<String> myExtraNameToppings = new ArrayList<String>();
    ArrayList<ModelTopping> extraToppingsList = new ArrayList<>();
    AdapterTopping adapterTopping;
    AdapterFreeTopping adapterFreeTopping;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customPizza = ActivityCustomPizzaBinding.inflate(getLayoutInflater());
        setContentView(customPizza.getRoot());
        priceToppings = 0;
        extraPriceToppings = 0;
        context = this.getApplicationContext();
        customPizza.txtSubTotal.setText("Rp. "+"0.0"+",-");
        customPizza.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkOut();
            }
        });
        getFreeToppings();
        getExtraToppings();
    }

    public void onButtonClicked(View v){
        boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()){
            case R.id.rbPersonal:
                if (checked)
                    customPizza.txtRBSIZE.setText(customPizza.rbPersonal.getText().toString());
                sizePizza = "Personal";
                priceSize = 30.0;
                pricePizza();
                break;
            case R.id.rbMedium:
                if (checked)
                    customPizza.txtRBSIZE.setText(customPizza.rbMedium.getText().toString());
                sizePizza = "Medium";
                priceSize = 55.0;
                pricePizza();
                break;
            case R.id.rbLarge:
                if (checked)
                    customPizza.txtRBSIZE.setText(customPizza.rbLarge.getText().toString());
                sizePizza = "Large";
                priceSize = 70.0;
                pricePizza();
                break;
        }
    }

    private void getFreeToppings(){
        freeToppingsList.addAll(DataToppings.getListData());
        adapterFreeTopping = new AdapterFreeTopping(freeToppingsList, getBaseContext());
        customPizza.rvFreeTopping.setAdapter(adapterFreeTopping);
        adapterFreeTopping.ActionClick(new AdapterFreeTopping.onAction() {
            @Override
            public void onActionClick(View view, int position) {
                customPizza.txtExTop.setText(extraToppingsList.get(position).getNamaTopping());
                String extra = customPizza.txtExTop.getText().toString();
//                Toast.makeText(context, extra, Toast.LENGTH_SHORT).show();
                onCheckedFreeToppings(position, extra);
            }
        });
    }

    public void onCheckedFreeToppings(int position, String extra){
//        if (isChecked && freeToppings >= 4) {
//            customPizza.checkBox.setChecked(false);
//            Toast.makeText(CustomPizzaActivity.this, "Maximum Toppings reached", Toast.LENGTH_SHORT).show();
//        } else {
//            if (isChecked) {
//                freeToppings++;
//                myFreeToppings.add(customPizza.checkBox.getText().toString());
//                priceToppings = priceToppings + 5;
//            } else {
//                freeToppings--;
//                myFreeToppings.remove(customPizza.checkBox.getText().toString());
//                priceToppings = priceToppings - 5;
//            }
//        }
        if (!freeToppingsList.get(position).clickedTopping() && myFreeToppings.size() >= 4){
            Toast.makeText(context, "Max Reached", Toast.LENGTH_SHORT).show();

        } else {
            if (freeToppingsList.get(position).clickedTopping()) {
//                    customPizza.btnOrder.setText(extraToppingsList.get(position).getHargaTopping());
//                    myExtraToppings.remove(Integer.parseInt(String.valueOf(customPizza.txtExTop.getText())));
//                    Toast.makeText(context, "off" + myExtraToppings.toString(), Toast.LENGTH_SHORT).show();
                //beda
//            customPizza.btnOrder.setText(extraToppingsList.get(position).getHargaTopping());
//            myExtraToppings.remove(Integer.parseInt(String.valueOf(customPizza.btnOrder.getText())));
//            Toast.makeText(context, myExtraToppings.toString(), Toast.LENGTH_SHORT).show();
//            freeToppingsList.get(position).clickedTopping();
//            Toast.makeText(context, "max toppings reached", Toast.LENGTH_SHORT).show();
                myFreeToppings.remove(extra);
                Toast.makeText(context, myFreeToppings.toString(), Toast.LENGTH_SHORT).show();
            } else {
                myFreeToppings.add(extra);
                Toast.makeText(context, myFreeToppings.toString(), Toast.LENGTH_SHORT).show();
//            if (!freeToppingsList.get(position).clickedTopping()){
//                freeToppings ++;
//            } else {
//                freeToppings --;
//            }
//            customPizza.btnOrder.setText(extraToppingsList.get(position).getHargaTopping());
//            myExtraToppings.add(Integer.parseInt(String.valueOf(customPizza.btnOrder.getText())));
            }
        }
    }

    private void getExtraToppings() {
        extraToppingsList.addAll(DataToppings.getListData());
        adapterTopping = new AdapterTopping(extraToppingsList, getBaseContext());
        customPizza.rvExtraTopping.setAdapter(adapterTopping);
        adapterTopping.ActionClick(new AdapterTopping.onAction() {
            @Override
            public void onActionClick(View view, int position) {
                customPizza.txtExTop.setText(extraToppingsList.get(position).getHargaTopping());
                String extra = customPizza.txtExTop.getText().toString();
                String namaExtra = extraToppingsList.get(position).getNamaTopping();
//                Toast.makeText(context, extra, Toast.LENGTH_SHORT).show();
                onCheckedExtraToppings(position, extra, namaExtra);
            }
        });
    }

    public void onCheckedExtraToppings(int position, String extra, String namaExtra){
        if (extraToppingsList.get(position).clickedTopping()){
//                    customPizza.btnOrder.setText(extraToppingsList.get(position).getHargaTopping());
//                    myExtraToppings.remove(Integer.parseInt(String.valueOf(customPizza.txtExTop.getText())));
//                    Toast.makeText(context, "off" + myExtraToppings.toString(), Toast.LENGTH_SHORT).show();
            //beda
//            customPizza.btnOrder.setText(extraToppingsList.get(position).getHargaTopping());
//            myExtraToppings.remove(Integer.parseInt(String.valueOf(customPizza.btnOrder.getText())));
            myExtraPriceToppings.remove(extra);
            myExtraNameToppings.remove(namaExtra);
        } else {
            myExtraPriceToppings.add(extra);
            myExtraNameToppings.add(namaExtra);
        }
        double[] doubleList = new double[myExtraPriceToppings.size()];
        double sum = 0;
        for (int i = 0; i < myExtraPriceToppings.size(); ++i) {
            doubleList[i] = Double.parseDouble(myExtraPriceToppings.get(i));
            sum += doubleList[i];
        }
        extraPriceToppings = sum;
        pricePizza();
    }

    public void pricePizza(){
        double dbl_priceTotal = priceSize + extraPriceToppings;
        String str_priceTotal = String.valueOf(dbl_priceTotal);
        customPizza.txtSubTotal.setText("Rp. "+str_priceTotal+",-");
//        Toast.makeText(context, "Ukuran Pizza : " +  sizePizza +"\n Total Price : " + str_priceTotal, Toast.LENGTH_SHORT).show();
    }

    private void checkOut(){
        Intent i = new Intent(this, DetailOrderActivity.class);
        i.putExtra("cstPizza", "Custom Pizza ("+sizePizza+")");
        i.putExtra("cstPrice", String.valueOf(priceSize));
        i.putExtra("cstFreeTop", myFreeToppings.toString());
        i.putExtra("cstTotal", customPizza.txtSubTotal.getText().toString());
        i.putStringArrayListExtra("cstExtraPrice", myExtraPriceToppings);
        i.putStringArrayListExtra("cstExtraName", myExtraNameToppings);
        startActivity(i);
    }

}













