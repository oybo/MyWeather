package app.android.oyb.com.myapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by O on 2017/3/16.
 * 天气和位置的实体类
 */

public class Weather {

    // 获取区域编码
    private String adCode;
    // 获取地址
    private String address;
    // 省
    private String province;
    // 城市
    private String city;
    // 获取城市编码
    private String cityCode;
    // 区的名称
    private String district;
    private String street;
    // 经&纬度
    private double longitude;
    private double latitude;

    // ======天气=======
    private String pm10;        // pm10
    private String pm2_5;       // pm2_5
    private String aqi;     // 空气质量数值
    private String quality;     // 空气质量
    private String weather;     // 天气
    private String humidity;     // 湿度
    private String weather_pic;     // 天气图标
    private String temperature;     // 温度
    private String wind_direction;     // 风向
    private String wind_power;     // 风力
    private List<ChildWeatherBean> childWeatherBeanList;    // 最近几天的天气情况

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm2_5() {
        return pm2_5;
    }

    public void setPm2_5(String pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWeather_pic() {
        return weather_pic;
    }

    public void setWeather_pic(String weather_pic) {
        this.weather_pic = weather_pic;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getWind_power() {
        return wind_power;
    }

    public void setWind_power(String wind_power) {
        this.wind_power = wind_power;
    }

    public List<ChildWeatherBean> getChildWeatherBeanList() {
        return childWeatherBeanList;
    }

    public void setChildWeatherBeanList(List<ChildWeatherBean> childWeatherBeanList) {
        this.childWeatherBeanList = childWeatherBeanList;
    }

    public static class ChildWeatherBean implements Serializable {

        /**
         * day_weather : 小雨
         * night_weather : 小雨
         * night_weather_code : 07
         * air_press : 1016 hPa
         * jiangshui : 83%
         * night_wind_power : 微风 <5.4m/s
         * day_wind_power : 3-4级 5.5~7.9m/s
         * day_weather_code : 07
         * sun_begin_end : 06:31|18:33
         * ziwaixian : 弱
         * day_weather_pic : http://app1.showapi.com/weather/icon/day/07.png
         * weekday : 5
         * night_air_temperature : 18
         * day_air_temperature : 21
         * day_wind_direction : 东风
         * day : 20170317
         * night_weather_pic : http://app1.showapi.com/weather/icon/night/07.png
         * night_wind_direction : 无持续风向
         */

        private String day_weather;
        private String night_weather;
        private String night_weather_code;
        private String air_press;
        private String jiangshui;
        private String night_wind_power;
        private String day_wind_power;
        private String day_weather_code;
        private String sun_begin_end;
        private String ziwaixian;
        private String day_weather_pic;
        private int weekday;
        private String night_air_temperature;
        private String day_air_temperature;
        private String day_wind_direction;
        private String day;
        private String night_weather_pic;
        private String night_wind_direction;

        public String getDay_weather() {
            return day_weather;
        }

        public void setDay_weather(String day_weather) {
            this.day_weather = day_weather;
        }

        public String getNight_weather() {
            return night_weather;
        }

        public void setNight_weather(String night_weather) {
            this.night_weather = night_weather;
        }

        public String getNight_weather_code() {
            return night_weather_code;
        }

        public void setNight_weather_code(String night_weather_code) {
            this.night_weather_code = night_weather_code;
        }

        public String getAir_press() {
            return air_press;
        }

        public void setAir_press(String air_press) {
            this.air_press = air_press;
        }

        public String getJiangshui() {
            return jiangshui;
        }

        public void setJiangshui(String jiangshui) {
            this.jiangshui = jiangshui;
        }

        public String getNight_wind_power() {
            return night_wind_power;
        }

        public void setNight_wind_power(String night_wind_power) {
            this.night_wind_power = night_wind_power;
        }

        public String getDay_wind_power() {
            return day_wind_power;
        }

        public void setDay_wind_power(String day_wind_power) {
            this.day_wind_power = day_wind_power;
        }

        public String getDay_weather_code() {
            return day_weather_code;
        }

        public void setDay_weather_code(String day_weather_code) {
            this.day_weather_code = day_weather_code;
        }

        public String getSun_begin_end() {
            return sun_begin_end;
        }

        public void setSun_begin_end(String sun_begin_end) {
            this.sun_begin_end = sun_begin_end;
        }

        public String getZiwaixian() {
            return ziwaixian;
        }

        public void setZiwaixian(String ziwaixian) {
            this.ziwaixian = ziwaixian;
        }

        public String getDay_weather_pic() {
            return day_weather_pic;
        }

        public void setDay_weather_pic(String day_weather_pic) {
            this.day_weather_pic = day_weather_pic;
        }

        public int getWeekday() {
            return weekday;
        }

        public void setWeekday(int weekday) {
            this.weekday = weekday;
        }

        public String getNight_air_temperature() {
            return night_air_temperature;
        }

        public void setNight_air_temperature(String night_air_temperature) {
            this.night_air_temperature = night_air_temperature;
        }

        public String getDay_air_temperature() {
            return day_air_temperature;
        }

        public void setDay_air_temperature(String day_air_temperature) {
            this.day_air_temperature = day_air_temperature;
        }

        public String getDay_wind_direction() {
            return day_wind_direction;
        }

        public void setDay_wind_direction(String day_wind_direction) {
            this.day_wind_direction = day_wind_direction;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getNight_weather_pic() {
            return night_weather_pic;
        }

        public void setNight_weather_pic(String night_weather_pic) {
            this.night_weather_pic = night_weather_pic;
        }

        public String getNight_wind_direction() {
            return night_wind_direction;
        }

        public void setNight_wind_direction(String night_wind_direction) {
            this.night_wind_direction = night_wind_direction;
        }
    }

}
