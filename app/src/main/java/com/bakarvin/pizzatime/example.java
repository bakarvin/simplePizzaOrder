package com.bakarvin.pizzatime;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bakarvin.pizzatime.Data.DBsqlite.SqliteManager;
import com.bakarvin.pizzatime.View.Ui.ShoppingCartActivity;
import com.bakarvin.pizzatime.databinding.DialogContinueShopBinding;
import com.bakarvin.pizzatime.databinding.DialogRateFoodBinding;
import com.bakarvin.pizzatime.databinding.ExampleBinding;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class example extends AppCompatActivity {
    ExampleBinding exampleBinding;

    private static final long START_TIME_IN_MILLIS = 60000;
    public CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mEndTime;
    Context context;
    SqliteManager sqliteManager;

    ArrayList<String> myExtraPrice = new ArrayList<String>();
    ArrayList<String> myExtraName = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exampleBinding = ExampleBinding.inflate(getLayoutInflater());
        setContentView(exampleBinding.getRoot());
        context = example.this;
        sqliteManager = new SqliteManager(context);
        startTimer();
        getIntentData();
    }

    private void getIntentData() {
        int kode = getIntent().getIntExtra("kode", 1);
        if (kode == 1){
            exampleBinding.txtCustomPizza.setText(getIntent().getStringExtra("cstPizza"));
            exampleBinding.txtBasePrice.setText(getIntent().getStringExtra("cstPrice"));
            exampleBinding.txtFreeTop.setText(getIntent().getStringExtra("cstFreeTop"));
            exampleBinding.txtTotalPrice.setText(getIntent().getStringExtra("cstTotal"));
            myExtraName = getIntent().getStringArrayListExtra("cstExtraName");
            myExtraPrice = getIntent().getStringArrayListExtra("cstExtraPrice");
            showMap(myExtraName,myExtraPrice);
        } else {
            exampleBinding.txtTotalPrice.setText(getIntent().getStringExtra("cstTotal"));
        }
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
        exampleBinding.listExtraTop.setAdapter(simpleAdapter);
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                exampleBinding.txtTimer.setText("00:00");
                createDialog();
                createNotif();
            }
        }.start();
        mTimerRunning = true;
    }

    private void createDialog() {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        DialogRateFoodBinding dialogBinding = DialogRateFoodBinding.inflate(layoutInflater);
        dialog.setView(dialogBinding.getRoot());
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialogBinding.lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqliteManager.open();
                dialog.dismiss();
                sqliteManager.deleteAll();
                sqliteManager.close();
            }
        });
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        exampleBinding.txtTimer.setText(timeLeftFormatted);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        mTimeLeftInMillis = prefs.getLong("millisLeft", START_TIME_IN_MILLIS);
        mTimerRunning = prefs.getBoolean("timerRunning", false);
        updateCountDownText();
        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
                updateCountDownText();
            } else {
                startTimer();
            }
        }
    }

    public void createNotif(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Your PIZZA is ready!!!")
                .setContentText("Prepare your tummy because your pizza is ready")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }


}
