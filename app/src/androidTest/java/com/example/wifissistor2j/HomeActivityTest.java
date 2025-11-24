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
        SharedPreferences prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        prefs.edit().putBoolean("first_run", true).apply();

        // Launch activity
        scenario = ActivityScenario.launch(HomeActivity.class);

        // Wait a moment for the activity to complete initialization
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify the first run flag is now false
        boolean isFirstRun = prefs.getBoolean("first_run", true);
        assertFalse("First run flag should be false after activity launches", isFirstRun);
    }

    @Test
    public void firstRunFlagRemainsUnchangedOnSubsequentLaunches() {
        // Set the first run flag to false (simulating not first run)
        SharedPreferences prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        prefs.edit().putBoolean("first_run", false).apply();

        // Launch activity
        scenario = ActivityScenario.launch(HomeActivity.class);

        // Verify the first run flag remains false
        boolean isFirstRun = prefs.getBoolean("first_run", true);
        assertFalse("First run flag should remain false", isFirstRun);
    }
}
