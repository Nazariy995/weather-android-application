package com.hfad.weather;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter extends ArrayAdapter{
    List list = new ArrayList();

    public ForecastAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(WeatherData object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        WeatherDataHolder weatherDataHolder;
        if (row == null){
            //Prepare the view
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.list_v,parent,false);
            //Get all the TextViews by their id and assign them to the appropriate variables
            weatherDataHolder = new WeatherDataHolder();
            weatherDataHolder.tx_date = (TextView) row.findViewById(R.id.date);
            weatherDataHolder.tx_weatherType = (TextView) row.findViewById(R.id.weatherType);
            weatherDataHolder.tx_mornTemp = (TextView) row.findViewById(R.id.morn_temp);
            weatherDataHolder.tx_dayTemp = (TextView) row.findViewById(R.id.day_temp);
            weatherDataHolder.tx_eveTemp = (TextView) row.findViewById(R.id.eve_temp);
            weatherDataHolder.tx_nightTemp = (TextView) row.findViewById(R.id.night_temp);
            row.setTag(weatherDataHolder);
        }else{
            weatherDataHolder = (WeatherDataHolder) row.getTag();
        }
        //Get the Weather object based on the position
        WeatherData weatherData = (WeatherData) this.getItem(position);
        //Set the list view data
        weatherDataHolder.tx_date.setText(weatherData.getDate());
        weatherDataHolder.tx_weatherType.setText(weatherData.getWeatherType());
        weatherDataHolder.tx_mornTemp.setText(weatherData.getMornTemp());
        weatherDataHolder.tx_dayTemp.setText(weatherData.getDayTemp());
        weatherDataHolder.tx_eveTemp.setText(weatherData.getEveTemp());
        weatherDataHolder.tx_nightTemp.setText(weatherData.getNightTemp());

        return row;
    }

    //For the purpose of  holding TextViews
    static class WeatherDataHolder {
        TextView tx_weatherType, tx_mornTemp, tx_dayTemp, tx_eveTemp, tx_nightTemp, tx_date;
    }
}
