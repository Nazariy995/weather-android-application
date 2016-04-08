package com.hfad.weather;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText city = (EditText) findViewById(R.id.city_text);
        city.setText("");
    }


    //Pass city name to the next activity ForecastListView
    public void onGetForecast(View view){
        EditText city = (EditText) findViewById(R.id.city_text);
        String city_name = city.getText().toString();
        if (!city_name.isEmpty()) {
            Intent forecast = new Intent(this,ForecastListView.class);
            forecast.putExtra("city", city.getText().toString());
            startActivity(forecast);
        }

    }
}
