package com.hfad.weather;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherData {
    //Set all the data for a day worth of weather data
    private String unit = "°F";
    private String weatherType, mornTemp, dayTemp, eveTemp, nightTemp;
    private Date date;

    public WeatherData(String weatherType, String mornTemp, String dayTemp, String eveTemp, String nightTemp, String date){
        this.setDate(date);
        this.setDayTemp(dayTemp);
        this.setEveTemp(eveTemp);
        this.setMornTemp(mornTemp);
        this.setNightTemp(nightTemp);
        this.setWeatherType(weatherType);
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getMornTemp() {
        return mornTemp + unit;
    }

    public void setMornTemp(String mornTemp) {
        this.mornTemp = mornTemp;
    }

    public String getDayTemp() {
        return dayTemp + unit;
    }

    public void setDayTemp(String dayTemp) {
        this.dayTemp = dayTemp;
    }

    public String getEveTemp() {
        return eveTemp + unit;
    }

    public void setEveTemp(String eveTemp) {
        this.eveTemp = eveTemp;
    }

    public String getNightTemp() {
        return nightTemp + unit;
    }

    public void setNightTemp(String nightTemp) {
        this.nightTemp = nightTemp;
    }

    public String getDate() {
        SimpleDateFormat ft = new SimpleDateFormat("EEEE, MMM d");
//        Log.d("date in format", ft.format(date));
        return ft.format(date);
    }

    public void setDate(String date) {
        Long date_long = Long.parseLong(date);
        Date date_obj = new Date(date_long*1000);
//        Log.d("date", date_obj.toString());
        this.date = date_obj;
    }

}
