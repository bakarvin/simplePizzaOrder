package com.bakarvin.pizzatime;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bakarvin.pizzatime.Adapter.AdapterTopping;
import com.bakarvin.pizzatime.Model.ModelTopping;
import com.bakarvin.pizzatime.databinding.ActivityCustomPizzaBinding;
import com.bakarvin.pizzatime.databinding.ItemOrderSummaryBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class CustomPizzaActivity extends AppCompatActivity {

    ActivityCustomPizzaBinding customPizza;
    int freeToppings = 0;
    private Dialog myDialog;
    String sizePizza;
    double priceSize;
    int priceToppings;
    double extraPriceToppings;
    ArrayList<String> myFreeToppings = new ArrayList<String>();
    ArrayList<String> myExtraToppings = new ArrayList<String>();
    ArrayList<ModelTopping> extraToppingsList = new ArrayList<>();
    AdapterTopping adapterTopping;
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
        customPizza.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && freeToppings >= 4) {
                    customPizza.checkBox.setChecked(false);
                    Toast.makeText(CustomPizzaActivity.this, "Maximum Toppings reached", Toast.LENGTH_SHORT).show();
                } else {
                    if (isChecked) {
                        freeToppings++;
                        myFreeToppings.add(customPizza.checkBox.getText().toString());
                        priceToppings = priceToppings + 5;
                    } else {
                        freeToppings--;
                        myFreeToppings.remove(customPizza.checkBox.getText().toString());
                        priceToppings = priceToppings - 5;
                    }
                }
            }
        });
        customPizza.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && freeToppings >= 4) {
                    customPizza.checkBox2.setChecked(false);
                    Toast.makeText(CustomPizzaActivity.this, "Maximum Toppings reached", Toast.LENGTH_SHORT).show();
                } else {
                    if (isChecked) {
                        freeToppings++;
                        myFreeToppings.add(customPizza.checkBox2.getText().toString());
                        priceToppings = priceToppings + 5;
                    } else {
                        freeToppings--;
                        myFreeToppings.remove(customPizza.checkBox2.getText().toString());
                        priceToppings = priceToppings - 5;
                    }
                }
            }
        });
        customPizza.checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && freeToppings >= 4) {
                    customPizza.checkBox3.setChecked(false);
                    Toast.makeText(CustomPizzaActivity.this, "Maximum Toppings reached", Toast.LENGTH_SHORT).show();
                } else {
                    if (isChecked) {
                        freeToppings++;
                        myFreeToppings.add(customPizza.checkBox3.getText().toString());
                        priceToppings = priceToppings + 5;
                    } else {
                        freeToppings--;
                        myFreeToppings.remove(customPizza.checkBox3.getText().toString());
                        priceToppings = priceToppings - 5;
                    }
                }
            }
        });
        customPizza.checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && freeToppings >= 4) {
                    customPizza.checkBox4.setChecked(false);
                    Toast.makeText(CustomPizzaActivity.this, "Maximum Toppings reached", Toast.LENGTH_SHORT).show();
                } else {
                    if (isChecked) {
                        freeToppings++;
                        myFreeToppings.add(customPizza.checkBox4.getText().toString());
                        priceToppings = priceToppings + 5;
                    } else {
                        freeToppings--;
                        myFreeToppings.remove(customPizza.checkBox4.getText().toString());
                        priceToppings = priceToppings - 5;
                    }
                }
            }
        });
        customPizza.checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && freeToppings >= 4) {
                    customPizza.checkBox5.setChecked(false);
                    Toast.makeText(CustomPizzaActivity.this, "Maximum Toppings reached", Toast.LENGTH_SHORT).show();
                } else {
                    if (isChecked) {
                        freeToppings++;
                        myFreeToppings.add(customPizza.checkBox5.getText().toString());
                        priceToppings = priceToppings + 5;
                    } else {
                        freeToppings--;
                        myFreeToppings.remove(customPizza.checkBox5.getText().toString());
                        priceToppings = priceToppings - 5;
                    }
                }
            }
        });
        customPizza.checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && freeToppings >= 4) {
                    customPizza.checkBox6.setChecked(false);
                    Toast.makeText(CustomPizzaActivity.this, "Maximum Toppings reached", Toast.LENGTH_SHORT).show();
                } else {
                    if (isChecked) {
                        freeToppings++;
                        myFreeToppings.add(customPizza.checkBox6.getText().toString());
                        priceToppings = priceToppings + 5;
                    } else {
                        freeToppings--;
                        myFreeToppings.remove(customPizza.checkBox6.getText().toString());
                        priceToppings = priceToppings - 5;
                    }
                }
            }
        });
        customPizza.checkBox7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && freeToppings >= 4) {
                    customPizza.checkBox7.setChecked(false);
                    Toast.makeText(CustomPizzaActivity.this, "Maximum Toppings reached", Toast.LENGTH_SHORT).show();
                } else {
                    if (isChecked) {
                        freeToppings++;
                        myFreeToppings.add(customPizza.checkBox7.getText().toString());
                        priceToppings = priceToppings + 5;
                    } else {
                        freeToppings--;
                        myFreeToppings.remove(customPizza.checkBox7.getText().toString());
                        priceToppings = priceToppings - 5;
                    }
                }
            }
        });
        customPizza.checkBox8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && freeToppings >= 4) {
                    customPizza.checkBox8.setChecked(false);
                    Toast.makeText(CustomPizzaActivity.this, "Maximum Toppings reached", Toast.LENGTH_SHORT).show();
                } else {
                    if (isChecked) {
                        freeToppings++;
                        myFreeToppings.add(customPizza.checkBox8.getText().toString());
                        priceToppings = priceToppings + 5;
                    } else {
                        freeToppings--;
                        myFreeToppings.remove(customPizza.checkBox8.getText().toString());
                        priceToppings = priceToppings - 5;
                    }
                }
            }
        });
        customPizza.checkBox9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && freeToppings >= 4) {
                    customPizza.checkBox9.setChecked(false);
                    Toast.makeText(CustomPizzaActivity.this, "Maximum Toppings reached", Toast.LENGTH_SHORT).show();
                } else {
                    if (isChecked) {
                        freeToppings++;
                        myFreeToppings.add(customPizza.checkBox9.getText().toString());
                        priceToppings = priceToppings + 5;  
                    } else {
                        freeToppings--;
                        myFreeToppings.remove(customPizza.checkBox9.getText().toString());
                        priceToppings = priceToppings - 5;
                    }
                }
            }
        });
        customPizza.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), TimerOrderActivity.class));
            }
        });
