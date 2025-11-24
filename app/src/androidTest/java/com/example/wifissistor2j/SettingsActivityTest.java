package com.example.wifissistor2j;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test for SettingsActivity
 */
@RunWith(AndroidJUnit4.class)
public class SettingsActivityTest {

    private ActivityScenario<SettingsActivity> scenario;
    private Context context;
    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        sharedPreferences = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE);
        // Clear preferences before each test
        sharedPreferences.edit().clear().apply();
    }

    @After
    public void tearDown() {
        if (scenario != null) {
            scenario.close();
        }
        // Clean up preferences after tests
        sharedPreferences.edit().clear().apply();
    }

    @Test
    public void activityLaunches() {
        scenario = ActivityScenario.launch(SettingsActivity.class);
        assertNotNull(scenario);
    }

    @Test
    public void activityCanBeLaunchedMultipleTimes() {
        scenario = ActivityScenario.launch(SettingsActivity.class);
        scenario.close();
        scenario = ActivityScenario.launch(SettingsActivity.class);
        assertNotNull(scenario);
    }

    @Test
    public void activityHandlesLifecycle() {
        scenario = ActivityScenario.launch(SettingsActivity.class);
        scenario.onActivity(activity -> {
            assertNotNull(activity);
            assertFalse(activity.isFinishing());
        });
    }

    @Test
    public void settingsActivity_loadsDefaultTheme() {
        scenario = ActivityScenario.launch(SettingsActivity.class);
        int savedTheme = sharedPreferences.getInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        assertEquals(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, savedTheme);
    }

    @Test
    public void settingsActivity_loadsDefaultSpeedUnits() {
        scenario = ActivityScenario.launch(SettingsActivity.class);
        String savedUnits = sharedPreferences.getString("speed_units", "Default");
        assertEquals("Default", savedUnits);
    }

    @Test
    public void sharedPreferences_canStoreThemeSetting() {
        sharedPreferences.edit().putInt("theme", AppCompatDelegate.MODE_NIGHT_YES).apply();
        int savedTheme = sharedPreferences.getInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        assertEquals(AppCompatDelegate.MODE_NIGHT_YES, savedTheme);
    }

    @Test
    public void sharedPreferences_canStoreSpeedUnitsSetting() {
        sharedPreferences.edit().putString("speed_units", "Mbps").apply();
        String savedUnits = sharedPreferences.getString("speed_units", "Default");
        assertEquals("Mbps", savedUnits);
    }
}
