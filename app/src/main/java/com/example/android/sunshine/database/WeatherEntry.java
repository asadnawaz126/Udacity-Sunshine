package com.example.android.sunshine.database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.android.sunshine.models.Weather;


@Entity(tableName = "weather")
public class WeatherEntry implements Weather {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String weatherID;
    private String description;
    private String date;
    private String minTemp;
    private String maxTemp;
    private String humidity;
    private String pressure;
    private String wind;
    private String degrees;

    public WeatherEntry() {
    }

    public WeatherEntry(int id, String weatherID, String description, String date, String minTemp, String maxTemp, String humidity, String pressure, String wind, String degrees) {
        this.id = id;
        this.weatherID = weatherID;
        this.description = description;
        this.date = date;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind = wind;
        this.degrees = degrees;
    }

    @Ignore
    public WeatherEntry(String weatherID, String date, String description, String minTemp, String maxTemp, String humidity, String pressure, String wind, String degrees) {
        this.weatherID = weatherID;
        this.date = date;
        this.description = description;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind = wind;
        this.degrees = degrees;
    }

    @Ignore
    public WeatherEntry(String date, String description, String minTemp, String maxTemp) {
        this.date = date;
        this.description = description;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getWeatherID() {
        return weatherID;
    }

    public void setWeatherID(String weatherID) {
        this.weatherID = weatherID;
    }

    @Override
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }

    public String convertToString(WeatherEntry weatherEntry){
        String entry = weatherEntry.date + " - " + weatherEntry.maxTemp + " - " + weatherEntry.minTemp;

        return entry;
    }


    public String[] convertToStringArray(WeatherEntry[] weatherEntries){
        String[] entries = new String[weatherEntries.length];
        for(int i=0;i<weatherEntries.length;i++){
            entries[i] = weatherEntries[i].date + " - " + weatherEntries[i].description + " - " + weatherEntries[i].maxTemp + "/" + weatherEntries[i].minTemp;
        }
        return entries;
    }

}
