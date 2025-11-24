package com.example.wifissistor2j;

import android.app.Application;
import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test for MyApplication
 */
@RunWith(AndroidJUnit4.class)
public class MyApplicationTest {

    @Test
    public void applicationContext_isNotNull() {
        Context context = ApplicationProvider.getApplicationContext();
        assertNotNull(context);
    }

    @Test
    public void applicationContext_hasCorrectPackageName() {
        Context context = ApplicationProvider.getApplicationContext();
        assertEquals("com.example.wifissistor2j", context.getPackageName());
    }

    @Test
    public void application_isInstanceOfMyApplication() {
        Application application = (Application) ApplicationProvider.getApplicationContext();
        assertTrue(application instanceof MyApplication);
    }

    @Test
    public void application_hasSharedPreferences() {
        Context context = ApplicationProvider.getApplicationContext();
        assertNotNull(context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE));
    }
}
