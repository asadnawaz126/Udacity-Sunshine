package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.android.sunshine.database.AppDatabase;
import com.example.android.sunshine.database.WeatherEntry;
import com.example.android.sunshine.utilities.AppExecutors;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    private AppDatabase databaseInstance;

    private String mForecast;
    private TextView mWeatherDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        databaseInstance = AppDatabase.getInstance(getApplicationContext());
        mWeatherDisplay = (TextView) findViewById(R.id.tv_display_weather);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("EXTRA_WEATHER_ID")) {
                int id = intentThatStartedThisActivity.getIntExtra("EXTRA_WEATHER_ID", 0);
                Log.d(TAG, "Detail Activity: value of id: "+ id);
                LiveData<WeatherEntry> weatherEntry = databaseInstance.weatherDao().loadSingleEntry(id+1);
                weatherEntry.observe(this, new Observer<WeatherEntry>() {
                    @Override
                    public void onChanged(WeatherEntry weatherEntry) {
                        boolean isNULL = weatherEntry == null ? true : false;
                        Log.d(TAG, "LiveData: got single entry from database:" + isNULL + "\t database entry number: "+ weatherEntry.getId());
                        mForecast = new WeatherEntry().convertToString(weatherEntry);
                        mWeatherDisplay.setText(mForecast);
                    }
                });

            }
        }
    }

    /**
     * Uses the ShareCompat Intent builder to create our Forecast intent for sharing. We set the
     * type of content that we are sharing (just regular text), the text itself, and we return the
     * newly created Intent.
     *
     * @return The Intent to use to start our share.
     */
    private Intent createShareForecastIntent() {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(mForecast + FORECAST_SHARE_HASHTAG)
                .getIntent();
        return shareIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);

        //get the individual menuItem and set the intent to it
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForecastIntent());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}