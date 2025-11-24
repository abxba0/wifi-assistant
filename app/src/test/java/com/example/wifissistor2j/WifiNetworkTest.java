package com.example.wifissistor2j;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for WifiNetwork
 */
public class WifiNetworkTest {

    @Test
    public void constructor_validParameters() {
        WifiNetwork network = new WifiNetwork("TestSSID", "Excellent");
        assertNotNull(network);
    }

    @Test
    public void toString_formatsCorrectly() {
        WifiNetwork network = new WifiNetwork("TestSSID", "Excellent");
        String expected = "TestSSID\t\tExcellent";
        assertEquals(expected, network.toString());
    }

    @Test
    public void toString_withDifferentValues() {
        WifiNetwork network1 = new WifiNetwork("Home WiFi", "Good");
        assertEquals("Home WiFi\t\tGood", network1.toString());

        WifiNetwork network2 = new WifiNetwork("Office", "Fair");
        assertEquals("Office\t\tFair", network2.toString());

        WifiNetwork network3 = new WifiNetwork("Guest", "Weak");
        assertEquals("Guest\t\tWeak", network3.toString());
    }

    @Test
    public void toString_withEmptySSID() {
        WifiNetwork network = new WifiNetwork("", "Excellent");
        assertEquals("\t\tExcellent", network.toString());
    }

    @Test
    public void toString_withEmptySignalStrength() {
        WifiNetwork network = new WifiNetwork("TestSSID", "");
        assertEquals("TestSSID\t\t", network.toString());
    }

    @Test
    public void toString_withSpecialCharactersInSSID() {
        WifiNetwork network = new WifiNetwork("Test-WiFi_2.4GHz", "Good");
        assertEquals("Test-WiFi_2.4GHz\t\tGood", network.toString());
    }

    @Test
    public void toString_withLongSSID() {
        String longSSID = "VeryLongSSIDNameThatSomeoneDecidedToUse";
        WifiNetwork network = new WifiNetwork(longSSID, "Excellent");
        assertEquals(longSSID + "\t\tExcellent", network.toString());
    }

    @Test
    public void toString_withUnicodeCharacters() {
        WifiNetwork network = new WifiNetwork("WiFi™", "Good");
        assertEquals("WiFi™\t\tGood", network.toString());
    }
}
