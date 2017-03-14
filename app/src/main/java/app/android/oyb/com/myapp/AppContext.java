package app.android.oyb.com.myapp;

import android.app.Application;
import android.content.Intent;

import app.android.oyb.com.myapp.manager.OkHttpManager;
import app.android.oyb.com.myapp.service.WeatherService;

/**
 * Created by O on 2017/2/23.
 */

public class AppContext extends Application {

    private static AppContext mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        // OkGo初始化
        OkHttpManager.init(this);
        // 开启服务-定位
        startService(new Intent(this, WeatherService.class));
    }

    public static AppContext getInstance() {
        return mInstance;
    }

}
