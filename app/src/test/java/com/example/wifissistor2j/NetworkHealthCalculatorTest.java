package com.example.wifissistor2j;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for NetworkHealthCalculator
 */
public class NetworkHealthCalculatorTest {

    @Test
    public void calculateSignalScore_excellentSignal() {
        assertEquals(50, NetworkHealthCalculator.calculateSignalScore(-30));
        assertEquals(50, NetworkHealthCalculator.calculateSignalScore(-40));
        assertEquals(50, NetworkHealthCalculator.calculateSignalScore(-50));
    }

    @Test
    public void calculateSignalScore_goodSignal() {
        assertEquals(40, NetworkHealthCalculator.calculateSignalScore(-51));
        assertEquals(40, NetworkHealthCalculator.calculateSignalScore(-55));
        assertEquals(40, NetworkHealthCalculator.calculateSignalScore(-60));
    }

    @Test
    public void calculateSignalScore_fairSignal() {
        assertEquals(30, NetworkHealthCalculator.calculateSignalScore(-61));
        assertEquals(30, NetworkHealthCalculator.calculateSignalScore(-65));
        assertEquals(30, NetworkHealthCalculator.calculateSignalScore(-70));
    }

    @Test
    public void calculateSignalScore_weakSignal() {
        assertEquals(20, NetworkHealthCalculator.calculateSignalScore(-71));
        assertEquals(20, NetworkHealthCalculator.calculateSignalScore(-75));
        assertEquals(20, NetworkHealthCalculator.calculateSignalScore(-80));
    }

    @Test
    public void calculateSignalScore_veryWeakSignal() {
        assertEquals(10, NetworkHealthCalculator.calculateSignalScore(-81));
        assertEquals(10, NetworkHealthCalculator.calculateSignalScore(-90));
        assertEquals(10, NetworkHealthCalculator.calculateSignalScore(-100));
    }

    @Test
    public void calculateSpeedScore_excellentSpeed() {
        assertEquals(50, NetworkHealthCalculator.calculateSpeedScore(100));
        assertEquals(50, NetworkHealthCalculator.calculateSpeedScore(150));
        assertEquals(50, NetworkHealthCalculator.calculateSpeedScore(500));
    }

    @Test
    public void calculateSpeedScore_veryGoodSpeed() {
        assertEquals(40, NetworkHealthCalculator.calculateSpeedScore(50));
        assertEquals(40, NetworkHealthCalculator.calculateSpeedScore(75));
        assertEquals(40, NetworkHealthCalculator.calculateSpeedScore(99));
    }

    @Test
    public void calculateSpeedScore_goodSpeed() {
        assertEquals(30, NetworkHealthCalculator.calculateSpeedScore(25));
        assertEquals(30, NetworkHealthCalculator.calculateSpeedScore(35));
        assertEquals(30, NetworkHealthCalculator.calculateSpeedScore(49));
    }

    @Test
    public void calculateSpeedScore_fairSpeed() {
        assertEquals(20, NetworkHealthCalculator.calculateSpeedScore(10));
        assertEquals(20, NetworkHealthCalculator.calculateSpeedScore(15));
        assertEquals(20, NetworkHealthCalculator.calculateSpeedScore(24));
    }

    @Test
    public void calculateSpeedScore_slowSpeed() {
        assertEquals(15, NetworkHealthCalculator.calculateSpeedScore(5));
        assertEquals(15, NetworkHealthCalculator.calculateSpeedScore(7));
        assertEquals(15, NetworkHealthCalculator.calculateSpeedScore(9));
    }

    @Test
    public void calculateSpeedScore_verySlowSpeed() {
        assertEquals(10, NetworkHealthCalculator.calculateSpeedScore(0));
        assertEquals(10, NetworkHealthCalculator.calculateSpeedScore(2));
        assertEquals(10, NetworkHealthCalculator.calculateSpeedScore(4.9));
    }

    @Test
    public void calculate_excellentHealth() {
        NetworkHealthCalculator.HealthScore score = NetworkHealthCalculator.calculate(-40, 120);
        assertEquals(100, score.getScore());
        assertEquals("Excellent", score.getLabel());
        assertTrue(score.getTip().isEmpty()); // No tip needed for excellent
    }

