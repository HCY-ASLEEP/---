package com.example.final_work;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Intent intent=new Intent("my.ForceOffLine");
        sendBroadcast(intent);
        stopSelf();
    }
}