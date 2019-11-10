package com.example.android.sunshine.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = WeatherEntry.class, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG =AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "sunshine_database";
    private static AppDatabase databaseInstance;

    public static AppDatabase getInstance(Context context){
        if(databaseInstance == null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "Creating new Database Instance ");
                databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
            }
        }
        Log.d(LOG_TAG, "Getting the Database Instance ");
        return databaseInstance;
    }

    public abstract WeatherDao weatherDao();

}
