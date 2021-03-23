package com.bakarvin.pizzatime;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bakarvin.pizzatime.databinding.ActivityCustomPizzaBinding;
import com.bakarvin.pizzatime.databinding.ItemOrderSummaryBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class CustomPizzaActivity extends AppCompatActivity {

    ActivityCustomPizzaBinding customPizza;
    int freeToppings = 0;
    private Dialog myDialog;
    String sizePizza;
    int priceSize;
    int priceToppings;
    ArrayList<String> myFreeToppings = new ArrayList<String>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customPizza = ActivityCustomPizzaBinding.inflate(getLayoutInflater());
        setContentView(customPizza.getRoot());
        priceToppings = 0;
        context = this.getApplicationContext();
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
                startActivity(new Intent(getApplicationContext(), TimerOrderActivity.class));
            }
        });
    }

    public void onButtonClicked(View v){
        boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()){
            case R.id.rbPersonal:
                if (checked)
                    customPizza.txtRBSIZE.setText(customPizza.rbPersonal.getText().toString());
                    sizePizza = "Personal";
                    priceSize = 5;
                    break;
            case R.id.rbMedium:
                if (checked)
                    customPizza.txtRBSIZE.setText(customPizza.rbMedium.getText().toString());
                    sizePizza = "Medium";
                    priceSize = 10;
                    break;
            case R.id.rbLarge:
                if (checked)
                    customPizza.txtRBSIZE.setText(customPizza.rbLarge.getText().toString());
                    sizePizza = "Large";
                    priceSize = 15;
                    break;
        }
    }
    
     public void pricePizza(){
        int priceTotal = priceSize + priceToppings;
        Toast.makeText(context, "Ukuran Pizza : " +  sizePizza +"\n Total Price : " + priceTotal, Toast.LENGTH_SHORT).show();
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
}