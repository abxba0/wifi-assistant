package com.example.wifissistor2j;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test for MapActivity
 */
@RunWith(AndroidJUnit4.class)
public class MapActivityTest {

    private ActivityScenario<MapActivity> scenario;
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
        scenario = ActivityScenario.launch(MapActivity.class);
        assertNotNull(scenario);
    }

    @Test
    public void activityCanBeLaunchedMultipleTimes() {
        scenario = ActivityScenario.launch(MapActivity.class);
        scenario.close();
        scenario = ActivityScenario.launch(MapActivity.class);
        assertNotNull(scenario);
    }

    @Test
    public void activityHandlesLifecycle() {
        scenario = ActivityScenario.launch(MapActivity.class);
        scenario.onActivity(activity -> {
            assertNotNull(activity);
            assertFalse(activity.isFinishing());
        });
    }
}
