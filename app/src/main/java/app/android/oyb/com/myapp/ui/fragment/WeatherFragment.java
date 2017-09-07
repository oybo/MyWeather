package app.android.oyb.com.myapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.android.oyb.com.myapp.R;
import app.android.oyb.com.myapp.adapter.WeatherNewsAdapter;
import app.android.oyb.com.myapp.bean.Weather;
import app.android.oyb.com.myapp.bean.WeatherNews;
import app.android.oyb.com.myapp.manager.ImageLoadManager;
import app.android.oyb.com.myapp.mvp.presenter.WeatherPresenter;
import app.android.oyb.com.myapp.mvp.view.WeatherView;
import app.android.oyb.com.myapp.ui.BaseFragment;
import app.android.oyb.com.myapp.widget.WeatherTemperatureView;
import butterknife.Bind;
import butterknife.ButterKnife;

import static app.android.oyb.com.myapp.R.id.weather_wind_direction_txt;

/**
 * Created by O on 2017/3/2.
 */

public class WeatherFragment extends BaseFragment implements WeatherView {

    @Bind(R.id.fragment_weather_address) TextView fragmentWeatherAddress;
    @Bind(weather_wind_direction_txt) TextView weatherWindDirectionTxt;
    @Bind(R.id.weather_quality_txt) TextView weatherQualityTxt;
    @Bind(R.id.weather_pm_txt) TextView weatherPmTxt;
    @Bind(R.id.weather_recyclerview) RecyclerView weatherRecyclerview;
    @Bind(R.id.fragment_weather_temperature_txt) WeatherTemperatureView weatherTemperatureView;
    @Bind(R.id.fragment_weather_weather_txt) TextView weatherWeatherTextView;
    // 底部今天天气情况view
    @Bind(R.id.fragment_buttom_today_pm_power_txt) TextView fragmentButtomTodayPmPowerTxt;
    @Bind(R.id.fragment_buttom_today_temperature_txt) TextView fragmentButtomTodayTemperatureTxt;
    @Bind(R.id.fragment_buttom_today_weather_txt) TextView fragmentButtomTodayWeatherTxt;
    @Bind(R.id.fragment_buttom_today_weather_image) ImageView fragmentButtomTodayWeatherImage;
    // 底部明天天气情况view
    @Bind(R.id.fragment_buttom_tomorrow_pm_power_txt) TextView fragmentButtomTomorrowPmPowerTxt;
    @Bind(R.id.fragment_buttom_tomorrow_temperature_txt) TextView fragmentButtomTomorrowTemperatureTxt;
    @Bind(R.id.fragment_buttom_tomorrow_weather_txt) TextView fragmentButtomTomorrowWeatherTxt;
    @Bind(R.id.fragment_buttom_tomorrow_weather_image) ImageView fragmentButtomTomorrowWeatherImage;

    private WeatherPresenter mPresenter;
    private WeatherNewsAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();
        initNews();
    }

    private void initView() {
        weatherRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    private void initData() {
        mPresenter = new WeatherPresenter(this);
    }

    private void initNews() {
        mAdapter = new WeatherNewsAdapter(getActivity());

        List<WeatherNews> weatherNewses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            weatherNewses.add(new WeatherNews());
        }
        mAdapter.appendToList(weatherNewses);
        weatherRecyclerview.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.startLocation();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.stopLocation();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mPresenter.destoreLocation();
    }

    @Override
    public void onWeatherChanged(Weather weather) {
        String address = weather.getProvince() + weather.getCity() + weather.getDistrict() + weather.getStreet();
        fragmentWeatherAddress.setText(address);
        if (TextUtils.isEmpty(weather.getTemperature())) {
            // 再去请求天气
            mPresenter.getWeather(weather);
        }
        // 温度天气等
        Weather.ChildWeatherBean weatherBean = weather.getChildWeatherBeanList().get(0);
        weatherTemperatureView.setTemperature(weatherBean.getDay_air_temperature());
        weatherWeatherTextView.setText(weatherBean.getDay_weather());
        // 风向等
        weatherWindDirectionTxt.setText("风向：" + weather.getWind_direction() + "     [" + weather.getWind_power() + "]");
        weatherQualityTxt.setText("空气质量：" + weather.getAqi() + "     " + weather.getQuality());
        weatherPmTxt.setText("PM2.5：" + weather.getPm2_5() + "     [" + getPmPower(weather.getPm2_5()) + "]");
        // 底部今天天气情况
        fragmentButtomTodayPmPowerTxt.setText(getPmPower(weather.getPm2_5()));
        fragmentButtomTodayTemperatureTxt.setText(weatherBean.getDay_air_temperature() +"/ "+ weatherBean.getNight_air_temperature() + "℃");
        fragmentButtomTodayWeatherTxt.setText(weatherBean.getDay_weather());
        ImageLoadManager.setImage(_getContext(), weatherBean.getDay_weather_pic(), fragmentButtomTodayWeatherImage);
        // 底部明天天气情况
        weatherBean = weather.getChildWeatherBeanList().get(1);
        fragmentButtomTomorrowPmPowerTxt.setText(getPmPower(weather.getPm2_5()));
        fragmentButtomTomorrowTemperatureTxt.setText(weatherBean.getDay_air_temperature() +"/ "+ weatherBean.getNight_air_temperature() + "℃");
        fragmentButtomTomorrowWeatherTxt.setText(weatherBean.getDay_weather());
        ImageLoadManager.setImage(_getContext(), weatherBean.getDay_weather_pic(), fragmentButtomTomorrowWeatherImage);
    }

    @Override
    public Context _getContext() {
        return getActivity();
    }

    private String getPmPower(String pm2_5) {
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
