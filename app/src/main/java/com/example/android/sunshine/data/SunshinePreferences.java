/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.example.android.sunshine.R;

public class SunshinePreferences {

    private static final String TAG = SunshinePreferences.class.getSimpleName();

    public static final String PREF_CITY_NAME = "city_name";


    public static final String PREF_COORD_LAT = "coord_lat";
    public static final String PREF_COORD_LONG = "coord_long";


    private static final String DEFAULT_WEATHER_LOCATION = "94043,USA";
    private static final double[] DEFAULT_WEATHER_COORDINATES = {37.4284, 122.0724};

    private static final String DEFAULT_MAP_LOCATION =
            "1600 Amphitheatre Parkway, Mountain View, CA 94043";



    static public void setLocationDetails(Context c, String cityName, double lat, double lon) {

    }


    static public void setLocation(Context c, String locationSetting, double lat, double lon) {
        /** This will be implemented in a future lesson **/
    }


    static public void resetLocationCoordinates(Context c) {

    }


    public static String getPreferredWeatherLocation(Context context) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Log.d(TAG, "getPreferredWeatherLocation: preffered location = " + sharedPreferences.getString(context.getString(R.string.pref_location_key),
                context.getString(R.string.pref_location_default_value)));
        return sharedPreferences.getString(context.getString(R.string.pref_location_key),
                context.getString(R.string.pref_location_default_value));
    }


    public static boolean isMetric(Context context) {

//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//        String prefUnit = sharedPreferences.getString(Integer.toString(R.string.pref_unit_key),
//                Integer.toString(R.string.pref_unit_default_value));
//        String metric = context.getString(R.string.pref_unit_celsius_value);
//
//        if(prefUnit.equals(metric))
//            return true;
//        else
//            return false;
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        String keyForUnits = context.getString(R.string.pref_unit_key);
        String defaultUnits = context.getString(R.string.pref_unit_default_value);
        String preferredUnits = prefs.getString(keyForUnits, defaultUnits);
        String metric = context.getString(R.string.pref_unit_celsius_value);
        boolean userPrefersMetric;
        if (metric.equals(preferredUnits)) {
            userPrefersMetric = true;
        } else {
            userPrefersMetric = false;
        }
        return userPrefersMetric;
    }


    public static double[] getLocationCoordinates(Context context) {
        return getDefaultWeatherCoordinates();
    }


    public static boolean isLocationLatLonAvailable(Context context) {

        return false;
    }

    private static String getDefaultWeatherLocation() {

        return DEFAULT_WEATHER_LOCATION;
    }

    public static double[] getDefaultWeatherCoordinates() {

        return DEFAULT_WEATHER_COORDINATES;
    }
}