package com.bakarvin.pizzatime.View.Ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bakarvin.pizzatime.Api.ConfigRetrofit;
import com.bakarvin.pizzatime.Data.DataLokal.DataToppings;
import com.bakarvin.pizzatime.ListAlamatDialogActivity;
import com.bakarvin.pizzatime.Model.CustomTransaksi.CustomTransaksiResponse;
import com.bakarvin.pizzatime.Model.CustomTransaksi.ModelCustomTransaksi;
import com.bakarvin.pizzatime.Model.Transaksi.TransaksiResponse;
import com.bakarvin.pizzatime.Preferences;
import com.bakarvin.pizzatime.R;
import com.bakarvin.pizzatime.View.Adapter.AdapterFreeTopping;
import com.bakarvin.pizzatime.View.Adapter.AdapterTopping;
import com.bakarvin.pizzatime.Model.ModelTopping;
import com.bakarvin.pizzatime.databinding.ActivityCustomPizzaBinding;
import com.bakarvin.pizzatime.databinding.DialogAddAlamatBinding;
import com.bakarvin.pizzatime.example;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CustomPizzaActivity extends AppCompatActivity {

    ActivityCustomPizzaBinding customPizza;
    int freeToppings = 0;
    String sizePizza;
    String str_priceTotal;
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
        context = CustomPizzaActivity.this;
        customPizza.txtSubTotal.setText("Rp. "+"0"+",-");
        customPizza.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(CustomPizzaActivity.this, example.class));
                checkOut();
            }
        });
        customPizza.txtAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ListAlamatDialogActivity.class));
            }
        });
        getAlamat();
        getFreeToppings();
        getExtraToppings();
    }

    private void getAlamat() {
        if (!Preferences.getAlamatUserSingkat(context).isEmpty()){
            customPizza.txtAlamat.setText(Preferences.getAlamatUserSingkat(context));
        } else {
            customPizza.txtAlamat.setText("Kos - alamat lengkap, kode pos");
        }
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
        NumberFormat format = new DecimalFormat("0.#");
        str_priceTotal = String.valueOf(format.format(dbl_priceTotal));
        customPizza.txtSubTotal.setText("Rp. "+str_priceTotal+",-");
//        Toast.makeText(context, "Ukuran Pizza : " +  sizePizza +"\n Total Price : " + str_priceTotal, Toast.LENGTH_SHORT).show();
    }

    private void checkOut(){
        String uname = Preferences.getLoginUname(context);
        final String tgl_trans_db = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        final String tgl_trans = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        final String id_trans = "customx"+uname+tgl_trans;
        final String total_trans = str_priceTotal;
        final String free_topping = myFreeToppings.toString();
        final String extra_topping = myExtraNameToppings.toString();
        String alamat_user = "";


        if (customPizza.txtRBSIZE.getText().equals("")){
            Toast.makeText(context, "Belum pilih ukuran pizza", Toast.LENGTH_SHORT).show();
        } else {
            if (Preferences.getAlamatUserLengkap(context).isEmpty()){
                dialogAddAlamat();
            } else {
                alamat_user = Preferences.getAlamatUserLengkap(context);
                Toast.makeText(context, id_trans + " " + uname + " " + tgl_trans + " " + total_trans + " " + alamat_user, Toast.LENGTH_SHORT).show();
                ConfigRetrofit.service.insertTransaksi(id_trans,uname,tgl_trans_db,total_trans,alamat_user).enqueue(new Callback<TransaksiResponse>() {
                    @Override
                    public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                        int kode = response.body().getKode();
                        Toast.makeText(context, String.valueOf(kode), Toast.LENGTH_SHORT).show();
                        if (kode == 1){
                            insertCustomTransaksi(id_trans, sizePizza, free_topping, extra_topping, total_trans);
                        } else {
                            Toast.makeText(context, "Transaksi Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                        Log.d("Server Error", t.getMessage());
                    }
                });
            }
        }
    }

    private void insertCustomTransaksi(String id_trans, String sizePizza, String free_topping, String extra_topping, String total_trans){
        ConfigRetrofit.service.insertCustomTransaksi(id_trans, sizePizza, free_topping, extra_topping, total_trans).enqueue(new Callback<CustomTransaksiResponse>() {
            @Override
            public void onResponse(Call<CustomTransaksiResponse> call, Response<CustomTransaksiResponse> response) {
                int kode = response.body().getKode();
                if (kode == 1){
                    Toast.makeText(context, "BERHASIL", Toast.LENGTH_SHORT).show();

//                    Intent i = new Intent(context, example.class);
//                    i.putExtra("kode", 1);
//                    i.putExtra("cstPizza", "Custom Pizza ("+sizePizza+")");
//                    i.putExtra("cstPrice", String.valueOf(priceSize));
//                    i.putExtra("cstFreeTop", myFreeToppings.toString());
//                    i.putExtra("cstTotal", customPizza.txtSubTotal.getText().toString());
//                    i.putStringArrayListExtra("cstExtraPrice", myExtraPriceToppings);
//                    i.putStringArrayListExtra("cstExtraName", myExtraNameToppings);
//                    startActivity(i);
                } else {
                    Toast.makeText(context, "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomTransaksiResponse> call, Throwable t) {
                Log.d("Server Error", t.getMessage());
            }
        });
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

}













