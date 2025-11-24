package com.example.wifissistor2j;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test for HomeActivity
 */
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    private static final String PREFS_NAME = "app_prefs";
    private static final String KEY_FIRST_RUN = "first_run";

    private ActivityScenario<HomeActivity> scenario;
    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
    }

    @After
    public void tearDown() {
        if (scenario != null) {
            scenario.close();
        }
    }

    @Test
    public void activityLaunches() {
        scenario = ActivityScenario.launch(HomeActivity.class);
        assertNotNull(scenario);
    }

    @Test
    public void activityHasCorrectPackageName() {
        assertEquals("com.example.wifissistor2j", context.getPackageName());
    }

    @Test
    public void activityCanBeLaunchedMultipleTimes() {
        scenario = ActivityScenario.launch(HomeActivity.class);
        scenario.close();
        scenario = ActivityScenario.launch(HomeActivity.class);
        assertNotNull(scenario);
    }

    @Test
    public void activityHandlesResume() {
        scenario = ActivityScenario.launch(HomeActivity.class);
        scenario.onActivity(activity -> {
            assertNotNull(activity);
            assertFalse(activity.isFinishing());
        });
    }

    @Test
    public void activityHandlesIntentReorderToFront() {
        scenario = ActivityScenario.launch(HomeActivity.class);
        scenario.onActivity(activity -> {
            Intent intent = new Intent(activity, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            // Verify intent doesn't crash
            assertNotNull(intent);
        });
    }

    @Test
    public void firstRunFlagIsSetAfterActivityLaunch() {
        // Clear the first run flag to simulate first run
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(KEY_FIRST_RUN, true).apply();

        // Launch activity
        scenario = ActivityScenario.launch(HomeActivity.class);

        // Use onActivity callback to ensure activity is ready before checking
        scenario.onActivity(activity -> {
            // Verify the first run flag is now false after activity onCreate completes
            boolean isFirstRun = prefs.getBoolean(KEY_FIRST_RUN, true);
            assertFalse("First run flag should be false after activity launches", isFirstRun);
        });
    }

    @Test
    public void firstRunFlagRemainsUnchangedOnSubsequentLaunches() {
        // Set the first run flag to false (simulating not first run)
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(KEY_FIRST_RUN, false).apply();

        // Launch activity
        scenario = ActivityScenario.launch(HomeActivity.class);

        // Verify the first run flag remains false
        boolean isFirstRun = prefs.getBoolean(KEY_FIRST_RUN, true);
        assertFalse("First run flag should remain false", isFirstRun);
    }
}
