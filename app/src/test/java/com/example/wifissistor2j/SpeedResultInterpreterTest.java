package com.example.wifissistor2j;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for SpeedResultInterpreter
 */
public class SpeedResultInterpreterTest {

    @Test
    public void interpret_excellentSpeed() {
        assertEquals("Excellent", SpeedResultInterpreter.interpret(100));
        assertEquals("Excellent", SpeedResultInterpreter.interpret(150));
        assertEquals("Excellent", SpeedResultInterpreter.interpret(200));
        assertEquals("Excellent", SpeedResultInterpreter.interpret(500));
        assertEquals("Excellent", SpeedResultInterpreter.interpret(1000));
    }

    @Test
    public void interpret_veryGoodSpeed() {
        assertEquals("Very Good", SpeedResultInterpreter.interpret(40));
        assertEquals("Very Good", SpeedResultInterpreter.interpret(50));
        assertEquals("Very Good", SpeedResultInterpreter.interpret(75));
        assertEquals("Very Good", SpeedResultInterpreter.interpret(99));
    }

    @Test
    public void interpret_goodSpeed() {
        assertEquals("Good", SpeedResultInterpreter.interpret(10));
        assertEquals("Good", SpeedResultInterpreter.interpret(20));
        assertEquals("Good", SpeedResultInterpreter.interpret(30));
        assertEquals("Good", SpeedResultInterpreter.interpret(39));
    }

    @Test
    public void interpret_fairSpeed() {
        assertEquals("Fair", SpeedResultInterpreter.interpret(5));
        assertEquals("Fair", SpeedResultInterpreter.interpret(7));
        assertEquals("Fair", SpeedResultInterpreter.interpret(9));
    }

    @Test
    public void interpret_poorSpeed() {
        assertEquals("Poor", SpeedResultInterpreter.interpret(0));
        assertEquals("Poor", SpeedResultInterpreter.interpret(1));
        assertEquals("Poor", SpeedResultInterpreter.interpret(2));
        assertEquals("Poor", SpeedResultInterpreter.interpret(4));
        assertEquals("Poor", SpeedResultInterpreter.interpret(4.9));
    }

    @Test
    public void interpret_boundaryValues() {
        // Test exact boundary values
        assertEquals("Excellent", SpeedResultInterpreter.interpret(100.0));
        assertEquals("Very Good", SpeedResultInterpreter.interpret(99.9));
        assertEquals("Very Good", SpeedResultInterpreter.interpret(40.0));
        assertEquals("Good", SpeedResultInterpreter.interpret(39.9));
        assertEquals("Good", SpeedResultInterpreter.interpret(10.0));
        assertEquals("Fair", SpeedResultInterpreter.interpret(9.9));
        assertEquals("Fair", SpeedResultInterpreter.interpret(5.0));
        assertEquals("Poor", SpeedResultInterpreter.interpret(4.9));
    }

    @Test
    public void interpret_negativeSpeed() {
        // Should handle edge cases gracefully
        assertEquals("Poor", SpeedResultInterpreter.interpret(-1));
        assertEquals("Poor", SpeedResultInterpreter.interpret(-10));
    }

    @Test
    public void interpret_verySmallSpeed() {
        assertEquals("Poor", SpeedResultInterpreter.interpret(0.1));
        assertEquals("Poor", SpeedResultInterpreter.interpret(0.5));
        assertEquals("Poor", SpeedResultInterpreter.interpret(0.9));
    }

    @Test
    public void interpret_decimalValues() {
        assertEquals("Excellent", SpeedResultInterpreter.interpret(100.5));
        assertEquals("Very Good", SpeedResultInterpreter.interpret(40.1));
        assertEquals("Good", SpeedResultInterpreter.interpret(10.5));
        assertEquals("Fair", SpeedResultInterpreter.interpret(5.5));
    }
}
