package com.example.myth.task3h;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    private static MainActivity ins;
    private Button b3h, ba;
    private TextView tv;
    private String s3;
    private MediaPlayer mp;
    AlarmManager am;
    PendingIntent pi;

    public static MainActivity instance() {
        if (ins == null)
            ins = new MainActivity();
        return ins;
    }
    @Override
    public void onStart() {
        super.onStart();
        ins = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(getApplicationContext(), Alarm.class);
        pi = PendingIntent.getBroadcast(getApplicationContext(), 0, i, PendingIntent.FLAG_IMMUTABLE);

        ins = this;
        m3h();
        ma();
        text();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void ma() {
        ba = findViewById(R.id.ba);
        ba.setOnClickListener(v -> {
            Log.d("TAG", "ba clicked");
            MainActivity.instance().setTitle("AUTOMATIC");
            b3h.setClickable(false);
            ba.setClickable(false);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a", Locale.getDefault());
            Date d1 = new Date(System.currentTimeMillis());
            Date d2 = new Date(System.currentTimeMillis() + 10_800_000);
            Date d3 = new Date(System.currentTimeMillis() + 21_600_000);
            Date d4 = new Date(System.currentTimeMillis() + 32_400_000);
            Date d5 = new Date(System.currentTimeMillis() + 43_200_000);
            String s1 = sdf.format(d1);
            String s2 = sdf.format(d2);
            String s4 = sdf.format(d3);
            String s5 = sdf.format(d4);
            String s6 = sdf.format(d5);
            String s = "\nLook at schedule.\n" + "Start time: " + s1 + "\nEnd time: " + s2
                    + "\n" + "\nTask 1: completed.\n" + "Task 2: " + s2 + "\nTask 3: "
                    + s4 + "\nTask 4: " + s5 + "\nTask 5: " + s6;
            tv.setText(s);
            int in = 10_740_000; // 3h
            //int in = 10000;
            am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10_740_000, in, pi);
            Toast.makeText(MainActivity.this, "Alarm Set", Toast.LENGTH_SHORT).show();
        });
    }

    private void m3h() {
        b3h = findViewById(R.id.b3h);
        b3h.setOnClickListener(v -> {
            MainActivity.instance().setTitle("3 HOUR");
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a", Locale.getDefault());
            Date d1 = new Date(System.currentTimeMillis());
            String s1 = sdf.format(d1);
            Date d2 = new Date(System.currentTimeMillis() + 10_800_000);
            String s2 = sdf.format(d2);
            s3 = "\12Start time: " + s1 + "\12End time: " + s2;
            tv.setText(s3);
            ba.setClickable(false);
            b3h.setClickable(false);
            int in = 10_800_000; // 3h
//            int in = 20_000;
            am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + in, pi);
            Toast.makeText(MainActivity.this, "Alarm Set", Toast.LENGTH_SHORT).show();
        });
    }

    private void text() {
        tv = findViewById(R.id.text);
        Date d = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MM-yyyy", Locale.getDefault());
        String s = sdf.format(d);
        String s2 = "\n" + s;
        tv.setText(s2);
    }

    public void updateText(final String t) {
        MainActivity.this.runOnUiThread(() -> tv.setText(t));
    }

    void sound() {
        MainActivity.this.runOnUiThread(() -> {
            mp = MediaPlayer.create(MainActivity.this, R.raw.alarm);
            mp.start();
        });
    }

//    protected void onPause() {
//        super.onPause();
//        if (mp != null) {
//            mp.release();
//            mp = null;
//        }
//    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Are you sure you want to exit?");
        adb.setCancelable(false);
        adb.setMessage("Timer will be stopped.");
        adb.setPositiveButton("OK", (dialog, which) -> MainActivity.this.finish());
        adb.setNegativeButton("Cancel", null);
        adb.show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu) {
            text();
            try {
                if (am != null) {
                    am.cancel(pi);
                    Toast.makeText(this, "Alarm Canceled!", Toast.LENGTH_SHORT).show();
                }
                b3h.setClickable(true);
                ba.setClickable(true);
                MainActivity.instance().setTitle("Task3H");
                Alarm.ir = 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (item.getItemId() == R.id.menuA) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setPositiveButton("OK", null);
            adb.setTitle("Timer v1.6");
            adb.setMessage("Development: Stark C.");
            AlertDialog ad = adb.create();
            ad.show();
        }
        return super.onOptionsItemSelected(item);
    }
}