    @Test
    public void calculate_goodHealth() {
        NetworkHealthCalculator.HealthScore score = NetworkHealthCalculator.calculate(-55, 60);
        assertEquals(80, score.getScore());
        assertEquals("Excellent", score.getLabel());
    }

    @Test
    public void calculate_fairHealth() {
        NetworkHealthCalculator.HealthScore score = NetworkHealthCalculator.calculate(-75, 15);
        assertEquals(40, score.getScore());
        assertEquals("Fair", score.getLabel());
        assertFalse(score.getTip().isEmpty()); // Should have a tip
    }

    @Test
    public void calculate_poorHealth() {
        NetworkHealthCalculator.HealthScore score = NetworkHealthCalculator.calculate(-90, 3);
        assertEquals(20, score.getScore());
        assertEquals("Poor", score.getLabel());
        assertFalse(score.getTip().isEmpty());
    }

    @Test
    public void getLabel_boundaryValues() {
        assertEquals("Excellent", NetworkHealthCalculator.getLabel(100));
        assertEquals("Excellent", NetworkHealthCalculator.getLabel(80));
        assertEquals("Good", NetworkHealthCalculator.getLabel(79));
        assertEquals("Good", NetworkHealthCalculator.getLabel(60));
        assertEquals("Fair", NetworkHealthCalculator.getLabel(59));
        assertEquals("Fair", NetworkHealthCalculator.getLabel(40));
        assertEquals("Poor", NetworkHealthCalculator.getLabel(39));
        assertEquals("Poor", NetworkHealthCalculator.getLabel(0));
    }

    @Test
    public void getSignalBars_allLevels() {
        assertEquals(5, NetworkHealthCalculator.getSignalBars(-40));
        assertEquals(5, NetworkHealthCalculator.getSignalBars(-50));
        assertEquals(4, NetworkHealthCalculator.getSignalBars(-51));
        assertEquals(4, NetworkHealthCalculator.getSignalBars(-60));
        assertEquals(3, NetworkHealthCalculator.getSignalBars(-61));
        assertEquals(3, NetworkHealthCalculator.getSignalBars(-70));
        assertEquals(2, NetworkHealthCalculator.getSignalBars(-71));
        assertEquals(2, NetworkHealthCalculator.getSignalBars(-80));
        assertEquals(1, NetworkHealthCalculator.getSignalBars(-81));
        assertEquals(1, NetworkHealthCalculator.getSignalBars(-100));
    }

    @Test
    public void getSignalBarColor_allColors() {
        assertEquals("green", NetworkHealthCalculator.getSignalBarColor(-40));
        assertEquals("green", NetworkHealthCalculator.getSignalBarColor(-50));
        assertEquals("light_green", NetworkHealthCalculator.getSignalBarColor(-55));
        assertEquals("light_green", NetworkHealthCalculator.getSignalBarColor(-60));
        assertEquals("yellow", NetworkHealthCalculator.getSignalBarColor(-65));
        assertEquals("yellow", NetworkHealthCalculator.getSignalBarColor(-70));
        assertEquals("orange", NetworkHealthCalculator.getSignalBarColor(-75));
        assertEquals("orange", NetworkHealthCalculator.getSignalBarColor(-80));
        assertEquals("red", NetworkHealthCalculator.getSignalBarColor(-85));
        assertEquals("red", NetworkHealthCalculator.getSignalBarColor(-100));
    }

    @Test
    public void getTip_signalIsMainIssue() {
        // Signal is weak (-85), speed is good (50)
        NetworkHealthCalculator.HealthScore score = NetworkHealthCalculator.calculate(-85, 50);
        assertTrue(score.getTip().contains("closer") || score.getTip().contains("signal"));
    }

    @Test
    public void getTip_speedIsMainIssue() {
        // Signal is good (-50), speed is slow (3)
        NetworkHealthCalculator.HealthScore score = NetworkHealthCalculator.calculate(-50, 3);
        assertTrue(score.getTip().contains("slow") || score.getTip().contains("network") || score.getTip().contains("connection"));
    }
}
