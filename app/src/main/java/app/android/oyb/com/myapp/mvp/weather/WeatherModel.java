package app.android.oyb.com.myapp.mvp.weather;

import android.content.Context;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import app.android.oyb.com.myapp.bean.Weather;
import app.android.oyb.com.myapp.callback.JsonStringCallback;
import app.android.oyb.com.myapp.callback.OnLocationCallback;
import app.android.oyb.com.myapp.utils.ToastUtil;

/**
 * Created by O on 2017/3/16.
 */

public class WeatherModel implements AMapLocationListener {

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private OnLocationCallback mLocationCallback;

    private Weather mWeather;

    public WeatherModel(Context context) {
        locationClient = new AMapLocationClient(context.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置是否需要显示地址信息
        locationOption.setNeedAddress(true);
        /**
         * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
         * 注意：只有在高精度模式下的单次定位有效，其他方式无效
         */
        locationOption.setGpsFirst(true);
        // 设置发送定位请求的时间间隔,最小值为1000，如果小于1000，按照1000算
        locationOption.setInterval(Long.valueOf(10 * 60 * 1000));

        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(this);
    }

    public void startLocation() {
        // 启动定位
        if(locationClient != null) {
            locationClient.startLocation();
        }
    }

    public void stopLocation() {
        if(locationClient != null) {
            locationClient.stopLocation();
        }
    }

    public void setLocationListener(OnLocationCallback onLocationCallback) {
        mLocationCallback = onLocationCallback;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if(aMapLocation != null && aMapLocation.getLatitude() != 0) {
            mWeather = new Weather();
            // 获取区域编码
            mWeather.setAdCode(aMapLocation.getAdCode());
            // 获取地址
            mWeather.setAddress(aMapLocation.getAddress());
            // 省
            mWeather.setProvince(aMapLocation.getProvince());
            // 城市
            mWeather.setCity(aMapLocation.getCity());
            // 获取城市编码
            mWeather.setCityCode(aMapLocation.getCityCode());
            // 获取区的名称
            mWeather.setDistrict(aMapLocation.getDistrict());
            mWeather.setStreet(aMapLocation.getStreet());
            // 获取经&纬度
            mWeather.setLongitude(aMapLocation.getLongitude());
            mWeather.setLatitude(aMapLocation.getLatitude());

            if(mLocationCallback != null) {
                mLocationCallback.onLocation(mWeather);
            }
        }
    }

    public void destoreLocation() {
        /**
         * 如果AMapLocationClient是在当前Activity实例化的，
         * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
         */
        if (null != locationClient) {
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    public void getWeather(final Context context, Weather weather) {
        OkGo.get("http://route.showapi.com/9-5")     // 请求方式和请求url
                .tag(context)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey(getClass().getName())            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .params("showapi_appid", "32985")
                .params("showapi_sign", "b226815739ae4cfd8a75a8828148ff2e")
                .params("from", "5")
                .params("lng", String.valueOf(weather.getLongitude()))
                .params("lat", String.valueOf(weather.getLatitude()))
                .execute(new JsonStringCallback() {
                    @Override
                    public void success(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.optInt("showapi_res_code");
                            if(code == 0) {
                                // 接口请求成功
                                if(mWeather != null) {
                                    JSONObject bodyObject = jsonObject.optJSONObject("showapi_res_body");
                                    JSONObject nowObject = bodyObject.optJSONObject("now");
                                    JSONObject aqiDetaiObject = nowObject.optJSONObject("aqiDetail");

                                    mWeather.setPm2_5(aqiDetaiObject.optString("pm2_5"));   // pm2_5
                                    mWeather.setPm10(aqiDetaiObject.optString("pm10"));     // pm10
                                    mWeather.setAqi(aqiDetaiObject.optString("aqi"));   // 空气质量数值
                                    mWeather.setQuality(aqiDetaiObject.optString("quality"));   // 空气质量
                                    mWeather.setWeather(nowObject.optString("weather"));        // 天气
                                    mWeather.setHumidity(nowObject.optString("sd"));        // 湿度
                                    mWeather.setWeather_pic(nowObject.optString("weather_pic"));    // 天气图标
                                    mWeather.setTemperature(nowObject.optString("temperature"));    // 温度
                                    mWeather.setWind_direction(nowObject.optString("wind_direction"));  // 风向
                                    mWeather.setWind_power(nowObject.optString("wind_power"));  // 风力

                                    List<Weather.ChildWeatherBean> childWeatherBeanList = new ArrayList<Weather.ChildWeatherBean>();
                                    childWeatherBeanList.add(json2Weather(bodyObject.optJSONObject("f1")));
                                    childWeatherBeanList.add(json2Weather(bodyObject.optJSONObject("f2")));
                                    childWeatherBeanList.add(json2Weather(bodyObject.optJSONObject("f3")));
                                    mWeather.setChildWeatherBeanList(childWeatherBeanList);

                                    if(mLocationCallback != null) {
                                        mLocationCallback.onLocation(mWeather);
                                    }
                                }
                            } else {
                                ToastUtil.showToast(context, jsonObject.optString("showapi_res_error"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void error(String message) {

                    }
                });
    }

    private Weather.ChildWeatherBean json2Weather(JSONObject f1) {
        Weather.ChildWeatherBean childWeatherBean = new Weather.ChildWeatherBean();
        childWeatherBean.setDay_weather(f1.optString("day_weather"));
        childWeatherBean.setNight_weather(f1.optString("night_weather"));
        childWeatherBean.setNight_weather_code(f1.optString("night_weather_code"));
        childWeatherBean.setAir_press(f1.optString("air_press"));
        childWeatherBean.setJiangshui(f1.optString("jiangshui"));
        childWeatherBean.setNight_wind_power(f1.optString("night_wind_power"));
        childWeatherBean.setDay_wind_power(f1.optString("day_wind_power"));
        childWeatherBean.setDay_weather_code(f1.optString("day_weather_code"));
        childWeatherBean.setSun_begin_end(f1.optString("sun_begin_end"));
        childWeatherBean.setZiwaixian(f1.optString("ziwaixian"));
        childWeatherBean.setDay_weather_pic(f1.optString("day_weather_pic"));
        childWeatherBean.setWeekday(f1.optInt("weekday"));
        childWeatherBean.setNight_air_temperature(f1.optString("night_air_temperature"));
        childWeatherBean.setDay_air_temperature(f1.optString("day_air_temperature"));
        childWeatherBean.setDay_wind_direction(f1.optString("day_wind_direction"));
        childWeatherBean.setDay(f1.optString("day"));
        childWeatherBean.setNight_weather_pic(f1.optString("night_weather_pic"));
        childWeatherBean.setNight_wind_direction(f1.optString("night_wind_direction"));
        return childWeatherBean;
    }

}
