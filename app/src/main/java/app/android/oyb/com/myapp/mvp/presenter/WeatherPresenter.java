package app.android.oyb.com.myapp.mvp.presenter;

import app.android.oyb.com.myapp.bean.Weather;
import app.android.oyb.com.myapp.callback.OnLocationCallback;
import app.android.oyb.com.myapp.mvp.weather.WeatherModel;
import app.android.oyb.com.myapp.mvp.view.WeatherView;

/**
 * Created by O on 2017/3/16.
 */

public class WeatherPresenter implements OnLocationCallback {

    private WeatherView mView;
    private WeatherModel mModel;

    public WeatherPresenter(WeatherView view) {
        mView = view;
        mModel = new WeatherModel(mView._getContext());
        mModel.setLocationListener(this);
    }

    public void getWeather(Weather weather) {
        mModel.getWeather(mView._getContext(), weather);
    }

    /**
     * 开始请求定位
     */
    public void startLocation() {
        mModel.startLocation();
    }

    /**
     * 停止定位
     */
    public void stopLocation() {
        mModel.stopLocation();
    }

    /**
     * 注销定位
     */
    public void destoreLocation() {
        mModel.destoreLocation();
    }

    /**
     * 定位成功回调
     */
    @Override
    public void onLocation(Weather weather) {
        if(mView != null) {
            mView.onWeatherChanged(weather);
        }
    }

}
