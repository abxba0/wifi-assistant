package com.example.wifissistor2j;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for NetworkSecurityChecker
 */
public class NetworkSecurityCheckerTest {

    @Test
    public void checkSecurity_wpa3Network() {
        NetworkSecurityChecker.SecurityResult result = 
                NetworkSecurityChecker.checkSecurity("[WPA3-SAE-CCMP][ESS]");
        assertTrue(result.isSecure());
        assertEquals("Secure", result.getLabel());
        assertEquals("WPA3", result.getSecurityType());
    }

    @Test
    public void checkSecurity_wpa2Network() {
        NetworkSecurityChecker.SecurityResult result = 
                NetworkSecurityChecker.checkSecurity("[WPA2-PSK-CCMP][ESS]");
        assertTrue(result.isSecure());
        assertEquals("Secure", result.getLabel());
        assertEquals("WPA2", result.getSecurityType());
    }

    @Test
    public void checkSecurity_wpaNetwork() {
        NetworkSecurityChecker.SecurityResult result = 
                NetworkSecurityChecker.checkSecurity("[WPA-PSK-TKIP][ESS]");
        assertTrue(result.isSecure());
        assertEquals("Secure", result.getLabel());
        assertEquals("WPA", result.getSecurityType());
    }

    @Test
    public void checkSecurity_wepNetwork() {
        NetworkSecurityChecker.SecurityResult result = 
                NetworkSecurityChecker.checkSecurity("[WEP][ESS]");
        assertFalse(result.isSecure());
        assertEquals("Weak Security", result.getLabel());
        assertEquals("WEP", result.getSecurityType());
        assertTrue(result.getAdvice().contains("outdated"));
    }

    @Test
    public void checkSecurity_openNetwork() {
        NetworkSecurityChecker.SecurityResult result = 
                NetworkSecurityChecker.checkSecurity("[ESS]");
        assertFalse(result.isSecure());
        assertEquals("Unsecured", result.getLabel());
        assertEquals("Open", result.getSecurityType());
        assertTrue(result.getAdvice().toLowerCase().contains("open"));
    }

    @Test
    public void checkSecurity_nullCapabilities() {
        NetworkSecurityChecker.SecurityResult result = 
                NetworkSecurityChecker.checkSecurity(null);
        assertFalse(result.isSecure());
        assertEquals("Unknown", result.getLabel());
        assertEquals("Unknown", result.getSecurityType());
    }

    @Test
    public void checkSecurity_emptyCapabilities() {
        NetworkSecurityChecker.SecurityResult result = 
                NetworkSecurityChecker.checkSecurity("");
        assertFalse(result.isSecure());
        assertEquals("Unknown", result.getLabel());
    }

    @Test
    public void checkSecurity_mixedWpa2Wpa3() {
        // WPA3 should take priority
        NetworkSecurityChecker.SecurityResult result = 
                NetworkSecurityChecker.checkSecurity("[WPA2-PSK-CCMP][WPA3-SAE-CCMP][ESS]");
        assertTrue(result.isSecure());
        assertEquals("Secure", result.getLabel());
        assertEquals("WPA3", result.getSecurityType());
    }

    @Test
    public void getSimpleSecurityLabel_secure() {
        assertEquals("Secure", NetworkSecurityChecker.getSimpleSecurityLabel("[WPA2-PSK-CCMP][ESS]"));
        assertEquals("Secure", NetworkSecurityChecker.getSimpleSecurityLabel("[WPA3-SAE][ESS]"));
    }

    @Test
    public void getSimpleSecurityLabel_unsecured() {
        assertEquals("Unsecured", NetworkSecurityChecker.getSimpleSecurityLabel("[ESS]"));
        assertEquals("Unsecured", NetworkSecurityChecker.getSimpleSecurityLabel("[WEP][ESS]"));
    }

    @Test
    public void isNetworkSecure_trueForWpa() {
        assertTrue(NetworkSecurityChecker.isNetworkSecure("[WPA2-PSK-CCMP][ESS]"));
        assertTrue(NetworkSecurityChecker.isNetworkSecure("[WPA3-SAE][ESS]"));
        assertTrue(NetworkSecurityChecker.isNetworkSecure("[WPA-PSK][ESS]"));
    }

    @Test
    public void isNetworkSecure_falseForOpen() {
        assertFalse(NetworkSecurityChecker.isNetworkSecure("[ESS]"));
        assertFalse(NetworkSecurityChecker.isNetworkSecure(""));
        assertFalse(NetworkSecurityChecker.isNetworkSecure(null));
    }

    @Test
    public void isNetworkSecure_falseForWep() {
        assertFalse(NetworkSecurityChecker.isNetworkSecure("[WEP][ESS]"));
    }

    @Test
    public void checkSecurity_caseInsensitive() {
        NetworkSecurityChecker.SecurityResult upper = 
                NetworkSecurityChecker.checkSecurity("[WPA2-PSK-CCMP][ESS]");
        NetworkSecurityChecker.SecurityResult lower = 
                NetworkSecurityChecker.checkSecurity("[wpa2-psk-ccmp][ess]");
        
        assertEquals(upper.isSecure(), lower.isSecure());
        assertEquals(upper.getSecurityType(), lower.getSecurityType());
    }

    @Test
    public void checkSecurity_adviceNotEmpty() {
        NetworkSecurityChecker.SecurityResult secure = 
                NetworkSecurityChecker.checkSecurity("[WPA2-PSK-CCMP][ESS]");
        NetworkSecurityChecker.SecurityResult open = 
                NetworkSecurityChecker.checkSecurity("[ESS]");
        
        assertFalse(secure.getAdvice().isEmpty());
        assertFalse(open.getAdvice().isEmpty());
    }
}
