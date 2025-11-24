package com.example.wifissistor2j;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for SignalStrengthMapper
 */
public class SignalStrengthMapperTest {

    @Test
    public void getSignalStrength_excellentRange() {
        assertEquals("Excellent", SignalStrengthMapper.getSignalStrength(-30));
        assertEquals("Excellent", SignalStrengthMapper.getSignalStrength(-40));
        assertEquals("Excellent", SignalStrengthMapper.getSignalStrength(-49));
        assertEquals("Excellent", SignalStrengthMapper.getSignalStrength(-50));
    }

    @Test
    public void getSignalStrength_goodRange() {
        assertEquals("Good", SignalStrengthMapper.getSignalStrength(-51));
        assertEquals("Good", SignalStrengthMapper.getSignalStrength(-60));
        assertEquals("Good", SignalStrengthMapper.getSignalStrength(-66));
        assertEquals("Good", SignalStrengthMapper.getSignalStrength(-67));
    }

    @Test
    public void getSignalStrength_fairRange() {
        assertEquals("Fair", SignalStrengthMapper.getSignalStrength(-68));
        assertEquals("Fair", SignalStrengthMapper.getSignalStrength(-69));
        assertEquals("Fair", SignalStrengthMapper.getSignalStrength(-70));
    }

    @Test
    public void getSignalStrength_weakRange() {
        assertEquals("Weak", SignalStrengthMapper.getSignalStrength(-71));
        assertEquals("Weak", SignalStrengthMapper.getSignalStrength(-80));
        assertEquals("Weak", SignalStrengthMapper.getSignalStrength(-90));
        assertEquals("Weak", SignalStrengthMapper.getSignalStrength(-100));
    }

    @Test
    public void getSignalStrength_edgeCases() {
        // Very strong signal
        assertEquals("Excellent", SignalStrengthMapper.getSignalStrength(-10));
        assertEquals("Excellent", SignalStrengthMapper.getSignalStrength(0));
        
        // Very weak signal
        assertEquals("Weak", SignalStrengthMapper.getSignalStrength(-110));
        assertEquals("Weak", SignalStrengthMapper.getSignalStrength(-120));
    }

    @Test
    public void getSignalStrengthAsDistance_practicallyOnTopOfIt() {
        assertEquals("You're practically on top of it!", SignalStrengthMapper.getSignalStrengthAsDistance(-30));
        assertEquals("You're practically on top of it!", SignalStrengthMapper.getSignalStrengthAsDistance(-39));
        assertEquals("You're practically on top of it!", SignalStrengthMapper.getSignalStrengthAsDistance(-40));
    }

    @Test
    public void getSignalStrengthAsDistance_veryClose() {
        assertEquals("Very close", SignalStrengthMapper.getSignalStrengthAsDistance(-41));
        assertEquals("Very close", SignalStrengthMapper.getSignalStrengthAsDistance(-50));
        assertEquals("Very close", SignalStrengthMapper.getSignalStrengthAsDistance(-54));
        assertEquals("Very close", SignalStrengthMapper.getSignalStrengthAsDistance(-55));
    }

    @Test
    public void getSignalStrengthAsDistance_gettingCloser() {
        assertEquals("Getting closer", SignalStrengthMapper.getSignalStrengthAsDistance(-56));
        assertEquals("Getting closer", SignalStrengthMapper.getSignalStrengthAsDistance(-60));
        assertEquals("Getting closer", SignalStrengthMapper.getSignalStrengthAsDistance(-64));
        assertEquals("Getting closer", SignalStrengthMapper.getSignalStrengthAsDistance(-65));
    }

    @Test
    public void getSignalStrengthAsDistance_distant() {
        assertEquals("Distant", SignalStrengthMapper.getSignalStrengthAsDistance(-66));
        assertEquals("Distant", SignalStrengthMapper.getSignalStrengthAsDistance(-70));
        assertEquals("Distant", SignalStrengthMapper.getSignalStrengthAsDistance(-74));
        assertEquals("Distant", SignalStrengthMapper.getSignalStrengthAsDistance(-75));
    }

    @Test
    public void getSignalStrengthAsDistance_veryDistant() {
        assertEquals("Very distant", SignalStrengthMapper.getSignalStrengthAsDistance(-76));
        assertEquals("Very distant", SignalStrengthMapper.getSignalStrengthAsDistance(-80));
        assertEquals("Very distant", SignalStrengthMapper.getSignalStrengthAsDistance(-90));
        assertEquals("Very distant", SignalStrengthMapper.getSignalStrengthAsDistance(-100));
    }

    @Test
    public void getSignalStrengthAsDistance_edgeCases() {
        // Very strong signal
        assertEquals("You're practically on top of it!", SignalStrengthMapper.getSignalStrengthAsDistance(-10));
        assertEquals("You're practically on top of it!", SignalStrengthMapper.getSignalStrengthAsDistance(0));
        
        // Very weak signal
        assertEquals("Very distant", SignalStrengthMapper.getSignalStrengthAsDistance(-110));
        assertEquals("Very distant", SignalStrengthMapper.getSignalStrengthAsDistance(-120));
    }
}
