package app.android.oyb.com.myapp.mvp.weather;

import app.android.oyb.com.myapp.bean.Weather;
import app.android.oyb.com.myapp.callback.OnLocationCallback;

/**
 * Created by O on 2017/4/7.
 */

public class WeatherPresenter implements WeatherContract.Presenter, OnLocationCallback {

    private WeatherContract.View mView;
    private WeatherModel mModel;

    public WeatherPresenter(WeatherContract.View view) {
        mView = view;
        mModel = new WeatherModel(view._getContext());
        mView.setPresenter(this);
        mModel.setLocationListener(this);
    }

    @Override
    public void getWeather(Weather weather) {
        mModel.getWeather(mView._getContext(), weather);
    }

    @Override
    public void startLocation() {
        mModel.startLocation();
    }

    @Override
    public void stopLocation() {
        mModel.stopLocation();
    }

    @Override
    public void destoreLocation() {
        mModel.destoreLocation();
    }

    @Override
    public void onLocation(Weather weather) {
        if(mView != null) {
            mView.onWeatherChanged(weather);
        }
    }

    @Override
    public String getPmPower(String pm2_5) {
        String pm_power = "优";
        int pm_value = Integer.parseInt(pm2_5);
        if (pm_value <= 35) {
            pm_power = "优";
        } else if (pm_value > 35 && pm_value <= 75) {
            pm_power = "良";
        } else if (pm_value > 75 && pm_value <= 115) {
            pm_power = "轻度污染";
        } else if (pm_value > 115 && pm_value <= 150) {
            pm_power = "中度污染";
        } else if (pm_value > 150 && pm_value <= 250) {
            pm_power = "重度污染";
        }
        return pm_power;
    }

}
