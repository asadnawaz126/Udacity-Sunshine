package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_settings);


        SharedPreferences sharedPreferences =getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen = getPreferenceScreen();
       /** the following code is to access all the preferences that we want to update the weather to but
        can't do via xml so we're doing it programmatically.
        this is for when the settingsactivity is first created. for subsequent changes, there is the
        setPreferenceSummary **/
        int count = prefScreen.getPreferenceCount();

        for(int i = 0; i < count; i++){
            Preference preference = prefScreen.getPreference(i);
            /**get the value stored in the sharedPreferences file that corresponds to the key of the preference we
            currently iterating **/
            if (!(preference instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, value);
            }
        }


    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d(TAG, "onSharedPreferenceChanged: method beginning");
        Preference preference = findPreference(key);
        if (null != preference) {
            /** Updates the summary for the preference **/
            if (!(preference instanceof CheckBoxPreference)) {
                setPreferenceSummary(preference, sharedPreferences.getString(key, ""));
            }
        }
        Log.d(TAG, "onSharedPreferenceChanged: method end");

    }

    public void setPreferenceSummary(Preference preference, Object value){
        String stringValue = value.toString();
        String key = preference.getKey();

        if (preference instanceof ListPreference) {

            Log.d(TAG, "setPreferenceSummary ListPreference Check: ");
            // For list preferences, figure out the label of the selected value
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                // Set the summary to that label
                listPreference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            Log.d(TAG, "setPreferenceSummary EditText Check: ");
            // For EditTextPreferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