//        customPizza.itemCardOutter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                itemCard();
//            }
//        });
        getFreeToppings();
        getExtraToppings();
    }

    private void getFreeToppings(){

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
//                Toast.makeText(context, extra, Toast.LENGTH_SHORT).show();
                onChecked(position, extra);
            }
        });
    }

    public void onChecked(int position, String extra){
        if (extraToppingsList.get(position).clickedTopping()){
//                    customPizza.btnOrder.setText(extraToppingsList.get(position).getHargaTopping());
//                    myExtraToppings.remove(Integer.parseInt(String.valueOf(customPizza.txtExTop.getText())));
//                    Toast.makeText(context, "off" + myExtraToppings.toString(), Toast.LENGTH_SHORT).show();
            //beda
//            customPizza.btnOrder.setText(extraToppingsList.get(position).getHargaTopping());
//            myExtraToppings.remove(Integer.parseInt(String.valueOf(customPizza.btnOrder.getText())));
//            Toast.makeText(context, myExtraToppings.toString(), Toast.LENGTH_SHORT).show();
            myExtraToppings.remove(extra);
        } else {
            myExtraToppings.add(extra);
//            customPizza.btnOrder.setText(extraToppingsList.get(position).getHargaTopping());
//            myExtraToppings.add(Integer.parseInt(String.valueOf(customPizza.btnOrder.getText())));
        }
        double[] doubleList = new double[myExtraToppings.size()];
        double sum = 0;
        for (int i = 0; i < myExtraToppings.size(); ++i) {
            doubleList[i] = Double.parseDouble(myExtraToppings.get(i));
            sum += doubleList[i];
        }
        extraPriceToppings = sum;
        pricePizza();
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

    public void pricePizza(){
        double dbl_priceTotal = priceSize + extraPriceToppings;
        String str_priceTotal = String.valueOf(dbl_priceTotal);
        customPizza.txtSubTotal.setText("Rp. "+str_priceTotal+",-");
//        Toast.makeText(context, "Ukuran Pizza : " +  sizePizza +"\n Total Price : " + str_priceTotal, Toast.LENGTH_SHORT).show();
    }

    private void openFilter(){
        AlertDialog builder = new AlertDialog.Builder(context).create();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ItemOrderSummaryBinding orderSummary = ItemOrderSummaryBinding.inflate(layoutInflater);
        builder.setView(orderSummary.getRoot());
        orderSummary.txtSizePizza.setText("Custom Pizza : " + sizePizza + " Size");
//        orderSummary.txtFreeTop.setText(myFreeToppings.toString());
        builder.show();
    }

//    public void itemCard(){
//        ColorStateList background = customPizza.itemCardOutter.getCardBackgroundColor();
//        if(background == getResources().getColorStateList(R.color.white)) {
//            customPizza.itemCardOutter.setCardBackgroundColor(getResources().getColor(R.color.black));
//        } else {
//            customPizza.itemCardOutter.setCardBackgroundColor(getResources().getColor(R.color.white));
//        }
//    }


}













