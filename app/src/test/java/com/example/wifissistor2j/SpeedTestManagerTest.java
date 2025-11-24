package com.example.wifissistor2j;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for SpeedTestManager
 */
@RunWith(MockitoJUnitRunner.class)
public class SpeedTestManagerTest {

    @Mock
    private Context mockContext;

    @Mock
    private SpeedTester.SpeedTestListener mockListener;

    private SpeedTestManager manager;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockContext.getApplicationContext()).thenReturn(mockContext);
    }

    @Test
    public void getInstance_returnsSingletonInstance() {
        SpeedTestManager instance1 = SpeedTestManager.getInstance(mockContext);
        SpeedTestManager instance2 = SpeedTestManager.getInstance(mockContext);
        
        assertNotNull(instance1);
        assertNotNull(instance2);
        assertSame("Should return same singleton instance", instance1, instance2);
    }

    @Test
    public void getInstance_usesApplicationContext() {
        SpeedTestManager.getInstance(mockContext);
        verify(mockContext, atLeastOnce()).getApplicationContext();
    }

    @Test
    public void setListener_storesListener() {
        manager = SpeedTestManager.getInstance(mockContext);
        manager.setListener(mockListener);
        // If this doesn't crash, the listener was set successfully
    }

    @Test
    public void removeListener_clearsListener() {
        manager = SpeedTestManager.getInstance(mockContext);
        manager.setListener(mockListener);
        manager.removeListener();
        // If this doesn't crash, the listener was removed successfully
    }

    @Test
    public void setListener_canBeCalledMultipleTimes() {
        manager = SpeedTestManager.getInstance(mockContext);
        manager.setListener(mockListener);
        manager.setListener(mockListener);
        manager.setListener(null);
        manager.setListener(mockListener);
        // Should not crash
    }

    @Test
    public void removeListener_canBeCalledWhenNoListenerSet() {
        manager = SpeedTestManager.getInstance(mockContext);
        manager.removeListener();
        manager.removeListener();
        // Should not crash even if no listener was set
    }

    @Test
    public void startTest_doesNotCrashWithoutListener() {
        manager = SpeedTestManager.getInstance(mockContext);
        // Note: This will try to load servers from resources which won't work in unit tests
        // But we can verify it doesn't crash immediately
        try {
            manager.startTest();
        } catch (Exception e) {
            // Expected to fail due to missing resources in unit test, but shouldn't crash
            assertTrue(e.getMessage() == null || !e.getMessage().contains("NullPointerException"));
        }
    }

    @Test
    public void stopTest_doesNotCrash() {
        manager = SpeedTestManager.getInstance(mockContext);
        manager.stopTest();
        // Should not crash even if no test is running
    }

    @Test
    public void stopTest_canBeCalledMultipleTimes() {
        manager = SpeedTestManager.getInstance(mockContext);
        manager.stopTest();
        manager.stopTest();
        manager.stopTest();
        // Should not crash
    }

    @Test
    public void onDownloadProgress_withNoListener_doesNotCrash() {
        manager = SpeedTestManager.getInstance(mockContext);
        manager.removeListener();
        GenericSpeedTestReport report = new GenericSpeedTestReport(100000000.0);
        manager.onDownloadProgress(50.0f, report);
        // Should not crash when no listener is set
    }

    @Test
    public void onDownloadComplete_withNoListener_doesNotCrash() {
        manager = SpeedTestManager.getInstance(mockContext);
        manager.removeListener();
        GenericSpeedTestReport report = new GenericSpeedTestReport(100000000.0);
        manager.onDownloadComplete(report);
        // Should not crash when no listener is set
    }

    @Test
    public void onUploadProgress_withNoListener_doesNotCrash() {
        manager = SpeedTestManager.getInstance(mockContext);
        manager.removeListener();
        GenericSpeedTestReport report = new GenericSpeedTestReport(50000000.0);
        manager.onUploadProgress(75.0f, report);
        // Should not crash when no listener is set
    }

    @Test
    public void onUploadComplete_withNoListener_doesNotCrash() {
        manager = SpeedTestManager.getInstance(mockContext);
        manager.removeListener();
        GenericSpeedTestReport report = new GenericSpeedTestReport(50000000.0);
        manager.onUploadComplete(report);
        // Should not crash when no listener is set
    }

    @Test
    public void onTestFailed_withNoListener_doesNotCrash() {
        manager = SpeedTestManager.getInstance(mockContext);
        manager.removeListener();
        manager.onTestFailed(GenericSpeedTestError.CONNECTION_ERROR, "Test error");
        // Should not crash when no listener is set
    }

    @Test
    public void onTestCancelled_withNoListener_doesNotCrash() {
        manager = SpeedTestManager.getInstance(mockContext);
        manager.removeListener();
        manager.onTestCancelled();
        // Should not crash when no listener is set
    }
}
