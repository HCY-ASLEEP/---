package com.example.final_work;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class ForceOffLineReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("警告");
        builder.setMessage("你现在已经被强制下线，请重新登录，并且打开设备的网络连接");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                ActivityAdministrator.finishAll();
                Intent intent=new Intent(context,InitialEnterActivity.class);
                context.startActivity(intent);
            }
        });
        builder.show();
    }
}