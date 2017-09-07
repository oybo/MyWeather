package app.android.oyb.com.myapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by O on 2017/3/14.
 */

public class WeatherService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
