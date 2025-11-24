package com.example.wifissistor2j;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for ShareHelper
 */
public class ShareHelperTest {

    @Test
    public void createShareableText_containsHealthInfo() {
        String text = ShareHelper.createShareableText(
                "Good", 75, 45.5, 10.2, "Good Signal", true);
        
        assertTrue("Should contain health label", text.contains("Good"));
        assertTrue("Should contain score", text.contains("75"));
    }

    @Test
    public void createShareableText_containsSpeedInfo() {
        String text = ShareHelper.createShareableText(
                "Good", 75, 45.5, 10.2, "Good Signal", true);
        
        assertTrue("Should contain download speed", text.contains("45.5"));
        assertTrue("Should contain upload speed", text.contains("10.2"));
        assertTrue("Should contain Mbps", text.contains("Mbps"));
    }

    @Test
    public void createShareableText_containsSignalInfo() {
        String text = ShareHelper.createShareableText(
                "Good", 75, 45.5, 10.2, "Good Signal", true);
        
        assertTrue("Should contain signal quality", text.contains("Good Signal"));
    }

    @Test
    public void createShareableText_showsSecureStatus() {
        String secureText = ShareHelper.createShareableText(
                "Good", 75, 45.5, 10.2, "Good Signal", true);
        
        assertTrue("Should indicate secure", 
                secureText.contains("Secure") || secureText.contains("🔒"));
    }

    @Test
    public void createShareableText_showsOpenStatus() {
        String openText = ShareHelper.createShareableText(
                "Good", 75, 45.5, 10.2, "Good Signal", false);
        
        assertTrue("Should indicate open/unsecured", 
                openText.contains("Open") || openText.contains("⚠️"));
    }

    @Test
    public void createShareableText_containsAppName() {
        String text = ShareHelper.createShareableText(
                "Good", 75, 45.5, 10.2, "Good Signal", true);
        
        assertTrue("Should mention app", text.contains("Wi-Fi Assistant"));
    }

    @Test
    public void createSimpleShareableText_isShort() {
        String text = ShareHelper.createSimpleShareableText("Good", 45.5);
        
        // Should be a single line
        assertFalse("Should be single line", text.contains("\n"));
        assertTrue("Should be reasonably short", text.length() < 100);
    }

    @Test
    public void createSimpleShareableText_containsLabel() {
        String text = ShareHelper.createSimpleShareableText("Excellent", 100.0);
        
        assertTrue("Should contain health label", text.contains("Excellent"));
    }

    @Test
    public void createSimpleShareableText_containsSpeed() {
        String text = ShareHelper.createSimpleShareableText("Good", 45.0);
        
        assertTrue("Should contain speed", text.contains("45") || text.contains("Mbps"));
    }

    @Test
    public void createSimpleShareableText_containsEmoji() {
        String text = ShareHelper.createSimpleShareableText("Good", 45.0);
        
        assertTrue("Should contain Wi-Fi emoji", text.contains("📶"));
    }

    @Test
    public void createShareIntent_hasCorrectType() {
        android.content.Intent intent = ShareHelper.createShareIntent("Test text");
        
        assertEquals("text/plain", intent.getType());
    }

    @Test
    public void createShareIntent_containsText() {
        String testText = "Test share text";
        android.content.Intent intent = ShareHelper.createShareIntent(testText);
        
        assertEquals(testText, intent.getStringExtra(android.content.Intent.EXTRA_TEXT));
    }

    @Test
    public void createShareIntent_isSendAction() {
        android.content.Intent intent = ShareHelper.createShareIntent("Test");
        
        assertEquals(android.content.Intent.ACTION_SEND, intent.getAction());
    }

    @Test
    public void createShareableText_handlesZeroSpeed() {
        String text = ShareHelper.createShareableText(
                "Poor", 20, 0.0, 0.0, "Weak", false);
        
        assertNotNull(text);
        assertFalse(text.isEmpty());
    }

    @Test
    public void createShareableText_handlesHighSpeed() {
        String text = ShareHelper.createShareableText(
                "Excellent", 100, 1000.0, 500.0, "Excellent", true);
        
        assertNotNull(text);
        assertTrue(text.contains("1000") || text.contains("1,000"));
    }

    @Test
    public void createShareableText_formattedNicely() {
        String text = ShareHelper.createShareableText(
                "Good", 75, 45.5, 10.2, "Good Signal", true);
        
        // Check for basic formatting
        assertTrue("Should have line breaks", text.contains("\n"));
        assertTrue("Should have header emoji", text.contains("📶"));
    }
}
