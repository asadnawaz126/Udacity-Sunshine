package com.example.android.sunshine.models;



public interface Weather {
    int getId();
    String getWeatherID();
    String getDate();
    String getMinTemp();
    String getMaxTemp();
    String getHumidity();
    String getPressure();
    String getWind();
    String getDegrees();
}
