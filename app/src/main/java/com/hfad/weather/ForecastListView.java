package com.hfad.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.InputStream;
import java.util.ArrayList;

public class ForecastListView extends Activity {

    ForecastAdapter forecastAdapter = null;
    ListView listView;
    JSONObject jsonObject;
    JSONArray jsonArray;

    static final String URL= "http://api.openweathermap.org/data/2.5/forecast/daily?APPID=38c5418a7c20bc425e41f0f5f5e11908&cnt=16&units=Imperial";
    String city_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        Intent intent = getIntent();
        //Get city_name from the last view
        city_name = intent.getStringExtra("city");
        TextView city_tag = (TextView)findViewById(R.id.forecast_city);
        city_tag.setText(city_name);
        //Get forecast data in a separate thread
        GetForecast(city_name);
    }

    private  void GetForecast(String city_name){
        //append city name to the current url
        String api_url = URL + "&q=" + city_name;
        Log.d("api_url", api_url);
        //Execute get weather data
        new GetWeather().execute(api_url);
    }

    private class GetWeather extends AsyncTask<String,Void, String>{

        @Override
        protected String doInBackground(String... params){
            //Get the first parameter that was passed in from execute command
            //in this case it is the api url
            String url_string = params[0];

            InputStream in = null;

            try{
                URL url = new URL(url_string);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(urlConnection.getInputStream());
            }catch (Exception e){
                Log.e("ERROR",e.getMessage());
                return e.getMessage();
            }
            //Build a string from the returned json
            StringBuilder sb = new StringBuilder();
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"), 8);
                String line = null;
                while ((line = reader.readLine()) != null){
                    sb.append(line + "\n");
                }
                reader.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Return json as a string and have the function onPostExecute run
            return sb.toString();
        }

        protected void onPostExecute(String result) {
            try {
                //Get the custom adapter and assign it to the view
                forecastAdapter = new ForecastAdapter(ForecastListView.this, R.layout.list_v);
                listView = (ListView) findViewById(R.id.forecast_results);
                listView.setAdapter(forecastAdapter);

                String weatherType, mornTemp, dayTemp, eveTemp, nightTemp,date;
                //convert the string to a JSON
                jsonObject =  new JSONObject(result);
                JSONObject day, day_temp, main;
                JSONArray weather;
                JSONArray days = jsonObject.getJSONArray("list");
                for (int i = 0; i < days.length();i++){
                    day = days.getJSONObject(i);
                    date = day.getString("dt");
                    day_temp = day.getJSONObject("temp");
                    mornTemp = day_temp.getString("morn");
                    dayTemp = day_temp.getString("day");
                    eveTemp = day_temp.getString("eve");
                    nightTemp = day_temp.getString("night");
                    weather = day.getJSONArray("weather");
                    main = weather.getJSONObject(0);
                    weatherType = main.getString("main");
                    //Initialize the weatherDate object with the data from json
                    WeatherData weatherData = new WeatherData(weatherType, mornTemp, dayTemp, eveTemp, nightTemp, date);
                    //Append the data object to the adapter
                    forecastAdapter.add(weatherData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
