package com.example.android.sunshine.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;



@Dao
public interface WeatherDao {

    @Query("SELECT * FROM weather")
    LiveData<WeatherEntry[]> loadAllWeather();

    @Query("SELECT * FROM weather WHERE id = :id ")
    LiveData<WeatherEntry> loadSingleEntry(int id);

    @Insert
    void insertAllDataIntoDatabase(WeatherEntry[] weatherEntryList);

    @Insert
    void insertWeatherData(WeatherEntry weatherEntry);

    @Query("DELETE FROM weather")
    void deleteAllDatabaseData();
}
