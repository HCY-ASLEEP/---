package com.example.final_work;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetWorkChangeReceiver extends BroadcastReceiver {
    private final MainActivity mainActivity;

    public NetWorkChangeReceiver(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isAvailable()) {
            Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "network is not available", Toast.LENGTH_SHORT).show();
            Intent intent1=new Intent(mainActivity,MyService.class);
            mainActivity.startService(intent1);
        }
    }
}