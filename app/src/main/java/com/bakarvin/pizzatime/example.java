package com.bakarvin.pizzatime;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bakarvin.pizzatime.Data.DBsqlite.SqliteManager;
import com.bakarvin.pizzatime.View.Ui.Fragment.DialogDetailTransaksiFragment;
import com.bakarvin.pizzatime.View.Ui.MainActivity;
import com.bakarvin.pizzatime.View.Ui.ShoppingCartActivity;
import com.bakarvin.pizzatime.databinding.DialogContinueShopBinding;
import com.bakarvin.pizzatime.databinding.DialogRateFoodBinding;
import com.bakarvin.pizzatime.databinding.ExampleBinding;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class example extends AppCompatActivity {
    ExampleBinding exampleBinding;

    private static final long START_TIME_IN_MILLIS = 300000;
    public CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mEndTime;
    String id_trans;
    Context context;
    ArrayList<String> myExtraPrice = new ArrayList<String>();
    ArrayList<String> myExtraName = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exampleBinding = ExampleBinding.inflate(getLayoutInflater());
        setContentView(exampleBinding.getRoot());
        context = example.this;
        startTimer();
        getSetIntentData();

    }

    private void getSetIntentData() {
        id_trans = getIntent().getStringExtra("id_trans");
        String tgl_trans = getIntent().getStringExtra("tgl_trans");
        String total_trans = getIntent().getStringExtra("total_trans");
        String alamat_user = getIntent().getStringExtra("alamat_user");
        String time_trans = getIntent().getStringExtra("time_trans");
        exampleBinding.txtTotalPrice.setText(total_trans);
        exampleBinding.cardSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogDetailTransaksiFragment dialogFragment = new DialogDetailTransaksiFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id_trans",id_trans);
                bundle.putString("tgl_trans",tgl_trans);
                bundle.putString("total_trans",total_trans);
                bundle.putString("alamat_user",alamat_user);
                dialogFragment.setArguments(bundle);
                dialogFragment.show(getSupportFragmentManager(),dialogFragment.getTag());
            }
        });
        estTime(time_trans);
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
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setView(dialogBinding.getRoot());
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialogBinding.cardComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
                startActivity(new Intent(context, MainActivity.class));
            }
        });
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        exampleBinding.txtTimer.setText(timeLeftFormatted);
    }

    private void estTime(String time_trans){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDate = new SimpleDateFormat("hh:mm");
        String time = time_trans;
        try {
            Date date = simpleDate.parse(time);
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, 5);
            String result = simpleDate.format(calendar.getTime());
            exampleBinding.txtESTtime.setText(result);
        } catch (Exception e){

        }
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
        Intent resultIntent = new Intent(this, SpecialIntentActivity.class);
        resultIntent.putExtra("id_trans", id_trans);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Your PIZZA has Arrived!!!")
                .setContentText("Prepare your tummy because your pizza is ready")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(notifyPendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0,builder.build());
    }

}
