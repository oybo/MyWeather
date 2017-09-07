package app.android.oyb.com.myapp.mvp.weather;

import android.content.Context;

import app.android.oyb.com.myapp.bean.Weather;
import app.android.oyb.com.myapp.mvp.BasePresenter;
import app.android.oyb.com.myapp.mvp.BaseView;

/**
 * Created by O on 2017/4/7.
 */

public interface WeatherContract {

    interface View extends BaseView<Presenter> {

        Context _getContext();

        /**
         * 天气回调
         */
        void onWeatherChanged(Weather weather);
    }

    interface Presenter extends BasePresenter {
        /**
         * 去请求天气
         */
        void getWeather(Weather weather);

        /**
         * 开始请求定位
         */
        void startLocation();

        /**
         * 停止定位
         */
        void stopLocation();

        /**
         * 注销定位
         */
        void destoreLocation();

        String getPmPower(String pm2_5);
    }

}
