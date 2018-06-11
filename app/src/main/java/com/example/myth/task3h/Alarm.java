package com.example.myth.task3h;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Alarm extends BroadcastReceiver {
    static int ir = 1;

    public void onReceive(Context c, Intent i) {
        Toast.makeText(c, "Timing completed!", Toast.LENGTH_LONG).show();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a", Locale.getDefault());
        Date d = new Date(System.currentTimeMillis());
        String s1 = sdf.format(d);
        Date d2 = new Date(System.currentTimeMillis() + 10_800_000);
        String s2 = sdf.format(d2);
        String s3 = "\nStart time: " + s1 + "\nEnd time: " + s2;
        String s = "\nTiming completed!\nUse reset for new timing." + s3;

        Date d1 = new Date(System.currentTimeMillis() + 10_800_000);
        Date d3 = new Date(System.currentTimeMillis() + 21_600_000);
        Date d4 = new Date(System.currentTimeMillis() + 32_400_000);
        //Date d5 = new Date(System.currentTimeMillis() + 43_200_000);
        String s8 = sdf.format(d1);
        String s4 = sdf.format(d3);
        String s5 = sdf.format(d4);
        //String s6 = sdf.format(d5);
        String s7 = "\nLook at schedule.\n" + "Start time: " + s1 + "\12End time: " + s8
                + "\n" + "\nTask 1: completed.\n" + "Task 2: completed.\n" + "Task 3: "
                + s8 + "\nTask 4: " + s4 + "\nTask 5: " + s5;
        String s9 = "\nLook at schedule.\n" + "Start time: " + s1 + "\nEnd time: " + s8
                + "\n" + "\nTask 1: completed.\n" + "Task 2: completed.\n" + "Task 3: completed.\n"
                + "Task 4: " + s8 + "\nTask 5: " + s4;
        String s10 = "\nLook at schedule.\n" + "Start time: " + s1 + "\nEnd time: " + s8
                + "\n" + "\nTask 1: completed.\n" + "Task 2: completed.\n" + "Task 3: completed.\n"
                + "Task 4: completed." + "\nTask 5: " + s8;
        String s11 = "\nStatus: Final timing for today!";

        //  public void foo(View v){
        //      if (v.getId() == R.id.yourButton) {

//        Button ba = (Button)MainActivity.getInstance().findViewById(R.id.ba);
//        Button b3h = (Button)MainActivity.getInstance().findViewById(R.id.b3h);
//        if (ba != null && ba.isPressed()) {
        if (MainActivity.instance().getTitle() == "AUTOMATIC") {
            if (ir == 1) {
                try {
                    MainActivity.instance().updateText(s7);
                    ir++;
                    try {
                        MainActivity.instance().sound();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ir == 2) {
                try {
                    MainActivity.instance().updateText(s9);
                    ir++;
                    try {
                        MainActivity.instance().sound();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ir == 3) {
                try {
                    MainActivity.instance().updateText(s10);
                    ir++;
                    try {
                        MainActivity.instance().sound();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ir == 4) {
                try {
                    MainActivity.instance().updateText(s11);
                    try {
                        if (MainActivity.instance().am != null)
                            MainActivity.instance().am.cancel(MainActivity.instance().pi);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        MainActivity.instance().sound();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//        } else if (b3h != null && b3h.isPressed()) {
        } else if (MainActivity.instance().getTitle() == "3 HOUR") {
            try {
                MainActivity.instance().updateText(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            MainActivity.instance().sound();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("myth", DateFormat.getDateTimeInstance().format(new Date()));
    }
}
