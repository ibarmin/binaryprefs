package com.ironz.binaryprefs.sample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.ironz.binaryprefs.BinaryPreferences;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RequestService extends Service {

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        final BinaryPreferences preferences = App.getPreferences();

        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                preferences.edit().putLong("nano_time", System.nanoTime()).apply();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}