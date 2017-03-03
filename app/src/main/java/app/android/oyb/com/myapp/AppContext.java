package app.android.oyb.com.myapp;

import android.app.Application;

import app.android.oyb.com.myapp.manager.OkHttpManager;

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
    }

    public static AppContext getInstance() {
        return mInstance;
    }

}
