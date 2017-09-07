package app.android.oyb.com.myapp.mvp.view;

import app.android.oyb.com.myapp.bean.Weather;
import app.android.oyb.com.myapp.mvp.base.BaseView;

/**
 * Created by O on 2017/3/16.
 */

public interface WeatherView extends BaseView {

    void onWeatherChanged(Weather weather);

}
